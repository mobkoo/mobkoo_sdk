package com.hai.haiyoudemo;

import android.app.Application;

import com.walletfun.common.app.WalletHelp;
import com.walletfun.huawei.HuaWeiSDK;
import com.walletfun.login.HaiYouLoginSdk;
import com.walletfun.pay.ui.HaiYouPaySDK;

public class MyApplication extends Application {
    private String svngoogleLoginID = "203631936303-69hjqlv5g7v3aaftjrc2du8t8qiha2ua.apps.googleusercontent.com";
    private static final String VPNgooglePulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAolI1tb4Oc11PEWvqkq2lJTtIEowjsm5KXmOyYtDzw1034sECMSrIC3lsx1p2EtUM0pVyGA1Jgp2vWQNblYAPwQH+vIg4RqOpJIndLyS/4Aa9sI5+/yrbI+LV7tRpx9t2D5hAOdIrHw5EcgQAHegWz9Pcro7T4FSY3wMebQFM2Q9i3xcNT4fkwl2bZlDr9ZOkyuxsWhoRByBAvJ5DUg9MnpQY5CNarsW7Ze9fdTVT6iA6OpDX5lA0thAKHLmSNIvPIWtNeSikDJB8TjQPUELLXgnlMlUd6fMYPjweaOmTjN0G4uSuukvTlLcxXrT4NodXmPV9ZfvsRitByK+nQ3aOYQIDAQAB";


    public static final String HuaWei_pay_privkey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXqvMm8n8b2ER31ulTZJ2H7k22y5MTFfh/lSwXcJKlNNaY/W8KzpeZUqAwsRc6dVzeQ1T2WvtGflgt1SxQAGI9nquYIC6vH9jV/HLFUBi6SOetTC1mM1qQLMkwDNoPxsTJmKpA1BCKyY/arQlVGpxDbwSH06UZNm+bWPumvePxmTQxe18iRhOCzb8+LdiM2JFEm2kk6BFi7IUElchx5fhRblAgYysg/uCgxmUqoVVDDAIyfcGnBfS4bPOhXVonuI/n+YpZ2FGGfURKVpjDONUhxq9wEV9Piz59xaCTSu3ymR/EhJOGkhZ3iE8lzHiMMykRUIXBHcIIUz4tXE/2T10bAgMBAAECggEADe3RHfMTEbjCH6rDoU6HbzFHaYchueorI8sh56yFiJmlLxtwuXKGwWSGugy8NY4Lb42J0y7rjn+nqTHqO2ta77jPno2EwWhF2ZqGjzDPdzr0gq8RKPvaEI1PFCl3VLqXCRgLDsrLEsv3I7NU55V643CMic2PXJTqGWofK82bDm2KKxLzz1eYfYezyEZy0mMAjMgzV/SbYzL+WWssk/U85hFutuKg00BEDbV8XcOIkOcsH776esJ/FL2BG0cRSEcd8Npi42XTdzIaOqoZ7kMFlPS32J89YSDn43hZZcNjf9LizvpYJOPMPx3LxuP0gG2kMlupL5Rx/lmV5NKatXzQDQKBgQDNUXsDUEF9VUTU/6d1u29WlmaK7mWdVtgj1ntMQDHnoTQuAEDHnZJL3AgpozIbBLyYACk+4N3sGxLjUohe7ygvDWDjVafgtsExCtLtjyXOVsb5VkBFRc+AFr455VjU7TnTYRdxSWi3vrNmXBiX8513WTAilvotWFq4rWJ/P04DTQKBgQC9GyyQCeAD5qb3MIAdhjocvT7Z00vGyh2g5gQOXi/M3QO8TUL5bwhfzTg+J82NUgkT+J9YbeMNOC0Mgq/IlbABsb5TlvR3txACryvMXSRhLxhRboD2S2R3CX7W/2fh1Q85Xh/aV0BGgMVq6QEYAe0b1Rj/uAuYmE/Yiu9vlFNeBwKBgHxeSNde++yBhCpJmhR0ryKSBBdQBPljq5U5zIIJuLEIMuVSfershbux0rf2BNnc6qhgIJvX9lWYqL5ONhDbaUnnHrNWJVS3Hxsn5VDFDy+H3QsXteLAQMrj++JyDHceI5LpExwqTWZP+7ebPcJ9bProaYHqViy1gm6viCRwIj9ZAoGBAJzWdsUXi5VwrLWCoh7afkPrqL7cAZLjemNBoceQ0jAkdb6sjYl2Twm1mJnkRRSaHzFTGEUtDgyetK4VjVjc3Nbq5aFKJ4NKbprrUXXzIgMAHjkbXxXmPXKEi5Agz7V7EV9Q8zYQ+xv+HL2odeHFZzOYL12W9PcgC8jaoe52pBXtAoGBAIr01fQaX+y/jFwpDclFF1tfVrRPQQS4LvT1Xv7oBXto0OiQ6LRwlaOPRqx/1j9c9vmLATV/fv/HXZ7M68EGABbQvoJ53iOZ+V90HjWxOYHNlvCJwFrk1Y1cvKtVVEK/WbsDIduO+vXs2Q/qxR25waprdiQmzQ1av9CokGnfeHOZ";
    public static final String HuaWei_pay_pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl6rzJvJ/G9hEd9bpU2Sdh+5NtsuTExX4f5UsF3CSpTTWmP1vCs6XmVKgMLEXOnVc3kNU9lr7Rn5YLdUsUABiPZ6rmCAurx/Y1fxyxVAYukjnrUwtZjNakCzJMAzaD8bEyZiqQNQQismP2q0JVRqcQ28Eh9OlGTZvm1j7pr3j8Zk0MXtfIkYTgs2/Pi3YjNiRRJtpJOgRYuyFBJXIceX4UW5QIGMrIP7goMZlKqFVQwwCMn3BpwX0uGzzoV1aJ7iP5/mKWdhRhn1ESlaYwzjVIcavcBFfT4s+fcWgk0rt8pkfxISThpIWd4hPJcx4jDMpEVCFwR3CCFM+LVxP9k9dGwIDAQAB\n";
    public static final String HuaWei_game_name = "海游网络";



    /*
    private static final String talkingData_Key = "E96330E98A0F43E3918E43C444E3D7C2";
    private static final String appsfly_DEVKEY = "dz2eqVEhio3jEBLnTwkUw4";
    private static final String FacebookId = "dz2eqVEhio3jEBLnTwkUw4";
*/


    @Override
    public void onCreate() {
        super.onCreate();
        WalletHelp.setEnvDebug(true);
        /*设置googel登录参数*/
        HaiYouLoginSdk.setGoogleServerClientId(svngoogleLoginID);
        /*设置google支付参数*/
        HaiYouPaySDK.setPayMode(HaiYouPaySDK.ALL);
        HaiYouPaySDK.setGooglePublicKey(VPNgooglePulicKey);
        /* 设置华为参数*/
        HuaWeiSDK.instance().HuaWei_init(this, HuaWei_pay_privkey, HuaWei_pay_pubkey, HuaWei_game_name);
        /* 初始化*/
        WalletHelp.init(this, "8", "31hriti1l33zc2nk", WalletHelp.SANDBOX_CLOSE);
    }
}
