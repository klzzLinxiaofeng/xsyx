package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.dao.MoralEvaluationStudentDao;
import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
import platform.education.generalTeachingAffair.service.MoralEvaluationResultService;
import platform.education.generalTeachingAffair.service.MoralEvaluationStudentService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MoralEvaluationStudentServiceImpl implements MoralEvaluationStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MoralEvaluationStudentDao moralEvaluationStudentDao;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("moralEvaluationResultService")
	private MoralEvaluationResultService moralEvaluationResultService;

	public void setMoralEvaluationStudentDao(MoralEvaluationStudentDao moralEvaluationStudentDao) {
		this.moralEvaluationStudentDao = moralEvaluationStudentDao;
	}
	
	@Override
	public MoralEvaluationStudent findMoralEvaluationStudentById(Integer id) {
		try {
			return moralEvaluationStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MoralEvaluationStudent add(MoralEvaluationStudent moralEvaluationStudent) {
		if(moralEvaluationStudent == null) {
    		return null;
    	}
    	Date createDate = moralEvaluationStudent.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	moralEvaluationStudent.setCreateDate(createDate);
    	moralEvaluationStudent.setModifyDate(createDate);
		return moralEvaluationStudentDao.create(moralEvaluationStudent);
	}

	@Override
	public MoralEvaluationStudent modify(MoralEvaluationStudent moralEvaluationStudent) {
		if(moralEvaluationStudent == null) {
    		return null;
    	}
    	Date modify = moralEvaluationStudent.getModifyDate();
    	moralEvaluationStudent.setModifyDate(modify != null ? modify : new Date());
		return moralEvaluationStudentDao.update(moralEvaluationStudent);
	}
	
	@Override
	public void remove(MoralEvaluationStudent moralEvaluationStudent) {
		 moralEvaluationStudentDao.delete(moralEvaluationStudent);
	}
	
	@Override
	public List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order) {
		return moralEvaluationStudentDao.findMoralEvaluationStudentByCondition(moralEvaluationStudentCondition, page, order);
	}
	
	@Override
	public List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition) {
		return moralEvaluationStudentDao.findMoralEvaluationStudentByCondition(moralEvaluationStudentCondition, null, null);
	}
	
	@Override
	public List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page) {
		return moralEvaluationStudentDao.findMoralEvaluationStudentByCondition(moralEvaluationStudentCondition, page, null);
	}
	
	@Override
	public List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Order order) {
		return moralEvaluationStudentDao.findMoralEvaluationStudentByCondition(moralEvaluationStudentCondition, null, order);
	}
	
	/**
	 * 功能描述： 关联查询出班级名称以及学生姓名
	 */
	@Override
	public List<MoralEvaluationStudentVo> findMoralEvaluationStudentByConditionMore(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order) {
		return moralEvaluationStudentDao.findMoralEvaluationStudentByConditionMore(moralEvaluationStudentCondition, page, order);
	}
	
	@Override
	public Long count() {
		return this.moralEvaluationStudentDao.count(null);
	}

	@Override
	public Long count(MoralEvaluationStudentCondition moralEvaluationStudentCondition) {
		return this.moralEvaluationStudentDao.count(moralEvaluationStudentCondition);
	}

	@Override
	public void remove(MoralEvaluationStudentCondition moralEvaluationStudentCondition) {
		this.moralEvaluationStudentDao.deleteByCondition(moralEvaluationStudentCondition);
	}

	/**
	 * 功能描述： 通过teamId(对应于pj_team.id)、studentId(对应于pj_student.id)查找唯一未删除的记录
	 */
	@Override
	public MoralEvaluationStudent findUnique(Integer teamId, Integer studentId) {
		try {
			return moralEvaluationStudentDao.findUnique(teamId, studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String abandon(MoralEvaluationStudent moralEvaluationStudent) {
		if(moralEvaluationStudent != null){
			moralEvaluationStudent.setIsDelete(true);
			try {
				moralEvaluationStudent = this.moralEvaluationStudentDao.update(moralEvaluationStudent);
				if(moralEvaluationStudent != null){
					Integer evaluationId = moralEvaluationStudent.getId();
					MoralEvaluationResultCondition condition = new MoralEvaluationResultCondition();
					condition.setEvaluationId(evaluationId);
					this.moralEvaluationResultService.remove(condition);
					return MoralEvaluationStudentService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()){
					log.info("废弃 -> {} 失败，异常信息为 {}", moralEvaluationStudent.getId(), e);
				}
				return MoralEvaluationStudentService.OPERATE_ERROR;
			}
		}
		return MoralEvaluationStudentService.OPERATE_FAIL;
	}

	@Override
	public String addMoralEvaluationResult(
			Integer schoolId,
			MoralEvaluationStudent moralEvaluationStudent,
			List<MoralEvaluationResult> moralEvaluationResults) {
		
		MoralEvaluationStudent moralEvaluationStudentTemp = new MoralEvaluationStudent();
		MoralEvaluationStudentCondition condition = new MoralEvaluationStudentCondition();
		
		Integer teamId = moralEvaluationStudent.getTeamId();
		Integer studentId = moralEvaluationStudent.getStudentId();
		
		Integer evaluationId = null;
//		if(teamStudent != null){
			
		moralEvaluationStudentTemp.setTotalEvaluation(moralEvaluationStudent.getTotalEvaluation());  //总评价
		moralEvaluationStudentTemp.setRemark(moralEvaluationStudent.getRemark());  //备注
		moralEvaluationStudentTemp.setIsDelete(false);
		moralEvaluationStudentTemp.setModifyDate(new Date());
			
			//对已经进行过评价的学生做更新操作，否则添加
			condition.setTeamId(teamId);
			condition.setStudentId(studentId);
			condition.setIsDelete(false);
			List<MoralEvaluationStudent> mes = this.moralEvaluationStudentDao.findMoralEvaluationStudentByCondition(condition, null, null);
			if(mes.size() > 0){
				//查找出表pj_moral_evaluation_student中关于这个学生的评价
				MoralEvaluationStudent student = this.moralEvaluationStudentDao.findUnique(teamId, studentId);
				
				//表pj_moral_evaluation_student.id
				evaluationId = student.getId();  
				
				moralEvaluationStudentTemp.setId(student.getId());
				moralEvaluationStudentTemp.setSchoolId(schoolId);
//				moralEvaluationStudentTemp.setCreateDate(student.getCreateDate());
				moralEvaluationStudentTemp.setCreateDate(new Date());
				moralEvaluationStudentTemp = this.moralEvaluationStudentDao.update(moralEvaluationStudentTemp);
				
				//查询出pj_moral_evaluationResoult表中已经存在的对这个学生的评价
				MoralEvaluationResultCondition merCondition = new MoralEvaluationResultCondition();
				merCondition.setEvaluationId(evaluationId);
				List<MoralEvaluationResult> mers = this.moralEvaluationResultService.findMoralEvaluationResultByCondition(merCondition);
				for(MoralEvaluationResult mer : mers){
					this.moralEvaluationResultService.remove(mer);
				}
				
			}else{
				moralEvaluationStudentTemp.setTeamId(teamId);
				moralEvaluationStudentTemp.setStudentId(studentId);
				moralEvaluationStudentTemp.setSchoolId(schoolId);
				moralEvaluationStudentTemp.setCreateDate(new Date());
				moralEvaluationStudentTemp = this.moralEvaluationStudentDao.create(moralEvaluationStudentTemp);
				if(moralEvaluationStudentTemp != null){
					evaluationId = moralEvaluationStudentTemp.getId();
				}
			}
			
			if(moralEvaluationStudentTemp != null) {
			if(moralEvaluationResults.size() > 0){
				for(MoralEvaluationResult moralEvaluationResult : moralEvaluationResults){
					moralEvaluationResult.setEvaluationId(evaluationId);
					moralEvaluationResult = this.moralEvaluationResultService.add(moralEvaluationResult);
				}
			}
			}
//		}
		
		if(moralEvaluationStudentTemp != null){
			return MoralEvaluationStudentService.OPERATE_SUCCESS;
		}else{
			return MoralEvaluationStudentService.OPERATE_FAIL;
		}
		
	}

	@Override
	public String modifyMoralEvaluationResult(Integer id,
			MoralEvaluationStudent moralEvaluationStudent,
			List<MoralEvaluationResult> moralEvaluationResults) {
		
		MoralEvaluationStudent moralEvaluationStudentTemp = new MoralEvaluationStudent();
		
		BeanUtils.copyProperties(moralEvaluationStudent, moralEvaluationStudentTemp);
		moralEvaluationStudentTemp = this.moralEvaluationStudentDao.update(moralEvaluationStudent);
		
		//查询出pj_moral_evaluationResoult表中已经存在的对这个学生的评价
		MoralEvaluationResultCondition merCondition = new MoralEvaluationResultCondition();
		merCondition.setEvaluationId(id);
		List<MoralEvaluationResult> mers = this.moralEvaluationResultService.findMoralEvaluationResultByCondition(merCondition);
		for(MoralEvaluationResult mer : mers){
			this.moralEvaluationResultService.remove(mer);
		}
		
		if(moralEvaluationStudentTemp != null) {
		if(moralEvaluationResults.size() > 0){
			for(MoralEvaluationResult moralEvaluationResult : moralEvaluationResults){
				moralEvaluationResult = this.moralEvaluationResultService.add(moralEvaluationResult);
			}
		}
		}
		
		if(moralEvaluationStudentTemp != null){
			return MoralEvaluationStudentService.OPERATE_SUCCESS;
		}else{
			return MoralEvaluationStudentService.OPERATE_FAIL;
		}
	}
	
}
