package com.bowazik.bob.ibet.activities;

import android.content.Intent;
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

/**
 * The bet feed activity of the app.
 * The user can see his active bets in an expandable listview which is separated
 * in a list of pending and a list of accepted bets.
 * If no active bets exist an info text is shown.
 * If an list element is clicked the bet detail activity is started and the bet
 * information and buttons to react to the bet are shown.
 */

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

    /**
     * Refresh the expandable listview adapter for the betfeedview
     */
    //TODO: Create a new adapter if no adapter exists yet instead of everytime
    private void setupBetFeedList(){
        setupBetListData();

        expandableListAdapter = new ExpandableBetFeedAdapter(this, listDataHeader, listsDataChild);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupNumber, int itemNumber, long l) {
                showBetDetailActivity(groupNumber, itemNumber);
                return false;
            }
        });
    }

    /**
     * Switch to the betDetailActivity saving the chosen bet as intent extra
     */
    private void showBetDetailActivity(int groupNumber, int itemNuber) {
        Intent startBetDetailActivity = new Intent(this, BetDetailActivity.class);
        startBetDetailActivity.putExtra(Constants.IBET_INTENT_BET_TAG, groupNumber == 0 ? pendingBetList.get(itemNuber) : activeBetList.get(itemNuber));
        startActivity(startBetDetailActivity);
    }

    /**
     * Process the received bet lists for the expandable listview adapter of the bet feed view
     */
    private void setupBetListData() {
        listsDataChild.put(Constants.IBET_STATUS_PENDING, pendingBetList);
        listsDataChild.put(Constants.IBET_STATUS_ACTIVE, activeBetList);
    }

    private void initView() {
        noBetsTextView = (TextView) findViewById(R.id.no_bets_text);
        expandableListView = (ExpandableListView) findViewById(R.id.list_bet_feed);
    }

    //Initiate the bet feed presenter
    private void initPresenter() {
        betFeedPresenterImpl = new BetFeedPresenterImpl(this, this);
    }

    /**
     * Check whether the received bet lists are empty. If they are show two info texts.
     * If at least one list is not empty the bet feed list is updated.
     * @param pendingBetList A list of iBets containing the pending bets received from the web server
     * @param activeBetList A list of iBets containing the active bets received from the web server
     */
    @Override
    public void setBetFeedList(List<iBet> pendingBetList, List<iBet> activeBetList) {
        this.activeBetList = activeBetList;
        this.pendingBetList = pendingBetList;
        if(activeBetList.size() > 0 || pendingBetList.size() > 0) {
            noBetsTextView.setVisibility(View.INVISIBLE);
            setupBetFeedList();
        }else{
            noBetsTextView.setVisibility(View.VISIBLE);
        }
    }
}
