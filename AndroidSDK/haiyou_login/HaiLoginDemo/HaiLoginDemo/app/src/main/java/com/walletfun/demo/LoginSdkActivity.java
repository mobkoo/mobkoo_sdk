package com.walletfun.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.facebook.FacebookException;
import com.google.android.gms.common.api.ApiException;
import com.walletfun.common.app.WalletHelp;
import com.walletfun.common.util.DeviceUtils;
import com.walletfun.login.HaiYouLoginCallback;
import com.walletfun.login.HaiYouLoginHelper;
import com.walletfun.login.HaiYouLoginResult;
import com.walletfun.login.HaiYouLoginSdk;
import com.walletfun.login.HaiYouLoginStatus;
import com.walletfun.login.HaiYouUser;
import com.walletfun.login.HaiYouUserCallback;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Jzfox <jz_boy@163.com>
 * @version 1.0
 * @date 2018/11/2 15:29
 * @description
 * @copyright ©2018 www.jzfox.net All rights reserved.
 */
public class LoginSdkActivity extends Activity {

    @BindView(R.id.edit_result)
    EditText resultEdit;
    String googlekey = "259280296771-nr16in22tsgkhads8rls3lj8bgn9v1qp.apps.googleusercontent.com";
    String appid = "7";
    String key = "ddivcdzh1vxabtqr";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sdk);
        ButterKnife.bind(this);
        init();

    }

    // 登录主要使用的类
    private HaiYouLoginHelper helper;

    private void init() {
        initSDK();
        initLoginCallBack();
    }

    private void initLoginCallBack() {
        // 设置登录回调方法
        helper.setLoginCallback(new HaiYouLoginCallback() {
            @Override
            public void onResult(HaiYouLoginResult result) {
                // 登录结果
                switch (result.getStatus()) {
                    case HaiYouLoginStatus.LOGIN_SUCCESS:
                        // 成功
                        resultEdit.append("\n 登录成功");

                        loginSuccess(result.getUser());
                        break;
                    case HaiYouLoginStatus.LOGIN_CANCEL:
                        // 用户取消了登录
                        resultEdit.append("\n用户取消了登录" + helper.platformName(result.getPlatform()));
                        break;
                    default:
                        // 登录失败
                        resultEdit.append("\n登录失败：" + helper.platformName(result.getPlatform()));
                        loginFailure(result.getPlatform(), result.getFailed());
                        break;
                }
            }
        });
    }

    private void initSDK() {
        //将登录SDK加入到公共模块
        WalletHelp.addSDK(HaiYouLoginSdk.instance());
        // 设置google登录参数
        HaiYouLoginSdk.setGoogleServerClientId(googlekey);
        /*   初始化登录SDK ,
        初始化公共参数 appid （测试id） 加密秘钥key  沙盒模式（沙盒模式暂时只有充值使用）；*/
        WalletHelp.init(this, appid, key, WalletHelp.SANDBOX_CLOSE);
        // 设置DEBUG模式 和日志相关 上线时建议关闭
        WalletHelp.setEnvDebug(false);

        // 初始化 登录辅助类
        helper = HaiYouLoginHelper.getInstance(this);
    }

    public void getDeviceId(){
        // 获取手机设备号
        String deviceID = WalletHelp.getDeviceID(this);
    }

    private void loginSuccess(HaiYouUser user) {
        resultEdit.append("\n");
        resultEdit.append("\n登录的平台：" + helper.platformName(user.getPlatform()));
        resultEdit.append("\n用户ID:" + user.getId());
        resultEdit.append("\nToken:" + user.getToken());

        // 游客登录的时候一般没有，自己给游客取个名字吧
        resultEdit.append("\n昵称:" + user.getNickname());
        // 登录的用户名，一般没有

        resultEdit.append("\n头像:" + user.getUserIcon());

    }

    private void loginFailure(int platform, Object exception) {

        resultEdit.append("\n");
        switch (platform) {
            case HaiYouLoginHelper.LOGIN_PLATFORM_FACEBOOK:

                if (exception instanceof FacebookException) {
                    resultEdit.append("\nFacebook登录失败:" + exception.toString());
                }
                break;

            case HaiYouLoginHelper.LOGIN_PLATFORM_GOOGLE:

                if (exception instanceof ApiException) {
                    resultEdit.append("\nGoogle登录失败：" + ((ApiException) exception).getStatusCode());
                }
                break;
            case HaiYouLoginHelper.REQUEST_LOGIN_HAIYOU:

            case HaiYouLoginHelper.LOGIN_PLATFORM_GUEST:

                if (exception instanceof Exception) {
                    resultEdit.append("\n" + helper.platformName(platform) + ":" + exception.toString());
                }
                break;
            default:
                resultEdit.append("\n ----- 看到此消息联系开发人员！------------");
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 很重要的一步，自己使用 startActivityForResult 时，requestCode 请不要设置成 10001 到 10005
        if (helper.handlerActivityResult(requestCode, resultCode, data)) return;
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (helper != null) helper.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) helper.destroy();
    }

    @OnClick({R.id.btn_login_default, R.id.btn_login_facebook, R.id.btn_login_google, R.id.btn_login_hai, R.id.btn_login_guest, R.id.btn_login_out, R.id.btn_login_history})
    public void onClick(View view) {

        resultEdit.append("\n");

        switch (view.getId()) {
            case R.id.btn_login_default:
                // 使用sdk 默认登录框
                helper.showLogin(HaiYouLoginHelper.LOGIN_DEFAULT);
                break;
            case R.id.btn_login_facebook:
                // 单独使用facebook
                helper.showLogin(HaiYouLoginHelper.LOGIN_PLATFORM_FACEBOOK);
                break;
            case R.id.btn_login_google:
                helper.showLogin(HaiYouLoginHelper.LOGIN_PLATFORM_GOOGLE);
                break;
            case R.id.btn_login_hai:
                helper.showLogin(HaiYouLoginHelper.LOGIN_PLATFORM_HAIYOU);
                break;
            case R.id.btn_login_guest:
                helper.showLogin(HaiYouLoginHelper.LOGIN_PLATFORM_GUEST);
                break;
            case R.id.btn_login_history:
                // 获取上次的登录信息是否有效，谷歌登录的暂时不能获取
                helper.hrtUser(new HaiYouUserCallback() {
                    @Override
                    public void onUserResult(HaiYouUser user) {
                        // 如果为 null 则说明登录过期，或者是谷歌登录
                        if (user == null) {
                            resultEdit.append("请重新登录\n");
                        } else {
                            loginSuccess(user);
                        }
                    }
                });
                break;
            case R.id.btn_login_out:
                helper.logout();
                resultEdit.append("登录退出\n");

                break;


        }
    }
}
