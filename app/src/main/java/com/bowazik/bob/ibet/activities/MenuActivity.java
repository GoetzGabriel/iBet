package com.bowazik.bob.ibet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bowazik.bob.ibet.LoginActivity;
import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.interfaces.MenuInterfaces;
import com.bowazik.bob.ibet.presenter.MenuPresenterImpl;

/**
 * The menu activity of the app.
 * The user can click four different buttons (new bet, active bets, bet history, exit)
 * that redirect the user to the according activity.
 * New bet: The user can create a new bet
 * Active Bets: The user can see his currently active bets
 * Bet history: The user can see his bet history
 * Exit: The user can log out
 */

public class MenuActivity extends AppCompatActivity implements MenuInterfaces.MenuView {

    private static final String TAG = "Main Menu";
    private MenuPresenterImpl menuPresenterImpl;
    private Button buttonNewBet, buttonBetFeed, buttonExit, btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initPresenter();
        initView();
    }

    private void initView() {
        buttonNewBet = (Button) findViewById(R.id.button_new_bet);
        buttonBetFeed = (Button) findViewById(R.id.button_running_bets);
        buttonExit = (Button) findViewById(R.id.button_exit);
        btnHistory = (Button) findViewById(R.id.button_history);

        buttonNewBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenterImpl.onButtonNewBetClicked();
            }
        });

        buttonBetFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenterImpl.onButtonBetFeedClicked();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenterImpl.onButtonBetExitClicked();
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenterImpl.onButtonBetHistoryClicked();
            }
        });
    }

    //Initiate the menu presenter
    private void initPresenter() {
        menuPresenterImpl = new MenuPresenterImpl(this);
    }

    //Use an intent to start the new bet activity
    @Override
    public void startNewBetActivity() {
        Intent newBetIntent = new Intent(this, NewBetActivity.class);
        startActivity(newBetIntent);
    }

    //Use an intent to start the bet feed activity
    @Override
    public void startBetFeedActivity() {
        Intent runningBetsIntent = new Intent(this, BetFeedActivity.class);
        startActivity(runningBetsIntent);
    }

    //Use an intent to start the login activity
    @Override
    public void startLoginActivity() {
        Intent logOutIntent = new Intent(this, LoginActivity.class);
        startActivity(logOutIntent);
    }

    //Use an intent to start the bet history activity
    @Override
    public void startHistoryActivity() {
        Intent historyIntent = new Intent(this, HistoryActivity.class);
        startActivity(historyIntent);
    }

    @Override
    public void exitApp() {

    }

    //Override the onBackPressed method to prevent the app to return to the last activity
    @Override
    public void onBackPressed(){
        Log.v(TAG, "Back button pressed");
    }
}
