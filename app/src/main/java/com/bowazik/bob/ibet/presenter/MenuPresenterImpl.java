package com.bowazik.bob.ibet.presenter;

import com.bowazik.bob.ibet.interfaces.MenuInterfaces;

/**
 * The presenter for the menu activity.
 * It reacts to view operations.
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

    @Override
    public void onButtonBetHistoryClicked() {
        menuView.startHistoryActivity();
    }
}
