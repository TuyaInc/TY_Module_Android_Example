#### 自定义路由
+ 1、build.gradle中增加apply plugin: 'tymodule-config'及tymodule-annotation依赖
+ 2、创建类继承ModuleApp并实现route接口
+ 3、增加注解，通过注解标识ModuleApp支持的页面名称target

注意

+ ***跳转会根据ModuleApp进行跳转，ModuleApp为组件的跳转入口***
+ ***ModuleApp对象可能会销毁后重新创建，暂时不要在这个对象里通过成员变量保存信息***
+ ***ModuleApp支持的target在各个module里不能重复，重复会在最终打包时失败，故在自定义路由时需要增加自己的前缀，避免冲突***
	
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
            if (context instanceof Activity && requestCode > 0) {
                ((Activity) context).startActivityForResult(intent, requestCode);
            } else {
                context.startActivity(intent);
            }
        } else {
            //
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

#### 使用路由
```
UrlRouter.execute(context, "tuyaSmart://setting")
```
或者

```
UrlRouter.execute(UrlRouter.makeBuilder(context,"setting")）

```