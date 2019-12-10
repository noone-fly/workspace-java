package io.agora.media.sample;

import io.agora.media.AccessToken;
import io.agora.media.RtmTokenBuilder;

public class RtmTokenBuilderSample {
    static String appId = "970CA35de60c44645bbae8a215061b33";
    static String appCertificate = "5CFd2fd1755d40ecb72977518be15d3b";
    static String channelName = "7d72365eb983485397e3e3f9d460bdda";
    static String userId = "2882341273";
    static int expireTimestamp = 0;

    public static void main(String[] args) throws Exception {
        RtmTokenBuilder token = new RtmTokenBuilder(appId, appCertificate, userId);
        token.setPrivilege(AccessToken.Privileges.kRtmLogin, expireTimestamp);

        String result = token.buildToken();
        System.out.println(result);
    }
}
