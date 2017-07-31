package com.bowazik.bob.ibet.network;

import com.bowazik.bob.ibet.data.iBet;

import java.util.List;

/**
 * Interfaces for the custom async task classes
 */

public interface AsyncInterfaces {

    interface FetchBetListAsyncInterface{
        void onFetchBetListSuccess(List<iBet> pendingBetList, List<iBet> activeBetList);

        void onFetchBetListError();
    }

    interface LoginAsyncInterface{
        void onLoginSuccess(int userId);

        void onLoginError();
    }

    interface SignupAsyncInterface{
        void onSignupSuccess();

        void onSignupNameTakenError();

        void onSignupError();
    }

    interface CreateBetAsyncInterface{
        void onCreateBetSuccess();

        void onCreateBetError();
    }

    interface BetReactionAsyncInterface{
        void onBetReactionSuccess();

        void onBetReactionError();
    }

    interface FetchBetHistoryAsyncInterface{
        void onFetchBetHistorySuccess(List<iBet> betHistory);

        void onFetchBetHistoryError(String error);
    }
}
