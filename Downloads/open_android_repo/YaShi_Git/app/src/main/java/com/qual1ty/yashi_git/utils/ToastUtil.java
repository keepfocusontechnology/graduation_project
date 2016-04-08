package com.qual1ty.yashi_git.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tianci on 16/4/7.
 */
public class ToastUtil {

    public static void toastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
