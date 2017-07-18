package com.bowazik.bob.ibet.interfaces;

/**
 * Created by bob on 18.07.17.
 */

public interface BetDetailInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface BetDetailView{

        void showBetReactionSuccessMessage();

        void showBetRectionErrorMessage();
    }

    /**
     * Presenter operations offered to the view
     */
    interface BetDetailPresenter{

        void acceptBet(int betId);

        void declineBet(int betId);
    }

    /**
     * Presenter operations offered to the model
     */
    interface BetDetailRequiredPresenterOps{

        void onBetReactionSuccess();

        void onBetReactionError();
    }

    /**
     * Model operations offered to the presenter
     */
    interface BetDetailModelOps{

        void acceptBet(int betId);

        void declineBet(int betId);
    }
}
