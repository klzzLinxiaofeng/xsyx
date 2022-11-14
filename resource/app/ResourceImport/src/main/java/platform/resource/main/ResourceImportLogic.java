package platform.resource.main;

import org.springframework.context.ApplicationContext;
import platform.education.exam.contants.ExamType;
import platform.education.exam.model.Exam;
import platform.education.exam.service.ExamService;
import platform.education.generalcode.model.*;
import platform.education.generalcode.service.*;
import platform.education.generalcode.vo.*;
import platform.education.homework.contants.HomeworkType;
import platform.education.homework.model.Homework;
import platform.education.homework.service.HomeworkService;
import platform.education.learningDesign.contants.LearningDesignType;
import platform.education.learningDesign.contants.LearningPlanType;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.material.contants.MaterialType;
import platform.education.material.model.Material;
import platform.education.material.service.MaterialService;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MicroLessonService;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.ResKnowledgeResource;
import platform.education.resource.model.Resource;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.*;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.utils.UUIDUtil;
import platform.education.resource.vo.ImportCatalogVo;
import platform.education.resource.vo.ResKnowledgeResourceCondition;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceLibraryCondition;
import platform.education.teachingPlan.contants.TeachingPlanType;
import platform.education.teachingPlan.model.TeachingPlan;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.resource.main.sys.SysContants;
import platform.resource.main.util.MD5;
import platform.resource.main.vo.*;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResourceImportLogic {
	
	private ImportResourceService importResourceServiceImpl ;
	protected TextbookCatalogService jcTextbookCatalogService;
	protected platform.education.generalcode.service.GradeService jcGradeService;
	protected TextbookPublisherService jcTextbookPublisherService;
	protected StageService jcStageService;
	protected platform.education.generalcode.service.SubjectService jcSubjectService;
	protected TextbookService jcTextbookService;
	protected ResTextbookService resTextbookService;
	protected ResTextbookCatalogService resTextbookCatalogService;
	private TextbookVersionService jcTextbookVersionService;
	protected TextbookVolumnService jcTextbookVolumnService;
	protected ResourceLibraryService resourceLibraryService;

	protected KnowledgeNodeService knowledgeNodeService;
	protected KnowledgeTreeService knowledgeTreeService;
	protected ResourceHandlerService resourceHandlerService;
	protected FileService fileService;
	protected HomeworkService homeworkService;
	protected TeachingPlanService teachingPlanService;
	protected MicroLessonService microLessonService;
	protected LearningDesignService learningDesignService;
	protected MaterialService materialService;
	protected LearningPlanService learningPlanService;
	protected ExamService examService;
	protected ResourceService resourceService;
	protected ResKnowledgeResourceService resKnowledgeResourceService;

    private static Map<String, Integer> resourseFileMap = null;
    private static List<TextBookVoResourse> voResourseList = new ArrayList<TextBookVoResourse>();
    
    private static   Map<String, String> gradeMap = new HashMap<String, String>();
    private static  Map<String, String> gradeNameMap = new HashMap<String, String>();
    
    private static   Map<String, String> stageMap = new HashMap<String, String>();
    private static  Map<String, String> stageNameMap = new HashMap<String, String>();
    
    private static   Map<String, Integer> subjectMap = new HashMap<String, Integer>();
    private static  Map<Integer, String> subjectNameMap = new HashMap<Integer, String>();
    
    private static   Map<String, Integer> versionMap = new HashMap<String, Integer>();
    private static   Map<Integer, String> versionNameMap = new HashMap<Integer, String>();  

    private static   Map<String, String> volumnMap = new HashMap<String, String>();
    private static  Map<String, String> volumnNameMap = new HashMap<String, String>();
    
    private static   Map<Integer, Integer> publishMap = new HashMap<Integer, Integer>();
    
    private static   String[] defaultMicros;
    private static   String[] defaultLearning_designs;
    private static   String[] defaultLearning_plans;
    private static   String[] defaultHomeworks;
    private static   String[] defaultExams;
    private static   String[] defaultTeaching_plans;
    private static   String[] defaultMaterials;
    private static   String[] defaultOthers;
    
    
    public static  Resourse nowUndo = new Resourse();
    public static  Resourse beforeDone = new Resourse();
    public static  Resourse nowDone = new Resourse();
    
    static{
    	
    	resourseFileMap = new HashMap<String, Integer>();
    	resourseFileMap.put("教案", 5);
    	resourseFileMap.put("课件", 2);
    	resourseFileMap.put("试卷", 4);
    	resourseFileMap.put("素材", 6);
    	resourseFileMap.put("微课", 1);
    	resourseFileMap.put("作业", 3);
    	resourseFileMap.put("作业", 3);
    	resourseFileMap.put("导学案", 8);
    	resourseFileMap.put("其他", 7);
    	
    }
	private ApplicationContext applicationContext;

	public ResourceImportLogic() {
		
	}
    /**
     * 初始化服务
     * @param applicationContext
     */
	public ResourceImportLogic(ApplicationContext applicationContext) {
		
		this.applicationContext = applicationContext;
		this.importResourceServiceImpl = (ImportResourceService)applicationContext.getBean("importResourceService");//资源应用
		this.jcTextbookCatalogService = (TextbookCatalogService)applicationContext.getBean("jcTextbookCatalogService");//教材目录
		this.jcGradeService = (platform.education.generalcode.service.GradeService)applicationContext.getBean("jcGradeService");//基础年级代码
		this.jcTextbookPublisherService = (TextbookPublisherService)applicationContext.getBean("jcTextbookPublisherService");//教材出版社
		this.jcStageService = (StageService)applicationContext.getBean("jcStageService");// 学段
		this.jcSubjectService = (platform.education.generalcode.service.SubjectService)applicationContext.getBean("jcSubjectService");//基础科目
		this.jcTextbookService = (TextbookService)applicationContext.getBean("jcTextbookService");//教材
		this.resTextbookService = (ResTextbookService)applicationContext.getBean("resTextbookService");
		this.resourceLibraryService = (ResourceLibraryService)applicationContext.getBean("resourceLibraryService");
		this.resTextbookCatalogService = (ResTextbookCatalogService)applicationContext.getBean("resTextbookCatalogService");
		this.jcTextbookVersionService = (TextbookVersionService)applicationContext.getBean("jcTextbookVersionService");//版本
		this.jcTextbookVolumnService = (TextbookVolumnService)applicationContext.getBean("jcTextbookVolumnService");//册次

		/**
		 * 知识点管理上传
		 */
		this.knowledgeTreeService = (KnowledgeTreeService)applicationContext.getBean("knowledgeTreeService");
		this.knowledgeNodeService = (KnowledgeNodeService)applicationContext.getBean("knowledgeNodeService");
		this.fileService = (FileService) applicationContext.getBean("fileService");
		this.resourceHandlerService = (ResourceHandlerService)applicationContext.getBean("resourceHandlerService");
		this.homeworkService = (HomeworkService)applicationContext.getBean("homeworkService");
		this.teachingPlanService = (TeachingPlanService)applicationContext.getBean("teachingPlanService");
		this.microLessonService = (MicroLessonService)applicationContext.getBean("microLessonService");
		this.learningDesignService = (LearningDesignService)applicationContext.getBean("learningDesignService");
		this.examService = (ExamService)applicationContext.getBean("examService");
		this.materialService = (MaterialService)applicationContext.getBean("materialService");
		this.learningPlanService = (LearningPlanService)applicationContext.getBean("learningPlanService");
		this.resourceService = (ResourceService)applicationContext.getBean("resourceService");
		this.resKnowledgeResourceService = (ResKnowledgeResourceService)applicationContext.getBean("resKnowledgeResourceService");

	}
	
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		
	}

	public void start( String path ) {
		Long startTime= System.currentTimeMillis();
		System.out.println("----------------------资源进行导入---begin---------------");
		System.out.println("----------------------资源进行导入中----------------------");
		this.entrance(path);
		System.out.println("----------------------添加资源执行关闭，输出执行结果-- end--------------");
		
		System.out.println("----------------------添加资源执行关闭，输出执行结果---begin---------------");
		System.out.println("----------------------添加资源执行关闭，输出执行结果-- end--------------");
		System.out.println("----------------------导出txt文件---begin---------------");
		this.outBeforeListToTxt(beforeDone.getResourseList());
		this.outDoneListToTxt(nowDone.getResourseList());
		this.outUndoListToTxt(nowUndo.getResourseList());
		System.out.println("----------------------导出txt文件-- end--------------");
		Long endTime= System.currentTimeMillis();
		
		Date startDate = new Date(startTime);
		
		Date endDate = new Date(endTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = sdf.format(startDate);
		String date2 = sdf.format(endDate);
		System.out.println("开始时间："+date1);
		System.out.println("结束时间："+date2);
	}
	/**
	 * 内容类型为 前面文件夹分别为 《学段，科目，版本，年级，册次》前台解析的时候需要判断年级中是否包含 123，，，一二三，，，之类的数字问题，如果包括，就把年级去掉
	 * 中间内容为教材目录内容
	 * 最后一个文件夹为资源为资源文件夹，资源文件夹的名称受到限制 只能为 《教案，课件，试卷，素材，微课，作业》
	 * 
	 */
	//正常的 小学\语文\版本\年级\册次
	  //把目录解析成一棵树，判断教材目录是否正确
	public void entrance(String path) {
		  this.init();//初始化
		  OpenResourse openResourse = verifyDirectoryAndFiles(path);//校验目录文件
		  
		List<TextBookVoResourse> voResourseList = openResourse.getVoResourseList();
		
		if(voResourseList != null&&voResourseList.size()>0 ){//判断教材对象是否存在，判断目录对象是否存在，判断资源对象是否存在
			voResourseList = dealWithTextBook(voResourseList);//处理教材信息
			voResourseList = dealWithTextBookCatalog(voResourseList);//处理教材目录信息
			dealWithTextBookCatalogResource(voResourseList);//处理每一个资源信息
		}else{
			
			//nowUndoResourse.setMessage(OpenResourse.ERROR);
		}
		
		
		System.out.println("----------------------添加资源执行关闭，输出执行结果---begin---------------");
		System.out.println("----------------------添加资源执行关闭，输出执行结果-- end--------------");
		
	}
	public OpenResourse verifyDirectoryAndFiles(String path) {
		OpenResourse openResourse = new OpenResourse();
		  File file = new File(path);
		  File[] stageList = file.listFiles();//第一层学科
		  voResourseList.clear();
		  for (int i = 0; i < stageList.length; i++) {
			File stageFile = stageList[i];
			 Map<String, Object> textBookMap = new HashMap<String, Object>();
			 iteraterStage(stageFile,textBookMap);//校验学段是否正确
			}
		  openResourse.setVoResourseList(voResourseList);
		return openResourse;
	}
	/**
	 * 处理文件夹内的资源
	 * @param voResourseList
	 */
	public void dealWithTextBookCatalogResource(List<TextBookVoResourse> voResourseList) {
		for (int k = 0; k < voResourseList.size(); k++) {
			TextBookVoResourse textBookVoResourse = voResourseList.get(k);
			List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
			if(voCatalogVoResourseList != null&&voCatalogVoResourseList.size()>0){
				for (int l = 0; l < voCatalogVoResourseList.size(); l++) {
					TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(l);
					//拿到对应的目录信息,查看资源情况
					
			    	addResourseByCatalog(
							textBookVoResourse,
							textBookCatalogVoResourse);
				}
			}else{
				 textBookVoResourse.setMessage(TextBookVoResourse.CATALOGERROR);//不存在教材目录内容
				 textBookVoResourse.setWrong(true);
				 
				 	Resourse resourse = new Resourse();
					resourse.setMessage("不存在教材目录内容");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
				 
			}
		}
	}
	/**
	 * 处理文件夹内的教材目录内容
	 * @param voResourseList
	 * @return
	 */
	public List<TextBookVoResourse> dealWithTextBookCatalog(List<TextBookVoResourse> voResourseList) {
		for (TextBookVoResourse textBookVoResourse : voResourseList) {//处理每一个教材目录信息
			Stack<TextbookCatalog> stack = new Stack<TextbookCatalog>();
			if(textBookVoResourse.isWrong()){
				continue;
			}else{
				System.out.println("====:"+textBookVoResourse.getName());
				List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
				TextbookCatalogCondition firstCatalogCondition = new TextbookCatalogCondition();
				  firstCatalogCondition.setTestBookId(textBookVoResourse.getId());
				  firstCatalogCondition.setParentId(0);
				  firstCatalogCondition.setLevel(0);
				  List<TextbookCatalog> firstCatalogList = this.jcTextbookCatalogService.findTextbookCatalogByCondition(firstCatalogCondition);
				  if(firstCatalogList !=null && firstCatalogList.size()==1){
					    stack.push(firstCatalogList.get(0));
				  }else{
					    textBookVoResourse.setDoneBefore(textBookVoResourse.isDoneBefore());
						textBookVoResourse.setWrong(true);
						textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"，查询根目录失败");
						
						Resourse resourse = new Resourse();
						resourse.setMessage("查询根目录失败");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(nowUndo,resourse);
						
				  }
				  
					for (int j = 0; j < voCatalogVoResourseList.size(); j++) {
						TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(j);
						String allName = textBookCatalogVoResourse.getName();
						String first = "";
						String name = "";
					 	Pattern p = null;  
				        Matcher m = null;  
				        
				        // 去掉<>标签及其之间的内容  
				        p = Pattern.compile("[0-9]{1,}[-]{3}");  
				        m = p.matcher(allName); 
			            if(m.find()){
			            	first = allName.substring(0, allName.indexOf("---"));
			            	textBookCatalogVoResourse.setListOrder(Integer.parseInt(first));
			            	name = allName.substring(allName.indexOf("---")+3, allName.length());
			            	textBookCatalogVoResourse.setName(name);
			            }else{
			            	Resourse resourse = new Resourse();
							resourse.setMessage("教材目录名称有问题");
							resourse.setName(textBookCatalogVoResourse.getName());
							resourse.setPath(textBookCatalogVoResourse.getPath());
							addDetailMessage(nowUndo,resourse);
							continue;
			            }
					    
					 if(textBookVoResourse.isDoneBefore()){
						
						  //根据教材的before属性判断是否进行 查询教材目录动作，
						  // 若是之前添加的 择执行查询动作，判断目录是否已经进行过相关的插入
						  
						  TextbookCatalogCondition catalogConditionTemp = new TextbookCatalogCondition();
						  catalogConditionTemp.setLevel(textBookCatalogVoResourse.getLevel());//level需要设置好
						  catalogConditionTemp.setTestBookId(textBookVoResourse.getId());
						  catalogConditionTemp.setName(textBookCatalogVoResourse.getName());
						  catalogConditionTemp.setIsDelete(false);
						  catalogConditionTemp.setParentId(this.getParentId(stack,catalogConditionTemp.getLevel()));
						  
						  //其他相关查询属性
						  List<TextbookCatalog>  textbookCatalogList = this.jcTextbookCatalogService.findTextbookCatalogByCondition(catalogConditionTemp);
						  if(textbookCatalogList != null&&textbookCatalogList.size()==1){
							  TextbookCatalog textBookCatalogTemp = null;
							  textBookCatalogTemp = textbookCatalogList.get(0);
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textBookCatalogTemp.getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.BEFORESUCCESS);
							  
							  if(textBookCatalogTemp.getListOrder() == null||textBookCatalogTemp.getListOrder()<1){
								  textBookCatalogTemp.setListOrder(textBookCatalogVoResourse.getListOrder());
								  this.jcTextbookCatalogService.modify(textBookCatalogTemp);
							  }
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("数据库已经存在对应教材目录");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(beforeDone,resourse);
							  
							  stack.push(textBookCatalogTemp);
							  
						  }else if(textbookCatalogList != null&&textbookCatalogList.size()>1){
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textbookCatalogList.get(0).getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+"数据库目录数据有问题");
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("数据库对应教材目录有问题");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(nowUndo,resourse);
							  
						  }else{
							  //添加教材目录
							  this.addTextBookCatalog(stack, 
									  textBookVoResourse,
									  textBookCatalogVoResourse);
							  
						  }
					  
						
					}else{
						  
						  //添加教材目录
						  this.addTextBookCatalog(
								  stack, 
								  textBookVoResourse,
								  textBookCatalogVoResourse);
					  }
				}
			}
			
		}
		
		return voResourseList;
	}
	/**
	 * 处理文件夹内的教材信息
	 * @param voResourseList
	 */
	public List<TextBookVoResourse> dealWithTextBook(List<TextBookVoResourse> voResourseList) {
		
		for (int i = 0; i < voResourseList.size(); i++) {
			    TextBookVoResourse textBookVoResourse = voResourseList.get(i);
			    System.out.println(textBookVoResourse.getName());
				this.iteraterTextBook(textBookVoResourse);//处理每一个教材信息
		}
		
		return voResourseList;
	}


	public static void outTextBookMessage(List<Resourse> resourseList) {
		if(resourseList != null &&resourseList.size()>0){
			for (Resourse resourse : resourseList) {
				System.out.println(resourse.getPath()+":"+resourse.getMessage());
			}
		}
	}
	
	
	private void addResourseByCatalog(
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		if(textBookCatalogVoResourse.isWrong()){
			
		}else{
			
			//之前有过添加，
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					boolean flag = true;
					
					try {
						flag = !this.doFilterResourse(resourseVo);
					} catch (Exception e1) {
						Resourse resourse = new Resourse();
						resourse.setMessage("资源有问题，请检查");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
						
					}
					
					if(flag){
						Resourse resourse = new Resourse();
						resourse.setMessage("资源后缀名有问题，请检查");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
					}
					
					
					
					
					File resoursefile = new File(resourseVo.getPath());
					String title = resoursefile.getName();//资源标题
					String png = "_thumbImg.png";
					if(title != null&&title.indexOf(png) >0){
						continue;
					}
					boolean isUpload = false;//判断资源是否添加过
		    		
					if(isUpload){
						resourseVo.setMessage(ResourseVo.BEFORESUCCESS);
						Resourse resourse = new Resourse();
						resourse.setMessage("数据库已经存在对应资源");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(beforeDone,resourse);
						  
					}else{
						
						
						try {
							Resource resource = null;
							boolean ensureAdd = true;
							
							if(textBookCatalogVoResourse.isDoneBefore()){
								//添加查询模块
								ensureAdd = false;
								
		    					
		    					ImportCatalogVo cv = new ImportCatalogVo();
		    					cv.setCatalogCode(textBookCatalogVoResourse.getId() + "");
		    					cv.setGradeCode(textBookVoResourse.getGradeCode());
		    					cv.setGradeName(gradeNameMap.get(textBookVoResourse.getGradeCode()));
		    					cv.setStageCode(textBookVoResourse.getStageCode());
		    					cv.setStageName(stageNameMap.get(textBookVoResourse.getStageCode()));
		    					cv.setSubjectCode(textBookVoResourse.getSubjectCode());
		    					cv.setSubjectName(subjectNameMap.get(Integer.parseInt(textBookVoResourse.getSubjectCode())));
		    					cv.setVersionCode(textBookVoResourse.getVersion());
		    					cv.setVersionName(versionNameMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
		    					cv.setVolumnCode(textBookVoResourse.getVolumn());
		    					cv.setVolumnName(volumnNameMap.get(textBookVoResourse.getVolumn()));
		    					
		    					boolean cofirmExist = false;
		    					
		    					cofirmExist = importResourceServiceImpl.confirmExist(SysContants.SYSTEM_APP_ID, SysContants.SYSTEM_OWNER_ID,resourseVo.getResType(), title, cv);
		    				
		    					if(cofirmExist){
		    						System.out.println("已经存在： "+resourseVo.getPath());
									resourseVo.setMessage("数据库已经存在对应资源");
									Resourse resourse = new Resourse();
									resourse.setMessage("数据库已经存在对应资源");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(beforeDone,resourse);
									
								}
		    					
		    					ensureAdd = !cofirmExist; 
								
							}else{
								 ensureAdd = true;
							}
							
							
							if(ensureAdd){//添加对应的资源内容
								/*File resoursefile = new File(resourseVo.getPath());
		    					String title = resoursefile.getName();//资源标题*/
		    				    String suffix=title.substring(title.lastIndexOf(".")+1);//文件后缀
		    				    InputStream input = new FileInputStream(resoursefile);//文件输入流
		    					ImportCatalogVo cv = new ImportCatalogVo();
		    					cv.setCatalogCode(textBookCatalogVoResourse.getId() + "");
		    					cv.setGradeCode(textBookVoResourse.getGradeCode());
		    					cv.setGradeName(gradeNameMap.get(textBookVoResourse.getGradeCode()));
		    					cv.setStageCode(textBookVoResourse.getStageCode());
		    					cv.setStageName(stageNameMap.get(textBookVoResourse.getStageCode()));
		    					cv.setSubjectCode(textBookVoResourse.getSubjectCode());
		    					cv.setSubjectName(subjectNameMap.get(Integer.parseInt(textBookVoResourse.getSubjectCode())));
		    					cv.setVersionCode(textBookVoResourse.getVersion());
		    					cv.setVersionName(versionNameMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
		    					cv.setVolumnCode(textBookVoResourse.getVolumn());
		    					cv.setVolumnName(volumnNameMap.get(textBookVoResourse.getVolumn()));
		    					
		    					//调用资源添加接口
		    					String resourseThumbName = resoursefile.getAbsolutePath().replaceFirst(resoursefile.getName(), resoursefile.getName().substring(0,resoursefile.getName().lastIndexOf(".")))+"_thumbImg.png";
		    					File resourseThumbfile = new File(resourseThumbName);
		    					InputStream inputThumb = null;
		    					if(!resourseThumbfile.exists()){
		    						inputThumb = null; //获取默认的图片流
		    					}else{
		    						inputThumb =  new FileInputStream(resourseThumbfile);//文件输入流
		    					}
		    				    cv.setLibraryId(ResourceCondition.DEFAULT_LIBRARY_ID);
		    					System.out.println("开始导入： "+resourseVo.getPath());
		    				    resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("资源添加成功");
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加成功");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
									System.out.println("导入失败： "+resourseVo.getPath());
									resourseVo.setMessage("资源添加失败");
									
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加失败");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowUndo,resourse);
									
									
								}
							}
							
							
						} catch (Exception e) {
							
							resourseVo.setMessage(ResourseVo.ERROR+"添加资源报未知问题");
							Resourse resourse = new Resourse();
							resourse.setMessage("添加资源报未知问题");
							resourse.setName(resourseVo.getName());
							resourse.setPath(resourseVo.getPath());
							addDetailMessage(nowUndo,resourse);
							e.printStackTrace();
							continue;
							
						}
					}
				}
			}else{
				/*textBookCatalogVoResourse.setMessage("目录中不存在相关资源内容");
				Resourse resourse = new Resourse();
				resourse.setMessage("目录中不存在相关资源内容");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);*/
			}
			
		}
	}
	/**
	 * 初始化
	 */
	public void init() {
		  this.initVersionMap();//初始化版本
		  this.initGradeMap();//初始化年级
		  this.initStageMap();//初始化学段
		  this.initSubjectMap();//初始化科目
		  this.initVolumnMap();//初始化册次
		  this.initArray();//初始化资源过滤条件
	}
	/**
	 * 初始化年级
	 */
	private void initGradeMap(){
		List<Grade> gradeList = this.jcGradeService.findAll();
		for (Grade grade : gradeList) {
			if(grade.getLength() == 6||grade.getLength() == 5 ||grade.getLength() == 3){
				gradeMap.put(grade.getName(), grade.getCode());
				gradeNameMap.put(grade.getCode(),grade.getName());
			}else{
				
			}
			
		}
		
		/*gradeMap.put("高中", "");
		gradeNameMap.put("", "高中");*/
	}
	/**
	 * 初始化版本
	 */
	private void initVersionMap(){
		
		List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(null);
		for (TextbookVersion version : versionList) {
			versionMap.put(version.getName(), version.getId());
			versionNameMap.put(version.getId(), version.getName());
			
			publishMap.put(version.getId(), version.getPublisherId());
		}
		
	}
	/**
	 * 初始化学段
	 */
	private void initStageMap(){
		List<Stage> stageList = this.jcStageService.findAll();
		for (Stage stage : stageList) {
			if(stage.getLength() == 6||stage.getLength() == 5 ||stage.getLength() == 3){
				stageMap.put(stage.getName(), stage.getCode());
				stageNameMap.put(stage.getCode(), stage.getName());
			}else{
				
			}
		}
	}
	/**
	 * 初始化科目
	 */
	private void initSubjectMap(){
		List<Subject> subjectList = this.jcSubjectService.findAll();
		for (Subject subject : subjectList) {
			if(subject.getSubjectClass() != null&&"1".equals(subject.getSubjectClass()) ){
				subjectMap.put(subject.getName(), subject.getCode());
				subjectNameMap.put(subject.getCode(), subject.getName());
			}
			
		}
	}
	
	/**
	 * 初始化册次
	 */
	private void initVolumnMap(){//volumnMap
		List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(null);
		for (TextbookVolumn volumn : volumnList) {
			
			volumnMap.put(volumn.getName(), volumn.getCode());
			volumnNameMap.put(volumn.getCode(), volumn.getName());
		}
		
	}
	
	private void initArray(){
		ResouceUtil util = new ResouceUtil();
		ResourceProperties resouce = util.getProperties();
		defaultMicros=resouce.getDefaultMicros();
		defaultLearning_designs=resouce.getDefaultLearning_designs();
		defaultHomeworks=resouce.getDefaultHomeworks();
		defaultExams=resouce.getDefaultExams();
		defaultTeaching_plans=resouce.getDefaultTeaching_plans();
		defaultMaterials=resouce.getDefaultMaterials();
		defaultOthers=resouce.getDefaultOthers();
		defaultLearning_plans = resouce.getDefaultLearning_plan();
	}
	  
	


	
	
	/**
	 * 循环校验学段 
	 * 学段，科目，版本，年级，册次
	 * @param textBookMap
	 * @param stageFile
	 */
	private void iteraterStage(File stageFile, Map<String, Object> textBookMap) {
		
		boolean stageOk = false;
		String stageCode = null;
		stageCode = stageMap.get(stageFile.getName());
		if(stageCode != null&&!"".equals(stageCode)){
			stageOk = true;
			textBookMap.put("stage", stageCode);
		}else{
			stageOk = false;
		}
		
		if(stageOk){
			File[] subjectList = stageFile.listFiles();
			for (int j = 0; j < subjectList.length; j++) {
				File subjectFile = subjectList[j];
				iteratorSubject( subjectFile,textBookMap);//循环校验科目
			}
			
			
		}else{

			String errorMessage = "学段信息不正确";
			
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(stageFile.getName());
			resourse.setPath(stageFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(stageFile, errorResourse, errorMessage);
		}
		
	}
	/**
	 * 循环校验科目
	 * 学段，科目，版本，年级，册次
	 * @param textBookMap
	 * @param subjectFile
	 */
	private void iteratorSubject( File subjectFile,Map<String, Object> textBookMap) {
		
		
		boolean subjectOk = false;
		
		Integer subjectCode = null;
		subjectCode = subjectMap.get(subjectFile.getName());
		if(subjectCode != null&&subjectCode >0){
			subjectOk = true;
			textBookMap.put("subject", subjectCode);
		}else{
			subjectOk = false;
		}
		
		if(subjectOk){
			File[] versionList = subjectFile.listFiles();
			for (int k = 0; k < versionList.length; k++) {
				File versionFile = versionList[k];
				iteratorVersion(versionFile,textBookMap);//循环校验版本
				
				
			}
			
		}else{
			String errorMessage = "科目信息不正确";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(subjectFile.getName());
			resourse.setPath(subjectFile.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}
	/**
	 * 循环校验版本
	 * 学段，科目，版本，年级，册次
	 * @param versionFile
	 * @param textBookMap
	 */
	private void iteratorVersion( File versionFile,Map<String, Object> textBookMap) {
		
		boolean versionOk = true;
		Integer versionId = null;
		versionId = versionMap.get(versionFile.getName());
		if(versionId != null&&versionId>0){
			versionOk = true;
			
			textBookMap.put("version", versionId);
		}else{
			versionOk = false;
		}
		
		if(versionOk){
			File[] versionList = versionFile.listFiles();
			for (int k = 0; k < versionList.length; k++) {
				File gradeFile = versionList[k];
				iteratorGrade( gradeFile,textBookMap);//循环校验学年
				
				
			}
			
		}else{
			String errorMessage = "版本信息不正确";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(versionFile.getName());
			resourse.setPath(versionFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(versionFile, errorResourse, errorMessage);
		}
	}
	
	/**
	 * 循环校验学年
	 * 学段，科目，版本，年级，册次
	 * @param textBookMap
	 * @param gradeFile
	 */
	private void iteratorGrade(File gradeFile,Map<String, Object> textBookMap) {
		
		
		
		boolean gradeOk = false;
		String gradeFileCode = gradeMap.get(gradeFile.getName());
		if(gradeFileCode != null&&!"".equals(gradeFileCode)){
			gradeOk = true;
			textBookMap.put("grade", gradeFileCode);
		}else{
			gradeOk = false;
		}
		
		
		//如果是高中设置年级为空，
		if("高中".equals(stageNameMap.get(textBookMap.get("stage")))){
			gradeOk = true;
			textBookMap.put("grade", "");
		}
		
		
		if(gradeOk){
			File[] volomnList = gradeFile.listFiles();
			for (int l = 0; l < volomnList.length; l++) {
				File volomnFile = volomnList[l];
				iteratorVolmn( volomnFile,textBookMap);
				
			}
			
		}else{
			String errorMessage = "年级信息不正确";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(gradeFile.getName());
			resourse.setPath(gradeFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(gradeFile, errorResourse, errorMessage);
		}
	}
	/**
	 * 循环校验册次
	 * 学段，科目，版本，年级，册次
	 * @param textBookMap
	 * @param volomnFile
	 */
	private void iteratorVolmn(File volomnFile,Map<String, Object> textBookMap) {
		//textBookVoResourse.setVolumn(volomnFile.getName());
		
		boolean volumnOk = true;
		String volumnCode = null;
		volumnCode = volumnMap.get(volomnFile.getName());
		if(volumnCode != null&&!"".equals(volumnCode)){
			textBookMap.put("volumn", volumnCode);
			volumnOk = true;
		}else{
			volumnOk = false;
		}
		
		//如果是高中设置年级为空，
		if("高中".equals(stageNameMap.get(textBookMap.get("stage")))){
			volumnOk = true;
			TextbookVolumn textbookVolumn = new TextbookVolumn();
			textbookVolumn.setCode(MD5.GetMD5Code(volomnFile.getName()));
			textbookVolumn.setName(volomnFile.getName());
			
			TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
			textbookVolumnCondition.setCode(textbookVolumn.getCode());
			textbookVolumnCondition.setName(textbookVolumn.getName());
			textbookVolumnCondition.setIsDelete(false);
			List<TextbookVolumn> textBookVolumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
			TextbookVolumn volumn =  null;
			if(textBookVolumnList != null&&textBookVolumnList.size()>0){
				volumn = textBookVolumnList.get(0);
			}else{
				volumn = this.jcTextbookVolumnService.add(textbookVolumn);
			}
			
			textBookMap.put("volumn", volumn.getCode());
			volumnNameMap.put(volumn.getCode(), volomnFile.getName());
		}
		
		if(volumnOk){//处理目录问题
			
			TextBookVoResourse textBookVoResourse = new TextBookVoResourse();
			textBookVoResourse.setStageCode((String)textBookMap.get("stage"));
			textBookVoResourse.setSubjectCode((Integer)textBookMap.get("subject")+"");
			textBookVoResourse.setVersion((Integer)textBookMap.get("version")+"");
			textBookVoResourse.setGradeCode((String)textBookMap.get("grade"));
			textBookVoResourse.setVolumn((String)textBookMap.get("volumn"));
			String gradeName = gradeNameMap.get((String)textBookMap.get("grade"));
			String subjectName = subjectNameMap.get((Integer)textBookMap.get("subject"));
			String volumnName = volumnNameMap.get((String)textBookMap.get("volumn"));
			
			//如果是高中设置年级为空，
			String textBookName = "";
			if("高中".equals(stageNameMap.get(textBookMap.get("stage")))){
				textBookName = "高中"+subjectName;
			}else{
				textBookName = gradeName+subjectName+volumnName;
			}
			
			textBookVoResourse.setName(textBookName);
			textBookVoResourse.setPath(volomnFile.getPath());
			List<TextBookCatalogVoResourse>  catalogList = new ArrayList<TextBookCatalogVoResourse>();
			Stack<ResourseVo> catalogStack = new Stack<ResourseVo>();
			List<ResourseVo> catalogStackList = new ArrayList<ResourseVo>();
			File[] catalogListFile = volomnFile.listFiles();
			for (File file : catalogListFile) {
				Integer level = 1;
				//iteratorCatalog(catalogStack,file,level);//循环迭代目录结构
				iteratorCatalogStackList(catalogStackList,file,level);
			}
			
			boolean isLeafBefore = false;
			boolean escape = true;
			boolean resourseUsed = true;
			boolean changeResourse = false;
			List<ResourseVo> resultResourseList = new ArrayList<ResourseVo>();
			List<ResourseVo> tempResourseList = new ArrayList<ResourseVo>();
			
			for (int i = 0; i < catalogStack.size(); i++) {
				ResourseVo vo = catalogStack.get(i);
				System.out.println(vo.getPath()+":"+vo.getName()); 
			}
			
			
			//while(!catalogStack.isEmpty()){
			for (int i = catalogStackList.size()-1; i >=0 ; i--){
				//ResourseVo resouseVo = catalogStack.pop();
				ResourseVo resouseVo = catalogStackList.get(i);
				System.out.println(resouseVo.getPath()+":"+resouseVo.getName());
				if(resouseVo.isLeaf()){
					if(changeResourse){
						resultResourseList = new ArrayList<ResourseVo>();
						changeResourse = false;
					}
					
					isLeafBefore = true;
				
					ResourseVo tempResourseVo = new ResourseVo();
					tempResourseVo.setName(resouseVo.getName());
					tempResourseVo.setPath(resouseVo.getPath());
					tempResourseVo.setResName(resouseVo.getName());
					//tempResourseVo.setLevel(0);
					tempResourseList.add(tempResourseVo);
					
				}else{
					if(isLeafBefore){
						Integer value  = resourseFileMap.get(resouseVo.getName());
						if(value != null&&value>0){
							for (ResourseVo resourseVo2 : tempResourseList) {
								resourseVo2.setResType(value);
								resourseVo2.setResName(value+"");
							}
							for (ResourseVo resourseVo3 : tempResourseList) {
								resultResourseList.add(resourseVo3);
								resourseUsed = false;
							}
							
							tempResourseList = new ArrayList<ResourseVo>();
							
						}else{
							//资源文件夹名称不对
						}
							
						}else{
							if(escape&&resourseFileMap.get(resouseVo.getName())!=null&&resourseFileMap.get(resouseVo.getName())>0){
								
							}else{
								
								TextBookCatalogVoResourse catalogVoResourse = new TextBookCatalogVoResourse();
								catalogVoResourse.setPath(resouseVo.getPath());
								catalogVoResourse.setName(resouseVo.getName());
								catalogVoResourse.setLevel(resouseVo.getLevel());
								if(resultResourseList != null&&resultResourseList.size()>0&&!resourseUsed){
									catalogVoResourse.setResourseVoList(resultResourseList);
									resourseUsed = true;
									changeResourse = true;
								}
								
								catalogList.add(catalogVoResourse);
								
							}
							
					}
					
					isLeafBefore = false;
				}
			}
			
			//颠倒一下顺序
			List<TextBookCatalogVoResourse>  catalogListResult = new ArrayList<TextBookCatalogVoResourse>();
			for (int i = catalogList.size()-1; i >= 0; i--) {
				catalogListResult.add(catalogList.get(i));
			}
			textBookVoResourse.setVoCatalogVoResourseList(catalogListResult);
			System.out.println(textBookVoResourse.getName());
			voResourseList.add(textBookVoResourse);
			
		}else{
			String errorMessage = "册次信息不正确";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(volomnFile.getName());
			resourse.setPath(volomnFile.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}
	
	private void iteratorCatalogStackList(List<ResourseVo> catalogStackList,File file,Integer level){
		String[] names = file.getName().split("---");
		
		ResourseVo resouseVo = new ResourseVo();
		resouseVo.setLeaf(false);
		if(names!=null && names.length==2) {
			resouseVo.setLevel(Integer.parseInt(names[0]));
		}else {
			resouseVo.setLevel(level);
		}
		
		resouseVo.setPath(file.getAbsolutePath());
		resouseVo.setResType(0);
		resouseVo.setName(file.getName());
		
		if(file.isFile()){
			resouseVo.setLeaf(true);
			catalogStackList.add(resouseVo);
			return;
		}else{
			resouseVo.setLeaf(false);
			catalogStackList.add(resouseVo);
			File[] fileList = file.listFiles();
			if(fileList != null &&fileList.length>0){
				++level;
				for (File file2 : fileList) {
					iteratorCatalogStackList(catalogStackList,file2,level);
				}
			}else{
				
				return;
			}
			
			
		}
		
	}

	
	/**
	 * 添加教材目录
	 * @param stack
	 * @param textBookVoResourse
	 * @param textBookCatalogVoResourse
	 */
	private void addTextBookCatalog(Stack<TextbookCatalog> stack,
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		TextbookCatalog textBookCatalogTemp;
		TextbookCatalog textBookCatalogAdd  = new TextbookCatalog();  // 根据资源目录设置要添加到数据库的字段属性值
		
		  textBookCatalogAdd.setLevel(textBookCatalogVoResourse.getLevel());//level需要设置好
		  textBookCatalogAdd.setTestBookId(textBookVoResourse.getId());
		  textBookCatalogAdd.setName(textBookCatalogVoResourse.getName());
		  textBookCatalogAdd.setListOrder(textBookCatalogVoResourse.getListOrder());
		  textBookCatalogAdd.setIsDelete(false);
		  textBookCatalogAdd.setCreateTime(new Date());
		  textBookCatalogAdd.setModifyTime(new Date());
		  textBookCatalogAdd.setParentId(this.getParentId(stack,textBookCatalogAdd.getLevel()));
		  
		  try {
			
			textBookCatalogTemp = this.jcTextbookCatalogService.add(textBookCatalogAdd);
			if(textBookCatalogTemp != null&&textBookCatalogTemp.getId()>1){
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(false);
				  textBookCatalogVoResourse.setId(textBookCatalogTemp.getId());
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.SUCCESS);
				  
				  	Resourse resourse = new Resourse();
					resourse.setMessage("教材目录添加成功");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowDone,resourse);
				  
				  stack.push(textBookCatalogTemp);
			  }else{
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(true);
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+",教材目录添加出错");
				  	Resourse resourse = new Resourse();
					resourse.setMessage("教材目录添加出错");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
			  }
		} catch (Exception e) {
			textBookCatalogVoResourse.setDoneBefore(false);
			textBookCatalogVoResourse.setWrong(true);
			textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+","+e.getMessage().substring(0,e.getMessage().length()>5?5:e.getMessage().length()));
			Resourse resourse = new Resourse();
			resourse.setMessage("教材目录添加出错,"+e.getMessage());
			resourse.setName(textBookCatalogVoResourse.getName());
			resourse.setPath(textBookCatalogVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}

	
	/**
	 * 根据队列确定目录的父目录是哪个
	 * @param stack
	 * @param level
	 * @return
	 */
	private Integer getParentId(Stack<TextbookCatalog> stack,Integer level) {
		TextbookCatalog mid = stack.peek();
		BigDecimal catalogLevel = new BigDecimal(level);
		if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1){
			
		}else{
			while(!(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1)){//根据节点的层次寻找自己的父类
				if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==0){
					stack.pop();
					mid = stack.peek();
				}else if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==-1){
					stack.pop();
					mid = stack.peek();
				}
			}
		}
		if(stack.empty()){
			stack.push(mid);
		}
		Integer parentId = mid.getId();
		return parentId;
	}
	/**
	 * 添加教材
	 * @param textBookVoResourse
	 */
	private void iteraterTextBook(TextBookVoResourse textBookVoResourse) {
		List<Textbook> textbookList = getTextBookByCondition(textBookVoResourse); //数据库查找相关教材，判断教材是否存在，不存在进行添加
		
		if(textbookList != null&&textbookList.size()==1){
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(false);
			textBookVoResourse.setId(textbookList.get(0).getId());
			textBookVoResourse.setMessage(TextBookVoResourse.BEFORESUCCESS);
			
			Resourse resourse = new Resourse();
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			resourse.setMessage("数据库已经存在对应教材");
			addDetailMessage(beforeDone,resourse);//已经添加过得目录内容
			TextbookCatalogCondition jcTextbookCatalogCondition = new TextbookCatalogCondition();
			
			jcTextbookCatalogCondition.setIsDelete(false);
			jcTextbookCatalogCondition.setLevel(0);
			jcTextbookCatalogCondition.setListOrder(0);
			jcTextbookCatalogCondition.setParentId(0);
			jcTextbookCatalogCondition.setTestBookId(textbookList.get(0).getId());
			List<TextbookCatalog> catalogList = this.jcTextbookCatalogService.findTextbookCatalogByCondition(jcTextbookCatalogCondition);
			
			if(catalogList != null&&catalogList.size()>0){
				
			}else{
				
				Textbook textBook = textbookList.get(0);
				addCatalogWithTextBook(textBookVoResourse, textBook);
			}
			
		}else if(textbookList.size()>1){//查询出来两本及其以上的教材，这种情况为出错情况
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR);//程序添加教材的地方没有控制好，出错了
			Resourse resourse = new Resourse();
			resourse.setMessage("数据库的教材有问题");
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);//已经添加过得目录内容
		}else{
			
			Textbook textBookAdd = new Textbook();
			textBookAdd.setName(textBookVoResourse.getName());
			textBookAdd.setStageCode(textBookVoResourse.getStageCode());
			textBookAdd.setSubjectCode(textBookVoResourse.getSubjectCode());
			textBookAdd.setGradeCode(textBookVoResourse.getGradeCode());
			textBookAdd.setVersion(textBookVoResourse.getVersion());
			textBookAdd.setVolumn(textBookVoResourse.getVolumn());
			textBookAdd.setPublisherId(publishMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
			textBookAdd.setCreateDate(new Date());
			textBookAdd.setIsDelete(false);
			textBookAdd.setModifyTime(new Date());
			textBookAdd.setIsHidden(false);
			textBookAdd.setUuid(UUIDUtil.getUUID());
			Textbook textBook = null;
			try {
				textBook = this.jcTextbookService.add(textBookAdd);//调用添加教材的方法
				if(textBook != null&&textBook.getId()>0){
					addCatalogWithTextBook(textBookVoResourse, textBook);
				}else{
					textBookVoResourse.setDoneBefore(false);
					textBookVoResourse.setWrong(true);
					textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
					Resourse resourse = new Resourse();
					resourse.setMessage("教材添加失败");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);//添加教材失败
				}
			} catch (Exception e) {
				textBookVoResourse.setDoneBefore(false);
				textBookVoResourse.setWrong(true);
				textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
				Resourse resourse = new Resourse();
				resourse.setMessage("教材添加失败:"+e.getMessage());
				resourse.setName(textBookVoResourse.getName());
				resourse.setPath(textBookVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);//添加教材失败
				
				e.printStackTrace();
			}
			
			
		}
	}
	private void addCatalogWithTextBook(TextBookVoResourse textBookVoResourse,
			Textbook textBook) {
		textBookVoResourse.setDoneBefore(false);
		textBookVoResourse.setWrong(false);
		textBookVoResourse.setId(textBook.getId());
		textBookVoResourse.setMessage(TextBookVoResourse.SUCCESS);
		Resourse resourse = new Resourse();
		resourse.setMessage("教材添加成功");
		resourse.setName(textBookVoResourse.getName());
		resourse.setPath(textBookVoResourse.getPath());
		addDetailMessage(nowDone,resourse);//添加教材成功
		
		TextbookCatalog catalog = new TextbookCatalog();
		catalog.setCreateTime(new Date());
		catalog.setIsDelete(false);
		catalog.setLevel(0);
		catalog.setModifyTime(new Date());
		catalog.setName(textBookVoResourse.getName());
		catalog.setListOrder(0);
		catalog.setPage(1);
		catalog.setParentId(0);
		catalog.setTestBookId(textBook.getId());
		
		TextbookCatalog  catalogAddResult  = this.jcTextbookCatalogService.add(catalog);
		if(catalogAddResult != null&&catalogAddResult.getId()>0){
			Resourse resourse2 = new Resourse();
			resourse2.setMessage("根目录添加成功");
			resourse2.setName(textBookVoResourse.getName());
			resourse2.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowDone,resourse2);//添加过得目录内容
		}else{
			textBookVoResourse.setDoneBefore(false);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"，添加根目录失败");
			
			Resourse resourse3 = new Resourse();
			resourse3.setMessage("根目录添加失败");
			resourse3.setName(textBookVoResourse.getName());
			resourse3.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse3);//添加过得目录内容
		}
	}
	/**
	 * 输出信息
	 * @param beginResourse
	 * @param resourse
	 */
	private void addDetailMessage(Resourse beginResourse,Resourse resourse) {
		List<Resourse> resourseList = beginResourse.getResourseList();
		 if(resourseList !=null&&resourseList.size()>0){
			 
		 }else{
			 resourseList = new ArrayList<Resourse>();
		 }
		 resourseList.add(resourse);
		 beginResourse.setResourseList(resourseList);
	}
	/**
	 * 根据查询条件查找教材列表
	 * @param textBookVoResourse
	 * @return
	 */
	private List<Textbook> getTextBookByCondition(
			TextBookVoResourse textBookVoResourse) {
		TextbookCondition textbookCondition = new TextbookCondition();
		textbookCondition.setStageCode(textBookVoResourse.getStageCode());
		textbookCondition.setGradeCode(textBookVoResourse.getGradeCode());
		textbookCondition.setSubjectCode(textBookVoResourse.getSubjectCode());
		textbookCondition.setVersion(textBookVoResourse.getVersion());
		textbookCondition.setVolumn(textBookVoResourse.getVolumn());
		List<Textbook> textbookList = this.jcTextbookService.findTextbookByCondition(textbookCondition);
		return textbookList;
	}


	/**
	 * 创建存放文件的目录
	 * @param dirPath
	 * @return 
	 */
	public static void creatDirs(String dirPath) {
		File txtDirecFile = new File(dirPath);
		  if((txtDirecFile.exists() && txtDirecFile.isDirectory())){
			  
		  }else{ 
			  txtDirecFile.mkdirs();
		  }
	}
	
	
	//undo
	private void outUndoListToTxt(List<Resourse> resourseList){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		 String dateStr = sdf.format(new Date());
		 ResouceUtil util = new ResouceUtil();
		 ResourceProperties resouceProper = util.getProperties();
		 String undoPath = resouceProper.getUndoPath();
		 String undoTxtFileName = undoPath+"/"+"undo"+dateStr+".txt";
		 creatDirs(undoPath);
		  
		  File undoFile = new File(undoTxtFileName);
		  StringBuffer sbf = new StringBuffer();
		  int count = 0;
		  if(resourseList != null &&resourseList.size()>0){
				for (Resourse resourse : resourseList) {
					++count;
					sbf.append("["+count+"] "+resourse.getPath()+"："+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }
		 
		 util.writeTxtFile(sbf.toString(), undoFile);
	}

	// done
	private void outDoneListToTxt(List<Resourse> resourseList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		ResouceUtil util = new ResouceUtil();
		ResourceProperties resouceProper = util.getProperties();
		String donePath = resouceProper.getDonePath();
		String doneTxtFileName = donePath + "/" + "done" + dateStr + ".txt";
		creatDirs(donePath);

		File doneFile = new File(doneTxtFileName);
		StringBuffer sbf = new StringBuffer();
		int count = 0;
		  if(resourseList != null &&resourseList.size()>0){
				for (Resourse resourse : resourseList) {
					++count;
					sbf.append("["+count+"] "+resourse.getPath()+"："+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }

		util.writeTxtFile(sbf.toString(), doneFile);
	}

	// before
	private void outBeforeListToTxt(List<Resourse> resourseList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		ResouceUtil util = new ResouceUtil();
		ResourceProperties resouceProper = util.getProperties();
		String beforePath = resouceProper.getBeforePath();
		String beforeTxtFileName = beforePath + "/" + "before" + dateStr+ ".txt";
		creatDirs(beforePath);

		File beforeFile = new File(beforeTxtFileName);
		StringBuffer sbf = new StringBuffer();
		int count = 0;
		  if(resourseList != null &&resourseList.size()>0){
				for (Resourse resourse : resourseList) {
					++count;
					sbf.append("["+count+"] "+resourse.getPath()+"："+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }
		util.writeTxtFile(sbf.toString(), beforeFile);
	}
	
	public static void outToTxt(List<Resourse> resourseList,String name,String beforePath,String suff) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		ResouceUtil util = new ResouceUtil();
		ResourceProperties resouceProper = util.getProperties();
		//String beforePath = resouceProper.getBeforePath();
		
		
		String beforeTxtFileName = beforePath + "/" + name + dateStr+ suff;
		creatDirs(beforePath);

		File beforeFile = new File(beforeTxtFileName);
		StringBuffer sbf = new StringBuffer();
		int count = 0;
		  if(resourseList != null &&resourseList.size()>0){
				for (Resourse resourse : resourseList) {
					++count;
					sbf.append("["+count+"] "+resourse.getPath()+"："+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }
		util.writeTxtFile(sbf.toString(), beforeFile);
	}
	
	public static String outToString(List<Resourse> resourseList) {
		StringBuffer sb = new StringBuffer();
		ResouceUtil util = new ResouceUtil();
		ResourceProperties resouceProper = util.getProperties();
		
		  int count = 0;
		  if(resourseList != null &&resourseList.size()>0){
				for (Resourse resourse : resourseList) {
					++count;
					sb.append("["+count+"] "+resourse.getPath()+"："+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }else{
			 sb.append("没有要输出的内容");
		 }
		return sb.toString();
	}
	
	
	private boolean arrayContainsStr(String[] array,String name){
		List<String> tempList = Arrays.asList(array);//利用list的包含方法，进行判断
		boolean result = false;
		name =name.toLowerCase();
		if(tempList.contains(name)){
			result = true;
		}else{
			result = false;
		}
		
		return result;
	 }
	/**
	 * 添加过滤资源的条件
	 * @param resourseVo
	 * @return
	 */
	private boolean doFilterResourse(ResourseVo resourseVo) throws Exception{
		boolean result = false;
		String name = resourseVo.getName();
		int resType = resourseVo.getResType();
		if(name == null||"".equals(name)){
			//文件名是什么东东
		}else{
			String strOld = name.substring(name.lastIndexOf("."), name.length());
			String str = strOld.toLowerCase();
			if(1 == resType){
				if(this.arrayContainsStr(defaultMicros, str)){
					result = true;
				}
				
			}else if(2 == resType){
				if(this.arrayContainsStr(defaultLearning_designs, str)){
					result = true;
				}
			}else if(3 == resType){
				if(this.arrayContainsStr(defaultHomeworks, str)){
					result = true;
				}
				
			}else if(4 == resType){
				if(this.arrayContainsStr(defaultExams, str)){
					result = true;
				}
			}else if(5 == resType){
				if(this.arrayContainsStr(defaultTeaching_plans, str)){
					result = true;
				}
				
			}else if(6 == resType){
				if(this.arrayContainsStr(defaultMaterials, str)){
					result = true;
				}
			}else if(7 == resType){
				if(this.arrayContainsStr(defaultOthers, str)){
					result = true;
				}
			}else if(8 == resType) {
				if(this.arrayContainsStr(defaultLearning_plans, str)){
					result = true;
				}
			}else
			{
				result = false;
			}
			
		}
		
		return result;
	}
	
/*    private static   String[] defaultMicros;
    private static   String[] defaultLearning_designs;
    private static   String[] defaultHomeworks;
    private static   String[] defaultExams;
    private static   String[] defaultTeaching_plans;
    private static   String[] defaultMaterials;*/
	/*//微课= 1;
    #public static final Integer MICRO 
   # //课件= 2;
    #public static final Integer LEARNING_DESIGN 
    #//作业= 3;
    #public static final Integer HOMEWORK 
    ##//试卷= 4;
   # public static final Integer EXAM 
    #//教案= 5;
   # public static final Integer TEACHING_PLAN 
    #//素材= 6;
    #public static final Integer MATERIAL */
	
	
	
	/*********************************************校本资源导入
	 * @param schoolId ******************************************************/
	public List<TextBookVoResourse> dealWithResTextBook(List<TextBookVoResourse> voResourseList, int schoolId) {
		for (int i = 0; i < voResourseList.size(); i++) {
			TextBookVoResourse textBookVoResourse = voResourseList.get(i);
			System.out.println(textBookVoResourse.getName());
			this.iteraterResTextBook(textBookVoResourse, schoolId);// 处理每一个教材信息
		}

		return voResourseList;
	}
	
	/**
	 * 添加教材
	 * @param textBookVoResourse
	 * @param schoolId 
	 */
	private void iteraterResTextBook(TextBookVoResourse textBookVoResourse, int schoolId) {
		List<ResTextbook> textbookList = getResTextBookByCondition(textBookVoResourse); //数据库查找相关教材，判断教材是否存在，不存在进行添加
		
		if(textbookList != null&&textbookList.size()==1){
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(false);
			textBookVoResourse.setId(textbookList.get(0).getId());
			textBookVoResourse.setMessage(TextBookVoResourse.BEFORESUCCESS);
			
			Resourse resourse = new Resourse();
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			resourse.setMessage("数据库已经存在对应教材");
			addDetailMessage(beforeDone,resourse);//已经添加过得目录内容
			
			ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
			
			resTextbookCatalogCondition.setIsDelete(false);
			resTextbookCatalogCondition.setParentId(0);
			resTextbookCatalogCondition.setTestBookId(textbookList.get(0).getId());
			List<ResTextbookCatalog> catalogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
			
			if(catalogList != null&&catalogList.size()>0){
				
			}else{
				
				ResTextbook textBook = textbookList.get(0);
				addCatalogWithResTextBook(textBookVoResourse, textBook);
			}
			
		}else if(textbookList.size()>1){//查询出来两本及其以上的教材，这种情况为出错情况
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR);//程序添加教材的地方没有控制好，出错了
			Resourse resourse = new Resourse();
			resourse.setMessage("数据库的教材有问题");
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);//已经添加过得目录内容
		}else{
			
			ResTextbook textBookAdd = new ResTextbook();
			textBookAdd.setName(textBookVoResourse.getName());
			textBookAdd.setStageCode(textBookVoResourse.getStageCode());
			textBookAdd.setSubjectCode(textBookVoResourse.getSubjectCode());
			textBookAdd.setGradeCode(textBookVoResourse.getGradeCode());
			textBookAdd.setVersion(textBookVoResourse.getVersion());
			textBookAdd.setVolumn(textBookVoResourse.getVolumn());
			textBookAdd.setPublisherId(publishMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
			textBookAdd.setCreateDate(new Date());
			textBookAdd.setIsDelete(false);
			textBookAdd.setModifyDate(new Date());
			textBookAdd.setIsHidden(false);
			textBookAdd.setResourceLibId(schoolId);
			ResTextbook textBook = null;
			try {
				textBook = this.resTextbookService.add(textBookAdd);//调用添加教材的方法
				if(textBook != null&&textBook.getId()>0){
					addCatalogWithResTextBook(textBookVoResourse, textBook);
				}else{
					textBookVoResourse.setDoneBefore(false);
					textBookVoResourse.setWrong(true);
					textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
					Resourse resourse = new Resourse();
					resourse.setMessage("教材添加失败");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);//添加教材失败
				}
			} catch (Exception e) {
				textBookVoResourse.setDoneBefore(false);
				textBookVoResourse.setWrong(true);
				textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
				Resourse resourse = new Resourse();
				resourse.setMessage("教材添加失败:"+e.getMessage());
				resourse.setName(textBookVoResourse.getName());
				resourse.setPath(textBookVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);//添加教材失败
				
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 根据查询条件查找教材列表
	 * @param textBookVoResourse
	 * @return
	 */
	private List<ResTextbook> getResTextBookByCondition(
			TextBookVoResourse textBookVoResourse) {
		ResTextbookCondition resTextbookCondition = new ResTextbookCondition();
		resTextbookCondition.setStageCode(textBookVoResourse.getStageCode());
		resTextbookCondition.setGradeCode(textBookVoResourse.getGradeCode());
		resTextbookCondition.setSubjectCode(textBookVoResourse.getSubjectCode());
		resTextbookCondition.setVersion(textBookVoResourse.getVersion());
		resTextbookCondition.setVolumn(textBookVoResourse.getVolumn());
		List<ResTextbook> resTextbookList = this.resTextbookService.findResTextbookByCondition(resTextbookCondition);
		return resTextbookList;
	}

	private void addCatalogWithResTextBook(TextBookVoResourse textBookVoResourse,
			ResTextbook textBook) {
		textBookVoResourse.setDoneBefore(false);
		textBookVoResourse.setWrong(false);
		textBookVoResourse.setId(textBook.getId());
		textBookVoResourse.setMessage(TextBookVoResourse.SUCCESS);
		Resourse resourse = new Resourse();
		resourse.setMessage("教材添加成功");
		resourse.setName(textBookVoResourse.getName());
		resourse.setPath(textBookVoResourse.getPath());
		addDetailMessage(nowDone,resourse);//添加教材成功
		
		ResTextbookCatalog catalog = new ResTextbookCatalog();
		catalog.setCreateDate(new Date());
		catalog.setIsDelete(false);
		catalog.setLevel(0);
		catalog.setModifyDate(new Date());
		catalog.setName(textBookVoResourse.getName());
		catalog.setListOrder(0);
		catalog.setPage(1);
		catalog.setParentId(0);
		catalog.setTestBookId(textBook.getId());
		
		ResTextbookCatalog  catalogAddResult  = this.resTextbookCatalogService.add(catalog);
		if(catalogAddResult != null&&catalogAddResult.getId()>0){
			Resourse resourse2 = new Resourse();
			resourse2.setMessage("根目录添加成功");
			resourse2.setName(textBookVoResourse.getName());
			resourse2.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowDone,resourse2);//添加过得目录内容
		}else{
			textBookVoResourse.setDoneBefore(false);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"，添加根目录失败");
			
			Resourse resourse3 = new Resourse();
			resourse3.setMessage("根目录添加失败");
			resourse3.setName(textBookVoResourse.getName());
			resourse3.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse3);//添加过得目录内容
		}
	}
	
	/**
	 * 处理文件夹内的教材目录内容
	 * @param voResourseList
	 * @return
	 */
	public List<TextBookVoResourse> dealWithResTextBookCatalog(List<TextBookVoResourse> voResourseList) {
		for (TextBookVoResourse textBookVoResourse : voResourseList) {//处理每一个教材目录信息
			Stack<ResTextbookCatalog> stack = new Stack<ResTextbookCatalog>();
			if(textBookVoResourse.isWrong()){
				continue;
			}else{
				System.out.println("====:"+textBookVoResourse.getName());
				List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
				ResTextbookCatalogCondition firstCatalogCondition = new ResTextbookCatalogCondition();
				  firstCatalogCondition.setTestBookId(textBookVoResourse.getId());
				  firstCatalogCondition.setParentId(0);
				  firstCatalogCondition.setLevel(0);
				  List<ResTextbookCatalog> firstCatalogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(firstCatalogCondition);
				  if(firstCatalogList !=null && firstCatalogList.size()==1){
					    stack.push(firstCatalogList.get(0));
				  }else{
					    textBookVoResourse.setDoneBefore(textBookVoResourse.isDoneBefore());
						textBookVoResourse.setWrong(true);
						textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"，查询根目录失败");
						
						Resourse resourse = new Resourse();
						resourse.setMessage("查询根目录失败");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(nowUndo,resourse);
						
				  }
				  
					for (int j = 0; j < voCatalogVoResourseList.size(); j++) {
						TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(j);
						String allName = textBookCatalogVoResourse.getName();
						String first = "";
						String name = "";
					 	Pattern p = null;  
				        Matcher m = null;  
				        
				        // 去掉<>标签及其之间的内容  
				        p = Pattern.compile("[0-9]{1,}[-]{3}");  
				        m = p.matcher(allName); 
			            if(m.find()){
			            	first = allName.substring(0, allName.indexOf("---"));
			            	textBookCatalogVoResourse.setListOrder(Integer.parseInt(first));
			            	name = allName.substring(allName.indexOf("---")+3, allName.length());
			            	textBookCatalogVoResourse.setName(name);
			            }else{
			            	Resourse resourse = new Resourse();
							resourse.setMessage("教材目录名称有问题");
							resourse.setName(textBookCatalogVoResourse.getName());
							resourse.setPath(textBookCatalogVoResourse.getPath());
							addDetailMessage(nowUndo,resourse);
							continue;
			            }
					    
					 if(textBookVoResourse.isDoneBefore()){
						
						  //根据教材的before属性判断是否进行 查询教材目录动作，
						  // 若是之前添加的 择执行查询动作，判断目录是否已经进行过相关的插入
						  
						  ResTextbookCatalogCondition catalogConditionTemp = new ResTextbookCatalogCondition();
						  catalogConditionTemp.setLevel(textBookCatalogVoResourse.getLevel());//level需要设置好
						  catalogConditionTemp.setTestBookId(textBookVoResourse.getId());
						  catalogConditionTemp.setName(textBookCatalogVoResourse.getName());
						  catalogConditionTemp.setIsDelete(false);
						  catalogConditionTemp.setParentId(this.getResParentId(stack,catalogConditionTemp.getLevel()));
						  
						  //其他相关查询属性
						  List<ResTextbookCatalog>  textbookCatalogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(catalogConditionTemp);
						  if(textbookCatalogList != null&&textbookCatalogList.size()==1){
							  ResTextbookCatalog textBookCatalogTemp = null;
							  textBookCatalogTemp = textbookCatalogList.get(0);
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textBookCatalogTemp.getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.BEFORESUCCESS);
							  
							  if(textBookCatalogTemp.getListOrder() == null||textBookCatalogTemp.getListOrder()<1){
								  textBookCatalogTemp.setListOrder(textBookCatalogVoResourse.getListOrder());
								  this.resTextbookCatalogService.modify(textBookCatalogTemp);
							  }
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("数据库已经存在对应教材目录");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(beforeDone,resourse);
							  
							  stack.push(textBookCatalogTemp);
							  
						  }else if(textbookCatalogList != null&&textbookCatalogList.size()>1){
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textbookCatalogList.get(0).getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+"数据库目录数据有问题");
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("数据库对应教材目录有问题");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(nowUndo,resourse);
							  
						  }else{
							  //添加教材目录
							  this.addResTextBookCatalog(stack, 
									  textBookVoResourse,
									  textBookCatalogVoResourse);
							  
						  }
					  
						
					}else{
						  
						  //添加教材目录
						  this.addResTextBookCatalog(
								  stack, 
								  textBookVoResourse,
								  textBookCatalogVoResourse);
					  }
					   
				}
				
			}
			
		}
		
		return voResourseList;
	}
	
	/**
	 * 根据队列确定目录的父目录是哪个
	 * @param stack
	 * @param level
	 * @return
	 */
	private Integer getResParentId(Stack<ResTextbookCatalog> stack,Integer level) {
		ResTextbookCatalog mid = stack.peek();
		BigDecimal catalogLevel = new BigDecimal(level);
		if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1){
			
		}else{
			while(!(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1)){//根据节点的层次寻找自己的父类
				if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==0){
					stack.pop();
					mid = stack.peek();
				}else if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==-1){
					stack.pop();
					mid = stack.peek();
				}
			}
		}
		if(stack.empty()){
			stack.push(mid);
		}
		Integer parentId = mid.getId();
		return parentId;
	}
	
	/**
	 * 添加教材目录
	 * @param stack
	 * @param textBookVoResourse
	 * @param textBookCatalogVoResourse
	 */
	private void addResTextBookCatalog(Stack<ResTextbookCatalog> stack,
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		ResTextbookCatalog textBookCatalogTemp;
		ResTextbookCatalog textBookCatalogAdd  = new ResTextbookCatalog();  // 根据资源目录设置要添加到数据库的字段属性值
		
		  textBookCatalogAdd.setLevel(textBookCatalogVoResourse.getLevel());//level需要设置好
		  textBookCatalogAdd.setTestBookId(textBookVoResourse.getId());
		  textBookCatalogAdd.setName(textBookCatalogVoResourse.getName());
		  textBookCatalogAdd.setListOrder(textBookCatalogVoResourse.getListOrder());
		  textBookCatalogAdd.setIsDelete(false);
		  textBookCatalogAdd.setCreateDate(new Date());
		  textBookCatalogAdd.setModifyDate(new Date());
		  textBookCatalogAdd.setParentId(this.getResParentId(stack,textBookCatalogAdd.getLevel()));
		  
		  try {
			
			textBookCatalogTemp = this.resTextbookCatalogService.add(textBookCatalogAdd);
			textBookCatalogTemp.setCode("custom_"+textBookCatalogTemp.getId());
			textBookCatalogTemp = this.resTextbookCatalogService.modify(textBookCatalogTemp);
			if(textBookCatalogTemp != null&&textBookCatalogTemp.getId()>1){
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(false);
				  textBookCatalogVoResourse.setId(textBookCatalogTemp.getId());
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.SUCCESS);
				  
				  	Resourse resourse = new Resourse();
					resourse.setMessage("教材目录添加成功");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowDone,resourse);
				  
				  stack.push(textBookCatalogTemp);
			  }else{
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(true);
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+",教材目录添加出错");
				  	Resourse resourse = new Resourse();
					resourse.setMessage("教材目录添加出错");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
			  }
		} catch (Exception e) {
			textBookCatalogVoResourse.setDoneBefore(false);
			textBookCatalogVoResourse.setWrong(true);
			textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+","+e.getMessage().substring(0,e.getMessage().length()>5?5:e.getMessage().length()));
			Resourse resourse = new Resourse();
			resourse.setMessage("教材目录添加出错,"+e.getMessage());
			resourse.setName(textBookCatalogVoResourse.getName());
			resourse.setPath(textBookCatalogVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}
	
	public void dealWithResTextBookCatalogResource(List<TextBookVoResourse> voResourseList, Integer schoolId) {
		for (int k = 0; k < voResourseList.size(); k++) {
			TextBookVoResourse textBookVoResourse = voResourseList.get(k);
			List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
			if(voCatalogVoResourseList != null&&voCatalogVoResourseList.size()>0){
				for (int l = 0; l < voCatalogVoResourseList.size(); l++) {
					TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(l);
					//拿到对应的目录信息,查看资源情况
					
			    	addResourseByResCatalog(
							textBookVoResourse,
							textBookCatalogVoResourse, schoolId);
				}
			}else{
				 textBookVoResourse.setMessage(TextBookVoResourse.CATALOGERROR);//不存在教材目录内容
				 textBookVoResourse.setWrong(true);
				 
				 	Resourse resourse = new Resourse();
					resourse.setMessage("不存在教材目录内容");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
				 
			}
		}
	}
	
	private void addResourseByResCatalog(
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse, Integer schoolId) {
		if(textBookCatalogVoResourse.isWrong()){
			
		}else{
			
			//之前有过添加，
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					boolean flag = true;
					
					try {
						flag = !this.doFilterResourse(resourseVo);
					} catch (Exception e1) {
						Resourse resourse = new Resourse();
						resourse.setMessage("资源有问题，请检查");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
						
					}
					
					if(flag){
						Resourse resourse = new Resourse();
						resourse.setMessage("资源后缀名有问题，请检查");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
					}
					
					File resoursefile = new File(resourseVo.getPath());
					String title = resoursefile.getName();//资源标题
					String png = "_thumbImg.png";
					if(title != null&&title.indexOf(png) >0){
						continue;
					}
					
					boolean isUpload = false;//判断资源是否添加过
		    		
					if(isUpload){
						resourseVo.setMessage(ResourseVo.BEFORESUCCESS);
						Resourse resourse = new Resourse();
						resourse.setMessage("数据库已经存在对应资源");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(beforeDone,resourse);
					}else{
						try {
							Resource resource = null;
							boolean ensureAdd = true;
							
							if(textBookCatalogVoResourse.isDoneBefore()){
								//添加查询模块
								ensureAdd = false;
		    					ImportCatalogVo cv = new ImportCatalogVo();
		    					cv.setCatalogCode("custom_"+textBookCatalogVoResourse.getId());
		    					cv.setGradeCode(textBookVoResourse.getGradeCode());
		    					cv.setGradeName(gradeNameMap.get(textBookVoResourse.getGradeCode()));
		    					cv.setStageCode(textBookVoResourse.getStageCode());
		    					cv.setStageName(stageNameMap.get(textBookVoResourse.getStageCode()));
		    					cv.setSubjectCode(textBookVoResourse.getSubjectCode());
		    					cv.setSubjectName(subjectNameMap.get(Integer.parseInt(textBookVoResourse.getSubjectCode())));
		    					cv.setVersionCode(textBookVoResourse.getVersion());
		    					cv.setVersionName(versionNameMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
		    					cv.setVolumnCode(textBookVoResourse.getVolumn());
		    					cv.setVolumnName(volumnNameMap.get(textBookVoResourse.getVolumn()));
		    					
		    					boolean cofirmExist = false;
		    					
		    					cofirmExist = importResourceServiceImpl.confirmExist(SysContants.SYSTEM_APP_ID, schoolId,resourseVo.getResType(), title, cv);
		    				
		    					if(cofirmExist){
		    						System.out.println("已经存在： "+resourseVo.getPath());
									resourseVo.setMessage("数据库已经存在对应资源");
									Resourse resourse = new Resourse();
									resourse.setMessage("数据库已经存在对应资源");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(beforeDone,resourse);
									
								}
		    					
		    					ensureAdd = !cofirmExist; 
								
							}else{
								 ensureAdd = true;
							}
							
							
							if(ensureAdd){//添加对应的资源内容
								/*File resoursefile = new File(resourseVo.getPath());
		    					String title = resoursefile.getName();//资源标题*/
		    				    String suffix=title.substring(title.lastIndexOf(".")+1);//文件后缀
		    				    InputStream input = new FileInputStream(resoursefile);//文件输入流
		    					ImportCatalogVo cv = new ImportCatalogVo();
		    					cv.setCatalogCode("custom_" + textBookCatalogVoResourse.getId());
		    					cv.setGradeCode(textBookVoResourse.getGradeCode());
		    					cv.setGradeName(gradeNameMap.get(textBookVoResourse.getGradeCode()));
		    					cv.setStageCode(textBookVoResourse.getStageCode());
		    					cv.setStageName(stageNameMap.get(textBookVoResourse.getStageCode()));
		    					cv.setSubjectCode(textBookVoResourse.getSubjectCode());
		    					cv.setSubjectName(subjectNameMap.get(Integer.parseInt(textBookVoResourse.getSubjectCode())));
		    					cv.setVersionCode(textBookVoResourse.getVersion());
		    					cv.setVersionName(versionNameMap.get(Integer.parseInt(textBookVoResourse.getVersion())));
		    					cv.setVolumnCode(textBookVoResourse.getVolumn());
		    					cv.setVolumnName(volumnNameMap.get(textBookVoResourse.getVolumn()));
		    					
		    					//调用资源添加接口
		    					String resourseThumbName = resoursefile.getAbsolutePath().replaceFirst(resoursefile.getName(), resoursefile.getName().substring(0,resoursefile.getName().lastIndexOf(".")))+"_thumbImg.png";
		    					File resourseThumbfile = new File(resourseThumbName);
		    					InputStream inputThumb = null;
		    					if(!resourseThumbfile.exists()){
		    						inputThumb = null; //获取默认的图片流
		    					}else{
		    						inputThumb =  new FileInputStream(resourseThumbfile);//文件输入流
		    					}
		    				  
		    					ResourceLibraryCondition  resourceLibraryCondition = new ResourceLibraryCondition();
		    					resourceLibraryCondition.setOwerId(schoolId);
		    					List<ResourceLibrary> list = resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		    					
		    					if(list.size()>0) {
		    						cv.setLibraryId(list.get(0).getUuid());
		    					}
		    					
		    					System.out.println("开始导入： "+resourseVo.getPath());
		    				    resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("资源添加成功");
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加成功");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
									System.out.println("导入失败： "+resourseVo.getPath());
									resourseVo.setMessage("资源添加失败");
									
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加失败");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowUndo,resourse);
									
									
								}
							}
						} catch (Exception e) {
							resourseVo.setMessage(ResourseVo.ERROR+"添加资源报未知问题");
							Resourse resourse = new Resourse();
							resourse.setMessage("添加资源报未知问题");
							resourse.setName(resourseVo.getName());
							resourse.setPath(resourseVo.getPath());
							addDetailMessage(nowUndo,resourse);
							e.printStackTrace();
							continue;
						}
					}
				}
			}else{
				/*textBookCatalogVoResourse.setMessage("目录中不存在相关资源内容");
				Resourse resourse = new Resourse();
				resourse.setMessage("目录中不存在相关资源内容");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);*/
			}
			
		}
	}

	/**
	 * @function 创建树和节点
	 * @param path
	 * @return
	 * @throws Exception
     */
	public String createTreeAndNode(String path) throws Exception {
		// String filePath = "/Users/panfei/test/初中#语文#语文知识点管理";
		if(path == null || "".equals(path)){
			System.out.println("文件路径为空=====创建失败======");
			return "error";
		}
		final File file = new File(path);
		//创建顶层知识点管理
		Integer userId = 1;
		String userName = "admin";
		Integer schoolId = 0;
		createNode(file, 1, 0, "", 0, true, userId,userName,schoolId);
		return "success";
	}

	private void createNode(File f, int level,int parentNodeId,String subjectCode,int treeId,Boolean isTop, Integer userId,String userName,Integer schoolId) throws Exception{
		KnowledgeNode knowledgeNode = null;
		KnowledgeTree knowledgeTree = null;
		if(isTop){
			knowledgeTree = createTree(f.getName());
		}
		if("".equals(subjectCode)){
			subjectCode = knowledgeTree.getSubjectCode();
			treeId = knowledgeTree.getId();
		}
		if(!isTop) {
			//根据系统编码转换文件名到对应的编码
			String fileCode=(String)System.getProperties().get("file.encoding");
			String fileName = f.getName();
			fileName = new String (fileName.getBytes(fileCode),fileCode);
			Integer wjType = getType(fileName);

			//判断文件类型，如果是资源文件夹，不创建节点
			if(wjType == 0){
				KnowledgeNodeCondition knowledgeNodeCondition = new KnowledgeNodeCondition();
				knowledgeNodeCondition.setTreeId(treeId);
				knowledgeNodeCondition.setSubjectCode(subjectCode);
				knowledgeNodeCondition.setParentId(parentNodeId);
				knowledgeNodeCondition.setName(f.getName());
				List<KnowledgeNode> list = knowledgeNodeService.findKnowledgeNodeByCondition(knowledgeNodeCondition);
				if(list != null && list.size() > 0){
					knowledgeNode = list.get(0);
				}else {
					knowledgeNode = new KnowledgeNode();
					knowledgeNode.setListOrder(1);
					knowledgeNode.setModifyDate(new Date());
					knowledgeNode.setIsDeleted(false);
					knowledgeNode.setCreateDate(new Date());
					knowledgeNode.setLevel(level);
					knowledgeNode.setName(f.getName());
					knowledgeNode.setParentId(parentNodeId);
					knowledgeNode.setSubjectCode(subjectCode);
					knowledgeNode.setTreeId(treeId);
					knowledgeNode = knowledgeNodeService.add(knowledgeNode);
					System.out.println("正在添加节点："+knowledgeNode.getName());
				}
			}

			if(knowledgeNode == null){
				knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(parentNodeId);
			}

		}

		isTop = false;
		File[] childs = f.listFiles();
		ResKnowledgeResource resKnowledgeResource = null;
		KnowledgeTree kt = knowledgeTreeService.findKnowledgeTreeById(treeId);
		ResKnowledgeResourceCondition resKnowledgeResourceCondition = null;

		for(int i=0; i<childs.length; i++) {
			if(childs[i].isDirectory()) {
				createNode(childs[i], level + 1,knowledgeNode != null ? knowledgeNode.getId() : 0,subjectCode,treeId,isTop,userId,userName,schoolId);
			}else if(!childs[i].isDirectory() && !childs[i].isHidden()){
				System.out.println("=====文件："+childs[i].getName()+" 保存=====");
				FileResult fileResult = this.fileService.upload(childs[i],"","6");

				Integer type = getType(childs[i].getParent());
				if(fileResult != null && fileResult.getEntityFile() != null){
					Resource resource = createResourceOfType(type, SysContants.SYSTEM_APP_ID,fileResult.getEntityFile().getUuid(),childs[i].getName(),userId,userName,schoolId,fileResult.getEntityFile().getExtension(),0);
					System.out.println("资源："+resource.getUuid()+" 上传完成");
					if(resource != null && resource.getObjectId() != null){
						//关联知识点和资源文件
						if(kt != null){
							resKnowledgeResourceCondition = new ResKnowledgeResourceCondition();
							resKnowledgeResourceCondition.setKnowledgeNodeId(knowledgeNode.getId());
							resKnowledgeResourceCondition.setOwnerType(type);
							resKnowledgeResourceCondition.setStageCode(kt.getStageCode());
							resKnowledgeResourceCondition.setSubjectCode(kt.getSubjectCode());
							resKnowledgeResourceCondition.setOwnerUuid(resource.getUuid());
							List<ResKnowledgeResource> resKnowledgeResourcesList = resKnowledgeResourceService.findResKnowledgeResourceByCondition(resKnowledgeResourceCondition);

							if(resKnowledgeResourcesList == null || resKnowledgeResourcesList.size() == 0){
								resKnowledgeResource = new ResKnowledgeResource();
								resKnowledgeResource.setStageCode(kt.getStageCode());
								resKnowledgeResource.setSubjectCode(kt.getSubjectCode());
								resKnowledgeResource.setCreateDate(new Date());
								resKnowledgeResource.setIsDeleted(false);
								resKnowledgeResource.setKnowledgeName(knowledgeNode.getName());
								resKnowledgeResource.setKnowledgeNodeId(knowledgeNode.getId());
								resKnowledgeResource.setModifyDate(new Date());
								resKnowledgeResource.setOwnerType(type);
								resKnowledgeResource.setOwnerUuid(resource.getObjectId());
								resKnowledgeResourceService.add(resKnowledgeResource);
								System.out.println("正在关联节点文件：上传的文件路径："+fileResult.getHttpUrl());
								System.out.println("上传文件的UUID："+fileResult.getEntityFile().getUuid()+"知识点以关联");
							}
						}
					}
				}
			}
		}
	}

	public String getStageCode(String name){
		String stageCode = "";
		if ("小学".equals(name)) {
			stageCode = "2";
		} else if ("初中".equals(name)) {
			stageCode = "3";
		} else if ("高中".equals(name)) {
			stageCode = "4";
		} else {
			System.out.println("==文件压缩包命名格式错误，倒入失败==格式为 学段#科目#管理名称==该名称不存在学段");
			return null;
		}
		return stageCode;
	}

	public KnowledgeTree createTree(String name) {
		KnowledgeTree knowledgeTree = null;
		String[] names = name.split("#");
		if (names.length != 3) {
			System.out.println("==文件压缩包命名格式错误，倒入失败==格式为 学段#科目#管理名称==");
			return null;
		}

		String stageCode = getStageCode(names[0]);
		Integer subjectCode = null;

		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setStageCode(stageCode);
		subjectCondition.setName(names[1]);
		List<Subject> subjectsList = jcSubjectService.findSubjectByCondition(subjectCondition,null,null);
		if (subjectsList != null && subjectsList.size() > 0) {
			for(Subject subject : subjectsList){
				if(subject != null && names[1].equals(subject.getName())){
					subjectCode = subject.getCode();
					break;
				}
				if(subjectCode == null){
					return null;
				}
			}
		} else {
			System.out.println("==文件压缩包命名格式错误，倒入失败==格式为 学段#科目#管理名称==该名称不存在科目，或者科目数据存在多条");
			return null;
		}

		KnowledgeTreeCondition knowledgeTreeCondition = new KnowledgeTreeCondition();
		knowledgeTreeCondition.setSubjectCode(subjectCode+"");
		knowledgeTreeCondition.setStageCode(stageCode);
		List<KnowledgeTree> list = knowledgeTreeService.findKnowledgeTreeByCondition(knowledgeTreeCondition);
		if(list != null && list.size() > 0){
			return list.get(0);
		}

		knowledgeTree = new KnowledgeTree();
		knowledgeTree.setIsDeleted(false);
		knowledgeTree.setName(names[2]);
		knowledgeTree.setStageCode(stageCode);
		knowledgeTree.setSubjectCode(subjectCode + "");
		knowledgeTree.setCreateDate(new Date());
		knowledgeTree.setDisable(true);
		knowledgeTree.setModifyDate(new Date());
		return knowledgeTreeService.add(knowledgeTree);
	}

	public Integer getType(String name){
		Integer type = 0;
		name = name.substring(name.lastIndexOf("/")+1);
		if(name != null && !"".equals(name)){
			if ("微课".equals(name)){
				type = ResourceType.MICRO;
			} else if ("课件".equals(name)) {
				type = ResourceType.LEARNING_DESIGN;
			}else if ("作业".equals(name)) {
				type = ResourceType.HOMEWORK;
			}else if ("试卷".equals(name)) {
				type = ResourceType.EXAM;
			}else if ("教案".equals(name)) {
				type = ResourceType.TEACHING_PLAN;
			}else if ("素材".equals(name)) {
				type = ResourceType.MATERIAL;
			}else if ("导学案".equals(name)) {
				type = ResourceType.LEARNING_PLAN;
			}else if ("试卷（真题）".equals(name)) {
				type = 8;
			}else if ("试题".equals(name)) {
				type = 9;
			}else if("其他".equals(name)){
				type = ResourceType.OTHER;
			}
		}
		return type;
	}

	/**
	 * @function 创建资源文件
	 * @return
	 */
	public Resource createResourceOfType(Integer typeint,Integer relateAppId,String entityId,String title,Integer userId,String userName,Integer schoolId,String extension,Integer type) {

		String objId="";
		Resource r=new Resource();

		if (ResourceType.EXAM == typeint) {

			Exam em = this.examService.saveOrUpdateExam(relateAppId, null, entityId, ExamType.COMMON_EXAM, title, userId, null);
			if(em != null){
				objId=em.getUuid();
			}

		} else if (ResourceType.HOMEWORK == typeint) {
			Homework em = this.homeworkService.saveOrUpdateHomework(relateAppId, null, entityId, HomeworkType.COMMON_HOMEWORK, title, userId, null);
			if(em != null){
				objId=em.getUuid();
			}

		} else if (ResourceType.LEARNING_DESIGN == typeint) {
			LearningDesign em = this.learningDesignService.saveOrUpdateLearningDesign(relateAppId, null, entityId, LearningDesignType.COMMON_LEARNINGDESIGN, title, userId, null);
			if(em != null){
				objId=em.getUuid();
			}

		} else if (ResourceType.MATERIAL == typeint) {
			Material em = this.materialService.saveOrUpdateMaterial(relateAppId, null, entityId, MaterialType.COMMON_MATERIAL, title, userId, null);
			if(em != null){
				objId=em.getUuid();
			}

		} else if (ResourceType.TEACHING_PLAN == typeint) {
			TeachingPlan em = this.teachingPlanService.saveOrUpdateTeachingPlan(relateAppId, null, entityId, TeachingPlanType.COMMON_TEACHINGPLAN, title, userId, null);
			if(em != null){
				objId=em.getUuid();
			}

		}
		else if(ResourceType.MICRO ==typeint){//微课上传
			MicroLesson em = this.microLessonService.saveOrUpdateMicroLesson(relateAppId, null, entityId, MicroType.COMMON_MICRO, title, userId, null, null, null,null);
			if(em != null){
				objId=em.getUuid();
			}
		}
		else if(ResourceType.LEARNING_PLAN ==typeint){//导学案上传
			LearningPlan em = this.learningPlanService.saveOrUpdateLearningPlan(relateAppId, null, entityId, LearningPlanType.COMMON_LEARNINGPLAN, title, userId, "0");
			if(em != null){
				objId=em.getUuid();
			}
		} else if (ResourceType.OTHER == typeint) {
			objId = entityId;
		}

		if(schoolId == null){
			schoolId = 0;
		}
		if (type==0){
			schoolId=0;
		}
		if(!objId.equals("")){
			ResourceLibraryCondition resourceLibraryCondition=new ResourceLibraryCondition();

			resourceLibraryCondition.setOwerId(schoolId);
			List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
					.findResourceLibraryByCondition(resourceLibraryCondition);
			ResourceLibrary resourceLibrary = new ResourceLibrary();
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// 如果不存在添加对应的记录
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(SysContants.SYSTEM_APP_ID);
				resourceLibraryAdd.setOwerId(schoolId);
				resourceLibraryAdd.setName("");
				resourceLibraryAdd.setUuid(platform.education.resource.utils.UUIDUtil.getUUID());// 获取唯一值uuid
				resourceLibrary = this.resourceLibraryService
						.add(resourceLibraryAdd);

			}


			r.setCreateDate(new Date());
			r.setModifyDate(new Date());
			r.setTitle(title);
			r.setIsDeleted(false);
			r.setVerify("0");
			r.setUserId(userId);
			r.setUserName(userName);
			r.setResType(typeint);
			r.setIsPersonal(false);
			r.setIconType(IconUtil.setIcon(extension));
			r.setUuid(UUIDUtil.getUUID());
			r.setObjectId(objId);
			r.setLibraryId(resourceLibrary.getUuid());
			this.resourceService.add(r);
		}
		return r;

	}
	
}
