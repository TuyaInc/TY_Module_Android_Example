## Andorid脚手架使用
下述内容中的“demo”指的是github上的[TY_Module\_Android\_Example](https://github.com/TuyaInc/TY_Module_Android_Example)工程，地址: https://github.com/TuyaInc/TY\_Module\_Android\_Example

以下说明和demo一起使用会加速理解，也可以基于demo来提高开发速度

***特别注意：由于组件的自动生成的一些配置会以组件名(即Android studio中的Module Name)来命名，故为避免重复，自定义的android组件名需要带上自己特定的前缀(如demo工程中的demoxxx)***


### 环境配置
##### 1. 添加依赖源和依赖（ rootProject的build.gradle）

```
buildscript {
    repositories {
        maven {
            url "https://raw.githubusercontent.com/TuyaInc/mavenrepo/master/releases"
        }
        //...
    }
    dependencies {
        'com.tuya.android.module:tymodule-config:@{version}'
    }
}
allprojects {
    repositories {
        maven {
            url "https://raw.githubusercontent.com/TuyaInc/mavenrepo/master/releases"
        }
        //...

    }
}

```
##### 2. 在自定义组件的build.gradle中增加Plugin和依赖

```
apply plugin: 'tymodule-config'
dependencies{
    compileOnly "com.tuya.android.module:tymodule-annotation:@{version}"
}
```

其中版本信息@{version}参考demo

### 自定义组件增加App启动时的初始化逻辑
1. build.gradle中增加apply plugin: 'tymodule-config'及tymodule-annotation依赖
2. 创建类继承AbstractPipeLineRunnable并实现run方法，在run方法中进行初始化操作
3. 在新创建的类中增加TYPipeLine注解，TYPipeLine的类型有下面三种：
	+ PIPE\_LINE\_APPLICATION\_SYNC
		+ Application启动时，在主线程中同步调用，在所有的activty、service、receiver的创建前执行，所花费的时间会直接影响第一个activity的启动，应尽量减少使用且不做耗时的逻辑处理
	+ PIPE\_LINE\_APPLICATION\_ASYNC
		+ Application启动时，在非主线程调用，可以做一些异步初始化的逻辑
	+ PIPE\_LINE\_TAB\_LAUNCHER\_STARTED
		+ 首页启动后在非主线程调用，可以做一些预处理操作

参考demo中的HomeModuleInitPipeLine及MyModuleInitPipeLine)

### 自定义登录组件
1. build.gradle中增加apply plugin: 'tymodule-config'及tymodule-annotation依赖
2. 创建类继承AbsCustomLoginModuleService并实现goLogin方法，在需要登录时会调用goLogin方法
3. 在创建类中增加@TYService("com.tuya.smart.commonbiz.api.login.AbsCustomLoginModuleService")注解
4. 在登陆成功后通过调用AbsLoginEventService服务的onLogin接口来通知登录成功的信息

参考demo中的login组件

### 服务调用
+ 服务使用方通过MicroContext.findServiceByInterface(String serviceName)来获取服务，并调用服务接口，如之前登录成功后的通知就是通过调用涂鸦服务完成

```
    AbsLoginEventService loginEventService = MicroContext.findServiceByInterface(
            AbsLoginEventService.class.getName());
    if (loginEventService != null) {
        loginEventService.onLogin();
    }
```
***当服务的实现组件没有被编译进apk时，findServiceByInterface返回的为null***

+ 如果希望在自定义模块中也使用路由，参看[自定义服务](./odm_service.md)

### 页面路由

+ 页面路由使用方提供下述两种方式进行跳转
	+ UrlRouter.execute(Context context, String url)及UrlRouter.execute(Context context, String url, int requestCode)，后一个方法带有requestCode，用于startActivityForResult
		+ url可以是http/https/files用于打开web页面
		+ url也可以是scheme + "://target?key=value&key2=value2", scheme默认为tuyaSmart，也可以是app后台配种自定义的scheme	
	+ UrlRouter.execute(UrlRouter.makeBuilder(activity, target)).putExtras(bundle).setRequestCode(requestCode))，此种方式中的target就是第一种方式中的target，bundle为入参(等同于Intent.putExtras(Bundle bundle))
+ 如跳转到涂鸦无家庭页面短链:tuyaSmart://familyAction?action=no_family，可以使用

```
UrlRouter.execute(context, "tuyaSmart://familyAction?action=no_family")
```
或者

```
UrlRouter.execute(UrlRouter.makeBuilder(context,"familyAction").putString("action","no_family"))

```

+ 如果希望在自定义模块中也使用路由，参看[自定义路由](./odm_route.md)

### 自定义首页tab组件
1. build.gradle中增加apply plugin: 'tymodule-config'及tymodule-annotation依赖
2. 创建tab配置类，继承BaseTabWidget，实现getIndicatorView及getTabFragment接口（getIndicatorView返回TAB底部的View，getTabFragment返回tab切换后的内容Fragment
3. 在tab配置类添加tab注解，通过注解来识别Tab组件
	+ name为tab的名字，需要和其他tab不同，跳转时会根据此name跳转到相应的tab，如demo中的tuyaSmart://home?tab=my链接会跳转到到My这个tab页
	+ url为可选参数，标识为tab点击后是否需要根据url进行跳转, url为空且getTabFragment方法返回空，则这个配置会被忽略。如果url非空，则tab点击后会根据url进行页面跳转，不会触发tab切换的操作
4. (可选)如果需要监听tab切换事件，可以在BaseTabWidget中重载onEnter和onLeave方法，分别标识进入tab和离开tab

***每个组件只允许一个配置一个Tab，如果有多个tab，需要创建多个组件，每个组件里分别进行tab配置***

参考demo中的hometab和mytab组件

### 混淆
+ 所有自定义组件在app打混淆包时的混淆配置通过在组件的build.gradle中增加consumerProguardFiles 'xxx-proguard-rules.pro'，xxx-proguard-rules.pro为自定义的混淆配置文件，在app打包时会使用此混淆配置。混淆配置文件中只需要包含此组件的混淆配置及此组件依赖的三方库的混淆配置

+ 自定义组件中所有继承ModuleApp、ITabGetter、MicroService及AbstractPipeLineRunnable的类的公共方法在都不能被混淆

```
-keep class * extends com.tuya.smart.api.model.ApiModel {
    *;
}

-keep class * extends com.tuya.smart.api.module.ModuleApp

-keep class * extends com.tuya.smart.api.tab.ITabGetter

-keep class * extends com.tuya.smart.api.service.MicroService{
    !private <methods>;
    !private <fields>;
}
-keep class * extends com.tuya.smart.api.start.AbstractPipeLineRunnable

```