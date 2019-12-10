package io.agora.accesstoken;

import java.util.Date;

import io.agora.accesstoken.AccessToken;
import io.agora.accesstoken.SimpleTokenBuilder;

public class BuilderTokenSample {
	
	static String appId = "";
    static String appCertificate = "";
    static String channelName = ""; //频道号
    static String uid = "";//32 位无符号整数。建议设置范围：1 到 (2^32-1)，
    //static int expireTimestamp = (int)(new Date().getTime()/1000) + 24*3600;
    static int expireTimestamp = 0; //不设置有效期，则为24小时
    
    public static void main(String[] args) throws Exception {
        SimpleTokenBuilder token = new SimpleTokenBuilder(appId, appCertificate, channelName, uid);
        token.initPrivileges(SimpleTokenBuilder.Role.Role_Publisher);
        token.setPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
        String result = token.buildToken();
        System.out.println(result);
		System.out.println(new Date());
    }
}
