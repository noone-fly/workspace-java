package io.pierre.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.agora.media.sample.AccessTokenSample;
import io.pierre.tools.DateTools;
import io.pierre.tools.PropertyTools;
import io.pierre.tools.ValidateField;

public class VarifyRtcTokenPanel extends JPanel {
	
	private JTextArea tokenArea;
	private JTextField appIDField;  // app ID
	private JTextField appCerField; // app certificate
	private JTextField cnameField;  // channel name
	private JTextField uidField;    // uid
	
	private JTextArea displayArea;
	private JPanel northPanel;
	private JPanel southPanel;
	private JButton validateToken;
	
	Map<String, String> map = null;
	String appidFromProperties = null;
	String CerFromProperties = null;
	String cnameFromProperties = null;
	String uidFromProperties = null;
	
	public VarifyRtcTokenPanel() {

		map = PropertyTools.getMessage(System.getProperty("user.home") + "/property.txt");
		
		appidFromProperties = map.get("appidCertificate");
		CerFromProperties = map.get("appCertificate");
		cnameFromProperties = map.get("cname");
		uidFromProperties = map.get("uid");

		
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(5,3)); //创建5行两列的表格
		
		JLabel label = new JLabel("<html><font color=\"red\"><b>说明：</b>token报错时<br>"
				+ "可以用JoinChannel或CreateChannel中的参数（token字符串，频道号，uid，appid）验证一下的传参的正确性<br>"
				+ "原理就是拿生成token的参数，和JoinChannel或CreateChannel中传的参数进行对比<br>"
				+ "如果一致则返回 *** validate is true，不一致则返回 *** validate is false<br><br>"
				+ "</font></html>"
				, SwingConstants.CENTER);
		add(label);
		
		tokenArea = new JTextArea(2, 21);
		tokenArea.setText("客户提供的 token 字符串");
		JScrollPane scrollPane1 = new JScrollPane(tokenArea);
		add(scrollPane1);
		
		tokenArea.addMouseListener(new MouseListener() {
			@Override  //鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override //鼠标按下时执行
			public void mousePressed(MouseEvent e) {tokenArea.setText("");}
			@Override  //鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override  //鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override  //鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		
		appIDField = new JTextField(appidFromProperties, 21);
		appCerField = new JTextField(CerFromProperties, 21);
		cnameField = new JTextField(cnameFromProperties, 21);
		uidField = new JTextField(uidFromProperties, 21);
		
		
		northPanel.add(new JLabel("App ID: ", SwingConstants.RIGHT));
		northPanel.add(appIDField);
		northPanel.add(new JLabel("客户提供的 app ID", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("App Certificate: ", SwingConstants.RIGHT));
		northPanel.add(appCerField);
		northPanel.add(new JLabel("客户的app认证，建议不要直接跟客户要", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Channel Name: ", SwingConstants.RIGHT));
		northPanel.add(cnameField);
		northPanel.add(new JLabel("客户提供的频道号", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Uid: ", SwingConstants.RIGHT));
		northPanel.add(uidField);
		northPanel.add(new JLabel("客户提供的 uid", SwingConstants.LEFT));
		
		
		appIDField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {appIDField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		appCerField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {appCerField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		cnameField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {cnameField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		uidField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {uidField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		
		
		add(northPanel, BorderLayout.NORTH); //添加到子面板上部
		
		//中部分
		
		displayArea = new JTextArea(17,60);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		add(scrollPane, BorderLayout.CENTER);
				
		//下部分
		southPanel = new JPanel();
		validateToken = new JButton("验证Token");
		southPanel.add(validateToken);
		
		validateToken.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String token = tokenArea.getText().trim();
				String appid = appIDField.getText().trim();
				String appCer = appCerField.getText().trim();
				String cname = cnameField.getText().trim();
				String uid = uidField.getText().trim();
				
				// validate appid
				String validateAppID = ValidateField.validateAppid(appid.trim());
				String validateAppCer = ValidateField.validateAppid(appCer.trim());
				String validateCname = ValidateField.validateChannelName(cname.trim());
				String validateUid = ValidateField.validateUid(uid.trim());
				
				if (null == token || token.equals("")) {
					displayArea.setText("");
					displayArea.append("Token 不能为空");
				} else if (null == appid || appid.equals("")) {
					displayArea.setText("");
					displayArea.append("Appid 不能为空");
				} else if ("invalid" == validateAppID) {
					displayArea.setText("");
					displayArea.append("Appid 必须是32的字符串，且只包含小写字母和数字字符");
				} else if (null == appCer || appCer.equals("")) {
					displayArea.setText("");
					displayArea.append("App Certificate 不能为空");
				} else if ("invalid" == validateAppCer) {
					displayArea.setText("");
					displayArea.append("App Certificate 必须是32的字符串，且只包含小写字母和数字字符");
				} else if (null == cname || cname.equals("")) {
					displayArea.setText("");
					displayArea.append("Channel Name 不能为空");
				} else if ("invalid" == validateCname) {
					displayArea.setText("");
					displayArea.append(
							"频道号长度在 64 字节以内的字符串，所允许 89 个字符，请查阅文档 \n https://docs.agora.io/cn/Interactive%20Broadcast/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_rtc_engine.html#a8b308c9102c08cb8dafb4672af1a3b4c");
				} else if (null == uid || uid.equals("")) {
					displayArea.setText("");
					displayArea.append("uid 不能为空");
				} else if ("correct" == validateUid && Long.parseLong(uid) > 4294967295L) {
					displayArea.setText("");
					displayArea.append("Uid 不能大于4294967295，即范围是1到(2^32-1)");
				} else if ("invalid" == validateUid) {
					displayArea.setText("");
					displayArea.append("Uid必须是数字，而且也不能以0开头");
				} else {
					displayArea.setText("");
					
					Map<String, String> map1 = new HashMap<String, String>();
					map1.put("token", token);
					map1.put("appCer", appCer);
					map1.put("appid", appid);
					map1.put("cname", cname);
					map1.put("uid", uid);
					
					String[] str = AccessTokenSample.validateToken(map1);
					String appid_positive = str[0];
					String crc_cname_positive = str[1];
					String crc_uid_positive = str[2];
					String ts_positive = str[3];
					String crc_cname_negative = str[4];
					String crc_uid_negative = str[5];
					
					System.out.println("crc_cname_positive="+crc_cname_positive + " : crc_cname_negative=" + crc_cname_negative);
					System.out.println("crc_uid_positive="+crc_uid_positive + " : crc_uid_negative=" + crc_uid_negative);
					
					
					if (appid.equals(appid_positive)) {
						displayArea.append("Appid validate is true \n" );
					}else {
						displayArea.append("Appid validate is false \n" );
					}
					if (crc_cname_negative.equals(crc_cname_positive)) {
						displayArea.append("Channel name validate is true \n" );
					}else {
						displayArea.append("Channel name validate is false \n" );
					}
					if (crc_uid_negative.equals(crc_uid_positive)) {
						displayArea.append("Uid validate is true \n" );
					}else {
						displayArea.append("Uid validate is false \n" );
					}
					displayArea.append("\n\n备注：");
					displayArea.append("\n时间UTC+8：" + DateTools.timestampToDate(Integer.parseInt(ts_positive)) + "\n");
					//displayArea.append("App Certificate 暂时不支持校验，请自行确认配置正确性");
				}
		
			}
		});
	
		add(validateToken, BorderLayout.SOUTH);
	}
}
