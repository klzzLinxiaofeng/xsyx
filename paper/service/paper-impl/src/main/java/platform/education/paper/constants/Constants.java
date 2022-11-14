/**   
* @Title: Constants.java
* @Package platform.education.paper.constants 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月25日 下午1:44:02 
* @version V1.0   
*/
package platform.education.paper.constants;

/** 
* @ClassName: Constants 
* @Description: 常量类 
* @author pantq
* @date 2017年2月25日 下午1:44:02 
*  
*/
public class Constants {
	
	//1.文件不存在
    public static final int FILENOTEXIST = 1;
    //文件不存在描述
    public static final String FILENOTEXISTMSG = "文件不存在";
    
    //2.文件存在没有被使用过
    public static final int FILEEXISTNOTUSED = 2;
    ////文件存在没有被使用过描述
    public static final String FILEEXISTNOTUSEDMSG = "文件存在没有被用过";
    
    //3.文件存在没有被使用过
    public static final int FILEEXISTUSED = 2;
    ////文件存在没有被使用过描述
    public static final String FILEEXISTUSEDMSG = "文件存在已被用过";
    
}
