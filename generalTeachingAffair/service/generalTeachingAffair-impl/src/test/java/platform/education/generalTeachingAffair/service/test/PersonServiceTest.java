package platform.education.generalTeachingAffair.service.test;


import org.springframework.context.support.ClassPathXmlApplicationContext;





public class PersonServiceTest{
	
	 public static void main(String[] args) throws Exception {  
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
	                new String[]{"generalTeachingAffair-test.xml"});
	        context.start();
	        
	        System.out.println("Press any key to exit.");  
	        System.in.read();  
	    } 
}
