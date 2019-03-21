package com.tuya.demo.login.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.demo.login.LoginActivity;
import com.tuya.smart.commonbiz.api.login.AbsCustomLoginModuleService;
import com.tuya.smart.tymodule_annotation.TYService;


/**
 * 自定义登录模块必须实现AbsCustomLoginModuleService服务并追加注解
 * '@TYService("com.tuya.smart.commonbiz.api.login.AbsCustomLoginModuleService")'
 */
@TYService("com.tuya.smart.commonbiz.api.login.AbsCustomLoginModuleService")
public class LoginModuleService extends AbsCustomLoginModuleService {
    /**
     * Jump to login page
     *
     * @param context
     * @param bundle
     */
    @Override
    public void goLogin(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        if(!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
