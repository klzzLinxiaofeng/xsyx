/**   
* @Title: PaperRestServiceImpl.java
* @Package platform.education.rest.paper.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月24日 下午1:51:45 
* @version V1.0   
*/
package platform.education.rest.paper.service.impl;

import java.text.ParseException;
import java.util.*;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONObject;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.UserDetailInfo;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.ExamQuestionWrongVo;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.paper.model.PaUserWrong;
import platform.education.paper.service.PaUserWrongService;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.WrongPaperVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.exam.service.ExamRestService;
import platform.education.rest.paper.service.PaperWrongQuestionRestService;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

/** 
* @ClassName: PaperRestServiceImpl 
* @Description: 错题本对外暴露业务接口实现类 
* @author pantq
* @date 2017年2月24日 下午1:51:45 
*  
*/
public class PaperWrongQuestionRestServiceImpl implements PaperWrongQuestionRestService{
	
	@Autowired
	@Qualifier("paUserWrongService")
	private PaUserWrongService paUserWrongService;
	
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

    @Autowired
    @Qualifier("pjExamService")
    private PjExamService pjExamService;

	@Autowired
	@Qualifier("fileService")
	private FileService fileService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;
	/* (非 Javadoc) 
	* <p>Title: userWronglist</p> 
	* <p>Description: </p> 
	* @param userId
	* @param appKey
	* @param page
	* @param index
	* @param subjectCode
	* @return
	* @throws ParseException 
	* @see platform.education.rest.paper.service.PaperRestService#userWronglist(java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String) 
	*/
	@Override
	public Object userWrongList(Integer userId, String appKey, Integer pageSize, Integer pageIndex, String subjectCode)
			throws ParseException {
		
		 ResponseInfo info = new ResponseInfo();
		 ResponseError responseError = new ResponseError();
		 List<WrongPaperVo> wrongPaperList = null; 
		 List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
		try{
			
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(subjectCode.equals("-1")){
				subjectCode=null;
			}
			Page page = new Page();
			page.setCurrentPage(pageIndex == null ? 1:pageIndex);
			page.setPageSize(pageSize== null ? 10:pageSize);
			page.setEnableGetTolaRows(false);
			wrongPaperList = paUserWrongService.findUserWrongList(userId, subjectCode, page);
			
			if(wrongPaperList!=null){
				for(WrongPaperVo vo:wrongPaperList){
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("questionUuId", vo.getQuestionUuId());
					map.put("userWrongId", vo.getUserWrongId());
					map.put("questionType", vo.getQuestionType());
					map.put("content", vo.getContent());
					map.put("complex", vo.isComplex());
					map.put("complexTitle", vo.getComplexTitle());
					map.put("correctAnswer", vo.getCorrectAnswer());
					map.put("paperAnswer", vo.getPaperAnswer());
					map.put("explanation", vo.getExplanation());
//					map.put("userAnswer", vo.getUserAnswer());
					map.put("userAnswer", MqtPaperUtil.fixOldDate(vo.getDbPaperAnswer(), vo.getQuestionType(), vo.getContent(), vo.getQuestionUuId()));
//					JSONArray ja=JSONArray.fromObject(vo.getDbPaperAnswer());
//					map.put("lastAnswer", ja);
					map.put("wrongCount", vo.getWrongCount());
					map.put("rightCount", vo.getRightCount());
					map.put("isCorrect", vo.getIsCorrect());
					map.put("totalTime", vo.getTotalTime());
					map.put("averageTime", vo.getAverageTime());
					map.put("rightAnswerRight", vo.getRightAnswerRight());
					map.put("difficulity", vo.getDifficulityFloat());
					map.put("number", vo.getNumber());
					map.put("paperTitle", vo.getPaperTitle());
//					map.put("lastAnswer", vo.getDbPaperAnswer());
					map.put("knowledges", vo.getKnowledges());
					map.put("score", vo.getScore());
					list.add(map);
				}
			}
			responseError.setResult("0");
		}catch(Exception e){
			e.printStackTrace();
			info.setDetail("移动端获取错题本异常");
	        info.setMsg("未知错误");
	        responseError.setResult("000001");
	        responseError.setInfo(info);
	        return responseError;
			
		}
		
		return new ResponseVo<Object>("0",list);
	}
	@Override
	public Object userWrongSelect(Integer userId, String appKey, String subjectCode, Integer teamId)
			throws ParseException {

 		 ResponseInfo info = new ResponseInfo();
		 ResponseError responseError = new ResponseError();
		 List<WrongPaperVo> wrongPaperList = null;
		 List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
        Map<String, Map<String, Object>> result = new HashMap<>();
		try{

			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(subjectCode.equals("-1")){
				subjectCode=null;
			}
			Page page = new Page();
			page.setPageSize(5);
			wrongPaperList = paUserWrongService.findUserWrongList(userId, subjectCode, page, new Order("rand()"));
            Team team = teamService.findTeamById(teamId);
			if(team == null ||  team.getGradeId() == null){
                info = new ResponseInfo();
                info.setDetail("获取班级信息失败,请确认");
                info.setMsg("班级");
                info.setParam("teamId");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            Integer gradeId = team.getGradeId();
//			page = new Page();
//            page.setPageSize(5);
//			List<ExamQuestionWrongVo> examQuestionWrongVos = pjExamService.findExamQuestionWrongForStudent(
//			        gradeId, null, subjectCode, 2, 1f,
//                     null);
//
//			String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
//			int sWrong = wrongPaperList.size();
//			int total = examQuestionWrongVos.size() + sWrong;
			while (result.size() < 5){
				int index = -1;
				index = (Math.abs(new Random(userId+new Date().getTime()).nextInt()))%9;

				if(index >= wrongPaperList.size()){
					List<ExamQuestionWrongVo> examQuestionWrongVos = pjExamService.findExamQuestionWrongForStudent(
							gradeId,  null, subjectCode, 2, 1f,
							null);
					if(examQuestionWrongVos.isEmpty() || examQuestionWrongVos.size() < 1) continue;
					ExamQuestionWrongVo wrongVo = examQuestionWrongVos.get(0);
					List<ExamQuestionWrongVo> wrongVos = new ArrayList<>();
					wrongVos.add(wrongVo);
					List<Map<String, Object>> data = copy(wrongVos, 2);
					result.put(wrongVo.getQuestionUuid(), data.size()>0? data.get(0) : null);
                }else{
                    WrongPaperVo wrongVo = wrongPaperList.get(index);
                    List<WrongPaperVo> wrongVos = new ArrayList<>();
                    wrongVos.add(wrongVo);
                    List<Map<String, Object>> data = copy(wrongVos);
                    result.put(wrongVo.getQuestionUuId(), data.size()>0? data.get(0) : null);
                    wrongPaperList.remove(index);
                }
//                total--;

			}
			responseError.setResult("0");
		}catch(Exception e){
			e.printStackTrace();
			info.setDetail("移动端获取错题本异常");
	        info.setMsg("未知错误");
	        responseError.setResult("000001");
	        responseError.setInfo(info);
	        return responseError;

		}

		return new ResponseVo<Object>("0", result.values());
	}

    private List<Map<String, Object>> copy(Collection<ExamQuestionWrongVo> list, Integer type) {
        String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (ExamQuestionWrongVo vo : list) {
            map = new HashMap<String, Object>();
            map.put("questionUuId", empty(vo.getQuestionUuid()));
            map.put("difficulity", vo.getDifficulity());
            if (type == 1) {
                map.put("scoringRate", vo.getTeamScoringRate());
            } else {
                map.put("scoringRate", vo.getGradeScoringRate());
            }
            map.put("questionType", empty(vo.getQuestionType()));
            if (vo.getGroupId() != null) {
                map.put("isComplex", true);
            } else {
                map.put("isComplex", false);
            }

            String complexTitle = vo.getComplexTitle();
            complexTitle = MqtPaperUtil.replaceDomain(complexTitle, domain);
            map.put("complexTitle", complexTitle);
            map.put("paperAnswer", MqtPaperUtil.StringToArray(vo.getAnswer(), domain));
            map.put("correctAnswer", MqtPaperUtil.StringToArray(vo.getCorrectAnswer(), domain));
            map.put("explanation", MqtPaperUtil.replaceDomain(vo.getExplanation(), domain));
            map.put("content", MqtPaperUtil.replaceDomain(vo.getContent(), domain));
            maplist.add(map);
        }

        return maplist;
    }

    private List<Map<String, Object>> copy(Collection<WrongPaperVo> list) {
        String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (WrongPaperVo vo : list) {
            map = new HashMap<String, Object>();
            map.put("questionUuId", empty(vo.getQuestionUuId()));
            map.put("difficulity", vo.getDifficulity() == null?0f:vo.getDifficulity());
            map.put("questionType", empty(vo.getQuestionType()));

            String complexTitle = vo.getComplexTitle();
            complexTitle = MqtPaperUtil.replaceDomain(complexTitle, domain);
            map.put("complexTitle", complexTitle);
            map.put("paperAnswer", MqtPaperUtil.StringToArray(vo.getAnswer(), domain));
            map.put("explanation", MqtPaperUtil.replaceDomain(vo.getExplanation(), domain));
            map.put("content", MqtPaperUtil.replaceDomain(vo.getContent(), domain));
            map.put("userWrongId", vo.getUserWrongId());
            maplist.add(map);
        }

        return maplist;
    }
    public String empty(String obj) {
        if (obj == null) {
            return "";
        } else {
            return obj;
        }

    }

	/* (非 Javadoc) 
	* <p>Title: redo</p> 
	* <p>Description: </p> 
	* @param userId
	* @param appKey
	* @param answers
	* @return
	* @throws ParseException 
	* @see platform.education.rest.paper.service.PaperRestService#redo(java.lang.Integer, java.lang.String, java.lang.String) 
	*/
	@Override
	public Object redo(Integer userWrongId, String appKey, String answers) throws ParseException {
	    /** 题目推送使用教师错题本数据，此时userWrongId为0，
         * 不需要保存，直接返回成功提示 */
	    if(userWrongId == 0){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", true);
            return new ResponseVo<Object>("0",map);
        }
	
		 ResponseInfo info = new ResponseInfo();
		 ResponseError responseError = new ResponseError();
		 try {
			 AppEdition app = this.appEditionService.findByAppKey(appKey);
				if(app == null){
					info = new ResponseInfo();
					info.setDetail("appkey不存在,请确认");
					info.setMsg("不存在该appKey");
					info.setParam("appKey");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
			 
				boolean flag =  paUserWrongService.redo(userWrongId, answers);
				 if(flag){
					 responseError.setResult("0");
				 }
			
		} catch (Exception e) {
			e.printStackTrace();
		 	info.setDetail("移动端上传用户作答答案异常");
	        info.setMsg("未知错误");
	        responseError.setResult("000001");
	        responseError.setInfo(info);
	        return responseError;
		}
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
		
	}

	/* (非 Javadoc) 
	* <p>Title: delete</p> 
	* <p>Description: </p> 
	* @param userWrongId
	* @param appKey
	* @return
	* @throws ParseException 
	* @see platform.education.rest.paper.service.PaperRestService#delete(java.lang.Integer, java.lang.String) 
	*/
	@Override
	public Object delete(Integer userWrongId, String appKey) throws ParseException {
		 ResponseInfo info = new ResponseInfo();
		 ResponseError responseError = new ResponseError();
		 try {
			 
			 AppEdition app = this.appEditionService.findByAppKey(appKey);
				if(app == null){
					info = new ResponseInfo();
					info.setDetail("appkey不存在,请确认");
					info.setMsg("不存在该appKey");
					info.setParam("appKey");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
				PaUserWrong uw=new PaUserWrong();
				uw=paUserWrongService.findPaUserWrongById(userWrongId);
               paUserWrongService.remove(uw);
					
		} catch (Exception e) {
			e.printStackTrace();
		 	info.setDetail("移动端删除错题本错误");
	        info.setMsg("未知错误");
	        responseError.setResult("000001");
	        responseError.setInfo(info);
	        return responseError;
		}
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
	}
	
	
}