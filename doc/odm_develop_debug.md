## 调试
调试框架基于滴滴开源的[VirtualAPK](https://github.com/didi/VirtualAPK)开发的
### 宿主APP
确定使用涂鸦的脚手架进行开发，且需要和涂鸦进行交互时，涂鸦侧会提供带插件调试功能的宿主APP来辅助开发阶段的调试工作
### 插件APP
插件APP的配置参考demo中的pluginapp，pluginapp中的配置是为了插件调试的特定配置，只对调试有效，正式集成的时候不再需要。pluginapp使用说明如下

+ 配置
	+ 将自己开发的业务模块添加到dependencies
	+ 首页Tab配置
		+ uiTabSequence.json：此文件配置自己开发的包含tab的模块中哪些需要打包到插件的tab配置中(只能控制自己开发的tab模块，通过tab实现类tabgetter来标记)
		+ asstes下的ui\_tab\_config\_seq.json：此文件配置插件化调试时首页tab的排序(可以控制uiTabSequence.json文件中已经配置的tab模块和涂鸦自身的tab模块在首页的展示和排序，通过tab模块中注解name属性来标记，只有在此文件中配置的tab才会在首页展示)
+ 打包
	+ 打包命令：根目录下执行 ./gradlew :pluginapp:assemblePlugin 
+ 安装
	+ 将插件推送到手机：adb push pluginapp/build/outputs/apk/release/pluginapp-release.apk /sdcard/TUYA/
	+ 安装插件
		+ 命令安装 adb shell am start -a "com.tuya.plugin.install" -e "plugin_app_path" "/sdcard/TUYA/pluginapp-release.apk"
			+ 其中 -e "plugin_app_path"的值"/sdcard/TUYA/pluginapp-release.apk"为上一步骤中插件apk推送到手机中的完整路径
		+ 手动安装 打开宿主APP的插件安装页面，进行安装
+ 打包&安装快捷脚本
	+ 根目录下的install_plugin.sh脚本封装了打包安装的命令，可以根据实际环境修改使用 
