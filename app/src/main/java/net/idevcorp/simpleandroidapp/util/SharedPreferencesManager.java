package net.idevcorp.simpleandroidapp.util;


import android.content.Context;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    private static final String KEEP_ME_LOGGED_IN = "keep_me_logged_in";

    private SharedPreferencesManager() {
    }

    public static void setKeepMeLoggedIn(Context context, boolean keepMeLoggedIn) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(KEEP_ME_LOGGED_IN, keepMeLoggedIn).apply();
    }

    public static boolean getKeepMeLoggedIn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEEP_ME_LOGGED_IN, false);
    }

    //TODO implement saving user email in shared preferences
    //TODO implement clear all shared preferences(when signing out)

}
