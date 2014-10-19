package com.twotoasters.watchface.gears;

import android.app.Application;

public class GearsWatchfaceApp extends Application {

    private static GearsWatchfaceApp app;

    public static GearsWatchfaceApp getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
