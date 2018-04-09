package net.idevcorp.simpleandroidapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.models.ItemQuestionModel;
import net.idevcorp.simpleandroidapp.models.QuestionModel;
import net.idevcorp.simpleandroidapp.ui.adapters.AnswerItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements CompleteInterface {

    private CompletePresenter presenter;
    private static final List<ItemQuestionModel> questionItems = new ArrayList<net.idevcorp.simpleandroidapp.models.ItemQuestionModel>();
    private AnswerItemsAdapter answerItemsAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        presenter = new CompletePresenter(this);
        presenter.findQuestion();

        answerItemsAdapter = new AnswerItemsAdapter(questionItems);
        recyclerView = findViewById(R.id.recyclerViewComplete);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(answerItemsAdapter);
    }

    @Override
    public void onFindResult(AnswerModel result) {

    }

    @Override
    public void onFindQuestion(QuestionModel questionModel) {
        if (questionModel !=null){
            questionItems.clear();
            questionItems.addAll(questionModel.getItemQuestion());
        }

    }
}
