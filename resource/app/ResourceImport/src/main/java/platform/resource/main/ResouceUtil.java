package platform.resource.main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;
public class ResouceUtil {
	
	public ResourceProperties getProperties(){
		

		  ResourceProperties resouce = new ResourceProperties();
		  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource.properties");   
		  Properties p = new Properties();
		 
		  try {   
		   p.load(inputStream); 
		   resouce.setResourcePath(p.getProperty("resource.path"));
		   resouce.setUndoPath(p.getProperty("now.undo.path"));
		   resouce.setBeforePath(p.getProperty("before.done.path"));
		   resouce.setDonePath(p.getProperty("now.done.path"));
		   resouce.setLineMark(p.getProperty("line.mark"));
		   
		   resouce.setDefaultMicros(propertiesString(p.getProperty("default.micro")));
		   resouce.setDefaultLearning_designs(propertiesString(p.getProperty("default.learning_design")));
		   resouce.setDefaultHomeworks(propertiesString(p.getProperty("default.homework")));
		   resouce.setDefaultExams(propertiesString(p.getProperty("default.exam")));
		   resouce.setDefaultTeaching_plans(propertiesString(p.getProperty("default.teaching_plan")));
		   resouce.setDefaultMaterials(propertiesString(p.getProperty("default.material")));
		   resouce.setDefaultOthers(propertiesString(p.getProperty("default.other")));
		   resouce.setDefaultLearning_plan(propertiesString(p.getProperty("default.learning_plan")));
		  } catch (IOException e1) {   
		   e1.printStackTrace();   
		  }   
		 
		
		return resouce;
	}
	
	private String[] propertiesString(String defaultStr){
		
		if(defaultStr == null||"".equals(defaultStr)){
			defaultStr = "";
		}
		
		return defaultStr.split(",");
		
	}
	public  boolean writeTxtFile(String content,File  fileName){  
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));
			o.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				try {
					mm.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	
}
