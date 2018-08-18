package com.tuya.home.pipeline;


import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.TabLauncher)
public class HomeTabStartPipeLine implements Runnable {
    @Override
    public void run() {
        LogUtil.d("HomeTabStartPipeLine", "HomeTabStartPipeLine run");
    }
}
