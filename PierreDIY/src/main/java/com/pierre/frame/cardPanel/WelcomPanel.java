package com.pierre.frame.cardPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pierre.frame.BuildCardLayout;

import io.pierre.tools.PropertyTools;

/**
 * 首页子面板
 * @author chenpiyang
 *
 */
public class WelcomPanel {
	private JTextField appidNoCertificateField;
	private JTextField appidCertificateField;
	private JTextField appCertificateField;
	private JTextField customerIDField;
	private JTextField customerCertificateField;
	private JTextField vendorIDField;
	private JTextField cnameField;
	private JTextField uidField;

	private JPanel mainPanel; //主面板，定义一个4行一列的表格布局
	
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel forthPanel;
	
	private JButton button;
	private JTextArea area;

	Map<String, String> map = null;
	String appid1FromProperties = null;
	String appid2FromProperties = null;
	String CerFromProperties = null;
	String customeridFromProperties = null;
	String cuscerFromProperties = null;
	String vidFromPreperties = null;
	String cnameFromProperties = null;
	String uidFromProperties = null;

	public WelcomPanel() {
		
	}
	
	public JPanel returnPanel(){
		map = PropertyTools.getMessage(System.getProperty("user.home") + "/property.txt");
		appid1FromProperties = map.get("appidNoCertificate");
		appid2FromProperties = map.get("appidCertificate");
		CerFromProperties = map.get("appCertificate");
		customeridFromProperties = map.get("customerID");
		cuscerFromProperties = map.get("customerCertificate");
		vidFromPreperties = map.get("vendorID");
		cnameFromProperties = map.get("cname");
		uidFromProperties = map.get("uid");

		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		forthPanel = new JPanel();
		
		//mainPanel = new JPanel(new GridLayout(4,1));
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		//第一行显示提示信息
		JLabel label = new JLabel("<html><font color=\"red\"><b>注意：</b>信息本地保存"
				+ "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<br></font></html>", SwingConstants.CENTER);
		
		firstPanel.add(label);
		
		//第二行输入域
		secondPanel.setLayout(new GridLayout(8, 2));

		appidNoCertificateField = new JTextField(appid1FromProperties, 21);
		appidCertificateField = new JTextField(appid2FromProperties, 21);
		appCertificateField = new JTextField(CerFromProperties, 21);
		customerIDField = new JTextField(customeridFromProperties, 21);
		customerCertificateField = new JTextField(cuscerFromProperties, 21);
		vendorIDField = new JTextField(vidFromPreperties, 21);
		cnameField = new JTextField(cnameFromProperties, 21);
		uidField = new JTextField(uidFromProperties, 21);

		secondPanel.add(new JLabel("未开app认证的appID（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(appidNoCertificateField);

		secondPanel.add(new JLabel("开了app认证的appID（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(appidCertificateField);

		secondPanel.add(new JLabel("App Certificate（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(appCertificateField);

		secondPanel.add(new JLabel("customerID（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(customerIDField);

		secondPanel.add(new JLabel("Customer Certificate（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(customerCertificateField);

		secondPanel.add(new JLabel("Vid（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(vendorIDField);

		secondPanel.add(new JLabel("用于测试的频道号（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(cnameField);

		secondPanel.add(new JLabel("用于测试的uid（非必填）: ", SwingConstants.RIGHT));
		secondPanel.add(uidField);

		
		//第3行显示按钮
				button = new JButton("保存在本地");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String appidNoCertificate = appidNoCertificateField.getText().trim();
						String appidCertificate = appidCertificateField.getText().trim();
						String appCertificate = appCertificateField.getText().trim();
						String customerID = customerIDField.getText().trim();
						String customerCertificate = customerCertificateField.getText().trim();
						String vendorID = vendorIDField.getText().trim();
						String cname = cnameField.getText().trim();
						String uid = uidField.getText().trim();

						Map<String, String> map = new HashMap<String, String>();
						map.put("appidNoCertificate", appidNoCertificate);
						map.put("appidCertificate", appidCertificate);
						map.put("appCertificate", appCertificate);
						map.put("customerID", customerID);
						map.put("customerCertificate", customerCertificate);
						map.put("vendorID", vendorID);
						map.put("cname", cname);
						map.put("uid", uid);
						
						//保存数据到属性文件，Mac home目录下的 property.txt
						PropertyTools.saveMessage(map);

						if (null == appidNoCertificate || appidNoCertificate.equals("")) {
							area.setText("");
							area.append("请填写信息再提交");
						} else {

							if (null != appidNoCertificate || !appidNoCertificate.equals("") || null != appidCertificate
									|| !appidCertificate.equals("") || null != appCertificate || !appCertificate.equals("")
									|| null != customerID || !customerID.equals("") || null != customerCertificate
									|| !customerCertificate.equals("") || null != vendorID || !vendorID.equals("")
									|| null != cname || !cname.equals("") || null != uid || !uid.equals("")) {
								JOptionPane.showMessageDialog(null, "保存成功", "提示", JOptionPane.DEFAULT_OPTION);
							} else {
								JOptionPane.showMessageDialog(null, "请填写正确信息", "提示", JOptionPane.DEFAULT_OPTION);
							}
							//button.setEnabled(false);
						}
					}
				});
				thirdPanel.add(button);

		//第4行显示文本
		area = new JTextArea();
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.append("CustomerID, Customer Certificate 从这里获取 https://console.agora.io/restfulApi\n"
				+ "保存目录为：System.getProperty(\"user.home\") + \"/property.txt\"  如：/Users/hanmeimei/property.txt"
				);
		forthPanel.add(new JScrollPane(area) {
    		public Dimension getPreferredSize() {return new Dimension(600, 300);}
    	});
		
		
		
		
		//setBounds(60, y, width, height);
		firstPanel.setBounds(10, 30, 600, 30);
		secondPanel.setBounds(0, 80, 600, 200);
		thirdPanel.setBounds(10, 300, 620, 30);
		forthPanel.setBounds(10, 350, 600, 400);
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(forthPanel);
		
		return mainPanel;
	}
}
