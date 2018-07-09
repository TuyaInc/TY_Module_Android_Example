package com.tuya.smart.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    private boolean mScrollEnabled;
    private IScrollManager mIScrollManager = null;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.mScrollEnabled = scrollEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return this.mScrollEnabled && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return this.mScrollEnabled && super.onInterceptTouchEvent(arg0);
    }

    public void setIScrollManager(IScrollManager scrollManager) {
        this.mIScrollManager = scrollManager;
    }
}
