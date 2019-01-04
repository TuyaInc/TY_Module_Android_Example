package com.tuya.demo.personal.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.my.R;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_result);
        initToolbar();
        setTitle(R.string.ex_result);
        setDisplayHomeAsUpEnabled();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("family_name", "TuyaDemo");
        setResult(RESULT_OK, intent);
        finish();
    }
}
