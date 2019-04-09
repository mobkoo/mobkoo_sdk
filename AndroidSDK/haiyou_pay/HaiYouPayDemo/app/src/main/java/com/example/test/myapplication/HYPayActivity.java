package com.example.test.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.paysdk.api.HaiYouPayCallBack;
import com.example.paysdk.api.HaiYouPayResult;
import com.example.paysdk.ui.HaiYouPayHelp;
import com.example.paysdk.ui.HaiYouPaySDK;
import com.walletfun.common.app.WalletHelp;
import com.walletfun.common.floatwindow.WalletFloatWindowListener;
import com.walletfun.common.util.LogUtils;
import cn.chrisx.google.pay.util.Purchase;

/**
 *
 */
public class HYPayActivity extends AppCompatActivity implements View.OnClickListener {
    protected TextView btnToPlatfrom;
    protected EditText editMoney;
    protected TextView textHistory;
    protected TextView btnUpdate;
    HaiYouPayHelp haiYouPayHelp;

    /*
     *  发起购买时的参数 请根据自己商品配置
     *  goosID:google      商品ID
     *  currency :         商品价格单位
     *  out_order_id ：    自定义额外参数
     *  money :            商品价格
     *  美元： USD  、韩币：KRW 、人民币：CNY*/

    String googsID = "netuppr0001", currency = "USD", out_order_id = "haiyoupay";


