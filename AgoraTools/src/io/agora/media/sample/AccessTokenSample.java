package io.agora.media.sample;

import java.util.Map;
import java.util.zip.CRC32;

import io.agora.media.AccessToken;
import io.agora.media.Utils;

public class AccessTokenSample {

		// TODO Auto-generated method stub
//		static String appId = "970CA35de60c44645bbae8a215061b33";
//	    static String appCertificate = "5CFd2fd1755d40ecb72977518be15d3b";
//	    static String channelName = "7d72365eb983485397e3e3f9d460bdda";
//	    static String uid = "2882341273";
//	    static int expireTimestamp = 0;
//
//	    public static void main(String[] args) throws Exception {
//	        AccessToken token = new AccessToken(appId, appCertificate, channelName, uid);
//	        token.addPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
//	        String result = token.build();
//	        System.out.println(result);
//
//	        AccessToken t = new AccessToken("", "", "");
//	        t.fromString(result);
//
//	        System.out.println();
//	        System.out.print("\nappId:\t" + t.appId);
//	        System.out.print("\nappCertificate:\t" + t.appCertificate);
//	        System.out.print("\nCRC channelName:\t" + t.crcChannelName + " crc calculated " + Utils.crc32(channelName));
//	        System.out.print("\nCRC uid:\t" + t.crcUid + " crc calculated " + Utils.crc32(uid));
//	        System.out.print("\nts:\t" + t.message.ts);
//	        System.out.print("\nsalt:\t" + t.message.salt);
//	    }
	

	    public static String generateNewRtcToken(String appId, String appCertificate, String channelName, String uid,
				int expireTimestamp) throws Exception {
	    		AccessToken token = new AccessToken(appId, appCertificate, channelName, uid);
	        token.addPrivilege(AccessToken.Privileges.kJoinChannel, expireTimestamp);
	        String result = token.build();
	        return result;
	    }
	    
	    public static String[] analyzeToken(String token) {
	    		AccessToken t = new AccessToken("", "", "");
	        t.fromString(token);
	        String[] strings = new String[] {t.appId, String.valueOf(t.message.ts)};
	        return strings;
	    }
	    
	    public static String[] validateToken(Map<String, String> map) {
	    		String[] strings = null;
	    		String token = map.get("token");
//	    		String appid = map.get("appid");
//	    		String appCer = map.get("appCer");
	    		String cname = map.get("cname");
	    		String uid = map.get("uid");
	    		
	    		//拿到客户token 解出app ID， crc channel name,  crc uid 
	    		AccessToken t = new AccessToken("", "", "");
	    		t.fromString(token);
	    		String appid_positive = t.appId;
	    		int crc_cname_positive = t.crcChannelName;
	    		int crc_int_positive = t.crcUid;
	    		int ts_positive = t.message.ts;
	    		 
	    		
	    		
		if (null == cname || cname.equals("")) {
			CRC32 crc32_1 = new CRC32();
			crc32_1.update(uid.getBytes());
			int crc_uid_negative = (int) crc32_1.getValue();
			strings = new String[] { appid_positive, String.valueOf(crc_int_positive), String.valueOf(ts_positive),String.valueOf(crc_uid_negative) };
		} else {
			// 用CRC32计算客户提供的频道号，uid，和上面获取的，进行对比
			CRC32 crc32 = new CRC32();
			crc32.update(cname.getBytes());
			int crc_cname_negative = (int) crc32.getValue();

			CRC32 crc32_1 = new CRC32();
			crc32_1.update(uid.getBytes());
			int crc_uid_negative = (int) crc32_1.getValue();
			strings = new String[] { appid_positive, String.valueOf(crc_cname_positive),
					String.valueOf(crc_int_positive), String.valueOf(ts_positive), String.valueOf(crc_cname_negative),
					String.valueOf(crc_uid_negative) };
		}
	    		
	    		return strings;
	    }
}
