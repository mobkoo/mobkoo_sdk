#### 检查登录状态接口
<table>
	<tr>
		<td>传参方式</td>
		<td>GET/POST</td>
	</tr>
</table>
<table>
	<tr>
		<td>接口地址</td>
        <td>http://api.haiyoupay.com/pay/user.Check/check_login</td>
	</tr>
</table>



##### 传递参数
|字段|类型|描述|
|-|-|-|
|token|String|token登录标记|
|appid|String|应用id|
|device_id|String|设备id|
|user_id|int|用户id|

##### 返回参数
|字段|类型|描述|
|-|-|-|
|code|int|登录成功返回200,未登录返回400|
|msg|String|返回的具体信息|

##### success response
```
{
    "code": 200,
    "msg": "Already logged in",
    "data": {}
}
```

##### error response
```
{
    "code": 400,
    "msg": "Login timeout, please login again",
    "data": {}
}
```



