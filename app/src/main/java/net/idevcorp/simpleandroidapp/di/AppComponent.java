package net.idevcorp.simpleandroidapp.di;

import net.idevcorp.simpleandroidapp.SimpleAndroidApplication;
import net.idevcorp.simpleandroidapp.ui.activities.InitialActivity;
import net.idevcorp.simpleandroidapp.ui.activities.answers.CompleteActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(SimpleAndroidApplication app);

    void inject(InitialActivity initialActivity);

    void inject(CompleteActivity completeActivity);
}
