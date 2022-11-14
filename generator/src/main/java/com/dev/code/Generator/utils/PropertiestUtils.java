package com.dev.code.Generator.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiestUtils {

	private static Properties properties = new Properties();

	private static InputStream inputFile;

	private static FileOutputStream outputFile;

	
	public PropertiestUtils() {
		inputFile = PropertiestUtils.class.getClassLoader()
				.getResourceAsStream("generator.properties");
		try {
			properties.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadProperties() {
		inputFile = PropertiestUtils.class.getClassLoader()
				.getResourceAsStream("generator.properties");
		try {
			properties.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public  String getValue(String key) {
		if (properties.containsKey(key)) {
			String value = properties.getProperty(key);
			return value;
		} else {
//			System.out.println("�޴������" + key);
			return null;
		}
	}
	
	public  void clear() {
		properties.clear();
	}

	public  void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	public  void saveFile(String filePath, String description) {
		try {
			outputFile = new FileOutputStream(filePath);
			properties.store(outputFile, description);
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}