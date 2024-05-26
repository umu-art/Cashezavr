package ru.kazenin.cherry.app.ui.receipts;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import lombok.var;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.data.DataHolder;

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
        View view = inflater.inflate(R.layout.fragment_receipts_list, container, false);
        var activity = this.getActivity();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setReceipts(activity, view);
            }
        }, 0, DataHolder.updatePeriod);

        return view;
    }

    private void setReceipts(Activity activity, View view) {
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            DataHolder.fillReceiptDtosIfNull();
            activity.runOnUiThread(() ->
                    recyclerView.setAdapter(new ReceiptsRecyclerViewAdapter(DataHolder.receiptDtos)));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
        timer.purge();
    }
}