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
import com.bowazik.bob.ibet.views.ExpandedListView;

import java.util.List;

/**
 * Created by bob on 19.07.17.
 */

public class HistoryActivity extends AppCompatActivity implements HistoryInterfaces.HistoryView {

    private static final String TAG = "HistoryActivity";
    private HistoryInterfaces.HistoryPresenter historyPresenter;

    private TextView noBetsTextView;
    private ExpandedListView betHistoryListView;
    private BetHistoryAdapter betHistoryAdapter;
    private List<iBet> betHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initPresenter();
        initView();
        historyPresenter.requestBetHistory();
    }

    private void initView() {
        noBetsTextView = (TextView) findViewById(R.id.no_history_text);
        betHistoryListView = (ExpandedListView) findViewById(R.id.list_bet_history);
    }

    private void initPresenter() {
        historyPresenter = new HistoryPresenterImpl(this, this);
    }

    @Override
    public void setHistoryList(List<iBet> betHistoryList) {
        //Hide the no bets in history textView if betHistoryList is no empty
        if(betHistoryList.size() > 0){
            noBetsTextView.setVisibility(View.GONE);
        }
        this.betHistoryList = betHistoryList;
        setAdapter();
    }

    private void setAdapter() {
        //Create a new adapter for the bet history expandedListView if is does not exist yet
        if(betHistoryAdapter == null){
            betHistoryAdapter = new BetHistoryAdapter(this, 0, betHistoryList);
            betHistoryListView.setAdapter(betHistoryAdapter);
        }else{
            betHistoryAdapter.addAll(betHistoryList);
            betHistoryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showHistoryFetchErrorMessage(String error) {

    }
}
