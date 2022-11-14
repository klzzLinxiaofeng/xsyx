package platform.resource.main;



import platform.resource.main.vo.HandleResource;
import platform.resource.main.vo.HandleResourceInterface;

import javax.swing.*;


public class DisplayResource{

	public static void main(String[] args) {
		
		HandleResourceInterface handleResource = new HandleResource();
		WindowResourse frame = new WindowResourse(handleResource);
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
}
