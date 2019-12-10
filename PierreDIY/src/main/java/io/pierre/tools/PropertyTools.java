package io.pierre.tools;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertyTools {

	public static Map<String, String> getMessage(String file) {
		FileReader fileReader=null;
		
		File file2 = new File(file);
		Map<String, String> map = new HashMap<String, String>();
		if (!file.equals("")) {
			if (!file2.exists()) {
				try {
					// createNewFile（）方法默认的权限是644
					file2.createNewFile();
					file2.setWritable(true);// 设置可写权限
					file2.setExecutable(true);// 设置可执行权限
					file2.setReadable(true);// 设置可读权限
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Properties properties = new Properties();

		try {
			// properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(file));
			
			fileReader = new FileReader(file2);
			properties.load(fileReader);
			map.put("appidNoCertificate", properties.getProperty("appidNoCertificate"));
			map.put("appidCertificate", properties.getProperty("appidCertificate"));
			map.put("appCertificate", properties.getProperty("appCertificate"));
			map.put("customerID", properties.getProperty("customerID"));
			map.put("customerCertificate", properties.getProperty("customerCertificate"));
			map.put("vendorID", properties.getProperty("vendorID"));
			map.put("cname", properties.getProperty("cname"));
			map.put("uid", properties.getProperty("uid"));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

	public static void saveMessage(Map<String, String> map) {

		// Map<String, String> map = new HashMap<String, String>();
		String appidNoCertificate = map.get("appidNoCertificate");
		String appidCertificate = map.get("appidCertificate");
		String appCertificate = map.get("appCertificate");
		String customerID = map.get("customerID");
		String customerCertificate = map.get("customerCertificate");
		String vendorID = map.get("vendorID");
		String cname = map.get("cname");
		String uid = map.get("uid");

		// https://blog.csdn.net/weixin_42591732/article/details/94720903
		// 属性集合类不支持泛型
		Properties properties = new Properties();
		// 添加键值对
		properties.setProperty("appidNoCertificate", appidNoCertificate);
		properties.setProperty("appidCertificate", appidCertificate);
		properties.setProperty("appCertificate", appCertificate);
		properties.setProperty("customerID", customerID);
		properties.setProperty("customerCertificate", customerCertificate);
		properties.setProperty("vendorID", vendorID);
		properties.setProperty("cname", cname);
		properties.setProperty("uid", uid);

		PrintWriter printWriter = null;

		try {
			// 创建自动刷新字符打印流对象
			//printWriter = new PrintWriter(new FileWriter(System.getProperty("user.dir") + "/src/property.txt"), true);
			printWriter = new PrintWriter(new FileWriter(System.getProperty("user.home") + "/property.txt"), true);
			// 拿到properties中所有的key
			Set keys = properties.keySet();
			// 遍历所有的key
			for (Object object : keys) {
				// 将object强转成String
				String key = (String) object;

				// 通过key获取到相对应的值
				String value = properties.getProperty(key);

				// key和值写入到文件中
				printWriter.println(key + "=" + value);
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}

	}
}
