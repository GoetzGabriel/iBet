package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.models.BetDetailModel;

/**
 * The presenter for the bet detail activity.
 * It redirects a reaction for a bet to the model and the model response back to the view.
 */

public class BetDetailPresenterImpl implements BetDetailInterfaces.BetDetailPresenter, BetDetailInterfaces.BetDetailRequiredPresenterOps {

    private BetDetailInterfaces.BetDetailView betDetailView;
    private BetDetailModel betDetailModel;

    public BetDetailPresenterImpl(BetDetailInterfaces.BetDetailView betDetailView){
        this.betDetailView = betDetailView;
        betDetailModel = new BetDetailModel(this);
    }

    /**
     * Redirect the bet and the reaction to the bet to the model
     * @param betId Id of the bet to react to
     * @param newStatus The new status of the bet
     */
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
