package com.pax.videotest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;

/**
 * Created by zhanzc on 2018/3/6.
 */

public class PlayerUtils {
    public static void setOrientation(Activity activity, int orientation) {
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            hideStatusBar(activity, true);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            hideStatusBar(activity, false);
        }
    }

    public static void hideStatusBar(Activity activity, boolean isHide) {
        if (isHide) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(lp);
            //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = activity.getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attr);
            //如果不注释下面这句话，状态栏将把界面挤下去
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
