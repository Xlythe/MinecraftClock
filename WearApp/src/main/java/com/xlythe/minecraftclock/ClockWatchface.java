package com.xlythe.minecraftclock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.twotoasters.watchface.gears.widget.IWatchface;
import com.twotoasters.watchface.gears.widget.Watch;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ClockWatchface extends FrameLayout implements IWatchface {
    @InjectView(R.id.dial) ImageView dial;
    private Watch mWatch;
    private boolean mInflated;
    private boolean mActive;

    public ClockWatchface(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ClockWatchface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ClockWatchface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mWatch = new Watch(this);
        addView(inflate(context, R.layout.widget, null));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this, getRootView());
        mInflated = true;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mWatch.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mWatch.onDetachedFromWindow();
    }

    @Override
    public void onTimeChanged(Calendar time) {
        setImageResources();
        invalidate();
    }

    @Override
    public void onActiveStateChanged(boolean active) {
        this.mActive = active;
        setImageResources();
    }

    @Override
    public boolean handleSecondsInDimMode() {
        return false;
    }

    private void setImageResources() {
        if (mInflated) {
            dial.setImageBitmap(BitmapUtil.getCurrentTimeAsBitmap(getContext()));
        }
    }
}
