package com.example.test.haiyouanalysis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haiyou_analysis.HaiYouAnalysisSDK;
import com.walletfun.common.app.WalletHelp;


public class MainActivity extends Activity implements View.OnClickListener {

    protected Button login;
    protected Button register;
    protected Button payStart;
    protected Button paySuc;
    protected Button pauCal;
    protected Button btn5;
    private String TAG = "统计 ";

    /**
     * 具体属性值 请根据个应用来确定  以下参数仅供测试使用
     */
    public String orderID = "goodsorder123456";
    public String goodsName = "haiyou_goods";
    public double currencyAmount = 0;
    public String currencyType = "USD";
    public String paymentType = "支付宝";

    public String appID = "8";
    public String key = "31hriti1l33zc2nk";

    private static final String talkingData_APPID = "E96330E98A0F43E3918E43C444E3D7C2";
    private static final String appsfly_DEVKEY = "dz2eqVEhio3jEBLnTwkUw4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
        /* 启动 直接调用初始化*/
        initSDK();
    }

    private void initSDK() {
        WalletHelp.addSDK(HaiYouAnalysisSDK.instance());
        HaiYouAnalysisSDK.instance().setAnalysisKey(this.getApplication(), appsfly_DEVKEY, talkingData_APPID, getString(R.string.facebook_app_id));
        WalletHelp.init(this, appID, key, WalletHelp.SANDBOX_CLOSE);
        WalletHelp.setEnvDebug(true);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.login) {
            /**
             *  获取用户信息后调用
             * @useID :用户ID  不需要账号系统时 传 ”“
             * @Platform : 登录平台名称
             */
            String userID = "123";
            HaiYouAnalysisSDK.instance().user_loginIn(this, userID, "haiyou");
        } else if (view.getId() == R.id.register) {

            /**
             *  注册时
             * @useID :用户ID  不需要账号系统时 传 ”“
             * @Platform : 登录平台名称
             */
            String userID = "123";
            HaiYouAnalysisSDK.instance().user_registration(this, userID, "haiyou");

        } else if (view.getId() == R.id.pay_start) {

            HaiYouAnalysisSDK.instance().Pay_checkout(this, orderID, goodsName,
                    currencyAmount, currencyType,
                    paymentType);

        } else if (view.getId() == R.id.pay_suc) {
            /**
             * 支付成功后调用
             * @orderId ：  订单ID 确保每次订单的orderID唯一
             * @iapId ： 商品名称
             * @currencyAmount ： 现金金额或现金等价物的额度
             * @currencyType ： 货币类型。例：人民币 CNY；美元 USD；欧元 EUR
             * @paymentType ： 支付的途径，最多16个字符。例如：“支付宝”、“苹果官方”、“XX 支付SDK*/

            HaiYouAnalysisSDK.instance().Pay_checkout(this, orderID, goodsName,
                    currencyAmount, currencyType,
                    paymentType);

        } else if (view.getId() == R.id.pau_cal) {

            HaiYouAnalysisSDK.instance().Pay_cancelled(this, orderID, paymentType);

        } else if (view.getId() == R.id.btn5) {

        }
    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(MainActivity.this);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(MainActivity.this);
        payStart = (Button) findViewById(R.id.pay_start);
        payStart.setOnClickListener(MainActivity.this);
        paySuc = (Button) findViewById(R.id.pay_suc);
        paySuc.setOnClickListener(MainActivity.this);
        pauCal = (Button) findViewById(R.id.pau_cal);
        pauCal.setOnClickListener(MainActivity.this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(MainActivity.this);

    }
}
