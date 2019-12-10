package io.agora.javaapi;

public class OkHttpRecording {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://www.xxx.com/api/test";


		OkHttpClient httpClient = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

		String post = "{\"test\":123}";


		RequestBody requestBody = RequestBody.create(mediaType, post);


		Request request = new Request.Builder()
		        .post(requestBody)
		        .url(url)
		        .build();

		Response response = httpClient.newCall(request).execute();
	}

}
