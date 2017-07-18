package com.bowazik.bob.ibet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetDetailInterfaces;
import com.bowazik.bob.ibet.presenter.BetDetailPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * Created by bob on 18.07.17.
 */

public class BetDetailActivity extends AppCompatActivity implements BetDetailInterfaces.BetDetailView {

    private static final String TAG = "BetDetailActivity";
    private BetDetailPresenterImpl betDetailPresenter;

    private TextView titleTextView, descTextView, contenderTextView, valueTextView;
    private Button acceptBtn, declineBtn;

    private iBet activeIbet;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_detail);

        fetchIntentData();
        initPresenter();
        initView();
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

        //Set the button clickListener
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //betDetailPresenter.acceptBet();
            }
        });
        declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //betDetailPresenter.declineBet();
            }
        });
    }

    private void initPresenter() {
        betDetailPresenter = new BetDetailPresenterImpl(this);
    }

    @Override
    public void showBetReactionSuccessMessage() {

    }

    @Override
    public void showBetRectionErrorMessage() {

    }
}
