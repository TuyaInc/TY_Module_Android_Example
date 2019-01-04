package com.tuya.demo.app;

import android.os.Bundle;
import android.os.Handler;

import com.tuya.demo.R;
import com.tuya.demo.common.BaseActivity;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.commonbiz.api.login.AbsLoginEventService;

/**
 * Launcher，demo运行的测试代码，在集成的时候不需要引入
 */
public class LauncherActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin()) {
                    UrlBuilder urlBuilder = UrlRouter.makeBuilder(LauncherActivity.this,
                            "home");
                    UrlRouter.execute(urlBuilder);
                } else {
                    AbsLoginEventService loginEventService = MicroContext.findServiceByInterface(
                            AbsLoginEventService.class.getName());
                    if (loginEventService != null) {
                        loginEventService.goLogin(LauncherActivity.this, null);
                    }
                }
                finish();
            }
        }, 500);

    }

    /**
     * 判断是否已登录
     *
     * @return
     */
    private boolean isLogin() {
        //此处mock为false，正常应该为TuyaHomeSdk.getUserInstance().isLogin()
        return false;
        //return TuyaHomeSdk.getUserInstance().isLogin();
    }

}