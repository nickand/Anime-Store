package test.android.testmerlin.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
