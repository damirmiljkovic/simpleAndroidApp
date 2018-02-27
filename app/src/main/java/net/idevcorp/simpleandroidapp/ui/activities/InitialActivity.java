package net.idevcorp.simpleandroidapp.ui.activities;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
            Toast.makeText(this, "log in u dure!", Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.buttonReg){
            Toast.makeText(this, "registracija u dure!", Toast.LENGTH_SHORT).show();
        }
    }
}
