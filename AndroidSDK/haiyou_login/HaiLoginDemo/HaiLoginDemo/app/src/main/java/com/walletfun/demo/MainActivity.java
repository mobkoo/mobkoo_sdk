package com.walletfun.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.walletfun.login.ui.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sdk_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sdk_login:
                startActivity(new Intent(this,LoginSdkActivity.class));
                break;
        }
    }
}
