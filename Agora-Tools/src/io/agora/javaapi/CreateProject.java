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

public class CreateProject {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.agora.io/dev/v1/project/");

		// username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
		String encoding = DatatypeConverter.printBase64Binary("9521f78d18a34213b60d9f5e517bbd61:52d8363b41c64afba1208585fa0e33f9".getBytes("UTF-8"));
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("name","createproject20190215"));
		nameValuePair.add(new BasicNameValuePair("enable_sign_key","true"));
		
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
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
