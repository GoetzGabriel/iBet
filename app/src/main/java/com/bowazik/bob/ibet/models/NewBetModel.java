package com.bowazik.bob.ibet.models;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 11.07.17.
 */

public class NewBetModel implements NewBetInterfaces.NewBetModelOps{
    private static final String TAG = "NewBetModel";

    private NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;

    public NewBetModel(NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps, Context context){
        this.requiredPresenterOps = requiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    public void addNewIBet(iBet iBet){
        Log.v(TAG, "Ibet: "+iBet.getTitle()+" added.");
        ibetSharedPrefs.addIbet(iBet);
        requiredPresenterOps.onIbetAdded(iBet);
    }
}
