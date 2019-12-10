package io.agora.signal;

import io.agora.signal.SignalingToken;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SignalingTokenTest {

    public static void testSignalingToken() throws NoSuchAlgorithmException {

        String appId = "02683f332f1d4153b821a617e08d1164";
        String certificate = "e381509697d2437cadbb2c120c9cf6ef";
        String account = "123456789";
        int expiredTsInSeconds = 1563441340;
       // String expected = "1:970ca35de60c44645bbae8a215061b33:1446455471:4815d52c4fd440bac35b981c12798774";
        String result = SignalingToken.getToken(appId, certificate, account, expiredTsInSeconds);
        System.out.println(result);
       // assertEquals(expected,result);

    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
    		testSignalingToken();
	}
}
