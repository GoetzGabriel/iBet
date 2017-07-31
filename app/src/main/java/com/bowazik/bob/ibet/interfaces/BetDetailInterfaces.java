package com.bowazik.bob.ibet.interfaces;

/**
 * The interfaces for the bet detail activity/view, presenter and model
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

        void reactToBet(int betId, String newStatus);
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

        void reactToBet(int betId, String newStatus);
    }
}
