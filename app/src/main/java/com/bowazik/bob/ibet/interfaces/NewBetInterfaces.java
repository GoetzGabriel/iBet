package com.bowazik.bob.ibet.interfaces;

/**
 * The interfaces for the new bet activity/view, presenter and model
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

        void doSubmitBet(String title, String description, String contender, String value);
    }

    /**
     * Presenter operations offered to the model
     */
    interface NewBetRequiredPresenterOps{

        void onCreateBetSuccess();

        void onCreateBetError();
    }

    /**
     * Model operations offered to the presenter
     */
    interface NewBetModelOps{
        void addNewIBet(String title, String description, String contender, String value);
    }
}
