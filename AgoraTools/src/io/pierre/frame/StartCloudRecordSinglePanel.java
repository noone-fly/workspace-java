package io.pierre.frame;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartCloudRecordSinglePanel extends JPanel {
	public StartCloudRecordSinglePanel() {

		JPanel panel = new JPanel();

		// textArea.append("下载详单，请移步 https://archer.agoralab.co/v2/search");

		JLabel label = new JLabel(
				"<html><font color=\"red\"><br><b>云录制和RTC RESTful一样，都是GET/POST请求，可用Postman来测试</b>" + "<br>"
						+"可参考如下页面的请求示例"+"<br>"
						+ "<br></font><a href=\"https://confluence.agoralab.co/pages/viewpage.action?pageId=638995960\">https://confluence.agoralab.co/pages/viewpage.action?pageId=638995960</a></html>"
						+"客户常用的有Java，PHP，curl命令方式的请求方式，也可以参考该页面的示例代码"
						,
				SwingConstants.CENTER);

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				try {
					//Runtime.getRuntime().exec("https://archer.agoralab.co/v2/search");
					Desktop.getDesktop().browse(new URI("https://archer.agoralab.co/v2/search"));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		panel.add(label);

		add(panel, BorderLayout.CENTER);
	}
}
