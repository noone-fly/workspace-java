package com.pierre.frame;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 创建左边的树节点
 * @author chenpiyang
 *
 */
public class BuildTree {
	
	
	public BuildTree() {
		
	}
	
	public static JTree buildTree() {
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Token");
        node1.add(new DefaultMutableTreeNode("validateRtcToken"));
        node1.add(new DefaultMutableTreeNode("parseRtcToken"));
        node1.add(new DefaultMutableTreeNode("createRtcToken"));
        node1.add(new DefaultMutableTreeNode("createRtmToken"));
 
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Record");
        node2.add(new DefaultMutableTreeNode("CloudRecord"));
        node2.add(new DefaultMutableTreeNode("RecordSDK"));
        node2.add(new DefaultMutableTreeNode("AliRecord"));
        
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("RESTfulAPI");
        node3.add(new DefaultMutableTreeNode("startLocalRecord"));
        node3.add(new DefaultMutableTreeNode("openAPPCertificate"));
        node3.add(new DefaultMutableTreeNode("checkUserStats"));
        node3.add(new DefaultMutableTreeNode("checkUserList"));
        node3.add(new DefaultMutableTreeNode("checkChannelList"));
        node3.add(new DefaultMutableTreeNode("Kickout"));
        
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("sign");
        node4.add(new DefaultMutableTreeNode("SHA"));
        node4.add(new DefaultMutableTreeNode("MD5"));
        node4.add(new DefaultMutableTreeNode("RSA"));
        node4.add(new DefaultMutableTreeNode("Base64"));
        
        DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("file");
        node5.add(new DefaultMutableTreeNode("search"));
        
        DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("billing");
        node6.add(new DefaultMutableTreeNode("analyze"));
        
        DefaultMutableTreeNode node7 = new DefaultMutableTreeNode("Database");
        node7.add(new DefaultMutableTreeNode("mysql"));
        
        DefaultMutableTreeNode node8 = new DefaultMutableTreeNode("Deploy");
        node8.add(new DefaultMutableTreeNode("server1"));
        
        DefaultMutableTreeNode node9 = new DefaultMutableTreeNode("ssh");
        node9.add(new DefaultMutableTreeNode("scp"));
 
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("IndexPage");
 
        top.add(node1);
        top.add(node2);
        top.add(node3);
        top.add(node4);
        top.add(node5);
        top.add(node6);
        top.add(node7);
        top.add(node8);
        top.add(node9);
        final JTree tree = new JTree(top);
        
        
        
        
		return tree;
	}
}
