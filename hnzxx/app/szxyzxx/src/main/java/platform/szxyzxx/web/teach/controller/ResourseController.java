package platform.szxyzxx.web.teach.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.Grade;
import platform.education.generalcode.model.Stage;
import platform.education.generalcode.model.Subject;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.service.TextbookCatalogService;
import platform.education.generalcode.service.TextbookPublisherService;
import platform.education.generalcode.service.TextbookService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.service.TextbookVolumnService;
import platform.education.generalcode.vo.TextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookCondition;
import platform.education.resource.service.ImportResourceService;
import platform.education.resource.vo.ImportCatalogVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.vo.OpenResourse;
import platform.szxyzxx.web.teach.vo.Resourse;
import platform.szxyzxx.web.teach.vo.ResourseVo;
import platform.szxyzxx.web.teach.vo.TextBookCatalogVoResourse;
import platform.szxyzxx.web.teach.vo.TextBookVoResourse;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.resource.model.Resource;

@Controller
@RequestMapping("/teach/resourse")
public class ResourseController {
	
	
	@Autowired
	@Qualifier("importResourceService")
	private ImportResourceService importResourceServiceImpl;
	
	//教材目录
	@Autowired
	@Qualifier("jcTextbookCatalogService")
	protected TextbookCatalogService jcTextbookCatalogService;
	
	//基础年级代码
	@Autowired
	@Qualifier("jcGradeService")
	protected platform.education.generalcode.service.GradeService jcGradeService;
	
	//教材出版社
	@Autowired
	@Qualifier("jcTextbookPublisherService")
	protected TextbookPublisherService jcTextbookPublisherService;
	
	// 学段
	@Autowired
	@Qualifier("jcStageService")
	protected StageService jcStageService;
	
	//基础科目
	@Autowired
	@Qualifier("jcSubjectService")
	protected platform.education.generalcode.service.SubjectService jcSubjectService;
	
	
	//教材
	@Autowired
	@Qualifier("jcTextbookService")
	protected TextbookService jcTextbookService;
	
	//版本
	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService jcTextbookVersionService;
	
	//册次
	@Autowired
	@Qualifier("jcTextbookVolumnService")
	protected TextbookVolumnService jcTextbookVolumnService;
	
	
	
	
	
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
    
    private static  Resourse nowUndo = new Resourse();
    private static  Resourse beforeDone = new Resourse();
    private static  Resourse nowDone = new Resourse();
    
