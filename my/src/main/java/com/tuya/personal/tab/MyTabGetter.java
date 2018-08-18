package com.tuya.personal.tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.tuya.demomodule2.R;
import com.tuya.smart.api.tab.ITabGetter;
import com.tuya.smart.tymodule_annotation.TYTabIndicator;
import com.tuya.tab.example.TabItemView;

@TYTabIndicator(name = "my", index = 2)
public class MyTabGetter implements ITabGetter {
    @Override
    public View getIndicatorView(Context context) {
        TabItemView tabItemView = new TabItemView(context);
        tabItemView.setTitle("我的");
        tabItemView.setIconImageResource(R.drawable.ty_persopn_icon);
        return tabItemView;
    }

    @Override
    public Fragment getFragment() {
        return new MyFrament();
    }
}
