package com.tuya.smart.demoapp;

import android.content.Context;
import android.graphics.Color;

import com.tuya.smart.router.ActionParams;
import com.tuya.smart.router.EventParams;
import com.tuya.smart.router.Provider;
import com.tuya.smart.smartapi.navbar.bean.ItemBean;

/**
 * Created by letian on 2018/6/19.
 * ***Provider 主要是用来和Tuya APP沟通的桥梁
 */

public class DemoProvider extends Provider {

    public static final String EVENT_INIT_APPLICATION = "event_application_on_create_in_main_thread";
    public static final String EVENT_LOGOUT = "event_logout";
    public static final String EVENT_LOGIN = "event_login";
    public static final String EVENT_APPLICATION_INIT_IN_THREAD = "event_application_on_create_init_in_thread";

    @Override
    public String getKey() {
        return "DemoProvider";
    }

    @Override
    public Object getInstance(ActionParams actionParams) {
        switch (actionParams.getActionName()) {
            case "tabFragment": //Tuya tab页面加载入口。第三方APP只要实现这个接口就好了
                return DemoFragment.newInstance();
            case "tabConfig":
                int mFuncBarTextNormalColor = Color.parseColor("#8A8E91");
                int mFuncBarTextSelectColor = Color.parseColor("#ff5800");//Color.RED;
                return addItemBean(R.string.demo_nav_demo, R.drawable.demo_ty_family_nav_home,
                        0, 0
                        , mFuncBarTextNormalColor, mFuncBarTextSelectColor, mFuncBarTextSelectColor, mFuncBarTextNormalColor,
                        3);
        }
        return super.getInstance(actionParams);
    }

    //支持SVG PNG、
    private ItemBean addItemBean(
            int nameResId,
            int svgIconResId,
            int selectedImageResId,
            int imageResId,
            int nameColor,
            int nameSelectedColor,
            int svgIconSelectedColor,
            int svgIconColor, int index) {
        ItemBean itemBean = new ItemBean();
        //图片resId
        itemBean.setImageResId(imageResId);
        itemBean.setNameResId(nameResId);
        itemBean.setNameColor(nameColor);
        itemBean.setNameSelectedColor(nameSelectedColor);
        itemBean.setSvgIconColor(svgIconColor);
        itemBean.setSvgIconSelectedColor(svgIconSelectedColor);
        //tab
        itemBean.setSvgIconResId(svgIconResId);
        //tab选中状态的图片resId
        itemBean.setSelectedImageResId(selectedImageResId);
        //tab里面排序
        itemBean.setIndex(index);
        return itemBean;
    }

    @Override
    public void invokeEvent(EventParams eventParams) {
        super.invokeEvent(eventParams);
        switch (eventParams.getEventName()) {
            //Application初始化
            case EVENT_INIT_APPLICATION:
                initApplicationOnCreate(eventParams);
                break;
            //Application初始化
            case EVENT_APPLICATION_INIT_IN_THREAD:
                initApplicationOnCreateInAsyncTask(eventParams);
                break;
            //登出事件
            case EVENT_LOGOUT:
                logout();
                break;
            //登陆事件
            case EVENT_LOGIN:
                login();
                break;
        }
    }

    //进程初始化 异步队列
    private void initApplicationOnCreateInAsyncTask(EventParams eventParams) {
        Context eventContext = eventParams.getEventContext();
        String progressName = (String) eventParams.getEventData("progressName");
        String mainProgressName = (String) eventParams.getEventData("mainProgressName");

    }

    //进程初始化 主线程
    private void initApplicationOnCreate(EventParams eventParams) {
        Context eventContext = eventParams.getEventContext();
        String progressName = (String) eventParams.getEventData("progressName");
        String mainProgressName = (String) eventParams.getEventData("mainProgressName");
    }

    //登陆操作
    private void login() {

    }

    //登出操作
    private void logout() {

    }
}
