<center><font size=12>appsflyer 文档</font></center>





[TOC]

# 一、说明

appsflyer SDK 

需要参数 

 1 、AF_DEV_KEY（appsflyer key值）

# 二、官网文档地址

appsfly 官方文档 ：https://support.appsflyer.com/hc/zh-cn/articles/207032126-AppsFlyer-SDK-%E5%AF%B9%E6%8E%A5-Android





# 三、手动添加 appsflyerSDK

将 SDK 对接到您的项目的最简单的方式就是使用 Gradle 的 Dependency Management。如果支持Gradle 方法2

 如果您使用的不是 Gradle 请使用方法1

方法1、引用本地jar 包

将压缩包中 libs 中文件复制到你项目libs中

导入af-android-sdk.jar文件 

项目中引用 aar 包
在 android 同级标签中增加以下代码

repositories { flatDir { dirs 'libs' }

引用

```
implementation(name: 'installreferrer-1.0', ext: 'aar')
```



方法2、使用Gradle 引用appsfly库

```plu
 dependencies {
	implementation 'com.appsflyer:af-android-sdk:4+@aar'
	implementation 'com.android.installreferrer:installreferrer:1.0'
}

```

​  

# 四 配置文件
 1、 权限 
```java
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- Optional : -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```



2、设置 BroadcastReceiver

	<receiver android:name="com.appsflyer.SingleInstallBroadcastReceiver"        android:exported="true">
		<intent-filter>
			 <action android:name="com.android.vending.INSTALL_REFERRER" />
		 </intent-filter>
	</receiver>



# 五 初始化

```java
public class AFApplication extends Application {
   private static final String AF_DEV_KEY = "";
   @Override
   public void onCreate() {
       super.onCreate();
       
       AppsFlyerConversionListener conversionDataListener = 
       new AppsFlyerConversionListener() {
           ...
       };
       AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionDataListener, getApplicationContext());
       AppsFlyerLib.getInstance().startTracking(this);   }
}
```



# 六 配置用户IMEI 和 androidID


```java
AppsFlyerLib.getInstance().setImeiData("IMEI_DATA_HERE");
AppsFlyerLib.getInstance().setAndroidIdData("ANDROID_ID_DATA_HERE");
AppsFlyerLib.getInstance().startTracking(getApplication(),AF_DEV_KEY);
```

