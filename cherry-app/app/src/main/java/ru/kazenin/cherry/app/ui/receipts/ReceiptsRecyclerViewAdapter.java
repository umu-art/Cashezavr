package ru.kazenin.cherry.app.ui.receipts;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.apache.commons.lang3.time.DateFormatUtils;
import ru.kazenin.cherry.app.R;
import ru.kazenin.cherry.app.data.DataHolder;
import ru.kazenin.cherry.app.databinding.FragmentReceiptsBinding;
import ru.kazenin.cherry.app.ui.receipt.ReceiptActivity;
import ru.kazenin.model.ReceiptDto;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.ViewHolder> {

    private final List<ReceiptDto> mValues;
    private final Activity activity;

    public ReceiptsRecyclerViewAdapter(List<ReceiptDto> items, Activity activity) {
        mValues = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentReceiptsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (isNull(mValues.get(position).getDate())) {
            holder.self.setBackgroundResource(R.color.purple_200);
            holder.shopView.setText("Чек загружается");
            return;
        }

        if (nonNull(mValues.get(position).getDate())) {
            holder.dateView.setText(
                    DateFormatUtils.format(mValues.get(position).getDate(), "HH:mm dd.MM.yyyy"));
        }
        holder.shopView.setText(mValues.get(position).getShop());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateView;
        public final TextView shopView;

        public final LinearLayout self;
        public ReceiptDto mItem;

        public ViewHolder(FragmentReceiptsBinding binding) {
            super(binding.getRoot());
            dateView = binding.date;
            shopView = binding.shop;
            self = binding.getRoot();
            binding.getRoot().setOnClickListener(v -> {
                DataHolder.lastReceipt = mItem;
                activity.startActivity(new Intent(activity, ReceiptActivity.class));
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" +
                    dateView.getText() + " " +
                    shopView.getText() + "'";
        }
    }
}