package fly.io.com.agora;

import net.sf.json.JSONObject;

public class TestJsonObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JSONObject json = new JSONObject();
		json.put("name", "John");
		System.out.println(json.toString());
	}

}
