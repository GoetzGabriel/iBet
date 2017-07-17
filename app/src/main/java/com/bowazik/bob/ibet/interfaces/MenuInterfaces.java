package com.bowazik.bob.ibet.interfaces;

/**
 * Created by bob on 08.05.17.
 */
public interface MenuInterfaces {

    interface MenuView{
        void startNewBetActivity();

        void startBetFeedActivity();

        void exitApp();

        void startLoginActivity();
    }

    interface MenuPresenter{
        void onButtonNewBetClicked();

        void onButtonBetFeedClicked();

        void onButtonBetExitClicked();
    }

}
