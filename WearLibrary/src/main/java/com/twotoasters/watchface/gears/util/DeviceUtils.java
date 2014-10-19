package com.twotoasters.watchface.gears.util;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;

import java.util.ArrayList;

public class DeviceUtils {

    private static final ArrayList<String> AMOLED_MODELS;
    static {
        AMOLED_MODELS = new ArrayList<String>();
        AMOLED_MODELS.add("Gear Live");
    }

    public static boolean hasAmoledScreen() {
        return AMOLED_MODELS.contains(Build.MODEL);
    }

    public static Pair<Float, Float> getScreenDimensDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        return new Pair(dpWidth, dpHeight);
    }
}
