package com.bowazik.bob.ibet.presenter;

import android.content.Context;

import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.models.NewBetModel;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The presenter for the new bet activity.
 * It redirects new bet data to the model and the model response back to the view.
 */

public class NewBetPresenterImpl implements NewBetInterfaces.NewBetPresenter, NewBetInterfaces.NewBetRequiredPresenterOps{

    private static final String TAG = "NewBetPresenter";
    private final NewBetInterfaces.NewBetView newBetView;
    private NewBetModel newBetModel;

    public NewBetPresenterImpl(NewBetInterfaces.NewBetView newBetView, Context context){
        this.newBetView = newBetView;
        this.newBetModel = new NewBetModel(this, context);
    }

    /**
     * Redirect the new bet data to the model if it is valid.
     * Otherwise show an error alert.
     * @param title The title of the new bet
     * @param description The description of the new bet
     * @param contender The contender for the new bet
     * @param value THe value of the new bet
     */
    public void doSubmitBet(String title, String description, String contender, String value){
        if(validate(title, description, contender, value)){
            newBetModel.addNewIBet(title, description, contender, value);
        }else{
            newBetView.showErrorMessageForContenderBet();
        }
    }

    /**
     * Validate the new bet data and redirect it to the model if valid
     * @param title The title of the new bet
     * @param description The description of the new bet
     * @param contender The contender for the new bet
     * @param value THe value of the new bet
     */
    private boolean validate(String title, String description, String contender, String value) {
        return title.matches(Constants.REGEX_NEW_BET_TITLE) &&
                description.matches(Constants.REGEX_NEW_BET_DESCRIPTION) &&
                contender.matches(Constants.REGEX_NEW_BET_CONTENDER) &&
                value.matches(Constants.REGEX_NEW_BET_VALUE);
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
