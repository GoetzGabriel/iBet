package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 08.05.17.
 */
public class LoginPresenterImplTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkLoginAttemptExceeded(){
        LoginInterfaces.LoginView loginView = mock(LoginInterfaces.LoginView.class);
        LoginPresenterImpl loginPresenterImpl = new LoginPresenterImpl(loginView);
        Assert.assertEquals(1, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(2, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(3, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertTrue(loginPresenterImpl.isLoginAttemptExceeded());
    }

    @Test
    public void checkLoginAttemptNotExceeded(){
        LoginInterfaces.LoginView loginView = mock(LoginInterfaces.LoginView.class);
        LoginPresenterImpl loginPresenterImpl = new LoginPresenterImpl(loginView);
        Assert.assertEquals(1, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertFalse(loginPresenterImpl.isLoginAttemptExceeded());
    }

    @Test
    public void checkUsernameAndPasswordIsCorrect(){
        LoginInterfaces.LoginView loginView = mock(LoginInterfaces.LoginView.class);
        LoginPresenterImpl loginPresenterImpl = new LoginPresenterImpl(loginView);
        loginPresenterImpl.doLogin("u", "p");
        verify(loginView).showLoginSuccessMessage();
    }

    @Test
    public void checkUsernameAndPasswordIsNotCorrect(){
        LoginInterfaces.LoginView loginView = mock(LoginInterfaces.LoginView.class);
        LoginPresenterImpl loginPresenterImpl = new LoginPresenterImpl(loginView);
        loginPresenterImpl.doLogin("wrongUn", "wrongPw");
        verify(loginView).showErrorMessageForUsernamePassword();
    }

}