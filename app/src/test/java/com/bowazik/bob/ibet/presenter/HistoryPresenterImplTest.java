package com.bowazik.bob.ibet.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.interfaces.LoginInterfaces;

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
public class HistoryPresenterImplTest {

    @Mock
    private HistoryInterfaces.HistoryView historyView;

    private int testUserId = 10;
    private Context context;
    private HistoryPresenterImpl historyPresenter;
    private List<iBet> testBetHistoryList = new ArrayList<>();
    private SharedPreferences sharedPreferences;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.sharedPreferences = Mockito.mock(SharedPreferences.class);
        this.context = Mockito.mock(Context.class);
        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);


        testBetHistoryList.add(new iBet(1, 10, 11, "Test1", "Test1", 8, "won", "Peter"));
        testBetHistoryList.add(new iBet(1, 10, 11, "Test2", "Test2", 4, "lost", "Peter"));
        testBetHistoryList.add(new iBet(1, 11, 10, "Test3", "Test3", 4, "won", "Hans"));
        testBetHistoryList.add(new iBet(1, 11, 10, "Test4", "Test4", 4, "lost", "Hans"));

        //Get a reference to the class to test
        historyPresenter = new HistoryPresenterImpl(historyView, context);
    }

    @Test
    public void historyModelFetchSuccess_CallsSetBalanceOp(){
        historyPresenter.onBetHistoryFetched(testBetHistoryList, testUserId);
        verify(historyView).setBetBalance(4);
    }

    @Test
    public void historyModelFetchEmptyList_CallsViewSetBalanceOp(){
        testBetHistoryList.clear();
        historyPresenter.onBetHistoryFetched(testBetHistoryList, testUserId);
        verify(historyView).setBetBalance(0);
    }

    @Test
    public void historyModelFetchError_CallsViewShowHistoryFetchErrorOp(){
        historyPresenter.onBetHistoryFetchError("error");
        verify(historyView).showHistoryFetchErrorMessage("error");
    }

    @Test
    public void historyModelFetchSuccess_CallsViewSetHistoryListOp(){
        historyPresenter.onBetHistoryFetched(testBetHistoryList, testUserId);
        verify(historyView).setHistoryList(testBetHistoryList);
    }

}