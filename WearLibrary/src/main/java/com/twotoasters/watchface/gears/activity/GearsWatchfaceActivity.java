package com.twotoasters.watchface.gears.activity;

import android.app.Activity;
import android.os.Bundle;

import com.twotoasters.watchface.gears.widget.IWatchface;

public abstract class GearsWatchfaceActivity extends Activity {

    private IWatchface watchface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        watchface = getWatchface();
        if (watchface == null) {
            throw new AssertionError("Layout must contain an " + IWatchface.class.getSimpleName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        watchface.onActiveStateChanged(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        watchface.onActiveStateChanged(false);
    }

    protected abstract int getLayoutResId();
    protected abstract IWatchface getWatchface();
}
