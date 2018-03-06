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
import net.idevcorp.simpleandroidapp.ui.activities.CompleteActivity;

public class LoginFragment extends Fragment  {


    private EditText editTextLoginMail;
    private EditText editTextLoginPass;
    private Button buttonLogin;
    private FirebaseAuth auth;
    private CheckBox checkBoxLogin;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Loging in");
        return inflater.inflate(R.layout.layout_login,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences("net.idevcorp.simpleandroidapp",Context.MODE_PRIVATE);
        editTextLoginMail = getActivity().findViewById(R.id.editTextLoginMail);
        editTextLoginPass = getActivity().findViewById(R.id.editTextLoginPass);
        checkBoxLogin = getActivity().findViewById(R.id.checkBoxLogin);
        buttonLogin = getActivity().findViewById(R.id.buttonLog);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFirebaseUser();
            }
        });
    }

    public void loginFirebaseUser(){
       if (editTextLoginMail.getText().toString().isEmpty() || editTextLoginPass.getText().toString().isEmpty()){
           Toast.makeText(getActivity(), "Empty field(s)!", Toast.LENGTH_SHORT).show();
       }else{
           auth.signInWithEmailAndPassword(editTextLoginMail.getText().toString(),editTextLoginPass.getText().toString())
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               if (checkBoxLogin.isChecked()){
                                   sharedPreferences.edit().putString("mailAddress",editTextLoginMail.getText().toString()).apply();
                                   sharedPreferences.edit().putString("passAddress",editTextLoginPass.getText().toString()).apply();
                               }
                               Intent intent = new Intent(getContext(),CompleteActivity.class);
                               startActivity(intent);
                           }else{
                               Toast.makeText(getActivity(), "Something is wrong!", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
       }
}


}
