package io.pierre.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.pierre.rtcrestfulapi.RestfulAPITools;
import io.pierre.tools.PropertyTools;

/**
 * 
 * @author chenpiyang
 *
 */
public class QueryUserStatusPanel extends JPanel {
	private JTextField appidField;
	private JTextField cnameField;
	private JTextField uidField;
	private JTextField cusIdField;
	private JTextField cusCerField;
	
	private JTextArea displayArea;
	private JPanel northPanel;
	private JPanel southPanel;
	private JButton queryUserStatus;
	
	Map<String, String> map = null;
	String appidFromProperties = null;
	String cnameFromProperties = null;
	String uidFromProperties = null;
	String cusIdFromProperties = null;
	String cusCerFromProperties = null;
	
	public QueryUserStatusPanel() {
		map = PropertyTools.getMessage(System.getProperty("user.home") + "/property.txt");
		
		appidFromProperties = map.get("appidNoCertificate");
		cnameFromProperties = map.get("cname");
		uidFromProperties = map.get("uid");
		cusIdFromProperties = map.get("customerID");
		cusCerFromProperties = map.get("customerCertificate");

		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(5,3)); //创建5行两列的表格
		
		appidField = new JTextField(appidFromProperties, 21);
		cnameField = new JTextField(cnameFromProperties, 21);
		uidField = new JTextField(uidFromProperties, 21);
		cusIdField = new JTextField(cusIdFromProperties, 21);
		cusCerField = new JTextField(cusCerFromProperties, 21);
		
		
		northPanel.add(new JLabel("App ID: ", SwingConstants.RIGHT));
		northPanel.add(appidField);
		northPanel.add(new JLabel("app ID", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Channel Name: ", SwingConstants.RIGHT));
		northPanel.add(cnameField);
		northPanel.add(new JLabel("频道号", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Uid: ", SwingConstants.RIGHT));
		northPanel.add(uidField);
		northPanel.add(new JLabel("uid", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Customer ID: ", SwingConstants.RIGHT));
		northPanel.add(cusIdField);
		northPanel.add(new JLabel("客户ID", SwingConstants.LEFT));
		
		northPanel.add(new JLabel("Customer Certificate: ", SwingConstants.RIGHT));
		northPanel.add(cusCerField);
		northPanel.add(new JLabel("客户认证", SwingConstants.LEFT));
		
		
		appidField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {appidField.setText("");}
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
		cusIdField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {cusIdField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		cusCerField.addMouseListener(new MouseListener() {
			@Override // 鼠标按下，释放时就执行
			public void mouseReleased(MouseEvent e) {}
			@Override // 鼠标按下时执行
			public void mousePressed(MouseEvent e) {cusCerField.setText("");}
			@Override // 鼠标离开组件时执行
			public void mouseExited(MouseEvent e) {}
			@Override // 鼠标回到组件时执行，离开组件不会执行
			public void mouseEntered(MouseEvent e) {}
			@Override // 鼠标按下时执行，释放时恢复显示文字
			public void mouseClicked(MouseEvent e) {}
		});
		
		add(northPanel, BorderLayout.NORTH); //添加到子面板上部
		
		//中部分
		displayArea = new JTextArea(20,60);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		add(scrollPane, BorderLayout.CENTER);

		//下部分
		southPanel = new JPanel();
		queryUserStatus = new JButton("查询单个用户状态");
		southPanel.add(queryUserStatus);
		
		queryUserStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("cusId", cusIdField.getText().trim());
				map1.put("cusCer", cusCerField.getText().trim());
				map1.put("appId", appidField.getText().trim());
				map1.put("cname", cnameField.getText().trim());
				map1.put("uid", uidField.getText().trim());
				String[] result = null;
				try {
					result = RestfulAPITools.QueryUserStatus(map1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String json_format = formatJson(result[1]);
				displayArea.setText("");
				displayArea.append("http code: " + result[0] + "\n" + "server response: "+json_format);
			}
		});
		add(southPanel);
	}
	
	public static String formatJson(String content) {

        StringBuffer sb = new StringBuffer();
        int index = 0;
        int count = 0;
        while(index < content.length()){
            char ch = content.charAt(index);
            if(ch == '{' || ch == '['){
                sb.append(ch);
                sb.append('\n');
                count++;
                for (int i = 0; i < count; i++) {                   
                    sb.append("   ");
                }
            }
            else if(ch == '}' || ch == ']'){
                sb.append('\n');
                count--;
                for (int i = 0; i < count; i++) {                   
                    sb.append("   ");
                }
                sb.append(ch);
            } 
            else if(ch == ','){
                sb.append(ch);
                sb.append('\n');
                for (int i = 0; i < count; i++) {                   
                    sb.append("   ");
                }
            } 
            else {
                sb.append(ch);              
            }
            index ++;
        }
        return sb.toString();
    }
}
