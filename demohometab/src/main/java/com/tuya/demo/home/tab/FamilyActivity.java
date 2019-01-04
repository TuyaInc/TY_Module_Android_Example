package com.tuya.demo.home.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tuya.demo.common.BaseActivity;
import com.tuya.demo.home.R;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;

public class FamilyActivity extends BaseActivity {

    private static final int REQUEST = 100;
    private TextView contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_activity_family);
        initToolbar();
        setTitle(R.string.ex_family);
        contentView = findViewById(R.id.family_content);
        setDisplayHomeAsUpEnabled();
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlRouter.execute(new UrlBuilder(FamilyActivity.this, "result")
                        .setRequestCode(REQUEST));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST) {
            if (resultCode == RESULT_OK) {
                contentView.setText(data.getStringExtra("family_name"));
                Toast.makeText(this, "get RESULT_OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "get RESULT_CANCELED", Toast.LENGTH_LONG).show();
            }
        }
    }
}
