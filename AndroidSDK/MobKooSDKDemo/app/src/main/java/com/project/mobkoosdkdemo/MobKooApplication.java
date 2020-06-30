package com.project.mobkoosdkdemo;

import android.app.Application;

import com.mobkoo.MobKooManager;


public class MobKooApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobKooManager.getInstance()
                .setContext(this)
                .setDebug(true)
                .setAppInfo("8", "31hriti1l33zc2nk")
                .setSandBox(false)
                .setPayType(MobKooManager.PAY_TYPE_ALL)
                .setGoogleClientID("491180860842-csffpugbv9oumfj5b265t8k7r8a4bqhd.apps.googleusercontent.com")
                .setGoogleLicenseKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3JSWpSZ7An/jYxoYiFFzbVSAP9JdxV8gowBxUwJjO38sKSeY4JfBOXWOqIW59ZGYRB/xMKaNsju1XNYo8q/LS1pEadYShkzYfQwI1mujIjhbnMu2hhsa6Z94cbSFuTZb+vHw+z7RcbAZU9xyiWDeCT8BC4BX2osnn4fn9C9DMMJD+AbgBBBGjduuohkgdUSZJl3j/U9gisSKYTu6F2R1hD1i9qYywMstbWrXlewikYVnao5KjKt+qbcrMTOemuK04gaxjhnuL953QIWO9yo+VU1LOMsyrFtYQ7e4YNywEfBpoowxVmns9MxP6FXfLSMqjgVZ58sMsnAWqCQyxoGXqwIDAQAB")
                .init();
    }
}
