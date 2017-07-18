package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.BetReactionAsyncTask;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * Created by bob on 18.07.17.
 */

public class BetDetailModel implements BetDetailInterfaces.BetDetailModelOps, AsyncInterfaces.BetReactionAsyncInterface {

    private BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps;
    private BetReactionAsyncTask betReactionAsyncTask;

    public BetDetailModel(BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps){
        this.betDetailRequiredPresenterOps = betDetailRequiredPresenterOps;
    }

    @Override
    public void acceptBet(int betId) {
        betReactionAsyncTask = new BetReactionAsyncTask();
        betReactionAsyncTask.betReactionAsyncInterface = this;
        betReactionAsyncTask.execute(Integer.toString(betId), Constants.IBET_BET_REACTION_ACCEPT);
    }

    @Override
    public void declineBet(int betId) {
        betReactionAsyncTask = new BetReactionAsyncTask();
        betReactionAsyncTask.betReactionAsyncInterface = this;
        betReactionAsyncTask.execute(Integer.toString(betId), Constants.IBET_BET_REACTION_DECLINED);
    }

    @Override
    public void onBetReactionSuccess() {
        betDetailRequiredPresenterOps.onBetReactionSuccess();
    }

    @Override
    public void onBetReactionError() {
        betDetailRequiredPresenterOps.onBetReactionError();
    }
}
