package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.models.LoginModel;

/**
 * Created by bob on 08.05.17.
 */

public class LoginPresenterImpl implements LoginInterfaces.LoginPresenter, LoginInterfaces.LoginRequiredPresenterOps{

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    public final LoginInterfaces.LoginView loginView;
    private int loginAttempt;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginInterfaces.LoginView loginView){
        this.loginView = loginView;
        this.loginModel = new LoginModel(this);
    }

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

    @Override
    public void onLoginSuccess() {
        loginAttempt = 0;
        loginView.showLoginSuccessMessage();
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
