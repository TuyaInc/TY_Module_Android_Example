package com.tuya.demo.app;

import android.content.Context;
import android.os.Bundle;

import com.tuya.smart.api.MicroContext;
import com.tuya.smart.commonbiz.api.login.AbsCustomLoginModuleService;
import com.tuya.smart.commonbiz.api.login.AbsLoginEventService;
import com.tuya.smart.tymodule_annotation.TYService;


/**
 * Demo运行的临时调试代码，登录事件处理服务，在集成的时候不需要引入
 */
@TYService("com.tuya.smart.commonbiz.api.login.AbsLoginEventService")
public class LoginEventServiceImpl extends AbsLoginEventService {
    @Override
    public void onLogin() {

    }

    @Override
    public void onLogout(Context context) {

    }

    @Override
    public void goLogin(Context context, Bundle bundle) {
        //跳转到自定义登录模块
        AbsCustomLoginModuleService absLoginEventService = MicroContext.findServiceByInterface(
                AbsCustomLoginModuleService.class.getName());
        if (absLoginEventService != null) {
            absLoginEventService.goLogin(context, null);
        } else {
            //跳转到涂鸦登录页
        }
    }

    @Override
    public void reLogin(Context context) {
        reLogin(context, true);
    }

    @Override
    public void reLogin(Context context, boolean b) {
        goLogin(context, null);
    }

    @Override
    public void registerLoginEventCallbacks(LoginEventCallback loginEventCallback) {

    }

    @Override
    public void unregisterLoginEventCallbacks(LoginEventCallback loginEventCallback) {

    }
}
