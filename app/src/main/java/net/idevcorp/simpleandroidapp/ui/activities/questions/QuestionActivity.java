package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.models.ItemQuestionModel;
import net.idevcorp.simpleandroidapp.models.QuestionModel;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteInterface;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompletePresenter;
import net.idevcorp.simpleandroidapp.ui.adapters.AnswerItemsAdapter;
import net.idevcorp.simpleandroidapp.ui.adapters.QuestionItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements QuestionInterface {

    private PresenterQuestion presenterQuestion;
    QuestionItemAdapter questionItemAdapter;
    private static final List<ItemQuestionModel> questionItems = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        presenterQuestion = new PresenterQuestion(this);
        presenterQuestion.findQuestion("desc","activity","stackoverflow","java");

        questionItemAdapter = new QuestionItemAdapter(questionItems);
        recyclerView = findViewById(R.id.recyclerViewQuestion);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(questionItemAdapter);
    }

    @Override
    public void onFindQuestion(QuestionModel questionModel) {
        if (questionModel !=null && questionModel.getItemQuestion() != null){
            questionItems.clear();
            questionItems.addAll(questionModel.getItemQuestion());
            questionItemAdapter.notifyDataSetChanged();
        }

    }
}
// TODO: 10.4.2018 ubaciti LayoutManager recyclerView u onCreate