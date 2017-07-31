package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.models.LoginModel;

/**
 * The presenter for the login activity.
 * It redirects user login data to the model and the model response back to the view.
 */

public class LoginPresenterImpl implements LoginInterfaces.LoginPresenter, LoginInterfaces.LoginRequiredPresenterOps{

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private final LoginInterfaces.LoginView loginView;
    private int loginAttempt;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginInterfaces.LoginView loginView){
        this.loginView = loginView;
        this.loginModel = new LoginModel(this);
    }

    /**
     * Redirect the user login data to the model
     * @param username The username used to login
     * @param password The user password used to login
     */
    public void doLogin(String username, String password){
        loginModel.checkUserLogin(username, password);
    }

    public int incrementLoginAttempts() {
        loginAttempt = loginAttempt + 1;
        return loginAttempt;
    }

    public boolean isLoginAttemptExceeded() {
        return loginAttempt >= MAX_LOGIN_ATTEMPTS;
    }

    /**
     * Redirect the user id to the view if the login validation succeeded
     * @param userId Id of the successfully logged in user
     */
    @Override
    public void onLoginSuccess(int userId) {
        loginAttempt = 0;
        loginView.showLoginSuccessMessage(userId);
    }

    @Override
    public void onLoginError() {
        if(loginAttempt > MAX_LOGIN_ATTEMPTS){
            loginView.showErrorMessageForExceededAttempts();
            return;
        }
        incrementLoginAttempts();
        loginView.showErrorMessageForUsernamePassword();
    }
}
