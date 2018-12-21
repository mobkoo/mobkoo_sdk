<h1><center>H5版的支付SDK</center></h1>

# 目录

[TOC]

# 更新记录

| 版本号 |  更新日期  | 更新日志 |
| :----: | :--------: | :------: |
|  1.0   | 2018-12-14 | 初始版本 |

# 对接说明

## 请求方式

采用http标准的GET请求方式

## 请求参数

| 字段         | 类型   | 是否必传 | 描述                                                         | 示例值        |
| ------------ | ------ | -------- | ------------------------------------------------------------ | ------------- |
| lang         | string | 是       | 语言信息，目前只支持英文                                     | en            |
| price        | float  | 是       | 价格                                                         | 321           |
| currency     | string | 是       | 货币种类（海游网络提供）                                     | USD           |
| appid        | string | 是       | 应用ID（海游网络提供）                                       | 8             |
| key          | string | 是       | 应用对应的密钥（海游网络提供）                               | 8             |
| out_order_id | string | 是       | 商户订单号<br />（在支付完成之后会通过服务器回传）           | 12345         |
| product_id   | string | 是       | 产品id<br />（在支付完成之后会通过服务器回传）               | 6789          |
| sandbox      | int    | 是       | 当前环境，<br />1=沙盒环境，0=正式环境<br />（在支付完成之后会通过服务器回传） | 1             |
| time         | int    | 是       | 时间戳，当前时间毫秒数，<br />防止url相同时的页面缓存        | 1544760000000 |

## 示例URL

http://pay.walletfun.com/web/paysdk/pay/payway.html?lang=en&price=321&currency=USD&appid=8&key=31hriti1l33zc2nk&out_order_id=12345&product_id=6789&sandbox=1&time=1544760000000



## demo的URL

http://pay.walletfun.com/web/paysdk/



