package ru.kazenin.cherry.app.ui.receipt;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import lombok.var;
import org.apache.commons.lang3.time.DateFormatUtils;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.databinding.ActivityReceiptBinding;

import java.math.RoundingMode;

import static java.util.Objects.isNull;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var binding = ActivityReceiptBinding.inflate(getLayoutInflater());

        var receipt = DataHolder.lastReceipt;

        if (isNull(receipt.getDate())) {
            binding.date.setText("Чек загружается, скоро всё будет готово");
        } else {
            binding.date.setText(
                    DateFormatUtils.format(receipt.getDate(), "HH:mm dd.MM.yyyy"));
            binding.shop.setText(receipt.getShop());
            binding.returnSum.setText("Кэшбэк: " + receipt.getReturnSum()
                    .setScale(2, RoundingMode.HALF_EVEN).toString() + " руб");

            binding.items.setAdapter(new ReceiptItemsViewAdapter(receipt.getReceiptItems()));
        }

        setContentView(binding.getRoot());
    }
}