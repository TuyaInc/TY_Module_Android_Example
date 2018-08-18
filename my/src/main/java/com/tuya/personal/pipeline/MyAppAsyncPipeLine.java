package com.tuya.personal.pipeline;

import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.APP_ASYNC)
public class MyAppAsyncPipeLine implements Runnable {
    @Override
    public void run() {
        LogUtil.d("MyAppAsyncPipeLine", "run");
    }
}
