package net.idevcorp.simpleandroidapp.ui.activities;

import android.content.Intent;
import android.icu.lang.UCharacter;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.ui.adapters.AnswerItemsAdapter;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class CompleteActivity extends AppCompatActivity implements CompleteInterface {

    FirebaseAuth auth;
    TextView textViewAnswer;
    EditText editTextComplete;
    String msgResult = "";
    private CompletePresenter presenter;
    private List<AnswerModel> answerModelList = new ArrayList<>();
    AnswerItemsAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        auth = FirebaseAuth.getInstance();
        setTitle(SharedPreferencesManager.getEmail(this));
        editTextComplete = findViewById(R.id.editTextComplete);
        textViewAnswer = findViewById(R.id.textViewAnswer);

        presenter = new CompletePresenter(this);
        presenter.find("desc", "activity", "stackoverflow", editTextComplete.getText().toString());

        adapter = new AnswerItemsAdapter(answerModelList);
        recyclerView = findViewById(R.id.recyclerViewComplete);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSignOut){
            auth.signOut();
            SharedPreferencesManager.clearSavedPreferences(getApplicationContext());
            Intent intent = new Intent(getApplicationContext(),InitialActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFindResult(AnswerModel result) {
        textViewAnswer.setText(msgResult);
        for (int i=0; i<10;i++){
            msgResult = result.getItems().get(i).getOwner().getDisplayName()+", ";
            textViewAnswer.append(msgResult);
        }
    }

    public void stuckOverSearch(View view){
        if (editTextComplete.getText().toString().isEmpty()){
            Toast.makeText(this, "A search-box is empty! ", Toast.LENGTH_SHORT).show();
        }else {
            presenter.find("desc", "activity", "stackoverflow", editTextComplete.getText().toString());
        }
    }

}
