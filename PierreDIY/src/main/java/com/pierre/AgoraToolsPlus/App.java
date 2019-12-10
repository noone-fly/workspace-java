package com.pierre.AgoraToolsPlus;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.pierre.frame.MainFrame;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame();//初始化主框架
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);//显示frame
				frame.setResizable(false);//固定frame
			}
		});
    }
}
