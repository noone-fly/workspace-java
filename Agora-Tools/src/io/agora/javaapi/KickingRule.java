package io.agora.javaapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class KickingRule {

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost httpPost = new HttpPost("https://api.agora.io/dev/v1/project");
//
//		// username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
//		String encoding = DatatypeConverter.printBase64Binary("118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b".getBytes("UTF-8"));
//		
//		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
//		nameValuePair.add(new BasicNameValuePair("name","3234"));
//		nameValuePair.add(new BasicNameValuePair("enable_sign_key","true"));
//		
//		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
//		httpPost.setHeader("Authorization", "Basic " + encoding);
//		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
//		
//		
//		CloseableHttpResponse response = httpClient.execute(httpPost);
//		HttpEntity entity = response.getEntity();
//		String content = EntityUtils.toString(entity, "utf-8");
//		System.out.println(content);
//		System.out.println(httpPost.getURI());
//		System.out.println(response);
//		httpClient.close();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.agora.io/dev/v1/kicking-rule"); //踢人地址

		// customerID:customer Certificate--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
		String encoding = DatatypeConverter.printBase64Binary("118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b".getBytes("UTF-8"));
		System.out.println(encoding);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("appid","fd33b96dc23c417fac91579efff42701")); //你的app ID
		nameValuePair.add(new BasicNameValuePair("cname","147258")); //频道号
		nameValuePair.add(new BasicNameValuePair("uid","258435777")); //需要踢的uid
		nameValuePair.add(new BasicNameValuePair("ip","")); // 如果不根据IP来封人，就填空
		nameValuePair.add(new BasicNameValuePair("time","5")); //封人时间
		
		httpPost.setHeader("Authorization", "Basic " + encoding);
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));

		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println(content);
		System.out.println(httpPost.getURI());
		System.out.println(response);
		httpClient.close();
	}
}
