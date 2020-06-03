# 查询游戏发货情况接口
|接口详情||
|-|-|
|请求方式|POST|

|查询参数|类型|描述|
|-|-|-|
|order_id|String|订单id|
|order_type|String|订单类型:"google_pay","apple_pay","mobkoo_pay"|

|返回参数|类型|描述|
|-|-|-|
|code|String|查询状态代码,"0000"代表已发货,其余代码均代表未发货|
|msg|String|查询具体信息,如"success","param error"|

#### 已发货返回信息(json)
```
{
    "code": "0000",
    "msg": "success"
}
```

#### 未返回信息(json)
```
{
    "code": "0001",
    "msg": "param error"
}
```


