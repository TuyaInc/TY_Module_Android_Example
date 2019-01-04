package com.tuya.demo.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.SmartInitializer;
import com.tuya.smart.commonbiz.api.login.AbsLoginEventService;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.INeedLoginListener;

/**
 * demo运行的Application，在集成的时候不需要引入
 */
public class DemoApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SmartInitializer.setDebug(true);
        SmartInitializer.init(this);
        //demo运行时需要替换为对应的appKey和appSecret
        TuyaHomeSdk.init(this, "appKey", "appSecret");
        TuyaHomeSdk.setOnNeedLoginListener(new INeedLoginListener() {
            @Override
            public void onNeedLogin(Context context) {
                //session失效时的处理,
                AbsLoginEventService loginEventService = MicroContext.findServiceByInterface(
                        AbsLoginEventService.class.getName());
                if (loginEventService != null) {
                    loginEventService.reLogin(context);
                }
            }
        });
        SmartInitializer.startPipeLine();
    }
}