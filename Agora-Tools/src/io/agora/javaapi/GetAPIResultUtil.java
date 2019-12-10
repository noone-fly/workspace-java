package io.agora.javaapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;


public class GetAPIResultUtil {
	
	public static void main(String[] args) {
		//227f2c7196ae497a8fad174b116b7436
		String url = "http://api.agora.io/dev/v1/channel/fd33b96dc23c417fac91579efff42701/?page_no=0&page_size=100";
		String param = "";
		
		System.out.println(getAPIResult(url, param));
	}
	
	public static String getAPIResult(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection)realUrl.openConnection();
			//URLConnection conn = realUrl.openConnection();
			String plainCredentials = "118f0fc996234f50bd2c8626e17dfd42:a840047a26fb408dbc11f1853f4c393b";
			
			Base64.Encoder encoder = Base64.getEncoder();
			String base64Credentials = new String(encoder.encode(plainCredentials.getBytes("UTF-8")));
			String base64Credentials1 = new String(Base64.getEncoder().encodeToString(plainCredentials.getBytes()));
			System.out.println(base64Credentials);
			System.out.println(base64Credentials1);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Basic " + base64Credentials);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while((line = in.readLine()) != null) {
				result += line;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				in.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
