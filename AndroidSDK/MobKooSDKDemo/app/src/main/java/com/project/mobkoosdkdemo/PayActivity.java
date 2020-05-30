package com.project.mobkoosdkdemo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobkoo.MobKooHelp;
import com.mobkoo.MobKooPayCallBack;



public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_actiivty);
        initGroupButton();
        initPay();
    }


    private void initPay() {
        findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobKooHelp.singleton().StartPay(PayActivity.this, "monthly_member", 1.0, "userid=1", new MobKooPayCallBack() {
                    @Override
                    public void paySuccess(String s) {
                        Toast.makeText(PayActivity.this, "支付成功：" + s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void payFail(String s) {
                        Toast.makeText(PayActivity.this, "支付失败：" + s, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void initGroupButton() {
        RadioGroup payType = findViewById(R.id.group_payType);
        payType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.type_all:
                        MobKooHelp.singleton().setPayType(MobKooHelp.PAY_TYPE_ALL);
                        break;
                    case R.id.type_mobkoo:
                        MobKooHelp.singleton().setPayType(MobKooHelp.PAY_TYPE_MOBKOO);
                        break;
                    case R.id.type_google:
                        MobKooHelp.singleton().setPayType(MobKooHelp.Pay_TYPE_GOOGLE);
                        break;

                }
            }
        });
        RadioGroup mSandbox = findViewById(R.id.group_Sandbox);
        mSandbox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sancbox_close:
                        MobKooHelp.singleton().setSandbox(false);
                        break;
                    case R.id.sancbox_open:
                        MobKooHelp.singleton().setSandbox(true);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MobKooHelp.singleton().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MobKooHelp.onDestory(this);
    }
}
