package com.bowazik.bob.ibet.utility;

/**
 * Created by bob on 08.05.17.
 */

public class Constants {

    public static final String SHARED_PREFERENCES_TAG = "com.bowazik.bob.ibet";
    public static final String SHARED_PREFERENCES_BET_LIST_TAG = "betList";

    public static final String IBET_SERVER_PHP_URL_BASE = "https://jayjaywebserver.000webhostapp.com/php/ibet/";
    public static final String IBET_SERVER_PHP_URL_BETS_BY_ID = IBET_SERVER_PHP_URL_BASE + "getbetsbyuserid.php";
    public static final String IBET_SERVER_PHP_URL_CHECK_USER = IBET_SERVER_PHP_URL_BASE + "checkuser.php";

    public static final String MESSAGE_SUCCESS_LOGIN = "Login was successful.";
    public static final String MESSAGE_ERROR_LOGIN = "An error occurred. Please try again later.";
    public static final String MESSAGE_ERROR_NEW_BET = "An error occurred creating the new bet. Pleasy try again later.";
    public static final String MESSAGE_SUCCESS_NEW_BET = "New bet was created successfully.";
    public static final String MESSAGE_ERROR_SET_BET_AS_LOST = "An error occurred seting the bet as lost.";

    public static final String IBET_TAG_ID = "id";
    public static final String IBET_TAG_CREATOR = "creator";
    public static final String IBET_TAG_CONTENDER_NAME = "user_name";
    public static final String IBET_TAG_TITLE = "title";
    public static final String IBET_TAG_DESCRIPTION = "description";
    public static final String IBET_TAG_VALUE = "value";
    public static final String IBET_TAG_DATE = "created";
    public static final String IBET_TAG_STATUS = "status";
    public static final String IBET_TAG_CONTENDER_ID = "contender";
}
