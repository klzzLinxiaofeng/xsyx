package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaUserWrong;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.PaUserWrongCondition;
import platform.education.paper.vo.WrongPaperVo;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaUserWrongService;
import platform.education.paper.dao.PaUserWrongDao;
import platform.service.storage.service.FileService;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PaUserWrongServiceImpl implements PaUserWrongService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PaUserWrongDao paUserWrongDao;
	@Resource
	private PaQuestionService paQuestionService;
    
	@Resource
	private FileService fileService;
	public void setPaUserWrongDao(PaUserWrongDao paUserWrongDao) {
		this.paUserWrongDao = paUserWrongDao;
	}
	
	@Override
	public PaUserWrong findPaUserWrongById(Integer id) {
		try {
			return paUserWrongDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PaUserWrong add(PaUserWrong paUserWrong) {
		if(paUserWrong == null) {
    		return null;
    	}
    	Date createDate = paUserWrong.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	paUserWrong.setCreateDate(createDate);
    	paUserWrong.setModifyDate(createDate);
		return paUserWrongDao.create(paUserWrong);
	}

	@Override
	public PaUserWrong modify(PaUserWrong paUserWrong) {
		if(paUserWrong == null) {
    		return null;
    	}
    	Date modify = paUserWrong.getModifyDate();
    	paUserWrong.setModifyDate(modify != null ? modify : new Date());
		return paUserWrongDao.update(paUserWrong);
	}
	
	@Override
	public void remove(PaUserWrong paUserWrong) {
		try {
			paUserWrongDao.delete(paUserWrong);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", paUserWrong.getId(), e);
			}
		}
	}
	
	@Override
	public List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Page page, Order order) {
		return paUserWrongDao.findPaUserWrongByCondition(paUserWrongCondition, page, order);
	}
	
	@Override
	public List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition) {
		return paUserWrongDao.findPaUserWrongByCondition(paUserWrongCondition, null, null);
	}
	
	@Override
	public List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Page page) {
		return paUserWrongDao.findPaUserWrongByCondition(paUserWrongCondition, page, null);
	}
	
	@Override
	public List<PaUserWrong> findPaUserWrongByCondition(PaUserWrongCondition paUserWrongCondition, Order order) {
		return paUserWrongDao.findPaUserWrongByCondition(paUserWrongCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.paUserWrongDao.count(null);
	}

	@Override
	public Long count(PaUserWrongCondition paUserWrongCondition) {
		return this.paUserWrongDao.count(paUserWrongCondition);
	}

	@Override
	public List<WrongPaperVo> findUserWrongList(Integer userId, String subjectCode, Page page) {
		return this.findUserWrongList(userId, subjectCode, page, null);
	}
	@Override
	public List<WrongPaperVo> findUserWrongList(Integer userId,
			String subjectCode, Page page, Order order) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		List<WrongPaperVo> wp=new ArrayList<WrongPaperVo>();
		wp=paUserWrongDao.findUserWrongList(userId, subjectCode, page, order);
		List<Integer> ids=new ArrayList<Integer>();
		List<Integer> ids1=new ArrayList<Integer>();
		for(WrongPaperVo wrongPaper:wp){
			if(wrongPaper.getParentId()!=null){
				ids.add(wrongPaper.getParentId());
				ids1.add(wrongPaper.getQuestionId());
			}
		}
		Map<Integer,String> map=new HashMap<Integer, String>();
		 Map<Integer, Object> map1=new HashMap<Integer, Object>();
		if(ids.size()>0){
        Integer[] idInt=new Integer[ids.size()];
        Integer[] idInt1=new Integer[ids.size()];
        int i=0;
        for(Integer ob:ids){
        	idInt[i]=ob;
        	i++;
        }
        i=0;
        for(Integer ob:ids1){
        	idInt1[i]=ob;
        	i++;
        }
       List<PaQuestion> pqlist= paQuestionService.findPaQuestionByIds(idInt);
       if(pqlist!=null&&pqlist.size()>0){
    	   for(PaQuestion pq:pqlist){
    		   map.put(pq.getId(), pq.getContent());
    	   }
       }
      map1=paQuestionService.findPaQuetionKnowledgeNodeNameByIds(idInt1);
  }
		
		for(WrongPaperVo wrongPaper:wp){
			String content = wrongPaper.getContent();
			String explanation = wrongPaper.getExplanation();
			if (explanation != null && !"".equals(explanation)) {

				wrongPaper.setExplanation(MqtPaperUtil.replaceDomain(explanation, domain));
			} else {
				wrongPaper.setExplanation("暂无解析!");
			}
			
			wrongPaper.setContent(MqtPaperUtil.replaceDomain(content, domain));
			String paperAnswer = wrongPaper.getAnswer();
			String correctAnswer = wrongPaper.getDbCorrectAnswer();
			String userAnswer = wrongPaper.getDbPaperAnswer();
			if(wrongPaper.getParentId()!=null&&wrongPaper.getParentId()!=0){
				wrongPaper.setComplex(true);
				wrongPaper.setComplexTitle(MqtPaperUtil.replaceDomain(map.get(wrongPaper.getParentId()), domain));
			}else{
				wrongPaper.setComplex(false);
				wrongPaper.setComplexTitle("");
			}
			wrongPaper.setPaperAnswer(MqtPaperUtil.StringToArray(paperAnswer, domain));
			wrongPaper.setCorrectAnswer(MqtPaperUtil.StringToArray(correctAnswer, domain));
			
			/*String[] userAwn={};
			if(userAnswer!=null){
				JSONArray array=JSONArray.fromObject(userAnswer);
				 userAwn=new String[array.size()];
				for(int i=0;i<array.size();i++){
					JSONObject obj=JSONObject.fromObject(array.get(i));
					String str=obj.getString("answer");
					if(str.length()>4){
						str=str.substring(2, str.length()-2);
					}
					userAwn[i]=str;
				}
			}*/
			String[] name={};
			wrongPaper.setUserAnswer(MqtPaperUtil.StringToArray(userAnswer, domain));
			if(map1.get(wrongPaper.getQuestionId())!=null){
				name=(String[]) map1.get(wrongPaper.getQuestionId());
			}
			wrongPaper.setKnowledges(name);
		}
		return wp;
	}
	@Override
	public Boolean redo(Integer userWrongId, String answers) throws Exception {

		Boolean flag = false;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode answerList = mapper.readValue(answers, JsonNode.class);

		// 根据userWrongId查询现有记录
			PaUserWrong userWrong = paUserWrongDao.findById(userWrongId);

		for (JsonNode answer : answerList) {
			String lastAnswer = answer.path("answer").toString();
			Integer isCorrect = null;
			if (answer.path("isCorrect") != null) {
				
				isCorrect = Integer.parseInt(answer.path("isCorrect").getTextValue());
			}
			// 判断答案对错
			if (isCorrect == 1) { // 如正确则在正答数加1
				userWrong.setRightCount(((userWrong.getRightCount()) == null ? 0 : userWrong.getRightCount()) + 1);
				userWrong.setIsCorrect(true);
			} else {// 如正确则在错答数加1
				userWrong.setWrongCount(((userWrong.getWrongCount()) == null ? 0 : userWrong.getWrongCount()) + 1);
				userWrong.setIsCorrect(false);
			}

			userWrong.setLastTime(new Date());
			userWrong.setLastAnswer(lastAnswer);
			paUserWrongDao.update(userWrong);
			flag = true;
		}

		return flag;
	}

}
