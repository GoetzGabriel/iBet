package com.bowazik.bob.ibet.models;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.FetchBetListAsyncTask;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.List;

/**
 * The Model for the bet feed activity.
 * It fetches the list of active and pending bets from the web server.
 * Furthermore it processes the answer and redirects the bet lists to the presenter.
 */

public class BetFeedModel implements BetFeedInterfaces.BetFeedModelOps, AsyncInterfaces.FetchBetListAsyncInterface{

    private static final String TAG = "BetFeedModel";
    private BetFeedInterfaces.BetFeedRequiredPresenterOps betFeedRequiredPresenterOps;
    private IbetSharedPrefs ibetSharedPrefs;

    public BetFeedModel(BetFeedInterfaces.BetFeedRequiredPresenterOps betFeedRequiredPresenterOps, Context context){
        this.betFeedRequiredPresenterOps = betFeedRequiredPresenterOps;
        ibetSharedPrefs = new IbetSharedPrefs(context);
    }

    /**
     * Fetch the bet lists for the active and the pending bets from the web server using an async task.
     */
    @Override
    public void fetchBetList() {
        FetchBetListAsyncTask fetchBetListAsyncTask = new FetchBetListAsyncTask();
        fetchBetListAsyncTask.fetchBetListAsyncInterface = this;
        fetchBetListAsyncTask.execute(ibetSharedPrefs.getUserId());
    }

    /**
     * Redirect the server answer to the bet feed presenter
     * @param pendingBetList List of pending bets
     * @param activeBetList List of active bets
     */
    @Override
    public void onFetchBetListSuccess(List<iBet> pendingBetList, List<iBet> activeBetList) {
        betFeedRequiredPresenterOps.onBetListFetched(pendingBetList, activeBetList);
    }

    @Override
    public void onFetchBetListError() {
    }
}
