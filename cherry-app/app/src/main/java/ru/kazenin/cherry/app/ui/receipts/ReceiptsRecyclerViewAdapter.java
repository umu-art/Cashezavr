package ru.kazenin.cherry.app.ui.receipts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import ru.kazenin.cherry.app.databinding.FragmentReceiptsBinding;
import ru.kazenin.model.ReceiptDto;

import java.util.List;

import static java.util.Objects.nonNull;

public class ReceiptsRecyclerViewAdapter extends RecyclerView.Adapter<ReceiptsRecyclerViewAdapter.ViewHolder> {

    private final List<ReceiptDto> mValues;

    public ReceiptsRecyclerViewAdapter(List<ReceiptDto> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentReceiptsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (nonNull(mValues.get(position).getDate())) {
            holder.dateView.setText(mValues.get(position).getDate().toString());
        }
        holder.shopView.setText(mValues.get(position).getShop());
        if (nonNull(mValues.get(position).getReturnSum())) {
            holder.sumView.setText(mValues.get(position).getReturnSum().toString());
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateView;
        public final TextView shopView;
        public final TextView sumView;
        public ReceiptDto mItem;

        public ViewHolder(FragmentReceiptsBinding binding) {
            super(binding.getRoot());
            dateView = binding.date;
            shopView = binding.shop;
            sumView = binding.sum;
        }

        @Override
        public String toString() {
            return super.toString() + " '" +
                    dateView.getText() + " " +
                    shopView.getText() + " " +
                    sumView.getText() + "'";
        }
    }
}