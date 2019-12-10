package io.pierre.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author chenpiyang 进一步优化一切重复代码，
 */
public class AgoraTool {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}
}

class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 750;

	// 定义卡片布局
	private CardLayout cardLayout = new CardLayout();
	// 分隔符
	JPanel lastPanel1 = new JPanel();
	JPanel lastPanel2 = new JPanel();
	JPanel lastPanel3 = new JPanel();
	// 卡片显示区域
	JPanel displayNext = new JPanel();
	//border
	JPanel border = new JPanel();

	// new button
	JButton welcomeButton = new JButton("欢迎页");
	JButton createRtcTokenButton = new JButton("创建RtcToken");
	JButton varifyRtcTokenButton = new JButton("验证RtcToken");;
	JButton analyzeRtcTokenButton = new JButton("解析RtcToken");
	JButton createRtmTokenButton = new JButton("创建RtmToken");
	JButton validateRtmTokenButton = new JButton("验证RtmToken"); //新增
	
	JButton queryUserStatusButton = new JButton("查询用户状态");
	JButton queryUserListButton = new JButton("查询用户列表");
	JButton queryChannelListButton = new JButton("查询频道列表");
	
	//JButton startCloudRecordSingleButton = new JButton("云录制单流");
	JButton startCloudRecordMixButton = new JButton("云录制");
	JButton startLocalRecordButton = new JButton("本地录制");
	
	
	JButton obtainDurationBillingButton = new JButton("获取详单");
	JButton queryUsageButton = new JButton("查询用量");
	JButton kickoutButton = new JButton("踢人");

	public MainFrame() {
		setTitle("CS Tools For Windows");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		JMenuBarTools.addMenu(menuBar, "File");
		JMenuBarTools.addMenu(menuBar, "Setting");
		JMenuBarTools.addMenu(menuBar, "Edit");
		JMenuBarTools.addMenu(menuBar, "Help");
		setJMenuBar(menuBar);
		
		//validateRtmTokenButton.setEnabled(false);
		//varifyRtcTokenButton.setEnabled(false);
		//analyzeRtcTokenButton.setEnabled(false);
		//startCloudRecordSingleButton.setEnabled(false);
		//startCloudRecordMixButton.setEnabled(false);
		//startLocalRecordButton.setEnabled(false);
		//queryUserStatusButton.setEnabled(false);
		//queryUserListButton.setEnabled(false);
		//queryChannelListButton.setEnabled(false);
		//obtainDurationBillingButton.setEnabled(false);
		//queryUsageButton.setEnabled(false);
		

		// 把卡片显示区域定义为卡片布局
		displayNext.setLayout(cardLayout);
		// 给卡片面板设置上边框
		displayNext.setBorder(BorderFactory.createMatteBorder(5,0,0,0, Color.WHITE));

		// 创建特定面板
		JPanel welcomePanel = new WelcomPanel();
		JPanel createRtcTokenPanel = new CreateRtcTokenPanel();
		JPanel validateRtcTokenPanel = new VarifyRtcTokenPanel();
		JPanel analyzeRtcTokenPanel = new AnalyzeRtcTokenPanel();
		JPanel createRtmTokenPanel = new CreateRtmTokenPanel();
		JPanel validateRtmTokenPanel = new ValidateRtmTokenPanel(); //新增
		
	    	JPanel queryUserStatusPanel = new QueryUserStatusPanel();
	    	JPanel queryUserListPanel = new QueryUserListPanel();
	    	JPanel queryChannelListPanel = new QueryChannelListPanel();
	    	JPanel startCloudRecordSinglePanel = new StartCloudRecordSinglePanel();
	    	JPanel startCloudRecordMixPanel = new StartCloudRecordMixPanel();
	    	JPanel startLocalRecordPanel = new StartLocalRecordPanel();
	    	JPanel kickoutPanel = new KickoutPanel();
	    	
	    	JPanel obtainDurationBillingPanel = new ObtainDurationBilling();
	    	JPanel queryUsagePanel = new QueryUsagePanel();

		// 把定义好的面板放入卡片布局中
	    	displayNext.add("welcomeTag", welcomePanel);
		displayNext.add("createRtcTokenTag", createRtcTokenPanel);
		displayNext.add("validateRtcTokenTag", validateRtcTokenPanel);
		displayNext.add("analyzeRtcTokenTag", analyzeRtcTokenPanel);
		displayNext.add("createRtmTokenTag", createRtmTokenPanel);
		displayNext.add("validateRtmTokenTag", validateRtmTokenPanel);
		
		displayNext.add("queryUserStatusTag", queryUserStatusPanel);
		displayNext.add("queryUserListTag",queryUserListPanel);
		displayNext.add("queryChannelListTag", queryChannelListPanel);
		displayNext.add("startCloudRecordSingleTag", startCloudRecordSinglePanel);
		displayNext.add("startCloudRecordMixTag", startCloudRecordMixPanel);
		displayNext.add("startLocalRecordTag",startLocalRecordPanel);
		
		displayNext.add("obtainDurationBillingTag", obtainDurationBillingPanel);
		displayNext.add("queryUsageTag", queryUsagePanel);
		displayNext.add("kickoutTag", kickoutPanel);

		// 添加按钮事件并加入到框架中，并在监听方法中显示子面板
		addButton(welcomeButton, "welcomeTag");
		addButton(createRtcTokenButton, "createRtcTokenTag");
		addButton(varifyRtcTokenButton, "validateRtcTokenTag");
		addButton(analyzeRtcTokenButton, "analyzeRtcTokenTag");
		addButton(createRtmTokenButton, "createRtmTokenTag");
		//addButton(validateRtmTokenButton, "validateRtmTokenTag");
		add(lastPanel1);
		
		addButton(queryUserStatusButton, "queryUserStatusTag");
		addButton(queryUserListButton, "queryUserListTag");
		addButton(queryChannelListButton, "queryChannelListTag");
		//addButton(startCloudRecordSingleButton, "startCloudRecordSingleTag");
		addButton(startCloudRecordMixButton, "startCloudRecordMixTag");
		//addButton(startLocalRecordButton, "startLocalRecordTag");
		addButton(obtainDurationBillingButton, "obtainDurationBillingTag");
		addButton(kickoutButton, "kickoutTag");
		add(lastPanel2);
		
		
		//addButton(queryUsageButton, "queryUsageTag");
		
		add(lastPanel3);
		

		// 主界面布局，默认显示createRtcToken
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);

		//add(lastPanel);
		add(displayNext);
		displayNext.setBackground(Color.WHITE);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		// 按钮布局
		buttonLayout(constraints, layout, 1, 0, 0, welcomeButton);
		buttonLayout(constraints, layout, 1, 0, 0, createRtcTokenButton);
		buttonLayout(constraints, layout, 1, 0, 0, varifyRtcTokenButton);
		buttonLayout(constraints, layout, 1, 0, 0, analyzeRtcTokenButton);
		buttonLayout(constraints, layout, 1, 0, 0, createRtmTokenButton);
		buttonLayout(constraints, layout, 1, 0, 0, validateRtmTokenButton);
		buttonLayout(constraints, layout, 0, 0, 0, lastPanel1);// 分隔符
		
		buttonLayout(constraints, layout, 1, 0, 0, queryUserStatusButton);
		buttonLayout(constraints, layout, 1, 0, 0, queryUserListButton);
		buttonLayout(constraints, layout, 1, 0, 0, queryChannelListButton);
		//buttonLayout(constraints, layout, 1, 0, 0, startCloudRecordSingleButton);
		buttonLayout(constraints, layout, 1, 0, 0, startCloudRecordMixButton);
		//buttonLayout(constraints, layout, 1, 0, 0, startLocalRecordButton);
		buttonLayout(constraints, layout, 1, 0, 0, obtainDurationBillingButton);
		buttonLayout(constraints, layout, 1, 0, 0, kickoutButton);
		buttonLayout(constraints, layout, 0, 0, 0, lastPanel2);// 分隔符
		
		
		buttonLayout(constraints, layout, 1, 0, 0, queryUsageButton);
		
		buttonLayout(constraints, layout, 0, 0, 0, lastPanel3);
		// 卡片显示区域布局
		buttonLayout(constraints, layout, 8, 1, 1, displayNext);

	}

	//匿名类： 添加监听事件并加入到框架中，简化代码编写量
	public void addButton(JButton button, String tag) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(displayNext, tag);
			}
		});
		add(button);
	}

	//主框架界面的布局
	public void buttonLayout(GridBagConstraints constraints, GridBagLayout layout, int gridwidth, int weightx,
			int weighty, JComponent component) {
		constraints.gridwidth = gridwidth;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		layout.setConstraints(component, constraints);
	}

}