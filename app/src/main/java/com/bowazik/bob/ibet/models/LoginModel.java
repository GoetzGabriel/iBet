package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.LoginAsyncTask;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;

/**
 * The Model for the login activity.
 * It sends user data to the web server that validates it.
 * If the data is valid the server sends a success otherwise an error message.
 */

public class LoginModel implements LoginInterfaces.LoginModelOps, AsyncInterfaces.LoginAsyncInterface {

    private LoginInterfaces.LoginRequiredPresenterOps loginRequiredPresenterOps;

    public LoginModel(LoginInterfaces.LoginRequiredPresenterOps loginRequiredPresenterOps){
        this.loginRequiredPresenterOps = loginRequiredPresenterOps;
    }

    /**
     * Send the user data to the web server using async task
     * @param username The username to be validated by the server
     * @param password The user's password to be validated by the server
     */
    @Override
    public void checkUserLogin(String username, String password) {
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.loginAsyncInterface = this;
        loginAsyncTask.execute(username, password);
    }

    /**
     * Redirect the user id to the login presenter on success
     * @param userId Id of the user to be logged in
     */
    @Override
    public void onLoginSuccess(int userId) {
        loginRequiredPresenterOps.onLoginSuccess(userId);
    }

    @Override
    public void onLoginError() {
        loginRequiredPresenterOps.onLoginError();
    }
}
