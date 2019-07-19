package com.tuya.demo.home.tab.pipeline;

import com.tuya.demo.home.tab.panel.PanelCallerService;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.router.UrlRouter;
import com.tuya.smart.api.service.RedirectService;
import com.tuya.smart.api.start.AbstractPipeLineRunnable;
import com.tuya.smart.panelcaller.api.AbsPanelCallerService;
import com.tuya.smart.tymodule_annotation.PipeLineType;
import com.tuya.smart.tymodule_annotation.TYPipeLine;

/**
 * 测试代码，请不要引入正式版本
 * <p>
 * home模块需要在app启动时做的初始化
 */
@TYPipeLine(type = PipeLineType.APP_SYNC)
public class HomeModuleInitPipeLine extends AbstractPipeLineRunnable {
    @Override
    public void run() {
        //注册自定义的重载服务
        RedirectService redirectService = MicroContext
                .findServiceByInterface(RedirectService.class.getName());
        redirectService.registerService(AbsPanelCallerService.class.getName(), new PanelCallerService());

        RedirectService.UrlInterceptor interceptor = new RedirectService.UrlInterceptor() {
            @Override
            public void forUrlBuilder(UrlBuilder urlBuilder, RedirectService.InterceptorCallback interceptorCallback) {
                //将到demo.home的路由重定向到home
                if ("demo.home".equals(urlBuilder.target)) {
                    UrlRouter.execute(urlBuilder.context, "tuyaSmart://home");
                    //拦截，不再走原来的路由操作
                    interceptorCallback.interceptor("");
                } else {
                    //不拦截，继续原来流程
                    interceptorCallback.onContinue(urlBuilder);
                }
            }
        };
        redirectService.registerUrlInterceptor(interceptor);
        LogUtil.d("HomeModuleInitPipeLine", "run");
    }
}