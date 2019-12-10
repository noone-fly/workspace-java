package com.pierre.actionlistener;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 所有的监听事件
 * @author chenpiyang
 *
 */
public class ActionListenerTools {
	
	public static void TreeSelectionListenerFun(final JTree tree, final JPanel panel, final CardLayout cardLayout) {
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}
				
				String nodeStr = node.toString().trim();
				//点击树中相应的节点，显示对应的子面板，MainFrame.java 中调用
				switch (nodeStr) {
				case "IndexPage":
					//在卡片布局中显示子面板，首页
					cardLayout.show(panel, "welcomeTag");
					break;
				case "startLocalRecord":
					//在卡片布局中显示子面板，开始本地服务端录制
					//System.out.println("=="+nodeStr);
					cardLayout.show(panel, "startLocalRecordTag");
					break;
				default:
					break;
				}
				
			}
		});
	}
	
	
}
