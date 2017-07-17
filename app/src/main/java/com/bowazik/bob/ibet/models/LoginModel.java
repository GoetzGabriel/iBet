package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.LoginAsyncTask;

/**
 * Created by bob on 17.07.17.
 */

public class LoginModel implements LoginInterfaces.LoginModelOps, AsyncInterfaces.LoginAsyncInterface {

    private LoginInterfaces.LoginRequiredPresenterOps loginRequiredPresenterOps;
    private LoginAsyncTask loginAsyncTask;

    public LoginModel(LoginInterfaces.LoginRequiredPresenterOps loginRequiredPresenterOps){
        this.loginRequiredPresenterOps = loginRequiredPresenterOps;
    }

    @Override
    public void checkUserLogin(String username, String password) {
        loginAsyncTask = new LoginAsyncTask();
        loginAsyncTask.loginAsyncInterface = this;
        loginAsyncTask.execute(username, password);
    }

    @Override
    public void onLoginSuccess() {
        loginRequiredPresenterOps.onLoginSuccess();
    }

    @Override
    public void onLoginError() {
        loginRequiredPresenterOps.onLoginError();
    }
}
