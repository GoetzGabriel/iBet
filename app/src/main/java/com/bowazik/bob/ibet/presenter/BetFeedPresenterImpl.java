package com.bowazik.bob.ibet.presenter;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.models.BetFeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 08.05.17.
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

    @Override
    public void setBetAsLost(int i) {

    }

    @Override
    public void onBetListFetched(List<iBet> pendingBetList, List<iBet> activeBetList) {
        betFeedView.setBetFeedList(pendingBetList, activeBetList);
    }
}
