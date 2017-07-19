package com.bowazik.bob.ibet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.data.iBet;
import com.bowazik.bob.ibet.utility.Constants;

import java.util.List;

/**
 * Created by bob on 27.06.17.
 */

public class BetHistoryAdapter extends ArrayAdapter<iBet> {

    private final String TAG = "BetFeedAdapter";

    private List<iBet> bets;
    private Context context;

    public BetHistoryAdapter(Context context, int eventId, List<iBet> bets){
        super(context, eventId, bets);
        this.bets = bets;
        this.context = context;
    }

    /**
     * Overriden getView method from the ArrayAdapter.
     * Declares the look of each event feed element.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(final int position, final View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bet_feed_item, null);
        }

        iBet i = bets.get(position);

        if(i.getStatus().equals(Constants.IBET_STATUS_WON)){
            v.setBackgroundColor(ContextCompat.getColor(context,R.color.bet_history_element_background_won));
        }else{
            v.setBackgroundColor(ContextCompat.getColor(context,R.color.bet_history_element_background_lost));
        }

        if (i != null) {

            // Gather references for the textviews

            TextView titleView = (TextView) v.findViewById(R.id.bet_item_title);
            TextView contenderView = (TextView) v.findViewById(R.id.bet_item_contender);
            TextView descView = (TextView) v.findViewById(R.id.bet_item_description);
            TextView stakeView = (TextView) v.findViewById(R.id.bet_item_stake);

            // Check to see if each individual textview is null
            // If not assign the according data
            if (titleView != null){
                titleView.setText(i.getTitle());
            }
            if (contenderView != null){
                contenderView.setText(String.valueOf(i.getContenderName()));
            }
            if (descView != null){
                descView.setText(i.getDescription());
            }
            if (stakeView != null){
                stakeView.setText(String.valueOf(i.getValue()));
            }
        }

        return v;

    }
}
