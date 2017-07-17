package com.bowazik.bob.ibet.interfaces;

/**
 * Created by bob on 17.07.17.
 */

public interface SignupInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface SignupView{
        void showErrorMessageUsernameTaken();

        void showErrorMessageInvalidInput();

        void showSignupSuccessMessage();

        void showErrorMessageServer();
    }

    /**
     * Presenter operations offered to the view
     */
    interface SignupPresenter{
        void signUp(String username, String password);
    }

    /**
     * Presenter operations offered to the model
     */
    interface SignupRequiredPresenterOps{
        void onSignupSuccess();

        void onSignupError();
    }

    /**
     * Model operations offered to the presenter
     */
    interface SignupModelOps{
        void createNewAccount(String username, String password);
    }
}
