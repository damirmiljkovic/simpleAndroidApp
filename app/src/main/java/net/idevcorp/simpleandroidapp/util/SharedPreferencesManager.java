package net.idevcorp.simpleandroidapp.util;


import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;

import net.idevcorp.simpleandroidapp.R;

public class SharedPreferencesManager {

    private static final String KEEP_ME_LOGGED_IN = "keep_me_logged_in";
    private static final String EMAIL_TO_INSERT = "email_to_insert";
    private static final String URI_TO_INSERT = "uri_to_insert";
    private static final String USER_TO_INSERT = "user_to_insert";

    private SharedPreferencesManager() {
    }

    public static void setKeepMeLoggedIn(Context context, boolean keepMeLoggedIn) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(KEEP_ME_LOGGED_IN, keepMeLoggedIn).apply();
    }

    public static boolean getKeepMeLoggedIn(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEEP_ME_LOGGED_IN, false);
    }
    public static void setEmail(Context context, String emailToInsert){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(EMAIL_TO_INSERT,emailToInsert).apply();
    }
    public static String getEmail(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(EMAIL_TO_INSERT,"");
    }
    public static void setUser(Context context, String userToInsert){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(USER_TO_INSERT,userToInsert).apply();
    }
    public static String getUser(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(USER_TO_INSERT,"");
    }
    public static void setUri(Context context, String uriToInsert){
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(URI_TO_INSERT,uriToInsert).apply();
    }
    public static String getUri(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(URI_TO_INSERT, "");
    }
    public static void clearSavedPreferences(Context context){
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

}
