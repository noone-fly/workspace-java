package com.pierre.socket;

import java.net.InetAddress;

/**
 * https://www.oracle.com/
 * www.oracle.com/23.76.80.211
 * www.oracle.com/2001:668:108:a87:0:0:0:366
 * www.oracle.com/2001:668:108:aa0:0:0:0:366
 * InetAddress.getLocalHost();
 * bogon/192.168.1.103
 * @author chenpiyang
 */
public class InetAddressTest {
	public static void main(String[] args) {
		try {
			if (args.length > 0) {
				String host=args[0];
				InetAddress[] addresses = InetAddress.getAllByName(host);
				for (InetAddress inetAddress : addresses) {
					System.out.println(inetAddress);
				}
			}else {
				InetAddress localInetAddress = InetAddress.getLocalHost();
				System.out.println(localInetAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
