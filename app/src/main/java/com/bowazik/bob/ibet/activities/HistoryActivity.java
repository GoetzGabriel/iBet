package com.bowazik.bob.ibet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.adapter.BetHistoryAdapter;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.HistoryInterfaces;
import com.bowazik.bob.ibet.presenter.HistoryPresenterImpl;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;
import com.bowazik.bob.ibet.views.ExpandedListView;

import java.util.List;

/**
 * The bet history activity of the app.
 * The user can see his won and lost bets in an expandable listview which is separated
 * in a list of pending and a list of accepted bets.
 * If the history is empty an info text is shown.
 * If an list element is clicked the bet detail activity is started and the bet
 * information and buttons to react to the bet are shown.
 */

public class HistoryActivity extends AppCompatActivity implements HistoryInterfaces.HistoryView {

    private static final String TAG = "HistoryActivity";
    private HistoryInterfaces.HistoryPresenter historyPresenter;

    private TextView noBetsTextView, betBalanceTextView;
    private ExpandedListView betHistoryListView;
    private BetHistoryAdapter betHistoryAdapter;
    private List<iBet> betHistoryList;
    private IbetSharedPrefs ibetSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ibetSharedPrefs = new IbetSharedPrefs(this);

        initPresenter();
        initView();
        historyPresenter.requestBetHistory();
    }

    private void initView() {
        noBetsTextView = (TextView) findViewById(R.id.no_history_text);
        betBalanceTextView = (TextView) findViewById(R.id.history_bet_balance);
        betHistoryListView = (ExpandedListView) findViewById(R.id.list_bet_history);
    }

    //Initiate the history presenter
    private void initPresenter() {
        historyPresenter = new HistoryPresenterImpl(this, this);
    }

    /**
     * Save the given history list from the web server if it is not empty and hide
     * the info text.
     * @param betHistoryList List containing the bet history of the user in iBet format
     */
    @Override
    public void setHistoryList(List<iBet> betHistoryList) {
        //Hide the no bets in history textView if betHistoryList is no empty
        if(betHistoryList.size() > 0){
            noBetsTextView.setVisibility(View.GONE);
        }
        this.betHistoryList = betHistoryList;
        setAdapter();
    }

    //Create a expandedlistview adapter for the history list if it does not exist yet
    //Update it otherwise
    private void setAdapter() {
        //Create a new adapter for the bet history expandedListView if is does not exist yet
        if(betHistoryAdapter == null){
            betHistoryAdapter = new BetHistoryAdapter(this, 0, betHistoryList, ibetSharedPrefs.getUserId());
            betHistoryListView.setAdapter(betHistoryAdapter);
        }else{
            betHistoryAdapter.addAll(betHistoryList);
            betHistoryAdapter.notifyDataSetChanged();
        }
    }

    //Show an error alert if the history fetch preocess failed
    @Override
    public void showHistoryFetchErrorMessage(String error) {

    }

    /**
     * Set the bet balance value according to the received bet history
     * @param betBalance Value (bets won - bets lost)
     */
    @Override
    public void setBetBalance(int betBalance) {
        String balanceText = Constants.BET_BALANCE_TEXT + betBalance;
        betBalanceTextView.setText(balanceText);
        betBalanceTextView.setBackgroundResource((betBalance < Constants.BET_BALANCE_MIN_SUCCESS_VALUE) ? R.drawable.border_lost : R.drawable.border_won);
    }
}
