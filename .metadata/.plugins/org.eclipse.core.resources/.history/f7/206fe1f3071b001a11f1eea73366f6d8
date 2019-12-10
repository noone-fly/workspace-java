package io.pierre.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.http.ParseException;

import io.agora.media.sample.AccessTokenSample;
import io.pierre.rtcrestfulapi.RestfulAPITools;
import io.pierre.tools.DateTools;

/**
 * 
 * @author chenpiyang
 *
 */
public class AnalyzeRtcTokenPanel extends JPanel {
	private JTextArea tokenArea;
	private JTextArea displayArea;
	private JPanel southPanel;
	private JButton validateToken;
	
	String appid = "";
	String ts = "";
	
	public AnalyzeRtcTokenPanel() {
		// TODO Auto-generated constructor stub
		
		JLabel label = new JLabel("<html><font color=\"red\"><b>说明：</b>若token报109<br>"
				+ "可以拿token解析其中设置的时间戳，和生成时间对比<br>"
				+ "109的错误一般是客户忘记加上当前时间戳，时间戳正确形式是：(int)(new Date().getTime()/1000) + seconds<br>"
				+ "<br>"
				+ "</font></html>"
				, SwingConstants.CENTER);
		add(label);
		
		tokenArea = new JTextArea(3, 50);
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
		
		// 中部分

		displayArea = new JTextArea(20, 60);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		add(scrollPane, BorderLayout.CENTER);

		// 下部分
		southPanel = new JPanel();
		validateToken = new JButton("解析Token");
		southPanel.add(validateToken);

		validateToken.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String token = tokenArea.getText().trim();

				if (null == token || token.equals("")) {
					displayArea.setText("");
					displayArea.append("Token 不能为空");
				} else {
					displayArea.setText("");
					try {
						String[] strings = AccessTokenSample.analyzeToken(token);
						appid = strings[0];
						ts = strings[1];
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					displayArea.append("AppID: " + appid + "\n\n" + "时间戳：" + ts +" （"+ DateTools.timestampToDate(Long.parseLong(ts)) + "）\n");

				}
			}
		});

		add(validateToken, BorderLayout.SOUTH);
	}
}
