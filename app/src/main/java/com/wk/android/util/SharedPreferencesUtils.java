package com.wk.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wk.android.MyApplication;
import com.wk.android.constant.Constant;

public class SharedPreferencesUtils {
    private SharedPreferences sharedPreferences;
    private static SharedPreferencesUtils utilsInstance;
    private SharedPreferencesUtils(){
        sharedPreferences = MyApplication.getInstance().getSharedPreferences(Constant.preferences, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getUtilsInstance(){
        if (utilsInstance == null){
            utilsInstance = new SharedPreferencesUtils();
        }
        return utilsInstance;
    }

    public void putBoolean(String key,boolean isBoolean){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,isBoolean);
        edit.apply();
    }

    public void putString(String key,String str){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,str);
        edit.apply();
    }

    public void putInteger(String key,int str){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (str>10) {
            str = 10;
        }
        edit.putInt(key,str);
        edit.apply();
    }

    public int getInteger(String key){
        return sharedPreferences.getInt(key,0);
    }

    public boolean getBoolean(String ratefinish) {
        return false;
    }
}
