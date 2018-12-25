package com.walletfun.payex;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.walletfun.common.app.WalletHelp;
import com.walletfun.common.floatwindow.WalletFloatWindowListener;
import com.walletfun.pay.PayEntry;
import com.walletfun.pay.PayResult;
import com.walletfun.pay.WalletCallback;
import com.walletfun.pay.WalletPayHelper;
import com.walletfun.pay.WalletPaySDK;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.text_history)
    TextView textHistory;
    @BindView(R.id.edit_appid)
    EditText editAppid;
    @BindView(R.id.edit_key)
    EditText editKey;
    @BindView(R.id.edit_sandbox)
    EditText editSandBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        textHistory.setMovementMethod(ScrollingMovementMethod.getInstance());

        initWalletPaySdk();

    }


    /**
     * 初始化支付 sdk
     */
    private void initWalletPaySdk() {
        // 添加充值SDK
        WalletHelp.addSDK(new WalletPaySDK());
        //  设置应用环境 测试环境:true  线上环境:false
        // 测试环境第三方支付会始终打开，方便调试
        WalletHelp.setEnvDebug(false);
        // 设置用户 id ，可选择
        // WalletPaySDK.setUserId("abc");
        // 设置悬浮窗口状态回调
        initSDK(null, null, null);

        WalletHelp.addFloatListener(this, windowListener);

        textHistory.append("sdk init it.\n");
    }

    private WalletPayHelper payHelper;

    @OnClick({R.id.btn_query_last_pay, R.id.btn_show_float, R.id.btn_update, R.id.btn_init})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_float:
                WalletHelp.showFloat(this);
                textHistory.append("show float window.\n");
                // 支付回调监听
                if (payHelper == null) {
                    payHelper = new WalletPayHelper(this);
                    payHelper.setWalletCallback(walletCallback);
                    textHistory.append("监听支付回调!\n");
                }
                break;

            case R.id.btn_query_last_pay:

                if (payHelper != null) {
                    textHistory.append("查询上一次未完成支付\n");
                    payHelper.queryOrder();
                } else {
                    textHistory.append("payHelper is null.\n");
                }
                break;
            case R.id.btn_update:
                textHistory.append("更新用户信息：关卡：" + grade + " 等级：" + level);
                // 必须调用此方法，否则第三方支付默认为关闭状态
                WalletHelp.updatePlayer(String.valueOf(grade++), String.valueOf(level++));// 计时事件存在于SDK内部

                break;
            case R.id.btn_init:
                String appid = getEditString(editAppid);
                String key = getEditString(editKey);
                String sandBox = getEditString(editSandBox);
                initSDK(appid, key, sandBox);
                // 必须调用此方法，否则第三方支付默认为关闭状态
                grade = 0;
                level = 0;
                WalletHelp.updatePlayer(String.valueOf(grade++), String.valueOf(level++));// 计时事件存在于SDK内部
                break;
            default:

                break;
        }
    }

    private void initSDK(String appid, String key, String sandBox) {

        if (TextUtils.isEmpty(appid) || TextUtils.isEmpty(key) || TextUtils.isEmpty(sandBox)) {
            // 初始化 测试环境（测试使用需要修改）
            WalletHelp.init(this, "8", "31hriti1l33zc2nk", WalletHelp.SANDBOX_CLOSE);
            textHistory.append("初始化 ：APPID：" + 8 +      "   沙盒环境： 关闭");

        } else {
            if (sandBox.equals("1")) {
                // 沙盒环境打开  支付默认成功
                WalletHelp.init(this, appid, key, WalletHelp.SANDBOX_OPEN);
                textHistory.append("初始化 ：APPID：" + appid + "  KEY： " + key + "   沙盒环境： OPEN");
            } else {
                // 沙盒环境关闭   正常调用支付
                WalletHelp.init(this, appid, key, WalletHelp.SANDBOX_CLOSE);
                textHistory.append("初始化 ：APPID：" + appid + "  KEY： " + key + "   沙盒环境： CLOSE");
            }
        }
    }


    private String getEditString(EditText edit) {
        if (edit == null) return null;
        return edit.getText().toString();

    }

    // 关卡 - 参数 1
    private int grade;

    // 等级 - 参数 2
    private int level;

    // 累计游戏时长(单位:秒)，这里只做演示，未保存总时长
    private long times = System.currentTimeMillis();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (payHelper != null) {

            if (payHelper.handleActivityResult(requestCode, resultCode, data)) {
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        // 如果在 Application 中初始化则不需要，但是需要 WalletPaySDK.destroy();

        WalletHelp.onDestory(this);
        //
        if (payHelper != null) {
            payHelper.onDestroy();
        }
        super.onDestroy();
    }

    private WalletFloatWindowListener windowListener = new WalletFloatWindowListener() {

        @Override
        public void onWindowShow() {
            textHistory.append("悬浮窗展示\n");
        }

        public void onWindowDismiss() {
            textHistory.append("悬浮窗消失\n");
        }

        @Override
        public void onThirdPayChange(String buttonName, boolean open) {
            super.onThirdPayChange(buttonName, open);
            textHistory.append("第三支付打开状态:" + buttonName + "  " + open + "\n");

        }

        /**
         * 返回一个自定义的值，会在支付完成的时候一块回调
         * @return
         */
        public String onThirdPayClick() {
            String userID = "12345";
            textHistory.append("点击支付传入备注参数" + userID+ "\n");
            return userID;
        }
    };

    private WalletCallback walletCallback = new WalletCallback() {
        @Override
        public void onQueryResult(PayResult result) {
            textHistory.append("onQueryResult:" + result + "\n");
            if (result != null && result.isValid()) {
                switch (result.status) {
                    case PayResult.PAY_STATUS_PENDING:
                        // 支付中
                        textHistory.append("支付回调结果 :   " + "支付中"+ "\n");
                        break;
                    case PayResult.PAY_STATUS_SUCCESS:
                        // 支付成功
                        textHistory.append("支付回调结果 :   " + "支付成功"+ "\n");
                        break;
                    case PayResult.PAY_STATUS_FAIL:
                        // 支付失败
                        textHistory.append("支付回调结果 :   " + "支付失败"+ "\n");
                        break;
                    case PayResult.PAY_STATUS_REFUND:
                        // 已经退款
                        textHistory.append("支付回调结果 :   " + "已经退款"+ "\n");
                        break;
                    default:
                        // 未知状态，请重新查询或者从自己的服务器上获取数据
                        textHistory.append("支付回调结果 :   " + "未知状态"+ "\n");
                        break;
                }
            }
        }

        @Override
        public void onPayStart(PayEntry payEntry) {
            textHistory.append("onPayStart:" + payEntry + "\n");
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
