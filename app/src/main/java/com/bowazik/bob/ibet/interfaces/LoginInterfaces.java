package com.bowazik.bob.ibet.interfaces;

/**
 * Created by bob on 08.05.17.
 */

public interface LoginInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface LoginView{
        void showErrorMessageForUsernamePassword();

        void showErrorMessageForExceededAttempts();

        void showLoginSuccessMessage(int userId);
    }

    /**
     * Presenter operations offered to the view
     */
    interface LoginPresenter{
        void doLogin(String username, String password);

        int incrementLoginAttempts();

        boolean isLoginAttemptExceeded();
    }

    /**
     * Presenter operations offered to the model
     */
    interface LoginRequiredPresenterOps{
        void onLoginSuccess(int userId);

        void onLoginError();
    }

    /**
     * Model operations offered to the presenter
     */
    interface LoginModelOps{
        void checkUserLogin(String username, String password);
    }

}
