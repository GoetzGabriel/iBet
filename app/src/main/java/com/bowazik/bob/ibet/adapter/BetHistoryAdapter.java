package com.bowazik.bob.ibet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
 * The custom array adapter for the bet history list.
 * It gets the list of bets and displays them in a listview.
 */

public class BetHistoryAdapter extends ArrayAdapter<iBet> {

    private final String TAG = "BetFeedAdapter";

    private List<iBet> bets;
    private Context context;
    private int userId;

    public BetHistoryAdapter(Context context, int eventId, List<iBet> bets, int userId){
        super(context, eventId, bets);
        this.bets = bets;
        this.context = context;
        this.userId = userId;
    }

    /**
     * Overriden getView method from the ArrayAdapter.
     * Declares the look of each event feed element.
     */
    @NonNull
    public View getView(final int position, final View convertView, ViewGroup parent){

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.bet_feed_item, null);
        }

        iBet i = bets.get(position);


        //If API lvl is > 15 set the border color to won/lost
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (isBetWon(i)) {
                v.setBackgroundResource(R.drawable.border_won);
            } else {
                v.setBackgroundResource(R.drawable.border_lost);
            }
        }else {
            //If API lvl is < 16 set background color of view item to won/lost
            if (isBetWon(i)) {
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.bet_history_element_background_won));
            } else {
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.bet_history_element_background_lost));
            }
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

    /**
     * Check whether a given iBet object is won or lost considering the given userId
     * A bet is lost if userId equals the creator id and the bet status is lost or
     * if the user id equals the contender id and the bet status is won
     * @param bet The bet object to be checked for win or defeat
     * @return Boolean value indicating if the bet is won or lost
     */
    private boolean isBetWon(iBet bet){
        Log.v(TAG, "creator: "+bet.getCreator());
        Log.v(TAG, "userId: "+userId);
        Log.v(TAG, "status: "+bet.getStatus());
        return bet.getCreator() == userId && bet.getStatus().equals(Constants.IBET_STATUS_WON)
                || bet.getCreator() != userId && bet.getStatus().equals(Constants.IBET_STATUS_LOST);
    }
}
