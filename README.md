## 脚手架 [![CircleCI](https://circleci.com/gh/TuyaInc/TY_Module_Android_Example/tree/master.svg?style=svg)](https://circleci.com/gh/TuyaInc/TY_Module_Android_Example/tree/master)
+ 脚手架定义了app内部的通信方式，各页面和模块之间通过脚手架提供的接口进行页面跳转和交互
+ 脚手架接入是基于涂鸦app开发的一种接入模式，比起基于涂鸦sdk开发，可以减少客户的开发时间和成本，此种模式下
	+ 客户可以将自身已经具有的功能通过aar的方式和涂鸦的功能进行打包无缝整合成一个应用
	+ 在此应用中，客户可以通过脚手架提供的接口和涂鸦app的页面进行交互，复用涂鸦app的已有页面和功能，如直接唤起设备详情页面
	+ 客户也可以自行研发功能页面，替换掉涂鸦app中的已有功能

**比如：客户可以自己实现登录和首页的各个tab页面，然后复用涂鸦已有的设备详情、配网、家庭管理等页面**

## 能力
+ 开发

	脚手架提供以下开发接口
	
	+ 自定义组件初始化
		+ 接入方不能直接使用自己定义的Application，需要使用脚手架中提供的AbstractPipeLineRunnable来完成原本在应用初始化时做的事情，在Application启动时，脚手架会在相应时刻执行AbstractPipeLineRunnable
	+ 自定义登录组件
		+ 通过实现AbsCustomLoginModuleService服务来注入自定义登录模块，登陆成功后通过调用AbsLoginEventService服务的onLogin接口来通知登录成功的信息，如demo中的login组件
	+ 自定义首页tab组件
		+ 可以将自己研发的页面作为一个tab合入到app中，如增加一个商场的tab页，或者替换掉涂鸦的某个tab页，如demo中的hometab和mytab组件
	+  与涂鸦的交互
		+ 页面跳转
			+ 通过url短链来跳到涂鸦页面
		+ 服务
			+ 服务是除了通过url短链交互外，对外提供的另一种通信方式，可以通过涂鸦已有的服务API来进行交互，一些逻辑复杂的页面跳转也是通过服务的方式提供

具体使用参见 [ODM开发](./doc/odm_develop_detail.md)

+ 调试

待补充

+ 集成

通过tuya plugin将模块上传到tuya仓库，然后通过后台配置进行集成打包，具体使用参见[ODM集成](./doc/odm_upload.md)

## TuyaSDK
[Tuya Smart Android SDK](https://github.com/TuyaInc/tuyasmart_home_android_sdk)

## demo说明
demo中展示了tab组件、登录组件的创建方式，以及自定义组件初始化、页面跳转、服务调用的的使用方式

+ app
	+ demo独立运行app，不参与集成，包含为了让demo正常运行的一些适配测试代码
+ democommon
	+ base组件
+ demohometab
	+ 首页home组件
+ demomytab
	+ 首页我的组件
+ demologin
	+ 登录组件
+ sdk-xxx
	+ 涂鸦提供的基础sdk和脚手架api  
