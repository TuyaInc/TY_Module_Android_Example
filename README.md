## 脚手架
+ 脚手架定义了app内部的通信方式，各页面和模块之间通过脚手架提供的接口进行页面跳转和交互
+ 脚手架接入是基于涂鸦app开发的一种接入模式，比起基于涂鸦sdk开发，可以减少客户的开发时间和成本，此种模式下
	+ 客户可以将自身已经具有的功能通过aar的方式和涂鸦的功能进行打包无缝整合成一个应用
	+ 在此应用中，客户可以通过脚手架提供的接口和涂鸦app的页面进行交互，复用涂鸦app的已有页面和功能，如直接唤起设备详情页面
	+ 客户也可以自行研发功能页面，替换掉涂鸦app中的已有功能

**比如：客户可以自己实现登录和首页的各个tab页面，然后复用涂鸦已有的设备详情、配网、家庭管理等页面**


## 接入方式
脚手架提供以下的各种接口，供各接入方使用

### 概要
+ 应用初始化
	+ 接入方不能直接使用自己定义的Application，需要使用脚手架中提供的AbstractPipeLineRunnable来完成原本在应用初始化时做的事情，在Application启动时，脚手架会在相应时刻执行AbstractPipeLineRunnable
+ 首页tab模块接入
	+ 可以将自己研发的页面作为一个tab合入到app中，如增加一个商场的tab页，或者替换掉涂鸦的某个tab页
+  与涂鸦的交互
	+ url页面跳转
		+ 简单的页面跳转可以直接通过url短链来跳到对应页面
	+ 服务
		+ 服务是除了通过url短链交互外，对外提供的另一种通信方式，可以通过已有的服务来来进行一定的交互，一些逻辑复杂的页面跳转也是通过服务的方式提供


### 应用初始化管理-PipeLineManager
+ 模块需要在应用启动时做的初始化可以通过自定义AbstractPipeLineRunnable并加上TYPipeLine注解来完成，TYPipeLine的类型有下面三种：
	+ PIPE\_LINE\_APPLICATION\_SYNC
		+ Application启动时，在主线程中同步调用，在所有的activty、service、receiver的创建前执行，所花费的时间会直接影响第一个activity的启动，应尽量减少使用且不做耗时的逻辑处理
	+ PIPE\_LINE\_APPLICATION\_ASYNC
		+ Application启动时，在非主线程调用，可以做一些异步初始化的逻辑
	+ PIPE\_LINE\_TAB\_LAUNCHER\_STARTED
		+ 首页启动后在非主线程调用，可以做一些预处理操作

**demo中的PipeLine是一个使用的参考，只有在确实需要的时候去实现PipeLine，尽量的减少PipeLine的使用以提高应用的启动速度**

+ PipeLine继承AbstractPipeLineRunnable，并添加相应注解,如：

```
@TYPipeLine(type = PipeLineType.APP_ASYNC)
public class MyAppAsyncPipeLine implements AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("MyAppAsyncPipeLine", "run");
    }
}
```

```
@TYPipeLine(type = PipeLineType.APP_SYNC)
public class MyAppSyncPipeLine implements AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("MyAppSyncPipeLine", "run");
    }
}
```


```
@TYPipeLine(type = PipeLineType.TabLauncher)
public class MyTabPipeLine implements AbstractPipeLineRunnable {
    @Override
    public void run() {
        LogUtil.d("MyTabPipeLine", "run");
    }
}
```
	
### 首页Tab开发接入
+ 每个tab实现下述接口，getIndicatorView返回TAB底部的View，getFragment返回tab切换后的内容Fragment

```
public interface ITabGetter {

    /**
     * 
     * Get bottom tab info，返回空会被忽略
     *
     * @return
     */
    View getIndicatorView(Context context);

    /**
     * Get tab Fragment，返回空但是注解中url非空点击会根据url跳转
     *
     * @return
     */
    Fragment getFragment();
}
```

+ 添加tab注解
	+ index为tab位置，tab展示会根据index从小到大排序
	+ name为tab的名字，需要和其他tab不同，跳转时会根据此name跳转到相应的tab，如下属链接会跳转到到ty_device这个tab

```
tuyaSmart://appshell?tab=ty_device
```

```

@TYTabIndicator(name = "ty_device", index = 1)
public class HomeTabGetter implements ITabGetter {
    @Override
    public View getIndicatorView(Context context) {
        View view = View.inflate(context, R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText("家庭");
        return view;
    }

    @Override
    public Fragment getFragment() {
        return new HomeFragment();
    }
}
```