    static{
    	
    	resourseFileMap = new HashMap<String, Integer>();
    	resourseFileMap.put("教案", 5);
    	resourseFileMap.put("课件", 2);
    	resourseFileMap.put("试卷", 4);
    	resourseFileMap.put("素材", 6);
    	resourseFileMap.put("微课", 1);
    	resourseFileMap.put("作业", 3);
    }
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "path", required = false) String path,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		ModelAndView mav = new ModelAndView();
		if(path == null || "".equals(path)){
			path="F:/小学资源";
		}
		
		this.main(path);
		
		return mav;
	}
	
	public void main(String path) {
		/**
		 * 内容类型为 前面文件夹分别为 《学段，科目，版本，年级，册次》前台解析的时候需要判断年级中是否包含 123，，，一二三，，，之类的数字问题，如果包括，就把年级去掉
		 * 中间内容为教材目录内容
		 * 最后一个文件夹为资源为资源文件夹，资源文件夹的名称受到限制 只能为 《教案，课件，试卷，素材，微课，作业》
		 * 
		 */
		
		//正常的 小学\语文\版本\年级\册次
		//把目录解析成一棵树，判断教材目录是否正确
		
		 

		  
		  this.initVersionMap();//初始化版本
		  this.initGradeMap();//初始化年级
		  this.initStageMap();//初始化学段
		  this.initSubjectMap();//初始化科目
		  this.initVolumnMap();//初始化册次
		  
		  OpenResourse openResourse = new OpenResourse();
		  Resourse errorResourse = new Resourse();
		  File file = new File(path);
		  File[] stageList = file.listFiles();//第一层学科
		  for (int i = 0; i < stageList.length; i++) {
			File stageFile = stageList[i];
			 Map<String, Object> textBookMap = new HashMap<String, Object>();
			 voResourseList.clear();
			 iteraterStage(errorResourse, stageFile,textBookMap);//校验学段是否正确
			 openResourse.setVoResourseList(voResourseList);
		  }
		  
		  this.outErrorResourse(errorResourse);
		  
		//判断教材对象是否存在，判断目录对象是否存在，判断资源对象是否存在

		OpenResourse nowDoneResourse = new OpenResourse(); 
		OpenResourse nowUndoResourse = new OpenResourse();
		OpenResourse beforeDoneResourse = new OpenResourse();
		

		
		List<TextBookVoResourse> voResourseList = openResourse.getVoResourseList();
		
		if(voResourseList != null&&voResourseList.size()>0 ){
			for (int i = 0; i < voResourseList.size(); i++) {
				TextBookVoResourse textBookVoResourse = voResourseList.get(i);
					this.iteraterTextBook(textBookVoResourse);//处理每一个教材信息
			}
			
			System.out.println("----------------------添加教材执行关闭，输出执行结果---begin---------------");
			this.outTextBookMessage(beforeDone.getResourseList());//输出之前加载过得教材
			this.outTextBookMessage(nowDone.getResourseList());//输出加载成功得教材
			this.outTextBookMessage(nowUndo.getResourseList());//输出未加载成功得教材
			System.out.println("----------------------添加教材执行关闭，输出执行结果-- end--------------");
		
			
			for (TextBookVoResourse textBookVoResourse : voResourseList) {//处理每一个教材目录信息
				Stack<TextbookCatalog> stack = new Stack<TextbookCatalog>();
				if(textBookVoResourse.isWrong()){
					continue;
				}else{
					
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
			
			System.out.println("----------------------添加教材目录执行关闭，输出执行结果---begin---------------");
			this.outTextBookMessage(beforeDone.getResourseList());//输出之前加载过得教材
			this.outTextBookMessage(nowDone.getResourseList());//输出加载成功得教材
			this.outTextBookMessage(nowUndo.getResourseList());//输出未加载成功得教材
			System.out.println("----------------------添加教材目录执行关闭，输出执行结果-- end--------------");
			
			//处理每一个资源信息
			
			for (int k = 0; k < voResourseList.size(); k++) {
				TextBookVoResourse textBookVoResourse = voResourseList.get(k);
				List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
				if(voCatalogVoResourseList != null&&voCatalogVoResourseList.size()>0){
					for (int l = 0; l < voCatalogVoResourseList.size(); l++) {
						TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(l);
						//拿到对应的目录信息,查看资源情况
						
				    	addResourseByCatalog(
								nowDoneResourse,
								nowUndoResourse, 
								beforeDoneResourse,
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
		
			
			
		}else{//ok
			nowUndoResourse.setMessage(OpenResourse.ERROR);
		}
		
		//对不存在的资源进行对应的添加
		
		System.out.println("----------------------添加资源执行关闭，输出执行结果---begin---------------");
		this.outTextBookMessage(beforeDone.getResourseList());//输出之前加载过得教材
		this.outTextBookMessage(nowDone.getResourseList());//输出加载成功得教材
		this.outTextBookMessage(nowUndo.getResourseList());//输出未加载成功得教材
		System.out.println("----------------------添加资源执行关闭，输出执行结果-- end--------------");
		
	}

	private void outTextBookMessage(List<Resourse> resourseList) {
		if(resourseList != null &&resourseList.size()>0){
			for (Resourse resourse : resourseList) {
				System.out.println(resourse.getPath()+":"+resourse.getMessage());
			}
		}
	}

	private void outErrorResourse(Resourse errorResourse) {
		List<Resourse> errorResourseList =  errorResourse.getResourseList();
		  if(errorResourseList != null &&errorResourseList.size()>0){
			  for (int i = 0; i < errorResourseList.size(); i++) {
				  Resourse resourse = errorResourseList.get(i);
				  System.out.println(resourse.getPath()+":"+resourse.getMessage());
				 
			}
		  }
	}
	
	private void addResourseByCatalog(
			 OpenResourse nowDoneResourse,
			OpenResourse nowUndoResourse, OpenResourse beforeDoneResourse,
			TextBookVoResourse textBookVoResourse,
			TextBookCatalogVoResourse textBookCatalogVoResourse) {
		if(textBookCatalogVoResourse.isWrong()){
			
		}else{
			
			//之前有过添加，
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					
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
		    					cv.setCatalogCode(textBookCatalogVoResourse.getId()+"");
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
		    					
		    					cofirmExist = importResourceServiceImpl.confirmExist(SysContants.SYSTEM_APP_ID, resourseVo.getResType(), title, cv);
		    				
		    					if(cofirmExist){
									resourseVo.setMessage("资源添加成功");
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加成功");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
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
		    					cv.setCatalogCode(textBookCatalogVoResourse.getId()+"");
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
		    				   
		    				    
		    					resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("资源添加成功");
									Resourse resourse = new Resourse();
									resourse.setMessage("资源添加成功");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
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
				textBookCatalogVoResourse.setMessage("目录中不存在相关资源内容");
				Resourse resourse = new Resourse();
				resourse.setMessage("目录中不存在相关资源内容");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);
			}
			
		}
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
			
			subjectMap.put(subject.getName(), subject.getCode());
			subjectNameMap.put(subject.getCode(), subject.getName());
		}
	}
	
	/**
	 * 初始化科目
	 */
	private void initVolumnMap(){//volumnMap
		List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(null);
		for (TextbookVolumn volumn : volumnList) {
			
			volumnMap.put(volumn.getName(), volumn.getCode());
			volumnNameMap.put(volumn.getCode(), volumn.getName());
		}
	}
	  
	

	
	
	
	/**
	 * 循环校验学段 
	 * 学段，科目，版本，年级，册次
	 * @param errorResourse
	 * @param stageFile
	 */
	private void iteraterStage(Resourse errorResourse, File stageFile, Map<String, Object> textBookMap) {
		
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
				iteratorSubject(errorResourse, subjectFile,textBookMap);//循环校验科目
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
	 * @param errorResourse
	 * @param subjectFile
	 */
	private void iteratorSubject(Resourse errorResourse, File subjectFile,Map<String, Object> textBookMap) {
		
		
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
				iteratorVersion(errorResourse, versionFile,textBookMap);//循环校验版本
				
				
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
	 * @param errorResourse
	 * @param subjectFile
	 */
	private void iteratorVersion(Resourse errorResourse, File versionFile,Map<String, Object> textBookMap) {
		
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
				iteratorGrade(errorResourse, gradeFile,textBookMap);//循环校验学年
				
				
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
	 * @param errorResourse
	 * @param gradeFile
	 */
	private void iteratorGrade(Resourse errorResourse, File gradeFile,Map<String, Object> textBookMap) {
		
		
		
		boolean gradeOk = false;
		String gradeFileCode = gradeMap.get(gradeFile.getName());
		if(gradeFileCode != null&&!"".equals(gradeFileCode)){
			gradeOk = true;
			textBookMap.put("grade", gradeFileCode);
		}else{
			gradeOk = false;
		}
		
		
		if(gradeOk){
			File[] volomnList = gradeFile.listFiles();
			for (int l = 0; l < volomnList.length; l++) {
				File volomnFile = volomnList[l];
				iteratorVolmn(errorResourse, volomnFile,textBookMap);
				
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
	 * @param errorResourse
	 * @param gradeFile
	 */
	private void iteratorVolmn(Resourse errorResourse, File volomnFile,Map<String, Object> textBookMap) {
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
			String textBookName = gradeName+subjectName+volumnName;
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
		ResourseVo resouseVo = new ResourseVo();
		resouseVo.setLeaf(false);
		resouseVo.setLevel(level);
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
				for (File file2 : fileList) {
					iteratorCatalogStackList(catalogStackList,file2,++level);
				}
				
			}else{
				
				return;
			}
			
			
		}
		
	}
	
//	private void iteratorCatalog(Stack<ResourseVo> catalogStack,File file,Integer level){
//		ResourseVo resouseVo = new ResourseVo();
//		resouseVo.setLeaf(false);
//		resouseVo.setLevel(level);
//		resouseVo.setPath(file.getAbsolutePath());
//		resouseVo.setResType(0);
//		resouseVo.setName(file.getName());
//		
//		if(file.isFile()){
//			resouseVo.setLeaf(true);
//			catalogStack.push(resouseVo);
//			return;
//		}else{
//			resouseVo.setLeaf(false);
//			catalogStack.push(resouseVo);
//			File[] fileList = file.listFiles();
//			if(fileList != null &&fileList.length>0){
//				for (File file2 : fileList) {
//					iteratorCatalog(catalogStack,file2,++level);
//				}
//				
//			}else{
//				
//				return;
//			}
//			
//			
//		}
//		
//	}
	
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
	 * @param catalogConditionTemp
	 * @return
	 */
	private Integer getParentId(Stack<TextbookCatalog> stack,
			Integer level) {
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
	 * @param nowDoneResourse
	 * @param nowUndoResourse
	 * @param beforeDoneResourse
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
			textBookAdd.setPublisherId(publishMap.get(textBookVoResourse.getVersion()));
			textBookAdd.setCreateDate(new Date());
			textBookAdd.setIsDelete(false);
			textBookAdd.setModifyTime(new Date());
			textBookAdd.setIsHidden(false);
			Textbook textBook = null;
			try {
				textBook = this.jcTextbookService.add(textBookAdd);//调用添加教材的方法
				if(textBook != null&&textBook.getId()>0){
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


	
	
	
	public static void main(String[] args) {
		String name = "C:/Users/Administrator/Desktop/资源";
		String mp4 = name+"/07-1.辉煌的隋唐文化.mp4";
		File file = new File(mp4);
		System.out.println(file.getAbsolutePath().replaceFirst(file.getName(), file.getName().substring(0,file.getName().lastIndexOf(".")))+"_thumbImg.png");
		
		
		System.out.println(file.getName());
		
		//file.renameTo(fileRename);
	}
	
}
