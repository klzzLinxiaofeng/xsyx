package com.dev.codeGenerator.context;

import com.dev.code.Generator.utils.DBUtil;
import com.dev.code.Generator.utils.PropertiestUtils;
import com.dev.codeGenerator.ui.MainFrame;
import com.dev.codeGenerator.ui.SettingFrame;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
//import cn.org.rapid_framework.generator.GeneratorFacade;
//import cn.org.rapid_framework.generator.GeneratorProperties;

public class ClientContext {

	private SettingFrame settingFrame;
	private MainFrame mainFrame;

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public SettingFrame getSettingFrame() {
		return settingFrame;
	}

	public void setSettingFrame(SettingFrame settingFrame) {
		this.settingFrame = settingFrame;
	}

	@SuppressWarnings("deprecation")
	public void connectAndShowData(String dbUsername, String dbPassword,
			String dbUrl, String dbDriver) throws UnsupportedEncodingException, SQLException {
		PropertiestUtils propertiesUtil = new PropertiestUtils();
		propertiesUtil.setValue("jdbc_username", dbUsername);
		propertiesUtil.setValue("jdbc_password", dbPassword);
		propertiesUtil.setValue("jdbc_url", dbUrl);
		propertiesUtil.setValue("jdbc_driver", dbDriver);
		URL url = ClientContext.class.getClassLoader().getResource("");
		String classPath = URLDecoder.decode(url.getPath(), "utf-8");
		propertiesUtil.saveFile(classPath + "/generator.properties",
				"JDBC configuration");
		try {
			String[] tables = getTables(dbUsername, dbPassword, dbUrl, dbDriver);
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("tables", tables);
			this.settingFrame.setDatas(maps);
			this.settingFrame.init();
			this.settingFrame.show(true);
			mainFrame.show(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw (e);
		}
	}

	private String[] getTables(String dbUsername, String dbPassword,
			String dbUrl, String dbDriver) throws SQLException {
		List<String> tables = new ArrayList<String>();
		DBUtil dbUtil = new DBUtil();
		Connection connection = dbUtil.getConnection();
		String[] types = { "TABLE" };
		ResultSet rs = connection.getMetaData()
				.getTables(null, null, "", types);
		int count = 0;
		while (rs.next()) {
			count++;
			tables.add(rs.getString(3));
		}
		String[] tablesString = new String[count];
		dbUtil.closeConnection(connection);
		return tables.toArray(tablesString);
	}
	
	public void setBasicConfiguration(String module, String basePackage, String subPackage) throws UnsupportedEncodingException {
		PropertiestUtils propertiestUtils = new PropertiestUtils();
		propertiestUtils.setValue("module", module);
		propertiestUtils.setValue("basepackage", basePackage);
		propertiestUtils.setValue("subpackage", subPackage);
		URL url = ClientContext.class.getClassLoader().getResource("");
		String classPath = URLDecoder.decode(url.getPath(), "utf-8");
		propertiestUtils.saveFile(classPath + "/generator.properties",
				"JDBC configuration");
	}
	
//	public String produce(String template, String[] tables) {
//		System.out.println(tables[0]);
//		GeneratorFacade g = new GeneratorFacade();
//		g.getGenerator().setTemplateRootDir(template);
//		String operation="success";
//		try {
//			g.deleteOutRootDir();
//			g.generateByTable(tables);
//			Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			operation = "error";
//		} catch (Exception e) {
//			e.printStackTrace();
//			operation = "error";
//		}
//		return operation;
//	}
//
}
