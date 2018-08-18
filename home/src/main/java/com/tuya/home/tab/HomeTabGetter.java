package com.tuya.home.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.tuya.demomodule.R;
import com.tuya.smart.api.tab.ITabGetter;
import com.tuya.smart.tymodule_annotation.TYTabIndicator;
import com.tuya.tab.example.TabItemView;

@TYTabIndicator(name = "home", index = 1)
public class HomeTabGetter implements ITabGetter {
    @Override
    public View getIndicatorView(Context context) {
        TabItemView tabItemView = new TabItemView(context);
        tabItemView.setTitle("家庭");
        tabItemView.setIconImageResource(R.drawable.ty_home_icon);
        return tabItemView;
    }

    @Override
    public Fragment getFragment() {
        return new HomeFragment();
    }
}
