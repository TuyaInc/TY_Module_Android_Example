package com.tuya.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.smart.api.module.ModuleApp;

public class DemoModuleApp extends ModuleApp {
    @Override
    public void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DemoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
