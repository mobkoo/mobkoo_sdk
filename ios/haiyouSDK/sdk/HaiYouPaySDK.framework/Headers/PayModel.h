//
//  PayModel.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/17.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>


NS_ASSUME_NONNULL_BEGIN

@interface PayModel : NSObject
@property (strong,nonatomic) NSString * state;                       // 状态
@property (strong,nonatomic) NSString * order_id;                    // 还有OrderID
@property (strong,nonatomic) NSString * out_order_id;                // 额外参数
@property (strong,nonatomic) NSString * game_currency;               // 游戏币
@property (strong,nonatomic) NSString * game_currency_present;       // 赠送的游戏币
@property (strong,nonatomic) NSString * price;                       // 价格（美元）
@property (strong,nonatomic) NSString * monetary_unit;               // 美元单位
@property (strong,nonatomic) NSString * sandbox;                     //沙盒状态（只针对第三方支付）
@property (strong,nonatomic) NSString * platform_name;               // 支付渠道名称
@property (strong,nonatomic) NSString * product_id;                  // 商品id


+(instancetype)initWithDic:(NSDictionary *)dic;

+(instancetype)initWithProductID:(NSString *)productID OrderID:(NSString *)orderID Price:(NSString *)price Currency:(NSString *)currency ;

@end

NS_ASSUME_NONNULL_END
