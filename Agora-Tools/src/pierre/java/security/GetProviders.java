package pierre.java.security;

import java.security.Provider;
import java.security.Security;
import java.util.Map;

public class GetProviders {

	public static void main(String[] args) {
		// 遍历目前环境中的安全提供者
		for(Provider provide : Security.getProviders()) {
			//打印当前提供者信息
			System.out.println(provide);
			//遍历提供者Set实体
			for(Map.Entry<Object, Object> entry : provide.entrySet()) {
				System.out.println("\t" + entry.getKey());
			}
		}
	}

}
