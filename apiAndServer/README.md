<h1><center>服务器API接口文档</center></h1>

# 目录

[TOC]

# 更新记录

| 版本号 |  更新日期  |               更新日志               |
| :----: | :--------: | :----------------------------------: |
|  1.0   | 2018-11-05 |               初始版本               |
|  1.1   | 2018-11-15 |  增加sign签名字段，提高数据的安全性  |
|  1.2   | 2018-11-23 | 增加沙盒测试，回调增加是否是沙盒测试 |
|  1.3   | 2018-11-28 | 优化签名算法的说明，让描述更容易理解 |
|  1.4   | 2018-12-13 | 支付回调增加product_id（商品ID）字段 |
|  1.5   | 2019-05-30 |     支付增加商家返回链接参数说明     |
|  2.0   | 2020-05-13 |               架构调整               |

# 发起支付

## 请求方式

以HTTP 的GET请求进行跳转，或者通过html的form表单提交也行

## 请求地址

http://api.mobkoo.com/payout/redirect

## 请求参数

| 字段         | 类型   | 是否必填           | **描述**                                                     | **示例值** |
| ------------ | ------ | ------------------ | ------------------------------------------------------------ | ---------- |
| appid        | string | 必传               | Mobkoo分配给开发者的应用ID                                   | 123456     |
| out_order_id | string | 可选               | 商户订单号                                    | dasd45sa45 |
| country_id   | int    | 必传               | 国家ID                                                       | 1          |
| item_id      | int    | 必传               | 项目ID                                                       | 1          |
| price        | float  | 必传 | 支付价格(必须大于0)                                          | 10.00      |
| currency     | string | 必传               | 货币单位                                                     | USD        |
| sandbox      | int    | 必传               | 是否是沙盒环境<br />0=正式环境<br />1=沙盒环境 |            |
| description  | string | 可选               | 订单描述                                                     | 购买xx物品 |

# 支付回调



## 支付回调参数

当支付成功时，

回传的方式：GET

| 字段          | 类型   | 是否必填 | 描述                                                         | 示例值              |
| ------------- | ------ | -------- | ------------------------------------------------------------ | ------------------- |
| appid         | string | 是       | Mobkoo分配给开发者的应用ID                                   | 123456              |
| out_order_id  | string | 是       | 商户订单号                                                   | dasd45sa45          |
| order_id      | string | 是       | 支付订单号                                                   | 201809191dksd55     |
| price         | string | 是       | 支付价格                                                     | 1.00                |
| currency      | string | 是       | 支付货币单位                                                 | RMB                 |
| sandbox       | int    | 是       | 是否是沙盒环境<br />0=正式环境<br />1=沙盒环境               | 0                   |
| state         | string | 是       | 订单状态，<br />succ=支付成功，<br />fail=支付失败，<br />refund=退款 | succ                |
| error_msg     | string | 否       | 支付失败的说明                                               | error               |
| platform_type | string | 是       | 支付平台类型                                                 |                     |
| pay_time      | string | 是       | 支付时间                                                     | 2019-01-10 16:56:20 |
| sign          | string | 是       | 签名，md5(md5(待签名字符串)+签名的钥匙)<br />生成过程请参考"如何签名"章节 | e1f092ac91b5fcdf9   |




### 如何签名

1.筛选并排序

获取所有请求参数，不包括字节类型参数，如文件、字节流，剔除sign字段，并按照字符的键值ASCII码递增排序（字母升序排序）。



2.拼接

将排序后的参数与其对应值，组合成“参数=参数值”的格式，并且把这些参数用&字符连接起来，此时生成的字符串为待签名字符串。

```
举例：
请求的所有参数的如下
efg=dsadsdsad
abc=123456
bcd=ewqeaqewq
cde=ewqdsad
def=dsadsadsa


按照字符的键值ASCII码递增排序（字母升序排序）
并生成待签名字符串为：
abc=123456&bcd=ewqeaqewq&cde=ewqdsad&def=dsadsadsa&efg=dsadsdsad

签名的算法：
md5(md5(待签名字符串)+签名的钥匙)

如果签名的钥匙为：lnxMZjgeIGlouasj

则生成的最终的签名为：
eed8bebc84c37bc5ecb46ff89598bfea

```





## 响应数据

接收到数据并正常时，响应一个“ok”的字符串即可,若接口地址返回200以外的状态码,则会向该回调接口地址连续请求4次,依旧返回200以外的状态码后放弃请求

示例：

```html
ok
```





# 订单交易查询接口

商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。 

请求方式：GET/POST

## 请求地址

http://api.mobkoo.com/pay/common.CheckOrder/index

| 字段     | 类型   | 是否必填 | 描述                       | 示例值          |
| -------- | ------ | -------- | -------------------------- | --------------- |
| appid    | string | 是       | Mobkoo分配给开发者的应用ID | 123456          |
| order_id | string | 是       | 订单ID                     | 201809191dksd55 |



## 响应参数

| 字段                                  | 类型   | 是否必填 | 描述                                                         |
| :------------------------------------ | ------ | -------- | ------------------------------------------------------------ |
| code                                  | string | 是       | 返回的状态结果<br />200=成功，<br />400=失败 <br/>401:未查询到该订单 |
| msg                                   | string | 是       | 返回的具体信息                                               |
| data                                  | string | 否       | json数据集合                                                 |
| data.order_info.state                 | String | 是       | 订单状态 <br/>pending:支付中<br />succ:支付成功<br />fail:支付失败<br />refund:退款 |
| data.order_info.order_id              | String | 是       | 订单ID                                                       |
| data.order_info.out_order_id          | String | 否       | 商户订单号                                                   |
| data.order_info.game_currency_present | float  | 否       | 当地货币单位                                                 |
| data.order_info.price                 | float  | 是       | 价格                                                         |
| data.order_info.sandbox               | int    | 是       | 是否为沙盒环境,1代表沙盒环境                                 |
| data.order_info.platform_name         | String | 是       | 支付平台ID                                                   |

