package com.tuya.smart.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class XScroller extends Scroller {
    private boolean noDuration = false;
    private int mScrollDuration = 1000;

    XScroller(Context context) {
        super(context);
    }

    public XScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public XScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    void setNoDuration(boolean noDuration) {
        this.noDuration = noDuration;
    }

    public void setScrollDuration(int duration) {
        this.mScrollDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (this.noDuration) {
            super.startScroll(startX, startY, dx, dy, 0);
        } else {
            super.startScroll(startX, startY, dx, dy, this.mScrollDuration);
        }

    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        if (this.noDuration) {
            super.startScroll(startX, startY, dx, dy, 0);
        } else {
            super.startScroll(startX, startY, dx, dy, this.mScrollDuration);
        }

    }
}