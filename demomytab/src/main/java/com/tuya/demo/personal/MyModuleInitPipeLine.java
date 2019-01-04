package com.tuya.demo.personal;

import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;


/**
 * 测试代码，请不要引入正式版本
 * <p>
 * 我的模块在APP启动时的异步初始化
 */
@TYPipeLine(type = PipeLineType.APP_ASYNC)
public class MyModuleInitPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("MyModuleInitPipeLine", "run");
    }
}
