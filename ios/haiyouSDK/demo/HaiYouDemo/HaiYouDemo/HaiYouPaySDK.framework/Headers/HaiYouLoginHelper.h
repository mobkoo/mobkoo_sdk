//
//  HaiYouLoginHelp.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/17.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>
typedef enum{
    Login_Type_DefaultDialog=0,       // 默认登录方式
    Login_Type_Guest                  // 游客登录
    
} LoginType;

NS_ASSUME_NONNULL_BEGIN

@interface HaiYouLoginHelper : NSObject

#pragma 登录
-(void)LoginWithType:(LoginType)loginType;

#pragma 账号密码登录
-(void)loginWitherUser:(NSString *)user WithPassword:(NSString *)pwd WithCountryCode:(NSString *)countryCode;

#pragma 获取用户登录信息
-(void)lastUserInfo;

-(void)logout;

#pragma  type==1 注册  type==2 找回密码
-(void)registWithUser:(NSString *)username Captcha:(NSString *)captcha Passwork:(NSString *)pass CountryCode:(NSString *)countryCode Type:(NSNumber *)type;

+ (instancetype)sharedSingleton;
@end

NS_ASSUME_NONNULL_END
