package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.models.SignupModel;

/**
 * Created by bob on 17.07.17.
 */

public class SignupPresenterImpl implements SignupInterfaces.SignupPresenter, SignupInterfaces.SignupRequiredPresenterOps {

    private SignupInterfaces.SignupView signupView;
    private SignupModel signupModel;

    public SignupPresenterImpl(SignupInterfaces.SignupView signupView){
        this.signupView = signupView;
        signupModel = new SignupModel(this);
    }

    @Override
    public void signUp(String username, String password) {
        validateInput(username, password);
    }

    private void validateInput(String username, String password) {
        
    }

    @Override
    public void onSignupSuccess() {
        signupView.showSignupSuccessMessage();
    }

    @Override
    public void onSignupError() {
        signupView.showErrorMessageUsernameTaken();
    }
}
