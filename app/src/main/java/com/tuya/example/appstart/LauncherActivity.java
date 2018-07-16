package com.tuya.example.appstart;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tuya.example.R;
import com.tuya.smart.api.router.UrlRouter;

public class LauncherActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UrlRouter.execute(LauncherActivity.this, UrlRouter.SCHEME_PREFIX
                        + UrlRouter.MODULE_APP + "=home&tab=my");
            }
        }, 2000);
    }

}
