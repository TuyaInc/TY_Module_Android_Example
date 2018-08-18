package com.tuya.example.appstart;

import android.os.Bundle;
import android.os.Handler;

import com.tuya.example.R;
import com.tuya.smart.api.base.BaseActivity;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.api.tab.Constants;

/**
 * @author huyang
 */
public class LauncherActivity extends BaseActivity {

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UrlBuilder urlBuilder = UrlRouter.makeBuilder(LauncherActivity.this,
                        "appshell").putString(Constants.TAB_PARAM, "my");
                UrlRouter.execute(urlBuilder);
                finish();
            }
        }, 2000);
    }

}
