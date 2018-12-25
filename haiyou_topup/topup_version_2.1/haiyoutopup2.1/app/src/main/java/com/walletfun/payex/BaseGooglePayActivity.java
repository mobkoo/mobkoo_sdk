package com.walletfun.payex;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.jzfox.google.pay.GoogleBill;
import net.jzfox.google.pay.GoogleBillingCallback;
import net.jzfox.google.pay.OnQueryInventoryCallback;
import net.jzfox.google.pay.util.Purchase;
import net.jzfox.google.pay.util.SkuDetails;

import java.util.Map;

/**
 * @author Jzfox <jz_boy@163.com>
 * @version 1.0
 * @date 2018/9/20 14:47
 * @description
 * @copyright ©2018 www.jzfox.net All rights reserved.
 */
public abstract class BaseGooglePayActivity extends AppCompatActivity implements GoogleBillingCallback, OnQueryInventoryCallback {


    protected abstract String base64EncodedPublicKey();


    private GoogleBill googleBill;


    protected void initGoogleBill() {
        googleBill = new GoogleBill(this, base64EncodedPublicKey(), this);

        // 添加商品 id
        //   googleBill.addItemSku("pay001", "pay002");

        // 添加订阅类 商品 id
        // googleBill.addSubSku("sub001");

    }

    @Override
    public void onInitResult(boolean b, String s) {

        if (b) {
            // 手机支持谷歌支付
            googleBill.setQueryInventoryCallback(this);

            // 查询前必须添加商品 id
            googleBill.queryInventory();

        } else {
            // 手机不支持谷歌支付
        }

    }



    @Override
    public void onConsumeResult(Purchase purchase, boolean b, String s) {

    }

    @Override
    public void onQueryInventory(Map<String, SkuDetails> map) {

        // 查询结果

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (googleBill != null && googleBill.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
