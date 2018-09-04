package com.tuya.home.pipeline;

import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.service.HomeService;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

@TYPipeLine(type = PipeLineType.APP_ASYNC)
public class HomeAppAsyncPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        HomeService testService = MicroContext.getServiceManager()
                .findServiceByInterface(HomeService.class.getName());
        testService.init();
        LogUtil.d("AppStartAsyncPipeLine", "run");
    }
}
