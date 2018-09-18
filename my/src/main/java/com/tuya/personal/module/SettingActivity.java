package com.tuya.personal.module;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tuya.demomodule2.R;
import com.tuya.tab.example.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolbar();
        setTitle("设置");
        setDisplayHomeAsUpEnabled();
    }
}
