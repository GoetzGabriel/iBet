package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.models.SignupModel;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The presenter for the sign up activity.
 * It redirects user sign up data to the model and the model response back to the view.
 */

public class SignupPresenterImpl implements SignupInterfaces.SignupPresenter, SignupInterfaces.SignupRequiredPresenterOps {

    private static final String TAG = "SignupPresenterImpl";
    private SignupInterfaces.SignupView signupView;
    private SignupModel signupModel;

    public SignupPresenterImpl(SignupInterfaces.SignupView signupView){
        this.signupView = signupView;
        signupModel = new SignupModel(this);
    }

    /**
     * Redirect the user sign up data to the model
     * @param username The username to sign up
     * @param password The user password to sign up
     */
    @Override
    public void signUp(String username, String password) {
        if(validateInput(username, password)){
            signupModel.createNewAccount(username, password);
        }else{
            signupView.showErrorMessageInvalidInput();
        }
    }

    /**
     * Validate the username and password to be signed up
     * @param username The username for the new account
     * @param password The password for the new account
     * @return
     */
    private boolean validateInput(String username, String password) {
        return username.matches(Constants.REGEX_SIGN_UP) && password.matches(Constants.REGEX_SIGN_UP);
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
