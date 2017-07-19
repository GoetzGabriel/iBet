package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.models.BetDetailModel;

/**
 * Created by bob on 18.07.17.
 */

public class BetDetailPresenterImpl implements BetDetailInterfaces.BetDetailPresenter, BetDetailInterfaces.BetDetailRequiredPresenterOps {

    private BetDetailInterfaces.BetDetailView betDetailView;
    private BetDetailModel betDetailModel;

    public BetDetailPresenterImpl(BetDetailInterfaces.BetDetailView betDetailView){
        this.betDetailView = betDetailView;
        betDetailModel = new BetDetailModel(this);
    }

    @Override
    public void reactToBet(int betId, String newStatus) {
        betDetailModel.reactToBet(betId, newStatus);
    }

    @Override
    public void onBetReactionSuccess() {
        betDetailView.showBetReactionSuccessMessage();
    }

    @Override
    public void onBetReactionError() {
        betDetailView.showBetRectionErrorMessage();
    }
}
