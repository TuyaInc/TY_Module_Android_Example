package com.tuya.demo.home.tab.panel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.smart.api.service.DefaultServiceProxy;
import com.tuya.smart.panelcaller.api.AbsPanelCallerService;
import com.tuya.smart.panelcaller.api.OnPanelOpenListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.GroupBean;


/**
 * 打开面板服务实现，此类用来演示如何重载涂鸦提供的默认服务,通过实现DefaultServiceProxy<T>接口，框架会将默认传递默认实现，
 * 然后使用者可以在实现中选择使用默认实现，还是使用自定义实现
 */

public class PanelCallerService extends AbsPanelCallerService implements DefaultServiceProxy<AbsPanelCallerService> {

    AbsPanelCallerService defaultService;

    /**
     * 设置涂鸦默认面板打开服务实现
     */
    @Override
    public void setDefaultService(AbsPanelCallerService absPanelCallerService) {
        defaultService = absPanelCallerService;
    }

    private boolean consume(Activity activity, String deviceId) {
        if ("xxxxxx".equals(deviceId)) {
            activity.startActivity(new Intent(activity, CustomDeviceControlActivity.class));
            return true;
        }
        return false;
    }

    /**
     * 跳转到设备
     *
     * @param activity
     * @param deviceBean
     */
    @Override
    public void goPanel(Activity activity, DeviceBean deviceBean) {

        //对特定类型的设备，使用自己的实现
        if (consume(activity, deviceBean.devId)) {
            return;
        }

        if (defaultService != null) {
            defaultService.goPanel(activity, deviceBean);
        }
    }

    /**
     * 跳转到设备,支持透传启动常量
     *
     * @param activity
     * @param deviceBean
     * @param launchExtraData 加入面板启动常量extraInfo中的信息
     */
    @Override
    public void goPanel(Activity activity, DeviceBean deviceBean, Bundle launchExtraData) {
        //对特定类型的设备，使用自己的实现
        if (consume(activity, deviceBean.devId)) {
            return;
        }
        if (defaultService != null) {
            defaultService.goPanel(activity, deviceBean, launchExtraData);
        }
    }

    /**
     * 跳转到设备,支持透传启动常量，面板运行额外上下文信息
     *
     * @param activity
     * @param deviceBean
     * @param launchExtraData  加入可被面板启动常量extraInfo中使用的信息
     * @param contextExtraData 加入可被面板上下文运行使用的信息
     */
    @Override
    public void goPanel(Activity activity, DeviceBean deviceBean,
                        Bundle launchExtraData, Bundle contextExtraData) {
        //对特定类型的设备，使用自己的实现
        if (consume(activity, deviceBean.devId)) {
            return;
        }
        if (defaultService != null) {
            defaultService.goPanel(activity, deviceBean, launchExtraData, contextExtraData);
        }
    }

    /**
     * 跳转到群组
     *
     * @param activity
     * @param groupBean
     * @param isAdmin   是否是管理员，如果非管理员，当群组没有设备时，仅弹toast提示；管理员会引导进入群组管理
     */
    @Override
    public void goPanel(Activity activity, GroupBean groupBean, boolean isAdmin) {
        if (defaultService != null) {
            defaultService.goPanel(activity, groupBean, isAdmin);
        }
    }

    /**
     * 跳转到群组,支持透传启动常量
     *
     * @param activity
     * @param groupBean
     * @param isAdmin         是否是管理员，如果非管理员，当群组没有设备时，仅弹toast提示；管理员会引导进入群组管理
     * @param launchExtraData 加入面板启动常量extraInfo中的信息
     */
    @Override
    public void goPanel(Activity activity, GroupBean groupBean, boolean isAdmin,
                        Bundle launchExtraData) {
        if (defaultService != null) {
            defaultService.goPanel(activity, groupBean, isAdmin, launchExtraData);
        }
    }

    /**
     * 跳转到群组,支持透传启动常量
     *
     * @param activity
     * @param groupBean
     * @param isAdmin          是否是管理员，如果非管理员，当群组没有设备时，仅弹toast提示；管理员会引导进入群组管理
     * @param launchExtraData  加入可被面板启动常量extraInfo中使用的信息
     * @param contextExtraData 加入可被面板上下文运行使用的信息
     */
    @Override
    public void goPanel(Activity activity, GroupBean groupBean, boolean isAdmin,
                        Bundle launchExtraData, Bundle contextExtraData) {
        if (defaultService != null) {
            defaultService.goPanel(activity, groupBean, isAdmin, launchExtraData, contextExtraData);
        }
    }

    /**
     * 跳转到设备,如果设备已不存在，会弹出toast提示
     *
     * @param activity
     * @param devId
     */
    @Override
    public void goPanelWithCheckAndTip(Activity activity, String devId) {
        //对特定类型的设备，使用自己的实现
        if (consume(activity, devId)) {
            return;
        }
        if (defaultService != null) {
            defaultService.goPanelWithCheckAndTip(activity, devId);
        }
    }

    /**
     * 跳转到群组,如果群组已不存在，会弹出toast提示
     *
     * @param activity
     * @param groupId
     * @param isAdmin  是否是管理员，如果非管理员，当群组没有设备时，仅弹toast提示；管理员会引导进入群组管理
     */
    @Override
    public void goPanelWithCheckAndTip(Activity activity, long groupId, boolean isAdmin) {
        if (defaultService != null) {
            defaultService.goPanelWithCheckAndTip(activity, groupId, isAdmin);
        }
    }


    @Override
    public void registerPanelOpenListener(OnPanelOpenListener listener) {
        if (defaultService != null) {
            defaultService.registerPanelOpenListener(listener);
        }
    }

    @Override
    public void unregisterPanelOpenListener(OnPanelOpenListener listener) {
        if (defaultService != null) {
            defaultService.unregisterPanelOpenListener(listener);
        }
    }

    /**
     * 取消跳转
     */
    @Override
    public void cancel() {
        if (defaultService != null) {
            defaultService.cancel();
        }
    }

    /**
     * 销毁页面时调用 销毁资源
     */
    @Override
    public void onDestroy() {
        if (defaultService != null) {
            defaultService.onDestroy();
        }
    }
}
