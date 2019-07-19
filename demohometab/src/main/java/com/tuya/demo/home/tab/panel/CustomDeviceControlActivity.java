package com.tuya.demo.home.tab.panel;

import android.os.Bundle;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.home.R;


public class CustomDeviceControlActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_panel);
        initToolbar();
        setTitle(R.string.ex_device_control);
    }
}
