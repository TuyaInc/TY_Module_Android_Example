package com.tuya.example.appstart;

import android.app.Application;

import com.tuya.smart.api.app.LauncherApplicationAgent;
import com.tuya.smart.api.utils.LogUtil;
import com.tuya.smart.home.sdk.TuyaHomeSdk;

public class ApplicationAgentExample extends LauncherApplicationAgent {

    /**
     * 替换为申请的appKey和appSerect
     */
    private static final String appKey = "test";
    private static final String appSerect = "test";

    public ApplicationAgentExample(Application application) {
        super(application);
    }

    @Override
    public void postOnCreate() {
        //do custom init
        LogUtil.d("ApplicationAgentExample", "postOnCreate");
        //使用涂鸦sdk开发接入时使用下述方法进行初始化
        //直接作为涂鸦app的一部分开发的时候不需要设置(使用demo调试的时候可以进行设置，正式release时不要设置)
        TuyaHomeSdk.init(getApplication(), appKey, appSerect);
    }
}
