package com.tuya.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.tuya.demomodule.R;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.tab.example.BaseActivity;

public class FamilyActivity extends BaseActivity {

    private static final int REQUEST = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolbar();
        setTitle("家庭");
        setDisplayHomeAsUpEnabled();
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(new UrlBuilder(FamilyActivity.this, "personal")
                        .setRequestCode(REQUEST));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "get RESULT_OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "get RESULT_CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }
}
