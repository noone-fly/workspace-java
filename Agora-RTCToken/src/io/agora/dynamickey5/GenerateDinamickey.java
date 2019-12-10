package io.agora.dynamickey5;
import java.util.Date;
import java.util.Random;


public class GenerateDinamickey {
	static String appId = "460bdcb249504781aed2354c555f30a2";
    static String appCertificate = "f11e762a75524825a973f988bf16cbb4";
    static String channel = "147258";
    static int ts = (int)(new Date().getTime()/1000);
    static int r = new Random().nextInt();
    static long uid = 12345; //32 位无符号整数。建议设置范围：1 到 (2^32-1)，
   // static int expiredTs = (int)(new Date().getTime()/1000) + 43200;
    static int expiredTs = 0;//不设置有效期，则为24小时

    public static void main(String[] args) throws Exception {
    		
        System.out.println(DynamicKey5.generateMediaChannelKey(appId, appCertificate, channel, ts, r, uid, expiredTs));
        System.out.println(new Random().nextInt());
        System.out.println(new Date().getTime()/1000);
		System.out.println(new Date().getTime());
		System.out.println(new Date());
    }
}