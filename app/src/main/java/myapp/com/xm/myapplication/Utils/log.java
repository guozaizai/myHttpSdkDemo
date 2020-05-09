package myapp.com.xm.myapplication.Utils;

import android.util.Log;

/**
 *
 */

public class log {
    public static final String TAG = "myApp";
    public static boolean SHOW = true;

    public static void e(String message) {
        if (SHOW) {
            Log.e(TAG, message);
        }
    }

    public static void d(String message) {
        if (SHOW) {
            Log.d(TAG, message);
        }
    }

    public static void v(String message) {
        if (SHOW) {
            Log.v(TAG, message);
        }
    }

    public static void i(String message) {
        if (SHOW) {
            Log.i(TAG, message);
        }
    }

    public static void w(String message) {
        if (SHOW) {
            Log.w(TAG, message);
        }
    }
}
