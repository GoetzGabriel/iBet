package com.bowazik.bob.ibet.interfaces;

import com.bowazik.bob.ibet.data.iBet;

/**
 * Created by bob on 22.06.17.
 */

public interface NewBetInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface NewBetView {

        void showErrorMessageForContenderBet();

        void showSuccessMessageForContenderBet();
    }

    /**
     * Presenter operations offered to the view
     */
    interface NewBetPresenter{

        void doSubmitBet(String contender, String bet);
    }

    /**
     * Presenter operations offered to the model
     */
    interface NewBetRequiredPresenterOps{

        void onIbetAdded(iBet iBet);
    }

    /**
     * Model operations offered to the presenter
     */
    interface NewBetModelOps{
        void addNewIBet(iBet iBet);
    }
}
