package com.tuya.demo.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tuya.demo.common.BaseActivity;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.commonbiz.api.login.AbsLoginEventService;

/**
 * 自定义登录界面，登陆成功后必须调用AbsLoginEventService的onLogin方法
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_login);
        initToolbar();
        setTitle(R.string.ex_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * Do login
     */
    private void login() {
        //处理登录逻辑
        showLoading("");
        //处理登录逻辑
        //...
        //...
        //...

        //使用handle模拟登录耗时
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //登录成功
                hideLoading();
                loginSuccess();
            }
        }, 1000);
    }

    /**
     * Login notification
     */
    private void loginSuccess() {
        //登录成功后一定需要调用AbsLoginEventService的onLogin方法，通知其他业务登陆成功
        AbsLoginEventService loginEventService = MicroContext.findServiceByInterface(
                AbsLoginEventService.class.getName());
        if (loginEventService != null) {
            loginEventService.onLogin();
        }
        finish();
        //登录成功调到首页
        UrlRouter.execute(UrlRouter.makeBuilder(this, "home"));
    }
}
