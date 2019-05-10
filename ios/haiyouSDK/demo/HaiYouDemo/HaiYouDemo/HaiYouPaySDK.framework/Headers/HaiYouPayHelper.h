//
//  HaiYouPayHelper.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/17.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ApplePayUtil.h"
@class HaiYouLoginHelp;
@protocol HaiYouPayResult <NSObject>

-(void)haiyouPayResult:(PayModel *)payData PayState:(PayState)state PayError:(NSError *)error;
-(void)checkOrderResult:(PayModel *)payData PayState:(PayState)state PayError:(NSError *)error;

@end

NS_ASSUME_NONNULL_BEGIN

@interface HaiYouPayHelper : NSObject

@property (strong,nonatomic) id<HaiYouPayResult> payResult;

-(void)checkOrderWithID:(NSString *)orderID;

-(void)startPayWithProductID:(NSString *)productID WithPrice:(NSString *)price WithCurrency:(NSString *)currency WithExtraString:(NSString *)extraString;

+ (instancetype)sharedSingleton;

@end

NS_ASSUME_NONNULL_END
