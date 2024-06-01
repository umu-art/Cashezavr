package ru.kazenin.cherry.app.ui.load;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import lombok.SneakyThrows;
import lombok.var;
import ru.kazenin.ApiException;
import ru.kazenin.cherry.app.MainActivity;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.data.ApiHolder;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.ui.receipt.ReceiptActivity;
import ru.kazenin.cherry.app.ui.scan.ScanFragment;
import ru.kazenin.model.ReceiptDto;
import ru.kazenin.model.ReceiptRequestDto;

import java.util.Timer;
import java.util.TimerTask;

public class ReceiptLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_load);
        var qr = getIntent().getCharSequenceExtra("qr");
        Log.d("receipt load", "onCreate: attempt to load " + qr);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                loadReceipt((String) qr);
            }
        }, 0);
    }

    @SneakyThrows
    private void loadReceipt(String qr) {
        try {
            var receiptLoadRequest = new ReceiptRequestDto();
            receiptLoadRequest.setQr(qr);
            DataHolder.lastReceipt = ApiHolder.receiptApi.loadReceipt(receiptLoadRequest);
            runOnUiThread(() -> {
                startActivity(new Intent(this, ReceiptActivity.class));
                finish();
            });
        } catch (ApiException e) {
            if (e.getCode() == 409) {

            } else if (e.getCode() == 201) {
                DataHolder.lastReceipt = new ReceiptDto();
                runOnUiThread(() -> {
                    startActivity(new Intent(this, ReceiptActivity.class));
                    finish();
                });
            } else {
                Log.e("receipt load", "error while loading: ", e);
            }

            runOnUiThread(() -> {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });
        }
        ScanFragment.loading = false;
        finish();
    }
}