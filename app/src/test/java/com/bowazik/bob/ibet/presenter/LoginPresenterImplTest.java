package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.models.LoginModel;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bob on 08.05.17.
 */
public class LoginPresenterImplTest {

    @Mock
    private LoginInterfaces.LoginView loginView;

    private int testUserId = 10;
    private LoginPresenterImpl loginPresenterImpl;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        //Get a reference to the class to test
        loginPresenterImpl = new LoginPresenterImpl(loginView);
    }

    @Test
    public void modelLoginSuccess_callsViewOp(){
        loginPresenterImpl.onLoginSuccess(testUserId);
        verify(loginView).showLoginSuccessMessage(testUserId);
    }

    @Test
    public void modelLoginErrorAttemptNotExcced_callsViewOp(){
        loginPresenterImpl.onLoginError();
        verify(loginView).showErrorMessageForUsernamePassword();
    }

    @Test
    public void modelLoginErrorAttemptExceed_callsViewOp(){
        Assert.assertEquals(1, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(2, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(3, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(4, loginPresenterImpl.incrementLoginAttempts());
        loginPresenterImpl.onLoginError();
        verify(loginView).showErrorMessageForExceededAttempts();
    }

    @Test
    public void checkLoginAttemptExceeded(){
        Assert.assertEquals(1, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(2, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertEquals(3, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertTrue(loginPresenterImpl.isLoginAttemptExceeded());
    }

    @Test
    public void checkLoginAttemptNotExceeded(){
        loginPresenterImpl = new LoginPresenterImpl(loginView);
        Assert.assertEquals(1, loginPresenterImpl.incrementLoginAttempts());
        Assert.assertFalse(loginPresenterImpl.isLoginAttemptExceeded());
    }

    @After
    public void tearDown() throws Exception {

    }

}