package com.tuya.example.appstart;

import android.app.Application;

import com.tuya.smart.api.app.LauncherApplicationAgent;
import com.tuya.smart.api.utils.LogUtil;

public class ApplicationAgentExample extends LauncherApplicationAgent {
    public ApplicationAgentExample(Application application) {
        super(application);
    }

    @Override
    public void postOnCreate() {
        //do custom init
        LogUtil.d("ApplicationAgentExample", "postOnCreate");
    }
}
