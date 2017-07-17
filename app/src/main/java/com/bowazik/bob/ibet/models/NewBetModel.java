package com.bowazik.bob.ibet.models;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.CreateBetAsyncTask;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 11.07.17.
 */

public class NewBetModel implements NewBetInterfaces.NewBetModelOps, AsyncInterfaces.CreateBetAsyncInterface{
    private static final String TAG = "NewBetModel";

    private NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;

    public NewBetModel(NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps, Context context){
        this.requiredPresenterOps = requiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    public void addNewIBet(String title, String description, String contender, String value){
        CreateBetAsyncTask createBetAsyncTask = new CreateBetAsyncTask();
        createBetAsyncTask.createBetAsyncInterface = this;
        createBetAsyncTask.execute(title, description, contender, value, Integer.toString(ibetSharedPrefs.getUserId()));
    }

    @Override
    public void onCreateBetSuccess() {
        requiredPresenterOps.onCreateBetSuccess();
    }

    @Override
    public void onCreateBetError() {
        requiredPresenterOps.onCreateBetError();
    }
}
