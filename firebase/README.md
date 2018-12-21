<center><font size=12>firebase 文档</font></center>





[TOC]

# 一、说明

firebaseSDK   分享 和 云推送

需要文件 ：

 1）google-sevices.json文件

声明  ：

本文档为android 文档  如接入其他平台请去下发官网查看

# 二、官网文档地址

firebase 官方文档 ：https://firebase.google.cn/docs/android/setup



# 三、手动添加 Firebase

1、 在项目app目录下添加 `google-services.json` 文件



2、向您的根级 build.gradle 文件添加规则，以纳入 google-services 插件和 Google 的  Maven 代码库：

```
buildscript {
    // ...
    dependencies {
        // ...
        classpath 'com.google.gms:google-services:4.1.0' 
    }
}

allprojects {
    // ...
    repositories {
        // ...
        google() // Google's Maven repository
    }
}
```



3、 导入firebaseSDK

  

方法一 、在您的模块 Gradle 文件（通常是 `app/build.gradle`）中，在文件的底部添加 `apply plugin` 代码行，以启用 Gradle 插件：

```plu
apply plugin: 'com.android.application'


dependencies {
 
  implementation 'com.google.firebase:firebase-core:16.0.1'
   implementation 'com.google.firebase:firebase-messaging:17.3.4'
}

// ADD THIS AT THE BOTTOM
apply plugin: 'com.google.gms.google-services'
```

​    

方法二、 引用本地jar 包
将压缩包中 libs 中文件复制到你项目libs中
导入af-android-sdk.jar文件
项目中引用 aar 包 在 android 同级标签中增加以下代码
repositories { flatDir { dirs 'libs' }

引入

```php+HTML
apply plugin: 'com.android.application'


dependencies {
 
        implementation(name: 'firebase-analytics-16.0.4', ext: 'aar')
        implementation(name: 'firebase-analytics-impl-16.2.2', ext: 'aar')
        implementation(name: 'firebase-common-16.0.3', ext: 'aar')
        implementation(name: 'firebase-core-16.0.4', ext: 'aar')
        implementation(name: 'firebase-iid-17.0.3', ext: 'aar')
        implementation(name: 'firebase-iid-interop-16.0.1', ext: 'aar')
        implementation(name: 'firebase-measurement-connector-17.0.1', ext: 'aar')
        implementation(name: 'firebase-measurement-connector-impl-17.0.2', ext: 'aar')
        implementation(name: 'firebase-messaging-17.3.4', ext: 'aar')

        implementation(name: 'play-services-ads-identifier-16.0.0', ext: 'aar')
        implementation(name: 'play-services-base-16.0.1', ext: 'aar')
        implementation(name: 'play-services-basement-16.0.1', ext: 'aar')
        implementation(name: 'play-services-measurement-base-16.0.3', ext: 'aar')
        implementation(name: 'play-services-measurement-api-16.0.2', ext: 'aar')
        implementation(name: 'play-services-stats-16.0.1', ext: 'aar')
        implementation(name: 'play-services-tasks-16.0.1', ext: 'aar')
}

// ADD THIS AT THE BOTTOM
apply plugin: 'com.google.gms.google-services'
```

​ # 四 、配置Firebase

1 、注册服务

```php+HTML
<!-- 云推送消息-->

<service
    android:name=".MyFirebaseMessagingService">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT"/>
    </intent-filter>
</service>
<!-- appsflyer 卸载监听   接入appsflyer 时使用-->
<service
    android:name="com.appsflyer.FirebaseInstanceIdListener">
    <intent-filter>
        <action android:name="com.google.firebase.INSTANCE_ID_EVENT"/>
    </intent-filter>
</service>
<!--设置通知图标-->
<meta-data
    android:name="com.google.firebase.messaging.default_notification_icon"
    android:resource="@mipmap/logo" />
```

2 、创建接收服务 继承 FirebaseMessagingService



```java
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(" firebaseonNewToken广播： ", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(" firebaseonNewToken广播： ", remoteMessage.toString());

    }
}
```