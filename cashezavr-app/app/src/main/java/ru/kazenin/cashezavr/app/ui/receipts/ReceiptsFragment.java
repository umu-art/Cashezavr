package ru.kazenin.cashezavr.app.ui.receipts;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import lombok.var;
import ru.kazenin.cashezavr.app.data.DataHolder;
import ru.kazenin.cashezavr.app.databinding.FragmentReceiptsListBinding;

import java.util.Timer;
import java.util.TimerTask;

public class ReceiptsFragment extends Fragment {

    private final Timer timer = new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        var binding = FragmentReceiptsListBinding.inflate(inflater, container, false);
        var activity = this.getActivity();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setReceipts(activity, binding.list);
            }
        }, 0, DataHolder.updatePeriod);

        return binding.getRoot();
    }

    private void setReceipts(Activity activity, RecyclerView view) {
        DataHolder.fillReceiptDtosIfNull();
        activity.runOnUiThread(() ->
                view.setAdapter(new ReceiptsRecyclerViewAdapter(DataHolder.receiptDtos, activity)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        timer.purge();
    }
}