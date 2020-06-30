package com.project.mobkoosdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobkoo.MobKooLoginCallBack;
import com.mobkoo.MobKooManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected ImageView btnBack;
    protected Button btnLogin;
    protected Button btnPay;
    protected Button btnGetUserInfo;
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
      MobKooManager.getInstance().getUserInfo(this, mobKooLoginCallBack);
    }

    public void login() {
         MobKooManager.getInstance().login(this, mobKooLoginCallBack);

    }

    public void LogOut() {
        MobKooManager.getInstance().logout(this);
    }

    void pay() {
        startActivity(new Intent(this, PayActivity.class));
    }

    long time=0L;

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
            login();
        } else if (view.getId() == R.id.btn_pay) {
            pay();
        } else if (view.getId() == R.id.btn_getUserInfo) {
            getUserInfo();
        }
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(MainActivity.this);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(MainActivity.this);
        btnPay = (Button) findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(MainActivity.this);
        btnGetUserInfo = (Button) findViewById(R.id.btn_getUserInfo);
        btnGetUserInfo.setOnClickListener(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         MobKooManager.getInstance().onActivityResult(requestCode, resultCode, data);
    }
}
