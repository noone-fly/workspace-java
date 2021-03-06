package io.pierre.token;

import java.util.Date;

import io.pierre.token.AccessToken;
import io.pierre.token.SimpleTokenBuilder;

public class BuilderTokenSample {

	public static String generateToken(String appId, String appCertificate, String channelName, String uid,
			int expireTimestamp) throws Exception {
		String result;
		SimpleTokenBuilder token = new SimpleTokenBuilder(appId, appCertificate, channelName, uid);
		token.initPrivileges(SimpleTokenBuilder.Role.Role_Publisher);
		token.setPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
		result = token.buildToken();
		return result;
	}
}
