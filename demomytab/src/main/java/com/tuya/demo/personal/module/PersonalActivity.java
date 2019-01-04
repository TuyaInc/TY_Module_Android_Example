package com.tuya.demo.personal.module;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.my.R;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.commonbiz.api.login.AbsLoginEventService;

/**
 * 个人页面，退出登录后必须调用AbsLoginEventService的onLogout方法
 */
public class PersonalActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_persona);
        initToolbar();
        setTitle(R.string.ex_my);
        setDisplayHomeAsUpEnabled();
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        showLoading("");

        //do logout
        //....
        //...

        //使用handle模拟退出登录耗时
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //退出登录成功后调用AbsLoginEventService通知其他业务已退出登录
                AbsLoginEventService absLoginEventService = MicroContext.findServiceByInterface(
                        AbsLoginEventService.class.getName());
                if (absLoginEventService != null) {
                    absLoginEventService.onLogout(PersonalActivity.this);
                }
                hideLoading();
                //跳转到登录页
                AbsLoginEventService loginEventService = MicroContext.findServiceByInterface(
                        AbsLoginEventService.class.getName());
                if (loginEventService != null) {
                    loginEventService.goLogin(PersonalActivity.this, null);
                }
            }
        }, 500);

    }
}
