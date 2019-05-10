//
//  ApplePayUtil.h
//  MySDK
//
//  Created by admin on 2019/4/16.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "payModel.h"
typedef enum {
    PAYSUCCESS=0,                    //成功
    PAYERROR,                        //失败
    PAYRestored,                     //恢复购买
    PAYDeferred,                     //状态未确认
    PAYPurchasing                    //购买中
} PayState;

typedef void(^PayResultCallBack)(PayModel * payModel,NSError *error,PayState *state);
NS_ASSUME_NONNULL_BEGIN

@interface ApplePayUtil : NSObject

- (void)clickPurcaseBtnAction;

-(void)startApplePayWithParentView:(UIView *)view WithProductID:(NSString *)productID WithPrice:(NSString *)price WithCurrency:(NSString *)currency WithExtraString:(NSString *)extraString WithPayResult:(PayResultCallBack) payResult;
 @end

NS_ASSUME_NONNULL_END
