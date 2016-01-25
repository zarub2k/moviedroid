package com.cloudskol.moviedroid.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * @author tham
 *
 * Manager class for finding the liviness of the network connection
 */
public class NetworkManager {
    private static final String LOG_TAG = NetworkManager.class.getSimpleName();

    private static final NetworkManager INSTANCE = new NetworkManager();
    private NetworkManager() {
    }

    public synchronized static final NetworkManager getInstance() {
        return INSTANCE;
    }

    /**
     * Checks whether the network connection is available or not
     *
     * @param context
     * @return true if the device is connected with any of the network available
     */
    public boolean isNetworkConnected(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            Log.v(LOG_TAG, "Wifi network is available");
            return true;
        }

        final NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetworkInfo != null && mobileNetworkInfo.isConnected()) {
            Log.v(LOG_TAG, "Mobile network is available");
            return true;
        }

        return false;
    }
}
