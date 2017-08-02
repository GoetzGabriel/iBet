package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.models.BetDetailModel;
import com.bowazik.bob.ibet.utility.Constants;

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
     * @param activeBet The bet to update
     * @param newStatus The new status of the bet
     * @param userId The id of the user
     */
    @Override
    public void reactToBet(iBet activeBet, String newStatus, int userId) {

        String updateStatus = determineNewStatus(activeBet, newStatus, userId);

        betDetailModel.reactToBet(activeBet.getId(), updateStatus);
    }

    /**
     * Check whether the user is the creator or contender of the bet
     * The status of the bet in the DB is saved refering to the creator
     * THe status of the contender is therefore the opposite
     */
    private String determineNewStatus(iBet activeBet, String newStatus, int userId) {
        if(activeBet.getCreator() == userId || newStatus.equals(Constants.IBET_BET_REACTION_ACCEPT) || newStatus.equals(Constants.IBET_BET_REACTION_DECLINED)){
            return newStatus;
        }else{
            return (newStatus.equals(Constants.IBET_STATUS_WON)) ? Constants.IBET_STATUS_LOST : Constants.IBET_STATUS_WON;
        }
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
