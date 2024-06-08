package ru.kazenin.cashezavr.app.ui.bills;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import ru.kazenin.cashezavr.app.BillRequestActivity;
import ru.kazenin.cashezavr.app.data.DataHolder;
import ru.kazenin.cashezavr.app.databinding.FragmentBillsListBinding;

import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;

public class BillsFragment extends Fragment {

    private final Timer timer = new Timer();
    private Activity parentActivity;
    private FragmentBillsListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBillsListBinding.inflate(inflater, container, false);
        parentActivity = this.getActivity();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setBills(parentActivity, binding);
            }
        }, 0, DataHolder.updatePeriod);

        binding.billCall.setOnClickListener((a) -> {
            startActivity(new Intent(this.getActivity(), BillRequestActivity.class));
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setBills(parentActivity, binding);
            }
        }, 0);
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