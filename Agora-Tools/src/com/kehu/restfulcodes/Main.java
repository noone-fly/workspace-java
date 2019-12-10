package com.kehu.restfulcodes;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.util.JSON;

public class Main {
	private static String baseURL = "http://api.agora.io/dev/v1";
	private static String queryAllProjectsUrl = "/projectS/";
	private static String projectUrl = "/project/";
	private static String statusUrl = "/project_status/";
	private static String usageUrl = "/usage/";
	private static String channelUrl = "/channel/user/property";
	private static String channelInfoUrl = "/channel/";
	private static String customerID = "118f0fc996234f50bd2c8626e17dfd42";
	private static String certificate = "a840047a26fb408dbc11f1853f4c393b";
	private static String plainCredentials = customerID + ":" + certificate;
	// base64Credentials 就是你要的 Authorization 值，是一个使用 Base64 算法编码的 Credential
	private static String base64Credentials = new String(Base64.getEncoder()
			.encodeToString(plainCredentials.getBytes()));
	public static Map<String, String> header;

	public static Map<String, String> getHeader() {
		HashMap<String, String> header1 = new HashMap<String, String>();
		header1.put("Authorization", "Basic <" + base64Credentials + ">");
		return header1;
	}

	public static void setHeader(Map<String, String> header) {
		Main.header = header;
	}

	public static void delProject(String id, String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("name", name);
		String params = JSON.serialize(param);
		String result = HttpProvider.sendDeleteJson(baseURL + projectUrl,
				params, getHeader());
		System.out.println("result: " + result);
	}

	public static void editProjectStatus(String id, int status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		String params = JSON.serialize(param);
		String result = HttpProvider.sendJson(baseURL + statusUrl, params,
				getHeader(), "POST");
		System.out.println("result: " + result);
	}

	public static void getProject(String id, String name) {
		String url = "?id=" + id + "&name=" + name;
		String result = HttpProvider.sendJson(baseURL + projectUrl + url, null,
				getHeader(), "GET");
		System.out.println("result: " + result);
	}

	public static void getChannelUserInfo(String appId, String channelName) {
		String channel = "";
		if(!"".equals(channelName)){
			channel = "&channelName=" + channelName;
		}
		String url = "?appId=" + appId+channel;
		String result = HttpProvider.sendJson(baseURL + channelUrl + url, null,
				getHeader(), "GET");
		System.out.println("result: " + result);
	}
	
	public static void getChannelInfo(String appid) {
		String idurl = appid+"?page_no=0&page_size=20";
//		                      "?page_no=0&page_size=100"
		String result = HttpProvider.sendJson(baseURL + channelInfoUrl+idurl,null, getHeader(),"GET");
		System.out.println("result: " + result);
	}

	public static void getProjectUsage() {
		// String url = "?id="+id+"&name="+name;
		String result = HttpProvider.sendJson(baseURL + usageUrl, null,
				getHeader(), "GET");
		System.out.println("result: " + result);
	}

	public static void getProjects() {
		String result = HttpProvider.sendJson(baseURL + queryAllProjectsUrl,
				null, getHeader(), "GET");
		System.out.println("result: " + result);
	}

	public static void createProject(String name, Boolean enable_sign_key) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);// 项目名
		param.put("enable_sign_key", enable_sign_key);// 是否需要秘钥
		String params = JSON.serialize(param);
		String result = HttpProvider.sendJson(baseURL + projectUrl, null,
				getHeader(), "POST");
		System.out.println("result: " + result);
		// result:{
		// "project":{
		// "company_id":147820,//
		// "name":"projectx",//项目名
		// "id":"B1rAFDfrH",//项目id
		// "created":1566894541485,//创建时间
		// "status":1,//项目状态
		// "vendor_key":"8a5b1f62c4f14abb90890d48d7645c26",//APPid
		// "sign_key":"be48169557594786a969e4c21870c41a"//秘钥
		// }
		// }
	}

	public static void main(String[] args) {
//		 createProject("testac1",false);//创建
		// editProjectStatus("Hk12QqzrB",0);//创建
		// getProjectUsage();//创建
		//getProjects();// 查询所有项目信息 OK
//		getChannelUserInfo("bdc9061aa16a4d77b56e9ee0e5647f63","fyx");
		getChannelInfo("fd33b96dc23c417fac91579efff42701");//查询频道信息
//		 getProject("nofnoSJWjZ","hollycrmVideo");//查询单个项目
		// delProject("B1rAFDfrH","projectx");//删除某个项目
	}
//	"projects":[
//	            {"id":"Hk12QqzrB","name":"test1","vendor_key":"376db0b430ce4fa7ac44978d64ee18d7","sign_key":"0bbcb14c7a8142a9bc82f00853f40ebc","recording_server":null,"status":0,"created":1566905254820},
//	            {"id":"C2MNa8ymLj","name":"test appId","vendor_key":"40c54caae122454c9b25c7dde76e91ae","sign_key":"","recording_server":null,"status":1,"created":1566443577986},
//	            {"id":"THCd6Nwro0","name":"videoTest","vendor_key":"6fe3e64b968d47deac2415c4cdf191f0","sign_key":"aaddfc2832f940d7a815404462e360f4","recording_server":null,"status":0,"created":1566273451263},
//	            {"id":"nofnoSJWjZ","name":"hollycrmVideo","vendor_key":"bdc9061aa16a4d77b56e9ee0e5647f63","sign_key":"","recording_server":null,"status":1,"created":1566272734588}
}
