package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.CommonJson;
import platform.education.generalTeachingAffair.vo.CommonScoreRank;
import platform.education.generalTeachingAffair.vo.ExamStatVo;
import platform.education.generalTeachingAffair.vo.PjExamCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * @function 教务——成绩统计管理
 * @author hmzhang
 * @date 2016年1月25日
 */
@Controller 
@RequestMapping("/teach/scoreManagement")
public class ScoreManagement extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(ScoreManagement.class);
	
	private final static String viewBasePath = "/teach/scoreManagement";
	
	/**
	 * @function 用于成绩统计页面
	 * @param model
	 * @param examId 考试的id pj_exam的id
	 * @return 
	 * @date 2016年1月25日
	 */
	@RequestMapping(value = "/index")
	private ModelAndView index(
			Model model,
			@RequestParam(value = "examId", required = true)Integer examId
			){
		/*this.examStudentService.updateTeamRank(examId);
		
		this.examStudentService.updateGradeRank(new Integer[]{39,41,45});*/
		String examTitle = "";
		String term = "";
		Team team = null;
		Subject subject = null;
		//考试信息
		PjExam exam = pjExamService.findPjExamById(examId);
		if(exam!=null){
			term = exam.getTermValue().equals("1")?"上学期":"下学期";
			team = this.teamService.findTeamById(exam.getTeamId());
			String teamName = team!=null?team.getName():"";
			subject = this.subjectService.findUnique(exam.getSchoolId(), exam.getSubjectCode());
			String subjectName = subject!=null?subject.getName():"";
			Teacher teacher = this.teacherService.findTeacherById(exam.getTeacherId());
			String teacherName = teacher!=null?teacher.getName():"";
			List<Map<String,Object>> result = this.jcGcCacheService.findByCondition("XY-JY-KSLX", "value", exam.getExamType(), null,false);
			if(result.size()==1){
				Map<String,Object> map = result.get(0);
				String name = (String)map.get("name");
				examTitle = exam.getSchoolYear()+"学年"+term+teamName+name+"成绩统计";
				model.addAttribute("examTitle",examTitle);
			}
			model.addAttribute("teamName",teamName);
			model.addAttribute("subjectName", subjectName);
			model.addAttribute("examDate",exam.getExamDate());
			model.addAttribute("teacherName",teacherName);
			List<Float> list = new ArrayList<Float>();
			List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examId);
			Long nextExamStudent = 0L;
			for(ExamStudent es : examStudentList){
				if(es.getScore().floatValue()!=-1){
					list.add(es.getScore());
				}
				if(es.getTestType().equals("11")){
					nextExamStudent++;
				}
			}
			Float[] floatArray = new Float[list.size()];
			for (int i = 0; i < list.size(); i++) {
				floatArray[i] = list.get(i);
			}
			Arrays.sort(floatArray);
			ExamStat statTemp = this.examStatService.findExamStatByExamId(examId);
			ExamStatVo vo = new ExamStatVo();
			BeanUtils.copyProperties(statTemp, vo);
			if(floatArray.length>0){
				vo.setMin_score(floatArray[0]);
				vo.setMax_score(floatArray[floatArray.length-1]);
			}else{
			}
			vo.setNextExamStudent(nextExamStudent);
			model.addAttribute("examStatVo",vo);
		}else{
			
		}
		return new  ModelAndView(viewBasePath+"/score_index", model.asMap());
	}
	
	/**
	 * @function 获取饼图的数据
	 * @param user
	 * @param examId
	 * @return
	 * @date 2016年1月27日
	 */
	@RequestMapping(value = "/getScoreRate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Double> getScoreRate(@CurrentUser UserInfo user,@RequestParam(value = "examId", required = true)Integer examId){
		ExamStat statTemp = this.examStatService.findExamStatByExamId(examId);
		Map<String,Double> map = new HashMap<String,Double>();
		if(statTemp!=null){
			CommonScoreRank csr = this.examStudentService.countScoreRate(examId, statTemp.getFullScore(), statTemp.getHighScore(), statTemp.getLowScore(), statTemp.getPassScore());
			Long sumStudent = this.examStudentService.countTotalStudent(examId);
			if(sumStudent>0){
				if(csr==null){
					csr = new CommonScoreRank();
				}
				Long level1 = csr.getLevel1()!=null?csr.getLevel1():0L;
				Long level2 = csr.getLevel1()!=null?csr.getLevel2():0L;
				Long level3 = csr.getLevel1()!=null?csr.getLevel3():0L;
				Long level4 = csr.getLevel1()!=null?csr.getLevel4():0L;
				map.put(statTemp.getHighScore()+"-"+statTemp.getFullScore()+"分段("+ level1 +")人", (double) (level1*100/sumStudent));
				map.put(statTemp.getLowScore()+"-"+statTemp.getHighScore()+"分段("+ level2 +")人", (double) (level2*100/sumStudent));
				map.put(statTemp.getPassScore()+"-"+statTemp.getLowScore()+"分段("+ level3 +")人", (double) (level3*100/sumStudent));
				map.put(statTemp.getPassScore()+"分以下("+ level4 +")人", (double) (level4*100/sumStudent));
			}
		}
		return map;		
	}
	
	/**
	 * @function 获取柱状图的数据
	 * @param user
	 * @param examId
	 * @return
	 * @date 2016年1月27日
	 */
	@RequestMapping(value = "/getZhuScore", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonJson> getZhuScore(@CurrentUser UserInfo user,@RequestParam(value = "examId", required = true)Integer examId){
		ExamStat statTemp = this.examStatService.findExamStatByExamId(examId);
		List<CommonJson> list = new ArrayList<CommonJson>();
		if(statTemp!=null){
			CommonScoreRank csr = this.examStudentService.countScoreRate(examId, statTemp.getFullScore(), statTemp.getHighScore(), statTemp.getLowScore(), statTemp.getPassScore());
			Long sumStudent = this.examStudentService.countTotalStudent(examId);
			if(csr==null){
				csr = new CommonScoreRank();
			}
			Long level1 = csr.getLevel1()!=null?csr.getLevel1():0L;
			Long level2 = csr.getLevel1()!=null?csr.getLevel2():0L;
			Long level3 = csr.getLevel1()!=null?csr.getLevel3():0L;
			Long level4 = csr.getLevel1()!=null?csr.getLevel4():0L;
			Long level2Count = level1+level2;
			Long level3Count = level1+level2+level3;
			list.add(new CommonJson("优秀人数("+level1+")人",new Double[]{(double) (level1*100/sumStudent)}));
			list.add(new CommonJson("良好人数("+level2Count+")人",new Double[]{(double) (level2Count*100/sumStudent)}));
			list.add(new CommonJson("及格人数("+level3Count+")人",new Double[]{(double) (level3Count*100/sumStudent)}));
			list.add(new CommonJson("不及格人数("+level4+")人",new Double[]{(double) (level4*100/sumStudent)}));
		}
		return list;
	}
	
	/**
	 * @function 获取学生排名情况
	 * @param user
	 * @param examId
	 * @param order
	 * @param model
	 * @return
	 * @date 2016年1月28日
	 */
	@RequestMapping(value = "/getStudentRank", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getStudentRank(@CurrentUser UserInfo user,
			@RequestParam(value = "examId", required = true)Integer examId,
			Order order,Model model,
			@RequestParam(value = "sortItem", required = true)String sortItem){
		/**
		 * 更新学生的班级排名
		 */
		this.examStudentService.updateTeamRank(examId);
		PjExam exam = this.pjExamService.findPjExamById(examId);
		PjExamCondition pjExamCondition = new PjExamCondition();
		if(exam!=null){
			pjExamCondition.setSchoolId(exam.getSchoolId());
			pjExamCondition.setSchoolYear(exam.getSchoolYear());
			pjExamCondition.setTermCode(exam.getTermCode());
			pjExamCondition.setGradeId(exam.getGradeId());
			pjExamCondition.setSubjectCode(exam.getSubjectCode());
			pjExamCondition.setExamType(exam.getExamType());
			pjExamCondition.setExamRound(exam.getExamRound());
		}
		/**
		 * 获取和这次考试同批次的考试,为了年级排名
		 */
		List<PjExam> examList = this.pjExamService.findPjExamByCondition(pjExamCondition);
		Integer[] ids = new Integer[examList.size()];
		for(int i=0;i<examList.size();i++){
			ids[i]=examList.get(i).getId();
		}
		/**
		 * 跟新年级排名
		 */
		this.examStudentService.updateGradeRank(ids);
		order.setProperty(sortItem);
		order.setAscending(true);
		List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examId,order);
		model.addAttribute("examStudentList",examStudentList);
		return new  ModelAndView(viewBasePath+"/studentRankList", model.asMap());
	}
	
	
	/**
	 * @function 设置考试的属性，例如满分，最高分
	 * @param examId  考试的id
	 * @date 2016年1月28日
	 */
	@RequestMapping(value = "/setAttribute")
	@ResponseBody
	public ModelAndView setAttribute(@CurrentUser UserInfo user,
			@RequestParam(value = "examId", required = true)Integer examId,
			Model model){
		Team team = null;
		Subject subject = null;
		ExamStat statTemp = null;
		PjExam exam = pjExamService.findPjExamById(examId);
		if(exam!=null){
			team = this.teamService.findTeamById(exam.getTeamId());
			String teamName = team!=null?team.getName():"";
			subject = this.subjectService.findUnique(exam.getSchoolId(), exam.getSubjectCode());
			String subjectName = subject!=null?subject.getName():"";
			Teacher teacher = this.teacherService.findTeacherById(exam.getTeacherId());
			String teacherName = teacher!=null?teacher.getName():"";
			model.addAttribute("teamName",teamName);
			model.addAttribute("subjectName", subjectName);
			model.addAttribute("teacherName",teacherName);
			statTemp = this.examStatService.findExamStatByExamId(examId);
		}
		model.addAttribute("exam",exam);
		model.addAttribute("examStat",statTemp);
		return new  ModelAndView(viewBasePath+"/setAttribute", model.asMap());
	}
	
	
	/**
	 * @function 获取页面设置的数据，初始化pj_exam_stat表
	 * @param examStat
	 * @param model
	 * @param pjExam
	 * @return
	 * @date 2016年1月28日
	 */
	@RequestMapping(value = "/saveExamAttribute")
	@ResponseBody
	public ResponseInfomation saveExamAttribute(
			ExamStat examStat,
			Model model,
			PjExam pjExam
			){
		PjExam examTemp = null;
		ExamStat statTemp = null;
		try {
			if(examStat!=null && examStat.getExamId()!=null ){
				examTemp = this.pjExamService.findPjExamById(examStat.getExamId());
				if(pjExam!=null){
					examTemp.setExamDate(pjExam.getExamDate());
					examTemp.setExamRound(pjExam.getExamRound());
					examTemp.setExamType(pjExam.getExamType());
					this.pjExamService.modify(examTemp);
				}
				statTemp = this.examStatService.findExamStatByExamId(examStat.getExamId());
				statTemp.setFullScore(examStat.getFullScore());//总分
				statTemp.setHighScore(examStat.getHighScore());//优秀
				statTemp.setLowScore(examStat.getLowScore());//良好
				statTemp.setPassScore(examStat.getPassScore());//及格
				Long sumScore = this.examStudentService.countTeamTotalScore(examStat.getExamId());
				if(sumScore!=null){
					statTemp.setTotalScore((float)sumScore);//班级总分
				}else{
					statTemp.setTotalScore(0f);
				}
				Long sumStudent = this.examStudentService.countTotalStudent(examStat.getExamId());
				statTemp.setStudentCount(sumStudent.intValue());//班级总人数
				sumScore = sumScore==null?0:sumScore;
				sumStudent = sumStudent==null?0:sumStudent;
				if(sumScore.intValue()!=0 && sumStudent.intValue()!=0){
					List<Float> list = new ArrayList<Float>();
					List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examStat.getExamId());
					Long nextExamStudent = 0L;
					for(ExamStudent es : examStudentList){
						if(es.getScore().floatValue()!=-1){
							list.add(es.getScore());
						}
						if(es.getTestType().equals("11")){
							nextExamStudent++;
						}
					}
					Float[] floatArray = new Float[list.size()];
					for (int i = 0; i < list.size(); i++) {
						floatArray[i] = list.get(i);
					}
					Arrays.sort(floatArray);
					statTemp.setAverageScore((float)sumScore/sumStudent);//平均分
					statTemp.setSdScore(getAdScore(floatArray,(float)(sumScore/sumStudent)));//标准差
					statTemp.setMadValue(getMadValue(floatArray,(float)(sumScore/sumStudent)));//离差
					statTemp.setMovValue(floatArray[floatArray.length-1]-floatArray[0]);//极差
				}
				statTemp = this.examStatService.modify(statTemp);
			}
			return statTemp != null ? new ResponseInfomation(statTemp.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		} catch (Exception e) {
			log.info("初始成绩统计数据异常。。。");
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
	}
	
	/**
	 * @function 检测是否有重复的考试
	 * @param user
	 * @param examId
	 * @param examType
	 * @param examRound
	 * @return
	 * @date 2016年1月18日
	 */
	@RequestMapping(value = "/checkExam", method = RequestMethod.POST)
	@ResponseBody
	public boolean checker(
			@CurrentUser UserInfo user,
			@RequestParam("examId") Integer examId,
			@RequestParam("examType") String examType,
			@RequestParam("examRound") Integer examRound) {
		PjExam pe = this.pjExamService.findPjExamById(examId);
		Boolean flag = false;
		if(pe!=null){
			PjExam temp = this.pjExamService.findUnique(pe.getSchoolYear(), pe.getTermValue(), pe.getTeamId(), examType, examRound, pe.getSubjectCode());
			if(temp!=null){
				if(temp.getId().intValue()==examId){
					flag =  true;
				}else{
					flag = false;
				}
			}else{
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @function 重新统计
	 * @param examId
	 * @return
	 * @date 2016年2月1日
	 */
	@RequestMapping(value = "/reCount", method = RequestMethod.POST)
	@ResponseBody
	public String reCount(@RequestParam(value = "examId", required = true)Integer examId){
		ExamStat statTemp = null;
		try {
			statTemp = this.examStatService.findExamStatByExamId(examId);
			/**
			 * dataChanged为true，即数据发生了变化，需要重新统计
			 */
			if(statTemp!=null && statTemp.getDataChanged()==true){
				Long sumScore = this.examStudentService.countTeamTotalScore(examId);
				if(sumScore != null){
					statTemp.setTotalScore((float)sumScore);//班级总分
				}else{
					statTemp.setTotalScore(0F);//班级总分
				}
				
				Long sumStudent = this.examStudentService.countTotalStudent(examId);
				statTemp.setStudentCount(sumStudent.intValue());//班级总人数
				sumScore = sumScore==null?0:sumScore;
				sumStudent = sumStudent==null?0:sumStudent;
				if(sumScore.intValue()!=0 && sumStudent.intValue()!=0){
					List<Float> list = new ArrayList<Float>();
					List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examId);
					for(ExamStudent es : examStudentList){
						if(es.getScore().floatValue()!=-1){
							list.add(es.getScore());
						}
					}
					Float[] floatArray = new Float[list.size()];
					for (int i = 0; i < list.size(); i++) {
						floatArray[i] = list.get(i);
					}
					Arrays.sort(floatArray);
					statTemp.setAverageScore((float)sumScore/sumStudent);//平均分
					statTemp.setSdScore(getAdScore(floatArray,(float)(sumScore/sumStudent)));//标准差
					statTemp.setMadValue(getMadValue(floatArray,(float)(sumScore/sumStudent)));//离差
					statTemp.setMovValue(floatArray[floatArray.length-1]-floatArray[0]);//极差
				}
				statTemp = this.examStatService.modify(statTemp);
			}
			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
	}
	
	/**
	 * @function 获取标准差
	 * @param array
	 * @return
	 * @date 2016年1月26日
	 */
	private Float getAdScore(Float[] array,Float avg){
		double sum = 0f;
        for(int i = 0;i < array.length;i++){
            sum += Math.sqrt(((double)array[i] -avg) * (array[i] -avg));
        }
        //return (float)(sum / (array.length - 1));
        return (float)(sum / (array.length));
	}
	
	/**
	 * @function 获取离差
	 * @param array
	 * @param avg
	 * @return
	 * @date 2016年1月26日
	 */
	private Float getMadValue(Float[] array,Float avg){
		Float date = 0f;
		for(int i = 0;i < array.length;i++){
			date += array[i]-avg;
		}
		return date;
	}
	
}
