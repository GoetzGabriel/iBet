package com.bowazik.bob.ibet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.adapter.BetFeedAdapter;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.presenter.BetFeedPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.ArrayList;
import java.util.List;

public class BetFeedActivity extends AppCompatActivity implements BetFeedInterfaces.BetFeedView{

    private static final String TAG = "BetFeedActivity";

    private ListView betFeedListView;
    private TextView noBetsTextView;

    private BetFeedAdapter betFeedAdapter;

    private BetFeedPresenterImpl betFeedPresenterImpl;

    private List<iBet> betFeedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_feed);

        initPresenter();
        initView();
        betFeedPresenterImpl.fetchBetFeed();
    }

    private void setupBetFeedList() {
        //TODO else fix
        if (betFeedAdapter == null) {
            betFeedAdapter = new BetFeedAdapter(this,
                    R.layout.bet_feed_item,
                    betFeedList);
            betFeedListView.setAdapter(betFeedAdapter);
            Log.v(TAG, "neuer adapter");
        } else {
            betFeedAdapter.clear();
            betFeedAdapter.addAll(betFeedList);
            betFeedAdapter.notifyDataSetChanged();
            Log.v(TAG, "size im adapter: "+betFeedList.size());
        }
    }

    private void initView() {
        betFeedListView = (ListView) findViewById(R.id.list_bet_feed);
        noBetsTextView = (TextView) findViewById(R.id.no_bets_text);

        betFeedListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                betFeedPresenterImpl.setBetAsLost(i);

                return true;
            }
        });
    }

    private void initPresenter() {
        betFeedPresenterImpl = new BetFeedPresenterImpl(this, this);
    }

    @Override
    public void setBetFeedList(List<iBet> betFeedList) {
        this.betFeedList = betFeedList;
        if(betFeedList.size() > 0){
            noBetsTextView.setVisibility(View.INVISIBLE);
            setupBetFeedList();
        }else{
            noBetsTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void emptyBetFeedList() {
        this.betFeedList.clear();
        noBetsTextView.setVisibility(View.VISIBLE);
        setupBetFeedList();
    }

    @Override
    public void showSetBetAsLostError() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SET_BET_AS_LOST, Toast.LENGTH_LONG).show();
    }
}
