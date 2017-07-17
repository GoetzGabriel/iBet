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
    private String titleRegex = "^[a-zA-Z0-9]+$",
            descRegex = "^[a-zA-Z0-9]+$",
            contenderRegex = "^[0-9]+$",
            valueRegex = "^[0-9]+$";

    public NewBetPresenterImpl(NewBetInterfaces.NewBetView newBetView, Context context){
        this.newBetView = newBetView;
        this.newBetModel = new NewBetModel(this, context);
    }

    /**
     * Validate the user data and redirect it to the model if valid
     * @param title
     * @param description
     * @param contender
     * @param value
     */
    public void doSubmitBet(String title, String description, String contender, String value){
        if(validate(title, description, contender, value)){
            newBetModel.addNewIBet(title, description, contender, value);
        }else{
            newBetView.showErrorMessageForContenderBet();
        }
    }

    private boolean validate(String title, String description, String contender, String value) {
        return title.matches(titleRegex) && description.matches(descRegex) && contender.matches(contenderRegex) && value.matches(valueRegex);
    }

    @Override
    public void onCreateBetSuccess() {
        newBetView.showSuccessMessageForContenderBet();
    }

    @Override
    public void onCreateBetError() {
        newBetView.showErrorMessageForContenderBet();
    }
}
