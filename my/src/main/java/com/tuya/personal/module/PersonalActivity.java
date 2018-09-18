package com.tuya.personal.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.tuya.demomodule2.R;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.service.HomeService;
import com.tuya.tab.example.BaseActivity;

public class PersonalActivity extends BaseActivity {

    private String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        initToolbar();
        setTitle("我的");
        setDisplayHomeAsUpEnabled();
        HomeService homeService = MicroContext.getServiceManager()
                .findServiceByInterface(HomeService.class.getName());
        result = homeService.getHomeName();
        ((TextView) findViewById(R.id.demo_content)).setText("PerPersonal center\n get name from" +
                "  Home = " + result);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "Personal");
        setResult(RESULT_OK, intent);
        finish();
    }

}
