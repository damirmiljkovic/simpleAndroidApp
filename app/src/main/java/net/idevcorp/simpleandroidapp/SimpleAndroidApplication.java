package net.idevcorp.simpleandroidapp;


import android.app.Application;

import net.idevcorp.simpleandroidapp.di.AppComponent;
import net.idevcorp.simpleandroidapp.di.AppModule;
import net.idevcorp.simpleandroidapp.di.DaggerAppComponent;
import net.idevcorp.simpleandroidapp.di.NetworkModule;
import net.idevcorp.simpleandroidapp.models.users.UserModel;
import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;

public class SimpleAndroidApplication extends Application {

    private AppComponent appComponent;
    private Endpoints endpoints = RetrofitBuilder.getInstance();
    private UserModel userModel = new UserModel();


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(true, 20L))
                .appModule(new AppModule())
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public Endpoints getEndpoints() {
        return endpoints;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
