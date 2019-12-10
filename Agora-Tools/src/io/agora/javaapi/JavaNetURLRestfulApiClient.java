package io.agora.javaapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class JavaNetURLRestfulApiClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		//认证信息对象，用于包含访问翻译服务的用户名和密码
		String path = "https://api.agora.io/dev/v1/project/?id=fd33b96dc23c417fac91579efff42701&name=IOS";
		//String path = "https://api.agora.io/dev/v1/projects/";
		//String path = "https://api.agora.io/dev/v1/usage/?from_date=2018-01-01&to_date=2018-04-11&projects=IOS";
		//String path = "http://api.agora.io/dev/v1/channel/user/property/1/w/1";
		//String path = "http://api.agora.io/dev/v1/channel/fd33b96dc23c417fac91579efff42701/";
		//String path = "http://api.agora.io/dev/v1/channel/user/fd33b96dc23c417fac91579efff42701/12356";

		// 1.创建客户端访问服务器的httpclient对象 打开浏览器
		//HttpClient httpclient = new DefaultHttpClient();
		HttpClient httpclient = HttpClientBuilder.create().build();
		// 2.以请求的连接地址创建get请求对象 浏览器中输入网址
		HttpGet httpget = new HttpGet(path);
		//HttpPost httpPost = new HttpPost(path);

		// username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
		String encoding = DatatypeConverter.printBase64Binary(
				"118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b".getBytes("UTF-8"));

		httpget.setHeader("Authorization", "Basic " + encoding);
		// 3.向服务器端发送请求 并且获取响应对象 浏览器中输入网址点击回车
		HttpResponse response = httpclient.execute(httpget);
		// 4.获取响应对象中的响应码
		StatusLine statusLine = response.getStatusLine();// 获取请求对象中的响应行对象
		int responseCode = statusLine.getStatusCode();// 从状态行中获取状态码

		System.out.println(responseCode);
		if (responseCode == 200) {
			// 5. 可以接收和发送消息
			HttpEntity entity = response.getEntity();
			// 6.从消息载体对象中获取操作的读取流对象
			InputStream input = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String str1 = br.readLine();
			String result = new String(str1.getBytes("gbk"), "utf-8");
			System.out.println("服务器的响应是:" + result);
			br.close();
			input.close();
		} else {
			System.out.println("响应失败!");
		}
	}

}
