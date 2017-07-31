package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;
import com.bowazik.bob.ibet.network.SignupAsyncTask;

/**
 * The Model for the sign up activity.
 * It sends user data to the web server that validates it.
 * If the data is valid the server inserts a new account into the DB and
 * sends a success otherwise it sends an error message.
 */

public class SignupModel implements SignupInterfaces.SignupModelOps, AsyncInterfaces.SignupAsyncInterface {

    private SignupInterfaces.SignupRequiredPresenterOps signupRequiredPresenterOps;

    public SignupModel(SignupInterfaces.SignupRequiredPresenterOps signupRequiredPresenterOps){
        this.signupRequiredPresenterOps = signupRequiredPresenterOps;
    }

    /**
     * Send the new account user data to the web server using async task
     * @param username The new username
     * @param password The new password
     */
    @Override
    public void createNewAccount(String username, String password) {
        SignupAsyncTask signupAsyncTask = new SignupAsyncTask();
        signupAsyncTask.signupAsyncInterface = this;
        signupAsyncTask.execute(username, password);
    }

    @Override
    public void onSignupSuccess() {
        signupRequiredPresenterOps.onSignupSuccess();
    }

    @Override
    public void onSignupNameTakenError() {

    }

    @Override
    public void onSignupError() {
        signupRequiredPresenterOps.onSignupError();
    }
}
