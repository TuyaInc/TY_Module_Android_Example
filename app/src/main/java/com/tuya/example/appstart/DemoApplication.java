package com.tuya.example.appstart;

import android.support.multidex.MultiDexApplication;

import com.tuya.smart.api.SmartInitializer;
import com.tuya.smart.api.logger.LogUtil;
import com.tuya.smart.home.sdk.TuyaHomeSdk;

/**
 * @author huyang
 */
public class DemoApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        SmartInitializer.setDebug(true);
        SmartInitializer.init(this);
        LogUtil.d("DemoApplication", "onCreate");
        //使用涂鸦sdk开发接入时使用下述方法进行初始化
        //直接作为涂鸦app的一部分开发的时候不需要设置(使用demo调试的时候可以进行设置，正式release时不要设置)
        TuyaHomeSdk.init(this, "appKey", "appSecret");
        SmartInitializer.startPipeLine();
//
//
//        RedirectService redirectService = MicroContext.getServiceManager().findServiceByInterface(
//                RedirectService.class.getName());
//        redirectService.registerServiceInterceptor(new RedirectService.ServiceInterceptor() {
//            @Override
//            public MicroService forService(String serviceInterface) {
//                if (HomeService.class.getName().equals(serviceInterface)) {
//                    RedirectTestService service = new RedirectTestService();
//                    service.attachBaseContext(DemoApplication.this);
//                    service.onCreate();
//                    return service;
//                }
//                return null;
//            }
//        });
//        redirectService.registerUrlInterceptor(new RedirectService.UrlInterceptor() {
//            @Override
//            public void forUrlBuilder(UrlBuilder builder, RedirectService.InterceptorCallback callback) {
//                if ("demo".equals(builder.target)) {
//                    UrlBuilder replace = new UrlBuilder(builder);
//                    replace.target = "demo2";
//                    callback.onContinue(replace);
//                    return;
//                }
//                callback.onContinue(builder);
//            }
//        });
    }
}
