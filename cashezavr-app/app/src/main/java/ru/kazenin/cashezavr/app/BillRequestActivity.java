package ru.kazenin.cashezavr.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import lombok.val;
import lombok.var;
import ru.kazenin.ApiException;
import ru.kazenin.cashezavr.app.data.ApiHolder;
import ru.kazenin.cashezavr.app.data.DataHolder;
import ru.kazenin.cashezavr.app.databinding.ActivityBillRequestBinding;
import ru.kazenin.model.BillRequireDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Objects.nonNull;

public class BillRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var binding = ActivityBillRequestBinding.inflate(getLayoutInflater());

        var balance = "0.0";
        if (nonNull(DataHolder.clientDto)) {
            balance = DataHolder.clientDto.getActualBalance()
                    .setScale(2, RoundingMode.HALF_EVEN).toString();
        }

        binding.available.setText("Всего доступно: " + balance);
        binding.sum.setText(balance);
        binding.send.setOnClickListener((a) ->
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        val dto = new BillRequireDto();
                        dto.setSum(BigDecimal.valueOf(
                                Double.parseDouble(
                                        binding.sum.getText().toString())));

                        try {
                            ApiHolder.billApi.requireBill(dto);
                        } catch (ApiException e) {
                            throw new RuntimeException(e);
                        }

                        DataHolder.recallBills();
                        DataHolder.recallClient();

                        runOnUiThread(() -> finish());
                    }
                }, 0));

        setContentView(binding.getRoot());
    }
}