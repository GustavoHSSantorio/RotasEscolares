package br.com.rotasescolar.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gustavosantorio on 26/07/17.
 */

public class InternetUtils {

    public static Boolean checkConnection(Context context) {

        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr == null) {
            return false;

        } else {
            NetworkInfo[] netWorkInfo = conMgr.getAllNetworkInfo();

            for (NetworkInfo aNetWorkInfo : netWorkInfo) {
                if (aNetWorkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
