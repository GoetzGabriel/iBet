package com.bowazik.bob.ibet.presenter;

import android.content.Context;
import android.util.Log;

import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.models.NewBetModel;

/**
 * Created by bob on 22.06.17.
 */

public class NewBetPresenterImpl implements NewBetInterfaces.NewBetPresenter, NewBetInterfaces.NewBetRequiredPresenterOps{

    private static final String TAG = "NewBetPresenter";
    private final NewBetInterfaces.NewBetView newBetView;
    private NewBetModel newBetModel;

    public NewBetPresenterImpl(NewBetInterfaces.NewBetView newBetView, Context context){
        this.newBetView = newBetView;
        this.newBetModel = new NewBetModel(this, context);
    }

    public void doSubmitBet(String contender, String bet){
        if(contender.equals("") || bet.equals("")){
            newBetView.showErrorMessageForContenderBet();
            return;
        }

        if(contender.equals(" ") || bet.equals(" ")){
            newBetView.showErrorMessageForContenderBet();
            return;
        }
        newBetModel.addNewIBet(new iBet(5, 1, 2, "title", bet, 3, 123456d, "pending", "Wettpartner"));
    }

    @Override
    public void onIbetAdded(iBet iBet) {
        Log.v(TAG, iBet.getDescription());
        newBetView.showSuccessMessageForContenderBet();
    }
}
