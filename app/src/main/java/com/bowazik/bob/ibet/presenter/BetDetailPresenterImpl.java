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
    public void acceptBet(int betId) {

    }

    @Override
    public void declineBet(int betId) {

    }

    @Override
    public void onBetReactionSuccess() {

    }

    @Override
    public void onBetReactionError() {

    }
}
