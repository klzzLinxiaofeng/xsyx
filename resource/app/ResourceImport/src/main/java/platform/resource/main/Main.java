package platform.resource.main;

import platform.resource.main.vo.HandleResource;
import platform.resource.main.vo.HandleResourceInterface;

public class Main {
	
	public static void main(String[] args) {
		
		HandleResource handleresource = new HandleResource();
		handleresource.checkBox(HandleResource.THIRD);
		String resourcePath = handleresource.getResouce().getResourcePath();
		System.out.println("main:resourcePath:"+resourcePath);
		handleresource.resourcePath(resourcePath);
		handleresource.startUploadResource();
	}

}
