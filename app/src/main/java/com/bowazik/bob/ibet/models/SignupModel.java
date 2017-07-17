package com.bowazik.bob.ibet.models;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.network.AsyncInterfaces;

/**
 * Created by bob on 17.07.17.
 */

public class SignupModel implements SignupInterfaces.SignupModelOps, AsyncInterfaces.SignupAsyncInterface {

    private SignupInterfaces.SignupRequiredPresenterOps signupRequiredPresenterOps;

    public SignupModel(SignupInterfaces.SignupRequiredPresenterOps signupRequiredPresenterOps){
        this.signupRequiredPresenterOps = signupRequiredPresenterOps;
    }

    @Override
    public void createNewAccount(String username, String password) {

    }

    @Override
    public void onSignupSuccess() {

    }

    @Override
    public void onSignupNameTakenError() {

    }

    @Override
    public void onSignupError() {

    }
}
