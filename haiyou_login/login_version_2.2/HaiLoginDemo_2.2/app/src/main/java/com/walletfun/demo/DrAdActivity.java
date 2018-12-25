package com.walletfun.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;

/**
 * @author Jzfox <jz_boy@163.com>
 * @version 1.0
 * @date 2018/11/6 10:14
 * @description
 * @copyright ©2018 www.jzfox.net All rights reserved.
 */
public class DrAdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drad);

        ButterKnife.bind(this);

       // init();

    }

 /*   private void init() {
        // 初始化 DrAdSdk
        DrAdSdk.init("客户id", "产品id", "渠道id");

        // 请设置imsi 和 imei

        // 设置imsi
        DrAdSdk.setSubscriberId("imsi");

        // 设置imei
        DrAdSdk.setDeviceId("imei");

        // 设置生产环境， true 为测试环境 false 是正式环境
        DrAdSdk.setEnvDebug(BuildConfig.DEBUG);


    }

    *//*
     * 插屏广告
     *//*
    DrInterstitialAd interstitialAd;

    private void useInterstitialAd() {
        if (interstitialAd == null) {
            interstitialAd = new DrInterstitialAd();

            // 配置 AdMob 的广告配置
            AdMobAdConfig adMobAdConfig = new AdMobAdConfig();

            // 广告位id,必填
            adMobAdConfig.setAdUnitId("");

            // 不需要可不填写
            adMobAdConfig.setAppId("appid");

            // banner 广告需要配置，不配置使用默认配置

            // 前面一个参数是广告的填充布局
            adMobAdConfig.banner(null, AdSize.SMART_BANNER);
            // end of banner

            interstitialAd.addAdConfig(adMobAdConfig);
			
			
			

            // 配置 AudienceNetwork 的广告配置
            AudienceNetworkAdConfig audienceNetworkAdConfig = new AudienceNetworkAdConfig();

            // banner 广告需要配置，不配置使用默认配置 前面一个参数是广告的填充布局
            audienceNetworkAdConfig.banner(null, com.facebook.ads.AdSize.BANNER_320_50);
            // end banner

            // 同上
            audienceNetworkAdConfig.setAppId("").setAdUnitId("");

            // 添加到配置中
            interstitialAd.addAdConfig(audienceNetworkAdConfig);

            // 设置广告加载完成后自动展示
            interstitialAd.setAutoShow(true);

            // 设置广告监听事件，并不是所有方法都会返回，有的方法是部分场景中适用
            interstitialAd.setAdListener(new SimpleAdListener() {
                @Override
                public void onAdLoadSuccess(DrAbstractAd drAbstractAd) {
                    super.onAdLoadSuccess(drAbstractAd);

                    // 广告加载成功

                    // 根据此判断是哪个广告平台平台加载成功了，如果广告没有加载成功，此处返回为 null
                    DrAdPlatform platform = drAbstractAd.getPlatform();

                    String platformName = platform.name();

                    switch (platformName) {
                        case AdMobPlatform.NAME:

                            break;
                        case AudienceNetworkPlatform.NAME:

                            break;
                        case AdColonyPlatform.NAME:

                            break;
                    }



                }

                @Override
                public void onAdLoadingError(DrAbstractAd drAbstractAd, int errorCode, DrAdError drAdError) {
                    super.onAdLoadingError(drAbstractAd, errorCode, drAdError);
                    // 各个平台错误的信息
                    switch (errorCode) {
                        case DrAdError.ERROR_DEBUG:
                            // 后台配置的是debug 类型，而app 是线上类型
                            break;
                        case DrAdError.ERROR_VALID:
                            // 上次请求的广告在有效期内
                            break;
                        case DrAdError.ERROR_NOT_CONFIG_U:
                            // 没有配置相关平台，使用 addAdConfig 设置配置信息
                            break;
                        case DrAdError.ERROR_NO_CONFIG:
                            // 未配置任何平台，使用 addAdConfig 设置配置信息

                            break;
                        case DrAdError.ERROR_NOT_SUPPORT:
                            // 不支持的平台，可能广告平台信息配置有错误
                            break;
                        case DrAdError.ERROR_REQUEST:
                            // 上一个广告在请求中
                            break;
                        case DrAdError.ERROR_THIRD:
                            // 广告平台返回的错误信息

                            if (drAdError != null) {
                                String platformName = drAdError.getPlatformName();

                                if (!TextUtils.isEmpty(platformName)) {

                                    // 错误代码
                                    int errCode = drAdError.getErrorCode();
                                    // 错误信息
                                    String errMsg = drAdError.getErrorMsg();

                                    // 根据状态码和错误信息去相应的平台查询错误说明

                                    switch (platformName) {
                                        case AdMobPlatform.NAME:

                                            break;
                                        case AudienceNetworkPlatform.NAME:

                                            break;
                                        case AdColonyPlatform.NAME:

                                            break;
                                    }

                                }


                            }

                            break;

                    }

                }

                @Override
                public void onAdLoadFailed(DrAbstractAd drAbstractAd, int i) {
                    super.onAdLoadFailed(drAbstractAd, i);
                    // 广告加载失败，这个是最后的结果，与平台无关
                    switch (i) {
                        case DrAdError.ERROR_NO_CONFIG:
                            // 没有配置平台 使用 addAdConfig 设置配置信息

                            break;
                        case DrAdError.ERROR_NO_FILL:
                            // 所有平台均请求失败

                            break;
                        case DrAdError.ERROR_REQUEST:
                            // 上一个广告还在有效期

                            break;


                    }

                }

                @Override
                public void onAdClicked(DrAbstractAd drAbstractAd) {
                    super.onAdClicked(drAbstractAd);
                    // 广告被点击了

                }

                @Override
                public void onAdShow(DrAbstractAd drAbstractAd) {
                    super.onAdShow(drAbstractAd);
                    // 广告显示回调

                }

                @Override
                public void onAdDismiss(DrAbstractAd drAbstractAd) {
                    super.onAdDismiss(drAbstractAd);
                    // 广告消失

                }

                @Override
                public void onReward(DrAbstractAd drAbstractAd, boolean b, Object o) {
                    super.onReward(drAbstractAd, b, o);

                    // 激励广告返回
                    if (b) {
                        // 获取激励成功
                        // o 为各个平台返回的额外数据，有的平台可能没有

                    } else {
                        // 失败
                    }
                }
            });
        } // end if

        // 加载广告
        interstitialAd.loadAd(this);

    }


    @Override
    protected void onPause() {
        super.onPause();

        if (interstitialAd != null) {
            interstitialAd.onPause(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (interstitialAd != null) {
            interstitialAd.onResume(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (interstitialAd != null) {
            interstitialAd.onDestroy(this);
        }
    }*/
}
