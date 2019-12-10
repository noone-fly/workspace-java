package com.kehu.restfulcodes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Cai Zhibin
 * 
 */
public class HttpProvider {
	private static Logger log = Logger.getLogger(HttpProvider.class.getName());

	public static String sendPost(String url, byte[] data, int timeout)
			throws Exception {
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			String result = "";
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(timeout);
			con.setReadTimeout(timeout);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				return result;
			} else {

			}

		} catch (IOException e) {

			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}

			if (con != null) {
				try {
					con.getInputStream().close();
				} catch (Throwable e) {

				}
				try {
					con.getOutputStream().close();
				} catch (Throwable e) {

				}
				con.disconnect();
			}
		}
		return null;
	}

	/**
	 * 封装POST发送百度会话管理
	 * 
	 * @param url
	 * @param data
	 * @param AISessionDevToken
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String url, byte[] data,
			String AISessionDevToken) throws Exception {
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			String result = "";
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			String Authorization = "AICP " + AISessionDevToken;
			con.setRequestProperty("Authorization", Authorization);
			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				return result;
			} else {

			}

		} catch (IOException e) {

			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}

			if (con != null) {
				try {
					con.getInputStream().close();
				} catch (Throwable e) {

				}
				try {
					con.getOutputStream().close();
				} catch (Throwable e) {

				}
				con.disconnect();
			}
		}
		return null;
	}

	public static String sendPost(String url, byte[] data) throws Exception {
		return sendPost(url, data, 10000);
	}

	public static String sendPostJson(String strURL, String params) {
		return sendPostJson(strURL, params, null);
	}

	public static String sendPostJson(String strURL, String params,
			Map<String, String> requestHeaders) {
		return sendJson(strURL, params, requestHeaders, "POST");
	}

	public static String sendDeleteJson(String strURL, String params,
			Map<String, String> requestHeaders) {
		return sendJson(strURL, params, requestHeaders, "DELETE");
	}

	public static String sendJson(String strURL, String params,
			Map<String, String> requestHeaders, String method) {
		String result = "";
		try {
			URL url = new URL(strURL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod(method == null ? "POST" : method);
			connection.setRequestProperty("Accept",
					"application/json;charset=utf-8");
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			if (requestHeaders != null && requestHeaders.size() > 0) {
				Set<String> requestHeaderSet = requestHeaders.keySet();
				for (String header : requestHeaderSet) {
					connection.setRequestProperty(header,
							requestHeaders.get(header));
					System.out.println("设置了请求头"+header+requestHeaders.get(header));
				}
				
			}
			connection.connect();
			if ((method.equals("POST") && params != null)
					|| (method.equals("PUT") && params != null)
					|| (method.equals("DELETE") && params != null)) {
				OutputStreamWriter out = new OutputStreamWriter(
						connection.getOutputStream(), "UTF-8");
				out.append(params);
				out.flush();
				out.close();
			}
			 System.out.println(connection.getResponseMessage());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			result = sb.toString();
			// System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sendGet(String url, int timeout, String encoding)
			throws Exception {
		String result = "";
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(timeout);
			con.setConnectTimeout(timeout);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(
						con.getInputStream(), encoding));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				return result;
			} else {

			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null)
				in.close();
			if (con != null) {
				try {
					con.getInputStream().close();
				} catch (Throwable e) {

				}
				try {
					con.getOutputStream().close();
					con.disconnect();
				} catch (Throwable e) {

				}
			}
		}
		return result;
	}

	public static String sendGet(String url, int timeout) throws Exception {
		return sendGet(url, timeout, "UTF-8");
	}

	public static String sendGet(String url) throws Exception {
		return sendGet(url, 10000, "UTF-8");
	}

	public static String sendFile(String url, String filename) throws Exception {
		File file = new File(filename);
		if (!file.exists())
			throw new Exception("File [" + filename + "] not found");
		HttpURLConnection con = null;
		BufferedReader in = null;
		BufferedOutputStream out = null;
		InputStream fileIn = null;
		String result = null;
		try {
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();

			fileIn = new BufferedInputStream(new FileInputStream(file));
			con.setFixedLengthStreamingMode(fileIn.available());
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setReadTimeout(3600000);

			out = new BufferedOutputStream(con.getOutputStream());

			byte[] data = new byte[4096];
			int count = -1;
			while ((count = fileIn.read(data)) != -1) {
				out.write(data, 0, count);
			}
			out.flush();
			out.close();
			fileIn.close();

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line;
				result = "";
				while ((line = in.readLine()) != null) {
					result += line;
				}
				return result;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
			try {
				if (in != null)
					out.close();
			} catch (Exception e) {
			}
			try {
				con.getInputStream().close();
			} catch (Throwable e) {

			}
			try {
				con.getOutputStream().close();
			} catch (Throwable e) {

			}
			try {
				if (con != null)
					con.disconnect();
			} catch (Exception e) {

			}
		}
		return result;
	}

	public static String downLoadFile(String fileUrl, String filePath,
			String fileName) throws Exception {
		String result = "";
		URL theURL;
		HttpURLConnection con = null;
		InputStream in = null;
		FileOutputStream os = null;
		try {
			theURL = new URL(fileUrl);
			con = (HttpURLConnection) theURL.openConnection();
			if (filePath != null && !"".equalsIgnoreCase(filePath)) {
				byte[] buffer = new byte[4 * 1024];
				int read;
				File fileDir = new File(filePath);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				result = filePath + "/" + fileName;
				in = con.getInputStream();
				os = new FileOutputStream(result);
				while ((read = in.read(buffer)) > 0) {
					os.write(buffer, 0, read);
				}
				os.close();
				in.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
			try {
				if (os != null)
					os.close();
			} catch (Exception e) {
			}
			try {
				con.getInputStream().close();
			} catch (Throwable e) {

			}
			try {
				con.getOutputStream().close();
			} catch (Throwable e) {

			}
			try {
				if (con != null)
					con.disconnect();
			} catch (Exception e) {

			}

		}
		return result;
	}

	public static String getCookie(String urlString, String sessionKey)
			throws Exception {
		HttpURLConnection con = null;
		try {
			URL httpUrl = new URL(urlString);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("GET");
			con.setReadTimeout(10000);
			con.setConnectTimeout(3000);
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				String line, result = "";
				while ((line = in.readLine()) != null) {
					result += line;
				}

				String cookies = null;
				Map<String, List<String>> map = con.getHeaderFields();
				Set<String> set = map.keySet();
				for (Iterator iterator = set.iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if (key == null) {
						continue;
					}
					if (key.equals("Set-Cookie")) {
						List<String> list = map.get(key);
						StringBuilder builder = new StringBuilder();
						for (String str : list) {
							builder.append(str + "; ").toString();
						}
						cookies = builder.toString();
						return cookies;
					}
				}
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "获取Http cookie失败", e);
		} finally {
			if (con != null) {
				try {
					con.getInputStream().close();
				} catch (Throwable e) {

				}
				try {
					con.getOutputStream().close();
				} catch (Throwable e) {

				}
				con.disconnect();
			}
		}
		return null;
	}

	public static String sendPostWithCookie(String url, byte[] data,
			String cookie) throws Exception {
		HttpURLConnection con = null;
		BufferedReader in = null;
		try {
			String result = "";
			URL httpUrl = new URL(url);
			con = (HttpURLConnection) httpUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(3000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty("Cookie", cookie);

			OutputStream out = con.getOutputStream();
			out.write(data);
			out.flush();
			out.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				return result;
			} else {

			}

		} catch (IOException e) {

			throw e;
		} finally {
			if (in != null) {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
				}
			}

			if (con != null) {
				try {
					con.getInputStream().close();
				} catch (Throwable e) {

				}
				try {
					con.getOutputStream().close();
				} catch (Throwable e) {

				}
				con.disconnect();
			}
		}
		return null;
	}
}
