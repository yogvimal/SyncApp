package com.example.yogi.syncapp;

/**
 * Created by YOGI on 10-11-2017.
 */

public class DbContract {

    public static final int SYNC_STATUS_OK = 0;
    public static final int SYNC_STATUS_FAILED = 1;

    //public static final String SERVER_URL = "http://10.0.2.2/android_php_mysql_test/login.php";

    public static final String SERVER_URL = "https://vimalapps.000webhostapp.com/sync_app/login.php";

    public static final String UI_UPDATE_BROADCAST = "com.example.syncapp.uiupdatebroadcast";

    public static final String DATABASE_NAME = "contactdb";
    public static final String TABLE_NAME  = "contactinfo";
    public static final String NAME = "name";
    public static final String SYNC_STATUS = "syncstatus";

}
