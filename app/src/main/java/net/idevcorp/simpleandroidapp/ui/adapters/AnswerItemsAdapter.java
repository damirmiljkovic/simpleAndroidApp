package net.idevcorp.simpleandroidapp.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.AnswerModel;

import java.util.ArrayList;
import java.util.List;

public class AnswerItemsAdapter extends RecyclerView.Adapter<AnswerItemsAdapter.MyViewHolder> {

    private List<AnswerModel> answerModelList;

    public AnswerItemsAdapter(List<AnswerModel>answerModelList){
        this.answerModelList = answerModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewDisplayName = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_display_name,parent,false);
        return new MyViewHolder(viewDisplayName);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AnswerModel answerModel = answerModelList.get(position);
        holder.displayName.setText(answerModel.getItems().get(position).getOwner().getDisplayName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView displayName;
        public MyViewHolder(View itemView) {
            super(itemView);
            displayName = itemView.findViewById(R.id.textViewDisplayName);
        }
    }
}
