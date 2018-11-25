package com.hacknife.iplayer.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hacknife.iplayer.AbsPlayer;
import com.hacknife.iplayer.state.ScreenType;

import java.util.Formatter;
import java.util.Locale;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : IPlayer
 */
public class PlayerUtils {
    public static final String PRE_NAME = "PROGRESS";

    public static String stringForTime(long timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        long totalSeconds = timeMs / 1000;
        int seconds = (int) (totalSeconds % 60);
        int minutes = (int) ((totalSeconds / 60) % 60);
        int hours = (int) (totalSeconds / 3600);
        StringBuilder stringBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(stringBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }


    public static Activity scanForActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }

    public static AppCompatActivity getAppCompActivity(Context context) {
        if (context == null) return null;
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return getAppCompActivity(((ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void setRequestedOrientation(Context context, int orientation) {
        if (PlayerUtils.getAppCompActivity(context) != null) {
            PlayerUtils.getAppCompActivity(context).setRequestedOrientation(
                    orientation);
        } else {
            PlayerUtils.scanForActivity(context).setRequestedOrientation(
                    orientation);
        }
    }

    public static Window getWindow(Context context) {
        if (PlayerUtils.getAppCompActivity(context) != null) {
            return PlayerUtils.getAppCompActivity(context).getWindow();
        } else {
            return PlayerUtils.scanForActivity(context).getWindow();
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static ScreenType integer2ScreenType(int type) {
        if (type == 1) {
            return ScreenType.SCREEN_TYPE_ADAPTER;
        } else if (type == 2) {
            return ScreenType.SCREEN_TYPE_FILL_PARENT;
        } else if (type == 3) {
            return ScreenType.SCREEN_TYPE_FILL_CROP;
        } else {
            return ScreenType.SCREEN_TYPE_ORIGINAL;
        }
    }


    public static AbsPlayer findPlayer(View view) {
        if (view instanceof AbsPlayer) {
            return (AbsPlayer) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                AbsPlayer player = findPlayer(group.getChildAt(i));
                if (player != null)
                    return player;
            }
            return null;
        } else {
            return null;
        }
    }
}
