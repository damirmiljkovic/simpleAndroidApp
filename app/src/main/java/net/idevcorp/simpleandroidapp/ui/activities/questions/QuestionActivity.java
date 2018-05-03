package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.Questions.ItemQuestionModel;
import net.idevcorp.simpleandroidapp.models.Questions.QuestionModel;
import net.idevcorp.simpleandroidapp.ui.Dialogs.DialogBrowser;
import net.idevcorp.simpleandroidapp.ui.Dialogs.DialogProfile;
import net.idevcorp.simpleandroidapp.ui.adapters.QuestionItemAdapter;
import net.idevcorp.simpleandroidapp.ui.interfaces.QuestionInterface;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionActivity extends AppCompatActivity implements QuestionInterface, QuestionItemAdapter.OnQuestionSelectedListener {

    private PresenterQuestion presenterQuestion;
    QuestionItemAdapter questionItemAdapter;
    private static final List<ItemQuestionModel> questionItems = new ArrayList<>();
    RecyclerView recyclerView;
    EditText editTextSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle(SharedPreferencesManager.getEmail(this));

        editTextSearch = findViewById(R.id.editTextQuestionSearch);

        presenterQuestion = new PresenterQuestion(this);
        presenterQuestion.findQuestion("desc", "activity", editTextSearch.getText().toString(), "stackoverflow");

        questionItemAdapter = new QuestionItemAdapter(questionItems, this);
        recyclerView = findViewById(R.id.recyclerViewQuestion);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(questionItemAdapter);

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
            if (inputMethodManager != null ){
                try{
                    inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),0);
                }catch (Exception e){
                    Log.i("msg",e.getMessage());
                }

            }
        }

    }

    @Override
    public void onQuestionSelected(ItemQuestionModel question) {

        DialogBrowser dialogBrowser = DialogBrowser.newInstance(question.getLink());
        dialogBrowser.show(getFragmentManager(),"dialog");
    }

    @Override
    public void onShare(ItemQuestionModel question) {
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT,question.getLink());
        startActivity(Intent.createChooser(intentShare,"share link via:"));
    }

    @Override
    public void onProfile(ItemQuestionModel question) {
        DialogProfile dialogProfile =DialogProfile
                .newInstance(question.getOwner().getProfileImage()
                        ,question.getOwner().getDisplayName()
                        ,question.getOwner().getLink());
        dialogProfile.show(getFragmentManager(),"profile");
    }
}
// TODO: 24.4.2018 Ubaciti my Profile opciju u menu koja otvara profil korisnika u web view