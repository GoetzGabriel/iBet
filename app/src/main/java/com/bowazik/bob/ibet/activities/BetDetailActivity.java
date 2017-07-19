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
 * Created by bob on 18.07.17.
 */

public class BetDetailActivity extends AppCompatActivity implements BetDetailInterfaces.BetDetailView {

    private static final String TAG = "BetDetailActivity";
    private BetDetailPresenterImpl betDetailPresenter;
    private IbetSharedPrefs ibetSharedPrefs;

    private TextView titleTextView, descTextView, contenderTextView, valueTextView;
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

        //Display the iBet data
        titleTextView.setText(activeIbet.getTitle());
        descTextView.setText(activeIbet.getDescription());
        contenderTextView.setText(activeIbet.getContenderName());
        valueTextView.setText(Integer.toString(activeIbet.getValue()));

        //Hide the accept and decline buttons if the active bet is either created by the user or already active
        //otherwise set the button clickListeners
        if(!pending || created){
            acceptBtn.setVisibility(View.GONE);
            declineBtn.setVisibility(View.GONE);
        }else{
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.acceptBet(activeIbet.getId());
                }
            });
            declineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    betDetailPresenter.declineBet(activeIbet.getId());
                }
            });
        }
    }

    private void initPresenter() {
        betDetailPresenter = new BetDetailPresenterImpl(this);
    }

    @Override
    public void showBetReactionSuccessMessage() {
        Toast.makeText(this, Constants.MESSAGE_SUCCESS_BET_REACTION, Toast.LENGTH_LONG).show();
        startBetFeedActivity();
    }

    private void startBetFeedActivity() {
        Intent startBetFeedActivity = new Intent(this, BetFeedActivity.class);
        startActivity(startBetFeedActivity);
    }

    @Override
    public void showBetRectionErrorMessage() {
        Toast.makeText(this, Constants.MESSAGE_ERROR_BET_REACTION, Toast.LENGTH_LONG).show();
    }
}
