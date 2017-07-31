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
 * The Model for the new bet activity.
 * It sends the users data for a new bet to the web server where a new bet is inserted to the DB.
 * The presenters callback methods for success and failure are called according to the server response.
 */

public class NewBetModel implements NewBetInterfaces.NewBetModelOps, AsyncInterfaces.CreateBetAsyncInterface{
    private static final String TAG = "NewBetModel";

    private NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;

    public NewBetModel(NewBetInterfaces.NewBetRequiredPresenterOps requiredPresenterOps, Context context){
        this.requiredPresenterOps = requiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    /**
     * Send the data of the new bet to the web server using async task
     * @param title Title of the bet
     * @param description Description of the bet
     * @param contender Contender for the bet
     * @param value Value of the bet
     */
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
