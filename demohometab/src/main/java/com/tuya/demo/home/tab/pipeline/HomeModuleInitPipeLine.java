package com.tuya.demo.home.tab.pipeline;

import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

/**
 * 测试代码，请不要引入正式版本
 *
 * home模块需要在app启动时做的初始化
 */
@TYPipeLine(type = PipeLineType.APP_SYNC)
public class HomeModuleInitPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("HomeModuleInitPipeLine", "run");
    }
}