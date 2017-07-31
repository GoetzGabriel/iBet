package com.bowazik.bob.ibet.interfaces;

import com.bowazik.bob.ibet.data.iBet;

import java.util.List;

/**
 * The interfaces for the bet history activity/view, presenter and model
 */

public interface HistoryInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface HistoryView{
        void setHistoryList(List<iBet> betHistoryList);

        void showHistoryFetchErrorMessage(String error);
    }

    /**
     * Presenter operations offered to the view
     */
    interface HistoryPresenter{
        void requestBetHistory();
    }

    /**
     * Presenter operations offered to the model
     */
    interface HistoryRequiredPresenterOps{
        void onBetHistoryFetched(List<iBet> betHistoryList);

        void onBetHistoryFetchError(String error);
    }

    /**
     * Model operations offered to the presenter
     */
    interface HistoryModel{
        void fetchBetHistory();
    }
}
