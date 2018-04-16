package net.idevcorp.simpleandroidapp.ui.adapters;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.ItemQuestionModel;

import java.util.List;

public class QuestionItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemQuestionModel>itemList;
    private OnQuestionSelectedListener listener;

    public interface OnQuestionSelectedListener{
        void onQuestionSelected(ItemQuestionModel question);
    }

    public QuestionItemAdapter(List<ItemQuestionModel> itemList,OnQuestionSelectedListener listener){
        this.itemList = itemList;
        this.listener = listener;
    }


    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView questionTextView;
        private ImageView deleteQuestion;
        public MyHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textViewQuestionId);
            deleteQuestion = itemView.findViewById(R.id.deleteQuestion);
        }
        public void bindQuestion(ItemQuestionModel itemQuestion){
            questionTextView.setText(itemQuestion.getTitle());
            setEvents();
        }

        private void setEvents(){
            itemView.setOnClickListener(this);
            deleteQuestion.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.layout_question_root:
                    listener.onQuestionSelected(itemList.get(getAdapterPosition()));
                    break;
                case R.id.deleteQuestion:
                    //TODO implement click on image
                    break;
            }
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

/* if(itemList!=null){
return itemList.size()
}else{
return 0
}
*/
