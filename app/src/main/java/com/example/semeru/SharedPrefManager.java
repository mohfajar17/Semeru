package com.example.semeru;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private Context mContext;

    public static final String KEY_ISLOGGEDIN = "is_logged_in";
    public static final String PREF_NAME  = "SemeruPref";

    public SharedPrefManager(Context mContext){
        this.mContext = mContext;
    }

    public static SharedPrefManager getInstance(Context mContext){
        SharedPrefManager sharedPrefManager = new SharedPrefManager(mContext);
        return sharedPrefManager;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_ISLOGGEDIN) && sharedPreferences.getBoolean(KEY_ISLOGGEDIN,false);
    }

    public void loggin(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISLOGGEDIN,true);
        editor.apply();
    }

    public void logout(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
