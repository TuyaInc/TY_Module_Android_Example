#### 自定义服务
+ 1、build.gradle中增加apply plugin: 'tymodule-config'及tymodule-annotation依赖
+ 2、服务提供方新建抽象api类(如TestService)继承下述基类
+ 3、服务提供方新建api实现类(如TestServiceImpl)
+ 4、在api实现类增加注解(@TYService("xxx"),xxx为服务接口名)，提供服务接口给使用方

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
