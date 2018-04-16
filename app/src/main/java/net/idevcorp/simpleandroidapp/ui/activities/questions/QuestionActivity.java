package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText editTextSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        editTextSearch = findViewById(R.id.editTextQuestionSearch);

        presenterQuestion = new PresenterQuestion(this);
        presenterQuestion.findQuestion("desc", "activity", editTextSearch.getText().toString(), "stackoverflow");

        questionItemAdapter = new QuestionItemAdapter(questionItems);
        recyclerView = findViewById(R.id.recyclerViewQuestion);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(questionItemAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerOnClickListener(getApplicationContext(), recyclerView,
                new RecyclerOnClickListener.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        openAlertDialogue();
                    }

                    @Override
                    public void onLongPress(View view, int position) {
                        Toast.makeText(QuestionActivity.this, "Long Press!", Toast.LENGTH_LONG).show();
                    }
                }));

    }

    private void openAlertDialogue() {
        new AlertDialog.Builder(QuestionActivity.this)
                .setIcon(android.R.drawable.btn_star)
                .setTitle("make a choice!")
                .setMessage("Open with?")
                .setPositiveButton("browser", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(QuestionActivity.this, "browser", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("WEB VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(QuestionActivity.this, "WEB VIEW", Toast.LENGTH_SHORT).show();

                    }
                })
                .show();
    }

    @Override
    public void onFindQuestion(QuestionModel questionModel) {
        if (questionModel !=null ){
            questionItems.clear();
            questionItems.addAll(questionModel.getItemQuestion());
            questionItemAdapter.notifyDataSetChanged();
        }

    }
    public void findQuestions(View view){

        String searchTag = editTextSearch.getText().toString();
        if (searchTag.isEmpty()){
            Toast.makeText(this, "Search box is empty!", Toast.LENGTH_SHORT).show();
        }else {
            presenterQuestion.searchQuestion("desc","activity",searchTag,"stackoverflow");
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

}
