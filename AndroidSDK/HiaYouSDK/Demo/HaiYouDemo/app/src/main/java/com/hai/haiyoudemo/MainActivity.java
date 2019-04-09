package com.hai.haiyoudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.walletfun.huawei.HuaWeiSDK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView toLogin;
    protected TextView toPay;
    protected TextView openHuawei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        HuaWeiSDK.instance().HuaWei_connect(this);
        initView();

    }

    boolean openHuaWei = false;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toLogin) {
            startActivity(new Intent(this, LoginMainActivity.class));

        } else if (view.getId() == R.id.toPay) {
            startActivity(new Intent(this, PayActivity.class));

        } else if (view.getId() == R.id.open_huawei) {
            openHuaWei = !openHuaWei;
            if (openHuaWei) {
                openHuawei.setText("华为模式(开)");
            } else {
                openHuawei.setText("华为模式(关)");
            }
            HuaWeiSDK.instance().HuaWei_openSDK(openHuaWei);

        }
    }

    private void initView() {
        toLogin = (TextView) findViewById(R.id.toLogin);
        toLogin.setOnClickListener(MainActivity.this);
        toPay = (TextView) findViewById(R.id.toPay);
        toPay.setOnClickListener(MainActivity.this);
        openHuawei = (TextView) findViewById(R.id.open_huawei);
        openHuawei.setOnClickListener(MainActivity.this);
    }
}
