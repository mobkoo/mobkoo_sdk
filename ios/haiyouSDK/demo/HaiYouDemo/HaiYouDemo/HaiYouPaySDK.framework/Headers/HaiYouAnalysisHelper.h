//
//  HaiYouAnalysisHelper.h
//  HaiYouPaySDK
//
//  Created by admin on 2019/4/22.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface HaiYouAnalysisHelper : NSObject
+ (instancetype)sharedSingleton;

-(void)report_Wlecom;

-(void)report_LoginWithUserID:(NSString *)userID;


-(void)report_PayWithOrderID:(NSString *)orderID Price:(NSString *)price Currency:(NSString *)currency;






@end

NS_ASSUME_NONNULL_END
