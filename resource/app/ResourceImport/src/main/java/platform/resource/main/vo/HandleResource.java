package platform.resource.main.vo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import platform.resource.main.ResouceUtil;
import platform.resource.main.ResourceImportLogic;
import platform.resource.main.ResourceProperties;
import platform.resource.main.sys.SysContants;

import java.io.File;
import java.util.List;

public class HandleResource implements HandleResourceInterface {
	
	public static String FIRST="教材级别";
	public static String SECOND="教材目录级别";
	public static String THIRD="教材目录资源级别";
	
	private ResourceImportLogic importLogic; 
	private ResourceProperties resouce;
	private String checkBox;
	private String resourcePath;
	
	public HandleResource() {
		//+"/
		//String[] fileUrl = new String[]{"classpath*:resource_application.xml"};  
		String[] fileUrl = new String[]{"classpath*:resource_application.xml"};
		/*String pathUrl = BeanFactory.class.getResource("/")+"resource_application.xml";
		System.out.println(pathUrl);*/
        ApplicationContext applicationContext  = new ClassPathXmlApplicationContext(fileUrl);  //初始化 applicationContext
        ResouceUtil util = new ResouceUtil();
        resouce = util.getProperties();
        //String resourcePath = resouce.getResourcePath();
        importLogic = new ResourceImportLogic(applicationContext);
        importLogic.init();
        
        
	}
	
	@Override
	public boolean checkBox(String checkBox) {
		this.checkBox = checkBox;
		System.out.println("HandleResource=="+checkBox);
		return true;
	}
	
	@Override
	public boolean resourcePath(String resourcePath) {
		
		if(resourcePath != null&&!"".equals(resourcePath.trim())){
			this.resourcePath = resourcePath;
		}else{
			this.resourcePath = resouce.getResourcePath();
		}
		System.out.println("this.resourcePath=="+this.resourcePath);
		return true;
	}
	
	/**
	 * 启动资源导入内容
	 * @return
	 */
	public String startUploadResource(){
		System.out.println(SysContants.SYSTEM_OWNER_ID);
		String message = "";
		File file = new File(resourcePath);
		if(!file.exists()){
			return message = "资源目录不存在";
		}
		OpenResourse openResourse = importLogic.verifyDirectoryAndFiles(resourcePath);
		List<TextBookVoResourse> voResourseList = openResourse.getVoResourseList();
		
		if(voResourseList != null&&voResourseList.size()>0 ){//判断教材对象是否存在，判断目录对象是否存在，判断资源对象是否存在
			
			if(SysContants.SYSTEM_OWNER_ID!=null) {
				if(HandleResource.FIRST.equals(this.checkBox)){
					voResourseList = importLogic.dealWithResTextBook(voResourseList, SysContants.SYSTEM_OWNER_ID);//处理教材信息
					message = "教材信息导入成功";
				}else if(HandleResource.SECOND.equals(this.checkBox)){
					voResourseList = importLogic.dealWithResTextBook(voResourseList, SysContants.SYSTEM_OWNER_ID);//处理教材信息
					voResourseList = importLogic.dealWithResTextBookCatalog(voResourseList);//处理教材目录信息
					message = "教材目录信息导入成功";
				}else if(HandleResource.THIRD.equals(this.checkBox)){
					voResourseList = importLogic.dealWithResTextBook(voResourseList, SysContants.SYSTEM_OWNER_ID);//处理教材信息
					voResourseList = importLogic.dealWithResTextBookCatalog(voResourseList);//处理教材目录信息
					importLogic.dealWithResTextBookCatalogResource(voResourseList, SysContants.SYSTEM_OWNER_ID);//处理每一个资源信息
					message = "教材目录资源导入成功";
				}
			} else {
				if(HandleResource.FIRST.equals(this.checkBox)){
					voResourseList = importLogic.dealWithTextBook(voResourseList);//处理教材信息
					message = "教材信息导入成功";
				}else if(HandleResource.SECOND.equals(this.checkBox)){
					voResourseList = importLogic.dealWithTextBook(voResourseList);//处理教材信息
					voResourseList = importLogic.dealWithTextBookCatalog(voResourseList);//处理教材目录信息
					message = "教材目录信息导入成功";
				}else if(HandleResource.THIRD.equals(this.checkBox)){
					voResourseList = importLogic.dealWithTextBook(voResourseList);//处理教材信息
					voResourseList = importLogic.dealWithTextBookCatalog(voResourseList);//处理教材目录信息
					importLogic.dealWithTextBookCatalogResource(voResourseList);//处理每一个资源信息
					message = "教材目录资源导入成功";
				}
			}
			
			ResourceImportLogic.outToTxt(ResourceImportLogic.beforeDone.getResourseList(), "beforeDone", resouce.getBeforePath(), ".txt");
			ResourceImportLogic.outToTxt(ResourceImportLogic.nowDone.getResourseList(), "nowDone", resouce.getDonePath(), ".txt");
			ResourceImportLogic.outToTxt(ResourceImportLogic.nowUndo.getResourseList(), "nowUndo", resouce.getUndoPath(), ".txt");
			
			
			
		}else{
			ResourceImportLogic.outToTxt(ResourceImportLogic.nowDone.getResourseList(), "nowDone", resouce.getDonePath(), ".txt");
			message = "没有要导入的资源";
		}
		System.out.println("result========================"+message);
		return message;
		}

	public String startUploadKnowledge(){
		String message = "";
		System.out.println(resourcePath);
		File file = new File(resourcePath);
		if(!file.exists()){
			return message = "资源目录不存在";
		}
		try{
			 importLogic.createTreeAndNode(resourcePath);
		}catch (Exception e){
			e.printStackTrace();
		}
		return message;
	}
		
	@Override
		public String outBeforeDoneString(){
			
			return ResourceImportLogic.outToString(ResourceImportLogic.beforeDone.getResourseList());
		}
	@Override
		public String outNowDoneString(){
				
				return ResourceImportLogic.outToString(ResourceImportLogic.nowDone.getResourseList());
			}
	@Override
		public String outNowUndoString(){
			
			return ResourceImportLogic.outToString(ResourceImportLogic.nowUndo.getResourseList());
		}
	
	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public ResourceProperties getResouce() {
		return resouce;
	}

	public void setResouce(ResourceProperties resouce) {
		this.resouce = resouce;
	}
	
	

	
}
