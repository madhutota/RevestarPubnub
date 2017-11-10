package com.sparity.revestarpubnub.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import com.sparity.revestarpubnub.app.activity.BaseActivity;

/**
 * Created by madhu on 10-Nov-17.
 */

public class Preferences {


    private static final String APP_CONFIG_PREF = "APP_CONFIG_PREF";
    public static final String DEVICE_TYPE = "android";
    public static final String APP_FIRST_DOWNLOAD = "APP_FIRST_DOWNLOAD";
    public static final String VERSION = android.os.Build.VERSION.RELEASE + " " + Utility.getDeviceName();

    /*SET THE STRING SHARED PREF DATA*/
    public static void setPrefStringData(Context context, String key, String value) {
        SharedPreferences appInstallInfoSharedPref = context.
                getSharedPreferences(APP_CONFIG_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }


    /*GET THE STRING SHARED PREF DATA*/
    public static String getPrefStringData(Context context, String key) {
        try {
            SharedPreferences pref = context.getSharedPreferences(APP_CONFIG_PREF, Context.MODE_PRIVATE);
            return pref.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*SET THE BOOLEAN  SHARED PREF DATA*/
    public static void setPrefBooleanData(Context context, String key, boolean value) {
        SharedPreferences appInstallInfoSharedPref = context.
                getSharedPreferences(APP_CONFIG_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
        appInstallInfoEditor.putBoolean(key, value);
        appInstallInfoEditor.apply();
    }


    /*GET THE BOOLEAN SHARED PREF DATA*/
    public static Boolean getPrefBooleanData(Context context, String key) {
        try {
            SharedPreferences pref = context.getSharedPreferences(APP_CONFIG_PREF, Context.MODE_PRIVATE);
            return pref.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*GET THE DEVICE UNIQUE ID*/
    public static String getDeviceId(BaseActivity context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /*GET THE DEVICE MAC ADDRESS*/
    public static String getMacAddress(BaseActivity parent) {
        WifiManager wifiManager = (WifiManager) parent.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }




}
