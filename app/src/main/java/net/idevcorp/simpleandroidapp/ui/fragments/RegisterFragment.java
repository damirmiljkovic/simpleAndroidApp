package net.idevcorp.simpleandroidapp.ui.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import net.idevcorp.simpleandroidapp.ui.activities.InitialActivity;

public class RegisterFragment extends Fragment {

    private EditText editTextRegisterMail;
    private EditText editTextRegisterPass;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;
    private Button buttonRegister;
    private CheckBox checkBoxRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Registration");
        return inflater.inflate(R.layout.layout_reg,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences("net.idevcorp.simpleandroidapp", Context.MODE_PRIVATE);
        editTextRegisterMail = getActivity().findViewById(R.id.editTextMailId);
        editTextRegisterPass = getActivity().findViewById(R.id.editTextPassId);
        checkBoxRegister = getActivity().findViewById(R.id.checkBoxRegister);
        buttonRegister = getActivity().findViewById(R.id.buttonReg);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUserFirebase();
            }
        });

    }

    private void createUserFirebase() {

        if (editTextRegisterMail.getText().toString().isEmpty() || editTextRegisterPass.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Empty field(s)!", Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(editTextRegisterMail.getText().toString(),editTextRegisterPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if (checkBoxRegister.isChecked()){
                                    sharedPreferences.edit().putString("mailAddress",editTextRegisterMail.getText().toString()).apply();
                                    sharedPreferences.edit().putString("passAddress",editTextRegisterPass.getText().toString()).apply();
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
