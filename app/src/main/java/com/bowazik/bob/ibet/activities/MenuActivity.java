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
 * Created by bob on 08.05.17.
 */

public class MenuActivity extends AppCompatActivity implements MenuInterfaces.MenuView {

    private static final String TAG = "Main Menu";
    private MenuPresenterImpl menuPresenterImpl;
    private Button buttonNewBet, buttonBetFeed, buttonExit;

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
    }

    private void initPresenter() {
        menuPresenterImpl = new MenuPresenterImpl(this);
    }

    @Override
    public void startNewBetActivity() {
        Intent newBetIntent = new Intent(this, NewBetActivity.class);
        startActivity(newBetIntent);
    }

    @Override
    public void startBetFeedActivity() {
        Intent runningBetsIntent = new Intent(this, BetFeedActivity.class);
        startActivity(runningBetsIntent);
    }

    @Override
    public void startLoginActivity() {
        Intent logOutIntent = new Intent(this, LoginActivity.class);
        startActivity(logOutIntent);
    }

    @Override
    public void exitApp() {

    }

    @Override
    public void onBackPressed(){
        Log.v(TAG, "Back button pressed");
    }
}
