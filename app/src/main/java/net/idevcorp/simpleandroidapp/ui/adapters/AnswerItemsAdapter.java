package net.idevcorp.simpleandroidapp.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.ItemModel;

import java.util.List;

public class AnswerItemsAdapter extends RecyclerView.Adapter<AnswerItemsAdapter.MyViewHolder> {

    private List<ItemModel> itemModels;

    public AnswerItemsAdapter(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewDisplayName = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_display_name, parent, false);
        return new MyViewHolder(viewDisplayName);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ItemModel itemModel = itemModels.get(position);
        holder.bind(itemModels.get(position));
//        holder.displayName.setText(itemModel.getOwner().getDisplayName());

    }

    @Override
    public int getItemCount() {
        if (itemModels == null) {
            return 0;
        } else {
            return itemModels.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView displayName;
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            displayName = itemView.findViewById(R.id.textViewDisplayName);
            text = itemView.findViewById(R.id.layout_display_text);
        }

        public void bind(ItemModel itemModel) {
            displayName.setText(itemModel.getOwner().getDisplayName());
            text.setText(itemModel.getOwner().getUserType());
        }

    }
}
