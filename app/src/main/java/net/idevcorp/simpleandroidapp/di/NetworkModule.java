package net.idevcorp.simpleandroidapp.di;

import net.idevcorp.simpleandroidapp.models.users.UserModel;
import net.idevcorp.simpleandroidapp.network.Endpoints;
import net.idevcorp.simpleandroidapp.network.RetrofitBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    private Boolean hasMode;
    private Long quotaMax;

    public NetworkModule(Boolean hasMode, Long quotaMax){
        this.hasMode = hasMode;
        this.quotaMax = quotaMax;
    }

    @Provides
    @Singleton
    Endpoints providesEndpoints(){
        return RetrofitBuilder.getInstance();
    }

    @Provides
    @Singleton
    UserModel providesUser(){
        return new UserModel(hasMode, quotaMax);
    }

}
