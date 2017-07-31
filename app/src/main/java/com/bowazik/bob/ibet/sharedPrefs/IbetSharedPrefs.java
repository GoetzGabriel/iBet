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
 * Class containing an implementation of shared preferences to save and retrieve user ids/bets.
 */

public class IbetSharedPrefs {

    private SharedPreferences sharedPreferences;

    public IbetSharedPrefs(Context context){

        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_TAG, Context.MODE_PRIVATE);
    }

    /**
     * Save the user id locally in the shared preferences
     * @param userId User id to be saved in the shared prefs
     */
    public void saveUserId(int userId){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(Constants.SHARED_PREFERENCES_USER_ID_TAG, userId);
        prefsEditor.apply();
    }

    /**
     * Get the locally saved user id from the shared preferences
     * @return User id to be returned
     */
    public int getUserId(){
        return sharedPreferences.getInt(Constants.SHARED_PREFERENCES_USER_ID_TAG, 0);
    }

    /**
     * Save an ibet locally in the shared preferences
     * @param ibet iBet to be saved as json in the prefs
     */
    public void addIbet(iBet ibet){
        List<iBet> betList = getBetList();
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();

        betList.add(ibet);

        String json = gson.toJson(betList);
        prefsEditor.putString(Constants.SHARED_PREFERENCES_BET_LIST_TAG, json);
        prefsEditor.apply();
    }

    /**
     * Retrieve the saved bet list as json from the shared prefs
     * @return List containing the saved bets
     */
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
