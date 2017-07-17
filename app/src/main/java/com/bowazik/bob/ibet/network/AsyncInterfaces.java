package com.bowazik.bob.ibet.network;

import com.bowazik.bob.ibet.data.iBet;

import java.util.List;

/**
 * Created by bob on 13.07.17.
 */

public interface AsyncInterfaces {

    interface FetchBetListAsyncInterface{
        void onFetchBetListSuccess(List<iBet> betList);

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
}
