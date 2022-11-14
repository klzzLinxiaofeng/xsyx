package com.dev.codeGenerator.ui;

import com.dev.code.Generator.utils.PropertiestUtils;
import com.dev.codeGenerator.context.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JTextField dbUsername;
	private JTextField dbPassword;
	private JTextField dbUrl;
	private JTextField dbDriver;

	private ClientContext clientContext;

	public ClientContext getClientContext() {
		return clientContext;
	}

	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public MainFrame() {
		init();
	}

	public void init() {
		this.setTitle("代码生成工具");
		this.setSize(1050, 200);
		this.setContentPane(createContentPane());
		showData();
	}

	private Container createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(BorderLayout.NORTH, new JLabel("代码生成工具", SwingConstants.CENTER));
		contentPane.add(BorderLayout.CENTER, createMainPane());
		contentPane.add(BorderLayout.SOUTH, createBtnPane());
		return contentPane;
	}

	private Component createBtnPane() {
		JPanel jpane = new JPanel();
		JButton confirmBtn = new JButton("确定");
		JButton cancelBtn = new JButton("退出");
		
		cancelBtn.addActionListener(new ActionListener() {
		/*	@Override*/
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		confirmBtn.addActionListener(new ActionListener() {
			
		/*	@Override*/
			public void actionPerformed(ActionEvent e) {
				String username = dbUsername.getText();
				String password = dbPassword.getText();
				String url = dbUrl.getText();
				String driver = dbDriver.getText();
				try {
					clientContext.setMainFrame(MainFrame.this);
					clientContext.connectAndShowData(username, password, url, driver);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(MainFrame.this, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		jpane.add(confirmBtn);
		jpane.add(cancelBtn);
		return jpane;
	}

	private Component createMainPane() {
		JPanel jpanel = new JPanel();
		dbUsername = new JTextField();
		dbPassword = new JTextField();
		dbUrl = new JTextField();
		dbDriver = new JTextField();
		jpanel.setLayout(new GridLayout(4, 2));
		jpanel.add(new JLabel("数据库用户名:", SwingConstants.CENTER));
		jpanel.add(dbUsername);
		jpanel.add(new JLabel("数据库密码:", SwingConstants.CENTER));
		jpanel.add(dbPassword);
		jpanel.add(new JLabel("数据库url:", SwingConstants.CENTER));
		jpanel.add(dbUrl);
		jpanel.add(new JLabel("数据库Driver:", SwingConstants.CENTER));
		jpanel.add(dbDriver);
		return jpanel;
	}
	
	private void showData() {
		PropertiestUtils propertiestUtils = new PropertiestUtils();
		this.dbUsername.setText(propertiestUtils.getValue("jdbc_username"));
		this.dbPassword.setText(propertiestUtils.getValue("jdbc_password"));
		this.dbUrl.setText(propertiestUtils.getValue("jdbc_url"));
		this.dbDriver.setText(propertiestUtils.getValue("jdbc_driver"));
	}

}
