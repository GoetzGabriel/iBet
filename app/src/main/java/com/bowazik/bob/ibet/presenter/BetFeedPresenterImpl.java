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
    private ArrayList<iBet> betFeedList = new ArrayList<>();

    public BetFeedPresenterImpl(BetFeedInterfaces.BetFeedView betFeedView, Context context){
        this.betFeedView = betFeedView;
        betFeedModel = new BetFeedModel(this, context);
    }

    public void fetchBetFeed() {
        //TODO replace placeholder data with server interaction(fetch bet list)
        betFeedModel.fetchBetList();
    }

    public void setBetAsLost(int i) {
        if(betFeedList.size() >= i){
            //TODO replace remove with server interaction(set bet as lost)
            Log.v(TAG, "Clicked: " + betFeedList.get(i).getContender());
            betFeedList.remove(i);
            Log.v(TAG, "Länge: " + betFeedList.size());
            betFeedView.setBetFeedList(betFeedList);
        }else{
            betFeedView.showSetBetAsLostError();
        }
    }

    @Override
    public void onBetListFetched(List<iBet> betList) {
        betFeedView.setBetFeedList(betList);
    }
}
