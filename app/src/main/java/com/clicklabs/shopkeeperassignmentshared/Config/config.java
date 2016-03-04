package com.clicklabs.shopkeeperassignmentshared.Config;

/**
 * Created by Krishika on 09-02-2016.
 */
public class config {
    static String GCM_PROJECT_NUMBER = "";
    static String BASE_URL = "";
    static String SERVER_URL="";
    static String FLURRY_KEY = "";
    static AppMode appMode = AppMode.TEST;

    static public String getBaseURL() {
        init(appMode);
        return BASE_URL;
    }
    public static String getServerUrl() {
        init(appMode);
        return SERVER_URL;
    }

    static public String getFlurryKey() {

        init(appMode);

        return FLURRY_KEY;
    }

    static public String getGCMProjectNumber() {

        init(appMode);

        return GCM_PROJECT_NUMBER;
    }


    /**
     * Initialize all the variable in this method
     *
     * @param appMode
     */
    public static void init(AppMode appMode) {

        switch (appMode) {
            case DEV:

                BASE_URL = "http://api.deets.clicklabs.in:1440/";
                FLURRY_KEY = "MNZJSQ9YV376F3NM39VZ";
                GCM_PROJECT_NUMBER = "563232976573";
                break;

            case TEST:

                BASE_URL = "http://54.173.40.155:3000";
                SERVER_URL="https://maps.googleapis.com/maps/api";
                FLURRY_KEY = "MNZJSQ9YV376F3NM39VZ";
                GCM_PROJECT_NUMBER = "563232976573";
                break;

            case LIVE:

                BASE_URL = "base URl for live mode";
                FLURRY_KEY = "flurry key for live mode";
                break;

        }


    }

    public enum AppMode {
        DEV, TEST, LIVE
    }

}
