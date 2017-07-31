package com.bowazik.bob.ibet.interfaces;

/**
 * The interfaces for the menu activity/view, presenter and model
 */

public interface MenuInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface MenuView{
        void startNewBetActivity();

        void startBetFeedActivity();

        void exitApp();

        void startLoginActivity();

        void startHistoryActivity();
    }

    /**
     * Presenter operations offered to the view
     */
    interface MenuPresenter{
        void onButtonNewBetClicked();

        void onButtonBetFeedClicked();

        void onButtonBetExitClicked();

        void onButtonBetHistoryClicked();
    }

}
