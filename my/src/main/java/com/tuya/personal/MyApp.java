package com.tuya.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.personal.module.PersonalActivity;
import com.tuya.personal.module.SettingActivity;
import com.tuya.smart.api.module.ModuleApp;
import com.tuya.smart.tymodule_annotation.TYRouter;

import java.util.HashMap;
import java.util.Map;

@TYRouter({"setting", "personal"})
public class MyApp extends ModuleApp {
    @Override
    public void startForResult(Context context, String target, Bundle bundle, int requestCode) {
        Class<? extends Activity> activityClass = activityMap.get(target);
        if (activityClass != null) {
            Intent intent = new Intent(context, activityClass);
            intent.putExtras(bundle);
            if (context instanceof Activity && requestCode > 0) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        } else {
            //todo
        }
    }

    private static final Map<String, Class<? extends Activity>> activityMap = new HashMap<>();

    static {
        activityMap.put("setting", SettingActivity.class);
        activityMap.put("personal", PersonalActivity.class);
    }
}
