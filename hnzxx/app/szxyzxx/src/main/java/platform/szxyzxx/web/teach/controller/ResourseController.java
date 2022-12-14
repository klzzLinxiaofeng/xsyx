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
	
	//????????????
	@Autowired
	@Qualifier("jcTextbookCatalogService")
	protected TextbookCatalogService jcTextbookCatalogService;
	
	//??????????????????
	@Autowired
	@Qualifier("jcGradeService")
	protected platform.education.generalcode.service.GradeService jcGradeService;
	
	//???????????????
	@Autowired
	@Qualifier("jcTextbookPublisherService")
	protected TextbookPublisherService jcTextbookPublisherService;
	
	// ??????
	@Autowired
	@Qualifier("jcStageService")
	protected StageService jcStageService;
	
	//????????????
	@Autowired
	@Qualifier("jcSubjectService")
	protected platform.education.generalcode.service.SubjectService jcSubjectService;
	
	
	//??????
	@Autowired
	@Qualifier("jcTextbookService")
	protected TextbookService jcTextbookService;
	
	//??????
	@Autowired
	@Qualifier("jcTextbookVersionService")
	private TextbookVersionService jcTextbookVersionService;
	
	//??????
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
    	resourseFileMap.put("??????", 5);
    	resourseFileMap.put("??????", 2);
    	resourseFileMap.put("??????", 4);
    	resourseFileMap.put("??????", 6);
    	resourseFileMap.put("??????", 1);
    	resourseFileMap.put("??????", 3);
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
			path="F:/????????????";
		}
		
		this.main(path);
		
		return mav;
	}
	
	public void main(String path) {
		/**
		 * ??????????????? ???????????????????????? ?????????????????????????????????????????????????????????????????????????????????????????????????????? 123????????????????????????????????????????????????????????????????????????????????????
		 * ?????????????????????????????????
		 * ??????????????????????????????????????????????????????????????????????????????????????? ????????? ?????????????????????????????????????????????????????????
		 * 
		 */
		
		//????????? ??????\??????\??????\??????\??????
		//????????????????????????????????????????????????????????????
		
		 

		  
		  this.initVersionMap();//???????????????
		  this.initGradeMap();//???????????????
		  this.initStageMap();//???????????????
		  this.initSubjectMap();//???????????????
		  this.initVolumnMap();//???????????????
		  
		  OpenResourse openResourse = new OpenResourse();
		  Resourse errorResourse = new Resourse();
		  File file = new File(path);
		  File[] stageList = file.listFiles();//???????????????
		  for (int i = 0; i < stageList.length; i++) {
			File stageFile = stageList[i];
			 Map<String, Object> textBookMap = new HashMap<String, Object>();
			 voResourseList.clear();
			 iteraterStage(errorResourse, stageFile,textBookMap);//????????????????????????
			 openResourse.setVoResourseList(voResourseList);
		  }
		  
		  this.outErrorResourse(errorResourse);
		  
		//????????????????????????????????????????????????????????????????????????????????????????????????

		OpenResourse nowDoneResourse = new OpenResourse(); 
		OpenResourse nowUndoResourse = new OpenResourse();
		OpenResourse beforeDoneResourse = new OpenResourse();
		

		
		List<TextBookVoResourse> voResourseList = openResourse.getVoResourseList();
		
		if(voResourseList != null&&voResourseList.size()>0 ){
			for (int i = 0; i < voResourseList.size(); i++) {
				TextBookVoResourse textBookVoResourse = voResourseList.get(i);
					this.iteraterTextBook(textBookVoResourse);//???????????????????????????
			}
			
			System.out.println("----------------------?????????????????????????????????????????????---begin---------------");
			this.outTextBookMessage(beforeDone.getResourseList());//??????????????????????????????
			this.outTextBookMessage(nowDone.getResourseList());//???????????????????????????
			this.outTextBookMessage(nowUndo.getResourseList());//??????????????????????????????
			System.out.println("----------------------?????????????????????????????????????????????-- end--------------");
		
			
			for (TextBookVoResourse textBookVoResourse : voResourseList) {//?????????????????????????????????
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
							textBookVoResourse.setMessage(TextBookVoResourse.ERROR+"????????????????????????");
							
							Resourse resourse = new Resourse();
							resourse.setMessage("?????????????????????");
							resourse.setName(textBookVoResourse.getName());
							resourse.setPath(textBookVoResourse.getPath());
							addDetailMessage(nowUndo,resourse);
							
					  }
					  
						for (int j = 0; j < voCatalogVoResourseList.size(); j++) {
							TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(j);
						    
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
			
			System.out.println("----------------------???????????????????????????????????????????????????---begin---------------");
			this.outTextBookMessage(beforeDone.getResourseList());//??????????????????????????????
			this.outTextBookMessage(nowDone.getResourseList());//???????????????????????????
			this.outTextBookMessage(nowUndo.getResourseList());//??????????????????????????????
			System.out.println("----------------------???????????????????????????????????????????????????-- end--------------");
			
			//???????????????????????????
			
			for (int k = 0; k < voResourseList.size(); k++) {
				TextBookVoResourse textBookVoResourse = voResourseList.get(k);
				List<TextBookCatalogVoResourse>  voCatalogVoResourseList = textBookVoResourse.getVoCatalogVoResourseList();
				if(voCatalogVoResourseList != null&&voCatalogVoResourseList.size()>0){
					for (int l = 0; l < voCatalogVoResourseList.size(); l++) {
						TextBookCatalogVoResourse textBookCatalogVoResourse = voCatalogVoResourseList.get(l);
						//???????????????????????????,??????????????????
						
				    	addResourseByCatalog(
								nowDoneResourse,
								nowUndoResourse, 
								beforeDoneResourse,
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
		
			
			
		}else{//ok
			nowUndoResourse.setMessage(OpenResourse.ERROR);
		}
		
		//??????????????????????????????????????????
		
		System.out.println("----------------------?????????????????????????????????????????????---begin---------------");
		this.outTextBookMessage(beforeDone.getResourseList());//??????????????????????????????
		this.outTextBookMessage(nowDone.getResourseList());//???????????????????????????
		this.outTextBookMessage(nowUndo.getResourseList());//??????????????????????????????
		System.out.println("----------------------?????????????????????????????????????????????-- end--------------");
		
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
			
			//?????????????????????
			List<ResourseVo> resourseVoList = textBookCatalogVoResourse.getResourseVoList();
			if(resourseVoList != null&&resourseVoList.size()>0){
				for (int i = 0; i < resourseVoList.size(); i++) {
					ResourseVo resourseVo = resourseVoList.get(i);
					
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
									resourseVo.setMessage("??????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
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
		    					
		    					//????????????????????????
		    					String resourseThumbName = resoursefile.getAbsolutePath().replaceFirst(resoursefile.getName(), resoursefile.getName().substring(0,resoursefile.getName().lastIndexOf(".")))+"_thumbImg.png";
		    					File resourseThumbfile = new File(resourseThumbName);
		    					InputStream inputThumb = null;
		    					if(!resourseThumbfile.exists()){
		    						inputThumb = null; //????????????????????????
		    					}else{
		    						inputThumb =  new FileInputStream(resourseThumbfile);//???????????????
		    					}
		    				   
		    				    
		    					resource = importResourceServiceImpl.importResouce(SysContants.SYSTEM_APP_ID, input, suffix, title, resourseVo.getResType(), cv, "",inputThumb);
		    					
		    					if(resource != null&&resource.getId()!=null&&resource.getId()>0){
									resourseVo.setMessage("??????????????????");
									Resourse resourse = new Resourse();
									resourse.setMessage("??????????????????");
									resourse.setName(resourseVo.getName());
									resourse.setPath(resourseVo.getPath());
									addDetailMessage(nowDone,resourse);
									
								}else{
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
				textBookCatalogVoResourse.setMessage("????????????????????????????????????");
				Resourse resourse = new Resourse();
				resourse.setMessage("????????????????????????????????????");
				resourse.setName(textBookCatalogVoResourse.getName());
				resourse.setPath(textBookCatalogVoResourse.getPath());
				addDetailMessage(nowUndo,resourse);
			}
			
		}
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
			
			subjectMap.put(subject.getName(), subject.getCode());
			subjectNameMap.put(subject.getCode(), subject.getName());
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
	  
	

	
	
	
	/**
	 * ?????????????????? 
	 * ??????????????????????????????????????????
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
				iteratorSubject(errorResourse, subjectFile,textBookMap);//??????????????????
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
				iteratorVersion(errorResourse, versionFile,textBookMap);//??????????????????
				
				
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
				iteratorGrade(errorResourse, gradeFile,textBookMap);//??????????????????
				
				
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
			String textBookName = gradeName+subjectName+volumnName;
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
	 * @param catalogConditionTemp
	 * @return
	 */
	private Integer getParentId(Stack<TextbookCatalog> stack,
			Integer level) {
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
	 * @param nowDoneResourse
	 * @param nowUndoResourse
	 * @param beforeDoneResourse
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
			textBookAdd.setPublisherId(publishMap.get(textBookVoResourse.getVersion()));
			textBookAdd.setCreateDate(new Date());
			textBookAdd.setIsDelete(false);
			textBookAdd.setModifyTime(new Date());
			textBookAdd.setIsHidden(false);
			Textbook textBook = null;
			try {
				textBook = this.jcTextbookService.add(textBookAdd);//???????????????????????????
				if(textBook != null&&textBook.getId()>0){
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


	
	
	
	public static void main(String[] args) {
		String name = "C:/Users/Administrator/Desktop/??????";
		String mp4 = name+"/07-1.?????????????????????.mp4";
		File file = new File(mp4);
		System.out.println(file.getAbsolutePath().replaceFirst(file.getName(), file.getName().substring(0,file.getName().lastIndexOf(".")))+"_thumbImg.png");
		
		
		System.out.println(file.getName());
		
		//file.renameTo(fileRename);
	}
	
}
