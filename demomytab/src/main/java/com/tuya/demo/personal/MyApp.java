package com.tuya.demo.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tuya.demo.personal.module.PersonalActivity;
import com.tuya.demo.personal.module.ResultActivity;
import com.tuya.smart.api.module.ModuleApp;
import com.tuya.smart.tymodule_annotation.TYRouter;

import java.util.HashMap;
import java.util.Map;


/**
 * 我的模块的路由配置
 */
@TYRouter({"demo.personal", "demo.result"})
public class MyApp extends ModuleApp {
    static Map<String, Class> activityMap = new HashMap<>();

    static {
        activityMap.put("demo.personal", PersonalActivity.class);
        activityMap.put("demo.result", ResultActivity.class);

    }

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
}
