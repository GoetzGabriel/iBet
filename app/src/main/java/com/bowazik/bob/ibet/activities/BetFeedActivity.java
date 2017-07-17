package com.bowazik.bob.ibet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.adapter.BetFeedAdapter;
import com.bowazik.bob.ibet.adapter.ExpandableBetFeedAdapter;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.interfaces.BetFeedInterfaces;
import com.bowazik.bob.ibet.presenter.BetFeedPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetFeedActivity extends AppCompatActivity implements BetFeedInterfaces.BetFeedView{

    private static final String TAG = "BetFeedActivity";

    private ExpandableListView expandableListView;
    private ExpandableBetFeedAdapter expandableListAdapter;
    //private ListView activeBetListView, pendingBetListView;
    private TextView noBetsTextView/*, pendingBetsHint, activeBetsHint*/;

    //private BetFeedAdapter activeBetFeedAdapter, pendingBetFeedAdapter;

    private BetFeedPresenterImpl betFeedPresenterImpl;

    private List<iBet> pendingBetList = new ArrayList<>();
    private List<iBet> activeBetList = new ArrayList<>();

    private List<String> listDataHeader;
    private HashMap<String, List<iBet>> listsDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_feed);

        initData();
        initPresenter();
        initView();
        betFeedPresenterImpl.fetchBetFeed();
    }

    /**
     * Initiate the header data for the expandable listview adapter
     */
    private void initData() {
        listDataHeader = new ArrayList<>();
        listDataHeader.add(Constants.IBET_STATUS_PENDING);
        listDataHeader.add(Constants.IBET_STATUS_ACTIVE);

        listsDataChild = new HashMap<>();
    }

    /*
    private void setupBetFeedList() {
        //Setup the active bet list adapter
        if (activeBetFeedAdapter == null) {
            activeBetFeedAdapter = new BetFeedAdapter(this,
                    R.layout.bet_feed_item,
                    activeBetList);
            activeBetListView.setAdapter(activeBetFeedAdapter);
            Log.v(TAG, "neuer active adapter");
        } else {
            activeBetFeedAdapter.clear();
            activeBetFeedAdapter.addAll(activeBetList);
            activeBetFeedAdapter.notifyDataSetChanged();
            Log.v(TAG, "size im active adapter: "+activeBetList.size());
        }

        //Setup the pending bet list adapter
        if (pendingBetFeedAdapter == null) {
            pendingBetFeedAdapter = new BetFeedAdapter(this,
                    R.layout.bet_feed_item,
                    pendingBetList);
            pendingBetListView.setAdapter(pendingBetFeedAdapter);
            Log.v(TAG, "neuer pending adapter");
        } else {
            pendingBetFeedAdapter.clear();
            pendingBetFeedAdapter.addAll(pendingBetList);
            pendingBetFeedAdapter.notifyDataSetChanged();
            Log.v(TAG, "size im pending adapter: "+pendingBetList.size());
        }
    }*/

    /**
     * Refresh the expandable listview adapter for the betfeedview
     */
    //TODO: Create a new adapter if no adapter exists yet instead of everytime
    private void setupBetFeedList(){
        setupBetListData();

        expandableListAdapter = new ExpandableBetFeedAdapter(this, listDataHeader, listsDataChild);
        expandableListView.setAdapter(expandableListAdapter);
    }

    /**
     * Process the received bet lists for the expandable listview adapter of the bet feed view
     */
    private void setupBetListData() {
        listsDataChild.put(Constants.IBET_STATUS_PENDING, pendingBetList);
        listsDataChild.put(Constants.IBET_STATUS_ACTIVE, activeBetList);
    }

    private void initView() {
        //activeBetListView = (ListView) findViewById(R.id.list_bet_feed);
        //pendingBetListView =  (ListView) findViewById(R.id.list_bet_pending);
        noBetsTextView = (TextView) findViewById(R.id.no_bets_text);
        //pendingBetsHint = (TextView) findViewById(R.id.pending_bets_text);
        //activeBetsHint = (TextView) findViewById(R.id.active_bets_text);
/*
        activeBetListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                betFeedPresenterImpl.setBetAsLost(i);

                return true;
            }
        });*/
        expandableListView = (ExpandableListView) findViewById(R.id.list_bet_feed);
    }

    private void initPresenter() {
        betFeedPresenterImpl = new BetFeedPresenterImpl(this, this);
    }

    @Override
    public void setBetFeedList(List<iBet> pendingBetList, List<iBet> activeBetList) {
        this.activeBetList = activeBetList;
        this.pendingBetList = pendingBetList;
        if(activeBetList.size() > 0 || pendingBetList.size() > 0) {
            noBetsTextView.setVisibility(View.INVISIBLE);
            setupBetFeedList();
        }else{
            noBetsTextView.setVisibility(View.VISIBLE);
            //activeBetsHint.setVisibility(View.INVISIBLE);
            //pendingBetsHint.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void emptyBetFeedList() {
        this.activeBetList.clear();
        noBetsTextView.setVisibility(View.VISIBLE);
        setupBetFeedList();
    }

    @Override
    public void showSetBetAsLostError() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SET_BET_AS_LOST, Toast.LENGTH_LONG).show();
    }
}
