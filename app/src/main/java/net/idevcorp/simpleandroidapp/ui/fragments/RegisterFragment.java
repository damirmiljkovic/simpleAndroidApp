package net.idevcorp.simpleandroidapp.ui.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteActivity;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

public class RegisterFragment extends Fragment {

    private EditText editTextRegisterMail;
    private EditText editTextRegisterPass;
    private EditText editTextRegisterUser;
    private FirebaseAuth auth;
    private Button buttonRegister;
    private CheckBox checkBoxRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Registration");
        return inflater.inflate(R.layout.layout_reg,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        PreferenceManager.getDefaultSharedPreferences(getActivity());
        editTextRegisterMail = view.findViewById(R.id.editTextMailId);
        editTextRegisterPass = view.findViewById(R.id.editTextPassId);
        editTextRegisterUser = view.findViewById(R.id.editTextUserReg);
        checkBoxRegister = view.findViewById(R.id.checkBoxRegister);
        buttonRegister = view.findViewById(R.id.buttonReg);
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
                                FirebaseUser currentUser = task.getResult().getUser();
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(editTextRegisterUser.getText().toString())
                                        .setPhotoUri(Uri.parse("android.resource://net.idevcorp.simpleandroidapp/"+ R.drawable.avatar2))
                                        .build();
                                currentUser.updateProfile(profileChangeRequest);
                                SharedPreferencesManager.setKeepMeLoggedIn(getActivity(), checkBoxRegister.isChecked());
                                SharedPreferencesManager.setEmail(getActivity(),task.getResult().getUser().getEmail());
                                SharedPreferencesManager.setUser(getActivity(),editTextRegisterUser.getText().toString());
                                SharedPreferencesManager.setUri(getActivity(), String.valueOf(Uri.parse("android.resource://net.idevcorp.simpleandroidapp/"+ R.drawable.avatar2)));
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
