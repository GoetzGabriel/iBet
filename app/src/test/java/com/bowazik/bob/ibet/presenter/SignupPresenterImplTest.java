package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.models.SignupModel;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 17.07.17.
 */
public class SignupPresenterImplTest {


    @Test
    public void checkUsernameAndPasswordIsInvalid(){
        SignupInterfaces.SignupView signupView = mock(SignupInterfaces.SignupView.class);
        SignupPresenterImpl signupPresenter = new SignupPresenterImpl(signupView);
        signupPresenter.signUp("username@", "password@");
        verify(signupView).showErrorMessageInvalidInput();
    }

    @Test
    public void checkUsernameAndPasswordIsEmpty(){
        SignupInterfaces.SignupView signupView = mock(SignupInterfaces.SignupView.class);
        SignupPresenterImpl signupPresenter = new SignupPresenterImpl(signupView);
        signupPresenter.signUp("", "");
        verify(signupView).showErrorMessageInvalidInput();
    }
}