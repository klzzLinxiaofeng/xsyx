package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.SchoolDao;
import platform.education.generalTeachingAffair.dao.SchoolUserDao;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolSystemService;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.generalcode.service.GradeService;
import platform.education.generalcode.vo.SubjectCondition;
import platform.education.user.dao.AppGroupDao;
import platform.education.user.dao.GroupDao;
import platform.education.user.model.AppGroup;
import platform.education.user.model.Group;
import platform.education.user.model.User;
import platform.education.user.service.AppGroupService;
import platform.education.user.service.GroupAdminService;
import platform.education.user.service.GroupService;
import platform.education.user.service.RoleService;
import platform.education.user.vo.GroupCondition;

public class SchoolServiceImpl implements SchoolService {

	private Logger log = LoggerFactory.getLogger(getClass());
	

	private GroupService groupService;
	//app_group
	private AppGroupService appGroupService;
	//教务平台科目
	private SubjectService subjectService;
	//角色
	private RoleService roleService;
	//学制
	private SchoolSystemService schoolSystemService;
	//基础平台年级
	private GradeService jcGradeService;
	//基础平台科目
	private platform.education.generalcode.service.SubjectService jcSubjectService;
	
	private SchoolDao schoolDao;

	private GroupDao groupDao;

	private AppGroupDao appGroupDao;
	
	private SchoolUserDao schoolUserDao;

	//年级科目表 （pj_subject_grade）
	private SubjectGradeService subjectGradeService;
		
	public SubjectGradeService getSubjectGradeService() {
		return subjectGradeService;
	}

	public void setSubjectGradeService(SubjectGradeService subjectGradeService) {
		this.subjectGradeService = subjectGradeService;
	}

	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setAppGroupDao(AppGroupDao appGroupDao) {
		this.appGroupDao = appGroupDao;
	}



	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public AppGroupService getAppGroupService() {
		return appGroupService;
	}

