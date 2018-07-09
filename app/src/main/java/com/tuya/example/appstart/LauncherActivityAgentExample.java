package com.tuya.example.appstart;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.tuya.example.R;
import com.tuya.smart.api.app.LaunchActivityAgent;
import com.tuya.smart.api.router.UrlRouter;

public class LauncherActivityAgentExample extends LaunchActivityAgent {

    private Activity activity;
    private Handler handler = new Handler();

    public LauncherActivityAgentExample(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.setContentView(R.layout.splash_activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UrlRouter.execute(activity, UrlRouter.SCHEME_PREFIX
                        + UrlRouter.MODULE_APP + "=home");
            }
        }, 2000);
    }

}
