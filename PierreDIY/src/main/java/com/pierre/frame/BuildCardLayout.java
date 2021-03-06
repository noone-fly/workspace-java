package com.pierre.frame;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.pierre.frame.cardPanel.StartLocalRecord;
import com.pierre.frame.cardPanel.WelcomPanel;

/**
 * 创建卡片布局，每个子面板对应一个tag  
 * @author chenpiyang
 *
 */
public class BuildCardLayout {
	
	
	public BuildCardLayout() {
		
	}
	
	public static void buildCardLayoutPanel(JPanel panel) {
		
		// 定义一个面板用于显示卡片布局
		//JPanel cardPanel = new JPanel();
		// 把该面板设置为卡片布局
		//cardPanel.setLayout(cardLayout);
		
		// 创建需要放入到卡片布局的子面板，
		JPanel welcomePanel = new WelcomPanel().returnPanel();
		JPanel startLocalRecord = new StartLocalRecord().returnPanel();
//		JPanel createRtcTokenPanel = new CreateRtcTokenPanel();
//		JPanel validateRtcTokenPanel = new VarifyRtcTokenPanel();
//		JPanel analyzeRtcTokenPanel = new AnalyzeRtcTokenPanel();
//		JPanel createRtmTokenPanel = new CreateRtmTokenPanel();
//		JPanel validateRtmTokenPanel = new ValidateRtmTokenPanel(); //新增
//
//		JPanel queryUserStatusPanel = new QueryUserStatusPanel();
//		JPanel queryUserListPanel = new QueryUserListPanel();
//		JPanel queryChannelListPanel = new QueryChannelListPanel();
//		JPanel startCloudRecordSinglePanel = new StartCloudRecordSinglePanel();
//		JPanel startCloudRecordMixPanel = new StartCloudRecordMixPanel();
//		JPanel startLocalRecordPanel = new StartLocalRecordPanel();
//		JPanel kickoutPanel = new KickoutPanel();
//
//		JPanel obtainDurationBillingPanel = new ObtainDurationBilling();
//		JPanel queryUsagePanel = new QueryUsagePanel();

		
		// 把定义好的子面板放入卡片布局中,每个子面板对应一个tag  
		panel.add("welcomeTag", welcomePanel);
		panel.add("startLocalRecord", startLocalRecord);
//		displayNext.add("createRtcTokenTag", createRtcTokenPanel);
//		displayNext.add("validateRtcTokenTag", validateRtcTokenPanel);
//		displayNext.add("analyzeRtcTokenTag", analyzeRtcTokenPanel);
//		displayNext.add("createRtmTokenTag", createRtmTokenPanel);
//		displayNext.add("validateRtmTokenTag", validateRtmTokenPanel);
//
//		displayNext.add("queryUserStatusTag", queryUserStatusPanel);
//		displayNext.add("queryUserListTag",queryUserListPanel);
//		displayNext.add("queryChannelListTag", queryChannelListPanel);
//		displayNext.add("startCloudRecordSingleTag", startCloudRecordSinglePanel);
//		displayNext.add("startCloudRecordMixTag", startCloudRecordMixPanel);
//		displayNext.add("startLocalRecordTag",startLocalRecordPanel);
//
//		displayNext.add("obtainDurationBillingTag", obtainDurationBillingPanel);
//		displayNext.add("queryUsageTag", queryUsagePanel);
//		displayNext.add("kickoutTag", kickoutPanel);
		
		//return cardPanel;
	}
	
}
