package com.example.test.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    protected TextView loginPau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_pau) {
            startActivity(new Intent(this, HYPayActivity.class));
        }
    }

    private void initView() {
        loginPau = (TextView) findViewById(R.id.login_pau);
        loginPau.setOnClickListener(MainActivity.this);
    }
}
