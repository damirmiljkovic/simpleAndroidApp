package net.idevcorp.simpleandroidapp.ui.adapters;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.ItemQuestionModel;

import java.util.List;

public class QuestionItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemQuestionModel>itemList;
    private OnQuestionSelectedListener listener;


    public interface OnQuestionSelectedListener{
        void onQuestionSelected(ItemQuestionModel question);
        void onShare(ItemQuestionModel question);
        void onProfile(ItemQuestionModel question);
    }

    public QuestionItemAdapter(List<ItemQuestionModel> itemList,OnQuestionSelectedListener listener){
        this.itemList = itemList;
        this.listener = listener;
    }


    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView questionTextView;
        private  TextView userTextView;
        private ImageView shareQuestionButton;
        private ImageView profileButton;
        private Uri uriProfileImage;
        private MyHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textViewQuestionId);
            userTextView = itemView.findViewById(R.id.userTextView);
            shareQuestionButton = itemView.findViewById(R.id.shareQuestionButton);
            profileButton = itemView.findViewById(R.id.imageViewProfile);
        }
        private void bindQuestion(ItemQuestionModel itemQuestion){
            questionTextView.setText(itemQuestion.getTitle());
            userTextView.setText(itemQuestion.getOwner().getDisplayName());
            uriProfileImage = Uri.parse(itemQuestion.getOwner().getProfileImage());
            Picasso.get().load(uriProfileImage).into(profileButton);
            setEvents();
        }

        private void setEvents(){
            questionTextView.setOnClickListener(this);
            shareQuestionButton.setOnClickListener(this);
            profileButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.textViewQuestionId:
                    listener.onQuestionSelected(itemList.get(getAdapterPosition()));
                    break;
                case R.id.shareQuestionButton:
                    listener.onShare(itemList.get(getAdapterPosition()));
                    break;
                case R.id.imageViewProfile:
                    listener.onProfile(itemList.get(getAdapterPosition()));
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
// 24.4.2018 ubaciti profile photo i username a ispod toga ostaviti question
//  24.4.2018 implementirati  onclick method da otvara dialog(web view) sa profile details
