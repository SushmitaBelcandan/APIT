package com.apinnovations.apit.adapters;

import android.content.Context;
import android.net.ConnectivityManager;

import com.apinnovations.apit.retrofit_models.APIInterface;
public class Utils {

    private static final String TAG = "Utils";
    static APIInterface apiInterface;

    public static final boolean CheckInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;

        } else {
            return false;
        }
    }
}
