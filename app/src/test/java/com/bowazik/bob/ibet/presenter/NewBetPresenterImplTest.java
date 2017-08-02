package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.network.CreateBetAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 22.06.17.
 */
public class NewBetPresenterImplTest {

    @Mock
    private NewBetInterfaces.NewBetView newBetView;

    private NewBetPresenterImpl newBetPresenter;
    private Context context;
    private CreateBetAsyncTask createBetAsyncTask = Mockito.mock(CreateBetAsyncTask.class);

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        newBetPresenter = new NewBetPresenterImpl(newBetView, context);
    }

}