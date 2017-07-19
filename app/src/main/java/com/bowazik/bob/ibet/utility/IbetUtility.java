package com.bowazik.bob.ibet.utility;

import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 13.07.17.
 */

public class IbetUtility {

    public static List<iBet> jsonArrayToIbetList(JSONArray jsonArray, String[] betStatus){
        List<iBet> betList = new ArrayList<>();
        iBet iBet;

        for(int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                iBet = jsonObjectToIbet(jsonObject);
                if(stringArrayContainsString(betStatus, iBet.getStatus())){
                    betList.add(iBet);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return betList;
    }

    //Check whether a string array contains a specific string
    private static Boolean stringArrayContainsString(String[] strings, String string){
        for(String tempString : strings){
            if(tempString.equals(string)){
                return true;
            }
        }
        return false;
    }

    private static iBet jsonObjectToIbet(JSONObject jsonObject) {
        try {
            return new iBet(
                    jsonObject.getInt(Constants.IBET_TAG_ID),
                    jsonObject.getInt(Constants.IBET_TAG_CREATOR),
                    jsonObject.getInt(Constants.IBET_TAG_CONTENDER_ID),
                    jsonObject.getString(Constants.IBET_TAG_TITLE),
                    jsonObject.getString(Constants.IBET_TAG_DESCRIPTION),
                    jsonObject.getInt(Constants.IBET_TAG_VALUE),
                    //jsonObject.getDouble(Constants.IBET_TAG_DATE),
                    jsonObject.getString(Constants.IBET_TAG_STATUS),
                    jsonObject.getString(Constants.IBET_TAG_CONTENDER_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new iBet();
    }
}
