# 安卓脚手架工程


## 集成准备

### 版本支持

```
   minSdkVersion 16
   targetSdkVersion 25
```

### 引入依赖

```

    api 'com.android.support:multidex:1.0.3'
    api 'com.alibaba:fastjson:1.1.67.android'
    implementation project(':tuyasmart-router')
    implementation project(':smartApi-release')

```

## Provider介绍

### Fragment加载

参考DemoProvider 需要配置对应tabFragment，集成到涂鸦APP会加载这个Fragment到TAB页面中，
需要配置对应tabConfig

### APP生命周期

* Application进程初始化 主线程
* Application进程初始化 异步线程
* 登陆
* 登出

### Tuya依赖参考
为了避免由于第三方依赖库冲突导致打包失败，请参考以下涂鸦APP的依赖
```
//TuyaAPP依赖库参考
//"com.android.support.constraint:constraint-layout": "1.0.2",
//"de.greenrobot:eventbus": "2.4.0",
//"com.kyleduo.switchbutton:library": "1.4.2",
//"com.google.android:flexbox": "0.2.5",
//"de.greenrobot:greendao": "1.3.7",
//"com.google.zxing:core": "3.2.1",
//"com.airbnb.android:lottie": "2.2.0",
//"org.apache.commons:commons-lang3": "3.3.2",
//"commons-collections:commons-collections": "3.2.1",
//"org.apache.commons:commons-compress": "1.9",
//"com.readystatesoftware.systembartint:systembartint": "1.0.3",
//"org.jetbrains.kotlin:kotlin-stdlib-jdk7": "1.2.41",
//"com.facebook.fbui.textlayoutbuilder:textlayoutbuilder": "1.0.0",
//"com.fasterxml.jackson.core:jackson-core": "2.2.3",
//"com.facebook.soloader:soloader": "0.1.0",

//MQTT
//"org.eclipse.paho:org.eclipse.paho.client.mqttv3": "1.2.0",
//"org.eclipse.paho:org.eclipse.paho.android.service": "1.1.1",

//rxjava
//"io.reactivex.rxjava2:rxandroid": "2.0.1",
//"io.reactivex.rxjava2:rxjava": "2.1.7",

//grpc
//"io.grpc:grpc-okhttp":"1.4.0",
//"io.grpc:grpc-protobuf-lite":"1.4.0",
//"io.grpc:grpc-stub":"1.4.0"


//okhttp
//"com.squareup.okhttp3:okhttp": "3.6.0",
//"com.squareup.okhttp3:okhttp-urlconnection": "3.6.0",

//Google
//"com.google.android.gms:play-services-location": "11.8.0",
//"com.google.android.gms:play-services-maps": "11.8.0",
//"com.google.android.gms:play-services-base": "11.8.0",
//"com.google.firebase:firebase-messaging": "11.8.0",

//Fresco
//"com.facebook.fresco:animated-gif": "1.3.0",
//"com.facebook.fresco:fresco": "1.3.0",
//"com.facebook.fresco:imagepipeline-okhttp3": "1.3.0",
//"com.facebook.react:react-native": "0.51.0",

//Umeng SDK
//"com.umeng.sdk:analytics": "7.5.0",
//"com.umeng.sdk:push-xiaomi":"1.0.0",
//"com.umeng.sdk:push-huawei":"1.0.0",
//"com.umeng.sdk:push-meizu":"1.0.0",
//"com.umeng.sdk:common":"1.5.3",
//"com.umeng.sdk:push":"4.2.0",
//"com.umeng.sdk:debug":"1.0.0",
//"com.umeng.sdk:utdid":"1.1.5.3"

//高德
//"com.amap.api:amap_search": "3.3.0",
//"com.amap.api:amap_2dmap": "2.9.0",
//"com.amap.api:amap_location": "2.4.1",
```
