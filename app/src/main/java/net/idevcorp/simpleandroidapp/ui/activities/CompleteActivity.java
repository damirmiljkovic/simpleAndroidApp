package net.idevcorp.simpleandroidapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import net.idevcorp.simpleandroidapp.models.ItemModel;
import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.util.ApiUtil;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Endpoints endpoint;
    TextView textViewAnswer;
    AnswerModel answerModel;
    EditText editTextComplete;

    public void stuckOverSearch(View view){
        if (editTextComplete.getText().toString().isEmpty()){
            Toast.makeText(this, "Make some input and then click the button!", Toast.LENGTH_SHORT).show();
        }else {
            endpoint.getAnswers(editTextComplete.getText().toString()).enqueue(new Callback<AnswerModel>() {
                @Override
                public void onResponse(@NonNull Call<AnswerModel> call, @NonNull Response<AnswerModel> response) {
                    for (int i=0; i<10;i++){
                        textViewAnswer.append(response.body().getItems().get(i).getOwner().getDisplayName()+", ");
                    }                }

                @Override
                public void onFailure(@NonNull Call<AnswerModel> call, @NonNull Throwable t) {
                    textViewAnswer.setText(t.getMessage());
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        auth = FirebaseAuth.getInstance();
        setTitle(SharedPreferencesManager.getEmail(this));
        editTextComplete = findViewById(R.id.editTextComplete);
        endpoint = ApiUtil.getEndpoints();

        endpoint.getAnswers().enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(@NonNull Call<AnswerModel> call, @NonNull Response<AnswerModel> response) {
                if (response.isSuccessful()){
                    textViewAnswer = findViewById(R.id.textViewAnswer);
                    //uzeo proizvoljnih 10 unosa zbog boljeg pregleda...
                    for (int i=0; i<10;i++){
                        textViewAnswer.append(response.body().getItems().get(i).getOwner().getDisplayName()+", ");
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<AnswerModel> call, @NonNull Throwable t) {
                textViewAnswer.setText(t.getMessage());
            }
        });

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
}
