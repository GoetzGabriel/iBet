package com.bowazik.bob.ibet.models;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.FetchBetListAsyncTask;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;

import java.util.List;

/**
 * Created by bob on 12.07.17.
 */

public class BetFeedModel implements BetFeedInterfaces.BetFeedModelOps, AsyncInterfaces.FetchBetListAsyncInterface{

    private static final String TAG = "BetFeedModel";
    private BetFeedInterfaces.BetFeedRequiredPresenterOps betFeedRequiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;
    private FetchBetListAsyncTask fetchBetListAsyncTask;

    public BetFeedModel(BetFeedInterfaces.BetFeedRequiredPresenterOps betFeedRequiredPresenterOps, Context context){
        this.betFeedRequiredPresenterOps = betFeedRequiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    @Override
    public void fetchBetList() {
        fetchBetListAsyncTask = new FetchBetListAsyncTask();
        fetchBetListAsyncTask.fetchBetListAsyncInterface = this;
        fetchBetListAsyncTask.execute(ibetSharedPrefs.getUserId());
    }

    @Override
    public void onFetchBetListSuccess(List<iBet> betList) {
        betFeedRequiredPresenterOps.onBetListFetched(betList);
    }

    @Override
    public void onFetchBetListError() {
    }
}
