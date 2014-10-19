package com.twotoasters.watchface.gears.widget;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import java.util.Calendar;

public interface IWatchface {

    // Implemented by the Watchface
    public void onActiveStateChanged(boolean active);
    public void onTimeChanged(Calendar time);
    public boolean handleSecondsInDimMode(); // returning true may have adverse effect on battery life

    // Watchface should delegate these calls to the watch
    public void onAttachedToWindow();
    public void onDetachedFromWindow();

    // Watchfaces should extend view and get these for free
    public Context getContext();
    public Handler getHandler();
    public Resources getResources();
}
