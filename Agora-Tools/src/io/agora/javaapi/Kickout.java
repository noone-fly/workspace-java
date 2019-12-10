package io.agora.javaapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Kickout {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.agora.io/dev/v1/kicking-rule");

		String cusID = "118f0fc996234f50bd2c8626e17dfd42";
		String cusCer = "a840047a26fb408dbc11f1853f4c393b";
		String appid ="fd33b96dc23c417fac91579efff42701";
		String cname = "147258";
		String uid = "2843680907";
		String ip = "";
		String time = "0";

		// username:password--->访问的用户名，密码,并使用base64进行加密，将加密的字节信息转化为string类型，encoding--->token

		String input = cusID + ":" + cusCer;
		String encoding = DatatypeConverter.printBase64Binary(input.getBytes("UTF-8"));

		String body = "{\"appid\":\"" + appid + "\"," + "\"cname\":\"" + cname + "\"," + "\"uid\":\"" + uid + "\","
				+ "\"ip\":\"" + ip + "\"," + "\"time\":\"" + time + "\"" + "}";
		
		System.out.println(body);

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
