package ru.kazenin.cherry.app.ui.receipt;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import ru.kazenin.cherry.app.databinding.FragmentReceiptItemBinding;
import ru.kazenin.model.ReceiptItemDto;

import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
public class ReceiptItemsViewAdapter extends RecyclerView.Adapter<ReceiptItemsViewAdapter.ViewHolder> {

    private final List<ReceiptItemDto> receiptItemDtos;

    @NotNull
    @Override
    public ReceiptItemsViewAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentReceiptItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        var item = receiptItemDtos.get(i);

        holder.mItem = item;
        holder.name.setText(item.getName());
        holder.count.setText(item.getCount() + " шт.");
        holder.price.setText("Цена: " + item.getPrice()
                .setScale(2, RoundingMode.HALF_EVEN).toString() + " руб");
    }

    @Override
    public int getItemCount() {
        return receiptItemDtos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name;
        public final TextView count;
        public final TextView price;

        public ReceiptItemDto mItem;

        public ViewHolder(FragmentReceiptItemBinding binding) {
            super(binding.getRoot());
            name = binding.name;
            price = binding.price;
            count = binding.count;
        }

        @Override
        public String toString() {
            return super.toString() + " '" +
                    name.getText() + " " +
                    price.getText() + " " +
                    count.getText() + "'";
        }
    }
}
