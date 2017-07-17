package com.bowazik.bob.ibet.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 12.07.17.
 */

public class IbetSharedPrefs {

    private SharedPreferences sharedPreferences;

    public IbetSharedPrefs(Context context){

        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE);
    }

    public void addIbet(iBet ibet){
        List<iBet> betList = getBetList();
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();

        betList.add(ibet);

        String json = gson.toJson(betList);
        prefsEditor.putString(Constants.SHARED_PREFERENCES_BET_LIST_TAG, json);
        prefsEditor.apply();
    }

    public List<iBet> getBetList(){
        String json = sharedPreferences.getString(Constants.SHARED_PREFERENCES_BET_LIST_TAG, "");

        if(json.equals("")){
            return new ArrayList<>();
        }else{
            Type type = new TypeToken<List<iBet>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
    }
}
