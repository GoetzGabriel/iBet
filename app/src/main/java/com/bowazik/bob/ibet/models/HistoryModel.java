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
 * Created by bob on 19.07.17.
 */

public class HistoryModel implements HistoryInterfaces.HistoryModel, AsyncInterfaces.FetchBetHistoryAsyncInterface {

    private HistoryInterfaces.HistoryRequiredPresenterOps historyRequiredPresenterOps;
    private FetchBetHistoryAsyncTask fetchBetHistoryAsyncTask;
    private IbetSharedPrefs ibetSharedPrefs;

    public HistoryModel(HistoryInterfaces.HistoryRequiredPresenterOps historyRequiredPresenterOps, Context context){
        this.historyRequiredPresenterOps = historyRequiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    @Override
    public void fetchBetHistory() {
        fetchBetHistoryAsyncTask = new FetchBetHistoryAsyncTask();
        fetchBetHistoryAsyncTask.fetchBetHistoryAsyncInterface = this;
        fetchBetHistoryAsyncTask.execute(ibetSharedPrefs.getUserId());
    }

    @Override
    public void onFetchBetHistorySuccess(List<iBet> betHistory) {
        historyRequiredPresenterOps.onBetHistoryFetched(betHistory);
    }

    @Override
    public void onFetchBetHistoryError(String error) {
        historyRequiredPresenterOps.onBetHistoryFetchError(error);
    }
}
