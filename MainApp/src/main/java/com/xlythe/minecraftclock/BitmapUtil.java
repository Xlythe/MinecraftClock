package com.xlythe.minecraftclock;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BitmapUtil {
    public static Bitmap getCurrentTimeAsBitmap(Context context) {
        final int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        final int minute = Calendar.getInstance().get(Calendar.MINUTE);
        final Bitmap defaultDial = BitmapFactory.decodeResource(context.getResources(), R.drawable.dial);
        final Matrix matrix = new Matrix();
        float degrees = (hour - 12) * 15;
        degrees += minute / 4;

        matrix.postRotate(degrees);

        Bitmap b = Bitmap.createBitmap(defaultDial, 0, 0, defaultDial.getWidth(), defaultDial.getHeight(), matrix, false);

        int x = (b.getWidth() - defaultDial.getWidth()) / 2;
        int y = (b.getHeight() - defaultDial.getHeight()) / 2;

        Bitmap croppedBmp = b;
        if(x > 0 && y > 0) croppedBmp = Bitmap.createBitmap(b, x, y, defaultDial.getWidth(), defaultDial.getHeight());
        else if(x < 0 || y < 0) croppedBmp = Bitmap.createScaledBitmap(b, defaultDial.getWidth(), defaultDial.getHeight(), false);

        return croppedBmp;
    }
}
