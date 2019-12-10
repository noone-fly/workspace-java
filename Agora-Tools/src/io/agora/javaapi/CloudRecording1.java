package io.agora.javaapi;

import java.io.IOException;
import javax.xml.bind.DatatypeConverter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CloudRecording1 {
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		HttpClient httpClient = HttpClients.createDefault();
		//
		HttpPost httpPost = new HttpPost("https://api.agora.io/v1/apps/4aeb01b2decb4d379df8c760b747fe57/cloud_recording/acquire");

		// username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token
		//adf30dcacfc54743a955e2ccd95b0edb:ea33ac7f43cf4403ac1a285958e2026a
		//118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b
		String encoding = DatatypeConverter.printBase64Binary("118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b".getBytes("UTF-8"));
		System.out.println(encoding);
		String body = "{\"cname\":\"12345678\",\"uid\":\"48025\",\"clientRequest\":{}}";
		
		httpPost.setHeader("Content-type", "application/json;charset=utf-8");
		httpPost.setHeader("Authorization", "Basic " + encoding);
		httpPost.setEntity(new StringEntity(body));
		
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println(content);
		System.out.println(httpPost.getURI());
		System.out.println(response);
	}
}
