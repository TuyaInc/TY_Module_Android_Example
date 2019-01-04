package com.tuya.demo.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.smart.api.module.ModuleApp;
import com.tuya.smart.appshell.activity.AppShellActivity;
import com.tuya.smart.tymodule_annotation.TYRouter;

/**
 * demo中跳转首页路由配置，在集成的时候不需要引入
 */
@TYRouter("home")
public class HomeApp extends ModuleApp {
    @Override
    public void route(Context context, String target, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, AppShellActivity.class);
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
