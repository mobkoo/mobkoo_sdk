package com.project.mobkoosdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobkoo.MobKooHelp;
import com.mobkoo.MobKooLoginCallBack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected ImageView btnBack;
    protected Button btnLogin;
    protected Button btnPay;
    private MobKooLoginCallBack mobKooLoginCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initCallBack();
        initView();
    }

    private void initCallBack() {
        mobKooLoginCallBack = new MobKooLoginCallBack() {
            @Override
            public void loginSuccess(String s) {
                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void loginFail(String s) {
                Toast.makeText(MainActivity.this, "Login Error=" + s, Toast.LENGTH_LONG).show();
            }
        };
    }

    public void getUserInfo() {
        MobKooHelp.singleton().getUserInfo(this, mobKooLoginCallBack);
    }

    public void LogOut() {
        MobKooHelp.singleton().logout(this);
    }

    Long time;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back) {
            if (System.currentTimeMillis() - time > 1000) {
                Toast.makeText(this, "再次点击退出登录", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
            time = System.currentTimeMillis();
        } else if (view.getId() == R.id.btn_login) {
            MobKooHelp.singleton().login(this, mobKooLoginCallBack);
        } else if (view.getId() == R.id.btn_pay) {
            startActivity(new Intent(this, PayActivity.class));
        }
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(MainActivity.this);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(MainActivity.this);
        btnPay = (Button) findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MobKooHelp.singleton().onActivityResult(requestCode, resultCode, data);
    }
}
