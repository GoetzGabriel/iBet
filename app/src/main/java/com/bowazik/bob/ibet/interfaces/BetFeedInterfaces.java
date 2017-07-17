package com.bowazik.bob.ibet.interfaces;

import com.bowazik.bob.ibet.data.iBet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob on 08.05.17.
 */

public interface BetFeedInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface BetFeedView{
        void setBetFeedList(List<iBet> pendingBetList, List<iBet> activeBetList);

        void emptyBetFeedList();

        void showSetBetAsLostError();
    }

    /**
     * Presenter operations offered to the view
     */
    interface BetFeedPresenter{
        void fetchBetFeed();

        void setBetAsLost(int i);
    }

    /**
     * Presenter operations offered to the model
     */
    interface BetFeedRequiredPresenterOps{
        void onBetListFetched(List<iBet> pendingBetList, List<iBet> activeBetList);
    }

    /**
     * Model operations offered to the presenter
     */
    interface BetFeedModelOps{
        void fetchBetList();
    }

}
