package platform.resource.main;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		try {
			new DataSynPractitioner().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
