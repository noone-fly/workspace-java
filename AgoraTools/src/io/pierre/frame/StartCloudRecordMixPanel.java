package io.pierre.frame;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class StartCloudRecordMixPanel extends JPanel {
	public StartCloudRecordMixPanel() {
		JPanel panelNorth = new JPanel();
		JTextArea java = new JTextArea();

		// textArea.append("下载详单，请移步 https://archer.agoralab.co/v2/search");

		JLabel label = new JLabel("<html><font color=\"red\"><br><b>云录制和RTC RESTful一样，都是GET/POST请求，可用Postman来测试</b>"
				+ "<br>" + "可参考如下页面的请求示例" + "<br>" + "客户常用的有Java，PHP，curl命令方式的请求方式，也可以参考该页面的示例" + "<br>"
				+ "<br></font><a href=\"https://confluence.agoralab.co/pages/viewpage.action?pageId=638995960\">https://confluence.agoralab.co/pages/viewpage.action?pageId=638995960</a></html>",
				SwingConstants.CENTER);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					// Runtime.getRuntime().exec("https://archer.agoralab.co/v2/search");
					Desktop.getDesktop()
							.browse(new URI("https://confluence.agoralab.co/pages/viewpage.action?pageId=638995960"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		panelNorth.add(label);
		
		

		add(panelNorth, BorderLayout.NORTH);

		// 中部分
		java = new JTextArea(25, 60);
		java.setLineWrap(true);
		java.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(java);

		java.append("curl命令方式发起请求示例：" + "\n\n"
				+ "$ curl -u <customerID>:<customer certificate> https://api.agora.io/v1/apps/<appid>/cloud_recording/acquire -H \"Content-Type:application/json\" -d '{\"cname\":\"<频道号>\",\"uid\":\"<录制uid>\",\"clientRequest\":{}}'"
				+ "\n\n\n" + "PHP请求示例：" + "\n\n" + "<?php" + "\n"
				+ "   $url = \"https://api.agora.io/v1/apps/<your appid>/cloud_recording/acquire\";" + "\n"
				+ "   $arr_header[] = \"Content-Type:application/json\";" + "\n"
				+ "   $arr_header[] = \"Authorization: Basic \".base64_encode(\"<your customerID>:<your customerCertificate>\"); //http basic auth"
				+ "\n\n" + "   $data = array('cname'=>'<cname>','uid'=>'<录制uid>','clientRequest'=>json_decode(\"{}\"));"
				+ "\n" + "   $data_json = json_encode($data);" + "\n" + "   echo $data_json;" + "\n\n"
				+ "   $ch = curl_init();" + "\n" + "   curl_setopt($ch, CURLOPT_URL, $url ); //request url" + "\n"
				+ "   curl_setopt($ch, CURLOPT_POST, 1); //post提交方式" + "\n"
				+ "   curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);" + "\n"
				+ "   curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); //print" + "\n\n" + "   if(!empty($arr_header)){"
				+ "\n" + "      curl_setopt($ch, CURLOPT_HTTPHEADER, $arr_header);" + "\n" + "   }" + "\n"
				+ "   $response = curl_exec($ch);" + "\n" + "   curl_close($ch);" + "\n" + "   echo $response;" + "\n"
				+ "   return json_decode($response);" + "\n" + "?>" + "\n\n\n" + "java请求示例：" + "\n"
				+ "HttpClient httpClient = HttpClients.createDefault();" + "\n"
				+ "HttpPost httpPost = new HttpPost(\"https://api.agora.io/v1/apps/<appid>/cloud_recording/acquire\");"
				+ "\n"
				+ "String encoding = DatatypeConverter.printBase64Binary(\"<customer ID>:<customer certificate>\".getBytes(\"UTF-8\"));"
				+ "\n"
				+ "String body = \"{\\n \\\"cname\\\":\\\"<频道号>\\\",\\n \\\"uid\\\":\\\"<录制uid>\\\",\\n \\\"clientRequest\\\":{}\\n}\";"
				+ "\n" + "httpPost.setHeader(\"Content-type\", \"application/json;charset=utf-8\");" + "\n"
				+ "httpPost.setHeader(\"Authorization\", \"Basic \" + encoding);" + "\n"
				+ "httpPost.setEntity(new StringEntity(body));" + "\n"
				+ "HttpResponse response = httpClient.execute(httpPost);" + "\n"
				+ "HttpEntity entity = response.getEntity();" + "\n"
				+ "String content = EntityUtils.toString(entity, \"utf-8\");" + "\n"

		);

		add(scrollPane, BorderLayout.CENTER);

	}
}
