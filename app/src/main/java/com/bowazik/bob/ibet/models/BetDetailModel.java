package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.BetReactionAsyncTask;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The Model for the bet detail activity.
 * It sends a request to react to an iBet to the web server using an async task.
 * Furthermore it processes the answer and redirects it to the bet detail presenter.
 */

public class BetDetailModel implements BetDetailInterfaces.BetDetailModelOps, AsyncInterfaces.BetReactionAsyncInterface {

    private BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps;

    public BetDetailModel(BetDetailInterfaces.BetDetailRequiredPresenterOps betDetailRequiredPresenterOps){
        this.betDetailRequiredPresenterOps = betDetailRequiredPresenterOps;
    }

    /**
     * Creates a new async task to send a reaction for a bet to the web server.
     * @param betId The id of the bet to react to
     * @param newStatus The new status of the id
     */
    @Override
    public void reactToBet(int betId, String newStatus) {
        BetReactionAsyncTask betReactionAsyncTask = new BetReactionAsyncTask();
        betReactionAsyncTask.betReactionAsyncInterface = this;
        betReactionAsyncTask.execute(Integer.toString(betId), newStatus);
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
