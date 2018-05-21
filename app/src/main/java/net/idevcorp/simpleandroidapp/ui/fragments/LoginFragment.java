package net.idevcorp.simpleandroidapp.ui.fragments;


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
import net.idevcorp.simpleandroidapp.models.answers.AnswerModel;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteActivity;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import java.util.List;
import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Objects.requireNonNull;

public class LoginFragment extends Fragment {

    @BindView(R.id.editTextLoginMail) EditText          editTextLoginMail;
    @BindView(R.id.editTextLoginPass) EditText          editTextLoginPass;
    @BindViews({R.id.editTextLoginMail,R.id.editTextLoginPass}) List<EditText> loginETs;
    @BindView(R.id.buttonLog)  Button            buttonLogin;
    @BindView(R.id.checkBoxLogin)  CheckBox          checkBoxLogin;
    @BindString(R.string.log_in) String titleLogIn;
    FirebaseAuth      auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(titleLogIn);
        return inflater.inflate(R.layout.layout_login, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        auth = FirebaseAuth.getInstance();
        getAnswersAPI();
    }

     public void getAnswersAPI() {
        RetrofitBuilder.getInstance().getAnswers()
                .enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(@NonNull Call<AnswerModel> call, @NonNull Response<AnswerModel> response) {
                //dobili smo rezultat
                    Log.e("test", Objects.requireNonNull(requireNonNull(response).body()).getItems().toString());
            }
            @Override
            public void onFailure(@NonNull Call<AnswerModel> call, @NonNull Throwable t) {
                //dobili smo error
                Log.e("test error", t.getMessage());
            }
        });
    }

    @OnClick(R.id.buttonLog)
    public void loginFirebaseUser() {
        String mailET = editTextLoginMail.getText().toString();
        String passET = editTextLoginPass.getText().toString();
        if (mailET.isEmpty() || passET.isEmpty()) {
            Toast.makeText(getActivity(), R.string.empty_fields, Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(mailET, passET)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferencesManager.setKeepMeLoggedIn(getActivity(), checkBoxLogin.isChecked());
                                SharedPreferencesManager.setEmail(getActivity(), task.getResult().getUser().getEmail());
                                SharedPreferencesManager.setUser(getActivity(),task.getResult().getUser().getDisplayName());
                                SharedPreferencesManager.setUri(getActivity(), requireNonNull(task.getResult().getUser().getPhotoUrl()).toString());
                                Intent intent = new Intent(getContext(), CompleteActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}
