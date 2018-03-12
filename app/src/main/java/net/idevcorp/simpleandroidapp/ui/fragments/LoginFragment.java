package net.idevcorp.simpleandroidapp.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.models.AnswerModel;
import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.activities.CompleteActivity;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {


    private EditText          editTextLoginMail;
    private EditText          editTextLoginPass;
    private Button            buttonLogin;
    private FirebaseAuth      auth;
    private CheckBox          checkBoxLogin;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Loging in");
        return inflater.inflate(R.layout.layout_login, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        editTextLoginMail = view.findViewById(R.id.editTextLoginMail);
        editTextLoginPass = view.findViewById(R.id.editTextLoginPass);
        checkBoxLogin = view.findViewById(R.id.checkBoxLogin);
        buttonLogin = view.findViewById(R.id.buttonLog);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFirebaseUser();
            }
        });
        getAnswersAPI();
    }

    private void getAnswersAPI() {
        RetrofitBuilder.getInstance().create(Endpoints.class).getAnswers()
                .enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                //dobili smo rezultat
                Log.e("test", response.body().getItems().toString());
            }

            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {
                //dobili smo error
                Log.e("test error", t.getMessage());
            }
        });
    }

    public void loginFirebaseUser() {
        if (editTextLoginMail.getText().toString().isEmpty() || editTextLoginPass.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Empty field(s)!", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(editTextLoginMail.getText().toString(), editTextLoginPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferencesManager.setKeepMeLoggedIn(getActivity(), checkBoxLogin.isChecked());
                                SharedPreferencesManager.setEmail(getActivity(), task.getResult().getUser().getEmail());
                                Intent intent = new Intent(getContext(), CompleteActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "Something is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}