	public void setAppGroupService(AppGroupService appGroupService) {
		this.appGroupService = appGroupService;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public SchoolSystemService getSchoolSystemService() {
		return schoolSystemService;
	}

	public void setSchoolSystemService(SchoolSystemService schoolSystemService) {
		this.schoolSystemService = schoolSystemService;
	}

	public GradeService getJcGradeService() {
		return jcGradeService;
	}

	public void setJcGradeService(GradeService jcGradeService) {
		this.jcGradeService = jcGradeService;
	}

	public platform.education.generalcode.service.SubjectService getJcSubjectService() {
		return jcSubjectService;
	}

	public void setJcSubjectService(
			platform.education.generalcode.service.SubjectService jcSubjectService) {
		this.jcSubjectService = jcSubjectService;
	}

	private GroupAdminService groupAdminService;

	public GroupAdminService getGroupAdminService() {
		return groupAdminService;
	}

	public void setGroupAdminService(GroupAdminService groupAdminService) {
		this.groupAdminService = groupAdminService;
	}

	public List<School> getSchoolOfRegion(Integer appId, String regionCode, Page page, Order order) {
		List<School> schoolList = schoolDao.findSchoolOfRegionByCondition(appId, regionCode, page, order);
		return schoolList;
	}

	@Override
	public School findSchoolById(Integer id) {
		try {
			return schoolDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public School add(School school) {
		return schoolDao.create(school);
	}

	
	/**
	 * 新增学校
	 * @throws Exception 
	 */
	@Override
	public School add(final School school,final Integer sys_appId,final TaskExecutor taskExcutor) throws Exception{
		//第一步，新增学校基本信息
		final School sc = schoolDao.create(school);
		taskExcutor.execute(new Runnable() {
			public void run() {
		try{
			//第二步，将学校ID放到Group表中
			Group group = new Group();
			group.setCreateDate(new Date());
			group.setModifyDate(new Date());
			group.setOwnerId(sc.getId());
			group.setName(school.getName());
			group.setType("1");
			Group g = groupService.add(group);
			
			//第三步 设置GroupId 放到 app_group
			AppGroup appGroup = new AppGroup();
			appGroup.setCreateDate(new Date());
			appGroup.setModifyDate(new Date());
			appGroup.setGroupId(g.getId());
			appGroup.setAppId(sys_appId);
			appGroupService.add(appGroup);
			
			//第四步，设置学段
			//(1)学段
			String stageScope = school.getStageScope();
			//(2)学制
			String ss = school.getSchoolSystem();
			String xz="";
			if(ss=="1" || "1".equals(ss)){
				xz ="6-3-3";
			}else if(ss=="2" || "2".equals(ss)){
				xz = "5-4-3";
			}
			String[] stage = stageScope.split(",");
			String[] schoolSys = xz.split("-");
			List<List<platform.education.generalcode.model.Grade>> ll = getGradeVoList(stage,schoolSys);
			for(int k=0;k<ll.size();k++){
				List<platform.education.generalcode.model.Grade> gg = ll.get(k);
				for(int a=0;a<gg.size();a++){
					platform.education.generalcode.model.Grade jcg = gg.get(a);
					SchoolSystem schoolSystem = new SchoolSystem();
					schoolSystem.setCreateDate(new Date());
					schoolSystem.setModifyDate(new Date());
					schoolSystem.setGradeCode(jcg.getCode());
					schoolSystem.setStageCode(jcg.getStageCode());
					schoolSystem.setSchoolId(sc.getId());
					schoolSystem.setGradeName(jcg.getName());
					schoolSystem.setGradeNumber(jcg.getGradeNumber());
					schoolSystemService.add(schoolSystem);
				}
			}
			//第五步，同步科目
			List<platform.education.generalcode.model.Subject> jjList = getJcSubjectListByStage(stage);
			for(int c=0;c<jjList.size();c++){
				Subject subject = new Subject();
				platform.education.generalcode.model.Subject s = jjList.get(c);
				subject.setCode(String.valueOf(s.getCode()));
				subject.setCreateDate(new Date());
				subject.setModifyDate(new Date());
				subject.setStageCode(s.getStageCode());
//				subject.setStageCode(stageScope);    //设置为学校学段
				subject.setSubjectCharacter(s.getSubjectCharacter());
				subject.setSubjectClass(s.getSubjectClass());
				subject.setSubjectType(s.getSubjectType());
				subject.setSchoolId(sc.getId());
				subject.setIsDelete(false);
				subject.setName(s.getName());
				subjectService.add(subject);
			}
			
			/* 同步科目成功后设置年级科目 于2017-1-9迁移，之前是在控制器*/
			platform.education.generalTeachingAffair.vo.SubjectCondition subjectCondition = new platform.education.generalTeachingAffair.vo.SubjectCondition();
			subjectCondition.setSchoolId(sc.getId());
			subjectCondition.setIsDelete(false);
			List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
			for(Subject subject : subjectList) {
				setSubjectGrade(subject);
			}
			
			//第六步 设置角色
			roleService.addDefaultRolesToGroup(g.getId(), sys_appId);

			//2018-05-05 新建校时自动创建“运营人员账号”
			User user = new User();
			user.setPassword("123456");
			user.setState("0");
			groupAdminService.addDefautGroupAdmin(user, g.getId(), sys_appId, "2");

		}catch(Exception e){
			log.info("添加学校信息异常");
			e.printStackTrace();
		}
		}
		});
		return sc;
	}
	
	/**
	 * 同步基础课程后，分别设置年级科目（2015-10-23）
	 * @param subject
	 */
	public List<SubjectGrade> setSubjectGrade(Subject subject) {
		String[] stageCodes = subject.getStageCode().split(",");
		List<SchoolSystem> schoolSystemList = new ArrayList<SchoolSystem>();
		List<SubjectGrade> subjectGradeList = new ArrayList<SubjectGrade>();
		SchoolSystemCondition schoolSystemCondition;
		for(String stageCode : stageCodes) {
			schoolSystemCondition = new SchoolSystemCondition();
			schoolSystemCondition.setSchoolId(subject.getSchoolId());
			schoolSystemCondition.setStageCode(stageCode);
			schoolSystemList = schoolSystemService.findSchoolSystemByCondition(schoolSystemCondition);
			for(SchoolSystem schoolSystem : schoolSystemList) {
				SubjectGrade subjectGrade = new SubjectGrade();
				subjectGrade.setSchoolId(subject.getSchoolId());
				subjectGrade.setStageCode(schoolSystem.getStageCode());  //学段Code
				subjectGrade.setGradeCode(schoolSystem.getGradeCode());  //年级Code
				subjectGrade.setSubjectCode(subject.getCode());          //科目Code
				subjectGrade.setSubjectName(subject.getName());          //科目名称
				subjectGrade.setDelete(false);
				subjectGrade.setCreateDate(new Date()); 
				subjectGrade.setModifyDate(new Date());
				subjectGrade = subjectGradeService.add(subjectGrade);
				if(subjectGrade != null) {
					subjectGradeList.add(subjectGrade);
				}
			}
		}
		return subjectGradeList;
	}
	
	public List<List<platform.education.generalcode.model.Grade>> getGradeVoList(String[] stage,String[] schoolSys){
		List<List<platform.education.generalcode.model.Grade>> gradeList_ = new ArrayList<List<platform.education.generalcode.model.Grade>>();
		for(int i=0;i<stage.length;i++){
			String stage_temp = stage[i].toString();
			String schoolSys_temp = schoolSys[i].toString();
			List<platform.education.generalcode.model.Grade> gradeList = jcGradeService.findGradeListByStageAndLength(stage_temp,schoolSys_temp);
			gradeList_.add(gradeList);
		}
		return gradeList_;
	}
	
	public List<platform.education.generalcode.model.Subject> getJcSubjectListByStage(String[] stage){
		List<platform.education.generalcode.model.Subject> subjectList_ = new ArrayList<platform.education.generalcode.model.Subject>();
		//List<platform.education.generalcode.model.Subject> jcSubList = jcSubjectService.findAll();
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSubjectClass("1");
		List<platform.education.generalcode.model.Subject> jcSubList = jcSubjectService.findSubjectByCondition(subjectCondition, null, null);
		for(int i=0;i<jcSubList.size();i++){
			platform.education.generalcode.model.Subject subject = jcSubList.get(i);
			String stage_temp = subject.getStageCode();
			platform.education.generalcode.model.Subject subjectTemp = null;
			for(int b=0;b<stage.length;b++){
				subjectTemp = new platform.education.generalcode.model.Subject();
				String stageStr = stage[b];
				if(stage_temp.contains(stageStr)){
					/*subjectTemp.setStageCode(stageStr);
					subjectTemp.setName(subject.getName());
					subjectTemp.setCode(subject.getCode());
					subjectTemp.setSubjectCharacter(subject.getSubjectCharacter());
					subjectTemp.setSubjectClass(subject.getSubjectClass());
					subjectTemp.setSubjectType(subject.getSubjectType());
					//subject.setStageCode(stageStr);
					subjectList_.add(subjectTemp);*/
					
					subjectList_.add(subject);
					break;
				}
			}
		}
		return subjectList_;
	}
	
	
	@Override
	public School modify(School school) {
		return schoolDao.update(school);
	}

	@Override
	public void remove(School school) {
		try {
			schoolDao.delete(school);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
	}

	@Override
	public List<School> findSchoolByName(String name) {
		List<School> schoolList = schoolDao.findSchoolByName(name);
		return schoolList;
	}

	@Override
	public List<School> findSchoolByCode(String code) {
		List<School> schoolList = schoolDao.findSchoolByCode(code);
		return schoolList;
	}

	@Override
	public List<School> findSchoolByCondition(SchoolCondition schoolCondition, Page page, Order order) {
		return schoolDao.findSchoolByCondition(schoolCondition, page, order);
	}

	@Override
	public SchoolInfo findSchoolInfoById(Integer id) {
		return schoolDao.findSchoolInfoById(id);
	}

	@Override
	public List<SchoolInfo> findSchoolInfoByCondition(SchoolCondition condition, Page page, Order order) {
		return this.schoolDao.findSchoolInfoByCondition(condition, page, order);
	}

	/***
	 * 保存学校信息---此方法是提供给师生多媒体项目用
	 */
	public School addSchool(School school, String type, Integer sys_appId) {
		School tmp = null;
		try {
			// 第一步，新增学校基本信息
			tmp = this.schoolDao.create(school);
			// 第二步，将学校ID放到Group表中
			Group group = new Group();
			group.setCreateDate(new Date());
			group.setModifyDate(new Date());
			group.setOwnerId(tmp.getId());
			group.setName(school.getName());
			group.setType(type);
			Group g = this.groupDao.create(group);
			// 第三步 设置GroupId 放到 app_group
			AppGroup appGroup = new AppGroup();
			appGroup.setCreateDate(new Date());
			appGroup.setModifyDate(new Date());
			appGroup.setGroupId(g.getId());
			appGroup.setAppId(sys_appId);
			appGroupDao.create(appGroup);
		} catch (Exception e) {
			log.info("保存师生多媒体学校信息详细信息异常....");
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return tmp;
	}

	@Override
	public List<User> findUserBySchoolAndGroupAndRoleId(SchoolCondition schoolCondition, Group group, Integer roleId, Page page, Order order) {
		return this.schoolDao.findUserBySchoolAndGroupAndRoleId(schoolCondition, group, roleId, page, order);
	}

	@Override
	public List<Group> findGroupBySchoolCondition(GroupCondition groupCondition, SchoolCondition schoolCondition, Page page, Order order) {
		return this.schoolDao.findGroupBySchoolCondition(groupCondition, schoolCondition, page, order);
	}

	@Override
	public School findSchoolByUserId(Integer userId) {
		/**用户所在学校信息*/
		SchoolUserCondition suc=new SchoolUserCondition();
		suc.setUserId(userId);
		List<SchoolUser> schoolUserList =this.schoolUserDao.findSchoolUserByCondition(suc,null,null);

		if(schoolUserList.size()>0) {
			Integer schoolId = schoolUserList.get(0).getSchoolId();
			School school = this.findSchoolById(schoolId);
			return school;
		}
		return null;
	}

	public SchoolUserDao getSchoolUserDao() {
		return schoolUserDao;
	}

	public void setSchoolUserDao(SchoolUserDao schoolUserDao) {
		this.schoolUserDao = schoolUserDao;
	}

	@Override
	public List<School> findIncrementData(Boolean isDelete, Date modifyDate, Integer schoolId, Boolean isGetNew) {
		return schoolDao.findIncrementData(isDelete, modifyDate, schoolId, isGetNew);
	}
}
