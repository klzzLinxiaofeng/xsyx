package com.dev.codeGenerator.ui;

import com.dev.code.Generator.utils.WindowUtils;
import com.dev.codeGenerator.context.ClientContext;

public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ClientContext clientContext = new ClientContext();
		SettingFrame settingFrame = new SettingFrame();
		clientContext.setSettingFrame(settingFrame);
		settingFrame.setClientContext(clientContext);
		MainFrame mainFrame = new MainFrame();
		clientContext.setMainFrame(mainFrame);
		mainFrame.setClientContext(clientContext);
		WindowUtils.placedMiddle(mainFrame);
		mainFrame.show(true);
	}
	
}
