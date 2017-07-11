package com.example.shekhar.queres;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Shekhar on 09/07/17.
 */

public class SavePreferences {
    private final String TAG = getClass().getSimpleName();
    private final static String mSaredPref = "queriesPref";
    private final static String KEY = "queries";
    private SharedPreferences sharedPreferences;

    SavePreferences(Context context){
        sharedPreferences = context.getSharedPreferences(mSaredPref, Context.MODE_PRIVATE);
    }

    public void saveStringSetPref(List<String> stringSet){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KEY,new HashSet<String>(stringSet));
        editor.commit();
        Log.i(TAG,"Shared Pref Saved");
    }

    public List<String> getStringSetPref(){
        List<String> list = new ArrayList<>();
        Log.i(TAG,"getting sharedPrefs");
        try{
            list.addAll(sharedPreferences.getStringSet(KEY, null));
        }catch(NullPointerException e){

        }

        return list;
    }
}
