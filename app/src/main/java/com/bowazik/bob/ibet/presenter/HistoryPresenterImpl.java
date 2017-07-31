package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.models.HistoryModel;

import java.util.List;

/**
 * The presenter for the bet history activity.
 * It redirects a request to fetch the bet history to the model and the model response back to the view.
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

    /**
     * Call the presenter callback operation on success and hand the received list
     * @param betHistoryList List containing the bet history
     */
    @Override
    public void onBetHistoryFetched(List<iBet> betHistoryList) {
        historyView.setHistoryList(betHistoryList);
    }

    @Override
    public void onBetHistoryFetchError(String error) {
        historyView.showHistoryFetchErrorMessage(error);
    }
}
