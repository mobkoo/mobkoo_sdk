//
//  HaiYouConfig.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/22.
//  Copyright © 2019 haiyou. All rights reserved.
//
#ifndef HaiYouConfig_H
#define HaiYouConfig_H
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum {
    SNADBOX_CLOSE,
    SANDBOX_OPEN
} SandboxState;

@interface HaiYouConfig : NSObject

+(instancetype)shareConfig;

-(void)setConfigWithAPPID:(NSString *)appID WithHaiYouKey:(NSString *)haiyouKey;

-(void)setConfigWithAPPID:(NSString *)appID WithHaiYouKey:(NSString *)haiyouKey WithSandBox:(SandboxState)sandbox;

@property (strong,nonatomic) NSString * APPID;
@property (strong,nonatomic) NSString * HaiYouKey;
@property (nonatomic) SandboxState * sandBox;

-(void)setDebug:(BOOL)state;
-(void)setOpenToast:(BOOL)state;

+(BOOL)Debug;
+(BOOL)Toast;

@end

NS_ASSUME_NONNULL_END

#endif