### Url页面跳转
+ 跳转会根据ModuleApp进行跳转，ModuleApp为模块的跳转入口，跳转提供方需要继承实现下述类,告诉跳转使用方modle支持跳转的页面名字target以及所需要的参数，通过注解标识ModuleApp支持的页面名称target(当然也可以定义其他非页面的target来进行非页面的跳转处理)
	+ 注意：ModuleApp对象可能会销毁后重新创建，暂时不要在这个对象里通过成员变量保存信息
	+ ModuleApp支持的target在各个module里不能重复，重复会在最终打包时失败
	+ ***在ModuleApp中startActivity时，如果传入的context不是activity，Intent需要增加Intent.FLAG_ACTIVITY_NEW_TASK，才能启动activity***
	
```

public abstract class ModuleApp {

    /**
     * Module Entrance
     *
     * @param context
     * @param target
     * @param bundle
     * @requestCode if> 0, this code must be returned in
     * onActivityResult() when the activity exits
     */
    public abstract void route(Context context, String target, Bundle bundle, int requestCode);
}

```

例：

```
@TYRouter({"setting", "personal"})
public class MyApp extends ModuleApp {
    @Override
    public void route(Context context, String target, Bundle bundle, int requestCode) {
        Class<? extends Activity> activityClass = activityMap.get(target);
        if (activityClass != null) {
            Intent intent = new Intent(context, activityClass);
            intent.putExtras(bundle);
	    if (!(context instanceof Activity)) {
            	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (context instanceof Activity && requestCode > 0) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        } else {
            //todo
        }
    }

    private static final Map<String, Class<? extends Activity>> activityMap = new HashMap<>();

    static {
        activityMap.put("setting", SettingActivity.class);
        activityMap.put("personal", PersonalActivity.class);
    }
}
}
```

+ 跳转使用方提供下述两种方式进行跳转
	+ UrlRouter.execute(Context context, String url)及UrlRouter.execute(Context context, String url, int requestCode)，后一个方法带有requestCode，用于startActivityForResult
		+ url可以是http/https/files用于打开web页面
		+ url也可以是UrlRouter.getScheme() + "://target?key=value&key2=value2",对于此种内部跳转，推荐使用UrlRouter.makeBuilder来跳转，无需拼接url
	
	+ UrlRouter.execute(UrlRouter.makeBuilder(activity, target)).putExtras(bundle).setRequestCode(requestCode))，设置requestCode则实现方判断是否使用startActivityForResult

### 服务
+ 服务提供方继承实现下述基类，通过注解将服务进行注册(@TYService("xxx"),xxx为服务接口名)，提供服务接口给使用方
+  ***服务MicroService的实现是一个单例，在整个APP的生命周期里只会被创建一次，也只会调用一次无参构造方法和服务的onCreate方法，不会主动调用onDestroy方法***

```
public abstract class MicroService {

    protected Context mBase;

    public void attachBaseContext(Context base) {
        if (mBase != null) {
            throw new IllegalStateException("Base context already set");
        }
        mBase = base;
    }
    /**
     * Service onCreate
     */
    public void onCreate() {
    }
    public void onDestroy() {
    }
    
    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {

    }
}
```
如：

```
package com.tuya.demo.service;

import com.tuya.smart.api.service.MicroService;
/** 服务接口 **/
public abstract class TestService extends MicroService {

    public abstract int add(int i, int j);

    public abstract void register();

    public abstract void init();
}
```


```
package com.tuya.demo.service;

import com.tuya.smart.api.utils.LogUtil;
/** 服务实现 **/
@TYService("com.tuya.smart.api.service.TestService")
public class TestServiceImpl extends TestService {
    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public void register() {

    }

    @Override
    public void init() {
        LogUtil.d("TestServiceImpl", " init");
    }
}
```

+ 服务使用方通过MicroServiceManagerImpl及服务名来获取服务，并调用服务接口
如

```
  TestService testService = MicroContext.getServiceManager()
            .findServiceByInterface(TestService.class.getName());
  int result = testService.add(1, 2);
```
+ 对于需要对外提供服务的模块，在模块里创建一个api的子模块，模块里放置api接口等其他外部服务需要依赖的类，api的实现放在另一个模块里，外部业务使用的时候可以通过依赖api模块来进行编译，对于api的实现使用模块无感知，实现的模块可以被替换

### 模块混淆

所有ModuleApp、ITabGetter、MicroService及pipleline的类的公共方法都不能被混淆

```
-keep class com.tuya.smart.api.** {
    *;
}

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

## TuyaSDK
[Tuya Smart Android SDK](https://github.com/TuyaInc/tuyasmart_home_android_sdk)
