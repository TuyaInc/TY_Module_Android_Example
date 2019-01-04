package com.tuya.demo.home.tab;

import android.content.Context;
import android.view.View;

import com.tuya.demo.common.TabItemView;
import com.tuya.demo.home.R;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.tab.BaseTabFragment;
import com.tuya.smart.api.tab.BaseTabWidget;
import com.tuya.smart.tymodule_annotation.TYTabIndicator;

/**
 * Home模块的tab配置
 */
@TYTabIndicator(name = "home", index = 1)
public class HomeTabGetter extends BaseTabWidget {

    private HomeFragment homeFragment;

    @Override
    public View getIndicatorView(Context context) {
        TabItemView tabItemView = new TabItemView(context);
        tabItemView.setTitle(context.getString(R.string.ex_tab_home_name));
        tabItemView.setIconImageResource(R.drawable.ex_home_icon);
        return tabItemView;
    }

    @Override
    public BaseTabFragment getTabFragment() {
        homeFragment = new HomeFragment();
        return homeFragment;
    }

    /**
     * HomeFragment
     */
    @Override
    public void onEnter() {
        LogUtil.d("HomeTabGetter", "onEnter");
        if (homeFragment != null) {
            homeFragment.onEnter();
        }
    }

    @Override
    public void onLeave() {
        LogUtil.d("HomeTabGetter", "onLeave");
        if (homeFragment != null) {
            homeFragment.onLeave();
        }
    }
}
