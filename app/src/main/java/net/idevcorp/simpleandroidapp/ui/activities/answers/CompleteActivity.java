package net.idevcorp.simpleandroidapp.ui.activities.answers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.answers.AnswerModel;
import net.idevcorp.simpleandroidapp.models.answers.ItemModel;
import net.idevcorp.simpleandroidapp.ui.activities.InitialActivity;
import net.idevcorp.simpleandroidapp.ui.activities.users.MyProfileActivity;
import net.idevcorp.simpleandroidapp.ui.activities.users.UserActivity;
import net.idevcorp.simpleandroidapp.ui.activities.questions.QuestionActivity;
import net.idevcorp.simpleandroidapp.ui.adapters.AnswerItemsAdapter;
import net.idevcorp.simpleandroidapp.ui.interfaces.CompleteInterface;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompleteActivity extends AppCompatActivity implements CompleteInterface {


    @BindView(R.id.editTextComplete) EditText     editTextComplete;
    @BindView(R.id.recyclerViewComplete) RecyclerView       recyclerView;
    @BindString(R.string.complete_title) String titleComplete;
    public static final String DESC = "desc";
    public static final String ACTIVITY = "activity";
    public static final String STACKOVERFLOW = "stackoverflow";
    private CompletePresenter presenter;
    private static final List<ItemModel> itemModels = new ArrayList<>();
    AnswerItemsAdapter adapter;
    FirebaseAuth auth;
    String contributorET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        setTitle(titleComplete);

        contributorET = editTextComplete.getText().toString();
        presenter = new CompletePresenter(this);
        presenter.findAnswer(DESC, ACTIVITY, STACKOVERFLOW, contributorET);

        adapter = new AnswerItemsAdapter(itemModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuMyProfile){
            Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFindResult(AnswerModel result) {
        if (result != null) {
            itemModels.clear();
            itemModels.addAll(result.getItems());
            adapter.notifyDataSetChanged();
        }

    }

    public void stuckOverSearch(View view) {
        if (editTextComplete.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
        } else {
            presenter.findAnswer(DESC, ACTIVITY, STACKOVERFLOW, contributorET);
        }
    }
    @OnClick(R.id.buttonQuestion)
    public void proceedQuestions(){
        Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
        startActivity(intent);
    }

}
