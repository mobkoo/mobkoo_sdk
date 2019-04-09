package com.hai.haiyoudemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.walletfun.common.app.WalletHelp;
import com.walletfun.common.util.LogUtils;
import com.walletfun.login.HaiYouLoginCallback;
import com.walletfun.login.HaiYouLoginHelper;
import com.walletfun.login.HaiYouLoginResult;
import com.walletfun.login.HaiYouLoginStatus;
import com.walletfun.login.HaiYouUser;
import com.walletfun.login.HaiYouUserCallback;


/**
 * @author Jzfox <jz_boy@163.com>
 * @version 1.0
 * @date 2018/10/24 15:23
 * @description
 * @copyright ©2018 www.jzfox.net All rights reserved.
 */
public class LoginMainActivity extends AppCompatActivity {

    private HaiYouLoginHelper loginHelper;

    private TextView resultText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        resultText = findViewById(R.id.text_result);
        loginHelper = HaiYouLoginHelper.getInstance(this);
        LogUtils.e("    单例 3" + loginHelper.toString());

        loginHelper.setLoginCallback(new HaiYouLoginCallback() {
            @Override
            public void onResult(HaiYouLoginResult result) {
                LogUtils.e(" 用户信息： " + result.toString());
                switch (result.getStatus()) {
                    case HaiYouLoginStatus.LOGIN_SUCCESS:
                        // 成功
                        resultText.append("\n 登录成功");
                        loginSuccess(result.getUser());
                        break;
                    case HaiYouLoginStatus.LOGIN_CANCEL:
                        // 用户取消了登录
                        resultText.append("\n用户取消了登录" + loginHelper.platformName(result.getPlatform()));
                        break;
                    default:
                        // 登录失败
                        resultText.append("\n登录失败：" + loginHelper.platformName(result.getPlatform()));
                        loginFailure(result.getPlatform(), result.getFailed());
                        break;
                }

            }
        });

    }

    String googlekey = "259280296771-nr16in22tsgkhads8rls3lj8bgn9v1qp.apps.googleusercontent.com";
    String appid = "7";
    String key = "ddivcdzh1vxabtqr";


    private void loginSuccess(HaiYouUser user) {

        resultText.append("\n");
        resultText.append("\n登录的平台：" + loginHelper.platformName(user.getPlatform()));
        resultText.append("\n平台ID:" + user.getPlatform());
        resultText.append("\nToken:" + user.getToken());
        // 游客登录的时候一般没有，自己给游客取个名字吧
        resultText.append("\n昵称:" + user.getNickname());
        // 登录的用户名，一般没有

        resultText.append("\n头像:" + user.getUserIcon());

        // 其他信息
        // 性别，一般无 0 : 未知 ; 1 : 男 ; 2 : 女


    }


    private void loginFailure(int platform, Object exception) {

        resultText.append("\n");
        switch (platform) {
            case HaiYouLoginHelper.LOGIN_PLATFORM_FACEBOOK:


                    resultText.append("\nFacebook登录失败:" + exception.toString());

                break;


            case HaiYouLoginHelper.LOGIN_PLATFORM_GOOGLE:


                    resultText.append("\nGoogle登录失败：" +  exception .toString());

                break;
            case HaiYouLoginHelper.REQUEST_LOGIN_HAIYOU:

            case HaiYouLoginHelper.LOGIN_PLATFORM_GUEST:

                    resultText.append("\n" + loginHelper.platformName(platform) + ":" + exception.toString());

                break;
            default:
                resultText.append("\n ----- 看到此消息联系开发人员！------------");
                break;
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (loginHelper != null) {
             loginHelper.onConfigurationChanged(newConfig);
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:
                loginHelper.showLogin(HaiYouLoginHelper.LOGIN_PLATFORM_HAIYOU);
                break;
            case R.id.btn_login_dialog:
                loginHelper.showLogin(HaiYouLoginHelper.LOGIN_DEFAULT);
                break;
            case R.id.btn_loginout:
                loginHelper.logout();
                break;
            case R.id.btn_login_hrt:

                loginHelper.hrtUser(new HaiYouUserCallback() {
                    @Override
                    public void onUserResult(HaiYouUser user) {
                        // 如果为 null 则说明登录过期，或者是谷歌登录
                        if (user == null) {
                            resultText.append("请重新登录");
                        } else {
                            loginSuccess(user);
                        }
                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (loginHelper.handlerActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if (loginHelper != null) {
            loginHelper.destroy();
        }
        WalletHelp.onDestory(this);
        super.onDestroy();
    }
}
