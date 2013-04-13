package com.xlythe.minecraftclock;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.RemoteViews;

public class ClockWidget extends AppWidgetProvider {
    public static String CLOCK_WIDGET_UPDATE = "com.xlythe.minecraftclock.CLOCK_WIDGET_UPDATE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetID : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetID);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(CLOCK_WIDGET_UPDATE.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, ClockWidget.class));
            for(int appWidgetID : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetID);
            }
        }
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        remoteViews.setBitmap(R.id.dial, "setImageBitmap", getCurrentTimeAsBitmap(context));

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private PendingIntent createClockTickIntent(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent(CLOCK_WIDGET_UPDATE), PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 1000, 1000, createClockTickIntent(context));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(createClockTickIntent(context));
    }

    private static Bitmap getCurrentTimeAsBitmap(Context context) {
        final int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        final Bitmap defaultDial = BitmapFactory.decodeResource(context.getResources(), R.drawable.dial);
        final Matrix matrix = new Matrix();
        float degrees = (hour - 12) * 15;

        matrix.postRotate(degrees);

        Bitmap b = Bitmap.createBitmap(defaultDial, 0, 0, defaultDial.getWidth(), defaultDial.getHeight(), matrix, false);

        int x = (b.getWidth() - defaultDial.getWidth()) / 2;
        int y = (b.getHeight() - defaultDial.getHeight()) / 2;
        Bitmap croppedBmp = Bitmap.createBitmap(b, x, y, defaultDial.getWidth(), defaultDial.getHeight());

        return croppedBmp;
    }
}
