package ru.kazenin.cherry.app.ui.bills;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import lombok.var;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.databinding.FragmentBillsListBinding;

import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

public class BillsFragment extends Fragment {

    private final Timer timer = new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        var binding = FragmentBillsListBinding.inflate(inflater, container, false);
        var activity = this.getActivity();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setBills(activity, binding);
            }
        }, 0, DataHolder.updatePeriod);

        binding.billCall.setOnClickListener((a) -> {
            // TODO: make bill
        });

        return binding.getRoot();
    }

    private void setBills(Activity activity, FragmentBillsListBinding binding) {
        DataHolder.fillBillsIfNull();
        DataHolder.fillClientDtoIfNull();
        activity.runOnUiThread(() -> {
            binding.balance.setText("Доступно: " + DataHolder.clientDto.getActualBalance()
                    .setScale(2, RoundingMode.HALF_EVEN).toString());
            binding.list.setAdapter(new BillsRecyclerViewAdapter(DataHolder.bills));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        timer.purge();
    }
}