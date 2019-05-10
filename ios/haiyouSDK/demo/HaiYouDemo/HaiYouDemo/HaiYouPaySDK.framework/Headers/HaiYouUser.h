//
//  HaiYouUser.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/30.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface HaiYouUser : NSObject

@property (strong,nonatomic) NSString * userID;                            // 用户ID
@property (strong,nonatomic) NSString * country_code;                      // 城市code
@property (strong,nonatomic) NSString * token;                             // 用户token
@property (strong,nonatomic) NSString * userIcon;                          // 用户头像
@property (strong,nonatomic) NSString * nickname;                          // 用户昵称
@property (strong,nonatomic) NSString * platform;                          // 登陆方式

+(instancetype)getModeWithDic:(NSDictionary *)dic;
+ (instancetype)getModeWithData:(id)idinfo;

@end

NS_ASSUME_NONNULL_END
