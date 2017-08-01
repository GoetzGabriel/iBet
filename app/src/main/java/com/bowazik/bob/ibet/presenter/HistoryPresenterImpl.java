package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.models.HistoryModel;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.List;

/**
 * The presenter for the bet history activity.
 * It redirects a request to fetch the bet history to the model and the model response back to the view.
 */

public class HistoryPresenterImpl implements HistoryInterfaces.HistoryPresenter, HistoryInterfaces.HistoryRequiredPresenterOps {

    private HistoryInterfaces.HistoryView historyView;
    private HistoryModel historyModel;
    private Context context;

    public HistoryPresenterImpl(HistoryInterfaces.HistoryView historyView, Context context){
        this.historyView = historyView;
        this.context = context;

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

        calculateBetBalance(betHistoryList);
    }

    /**
     * Calculate the bet balance depending on the bets in the betHistoryList
     * @param betHistoryList The list of bets in the bet history
     */
    private void calculateBetBalance(List<iBet> betHistoryList) {
        IbetSharedPrefs ibetSharedPrefs = new IbetSharedPrefs(context);
        int betBalance = 0, userId = ibetSharedPrefs.getUserId();

        //Check every bet in the bet history whether it was won or lost by the creator
        //and add or subtract the bet value accordingly to the bet balance
        for(iBet bet : betHistoryList){
            if(bet.getCreator() == userId && bet.getStatus().equals(Constants.IBET_STATUS_WON)
                    || bet.getCreator() != userId && bet.getStatus().equals(Constants.IBET_STATUS_LOST)){
                betBalance += bet.getValue();
            }else{
                betBalance -= bet.getValue();
            }
        }

        //Call the according view callback operation
        historyView.setBetBalance(betBalance);
    }

    @Override
    public void onBetHistoryFetchError(String error) {
        historyView.showHistoryFetchErrorMessage(error);
    }
}
