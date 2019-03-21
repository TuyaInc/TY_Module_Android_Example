./gradlew :pluginapp:clean
./gradlew :pluginapp:assemblePlugin
adb push pluginapp/build/outputs/apk/release/pluginapp-release.apk /sdcard/TUYA/
adb shell am start -a "com.tuya.plugin.install" -e "plugin_app_path" "/sdcard/TUYA/pluginapp-release.apk"