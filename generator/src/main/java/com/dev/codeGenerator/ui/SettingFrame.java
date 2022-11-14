package com.dev.codeGenerator.ui;

import com.dev.code.Generator.utils.PropertiestUtils;
import com.dev.code.Generator.utils.WindowUtils;
import com.dev.codeGenerator.context.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class SettingFrame extends JFrame {

	private Map<String, Object> datas;

	private TextField moduleText;
	private TextField basePackageText;
	private TextField subPackageText;
	private TextField templateText;

	private List<JCheckBox> checkBoxs;

	private ClientContext clientContext;

	public ClientContext getClientContext() {
		return clientContext;
	}

	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}

	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	public SettingFrame() {
	}

	public void init() {
		this.setTitle("代码生成工具");
		this.setSize(1050, 800);
		WindowUtils.placedMiddle(this);
		moduleText = new TextField();
		basePackageText = new TextField();
		subPackageText = new TextField();
		templateText = new TextField();
		templateText.setText("template/mysql");
		this.setContentPane(createContentPane());
		showData();
	}

	private Container createContentPane() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(BorderLayout.NORTH, new JLabel("代码生成工具",
				SwingConstants.CENTER));
		contentPanel.add(BorderLayout.CENTER, createMainPane());
		contentPanel.add(BorderLayout.SOUTH, createBtnAndSelectBtnPane());
		return contentPanel;
	}

	private Component createBtnAndSelectBtnPane() {
		JPanel jpane = new JPanel();
		jpane.setLayout(new BorderLayout());
		jpane.add(BorderLayout.WEST, createBackBtnPane());
		jpane.add(BorderLayout.CENTER, createButtonPane());
		jpane.add(BorderLayout.EAST, createSelectBtnPane());
		
		return jpane;
	}

	private Component createBackBtnPane() {
		JPanel jpane = new JPanel();
		JButton backBtn = new JButton("返回");
		
		backBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				SettingFrame.this.clientContext.getMainFrame().show(true);
				SettingFrame.this.dispose();
			}
			
		});
		
		jpane.add(backBtn);
		return jpane;
	}

	private Component createSelectBtnPane() {
		JPanel jpane = new JPanel();
		JRadioButton selectAll = new JRadioButton("全选");
		JRadioButton cancelSelectAll = new JRadioButton("取消全选");
		JRadioButton invertSelect = new JRadioButton("反选");
		
		ButtonGroup radioBtnGroup = new ButtonGroup();
		radioBtnGroup.add(selectAll);
		radioBtnGroup.add(cancelSelectAll);
		radioBtnGroup.add(invertSelect);
		
		invertSelect.addActionListener(new ActionListener () {

			public void actionPerformed(ActionEvent e) {
				for(JCheckBox checkBox : checkBoxs) {
					checkBox.setSelected(!checkBox.isSelected());
				}
			}
			
		});
		
		selectAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (JCheckBox checkBox : checkBoxs) {
					checkBox.setSelected(true);
				}
			}
			
		});
		
		cancelSelectAll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for(JCheckBox checkBox : checkBoxs) {
					checkBox.setSelected(false);
				}
			}
			
		});
		jpane.add(selectAll);
		jpane.add(cancelSelectAll);
		jpane.add(invertSelect);
		return jpane;
	}

	private Component createButtonPane() {
		JPanel jpane = new JPanel();
		JButton confirmBtn = new JButton("确定");
		JButton cancelBtn = new JButton("退出");
		
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tables = getSelectedTables();
				try {
					setBasicConfiguration();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				String template = templateText.getText();
				if(template != null && "".equals(template)) {
					JOptionPane.showMessageDialog(SettingFrame.this, "模板的路径不能为空 请输入模板路径!");
				} else {
					/*if(tables.length > 0) {
						String operationInfo = clientContext.produce(template, tables);
						JOptionPane.showMessageDialog(SettingFrame.this, operationInfo);
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(SettingFrame.this, "您未选择要生成代码的数据库表!");
					}*/
				}
				
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jpane.add(confirmBtn);
		jpane.add(cancelBtn);
		
		return jpane;
	}

	private String[] getSelectedTables() {
		List<String> tableList = new ArrayList<String>();
		for (JCheckBox checkBox : checkBoxs) {
			if (checkBox.isSelected()) {
				String tableName = checkBox.getText();
				tableList.add(tableName);
			}
		}
		String[] tables = new String[tableList.size()];
		return tableList.toArray(tables);
	}

	private Component createMainPane() {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jpanel.add(BorderLayout.NORTH, createSetPackageDataPanel());
		jpanel.add(BorderLayout.CENTER, createShowTablesPanel());
		return jpanel;
	}

	private Component createSetPackageDataPanel() {
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(5, 2));
		jpanel.add(new JLabel("模块名:", SwingConstants.CENTER));
		jpanel.add(moduleText);
		jpanel.add(new JLabel("根包路径名:", SwingConstants.CENTER));
		jpanel.add(basePackageText);
		jpanel.add(new JLabel("子包名:", SwingConstants.CENTER));
		jpanel.add(subPackageText);
		jpanel.add(new JLabel("模板路径名:", SwingConstants.CENTER));
		jpanel.add(templateText);
		jpanel.add(new JLabel("请选择以下要生成相应代码的数据表:", SwingConstants.LEFT));
		return jpanel;
	}

	private Component createShowTablesPanel() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel jpanel = new JPanel();
		String[] tables = (String[]) datas.get("tables");
		int length = tables.length;
		int rows = length % 6 > 0 ? length / 6 + 1 : length / 6;
		jpanel.setLayout(new GridLayout(rows, 6));
		checkBoxs = new ArrayList<JCheckBox>();
		for (String table : tables) {
			JCheckBox checkBox = new JCheckBox(table, false);
			checkBoxs.add(checkBox);
			jpanel.add(checkBox);
		}
		scrollPane.setViewportView(jpanel);
		return scrollPane;
	}

	private void setBasicConfiguration() throws UnsupportedEncodingException {
		String module = this.moduleText.getText();
		String basePackage = this.basePackageText.getText();
		String subPackage = this.subPackageText.getText();
		clientContext.setBasicConfiguration(module, basePackage, subPackage);
	}

	private void showData() {
		PropertiestUtils propertiesUtil = new PropertiestUtils();
		moduleText.setText(propertiesUtil.getValue("module"));
		basePackageText.setText(propertiesUtil.getValue("basepackage"));
		subPackageText.setText(propertiesUtil.getValue("subpackage"));
	}
}
