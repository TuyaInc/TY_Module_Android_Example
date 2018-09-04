package com.tuya.home.pipeline;

import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.APP_SYNC, priority = 2)
public class HomeSyncPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.w("HomeSyncPipeLine", "run");
    }
}
