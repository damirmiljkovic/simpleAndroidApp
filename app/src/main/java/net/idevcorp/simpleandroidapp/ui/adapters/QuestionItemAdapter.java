package net.idevcorp.simpleandroidapp.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.ItemQuestionModel;

import java.util.List;

public class QuestionItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemQuestionModel>itemList;
    public QuestionItemAdapter(List<ItemQuestionModel> itemList){
        this.itemList = itemList;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView questionTextView;
        public MyHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textViewQuestionId);
        }
        public void bindQuestion(ItemQuestionModel itemQuestion){
            questionTextView.setText(itemQuestion.getTitle());
        }
    }
    @Nullable
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewQuestion;
        viewQuestion = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question,parent,false);

        return new MyHolder(viewQuestion);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null){
            ((MyHolder)holder).bindQuestion(itemList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return itemList!=null?itemList.size():0;
    }
}
// TODO: 10.4.2018 implementirati recyclerView adapter za QuestionModel

/* if(itemList!-null){
return itemList.size()
}else{
return 0
}
*/
