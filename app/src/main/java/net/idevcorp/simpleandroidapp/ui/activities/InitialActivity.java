package net.idevcorp.simpleandroidapp.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.SimpleAndroidApplication;
import net.idevcorp.simpleandroidapp.models.users.UserModel;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteActivity;
import net.idevcorp.simpleandroidapp.ui.fragments.WelcomFragment;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

import javax.inject.Inject;


public class InitialActivity extends AppCompatActivity  {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    WelcomFragment fragmentWelcome;

    @Inject
    public UserModel userModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        ((SimpleAndroidApplication)getApplication()).getAppComponent().inject(this);

        if (SharedPreferencesManager.getKeepMeLoggedIn(this)){
            Intent intent = new Intent(getApplicationContext(),CompleteActivity.class);
            startActivity(intent);
        }
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentWelcome = new WelcomFragment();
        fragmentTransaction.add(R.id.frameLayoutInitial,fragmentWelcome);
        fragmentTransaction.commit();


    }

}
