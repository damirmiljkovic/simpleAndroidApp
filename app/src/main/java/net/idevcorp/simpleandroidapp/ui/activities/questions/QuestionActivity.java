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
import net.idevcorp.simpleandroidapp.models.questions.ItemQuestionModel;
import net.idevcorp.simpleandroidapp.models.questions.QuestionModel;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogBrowser;
import net.idevcorp.simpleandroidapp.ui.dialogs.DialogProfile;
import net.idevcorp.simpleandroidapp.ui.adapters.QuestionItemAdapter;
import net.idevcorp.simpleandroidapp.ui.interfaces.QuestionInterface;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements QuestionInterface, QuestionItemAdapter.OnQuestionSelectedListener {

    private PresenterQuestion presenterQuestion;
    QuestionItemAdapter questionItemAdapter;
    private static final List<ItemQuestionModel> questionItems = new ArrayList<>();
    @BindView(R.id.recyclerViewQuestion) RecyclerView recyclerView;
    @BindView(R.id.editTextQuestionSearch) EditText editTextSearch;
    @BindString(R.string.desc) String desc;
    @BindString(R.string.activity) String activity;
    @BindString(R.string.stackoverflow) String stackoverflow;
    @BindString(R.string.share_link_via) String shareCaption;
    @BindString(R.string.profile) String msgProfile;
    @BindString(R.string.dialog) String msgDialog;
    String questionET;
    @BindString(R.string.complete_title) String titleComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle(titleComplete);
        ButterKnife.bind(this);
        questionET = editTextSearch.getText().toString();
        presenterQuestion = new PresenterQuestion(this);
        presenterQuestion.findQuestion(desc, activity, questionET , stackoverflow);

        questionItemAdapter = new QuestionItemAdapter(questionItems, this);
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
            Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
        }else {
            presenterQuestion.searchQuestion(desc,activity,questionET,stackoverflow);
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
        dialogBrowser.show(getFragmentManager(),msgDialog);
    }

    @Override
    public void onShare(ItemQuestionModel question) {
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT,question.getLink());
        startActivity(Intent.createChooser(intentShare,shareCaption));
    }

    @Override
    public void onProfile(ItemQuestionModel question) {
        DialogProfile dialogProfile =DialogProfile
                .newInstance(question.getOwner().getProfileImage()
                        ,question.getOwner().getDisplayName()
                        ,question.getOwner().getLink());
        dialogProfile.show(getFragmentManager(),msgProfile);
    }
}
// TODO: 24.4.2018 Ubaciti my Profile opciju u menu koja otvara profil korisnika u web view