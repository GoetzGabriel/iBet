package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.MenuInterfaces;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 22.06.17.
 */
public class MenuPresenterTest {

    @Mock
    private MenuInterfaces.MenuView menuView;

    private MenuPresenterImpl menuPresenter;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        menuPresenter = new MenuPresenterImpl(menuView);
    }

    @Test
    public void buttonNewBetClicked_callsViewOp(){
        menuPresenter.onButtonNewBetClicked();
        verify(menuView).startNewBetActivity();
    }

    @Test
    public void buttonActiveBetClicked_callsViewOp(){
        menuPresenter.onButtonBetFeedClicked();
        verify(menuView).startBetFeedActivity();
    }

    @Test
    public void buttonBetHistoryClicked_callsViewOp(){
        menuPresenter.onButtonBetHistoryClicked();
        verify(menuView).startHistoryActivity();
    }

    @Test
    public void buttonExitClicked_callsViewOp(){
        menuPresenter.onButtonBetExitClicked();
        verify(menuView).startLoginActivity();
    }

}