package com.bowazik.bob.ibet.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by bob on 01.08.17.
 */
public class BetFeedPresenterImplTest {

    @Mock
    BetFeedInterfaces.BetFeedView betFeedView;

    private BetFeedPresenterImpl betFeedPresenter;
    private Context context;
    private List<iBet> testBetHistoryList = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.sharedPreferences = Mockito.mock(SharedPreferences.class);
        this.context = Mockito.mock(Context.class);

        testBetHistoryList.add(new iBet(1, 10, 11, "Test1", "Test1", 8, "won", "Peter"));

        //Get a reference to the class to test
        betFeedPresenter = new BetFeedPresenterImpl(betFeedView, context);
    }

    @Test
    public void betFeedModelFetchSuccess_callsViewSetBetListOp(){
        betFeedPresenter.onBetListFetched(testBetHistoryList, testBetHistoryList);
        verify(betFeedView).setBetFeedList(testBetHistoryList, testBetHistoryList);
    }


}