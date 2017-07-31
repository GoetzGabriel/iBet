package com.bowazik.bob.ibet.interfaces;

import com.bowazik.bob.ibet.data.iBet;

import java.util.ArrayList;
import java.util.List;

/**
 * The interfaces for the bet feed activity/view, presenter and model
 */

public interface BetFeedInterfaces {

    /**
     * View operations offered to the presenter
     */
    interface BetFeedView{
        void setBetFeedList(List<iBet> pendingBetList, List<iBet> activeBetList);
    }

    /**
     * Presenter operations offered to the view
     */
    interface BetFeedPresenter{
        void fetchBetFeed();
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
