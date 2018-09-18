package com.tuya.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.smart.api.module.ModuleApp;
import com.tuya.smart.tymodule_annotation.TYRouter;

import java.util.HashMap;
import java.util.Map;

@TYRouter({"example_home"})
public class HomeApp extends ModuleApp {
    @Override
    public void route(Context context, String target, Bundle bundle, int requestCode) {
        Class<? extends Activity> activityClass = activityMap.get(target);
        if (activityClass != null) {
            Intent intent = new Intent(context, activityClass);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } else {
            //todo
        }
    }

    private static final Map<String, Class<? extends Activity>> activityMap = new HashMap<>();

    static {
        activityMap.put("example_home", FamilyActivity.class);
    }
}
