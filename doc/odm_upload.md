## 涂鸦 ODM 组件上传插件使用规范

 涂鸦 `ODM` 组件上传插件由两部分组成，一个用于将你的组件进行打包并传递到本地的仓库，另一个用于将本地的组件提交到涂鸦的 `git` 仓库上。

### 1.配置远端  ***git*** 仓库地址

首先在根项目的 `build.gradle` 文件，添加这个插件下载源和插件信息，并填写对应的 `git 仓库账号信息`，这些的来源参考 `ODM开发流程`。

+ 需要注意的是repoUrl需要填写为完整的git仓库地址，git仓库地址为为你创建的group下名为xxxRepository的project(其中xxx为group名，***这个仓库为打包使用，不要修改***)，例如：https://code-odm.tuya-inc.com/odmDemo/odmDemoRepository.git(其中group名为odmDemo)
+ tyODM-config的@{version}参考github上[demo](https://github.com/TuyaInc/TY_Module_Android_Example) https://github.com/TuyaInc/TY\_Module\_Android\_Example中的版本配置

+ 示例如下：

```groovy
buildscript{
    repositories{
        maven {
            url'https://raw.githubusercontent.com/TuyaInc/mavenrepo/master/releases'
        }
    }
    dependencies{
        classpath 'com.tuya.smart:tyODM-config:@{version}'
    }
}

apply plugin:'TuyaODMRoot'
repInfo{
    userName = "your git name"
    password = "your git password"
    repUrl = "your git url"
}
```

### 2.配置组件构建插件

当你的组件需要上传到涂鸦构建仓库时，在你的组件 `build.gradle` 文件中使用如下的插件，并填写必要的信息：

```groovy
apply plugin: 'com.android.library'
apply plugin:'TuyaODM' //确保放在 'com.android.library'插件之后

moduleInfo{
    //以下为必填字段
    name // 组件名称
    moduleGroup //组件分组，比如com.xxx.android
    version // 组件版本
    descName //组件展示名称，用于前台展示
    //以下为非填字段
    releaseBundle = true //是否打出release的包，默认为 true
    hasPomDepend = true //是否上传依赖关系，默认为true
}
```

上述的 `name` ，`moduleGroup` 和 `version` 组成了 `maven` 的依赖引用格式 `moduleGroup:name:version`。

还有，我们默认你传递上去的都是 `release` 包，也就是说，不能传递一样的 `version` 到远端，我们会在打包时做检查。

### 3.打包组件并上传

当配置好前面的信息后，就可以进行打包上传了。你可以使用 `gradle` 命令进行上传:

Mac 或 Linux请使用：

```groovy
./gradlew projectName:uploadTuyaModule
```

Windows 请使用:

```groovy
./gradlew.bat projectName:uploadTuyaModule
```

### 4.有依赖关系的多组件上传

如果你在本地有两个组件，`A` 和 `B` 组件，而且 `A` 依赖于 `B` ，那么你需要将两个组件都上传到涂鸦的仓库中，而且上传的顺序应该是先上传 `B` ，在上传 `A` 。

在 `A`  的依赖中，你可能使用如下的形式对组件`B`  进行引用:

```groovy
dependencies{
    implementation project(':B')
}
```

那么，你只要先上传 组件 `B`，上传成功后，再上传 `A`组件，在正式打 `APK` 包时`A` 才实现了对`B`的引用，否则涂鸦打包平台将无法索引到 `B` 组件。