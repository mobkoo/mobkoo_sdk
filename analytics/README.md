<center><font size=12>facebook 文档</font></center>





[TOC]

# 一、说明

facebook 文档 

参数

1） facebook ID 

# 二、官网文档地址

官方文档 ：https://developers.facebook.com/docs/app-events/getting-started-app-events-android



# 三、在 Android 应用中集成 Facebook SDK

一  、通过Gradle 导入facebookSDK

1、在您的项目中，打开 <your_app> | Gradle Scripts | build.gradle (Project) 并将以下代码添加到 buildscript { repositories {}} 部分，以便从 Maven 中央存储库下载 SDK：

   mavenCentral()

2、在您的项目中，打开 <your_app> | Gradle Scripts | build.gradle (Module: app) 并将以下代码添加到 dependencies{} 部分，以便编译最新版本的 SDK：

implementation 'com.facebook.android:facebook-android-sdk:[4,5)'

二、 通过本地jar包导入 facebookSDK

1、将压缩包中 libs 中文件复制到你项目libs中

2、引用libs用的所有jar文件

3、项目中引用 aar 包 在 android 同级标签中增加以下代码
repositories { flatDir { dirs 'libs' }

引用

```

implementation(name: 'facebook-core', ext: 'aar')
implementation(name: 'facebook-common', ext: 'aar')
implementation(name: 'cardview-v7-28.0.0', ext: 'aar')

```

  


# 四 、配置facebook

1、打开您的 /app/res/values/strings.xml 文件并添加以下代码：

```xml​ 
<string name="facebook_app_id">[APP_ID]</string> 

<string name="fb_login_protocol_scheme">fb[APP_ID]</string>
```

2、添加权限

```​ 
      
<user-permission android:name="android.permission.INTERNET"/> 
```



3 、在Manifest.xml 添加 以下内容


​           

       <!--   配置facebookAPPID-->
           <meta-data
                android:name="com.facebook.sdk.ApplicationId"
                android:value="@string/facebook_app_id" />
       <!--自动事件记录开关-->
           <meta-data
                android:name="com.facebook.sdk.CodelessDebugLogEnabled"
                android:value="true" />
       
       <provider
            android:name="com.facebook.FacebookContentProvider"
            android:authorities="com.facebook.app.FacebookContentProvider[APP ID]"
            android:exported="true" />
         
        <activity
      android:name="com.facebook.FacebookActivity"
      android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
      android:label="@string/app_name" />
       
​ 

# 五、 事件监听

自定义事件  java 代码

```java
AppEventsLogger logger = AppEventsLogger.newLogger(this); 

public void logEVENTNAMEEvent (String string) {
    Bundle params = new Bundle();
    params.putString("参数",string);
    logger.logEvent("自定义事件名", params);
}
```

自定义事件  unity 代码

```unity
public void LogEwrEvent (string string) {
    var parameters = new Dictionary<string, object>();
    parameters["参数"] = string;
    FB.LogAppEvent(
        "自定义事件名",
        parameters
    );
}
```

如需监听其他事件（或标准事件）  可以使用官网的代码生成器生成代码