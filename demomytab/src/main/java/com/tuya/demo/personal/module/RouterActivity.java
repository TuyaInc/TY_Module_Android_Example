package com.tuya.demo.personal.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.my.R;
import com.tuya.smart.api.router.UrlRouter;

/**
 * Created by huyang on 2019/1/16.
 */

public class RouterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_router);
        initToolbar();
        setTitle(R.string.ex_router);
        setDisplayHomeAsUpEnabled();
        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(RouterActivity.this, "tuyaSmart://home");
            }
        });
        findViewById(R.id.device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(RouterActivity.this, "tuyaSmart://addDevice");
            }
        });
        findViewById(R.id.family).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(RouterActivity.this, "tuyaSmart://family_manage");
            }
        });
    }
}
