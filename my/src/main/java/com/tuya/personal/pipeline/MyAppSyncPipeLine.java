package com.tuya.personal.pipeline;


import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.APP_SYNC)
public class MyAppSyncPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("MyAppSyncPipeLine", "run");
    }
}
