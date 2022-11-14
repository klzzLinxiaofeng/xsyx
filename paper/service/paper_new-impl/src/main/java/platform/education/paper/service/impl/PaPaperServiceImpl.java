package platform.education.paper.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalcode.model.KnowledgeCatalog;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.service.JcCacheService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.vo.CatalogVo;
import platform.education.generalcode.vo.ResTextbookCatalogCondition;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.PaPaperTree;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.model.PaQuestionKnoledge;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.OwnerMode;
import platform.education.paper.vo.PaFavoritesCondition;
import platform.education.paper.vo.PaPaperCatalogCondition;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.PaPaperTreeCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaperQuestionTree;
import platform.education.paper.vo.QuestionKnoledgeScoreVo;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaPaperTreeService;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.dao.PaPaperDao;
import platform.education.paper.constants.PaperContans;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.ResourceLibraryService;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PaPaperServiceImpl implements PaPaperService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private PaPaperDao paPaperDao;
	@Resource
	private PaPaperCatalogService paPaperCatalogService;
	@Resource
	private PaPaperTreeService paPaperTreeService;
	@Resource
	private PaQuestionService paQuestionService;
	@Resource
	private JcCacheService jcCacheService;
	@Resource
	private SubjectService subjectService;
	@Resource
	private ResourceLibraryService resourceLibraryService;
	@Resource
	private ResTextbookCatalogService resTextbookCatalogService;
	@Resource
	private PaQuestionCatalogService  paQuestionCatalogService;
	@Resource
	private PaQuestionKnoledgeService paQuestionKnoledgeService;
	

	public void setPaPaperDao(PaPaperDao paPaperDao) {
		this.paPaperDao = paPaperDao;
	}

	@Override
	public PaPaper findPaPaperById(Integer id) {
		try {
			return paPaperDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public PaPaper add(PaPaper paPaper) {
		if (paPaper == null) {
			return null;
		}
		Date createDate = paPaper.getCreateDate();
		if (createDate == null) {
			createDate = new Date();
		}
		paPaper.setCreateDate(createDate);
		if(paPaper.getModifyDate()==null) {
			paPaper.setModifyDate(createDate);
		}
		return paPaperDao.create(paPaper);
	}

	@Override
	public PaPaper modify(PaPaper paPaper) {
		if (paPaper == null) {
			return null;
		}
		Date modify = paPaper.getModifyDate();
		paPaper.setModifyDate(modify != null ? modify : new Date());
		return paPaperDao.update(paPaper);
	}
	
	@Override
	public PaPaper modifyNotWithModifyDate(PaPaper paPaper) {
		if (paPaper == null) {
			return null;
		}
		return paPaperDao.update(paPaper);
	}

	@Override
	public void remove(PaPaper paPaper) {
		try {
			paPaperDao.delete(paPaper);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paPaper.getId(), e);
			}
		}
	}

	@Override
	public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page, Order order) {
		return paPaperDao.findPaPaperByCondition(paPaperCondition, page, order);
	}

	@Override
	public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition) {
		return paPaperDao.findPaPaperByCondition(paPaperCondition, null, null);
	}

	@Override
	public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Page page) {
		return paPaperDao.findPaPaperByCondition(paPaperCondition, page, null);
	}

	@Override
	public List<PaPaper> findPaPaperByCondition(PaPaperCondition paPaperCondition, Order order) {
		return paPaperDao.findPaPaperByCondition(paPaperCondition, null, order);
	}

	@Override
	public Long count() {
		return this.paPaperDao.count(null);
	}

	@Override
	public Long count(PaPaperCondition paPaperCondition) {
		return this.paPaperDao.count(paPaperCondition);
	}

	// ------业务方法----------

	// 试卷检索
	@Override
	public List<PaPaperVo> findPaPaperByConditions(Integer userId, String schoolUUID, String libType, String stagecode,
			String subjectcode, String type, String code, String textbookId, Page page, Order order) {

		if (order.getProperty() == null) {
			order.setProperty("modify_date");
		}

		List<PaPaperVo> plist = new ArrayList<PaPaperVo>();

		PaPaperCatalogCondition pcCondition = new PaPaperCatalogCondition();
		if (stagecode != null && !stagecode.equals("0")) {
			pcCondition.setStageCode(stagecode);
		}
		if (subjectcode != null && !subjectcode.equals("0")) {
			pcCondition.setSubjectCode(subjectcode);
		}

		// 收藏夹
		if (PaperContans.FAV.equals(libType)) {
			PaFavoritesCondition pfCondition = new PaFavoritesCondition();
			pfCondition.setObjectType(PaperContans.PAPER);
			plist = paPaperDao.findPaPaperByConditionsONfavorite(pcCondition, userId, pfCondition, page, order);
			return plist;
		}

		PaPaperCondition ppCondition = new PaPaperCondition();
		Integer ownerMode = 1;
		if (PaperContans.PUBLIC.equals(libType)) {
			ownerMode = 0;
		} else if (PaperContans.PERSIONAL.equals(libType)) {
			ownerMode = 2;
		}
		ppCondition.setOwnerMode(ownerMode);
		ppCondition.setUserId(userId);
		ppCondition.setOwnerUid(schoolUUID);

		// 学段科目
		if (type == null) {
			plist = paPaperDao.findPaPaperByStageSubject(ppCondition, pcCondition, page, order);
			// 高级搜索
		} else {
			pcCondition.setType(type);
			if (code != null && !code.equals("0")) {
				pcCondition.setCode(code);
			}
			if (textbookId != null && !textbookId.equals("0")) {
				pcCondition.setTextbookId(Integer.valueOf(textbookId));
			}
			plist = paPaperDao.findPaPaperByCode(ppCondition, pcCondition, page, order);
		}

		return plist;
	}

	@Override
	public List<QuestionKnoledgeScoreVo> findQuestionKnoledgeScoreByPaperId(Integer paperId) {
		return paPaperDao.findQuestionKnoledgeScoreByPaperId(paperId);
	}

	@Override
	public PaPaper findPaPaperByUUid(String paperUuid) {
		PaPaperCondition paPaperCondition = new PaPaperCondition();
		paPaperCondition.setPaperUuid(paperUuid);
		List<PaPaper> paperList = this.findPaPaperByCondition(paPaperCondition);
		if (paperList.size() > 0) {
			return paperList.get(0);
		}
		return null;
	}

	@Override
	public void removeAllPaperInfo(PaPaper paPaper) {
		Integer paperId = paPaper.getId();
		paPaperCatalogService.removeByPaperId(paperId);

		paQuestionService.removeByPaperId(paperId);

		paPaperTreeService.removeByPaperId(paperId);

		this.remove(paPaper);
	}

	public PaPaperCatalogService getPaPaperCatalogService() {
		return paPaperCatalogService;
	}

	public void setPaPaperCatalogService(PaPaperCatalogService paPaperCatalogService) {
		this.paPaperCatalogService = paPaperCatalogService;
	}

	public PaPaperTreeService getPaPaperTreeService() {
		return paPaperTreeService;
	}

	public void setPaPaperTreeService(PaPaperTreeService paPaperTreeService) {
		this.paPaperTreeService = paPaperTreeService;
	}

	public PaQuestionService getPaQuestionService() {
		return paQuestionService;
	}

	public void setPaQuestionService(PaQuestionService paQuestionService) {
		this.paQuestionService = paQuestionService;
	}
	
	//待存草稿
	@Override
	public String consturctNewPaper(List <PaPaper> papers, List <Integer> questionIds) {
		JSONArray object = new JSONArray();
		if(papers != null && papers.size() > 0){
			//第一份试卷题目
			String paperData = papers.get(0).getPaperData();
			JSONObject jsonObject = JSONObject.fromObject(paperData);
			object = (JSONArray) jsonObject.get("group");
			//如果有第二份试卷
			if(papers.size()>1){
				for(int i = 1 ; i< papers.size(); i++){
					paperData = papers.get(i).getPaperData();
					jsonObject = JSONObject.fromObject(paperData);
					JSONArray object1 = (JSONArray) jsonObject.get("group");
					int size = object1.size();
					for(int j = 0 ; j < size; j++){
						object.add(object1.get(j));
					}
				}
			}
		}
		//个别题目
		if(questionIds != null && questionIds.size() > 0){
			for (Integer qId : questionIds) {
				PaQuestion pq = paQuestionService.findPaQuestionById(qId);
				String pqJson = paQuestionService.getJsonDataFromPaQuestionById(qId);
				object.add(pqJson);
			}
		}
		return object.toString();
	}

	@Override
	public List<PaPaperVo> findPaPaperByCatalogCodes(String[] codes, Page page) {
		return paPaperDao.findPaPaperByCatalogCodes(codes, page);
	}
	
	@Override
	public List<PaPaper> findMyUploadPaper(Integer userId, Page page, Order order) {
		PaPaperCondition paPaperCondition = new PaPaperCondition();
		paPaperCondition.setOwnerMode(OwnerMode.PERSONAL);
		paPaperCondition.setUserId(userId);
		paPaperCondition.setIsDeleted(false);
		List<PaPaper> paPaperList = this.findPaPaperByCondition(paPaperCondition, page, order);
		return paPaperList;
	}

	@Override
	public List<PaPaper> findMyFavPaper(Integer userId, Page page, Order order) {
		return paPaperDao.findMyFavPaper(userId, page, order);
	}

	//个人中心 试卷
	@Override
	public List<PaPaperVo> findMyPaper(Integer userId, String libType, String stageCode, String subjectCode, Page page,
			Order order) {
		
		if (order.getProperty() == null) {
			order.setProperty("modify_date");
		}

		List<PaPaperVo> plist = new ArrayList<PaPaperVo>();

		PaPaperCatalogCondition pcCondition = new PaPaperCatalogCondition();
		if (stageCode != null && !stageCode.equals("0")) {
			pcCondition.setStageCode(stageCode);
		}
		if (subjectCode != null && !subjectCode.equals("0")) {
			pcCondition.setSubjectCode(subjectCode);
		}

		// 收藏夹
		if (PaperContans.FAV.equals(libType)) {
			PaFavoritesCondition pfCondition = new PaFavoritesCondition();
			pfCondition.setObjectType(PaperContans.PAPER);
			plist = paPaperDao.findPaPaperByConditionsONfavorite(pcCondition, userId, pfCondition, page, order);
			return plist;
		// 我创建的
		}else {
			plist = paPaperDao.findMyUploadPaPaper(pcCondition, userId, page, order);
		}
		
		return plist;
	}

	//试卷ID查找学段code
	@Override
	public String findStageCodeByPaperId(Integer paperId) {
		return paPaperDao.findStageCodeByPaperId(paperId);
	}

	@Override
	public List<KnowledgeCountVo> findKnowledgeCountByPaperUuid(Integer paperId) {
		return paPaperDao.findKnowledgeCountByPaperUuid(paperId);
	}

	@Override
	public String findPaperJsonByDb(Integer paperId) {
		try{
			ObjectMapper objm=new ObjectMapper();
			com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
			com.alibaba.fastjson.JSONObject obj=new com.alibaba.fastjson.JSONObject();
			com.alibaba.fastjson.JSONObject sections=new com.alibaba.fastjson.JSONObject();
			com.alibaba.fastjson.JSONArray subjects=new com.alibaba.fastjson.JSONArray();
			com.alibaba.fastjson.JSONArray catalogs=new com.alibaba.fastjson.JSONArray();
			com.alibaba.fastjson.JSONArray knowledges=new com.alibaba.fastjson.JSONArray();
			com.alibaba.fastjson.JSONObject body=new com.alibaba.fastjson.JSONObject();
			com.alibaba.fastjson.JSONArray group=new com.alibaba.fastjson.JSONArray();
			PaPaper pa=paPaperDao.findById(paperId);
			PaPaperCatalogCondition pcc=new PaPaperCatalogCondition();
			pcc.setPaperId(paperId);
			List<PaPaperCatalog> pclist=paPaperCatalogService.findPaPaperCatalogByCondition(pcc);
			List<PaperQuestionTree> list=new ArrayList<PaperQuestionTree>();
			list=paQuestionService.findPaperQuestionTreeByPaperId(paperId, null,1);
			PaPaperTreeCondition pptCondition=new PaPaperTreeCondition();
			pptCondition.setPaperId(paperId);
			pptCondition.setNodeType(0);
			List<PaPaperTree> pptlist=new ArrayList<PaPaperTree>();
			pptlist=paPaperTreeService.findPaPaperTreeByCondition(pptCondition, null, null);
			String stageCode="";
			String stageName="";
			Map<String,String>subjctMap=new HashMap<String,String>();
			ResourceLibrary rl= resourceLibraryService.findResourceLibraryById(pa.getResourceLibId());
			subjctMap=subjectService.getSubjectMap(rl.getOwerId());
			if(pclist!=null&&pclist.size()>0){
				String[] uuids=new String[pclist.size()];
				stageCode=pclist.get(0).getStageCode();
				stageName=jcCacheService.findByParam("jc_stage", "code", stageCode).get(0).get("name").toString();
				int i=0;
				for(PaPaperCatalog pc:pclist){
					uuids[i]=pc.getCatalogCode();
					String subjectCode=pc.getSubjectCode();
					String name=subjctMap.get(subjectCode);
					if(name==null){
						name="";
					}
					com.alibaba.fastjson.JSONObject subject=new com.alibaba.fastjson.JSONObject();
					subject.put("name", name);
					subject.put("code", subjectCode);
					subjects.add(subject);
					i++;
				}
				Map<String,CatalogVo> map= resTextbookCatalogService.findResCatalogVoByUuids(uuids);
				for(CatalogVo vo:map.values()){
					String rc2Name="";
					String rc2Code="";
					String rc1Name="";
					String rc1Code="";
				      com.alibaba.fastjson.JSONObject catalog=new com.alibaba.fastjson.JSONObject();
					  com.alibaba.fastjson.JSONObject book=new com.alibaba.fastjson.JSONObject();
					  book.put("name", vo.getVersionName());
					  book.put("id", Integer.valueOf(vo.getVersionId()));
					  com.alibaba.fastjson.JSONObject volumn=new com.alibaba.fastjson.JSONObject();
					  volumn.put("name", vo.getGradeName()+vo.getVolumnName());
					  volumn.put("code", vo.getVolumnCode());
					  com.alibaba.fastjson.JSONObject bookSction=new com.alibaba.fastjson.JSONObject();
					  com.alibaba.fastjson.JSONObject bookItem=new com.alibaba.fastjson.JSONObject();
					  bookItem.put("code", vo.getCatalogCode());
					  bookItem.put("name",vo.getCatalogName());
				      ResTextbookCatalog rc3= resTextbookCatalogService.findResTextbookCatalogByCode(vo.getCatalogCode());
				      if(rc3.getParentId()!=null&&rc3.getParentId()!=0){
				    	  ResTextbookCatalog rc2=resTextbookCatalogService.findResTextbookCatalogById(rc3.getParentId());
				    	  rc2Name=rc2.getName();
				    	  rc2Code=rc2.getCode();
				    	  if(rc2.getParentId()!=null&&rc2.getParentId()!=0){
				    		 ResTextbookCatalog rc1=resTextbookCatalogService.findResTextbookCatalogById(rc2.getParentId());
				    		 rc1Name=rc1.getName();
				    		 rc1Code=rc1.getCode();
				    	  }
				      }
					  bookSction.put("name", rc2Name);
					  bookSction.put("code",rc2Code);
					  com.alibaba.fastjson.JSONObject bookUnit=new com.alibaba.fastjson.JSONObject();
					  bookUnit.put("name", rc1Name);
					  bookUnit.put("code",rc1Code);
					  catalog.put("book", book);
					  catalog.put("volumn", volumn);
					  catalog.put("bookUnit", bookUnit);
					  catalog.put("bookSction", bookSction);
					  catalog.put("bookItem", bookItem);
					  catalogs.add(catalog);
				}
			}
			List<PaQuestionVo> pqvoList=new ArrayList<PaQuestionVo>();
			pqvoList=paQuestionService.findPaQuestionVoByPaperId(paperId,0,null);
			Integer[] ids=new Integer[pqvoList.size()];
			int i=0;
			for(PaQuestionVo vo:pqvoList){
				ids[i]=vo.getId();
				i++;
			}
			List<PaQuestionCatalog> pqclist=paQuestionCatalogService.findPaQuestionCatalogByQuestionIds(ids);
			List<PaQuestionKnoledge> pqklist=paQuestionKnoledgeService.findByQuestionIds(ids);
			Map<Integer,String>pqcMap=new HashMap<Integer, String>();
			Map<Integer,List<String>>pqkMap=new HashMap<Integer, List<String>>();
			for(PaQuestionCatalog pc:pqclist){
				pqcMap.put(pc.getQuestionId(), pc.getSubjectCode());
			}
			for(PaQuestionKnoledge pk:pqklist){
				List<String> strList=new ArrayList<String>();
				if(pqkMap.get(pk.getQuestionId())!=null){
					strList=pqkMap.get(pk.getQuestionId());
				}
				strList.add(pk.getKnowledgeCode());
				pqkMap.put(pk.getQuestionId(), strList);
			}
			int pos=1;
			for(PaperQuestionTree o:list){
				com.alibaba.fastjson.JSONObject first=new com.alibaba.fastjson.JSONObject();
				first.put("name", o.getName());
				first.put("pos", pos);
				pos++;
				if(o.getObj().getScore()==0){
					first.put("score", "0");
				}else{
					first.put("score", o.getObj().getScore().toString());
				}
				first.put("memo", o.getObj().getMemo());
				com.alibaba.fastjson.JSONArray secAry=new com.alibaba.fastjson.JSONArray();
				List<PaperQuestionTree> secList=o.getChildrens();
				if(secList!=null&&secList.size()>0){
					for(PaperQuestionTree o1:secList){
						com.alibaba.fastjson.JSONObject secObj=new com.alibaba.fastjson.JSONObject();
						String subjectName="";
						String subjectCode="";
						if(pqcMap.get(o1.getObj().getId())!=null){
							subjectCode=pqcMap.get(o1.getObj().getId());
							if(subjctMap.get(subjectCode)!=null){
								subjectName=subjctMap.get(subjectCode);
							}
						}
						com.alibaba.fastjson.JSONObject secSubject=new com.alibaba.fastjson.JSONObject();
						com.alibaba.fastjson.JSONArray secknowledges=new com.alibaba.fastjson.JSONArray();
						List<String> secKlist=pqkMap.get(o1.getObj().getId());
						if(secKlist!=null&&secKlist.size()>0){
							for(String code:secKlist){
								com.alibaba.fastjson.JSONObject secknowledge=new com.alibaba.fastjson.JSONObject();
							    String kName=	 jcCacheService.findByParam("jc_knowledge_node", "id", code).get(0).get("name").toString();
							    secknowledge.put("code", code);
							    secknowledge.put("name", kName);
							    secknowledges.add(secknowledge);
							}
						}
						com.alibaba.fastjson.JSONArray trdAry=new com.alibaba.fastjson.JSONArray();
						if(o1.getChildrens()!=null &&o1.getChildrens().size()>0){
							List<PaperQuestionTree> trdList=o1.getChildrens();
							for(PaperQuestionTree o2:trdList){
								com.alibaba.fastjson.JSONObject trdObj=new com.alibaba.fastjson.JSONObject();
								trdObj.put("id", o2.getObj().getUuid());
								trdObj.put("pos", o2.getObj().getPos());
								trdObj.put("num", o2.getObj().getPos());
								trdObj.put("score", o2.getObj().getScore());
								trdObj.put("memo", o2.getObj().getMemo());
								
								try {
									String[] ss={};
//									System.out.println(o2.getObj().getAnswer());
//									System.out.println(o2.getObj().getCorrectAnswer());
									if(!o2.getObj().getAnswer().equals("[]")){
										String[]  real={};
										try{
											 real=objm.readValue(o2.getObj().getAnswer() ,String[].class);
										}catch(Exception e){
											real=ss;
										}
										trdObj.put("answer", real);
									}else{
										trdObj.put("answer", ss);
									}
									if(!o2.getObj().getCorrectAnswer().equals("[]")){
										String[]  real={};
										try{
											 real=objm.readValue(o2.getObj().getCorrectAnswer() ,String[].class);
										}catch(Exception e){
											real=ss;
										}
									   trdObj.put("correctAnswer",real);
									}else{
										trdObj.put("correctAnswer",ss);
									}
								} catch (Exception e) {
//									System.out.println(o2.getObj().getAnswer());
									e.printStackTrace();
								}
								trdObj.put("type", o2.getObj().getQuestionType());
								trdObj.put("content", o2.getObj().getContent());
								trdObj.put("explanation", o2.getObj().getExplanation());
								trdObj.put("difficulty", o2.getObj().getDifficulity());
								trdObj.put("isSubjective", o2.getObj().getQuestionProperty().toString());
								trdObj.put("cognition", o2.getObj().getCognition());
								String subjectName1="";
								String subjectCode1="";
								if(pqcMap.get(o2.getObj().getId())!=null){
									subjectCode1=pqcMap.get(o2.getObj().getId());
									if(subjctMap.get(subjectCode1)!=null){
										subjectName1=subjctMap.get(subjectCode1);
									}
								}
								com.alibaba.fastjson.JSONObject trdSubject=new com.alibaba.fastjson.JSONObject();
								trdSubject.put("name", subjectName1);
								trdSubject.put("code", subjectCode1);
								trdObj.put("subject",trdSubject);
								com.alibaba.fastjson.JSONArray trdknowledges=new com.alibaba.fastjson.JSONArray();
								List<String> trdKlist=pqkMap.get(o2.getObj().getId());
								if(trdKlist!=null&&trdKlist.size()>0){
									for(String code:trdKlist){
										com.alibaba.fastjson.JSONObject trdknowledge=new com.alibaba.fastjson.JSONObject();
									    String kName=	 jcCacheService.findByParam("jc_knowledge_node", "id", code).get(0).get("name").toString();
									    trdknowledge.put("code", code);
									    trdknowledge.put("name", kName);
									    trdknowledges.add(trdknowledge);
									}
								}
								com.alibaba.fastjson.JSONArray trdAry1=new com.alibaba.fastjson.JSONArray();
								trdObj.put("knowledges", trdknowledges);
								trdObj.put("questions", trdAry1);
								trdAry.add(trdObj);
							}
						}
						secObj.put("id", o1.getObj().getUuid());
						secObj.put("pos", o1.getObj().getPos());
						secObj.put("num", o1.getObj().getPos());
						secObj.put("score", o1.getObj().getScore());
						secObj.put("memo", o1.getObj().getMemo());
						try {
							String[] ss={};
//							System.out.println(o1.getObj().getAnswer());
//							System.out.println(o1.getObj().getCorrectAnswer());
							if(!o1.getObj().getAnswer().equals("[]")){
								String[]  real={};
								try{
									 real=objm.readValue(o1.getObj().getAnswer() ,String[].class);
								}catch(Exception e){
									real=ss;
								}
								secObj.put("answer", real);
							}else{
								secObj.put("answer", ss);
							}
							if(!o1.getObj().getCorrectAnswer().equals("[]")){
								String[]  real={};
								try{
									 real=objm.readValue(o1.getObj().getCorrectAnswer() ,String[].class);
								}catch(Exception e){
									real=ss;
								}
								secObj.put("correctAnswer",real);
							}else{
								secObj.put("correctAnswer",ss);
							}
						} catch (Exception e) {
//							System.out.println(o1.getObj().getAnswer());
							e.printStackTrace();
						}
//						secObj.put("correctAnswer", o1.getObj().getCorrectAnswer());
						secObj.put("type", o1.getObj().getQuestionType());
						secObj.put("content", o1.getObj().getContent());
						secObj.put("explanation", o1.getObj().getExplanation());
						secObj.put("difficulty", o1.getObj().getDifficulity());
						secObj.put("isSubjective", o1.getObj().getQuestionProperty().toString());
						secObj.put("cognition", o1.getObj().getCognition());
						secSubject.put("name", subjectName);
						secSubject.put("code", subjectCode);
						secObj.put("subject",secSubject);
						secObj.put("knowledges", secknowledges);
						secObj.put("questions",trdAry);
						secAry.add(secObj);
					}
				}
				first.put("questions", secAry);
				group.add(first);
			}
			
			sections.put("name", stageName);
			sections.put("code", stageCode);
			obj.put("id", pa.getPaperUuid());
			obj.put("title", pa.getTitle());
			obj.put("type", 1);
			obj.put("sections", sections);
			obj.put("subjects", subjects);
			obj.put("catalogs", catalogs);
			obj.put("knowledges", knowledges);
			obj.put("memo", pptlist.get(0).getMemo());
			obj.put("hasGroup", true);
			obj.put("groupCount", list.size());
			obj.put("questionCount", pqvoList.size());
			obj.put("score", pa.getScore());
			body.put("group", group);
			com.alibaba.fastjson.JSONObject  extra=new com.alibaba.fastjson.JSONObject();
			extra.put("editor", "test");
			extra.put("editorVersion", "test001");
			extra.put("language", "CN");
			extra.put("keyword", "test");
			extra.put("createdTime", "");
			extra.put("modifiedTime", "");
			extra.put("userId", pa.getUserId().toString());
			extra.put("author", "test");
			extra.put("manufacturer", "MQT");
			extra.put("publisher", "MQT");
			extra.put("copyright", "0.0.0.1");
			extra.put("permission", "MQT");
			extra.put("source", "MQT");
			com.alibaba.fastjson.JSONObject school=new com.alibaba.fastjson.JSONObject();
			school.put("id",rl.getOwerId());
			school.put("name","");
			extra.put("school", school);
			extra.put("editor", "");
			json.put("paper", obj);
			json.put("body", body);
			json.put("extra", extra);
			return json.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public List<PaPaper> findPaperByUuids(String[] paperUuids) {
		
		return paPaperDao.findPaperByUuids(paperUuids);
	}

}

