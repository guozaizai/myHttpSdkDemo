package com.xm.httpapi.BaseUtils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 */

public class ToastUtil {


    public static void show(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, String str, int duration) {
        Toast.makeText(context, str, duration).show();
    }


}
