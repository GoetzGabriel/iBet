package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 22.06.17.
 */
public class NewBetPresenterImplTest {

    /**
    @Test
    public void checkContenderAndBetIsNotCorrect(){
        NewBetInterfaces.NewBetView newBetView = mock(NewBetInterfaces.NewBetView.class);
        NewBetPresenterImpl newBetPresenterImpl = new NewBetPresenterImpl(newBetView, );
        newBetPresenterImpl.doSubmitBet("", "");
        verify(newBetView).showErrorMessageForContenderBet();
    }

    @Test
    public void checkContenderAndBetIsEmpty(){
        NewBetView newBetView = mock(NewBetView.class);
        NewBetPresenterImpl newBetPresenterImpl = new NewBetPresenterImpl(newBetView);
        newBetPresenterImpl.doSubmitBet(" ", " ");
        verify(newBetView).showErrorMessageForContenderBet();
    }

    @Test
    public void checkContenderAndBetIsCorrect(){
        NewBetView newBetView = mock(NewBetView.class);
        NewBetPresenterImpl newBetPresenterImpl = new NewBetPresenterImpl(newBetView);
        newBetPresenterImpl.doSubmitBet("Example contender", "Example bet");
        verify(newBetView).showSuccessMessageForContenderBet();
    }*/
}