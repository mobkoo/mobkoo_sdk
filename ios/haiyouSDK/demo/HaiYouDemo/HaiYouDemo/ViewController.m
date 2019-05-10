//
//  ViewController.m
//  HaiYouDemo
//
//  Created by admin on 2019/5/7.
//  Copyright © 2019 haiyou. All rights reserved.
//

#import "ViewController.h"
#import <HaiYouPaySDK/HaiYouPaySDK.h>

@interface ViewController ()
- (IBAction)clickPay:(id)sender;
- (IBAction)click_checkOrder:(id)sender;
- (IBAction)click_login:(id)sender;
- (IBAction)click_loginGuest:(id)sender;
- (IBAction)click_userInfo:(id)sender;
- (IBAction)click_logout:(id)sender;
@property (weak, nonatomic) IBOutlet UIScrollView *ScrollView;
@property (weak, nonatomic) IBOutlet UILabel *resultLabel;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    NSString *APPID=@"8";
    NSString *NETKEY=@"31hriti1l33zc2nk";
    
    _resultLabel.numberOfLines = 0;
    _resultLabel.lineBreakMode = NSLineBreakByWordWrapping;
    _resultLabel.font=[UIFont systemFontOfSize:14];
    
    [self addNotification];
    [[HaiYouConfig shareConfig]setConfigWithAPPID:APPID WithHaiYouKey:NETKEY WithSandBox:SANDBOX_OPEN];
    
}

-(void)addNotification{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loginResult:) name:HaiYouSDKNotiLogin object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(payResult:) name:HaiYouSDKNotiPay object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(checkOrderResult:) name:HaiYouSDKNotiPayCheckOrder object:nil];
}


- (void)loginResult:(NSNotification *)notification {
    NSDictionary *dic= notification.userInfo;
    NSString *state=[dic objectForKey:@"state"];
    NSString *type=[dic objectForKey:@"type"];
    
    if([type isEqualToString:@"userinfo"]){
        [self addString: @"保存的用户信息"];
    }else if([type isEqualToString:@"login"]){
        [self addString: @"登录信息"];
    }else{
        [self addString: type];
    }
    
    
    if([state isEqualToString:HaiYouSDKStateSuccess]){
        HaiYouUser *user= [dic objectForKey:@"data"];
        NSString *userInfo=[NSString stringWithFormat:@"登陆成功：用户：%@ ID：%@",user.nickname,user.userID];
        [self addString:userInfo];
        NSLog(userInfo);
    }else{
        NSError *error=   [dic objectForKey:@"error"];
        NSString *errorInfo=[NSString stringWithFormat:@"登陆失败： %@",error.localizedDescription];
        [self addString: errorInfo];
        NSLog(@" 通知登陆 失败  %@ ",errorInfo);
    }
}


- (void)checkOrderResult:(NSNotification *)notification {
    NSDictionary *dic= notification.userInfo;
    NSString *state=   [dic objectForKey:@"state"];
    PayModel *paymode=[dic objectForKey:@"data"];
    NSError *error=   [dic objectForKey:@"error"];
    [self addString: @"查询订单"];
    [self haiyouPayResult:paymode PayState:state PayError:error];
}

- (void)payResult:(NSNotification *)notification {
    //do something when received notification
    //notification.name is @"NOTIFICATION_NAME"
    NSDictionary *dic= notification.userInfo;
    NSString *state=   [dic objectForKey:@"state"];
    PayModel *paymode=[dic objectForKey:@"data"];
    NSError *error=   [dic objectForKey:@"error"];
    [self addString: @"支付结果"];
    [self haiyouPayResult:paymode PayState:state PayError:error];
    
}

-(void)haiyouPayResult:(PayModel *)paymode PayState:(NSString *)state PayError:(NSError *)error{
    
    if([state isEqualToString:HaiYouSDKStateSuccess]){
        NSString *payInfo=[NSString stringWithFormat:@"成功：订单：%@ 价格：%@ 额外参数：%@",paymode.order_id,paymode.price, paymode.out_order_id];
        [self addString: payInfo];
        
    }else if ([state isEqualToString:HaiYouSDKStateError]){
        NSString *errorInfo=[NSString stringWithFormat:@"失败： %@",error.localizedDescription];
        [self addString: errorInfo];
        
    }else{
        NSString *errMessage=[NSString stringWithFormat:@"支付状态错误 %d   %@    %@",state,paymode,error];
        [self addString: errMessage];
    }
}


- (IBAction)clickPay:(id)sender {
    [[HaiYouPayHelper sharedSingleton]startPayWithProductID:@"xtby.ios.01" WithPrice:@"0.99" WithCurrency:@"USD" WithExtraString:@"userid=123"];
}

- (IBAction)click_checkOrder:(id)sender {
    [[HaiYouPayHelper sharedSingleton]checkOrderWithID:@"1557369629056n4u1U"];

}

- (IBAction)click_login:(id)sender {
    [[HaiYouLoginHelper sharedSingleton]LoginWithType:Login_Type_DefaultDialog];
}

- (IBAction)click_loginGuest:(id)sender {
    [[HaiYouLoginHelper sharedSingleton]LoginWithType:Login_Type_Guest];

}

- (IBAction)click_userInfo:(id)sender {
    [[HaiYouLoginHelper sharedSingleton]lastUserInfo];

}

- (IBAction)click_logout:(id)sender {
    [[HaiYouLoginHelper sharedSingleton]logout];
    [self addString: @"退出登录"];

}

-(void)addString:(NSString *)message{
    NSString *str= _resultLabel.text;
    NSString* newMessage= [NSString stringWithFormat:@"%@\n \n %@",message,str];
    CGRect fram= _resultLabel.frame;
    
    CGSize baseSize = CGSizeMake(fram.size.width, CGFLOAT_MAX);
    
    CGSize labelsize  = [newMessage
                         boundingRectWithSize:baseSize
                         options:NSStringDrawingUsesLineFragmentOrigin
                         attributes:@{NSFontAttributeName:[UIFont systemFontOfSize:14.0]}
                         context:nil].size;
    
    fram.size.height = labelsize.height;
    _resultLabel.frame=fram;
    _resultLabel.text=newMessage;
    _ScrollView.contentSize = CGSizeMake(0,_resultLabel.frame.size.height);//这里赋值label的frame大小，以支持滚动浏览。
    
}

@end
