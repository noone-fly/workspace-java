package rtm;

import rtm.AccessToken;
import rtm.RtmTokenBuilder;

public class RtmTokenBuilderSample {
    static String appId = "4103ed44c21543078978c435559b31ba";
    static String appCertificate = "d445fbd616294085a16fab680e7a5afc";
    static String channelName = "";
    static String userId = "121212";
 // static int expireTimestamp = (int)(new Date().getTime()/1000) + 2400;
    static int expireTimestamp = 0;

    public static void main(String[] args) throws Exception {
        RtmTokenBuilder token = new RtmTokenBuilder(appId, appCertificate, userId);
        token.setPrivilege(AccessToken.Privileges.kRtmLogin, expireTimestamp);

        String result = token.buildToken();
        System.out.println(result);
    }
}