    /*    初始化时的参数
     *    APPID ：          应用ID   用于区分不同应用,
     *    KEY ：            应用秘钥  用于接口数据加密 ;
     *    Sandbox：         沙盒模式：
     *   googlePulicKey：   google支付公钥
     */
    private static final String googlePulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs5e/tEnbofvNdLqhrPt+PHil0Yo5QLBV5EMzhNpzFrWX/9bViwsiPqcr7l3RxAQHDeNc67j/R64AF9e3SE06Y3RCHvHjPvc7M/wR7zpNHWDtHHDZ4bgOt7zTZz6YqhyaW6KNam08J16yZZEKI/EYjjDhxLXsn4rutblUIneTl17f0bHIV37pVTYLnp4e4oBvwKCmfUFy8TGSp12gfNUFPCcI9F1WnpMJ7XfGNce3MXq9jwkuf0aqXnyewDSTlKbRxKhmHPc1fJz5bXNMCjGkCpnoIYhmhIlsfO5ICWLapaH5C2jCwT2Xz4F2ZsIoIr86BTNdu8VvK2Psiai0Ni5znwIDAQAB";
    String APPID = "8", KEY = "31hriti1l33zc2nk";
    int Sandbox = WalletHelp.SANDBOX_CLOSE;
    // 更新用户信息时调用的参数 grade:关卡 level: 等级
    int grade = 0, level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_haiyoupay);
        initView();
        initHaiYouPay();
        initHaiyouCallBack();
        textHistory.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    private void initHaiyouCallBack() {

        haiYouPayCallBack = new HaiYouPayCallBack() {
            @Override
            public void onQueryResult(HaiYouPayResult payResult) {
                LogUtils.e(" 订单详情： " + payResult.toString());
                textHistory.append("支付订单号 :   " + payResult.orderId + " !\n");

                if (payResult != null && payResult.isValid()) {
                    switch (payResult.status) {
                        case HaiYouPayResult.PAY_STATUS_PENDING:
                            // 支付中
                            textHistory.append("支付回调结果 :   " + "支付中!\n");
                            LogUtils.e("支付回调结果 :   " + "支付中");
                            break;
                        case HaiYouPayResult.PAY_STATUS_SUCCESS:
                            // 支付成功
                            textHistory.append("支付回调结果 :   \" + \"支付成功!\n");
                            LogUtils.e("支付回调结果 :   " + "支付成功");
                            break;
                        case HaiYouPayResult.PAY_STATUS_FAIL:
                            // 支付失败
                            textHistory.append("支付回调结果 :   \" + \"支付失败!\n");
                            LogUtils.e("支付回调结果 :   " + "支付失败");
                            break;
                        default:
                            textHistory.append("支付回调结果 :   \" + \"未知状态!\n");
                            // 未知状态，请重新查询或者从自己的服务器上获取数据
                            LogUtils.e("支付回调结果 :   " + "未知状态");
                            break;
                    }
                }
            }

            @Override
            public void onPayStart(String orderId) {
                textHistory.append("点击支付： orderId= " + orderId + " \n");
                LogUtils.e(" 点击支付： orderId= " + orderId);
            }

            @Override
            public void onGoogleResult(boolean b, Purchase purchase, String s) {
                textHistory.append("google支付： 支付状态= " + b + " \n" + "   支付信息： " + s + " \n");
                textHistory.append("google支付： 支付信息purchase = " + (purchase == null ? "null" : purchase.toString()) + " \n");
            }
        };
    }

    /**
     * 初始化支付
     */
    private void initHaiYouPay() {
        // 在公共模块增加HaiYouPaySDK
        WalletHelp.addSDK(HaiYouPaySDK.instance());
        // 设置google play 支付公钥
        HaiYouPaySDK.setGooglePublicKey(googlePulicKey);
        // 设置 运行环境  正式上线时需要设置为false
        WalletHelp.setEnvDebug(true);
        // 初始化 参数： context  AppID  key: 秘钥  sandbox:沙盒开关 （上线时一定要设置为SANDBOX_CLOSE）
        WalletHelp.init(this, APPID, KEY, Sandbox);
        // 请求第三方支付权限 在等级或关卡发生变化时调用
        // 该demo
        WalletHelp.showFloat(this);


        WalletHelp.updatePlayer(grade + "", level + "");

        WalletHelp.addFloatListener(this, new WalletFloatWindowListener() {
            @Override
            public void onThirdPayChange(String buttonName, boolean open) {
                super.onThirdPayChange(buttonName, open);
                LogUtils.e(" 第三方支付权限开发状态： " + open);
                textHistory.append(" 第三方支付权限开发状态： " + buttonName + open + "!\n");
            }
        });


    }

    /**
     * 设置支付模式 默认模式 ALl
     *  HaiYouPaySDK.ALL : google和第三方支付
     *  HaiYouPaySDK.ONLYHAIYOUPAY ： 只支持第三方支付
     *  HaiYouPaySDK.ONLYGOOGLE  ： 只支持google 支付
     */
    public void setPayMode() {

        HaiYouPaySDK.setPayMode(HaiYouPaySDK.ALL);

    }


    /**
     * @param Type 支付类型
     */
    public void setPayModle(int Type) {

        /* 设置支付模块 参数：
         *  HaiYouPaySDK.ALL           //  支持google支付和还有第三方支付
         *  HaiYouPaySDK.ONLYGOOGLE : // 只支持 google支付
         *  HaiYouPaySDK.ONLYHAIYOUPAY // 只支持 haiyou第三方支付 不支持google
         */
        HaiYouPaySDK.setPayMode(HaiYouPaySDK.ALL);
    }


    /**
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_toPlatfrom) {
            // 开始支付
            if (haiYouPayHelp == null) {
                // 如果工具类为空 实例化工具类
                haiYouPayHelp = new HaiYouPayHelp(this);
                haiYouPayHelp.addHaiYouPayCallBack(haiYouPayCallBack);
            }

            String s = editMoney.getText().toString();
            try {
                //设置
                Double aDouble = Double.valueOf(s);
                if (aDouble != null) {
                    // 调用支付 参数 goodsID 商品ID  money 商品价格  currency 商品价格单位 extraString 额外参数
                    // 商品ID 为google商品ID时 能正常使用google支付
                    double money = aDouble;
                    haiYouPayHelp.startPay(googsID, money, currency, out_order_id);
                } else {
                    textHistory.append("请数据支付金额！\n");
                }
            } catch (Exception e) {
                textHistory.append("金额格式不正确（double）！\n");
            }

        } else if (view.getId() == R.id.btn_update) {
            ++level;
            ++grade;
            WalletHelp.updatePlayer(grade + "", level + "");
            textHistory.append("grade:" + grade + "    " + "level:" + level);

        }
    }

    HaiYouPayCallBack haiYouPayCallBack = null;

    private void initView() {
        btnToPlatfrom = (TextView) findViewById(R.id.btn_toPlatfrom);
        btnToPlatfrom.setOnClickListener(HYPayActivity.this);
        editMoney = (EditText) findViewById(R.id.edit_money);
        textHistory = (TextView) findViewById(R.id.platform_text_history);
        btnUpdate = (TextView) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(HYPayActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (haiYouPayHelp != null) haiYouPayHelp.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        WalletHelp.onDestory(this);
        haiYouPayHelp.ondestory();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WalletHelp.onConfigurationChanged();
            }
        });
    }
}
