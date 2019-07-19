package com.tuya.demo.home.tab.activity;

import android.os.Bundle;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.home.R;

public class AddDeviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_add_device);
        initToolbar();
        setTitle(R.string.ex_add_device);
    }
}
