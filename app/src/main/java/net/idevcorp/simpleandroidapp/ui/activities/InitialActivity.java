package net.idevcorp.simpleandroidapp.ui.activities;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.fragments.LoginFragment;
import net.idevcorp.simpleandroidapp.ui.fragments.WelcomFragment;


public class InitialActivity extends AppCompatActivity  {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    WelcomFragment fragment;
    LoginFragment loginFragment;

    public void probaj(View view){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.layoutInitialId, loginFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new WelcomFragment();
        fragmentTransaction.add(R.id.layoutInitialId,fragment);
        fragmentTransaction.commit();
    }



}
