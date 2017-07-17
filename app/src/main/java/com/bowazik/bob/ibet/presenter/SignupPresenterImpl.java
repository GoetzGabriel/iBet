package com.bowazik.bob.ibet.presenter;

import android.util.Log;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.models.SignupModel;

/**
 * Created by bob on 17.07.17.
 */

public class SignupPresenterImpl implements SignupInterfaces.SignupPresenter, SignupInterfaces.SignupRequiredPresenterOps {

    private static final String TAG = "SignupPresenterImpl";
    private SignupInterfaces.SignupView signupView;
    private SignupModel signupModel;
    private String inputRegex = "^[a-zA-Z0-9]+$";

    public SignupPresenterImpl(SignupInterfaces.SignupView signupView){
        this.signupView = signupView;
        signupModel = new SignupModel(this);
    }

    @Override
    public void signUp(String username, String password) {
        if(validateInput(username, password)){
            signupModel.createNewAccount(username, password);
        }else{
            signupView.showErrorMessageInvalidInput();
        }
    }

    private boolean validateInput(String username, String password) {
        return username.matches(inputRegex) && password.matches(inputRegex);
    }

    @Override
    public void onSignupSuccess() {
        signupView.showSignupSuccessMessage();
    }

    @Override
    public void onSignupError() {
        signupView.showErrorMessageServer();
    }
}
