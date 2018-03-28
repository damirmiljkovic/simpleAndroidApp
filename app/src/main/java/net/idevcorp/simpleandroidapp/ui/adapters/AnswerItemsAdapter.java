package net.idevcorp.simpleandroidapp.ui.adapters;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.ItemModel;

import java.util.List;

public class AnswerItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemModel> itemModels;

    private static final int TYPE_REGISTERED   = 1;
    private static final int TYPE_UNREGISTERED = 2;

    private static final String REGISTERED = "registered";

    public AnswerItemsAdapter(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    @Nullable
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewDisplayName;
        if (viewType == TYPE_REGISTERED) {
            viewDisplayName = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_display_name, parent, false);
            return new MyViewHolder(viewDisplayName);
        } else if (viewType == TYPE_UNREGISTERED) {
            viewDisplayName = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_second_display_name, parent, false);
            return new MySecondViewHolder(viewDisplayName);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ItemModel itemModel = itemModels.get(position);
        if (holder != null) {
            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).bind(itemModels.get(position));
            } else if (holder instanceof MySecondViewHolder) {
                ((MySecondViewHolder) holder).bind(itemModels.get(position));
            }
        }
//        holder.displayName.setText(itemModel.getOwner().getDisplayName());

    }

    @Override
    public int getItemViewType(int position) {
        if (itemModels.get(position).getOwner().getUserType().equals(REGISTERED))
            return TYPE_REGISTERED;
        else if (itemModels.get(position).getOwner().getUserType().equals("unregistered")) {
            return TYPE_UNREGISTERED;
        } else {
            return super.getItemViewType(position);
        }
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

    public class MySecondViewHolder extends RecyclerView.ViewHolder {

        private TextView displayName;

        public MySecondViewHolder(View itemView) {
            super(itemView);
            displayName = itemView.findViewById(R.id.textViewDisplayName);
        }

        public void bind(ItemModel itemModel) {
            displayName.setText(itemModel.getOwner().getDisplayName());
        }

    }
}
