package com.bowazik.bob.ibet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.presenter.BetDetailPresenterImpl;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The bet detail activity of the app.
 * Information according the chosen bet is displayed.
 * The user can react to the bet using different buttons depending on the state of the bet.
 * If the bet is pending the user can accept or decline it.
 * If the bet is active the user can tag is as won or lost.
 * If the reaction succeeded an success message is displayed.
 * Otherwise an error alert is shown.
 */

public class BetDetailActivity extends AppCompatActivity implements BetDetailInterfaces.BetDetailView {

    private static final String TAG = "BetDetailActivity";
    private BetDetailPresenterImpl betDetailPresenter;
    private IbetSharedPrefs ibetSharedPrefs;

    private TextView titleTextView, descTextView, contenderTextView, valueTextView, infoTextView;
    private Button acceptBtn, declineBtn;

    private iBet activeIbet;
    private Boolean pending, created;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_detail);

        fetchIntentData();
        setBetFlags();
        initPresenter();
        initView();
    }

    /**
     * Set the pending and created flags for the active bet indicating whether the active bet is
     * pending or active and created from the user created from another user
     */
    private void setBetFlags() {
        //Load the user id from the sharedPrefs
        ibetSharedPrefs = new IbetSharedPrefs(this);

        pending = (activeIbet.getStatus().equals(Constants.IBET_STATUS_PENDING));
        created = (activeIbet.getCreator() == ibetSharedPrefs.getUserId());
    }

    //Get the iBet data to display from the intent
    private void fetchIntentData() {
        Intent startBetDetailAcitivity = getIntent();
        activeIbet = (iBet) startBetDetailAcitivity.getSerializableExtra(Constants.IBET_INTENT_BET_TAG);
    }

    private void initView() {
        //Get the button and textview view references
        acceptBtn = (Button) findViewById(R.id.bet_detail_accept_btn);
        declineBtn = (Button) findViewById(R.id.bet_detail_decline_btn);
        titleTextView = (TextView) findViewById(R.id.bet_detail_title);
        descTextView = (TextView) findViewById(R.id.bet_detail_description);
        contenderTextView = (TextView) findViewById(R.id.bet_detail_contender);
        valueTextView = (TextView) findViewById(R.id.bet_detail_stake);
        infoTextView = (TextView) findViewById(R.id.bet_detail_info);

        //Display the iBet data
        titleTextView.setText(activeIbet.getTitle());
        descTextView.setText(activeIbet.getDescription());
        contenderTextView.setText(activeIbet.getContenderName());
        valueTextView.setText(Integer.toString(activeIbet.getValue()));

        //Hide the accept and decline buttons if the active bet is created by the user and pending
        //Hide the waiting for contender info textView change the buttons to set to won and set to lost
        // and set the according clickListener if the bet is active
        //Else hide the info textView and set the accept/decline bet clickListener
        if(pending && created) {
            acceptBtn.setVisibility(View.GONE);
            declineBtn.setVisibility(View.GONE);
        }else if(!pending ){
            infoTextView.setVisibility(View.GONE);
            acceptBtn.setText(Constants.IBET_BTN_SET_WON);
            declineBtn.setText(Constants.IBET_BTN_SET_LOST);
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.reactToBet(activeIbet.getId(), Constants.IBET_BET_REACTION_WON);
                }
            });
            declineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.reactToBet(activeIbet.getId(), Constants.IBET_BET_REACTION_LOST);
                }
            });
        }else{
            infoTextView.setVisibility(View.GONE);
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.reactToBet(activeIbet.getId(), Constants.IBET_BET_REACTION_ACCEPT);
                }
            });
            declineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.reactToBet(activeIbet.getId(), Constants.IBET_BET_REACTION_DECLINED);
                }
            });
        }
    }

    //Initiate the bet detail presenter
    private void initPresenter() {
        betDetailPresenter = new BetDetailPresenterImpl(this);
    }

    //Show an success message if the reaction to the bet succeeded
    @Override
    public void showBetReactionSuccessMessage() {
        Toast.makeText(this, Constants.MESSAGE_SUCCESS_BET_REACTION, Toast.LENGTH_LONG).show();
        startBetFeedActivity();
    }

    //Use an intent to start the bet feed activity
    private void startBetFeedActivity() {
        Intent startBetFeedActivity = new Intent(this, BetFeedActivity.class);
        startActivity(startBetFeedActivity);
    }

    //Show an error alert if the reaction to the bet did not succeed
    @Override
    public void showBetRectionErrorMessage() {
        Toast.makeText(this, Constants.MESSAGE_ERROR_BET_REACTION, Toast.LENGTH_LONG).show();
    }
}
