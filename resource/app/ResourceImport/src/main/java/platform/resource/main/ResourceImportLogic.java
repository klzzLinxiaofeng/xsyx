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
    	resourseFileMap.put("??????", 5);
    	resourseFileMap.put("??????", 2);
    	resourseFileMap.put("??????", 4);
    	resourseFileMap.put("??????", 6);
    	resourseFileMap.put("??????", 1);
    	resourseFileMap.put("??????", 3);
    	resourseFileMap.put("??????", 3);
    	resourseFileMap.put("?????????", 8);
    	resourseFileMap.put("??????", 7);
    	
    }
	private ApplicationContext applicationContext;

	public ResourceImportLogic() {
		
	}
    /**
     * ???????????????
     * @param applicationContext
     */
	public ResourceImportLogic(ApplicationContext applicationContext) {
		
		this.applicationContext = applicationContext;
		this.importResourceServiceImpl = (ImportResourceService)applicationContext.getBean("importResourceService");//????????????
		this.jcTextbookCatalogService = (TextbookCatalogService)applicationContext.getBean("jcTextbookCatalogService");//????????????
		this.jcGradeService = (platform.education.generalcode.service.GradeService)applicationContext.getBean("jcGradeService");//??????????????????
		this.jcTextbookPublisherService = (TextbookPublisherService)applicationContext.getBean("jcTextbookPublisherService");//???????????????
		this.jcStageService = (StageService)applicationContext.getBean("jcStageService");// ??????
		this.jcSubjectService = (platform.education.generalcode.service.SubjectService)applicationContext.getBean("jcSubjectService");//????????????
		this.jcTextbookService = (TextbookService)applicationContext.getBean("jcTextbookService");//??????
		this.resTextbookService = (ResTextbookService)applicationContext.getBean("resTextbookService");
		this.resourceLibraryService = (ResourceLibraryService)applicationContext.getBean("resourceLibraryService");
		this.resTextbookCatalogService = (ResTextbookCatalogService)applicationContext.getBean("resTextbookCatalogService");
		this.jcTextbookVersionService = (TextbookVersionService)applicationContext.getBean("jcTextbookVersionService");//??????
		this.jcTextbookVolumnService = (TextbookVolumnService)applicationContext.getBean("jcTextbookVolumnService");//??????

		/**
		 * ?????????????????????
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
		System.out.println("----------------------??????????????????---begin---------------");
		System.out.println("----------------------?????????????????????----------------------");
		this.entrance(path);
		System.out.println("----------------------?????????????????????????????????????????????-- end--------------");
		
		System.out.println("----------------------?????????????????????????????????????????????---begin---------------");
		System.out.println("----------------------?????????????????????????????????????????????-- end--------------");
		System.out.println("----------------------??????txt??????---begin---------------");
		this.outBeforeListToTxt(beforeDone.getResourseList());
		this.outDoneListToTxt(nowDone.getResourseList());
		this.outUndoListToTxt(nowUndo.getResourseList());
		System.out.println("----------------------??????txt??????-- end--------------");
		Long endTime= System.currentTimeMillis();
		
		Date startDate = new Date(startTime);
		
		Date endDate = new Date(endTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = sdf.format(startDate);
		String date2 = sdf.format(endDate);
		System.out.println("???????????????"+date1);
		System.out.println("???????????????"+date2);
	}
	/**
	 * ??????????????? ???????????????????????? ?????????????????????????????????????????????????????????????????????????????????????????????????????? 123????????????????????????????????????????????????????????????????????????????????????
	 * ?????????????????????????????????
	 * ??????????????????????????????????????????????????????????????????????????????????????? ????????? ?????????????????????????????????????????????????????????
	 * 
	 */
	//????????? ??????\??????\??????\??????\??????
	  //????????????????????????????????????????????????????????????
	public void entrance(String path) {
		  this.init();//?????????
		  OpenResourse openResourse = verifyDirectoryAndFiles(path);//??????????????????
		  
		List<TextBookVoResourse> voResourseList = openResourse.getVoResourseList();
		
		if(voResourseList != null&&voResourseList.size()>0 ){//????????????????????????????????????????????????????????????????????????????????????????????????
			voResourseList = dealWithTextBook(voResourseList);//??????????????????
			voResourseList = dealWithTextBookCatalog(voResourseList);//????????????????????????
			dealWithTextBookCatalogResource(voResourseList);//???????????????????????????
		}else{
			
			//nowUndoResourse.setMessage(OpenResourse.ERROR);
		}
		
		
		System.out.println("----------------------?????????????????????????????????????????????---begin---------------");
		System.out.println("----------------------?????????????????????????????????????????????-- end--------------");
		
	}
	public OpenResourse verifyDirectoryAndFiles(String path) {
		OpenResourse openResourse = new OpenResourse();
		  File file = new File(path);
		  File[] stageList = file.listFiles();//???????????????
		  voResourseList.clear();
		  for (int i = 0; i < stageList.length; i++) {
			File stageFile = stageList[i];
			 Map<String, Object> textBookMap = new HashMap<String, Object>();
			 iteraterStage(stageFile,textBookMap);//????????????????????????
			}
		  openResourse.setVoResourseList(voResourseList);
		return openResourse;
	}
	/**
	 * ???????????????????????????
	 * @param voResourseList
	 */
	public void dealWithTextBookCatalogResource(List<TextBookVoResourse> voResourseList) {
		for (int k = 0; k < voResourseList.size(); k++) {
			TextBookVoResourse textBookVoResourse = voResourseList.get(k);
			List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
			if(voCatalogVoResourseList != null&&voCatalogVoResourseList.size()>0){
				for (int l = 0; l < voCatalogVoResourseList.size(); l++) {
					TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(l);
					//???????????????????????????,??????????????????
					
			    	addResourseByCatalog(
							textBookVoResourse,
							textBookCatalogVoResourse);
				}
			}else{
				 textBookVoResourse.setMessage(TextBookVoResourse.CATALOGERROR);//???????????????????????????
				 textBookVoResourse.setWrong(true);
				 
				 	Resourse resourse = new Resourse();
					resourse.setMessage("???????????????????????????");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
				 
			}
		}
	}
	/**
	 * ???????????????????????????????????????
	 * @param voResourseList
	 * @return
	 */
	public List<TextBookVoResourse> dealWithTextBookCatalog(List<TextBookVoResourse> voResourseList) {
		for (TextBookVoResourse textBookVoResourse : voResourseList) {//?????????????????????????????????
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
						textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"????????????????????????");
						
						Resourse resourse = new Resourse();
						resourse.setMessage("?????????????????????");
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
				        
				        // ??????<>???????????????????????????  
				        p = Pattern.compile("[0-9]{1,}[-]{3}");  
				        m = p.matcher(allName); 
			            if(m.find()){
			            	first = allName.substring(0, allName.indexOf("---"));
			            	textBookCatalogVoResourse.setListOrder(Integer.parseInt(first));
			            	name = allName.substring(allName.indexOf("---")+3, allName.length());
			            	textBookCatalogVoResourse.setName(name);
			            }else{
			            	Resourse resourse = new Resourse();
							resourse.setMessage("???????????????????????????");
							resourse.setName(textBookCatalogVoResourse.getName());
							resourse.setPath(textBookCatalogVoResourse.getPath());
							addDetailMessage(nowUndo,resourse);
							continue;
			            }
					    
					 if(textBookVoResourse.isDoneBefore()){
						
						  //???????????????before???????????????????????? ???????????????????????????
						  // ????????????????????? ????????????????????????????????????????????????????????????????????????
						  
						  TextbookCatalogCondition catalogConditionTemp = new TextbookCatalogCondition();
						  catalogConditionTemp.setLevel(textBookCatalogVoResourse.getLevel());//level???????????????
						  catalogConditionTemp.setTestBookId(textBookVoResourse.getId());
						  catalogConditionTemp.setName(textBookCatalogVoResourse.getName());
						  catalogConditionTemp.setIsDelete(false);
						  catalogConditionTemp.setParentId(this.getParentId(stack,catalogConditionTemp.getLevel()));
						  
						  //????????????????????????
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
								resourse.setMessage("???????????????????????????????????????");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(beforeDone,resourse);
							  
							  stack.push(textBookCatalogTemp);
							  
						  }else if(textbookCatalogList != null&&textbookCatalogList.size()>1){
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textbookCatalogList.get(0).getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+"??????????????????????????????");
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("????????????????????????????????????");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(nowUndo,resourse);
							  
						  }else{
							  //??????????????????
							  this.addTextBookCatalog(stack, 
									  textBookVoResourse,
									  textBookCatalogVoResourse);
							  
						  }
					  
						
					}else{
						  
						  //??????????????????
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
	 * ?????????????????????????????????
	 * @param voResourseList
	 */
	public List<TextBookVoResourse> dealWithTextBook(List<TextBookVoResourse> voResourseList) {
		
		for (int i = 0; i < voResourseList.size(); i++) {
			    TextBookVoResourse textBookVoResourse = voResourseList.get(i);
			    System.out.println(textBookVoResourse.getName());
				this.iteraterTextBook(textBookVoResourse);//???????????????????????????
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
			
			//?????????????????????
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					boolean flag = true;
					
					try {
						flag = !this.doFilterResourse(resourseVo);
					} catch (Exception e1) {
						Resourse resourse = new Resourse();
						resourse.setMessage("???????????????????????????");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
						
					}
					
					if(flag){
						Resourse resourse = new Resourse();
						resourse.setMessage("????????????????????????????????????");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
					}
					
					
					
					
					File resoursefile = new File(resourseVo.getPath());
					String title = resoursefile.getName();//????????????
					String png = "_thumbImg.png";
					if(title != null&&title.indexOf(png) >0){
						continue;
					}
					boolean isUpload = false;//???????????????????????????
		    		
					if(isUpload){
						resourseVo.setMessage(ResourseVo.BEFORESUCCESS);
						Resourse resourse = new Resourse();
						resourse.setMessage("?????????????????????????????????");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(beforeDone,resourse);
						  
					}else{
						
						
						try {
							Resource resource = null;
							boolean ensureAdd = true;
							
							if(textBookCatalogVoResourse.isDoneBefore()){
								//??????????????????
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
		    						System.out.println("??????????????? "+resourseVo.getPath());
									resourseVo.setMessage("?????????????????????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("?????????????????????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(beforeDone,resourse);
									
								}
		    					
		    					ensureAdd = !cofirmExist; 
								
							}else{
								 ensureAdd = true;
							}
							
							
							if(ensureAdd){//???????????????????????????
								/*File resoursefile = new File(resourseVo.getPath());
		    					String title = resoursefile.getName();//????????????*/
		    				    String suffix=title.substring(title.lastIndexOf(".")+1);//????????????
		    				    InputStream input = new FileInputStream(resoursefile);//???????????????
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
		    					
		    					//????????????????????????
		    					String resourseThumbName = resoursefile.getAbsolutePath().replaceFirst(resoursefile.getName(), resoursefile.getName().substring(0,resoursefile.getName().lastIndexOf(".")))+"_thumbImg.png";
		    					File resourseThumbfile = new File(resourseThumbName);
		    					InputStream inputThumb = null;
		    					if(!resourseThumbfile.exists()){
		    						inputThumb = null; //????????????????????????
		    					}else{
		    						inputThumb =  new FileInputStream(resourseThumbfile);//???????????????
		    					}
		    				    cv.setLibraryId(ResourceCondition.DEFAULT_LIBRARY_ID);
		    					System.out.println("??????????????? "+resourseVo.getPath());
		    				    resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("??????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
									System.out.println("??????????????? "+resourseVo.getPath());
									resourseVo.setMessage("??????????????????");
									
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowUndo,resourse);
									
									
								}
							}
							
							
						} catch (Exception e) {
							
							resourseVo.setMessage(ResourseVo.ERROR+"???????????????????????????");
							Resourse resourse = new Resourse();
							resourse.setMessage("???????????????????????????");
							resourse.setName(resourseVo.getName());
							resourse.setPath(resourseVo.getPath());
							addDetailMessage(nowUndo,resourse);
							e.printStackTrace();
							continue;
							
						}
					}
				}
			}else{
				/*textBookCatalogVoResourse.setMessage("????????????????????????????????????");
				Resourse resourse = new Resourse();
				resourse.setMessage("????????????????????????????????????");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);*/
			}
			
		}
	}
	/**
	 * ?????????
	 */
	public void init() {
		  this.initVersionMap();//???????????????
		  this.initGradeMap();//???????????????
		  this.initStageMap();//???????????????
		  this.initSubjectMap();//???????????????
		  this.initVolumnMap();//???????????????
		  this.initArray();//???????????????????????????
	}
	/**
	 * ???????????????
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
		
		/*gradeMap.put("??????", "");
		gradeNameMap.put("", "??????");*/
	}
	/**
	 * ???????????????
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
	 * ???????????????
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
	 * ???????????????
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
	 * ???????????????
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
	 * ?????????????????? 
	 * ??????????????????????????????????????????
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
				iteratorSubject( subjectFile,textBookMap);//??????????????????
			}
			
			
		}else{

			String errorMessage = "?????????????????????";
			
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(stageFile.getName());
			resourse.setPath(stageFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(stageFile, errorResourse, errorMessage);
		}
		
	}
	/**
	 * ??????????????????
	 * ??????????????????????????????????????????
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
				iteratorVersion(versionFile,textBookMap);//??????????????????
				
				
			}
			
		}else{
			String errorMessage = "?????????????????????";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(subjectFile.getName());
			resourse.setPath(subjectFile.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}
	/**
	 * ??????????????????
	 * ??????????????????????????????????????????
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
				iteratorGrade( gradeFile,textBookMap);//??????????????????
				
				
			}
			
		}else{
			String errorMessage = "?????????????????????";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(versionFile.getName());
			resourse.setPath(versionFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(versionFile, errorResourse, errorMessage);
		}
	}
	
	/**
	 * ??????????????????
	 * ??????????????????????????????????????????
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
		
		
		//????????????????????????????????????
		if("??????".equals(stageNameMap.get(textBookMap.get("stage")))){
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
			String errorMessage = "?????????????????????";
			Resourse resourse = new Resourse();
			resourse.setMessage(errorMessage);
			resourse.setName(gradeFile.getName());
			resourse.setPath(gradeFile.getPath());
			addDetailMessage(nowUndo,resourse);
			//addResourseForFile(gradeFile, errorResourse, errorMessage);
		}
	}
	/**
	 * ??????????????????
	 * ??????????????????????????????????????????
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
		
		//????????????????????????????????????
		if("??????".equals(stageNameMap.get(textBookMap.get("stage")))){
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
		
		if(volumnOk){//??????????????????
			
			TextBookVoResourse textBookVoResourse = new TextBookVoResourse();
			textBookVoResourse.setStageCode((String)textBookMap.get("stage"));
			textBookVoResourse.setSubjectCode((Integer)textBookMap.get("subject")+"");
			textBookVoResourse.setVersion((Integer)textBookMap.get("version")+"");
			textBookVoResourse.setGradeCode((String)textBookMap.get("grade"));
			textBookVoResourse.setVolumn((String)textBookMap.get("volumn"));
			String gradeName = gradeNameMap.get((String)textBookMap.get("grade"));
			String subjectName = subjectNameMap.get((Integer)textBookMap.get("subject"));
			String volumnName = volumnNameMap.get((String)textBookMap.get("volumn"));
			
			//????????????????????????????????????
			String textBookName = "";
			if("??????".equals(stageNameMap.get(textBookMap.get("stage")))){
				textBookName = "??????"+subjectName;
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
				//iteratorCatalog(catalogStack,file,level);//????????????????????????
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
							//???????????????????????????
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
			
			//??????????????????
			List<TextBookCatalogVoResourse>  catalogListResult = new ArrayList<TextBookCatalogVoResourse>();
			for (int i = catalogList.size()-1; i >= 0; i--) {
				catalogListResult.add(catalogList.get(i));
			}
			textBookVoResourse.setVoCatalogVoResourseList(catalogListResult);
			System.out.println(textBookVoResourse.getName());
			voResourseList.add(textBookVoResourse);
			
		}else{
			String errorMessage = "?????????????????????";
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
	 * ??????????????????
	 * @param stack
	 * @param textBookVoResourse
	 * @param textBookCatalogVoResourse
	 */
	private void addTextBookCatalog(Stack<TextbookCatalog> stack,
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		TextbookCatalog textBookCatalogTemp;
		TextbookCatalog textBookCatalogAdd  = new TextbookCatalog();  // ???????????????????????????????????????????????????????????????
		
		  textBookCatalogAdd.setLevel(textBookCatalogVoResourse.getLevel());//level???????????????
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
					resourse.setMessage("????????????????????????");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowDone,resourse);
				  
				  stack.push(textBookCatalogTemp);
			  }else{
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(true);
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+",????????????????????????");
				  	Resourse resourse = new Resourse();
					resourse.setMessage("????????????????????????");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
			  }
		} catch (Exception e) {
			textBookCatalogVoResourse.setDoneBefore(false);
			textBookCatalogVoResourse.setWrong(true);
			textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+","+e.getMessage().substring(0,e.getMessage().length()>5?5:e.getMessage().length()));
			Resourse resourse = new Resourse();
			resourse.setMessage("????????????????????????,"+e.getMessage());
			resourse.setName(textBookCatalogVoResourse.getName());
			resourse.setPath(textBookCatalogVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);
		}
	}

	
	/**
	 * ?????????????????????????????????????????????
	 * @param stack
	 * @param level
	 * @return
	 */
	private Integer getParentId(Stack<TextbookCatalog> stack,Integer level) {
		TextbookCatalog mid = stack.peek();
		BigDecimal catalogLevel = new BigDecimal(level);
		if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1){
			
		}else{
			while(!(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1)){//??????????????????????????????????????????
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
	 * ????????????
	 * @param textBookVoResourse
	 */
	private void iteraterTextBook(TextBookVoResourse textBookVoResourse) {
		List<Textbook> textbookList = getTextBookByCondition(textBookVoResourse); //??????????????????????????????????????????????????????????????????????????????
		
		if(textbookList != null&&textbookList.size()==1){
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(false);
			textBookVoResourse.setId(textbookList.get(0).getId());
			textBookVoResourse.setMessage(TextBookVoResourse.BEFORESUCCESS);
			
			Resourse resourse = new Resourse();
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			resourse.setMessage("?????????????????????????????????");
			addDetailMessage(beforeDone,resourse);//??????????????????????????????
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
			
		}else if(textbookList.size()>1){//?????????????????????????????????????????????????????????????????????
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR);//??????????????????????????????????????????????????????
			Resourse resourse = new Resourse();
			resourse.setMessage("???????????????????????????");
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);//??????????????????????????????
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
				textBook = this.jcTextbookService.add(textBookAdd);//???????????????????????????
				if(textBook != null&&textBook.getId()>0){
					addCatalogWithTextBook(textBookVoResourse, textBook);
				}else{
					textBookVoResourse.setDoneBefore(false);
					textBookVoResourse.setWrong(true);
					textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
					Resourse resourse = new Resourse();
					resourse.setMessage("??????????????????");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);//??????????????????
				}
			} catch (Exception e) {
				textBookVoResourse.setDoneBefore(false);
				textBookVoResourse.setWrong(true);
				textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
				Resourse resourse = new Resourse();
				resourse.setMessage("??????????????????:"+e.getMessage());
				resourse.setName(textBookVoResourse.getName());
				resourse.setPath(textBookVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);//??????????????????
				
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
		resourse.setMessage("??????????????????");
		resourse.setName(textBookVoResourse.getName());
		resourse.setPath(textBookVoResourse.getPath());
		addDetailMessage(nowDone,resourse);//??????????????????
		
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
			resourse2.setMessage("?????????????????????");
			resourse2.setName(textBookVoResourse.getName());
			resourse2.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowDone,resourse2);//????????????????????????
		}else{
			textBookVoResourse.setDoneBefore(false);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"????????????????????????");
			
			Resourse resourse3 = new Resourse();
			resourse3.setMessage("?????????????????????");
			resourse3.setName(textBookVoResourse.getName());
			resourse3.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse3);//????????????????????????
		}
	}
	/**
	 * ????????????
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
	 * ????????????????????????????????????
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
	 * ???????????????????????????
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
					sbf.append("["+count+"] "+resourse.getPath()+"???"+resourse.getMessage()).append(resouceProper.getLineMark());
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
					sbf.append("["+count+"] "+resourse.getPath()+"???"+resourse.getMessage()).append(resouceProper.getLineMark());
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
					sbf.append("["+count+"] "+resourse.getPath()+"???"+resourse.getMessage()).append(resouceProper.getLineMark());
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
					sbf.append("["+count+"] "+resourse.getPath()+"???"+resourse.getMessage()).append(resouceProper.getLineMark());
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
					sb.append("["+count+"] "+resourse.getPath()+"???"+resourse.getMessage()).append(resouceProper.getLineMark());
				}
		 }else{
			 sb.append("????????????????????????");
		 }
		return sb.toString();
	}
	
	
	private boolean arrayContainsStr(String[] array,String name){
		List<String> tempList = Arrays.asList(array);//??????list??????????????????????????????
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
	 * ???????????????????????????
	 * @param resourseVo
	 * @return
	 */
	private boolean doFilterResourse(ResourseVo resourseVo) throws Exception{
		boolean result = false;
		String name = resourseVo.getName();
		int resType = resourseVo.getResType();
		if(name == null||"".equals(name)){
			//????????????????????????
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
	/*//??????= 1;
    #public static final Integer MICRO 
   # //??????= 2;
    #public static final Integer LEARNING_DESIGN 
    #//??????= 3;
    #public static final Integer HOMEWORK 
    ##//??????= 4;
   # public static final Integer EXAM 
    #//??????= 5;
   # public static final Integer TEACHING_PLAN 
    #//??????= 6;
    #public static final Integer MATERIAL */
	
	
	
	/*********************************************??????????????????
	 * @param schoolId ******************************************************/
	public List<TextBookVoResourse> dealWithResTextBook(List<TextBookVoResourse> voResourseList, int schoolId) {
		for (int i = 0; i < voResourseList.size(); i++) {
			TextBookVoResourse textBookVoResourse = voResourseList.get(i);
			System.out.println(textBookVoResourse.getName());
			this.iteraterResTextBook(textBookVoResourse, schoolId);// ???????????????????????????
		}

		return voResourseList;
	}
	
	/**
	 * ????????????
	 * @param textBookVoResourse
	 * @param schoolId 
	 */
	private void iteraterResTextBook(TextBookVoResourse textBookVoResourse, int schoolId) {
		List<ResTextbook> textbookList = getResTextBookByCondition(textBookVoResourse); //??????????????????????????????????????????????????????????????????????????????
		
		if(textbookList != null&&textbookList.size()==1){
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(false);
			textBookVoResourse.setId(textbookList.get(0).getId());
			textBookVoResourse.setMessage(TextBookVoResourse.BEFORESUCCESS);
			
			Resourse resourse = new Resourse();
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			resourse.setMessage("?????????????????????????????????");
			addDetailMessage(beforeDone,resourse);//??????????????????????????????
			
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
			
		}else if(textbookList.size()>1){//?????????????????????????????????????????????????????????????????????
			textBookVoResourse.setDoneBefore(true);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR);//??????????????????????????????????????????????????????
			Resourse resourse = new Resourse();
			resourse.setMessage("???????????????????????????");
			resourse.setName(textBookVoResourse.getName());
			resourse.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse);//??????????????????????????????
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
				textBook = this.resTextbookService.add(textBookAdd);//???????????????????????????
				if(textBook != null&&textBook.getId()>0){
					addCatalogWithResTextBook(textBookVoResourse, textBook);
				}else{
					textBookVoResourse.setDoneBefore(false);
					textBookVoResourse.setWrong(true);
					textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
					Resourse resourse = new Resourse();
					resourse.setMessage("??????????????????");
					resourse.setName(textBookVoResourse.getName());
					resourse.setPath(textBookVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);//??????????????????
				}
			} catch (Exception e) {
				textBookVoResourse.setDoneBefore(false);
				textBookVoResourse.setWrong(true);
				textBookVoResourse.setMessage(TextBookVoResourse.ERROR);
				Resourse resourse = new Resourse();
				resourse.setMessage("??????????????????:"+e.getMessage());
				resourse.setName(textBookVoResourse.getName());
				resourse.setPath(textBookVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);//??????????????????
				
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * ????????????????????????????????????
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
		resourse.setMessage("??????????????????");
		resourse.setName(textBookVoResourse.getName());
		resourse.setPath(textBookVoResourse.getPath());
		addDetailMessage(nowDone,resourse);//??????????????????
		
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
			resourse2.setMessage("?????????????????????");
			resourse2.setName(textBookVoResourse.getName());
			resourse2.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowDone,resourse2);//????????????????????????
		}else{
			textBookVoResourse.setDoneBefore(false);
			textBookVoResourse.setWrong(true);
			textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"????????????????????????");
			
			Resourse resourse3 = new Resourse();
			resourse3.setMessage("?????????????????????");
			resourse3.setName(textBookVoResourse.getName());
			resourse3.setPath(textBookVoResourse.getPath());
			addDetailMessage(nowUndo,resourse3);//????????????????????????
		}
	}
	
	/**
	 * ???????????????????????????????????????
	 * @param voResourseList
	 * @return
	 */
	public List<TextBookVoResourse> dealWithResTextBookCatalog(List<TextBookVoResourse> voResourseList) {
		for (TextBookVoResourse textBookVoResourse : voResourseList) {//?????????????????????????????????
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
						textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"????????????????????????");
						
						Resourse resourse = new Resourse();
						resourse.setMessage("?????????????????????");
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
				        
				        // ??????<>???????????????????????????  
				        p = Pattern.compile("[0-9]{1,}[-]{3}");  
				        m = p.matcher(allName); 
			            if(m.find()){
			            	first = allName.substring(0, allName.indexOf("---"));
			            	textBookCatalogVoResourse.setListOrder(Integer.parseInt(first));
			            	name = allName.substring(allName.indexOf("---")+3, allName.length());
			            	textBookCatalogVoResourse.setName(name);
			            }else{
			            	Resourse resourse = new Resourse();
							resourse.setMessage("???????????????????????????");
							resourse.setName(textBookCatalogVoResourse.getName());
							resourse.setPath(textBookCatalogVoResourse.getPath());
							addDetailMessage(nowUndo,resourse);
							continue;
			            }
					    
					 if(textBookVoResourse.isDoneBefore()){
						
						  //???????????????before???????????????????????? ???????????????????????????
						  // ????????????????????? ????????????????????????????????????????????????????????????????????????
						  
						  ResTextbookCatalogCondition catalogConditionTemp = new ResTextbookCatalogCondition();
						  catalogConditionTemp.setLevel(textBookCatalogVoResourse.getLevel());//level???????????????
						  catalogConditionTemp.setTestBookId(textBookVoResourse.getId());
						  catalogConditionTemp.setName(textBookCatalogVoResourse.getName());
						  catalogConditionTemp.setIsDelete(false);
						  catalogConditionTemp.setParentId(this.getResParentId(stack,catalogConditionTemp.getLevel()));
						  
						  //????????????????????????
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
								resourse.setMessage("???????????????????????????????????????");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(beforeDone,resourse);
							  
							  stack.push(textBookCatalogTemp);
							  
						  }else if(textbookCatalogList != null&&textbookCatalogList.size()>1){
							  textBookCatalogVoResourse.setDoneBefore(true);
							  textBookCatalogVoResourse.setWrong(false);
							  textBookCatalogVoResourse.setId(textbookCatalogList.get(0).getId());
							  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+"??????????????????????????????");
							  
							  	Resourse resourse = new Resourse();
								resourse.setMessage("????????????????????????????????????");
								resourse.setName(textBookCatalogVoResourse.getName());
								resourse.setPath(textBookCatalogVoResourse.getPath());
								addDetailMessage(nowUndo,resourse);
							  
						  }else{
							  //??????????????????
							  this.addResTextBookCatalog(stack, 
									  textBookVoResourse,
									  textBookCatalogVoResourse);
							  
						  }
					  
						
					}else{
						  
						  //??????????????????
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
	 * ?????????????????????????????????????????????
	 * @param stack
	 * @param level
	 * @return
	 */
	private Integer getResParentId(Stack<ResTextbookCatalog> stack,Integer level) {
		ResTextbookCatalog mid = stack.peek();
		BigDecimal catalogLevel = new BigDecimal(level);
		if(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1){
			
		}else{
			while(!(catalogLevel.compareTo(new BigDecimal(mid.getLevel()))==1)){//??????????????????????????????????????????
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
	 * ??????????????????
	 * @param stack
	 * @param textBookVoResourse
	 * @param textBookCatalogVoResourse
	 */
	private void addResTextBookCatalog(Stack<ResTextbookCatalog> stack,
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		ResTextbookCatalog textBookCatalogTemp;
		ResTextbookCatalog textBookCatalogAdd  = new ResTextbookCatalog();  // ???????????????????????????????????????????????????????????????
		
		  textBookCatalogAdd.setLevel(textBookCatalogVoResourse.getLevel());//level???????????????
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
					resourse.setMessage("????????????????????????");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowDone,resourse);
				  
				  stack.push(textBookCatalogTemp);
			  }else{
				  textBookCatalogVoResourse.setDoneBefore(false);
				  textBookCatalogVoResourse.setWrong(true);
				  textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+",????????????????????????");
				  	Resourse resourse = new Resourse();
					resourse.setMessage("????????????????????????");
					resourse.setName(textBookCatalogVoResourse.getName());
					resourse.setPath(textBookCatalogVoResourse.getPath());
					addDetailMessage(nowUndo,resourse);
			  }
		} catch (Exception e) {
			textBookCatalogVoResourse.setDoneBefore(false);
			textBookCatalogVoResourse.setWrong(true);
			textBookCatalogVoResourse.setMessage(TextBookCatalogVoResourse.ERROR+","+e.getMessage().substring(0,e.getMessage().length()>5?5:e.getMessage().length()));
			Resourse resourse = new Resourse();
			resourse.setMessage("????????????????????????,"+e.getMessage());
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
					//???????????????????????????,??????????????????
					
			    	addResourseByResCatalog(
							textBookVoResourse,
							textBookCatalogVoResourse, schoolId);
				}
			}else{
				 textBookVoResourse.setMessage(TextBookVoResourse.CATALOGERROR);//???????????????????????????
				 textBookVoResourse.setWrong(true);
				 
				 	Resourse resourse = new Resourse();
					resourse.setMessage("???????????????????????????");
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
			
			//?????????????????????
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					boolean flag = true;
					
					try {
						flag = !this.doFilterResourse(resourseVo);
					} catch (Exception e1) {
						Resourse resourse = new Resourse();
						resourse.setMessage("???????????????????????????");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
						
					}
					
					if(flag){
						Resourse resourse = new Resourse();
						resourse.setMessage("????????????????????????????????????");
						resourse.setName(resourseVo.getName());
						resourse.setPath(resourseVo.getPath());
						addDetailMessage(nowUndo,resourse);
						continue; 
					}
					
					File resoursefile = new File(resourseVo.getPath());
					String title = resoursefile.getName();//????????????
					String png = "_thumbImg.png";
					if(title != null&&title.indexOf(png) >0){
						continue;
					}
					
					boolean isUpload = false;//???????????????????????????
		    		
					if(isUpload){
						resourseVo.setMessage(ResourseVo.BEFORESUCCESS);
						Resourse resourse = new Resourse();
						resourse.setMessage("?????????????????????????????????");
						resourse.setName(textBookVoResourse.getName());
						resourse.setPath(textBookVoResourse.getPath());
						addDetailMessage(beforeDone,resourse);
					}else{
						try {
							Resource resource = null;
							boolean ensureAdd = true;
							
							if(textBookCatalogVoResourse.isDoneBefore()){
								//??????????????????
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
		    						System.out.println("??????????????? "+resourseVo.getPath());
									resourseVo.setMessage("?????????????????????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("?????????????????????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(beforeDone,resourse);
									
								}
		    					
		    					ensureAdd = !cofirmExist; 
								
							}else{
								 ensureAdd = true;
							}
							
							
							if(ensureAdd){//???????????????????????????
								/*File resoursefile = new File(resourseVo.getPath());
		    					String title = resoursefile.getName();//????????????*/
		    				    String suffix=title.substring(title.lastIndexOf(".")+1);//????????????
		    				    InputStream input = new FileInputStream(resoursefile);//???????????????
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
		    					
		    					//????????????????????????
		    					String resourseThumbName = resoursefile.getAbsolutePath().replaceFirst(resoursefile.getName(), resoursefile.getName().substring(0,resoursefile.getName().lastIndexOf(".")))+"_thumbImg.png";
		    					File resourseThumbfile = new File(resourseThumbName);
		    					InputStream inputThumb = null;
		    					if(!resourseThumbfile.exists()){
		    						inputThumb = null; //????????????????????????
		    					}else{
		    						inputThumb =  new FileInputStream(resourseThumbfile);//???????????????
		    					}
		    				  
		    					ResourceLibraryCondition  resourceLibraryCondition = new ResourceLibraryCondition();
		    					resourceLibraryCondition.setOwerId(schoolId);
		    					List<ResourceLibrary> list = resourceLibraryService.findResourceLibraryByCondition(resourceLibraryCondition);
		    					
		    					if(list.size()>0) {
		    						cv.setLibraryId(list.get(0).getUuid());
		    					}
		    					
		    					System.out.println("??????????????? "+resourseVo.getPath());
		    				    resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("??????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
									System.out.println("??????????????? "+resourseVo.getPath());
									resourseVo.setMessage("??????????????????");
									
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowUndo,resourse);
									
									
								}
							}
						} catch (Exception e) {
							resourseVo.setMessage(ResourseVo.ERROR+"???????????????????????????");
							Resourse resourse = new Resourse();
							resourse.setMessage("???????????????????????????");
							resourse.setName(resourseVo.getName());
							resourse.setPath(resourseVo.getPath());
							addDetailMessage(nowUndo,resourse);
							e.printStackTrace();
							continue;
						}
					}
				}
			}else{
				/*textBookCatalogVoResourse.setMessage("????????????????????????????????????");
				Resourse resourse = new Resourse();
				resourse.setMessage("????????????????????????????????????");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);*/
			}
			
		}
	}

	/**
	 * @function ??????????????????
	 * @param path
	 * @return
	 * @throws Exception
     */
	public String createTreeAndNode(String path) throws Exception {
		// String filePath = "/Users/panfei/test/??????#??????#?????????????????????";
		if(path == null || "".equals(path)){
			System.out.println("??????????????????=====????????????======");
			return "error";
		}
		final File file = new File(path);
		//???????????????????????????
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
			//???????????????????????????????????????????????????
			String fileCode=(String)System.getProperties().get("file.encoding");
			String fileName = f.getName();
			fileName = new String (fileName.getBytes(fileCode),fileCode);
			Integer wjType = getType(fileName);

			//???????????????????????????????????????????????????????????????
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
					System.out.println("?????????????????????"+knowledgeNode.getName());
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
				System.out.println("=====?????????"+childs[i].getName()+" ??????=====");
				FileResult fileResult = this.fileService.upload(childs[i],"","6");

				Integer type = getType(childs[i].getParent());
				if(fileResult != null && fileResult.getEntityFile() != null){
					Resource resource = createResourceOfType(type, SysContants.SYSTEM_APP_ID,fileResult.getEntityFile().getUuid(),childs[i].getName(),userId,userName,schoolId,fileResult.getEntityFile().getExtension(),0);
					System.out.println("?????????"+resource.getUuid()+" ????????????");
					if(resource != null && resource.getObjectId() != null){
						//??????????????????????????????
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
								System.out.println("???????????????????????????????????????????????????"+fileResult.getHttpUrl());
								System.out.println("???????????????UUID???"+fileResult.getEntityFile().getUuid()+"??????????????????");
							}
						}
					}
				}
			}
		}
	}

	public String getStageCode(String name){
		String stageCode = "";
		if ("??????".equals(name)) {
			stageCode = "2";
		} else if ("??????".equals(name)) {
			stageCode = "3";
		} else if ("??????".equals(name)) {
			stageCode = "4";
		} else {
			System.out.println("==????????????????????????????????????????????????==????????? ??????#??????#????????????==????????????????????????");
			return null;
		}
		return stageCode;
	}

	public KnowledgeTree createTree(String name) {
		KnowledgeTree knowledgeTree = null;
		String[] names = name.split("#");
		if (names.length != 3) {
			System.out.println("==????????????????????????????????????????????????==????????? ??????#??????#????????????==");
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
			System.out.println("==????????????????????????????????????????????????==????????? ??????#??????#????????????==?????????????????????????????????????????????????????????");
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
			if ("??????".equals(name)){
				type = ResourceType.MICRO;
			} else if ("??????".equals(name)) {
				type = ResourceType.LEARNING_DESIGN;
			}else if ("??????".equals(name)) {
				type = ResourceType.HOMEWORK;
			}else if ("??????".equals(name)) {
				type = ResourceType.EXAM;
			}else if ("??????".equals(name)) {
				type = ResourceType.TEACHING_PLAN;
			}else if ("??????".equals(name)) {
				type = ResourceType.MATERIAL;
			}else if ("?????????".equals(name)) {
				type = ResourceType.LEARNING_PLAN;
			}else if ("??????????????????".equals(name)) {
				type = 8;
			}else if ("??????".equals(name)) {
				type = 9;
			}else if("??????".equals(name)){
				type = ResourceType.OTHER;
			}
		}
		return type;
	}

	/**
	 * @function ??????????????????
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
		else if(ResourceType.MICRO ==typeint){//????????????
			MicroLesson em = this.microLessonService.saveOrUpdateMicroLesson(relateAppId, null, entityId, MicroType.COMMON_MICRO, title, userId, null, null, null,null);
			if(em != null){
				objId=em.getUuid();
			}
		}
		else if(ResourceType.LEARNING_PLAN ==typeint){//???????????????
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
			if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// ????????????????????????uui
				resourceLibrary = resourceLibraryList.get(0);
			} else {// ????????????????????????????????????
				ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
				resourceLibraryAdd.setAppId(SysContants.SYSTEM_APP_ID);
				resourceLibraryAdd.setOwerId(schoolId);
				resourceLibraryAdd.setName("");
				resourceLibraryAdd.setUuid(platform.education.resource.utils.UUIDUtil.getUUID());// ???????????????uuid
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
