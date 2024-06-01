package ru.kazenin.cherry.app.ui.bills;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.apache.commons.lang3.time.DateFormatUtils;
import ru.kazenin.cherry.app.databinding.FragmentBillsBinding;
import ru.kazenin.model.BillDto;

import java.math.RoundingMode;
import java.util.List;

public class BillsRecyclerViewAdapter extends RecyclerView.Adapter<BillsRecyclerViewAdapter.ViewHolder> {

    private final List<BillDto> mValues;

    public BillsRecyclerViewAdapter(List<BillDto> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentBillsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.dateView.setText("Время запроса: " +
                DateFormatUtils.format(mValues.get(position).getCreated(), "HH:mm dd.MM.yyyy"));
        holder.sumView.setText("Сумма: " + mValues.get(position).getSum()
                .setScale(2, RoundingMode.HALF_EVEN).toString() + " руб.");
        holder.statusView.setText("Статус: " + mValues.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateView;
        public final TextView sumView;
        public final TextView statusView;
        public BillDto mItem;

        public ViewHolder(FragmentBillsBinding binding) {
            super(binding.getRoot());
            dateView = binding.date;
            sumView = binding.sum;
            statusView = binding.status;
        }

        @Override
        public String toString() {
            return super.toString() + " '" +
                    dateView.getText() + " " +
                    sumView.getText() + " " +
                    statusView.getText() + "'";
        }
    }
}