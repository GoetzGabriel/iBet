package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.MenuInterfaces;

/**
 * Created by bob on 08.05.17.
 */

public class MenuPresenterImpl implements MenuInterfaces.MenuPresenter{

    private final MenuInterfaces.MenuView menuView;

    public MenuPresenterImpl(MenuInterfaces.MenuView menuView){
        this.menuView = menuView;
    }

    public void onButtonNewBetClicked(){
        menuView.startNewBetActivity();
    }

    public void onButtonBetFeedClicked() {
        menuView.startBetFeedActivity();
    }

    public void onButtonBetExitClicked() {
        menuView.startLoginActivity();
    }
}
