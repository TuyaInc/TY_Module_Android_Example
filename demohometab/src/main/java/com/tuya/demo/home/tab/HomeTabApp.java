package com.tuya.demo.home.tab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.demo.home.tab.activity.AddDeviceActivity;
import com.tuya.demo.home.tab.activity.FamilyActivity;
import com.tuya.smart.api.module.ModuleApp;
import com.tuya.smart.tymodule_annotation.TYRouter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huyang on 2019/7/19.
 */

@TYRouter({"demo.family_manage", "demo.addDevice"})
public class HomeTabApp extends ModuleApp {
    @Override
    public void route(Context context, String target, Bundle bundle, int requestCode) {
        Class<? extends Activity> activityClass = activityMap.get(target);
        if (activityClass == null) {
            return;
        }
        Intent intent = new Intent(context, activityClass);
        intent.putExtras(bundle);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (requestCode > 0 && (context instanceof Activity)) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    static Map<String, Class> activityMap = new HashMap<>();

    static {
        activityMap.put("demo.family_manage", FamilyActivity.class);
        activityMap.put("demo.addDevice", AddDeviceActivity.class);

    }
}
