package com.bowazik.bob.ibet.models;

import android.content.Context;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.FetchBetHistoryAsyncTask;
import com.bowazik.bob.ibet.network.FetchBetListAsyncTask;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.List;

/**
 * The Model for the bet detail activity.
 * It sends a request to react to an iBet to the web server using an async task.
 * Furthermore it processes the answer and redirects it to the bet detail presenter.
 */

public class HistoryModel implements HistoryInterfaces.HistoryModel, AsyncInterfaces.FetchBetHistoryAsyncInterface {

    private HistoryInterfaces.HistoryRequiredPresenterOps historyRequiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;
    private int userId;

    public HistoryModel(HistoryInterfaces.HistoryRequiredPresenterOps historyRequiredPresenterOps, Context context){
        this.historyRequiredPresenterOps = historyRequiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
        userId = ibetSharedPrefs.getUserId();
    }

    /**
     * Fetch the bet history from the web server using an async task
     */
    @Override
    public void fetchBetHistory() {
        FetchBetHistoryAsyncTask fetchBetHistoryAsyncTask = new FetchBetHistoryAsyncTask();
        fetchBetHistoryAsyncTask.fetchBetHistoryAsyncInterface = this;
        fetchBetHistoryAsyncTask.execute(userId);
    }

    /**
     * Redirect the fetched bet history list to the history presenter
     * @param betHistory Bet history list containing iBets
     */
    @Override
    public void onFetchBetHistorySuccess(List<iBet> betHistory) {
        historyRequiredPresenterOps.onBetHistoryFetched(betHistory, userId);
    }

    @Override
    public void onFetchBetHistoryError(String error) {
        historyRequiredPresenterOps.onBetHistoryFetchError(error);
    }
}
