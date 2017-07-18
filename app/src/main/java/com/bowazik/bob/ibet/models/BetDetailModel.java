package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;

/**
 * Created by bob on 18.07.17.
 */

public class BetDetailModel implements BetDetailInterfaces.BetDetailModelOps {

    private BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps;

    public BetDetailModel(BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps){
        this.betDetailRequiredPresenterOps = betDetailRequiredPresenterOps;
    }

    @Override
    public void acceptBet(int betId) {

    }

    @Override
    public void declineBet(int betId) {

    }
}
