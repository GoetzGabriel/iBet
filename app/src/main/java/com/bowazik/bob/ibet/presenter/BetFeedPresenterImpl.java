package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.models.BetFeedModel;

import java.util.List;

/**
 * The presenter for the bet feed activity.
 * It redirects request to fetch the bet lists to the model and the model response back to the view.
 */

public class BetFeedPresenterImpl implements BetFeedInterfaces.BetFeedPresenter, BetFeedInterfaces.BetFeedRequiredPresenterOps{

    private static final String TAG = "BetFeedPresenterImpl";
    private final BetFeedInterfaces.BetFeedView betFeedView;
    private BetFeedModel betFeedModel;

    public BetFeedPresenterImpl(BetFeedInterfaces.BetFeedView betFeedView, Context context){
        this.betFeedView = betFeedView;
        betFeedModel = new BetFeedModel(this, context);
    }

    public void fetchBetFeed() {
        betFeedModel.fetchBetList();
    }

    /**
     * Call the presenter callback operation on success and hand the requested lists
     * @param pendingBetList List containing the pending bets
     * @param activeBetList List containing the active bets
     */
    @Override
    public void onBetListFetched(List<iBet> pendingBetList, List<iBet> activeBetList) {
        betFeedView.setBetFeedList(pendingBetList, activeBetList);
    }
}
