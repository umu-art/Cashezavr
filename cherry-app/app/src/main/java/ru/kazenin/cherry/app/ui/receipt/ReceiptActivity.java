package ru.kazenin.cherry.app.ui.receipt;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import lombok.var;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.databinding.ActivityReceiptBinding;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var binding = ActivityReceiptBinding.inflate(getLayoutInflater());

        binding.textView.setText(DataHolder.lastReceipt.toString());

        setContentView(binding.getRoot());
    }
}