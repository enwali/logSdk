# 项目简介

本 SDK 支持动态配置服务器、开启日志，分享日志功能



<img src="https://raw.githubusercontent.com/enwali/logSdk/main/image/Screenshot_20220409_143212.png"  width=30%  /> <img src="https://raw.githubusercontent.com/enwali/logSdk/main/image/Screenshot_20220409_143141.png"  width=30%  /> <img src="https://raw.githubusercontent.com/enwali/logSdk/main/image/Screenshot_20220409_143303.png" width=30%  />

### 下载

由于还未将项目上传到maven，需要<a  href ="https://github.com/enwali/logSdk/raw/main/DemoApp/libs/LeeLenLogSDK_V1.0_20220409.aar">下载aar包</a> ，添加到项目 

添加 aar 到项目

```kotlin

dependencies {
    implementation 'org.jetbrains.kotlin:kotlin-reflect:1.6.10'
    implementation(name: 'LeeLenLogSDK_V1.0_20220409', ext: 'aar')
}

```

### 使用
在Applation初始化SDK

```
open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //初始化日志
        LogHelper.initLog(this)

        //设置默认服务器地址，可添加备选服务器列表 List<ServerBean>
        ServerHelper.setDefaultServer(
            "http://iot.leelen.com",
            "ssl://iot.leelen.com:8883"
        )
    }
}
```

启动设置配置页面

```
startActivity(Intent(this, LogExtSettingActivity::class.java))
```

获取当前缓存的配置信息,初始化网络请求工具时使用缓存的服务器地址

```
val config = SpModel.config

//当前是否开启了调试日志
val useLogcat = config?.useLogcat ?: false
//当前是否开启了保存日志到文件
val saveToFile = config?.saveToFile ?: false

//Http服务器地址
val baseUrl = RELEASE_BASE_URL
//Mqqt服务器地址
val mqttUrl = RELEASE_MQTT_HOST
```


### 使用其他日志库
SDK集成了log4j，无需集成其他log工具。如果有使用其他log工具的需求，可以通过 SpModel.config 获取用户是否设置了开启调试日志及保存日志到文件。
分享日志文件需要将日志文件保存在context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) 文件夹下
```
private fun getFileName(context: Context): String {
    var fileDocuments: File? = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    return fileDocuments.toString()
}
```