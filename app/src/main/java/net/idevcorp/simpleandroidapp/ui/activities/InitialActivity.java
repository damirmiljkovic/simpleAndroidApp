package net.idevcorp.simpleandroidapp.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.ui.fragments.WelcomFragment;



public class InitialActivity extends AppCompatActivity  {

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    WelcomFragment fragmentWelcome;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        sharedPreferences = getSharedPreferences("net.idevcorp.simpleandroidapp", Context.MODE_PRIVATE);
        if (! sharedPreferences.getString("mailAddress","").isEmpty()){
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
