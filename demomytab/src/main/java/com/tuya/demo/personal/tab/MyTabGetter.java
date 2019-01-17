package com.tuya.demo.personal.tab;

import android.content.Context;
import android.view.View;

import com.tuya.demo.common.TabItemView;
import com.tuya.demo.my.R;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.tab.BaseTabFragment;
import com.tuya.smart.api.tab.BaseTabWidget;
import com.tuya.smart.tymodule_annotation.TYTabIndicator;

/**
 * My模块的tab配置
 */
@TYTabIndicator(name = "my")
public class MyTabGetter extends BaseTabWidget {
    @Override
    public View getIndicatorView(Context context) {
        TabItemView tabItemView = new TabItemView(context);
        tabItemView.setTitle(context.getString(R.string.ex_tab_my_name));
        tabItemView.setIconImageResource(R.drawable.ty_persopn_icon);
        return tabItemView;
    }

    @Override
    public BaseTabFragment getTabFragment() {
        return new MyFrament();
    }

    /**
     * 我的tab被选中
     */
    @Override
    public void onEnter() {
        LogUtil.d("MyTabGetter", "onEnter");
    }

    /**
     * 离开我的tab
     */
    @Override
    public void onLeave() {
        LogUtil.d("MyTabGetter", "onLeave");
    }
}
