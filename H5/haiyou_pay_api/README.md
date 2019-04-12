<h1><center>服务器API接口文档</center></h1>

# 目录

[TOC]

# 更新记录



| 版本号 |  更新日期  | 更新日志 |
| :----: | :--------: | :------: |
|  1.0   | 2018-11-28 | 初始版本 |

# 发起支付

## 请求方式

以HTTP 的GET请求进行跳转，或者通过html的form表单提交也行

## 请求地址

http://pay.walletfun.com/pay/common.Pay/index

## 请求参数

| 字段          | 类型   | 是否必填 | **描述**                                       | **示例值**      |
| ------------- | ------ | -------- | ---------------------------------------------- | --------------- |
| appid         | string | 是       | 海游分配给开发者的应用ID                       | 123456          |
| out_order_id  | string | 否       | 商户订单号（自定义参数）                       | dasd45sa45      |
| country_name  | string | 是       | 国家ID(小写)                               | indonesia       |
| mode_name     | string | 是       | 支付方式ID                                     | e_wallet        |
| platform_name | string | 是       | 支付平台ID                                 | true_money      |
| price         | float  | 是       | 支付价格(必须大于0)                            | 10.00           |
| monetary_unit | string | 是       | 货币单位                                       | IDR             |
| sandbox       | int    | 是       | 是否是沙盒环境<br />0=正式环境<br />1=沙盒环境(进行沙盒测试请先将测试的客户端IP地址通知我方运营进行添加) | 0               |
| description       | string    | 否      | 订单描述| 0               |

# 支付回调

支付回调请点击链接查看详情：[https://github.com/haiyouent/haiyoupay_sdk/tree/master/serverSDK/server](https://github.com/haiyouent/haiyoupay_sdk/tree/master/serverSDK/server)

