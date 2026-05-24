package zubaida.begum.adu.ac.ae.lostandfounduae;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.description.setText(item.getDescription());
        holder.location.setText("Location: " + item.getLocation());
        holder.date.setText("Date: " + item.getDate());
        holder.type.setText(item.getType().toUpperCase());

        // Color code lost vs found
        if (item.getType().equals("lost")) {
            holder.type.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        } else {
            holder.type.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, description, location, date, type;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName    = itemView.findViewById(R.id.text_item_name);
            description = itemView.findViewById(R.id.text_description);
            location    = itemView.findViewById(R.id.text_location);
            date        = itemView.findViewById(R.id.text_date);
            type        = itemView.findViewById(R.id.text_type);
        }
    }
}
