package com.tuya.smart.widget;

import android.support.v4.view.ViewPager;

import java.lang.reflect.Field;

public class MultiScrollManager implements IScrollManager {
    private boolean mReflectOK = false;
    private XScroller mXScrooler;
    private ViewPager mViewPager;

    public MultiScrollManager(ViewPager viewPager) {
        this.mXScrooler = new XScroller(viewPager.getContext());
        this.mViewPager = viewPager;

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager, this.mXScrooler);
            this.mReflectOK = true;
        } catch (Exception var3) {
            this.mReflectOK = false;
        }

    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (this.mReflectOK) {
            int current = this.mViewPager.getCurrentItem();
            if (Math.abs(current - item) > 1) {
                this.mXScrooler.setNoDuration(true);
                this.mViewPager.setCurrentItem(item, smoothScroll);
                this.mXScrooler.setNoDuration(false);
            } else {
                this.mXScrooler.setNoDuration(false);
                this.mViewPager.setCurrentItem(item, smoothScroll);
            }
        }

    }
}