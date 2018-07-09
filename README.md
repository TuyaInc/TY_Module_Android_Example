# TY_Module_Android_Example

## APP初始化
+ 针对在APP启动时增加自定义的初始化逻辑，不需要无需实现
+ 继承com.tuya.smart.api.app.LauncherApplicationAgent实现APP初始化逻辑，Application的生命周期会回调LauncherApplicationAgent的对应方法，将自定义的LauncherApplicationAgent在AndroidManifest中配置进行<meta-data android:name="APPLICATION_AGENT" android:value="xxx"/>，xxx为自定义的LauncherApplicationAgent的全名(包括包名)

## Splash启动页面
+ 针对需要深度定义Splash启动页面启动逻辑，不需要无需实现
+ 继承com.tuya.smart.api.app.LaunchActivityAgent，Splash在各生命周期会回调LaunchActivityAgent对应方法

## 首页TAB定义
+ 针对需要在首页插入自定义TAB
+ 实现接口com.tuya.smart.api.tab.ITabGetter, 实现里返回对应的Fragment及底部tab View
+ 首页TAB配置放在assets的tab_config.json中

## 导航
+ 独立的功能模块Module支持跳转的，需要继承实现com.tuya.smart.api.module.ModuleApp的start方法，通过tuyaSmart://platformapi?app=?&key=value&key1=value1的方式进行页面导航，页面跳转调用execute(Context context, String url)方法进行跳转，会根据url中的app值跳转到对应的ModuleApp
+ 各ModuleApp配置放在assets的module_app.json中
