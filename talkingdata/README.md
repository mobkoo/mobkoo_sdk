<center><font size=12>talkingdata 文档</font></center>





[TOC]

## 一、说明

talkingdata 游戏分析

需要参数 ：

 1） appID  


## 二、官网文档地址

talkingdata 官方文档 ：[http://doc.talkingdata.com/posts/64](http://doc.talkingdata.com/posts/64)

## 三、talkingdata使用

1、将压缩包中 libs 中文件复制到你项目lib

2、引用libs 中的talkingdata_gamesdk.jar文件



## 四 、配置及初始化

### 1、权限

```
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 
```

 权限说明

| 权限                           | 用途                                                         |
| :----------------------------- | :----------------------------------------------------------- |
| INTERNET                       | 允许程序联网和发送统计数据的权限                             |
| ACCESS_NETWORK_STATE           | 允许应用检测网络连接状态，在网络异常状态下避免数据发送，节省流量和电量。 |
| READ_PHONE_STATE               | 允许游戏以只读的方式访问手机设备的信息，通过获取的信息来定位唯一的玩家。 |
| ACCESS_WIFI_STATE              | 获取设备的MAC地址。                                          |
| WRITE_EXTERNAL_STORAGE         | 用于保存设备信息，以及记录日志。                             |
| ACCESS_FINE_LOCATION（可选）   | 用来获取该游戏被使用的精确位置信息                           |
| ACCESS_COARSE_LOCATION（可选） | 用来获取该应用被使用的粗略位置信息。                         |

###  2、初始化

在应用程序的 Application 类的 onCreate 方法中调用 TalkingDataGA.init

```java
public class MainApplication extends Application{
@Override 
public void onCreate() {
    super.onCreate();
    // App ID: 在TalkingData Game Analytics创建应用后会得到App ID。 
     TalkingDataGA.init(this, "您的 AppID", "play.google.com");
    }
}  
```

## 五 、使用

### 1、 基础-统计玩家帐户（必须）

  统计玩家账户接口，定义了一个玩家单元，用于更新玩家最新的属性信息

   建议用户每次登陆游戏 或切换账户时调用 

   如需了解更多属性 请[前往官网](http://doc.talkingdata.com/posts/64)查看

```java
TDGAAccount account ；
// 设置用户ID
account=TDGAAccount.setAccount(userID); 
// 设置用户名称
account.setAccountName("xiaoming@163.com"); 
// 设置账户类型
account.setAccountType(TDGAAccount.AccountType.REGISTERED);

```



如在一款类似愤怒小鸟的休闲游戏中，玩家直接进入进行游戏。 您可自行决定唯一ID规则，如设备IMEI、uuid等，也可以使用TalkingData提供的设备唯一ID接口。调用如下

```java
TDGAAccount account；
account=TDGAAccount.setAccount(TalkingDataGA.getDeviceId(context));

account.setAccountType(TDGAAccount.AccountType.ANONYMOUS);
 
```

### 2、基础-追踪玩家充值（重要）

玩家充值接口用于统计玩家充值现金而获得虚拟币的行为，充入的现金将反映至游戏收入中。
充值过程分两个阶段：

1、发出有效的充值请求；

2、确认某次充值请求已完成充值。

集成SDK时，在玩家发起充值请求时（例如玩家选择了某个充值包，进入支付流程那一刻）调用 onChargeRequest，并传入该笔交易的唯一订单ID和详细信息；在确认玩家支付成功时调用 onChargeSuccess，并告知所完成的订单ID。

```java
//充值请求
  /*
   * @orderId ：  订单ID 确保每次订单的orderID唯一
   * @iapId ： 商品名称
   * @currencyAmount ： 现金金额或现金等价物的额度
   * @currencyType ： 货币类型。例：人民币 CNY；美元 USD；欧元 EUR
   * @virtualCurrencyAmount ： 虚拟币金额
   * @paymentType ： 支付的途径，最多16个字符。例如：“支付宝”、“苹果官方”、“XX 支付SDK
   * 
   */
public static void onChargeRequest(String orderId, String iapId, double currencyAmount, String currencyType, double virtualCurrencyAmount, String paymentType)
//充值成功
  /*
   * @orderId ：  订单ID 确保与onChargeRequest中的orderID 是同一个参数
   */
public static void onChargeSuccess (String orderId)
```

示例： 玩家使用支付宝方式成功购买了“大号宝箱”（实际为100元人民币购入1000元宝的礼包），该笔操作的订单编号为account123-0923173248-11。可以如下调用：

```java
//在向支付宝支付SDK发出请求时，同时调用：
TDGAVirtualCurrency.onChargeRequest("account123-0923173248-11", "大号宝箱", 100, "CNY", 1000, "AliPay");
//订单account123-0923173248-11充值成功后调用：
TDGAVirtualCurrency.onChargeSuccess("account123-0923173248-11");
```

### 3、基础-追踪获赠的虚拟币（可选）

```java
//赠予虚拟币
  /*
   * @currencyAmount：虚拟币金额
   * @reason ： 赠送虚拟币原因/类型
   * 
   */
public static void onReward (double currencyAmount, String reason)

//示例 
//TDGAVirtualCurrency.onReward(15, “新手奖励”);
```

### 4、基础-追踪游戏消费点（可选）

此方法用于追踪游戏中全部使用到虚拟币的消费点，如购买虚拟道具、VIP服务、复活等，用于追踪某物品或服务的消耗。
在任意消费点发生时尽快调用onPurchase，在某个道具/服务被用掉（消失）时尽快调用 onUse。

```java
//记录付费点
public static void onPurchase(String item, int itemNumber, double priceInVirtualCurrency)
//消耗物品或服务等
public static void onUse(String item, int itemNumber)
```

### 5、高级功能-自定义事件

自定义事件用于统计任何您期望去追踪的数据，如：点击某功能按钮、填写某个输入框、触发了某个广告等。
开发者可以自行定义eventId，在游戏中需要追踪的位置进行调用，注意eventId中仅限使用中英文字符、数字和下划线，不要加空格或其他的转义字符。

```java
//在游戏程序的event事件中加入下面的代码，也就成功的添加了一个简单的事件到您的游戏程序中   
  /*
   * @eventId：自定义事件名
   * @eventData ： 自定义事件参数
   * 
   */
TalkingDataGA.onEvent (String eventId, final Map<String, String> eventData);

```

