package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.models.HistoryModel;

import java.util.List;

/**
 * Created by bob on 19.07.17.
 */

public class HistoryPresenterImpl implements HistoryInterfaces.HistoryPresenter, HistoryInterfaces.HistoryRequiredPresenterOps {

    private HistoryInterfaces.HistoryView historyView;
    private HistoryModel historyModel;

    public HistoryPresenterImpl(HistoryInterfaces.HistoryView historyView, Context context){
        this.historyView = historyView;
        historyModel = new HistoryModel(this, context);
    }

    @Override
    public void requestBetHistory() {
        historyModel.fetchBetHistory();
    }

    @Override
    public void onBetHistoryFetched(List<iBet> betHistoryList) {
        historyView.setHistoryList(betHistoryList);
    }

    @Override
    public void onBetHistoryFetchError(String error) {
        historyView.showHistoryFetchErrorMessage(error);
    }
}
