package com.tuya.personal.pipeline;

import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.TabLauncher)
public class MyTabPipeLine implements Runnable {
    @Override
    public void run() {
        LogUtil.d("MyTabPipeLine", "run");
    }
}
