package net.idevcorp.simpleandroidapp.ui.activities;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.fragments.LoginFragment;
import net.idevcorp.simpleandroidapp.ui.fragments.RegisterFragment;
import net.idevcorp.simpleandroidapp.ui.fragments.WelcomFragment;



public class InitialActivity extends AppCompatActivity implements View.OnClickListener  {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    WelcomFragment fragmentWelcome;
    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    FirebaseAuth auth;
    EditText editTextMail;
    EditText editTextPass;

    public void fragmentRealize(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutInitial,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        auth = FirebaseAuth.getInstance();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentWelcome = new WelcomFragment();
        fragmentTransaction.add(R.id.frameLayoutInitial,fragmentWelcome);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLogin){
            loginFragment = new LoginFragment();
            fragmentRealize(loginFragment);
        }else if (view.getId() == R.id.buttonRegister){
            registerFragment = new RegisterFragment();
            fragmentRealize(registerFragment);
        }else if (view.getId() == R.id.buttonLog){
            // method for sign in with e-mail and password...
            signInFirebase();
        }else if (view.getId() == R.id.buttonReg){
            //method for creating new user in Firebase...
            createUserFirebase();

        }
    }

    private void createUserFirebase() {
        editTextMail = findViewById(R.id.editTextMailId);
        editTextPass = findViewById(R.id.editTextPassId);
        if (editTextMail.getText().toString().isEmpty() || editTextPass.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty field(s)!", Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(editTextMail.getText().toString(),editTextPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(InitialActivity.this, "Sign up is successful!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(InitialActivity.this, "Something is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void signInFirebase() {
        editTextMail = findViewById(R.id.editTextMailId);
        editTextPass = findViewById(R.id.editTextPassId);
        if (editTextMail.getText().toString().isEmpty() || editTextPass.getText().toString().isEmpty()){
            Toast.makeText(this, "empty field(s)!", Toast.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(editTextMail.getText().toString(),editTextPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(InitialActivity.this, "OK!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(InitialActivity.this, "Wrong or mail or password...!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }
}
