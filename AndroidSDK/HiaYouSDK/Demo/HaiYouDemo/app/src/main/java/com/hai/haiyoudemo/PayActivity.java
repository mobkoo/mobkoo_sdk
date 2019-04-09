package com.hai.haiyoudemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.walletfun.common.app.WalletHelp;
import com.walletfun.common.floatwindow.WalletFloatWindowListener;
import com.walletfun.common.util.LogUtils;
import com.walletfun.pay.api.HaiYouPayCallBack;
import com.walletfun.pay.api.HaiYouPayResult;
import com.walletfun.pay.ui.HaiYouPayHelp;

import cn.chrisx.google.pay.util.Purchase;

public class PayActivity extends AppCompatActivity implements View.OnClickListener, HaiYouPayCallBack {
    protected TextView btnToPlatfrom;
    protected EditText editMoney;
    protected TextView textHistory;
    protected CheckBox checkbox;
    HaiYouPayHelp haiYouPayHelp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.platform_activity);
        initView();
        textHistory.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (haiYouPayHelp == null) {
            haiYouPayHelp = new HaiYouPayHelp(this);
            haiYouPayHelp.addHaiYouPayCallBack(this);
        }

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    WalletHelp.setSandbox(WalletHelp.SANDBOX_OPEN);
                } else {
                    WalletHelp.setSandbox(WalletHelp.SANDBOX_CLOSE);
                }
            }
        });

        WalletHelp.addFloatListener(this, new WalletFloatWindowListener() {

        });
    }

    String vpn = "netuppr0001";
    String kuku2 = "com.kuku.token60";
    String xtby = "com.eye.fysgz.bag1";
    double xtby_money = 0.99;
    String currency = "USD";

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_toPlatfrom) {

            String s = editMoney.getText().toString();
            try {
                Double aDouble = Double.valueOf(s);
                if (aDouble != null) {
                    haiYouPayHelp.startPay(vpn, aDouble, "USD", "测试数据");
                }
            } catch (Exception e) {

            }
        } else if (view.getId() == R.id.google_one) {
            try {
                haiYouPayHelp.startPay(vpn, 0.15, currency, "测试数据");
            } catch (Exception e) {

            }
        } else if (view.getId() == R.id.btn_level) {

            try {
                level++;
                grade++;
                WalletHelp.updatePlayer(grade + "", level + "");
                textHistory.append("用户等级更新 :   " + "grade： " + grade + "    level： " + level + "\n");

            } catch (Exception e) {

            }
        }
    }

    int level = 0, grade = 0;

    private void initView() {
        btnToPlatfrom = (TextView) findViewById(R.id.btn_toPlatfrom);
        btnToPlatfrom.setOnClickListener(PayActivity.this);
        findViewById(R.id.google_one).setOnClickListener(this);
        findViewById(R.id.btn_level).setOnClickListener(this);
        editMoney = (EditText) findViewById(R.id.edit_money);
        textHistory = (TextView) findViewById(R.id.platform_text_history);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
    }

    @Override
    public void onQueryResult(HaiYouPayResult payResult) {
        LogUtils.e(" 订单详情： " + payResult.toString());
        textHistory.append("支付回调结果 :   " + "支付平台： " + payResult.platformName + "\n");
        textHistory.append("支付回调结果 :   " + "订单ID： " + payResult.orderId + "\n");
        textHistory.append("支付回调结果 :   " + "商品ID： " + payResult.productId + "\n");
        textHistory.append("支付回调结果 :   " + "支付金额： " + payResult.price + "\n");

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
                case HaiYouPayResult.PAY_STATUS_REFUND:
                    // 已经退款
                    textHistory.append("支付回调结果 :   \" + \"已经退款!\n");
                    LogUtils.e("支付回调结果 :   " + "已经退款");
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
    public void onGoogleResult(boolean state, Purchase purchase, String errorMessage) {
        textHistory.append("google支付 状态： state= " + state + " \n" +
                "google支付 错误信息: " + errorMessage + "\n" +
                "google支付 商品信息： " + (purchase == null ? " null" : purchase.toString()) + "\n");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (haiYouPayHelp != null) haiYouPayHelp.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        if (haiYouPayHelp != null) haiYouPayHelp.ondestory();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WalletHelp.onConfigurationChanged();
    }
}
