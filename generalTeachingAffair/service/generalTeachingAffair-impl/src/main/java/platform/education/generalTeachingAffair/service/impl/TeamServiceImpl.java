package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import platform.education.generalTeachingAffair.bo.TeamBo;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStructure;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamUserCondition;
import platform.education.generalTeachingAffair.vo.TreeVo;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SyllabusService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.dao.SyllabusLessonDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.TeamVo;

public class TeamServiceImpl implements TeamService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private TeamDao teamDao;
	
	private GradeService gradeService;
	//班级学生
	private TeamStudentService teamStudentService;
	//班级学生
	private StudentService studentService;
	
	private TeamTeacherService teamTeacherService;
	
	private SchoolTermCurrentService schoolTermCurrentService;
	
	private TeamUserService teamUserService;
	
	private SyllabusService syllabusService;
	
	private SyllabusLessonDao syllabusLessonDao;
	
	public SchoolTermCurrentService getSchoolTermCurrentService() {
		return schoolTermCurrentService;
	}

	public void setSchoolTermCurrentService(
			SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}

	public TeamStudentService getTeamStudentService() {
		return teamStudentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public List<Team> findByIds(List<Integer> ids) {
		return teamDao.findByIds(ids.toArray(new Integer[0]),null);
	}

	@Caching(
		cacheable = {
			@Cacheable(value = "affair_team_cache", key = "#id")
		}
	)
	@Override
	public Team findTeamById(Integer id) {
		try {
			return teamDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public List<Team> findTeamOfGrade(Integer gradeId) {
		Integer schoolId = null;
		if(gradeId != null){
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade != null){
				schoolId = grade.getSchoolId();
			}
		}
		return teamDao.findTeamOfGrade(gradeId,schoolId);
	}

	@Override
	public List<Team> findTeamOfGradeAndSchool(Integer gradeId, Integer schoolId) {
		return teamDao.findTeamOfGrade(gradeId, schoolId);
	}

	@Override
	public List<Team> findCurrentTeamOfSchool(Integer schoolId) {
		return teamDao.findCurrentTeamOfSchool(schoolId);
	}

	@Override
	public List<Team> findCurrentTeamOfSchoolAndYear(Integer schoolId,String year) {
		return teamDao.findCurrentTeamBySchoolIdAndYear(schoolId, year);
	}

	@Override
	public List<Team> findTeamByTeacher(Integer teacherId) {
		return teamDao.findTeamByTeacher(teacherId);
	}

	@Override
	public List<Team> findTeamByTeacher(Integer teacherId, String year) {
		return teamDao.findTeamByTeacherAndYear(teacherId, year);
	}

	@Override
	public List<Team> findCurrentTeamByTeacher(Integer teacherId) {
		return teamDao.findCurrentTeamByTeacher(teacherId);
	}

	@Override
	public List<Team> findGradeByCode(String code) {
		return teamDao.findGradeByCode(code);
	}

	@Override
	public Team add(Team team) {
		if(team != null) {
			if(team.getUuid() == null || "".equals(team.getUuid())){
				team.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
			}
			Date date = new Date();
			team.setCreateDate(date);
			team.setModifyDate(date);
			return teamDao.create(team);
		}
		return null;
	}
	
	@Caching(
		put = {
				@CachePut(value = "affair_team_cache", key = "#result.id"),
		}
	)
	@Override
	public Team modify(Team team) {
		if(team != null && team.getId() != null) {
			Date modifyDate = team.getModifyDate();
			team.setModifyDate(modifyDate != null ? modifyDate : new Date()); 
			return teamDao.update(team);
		}
		return null;
	}

	@Override
	@CacheEvict(value = "affair_team_cache", key = "#grade.id")
	public void remove(Team team) {
		try {
			teamDao.delete(team);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", team.getId(), e);
			}
		}

	}

	@Override
	public List<Team> findTeamByCondition(TeamCondition teamCondition,
			Page page, Order order) {
		return teamDao.findTeamByCondition(teamCondition, page, order);
	}
	
	@Override
	public List<Team> findCurrentTeamOfSchoolAndYearNotExistCurrentClass(Integer schoolId,String year,Integer teamId){
		return teamDao.findCurrentTeamOfSchoolAndYearNotExistCurrentClass(schoolId, year,teamId);
	}

	/**
	 * 功能描述：学校学生年级统计 2015-11-05
	 */
	@Override
	public Long count() {
		return this.teamDao.count(null);
	}

	/**
	 * 功能描述：学校学生年级统计 2015-11-05
	 */
	@Override
	public Long count(TeamCondition teamCondition) {
		return this.teamDao.count(teamCondition);
	}

	@Override
	public List<Team> findTeamOfGradeAndSchool(Integer gradeId, Integer schoolId, Page page, Order order) {
		return this.teamDao.findTeamOfGrade(gradeId, schoolId, page, order);
	}

	@Override
	public List<Team> findTeamOfGradeByAsc(Integer gradeId) {
		Integer schoolId = null;
		if(gradeId != null){
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade != null){
				schoolId = grade.getSchoolId();
			}
		}
		return teamDao.findTeamOfGradeByAsc(gradeId,schoolId);
	}

	@Override
	/**
	 * 2016-1-26 修改分班  调用teamStudent中的assignStudentToTeam接口
	 */
	public void addPlacementStudent(String teamId,String studentId)throws Exception{
		try{
			String[] studentId_ = studentId.split(",");
			if(studentId_ != null && studentId_.length > 0 ){
				for(String sId : studentId_){
					this.teamStudentService.assignStudentToTeam(Integer.valueOf(sId), Integer.valueOf(teamId));
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			log.info("--addPlacementStudent--分班异常");
			throw e;
		}
	}

	@Override
	/**
	 * /**
	 * 2016-1-26 修改自动分班  调用teamStudent中的assignStudentToTeam接口
	 */
	public void autoPlacementStudent(String teamId,String studentId)throws Exception{

		try{
			this.teamStudentService.assignStudentToTeam(Integer.valueOf(studentId), Integer.valueOf(teamId));
		}catch(Exception e){
			log.info("--autoPlacementStudent自动分班异常--");
			//e.printStackTrace();
			throw e;
		}
	
	}

	@Override
	//学生调班
		public void addOrUpdateStudent(String teamId0,String studentId0,String teamId1,String studentId1) throws Exception{
			try{
				//换进
				Team team0 = this.teamDao.findById(Integer.parseInt(teamId0));
				TeamStudentCondition teamStudentCondition0 = new TeamStudentCondition();
				teamStudentCondition0.setTeamId(Integer.parseInt(teamId0));
				teamStudentCondition0.setGradeId(team0.getGradeId());
				teamStudentCondition0.setFinishDate(new Date());
				List<TeamStudent> teamStudentList0 = this.teamStudentService.findTeamStudentByConditionForTransfer(teamStudentCondition0, null, null);
				
				if(!teamStudentList0.isEmpty()){
					for(TeamStudent teamStudent0 : teamStudentList0){
						teamStudent0.setFinishDate(new Date());
						this.teamStudentService.modify(teamStudent0);
						//teamStudentService.remove(teamStudent0);
						//Student student0 = this.studentService.findStudentById(teamStudent0.getStudentId());
						//studentService.modifyStudentSetTeamIsNull(student0);
					}
				}
				
				String[] studentId0_ = studentId0.split(",");
				TeamStudent teamStudent0 = null;
				boolean flag = studentId0_[0]=="";
				if(!flag){
					for(int i=0;i<studentId0_.length;i++){
						teamStudent0 = new TeamStudent();
						String stId = studentId0_[i];
						Student student0 = this.studentService.findStudentById(Integer.parseInt(stId));
						student0.setTeamId(team0.getId());
						student0.setTeamName(team0.getName());
						
						teamStudent0.setGradeId(team0.getGradeId());
						teamStudent0.setStudentId(student0.getId());
						teamStudent0.setTeamId(Integer.parseInt(teamId0));
						teamStudent0.setCreateDate(new Date());
						teamStudent0.setJoinDate(new Date());
						teamStudent0.setName(student0.getName());
						teamStudent0.setPosition(student0.getPosition());
						this.studentService.modify(student0);
						this.teamStudentService.add(teamStudent0);
					}
				}
				
				//换出班
				Team team1 = this.teamDao.findById(Integer.parseInt(teamId1));
				TeamStudentCondition teamStudentCondition1 = new TeamStudentCondition();
				teamStudentCondition1.setTeamId(Integer.parseInt(teamId1));
				teamStudentCondition1.setGradeId(team1.getGradeId());
				teamStudentCondition1.setFinishDate(new Date());
				List<TeamStudent> teamStudentList1 = this.teamStudentService.findTeamStudentByConditionForTransfer(teamStudentCondition1, null, null);
				if(!teamStudentList1.isEmpty()){
					for(TeamStudent teamStudent1 : teamStudentList1){
						teamStudent1.setFinishDate(new Date());
						this.teamStudentService.modify(teamStudent1);
						//teamStudentService.remove(teamStudent1);
						//Student student1 = this.studentService.findStudentById(teamStudent1.getStudentId());
						//studentService.modifyStudentSetTeamIsNull(student1);
					}
				}
				
				String[] studentId1_ = studentId1.split(",");
				TeamStudent teamStudent1 = null;
				boolean flag1 = studentId1_[0]=="";
				if(!flag1){
					for(int k=0;k<studentId1_.length;k++){
						teamStudent1 = new TeamStudent();
						String stId1 = studentId1_[k];
						Student student1 = this.studentService.findStudentById(Integer.parseInt(stId1));
						student1.setTeamId(team1.getId());
						student1.setTeamName(team1.getName());
						
						teamStudent1.setGradeId(team1.getGradeId());
						teamStudent1.setStudentId(student1.getId());
						teamStudent1.setTeamId(Integer.parseInt(teamId1));
						teamStudent1.setCreateDate(new Date());
						teamStudent1.setJoinDate(new Date());
						teamStudent1.setName(student1.getName());
						teamStudent1.setPosition(student1.getPosition());
						this.studentService.modify(student1);
						this.teamStudentService.add(teamStudent1);
					}
				}
			}catch(Exception e){
				log.info("--addOrUpdateStudent---学生调班异常");
				//e.printStackTrace();
				throw e;
			}
			
		}

	@Override
	public List<Team> findByIds(Integer[] ids) {
		List<Team> teamList= null;
		if(ids.length > 0){
			teamList = teamDao.findByIds(ids,null);
		}
		return teamList;
	}

	@Override
	public List<Team> findCurrentTeamByClassTeacher(Integer teacherId) {
		return this.teamDao.findCurrentTeamByClassTeacher(teacherId);
	}

	@Override
	public Team findTeamByCode(String code) {
		return this.teamDao.findTeamByCode(code);
	}

	@Override
	public List<Team> findAdministrativeTeam(Integer gradeId, Integer schoolId) {
		return this.teamDao.findAdministrativeTeam(gradeId, schoolId);
	}

	@Override
	public Team findCurrentTeamOfStudent(Integer studentUserId, String schoolYear) {
		if (studentUserId == null || schoolYear == null || "".equals(schoolYear)) {
			return null;
		}
		Team team = teamDao.findCurrentTeamOfStudent(studentUserId, schoolYear);
		if(team == null){
			team = teamDao.findById(studentService.findStudentByUserId(studentUserId).getTeamId());
		}
		return team;
	}

	@Override
	public Team findCurrentTeamOfStudent(Integer studentUserId, Integer schoolId) {
		if (studentUserId == null || schoolId == null) {
			return null;
		}
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
		Team team = null;
		if (schoolTermCurrent != null) {
			team = findCurrentTeamOfStudent(studentUserId, schoolTermCurrent.getSchoolYear());
		}
		return team;
	}

	@Override
	public List<TeamVo> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer teamId, Boolean isGetNew, Boolean isOrder) {
		return this.teamDao.findIncrementByModifyDate(schoolId, schoolYear, isDeleted, modifyDate, teamId, isGetNew, isOrder);
	}

	@Override
	public List<TreeVo> findAllTeamTreeBySchoolId(Integer schoolId) {
		List<TeamStructure> tslist=teamDao.findAllTeamTreeBySchoolId(schoolId);
		if(tslist==null||tslist.size()==0){
			return new ArrayList<TreeVo>();
		}
		return buildTreeVo(tslist);
	}

	@Override
	public List<TreeVo> findClassTeamTreeBySchoolId(Integer schoolId,
			Integer userId) {
		List<TeamStructure> tslist=teamDao.findClassTeamTreeBySchoolId(schoolId,userId);
		if(tslist==null||tslist.size()==0){
			return new ArrayList<TreeVo>();
		}
		return buildTreeVo(tslist);
	}

	private List<TreeVo>buildTreeVo(List<TeamStructure> tslist){
		List<TreeVo> tree=new ArrayList<TreeVo>();
		Map<Integer,Object> schooYearMap=new HashMap<Integer, Object>();
		Map<Integer,Object> gradeMap=new HashMap<Integer, Object>();
		Map<Integer,Object> teamMap=new HashMap<Integer, Object>();
		Map<Integer,String> syMap=new HashMap<Integer, String>();
		Map<Integer,String> gMap=new HashMap<Integer, String>();
		for(TeamStructure ts1:tslist){
			if(schooYearMap.get(ts1.getSchoolYearId())==null){
				gradeMap=new HashMap<Integer, Object>();
				}else{
					gradeMap=(Map<Integer, Object>) schooYearMap.get(ts1.getSchoolYearId());
				}
			   teamMap=(Map<Integer, Object>) gradeMap.get(ts1.getGradeId());
			   if(teamMap==null){
				   teamMap=new HashMap<Integer, Object>();
			   }
			    teamMap.put(ts1.getTeamId(), ts1.getTeamName()+"--"+ts1.getTeamNum());
			    gradeMap.put(ts1.getGradeId(), teamMap);
			    schooYearMap.put(ts1.getSchoolYearId(), gradeMap);
			    syMap.put(ts1.getSchoolYearId(), ts1.getSchoolYearName());
			    gMap.put(ts1.getGradeId(), ts1.getGradeName()+"--"+ts1.getGradeNum());
		}
		for (Map.Entry<Integer, Object> entry : schooYearMap.entrySet()) {
			  TreeVo vo1=new TreeVo();
			  vo1.setId(entry.getKey());
			  String name =(String)syMap.get(entry.getKey());
			  vo1.setName(name);
			  List<TreeVo> gvo=new ArrayList<TreeVo>();
			  Map<Integer,Object> map1=new HashMap<Integer, Object>();
			  map1=(Map<Integer, Object>) entry.getValue();
			for (Map.Entry<Integer, Object> entry1 : map1.entrySet()) {
				TreeVo vo2=new TreeVo();
				  vo2.setId(entry1.getKey());
				  String name1 =(String)gMap.get(entry1.getKey());
				  String[] num1=name1.split("--");
				  vo2.setName(num1[0]);
				  vo2.setSort(num1[1]);
				  List<TreeVo> tvo=new ArrayList<TreeVo>();
				  Map<Integer,Object> map2=new HashMap<Integer, Object>();
				  map2=(Map<Integer, Object>) entry1.getValue();
				for (Map.Entry<Integer, Object> entry2 : map2.entrySet()) {
					TreeVo vo3=new TreeVo();
					  vo3.setId(entry2.getKey());
					  String name2 =(String)entry2.getValue();
					  String[] num2=name2.split("--");
					  vo3.setName(num2[0]);
					  vo3.setSort(num2[1]);
					  tvo.add(vo3);
				}
				vo2.setChildrens(sortList(tvo));
				gvo.add(vo2);
			}
			   vo1.setChildrens(sortList(gvo));
			   tree.add(vo1);
		}
		return tree;
	}
    private	 List<TreeVo> sortList(List<TreeVo> vo){
    	Collections.sort(vo, new Comparator<TreeVo>(){
            public int compare(TreeVo o1, TreeVo o2) {
            	Integer a1=0;
            	Integer b1=0;
            	String  a2="0";
            	String  b2="0";
                if(o1.getSort() instanceof Integer){
                	a1=(Integer) o1.getSort();
                	b1=(Integer) o2.getSort();
                }else{
                	a2=(String) o1.getSort();
                	b2=(String) o2.getSort();
                	a1=Integer.valueOf(a2);
                	b1=Integer.valueOf(b2);
                }
                //按照学生的年龄进行升序排列
                if(a1> b1){
                    return 1;
                }
                if(a1==b1){
                    return 0;
                }
                return -1;
            }
        });
    	return vo;
    }

	public List<Team> findByIdsOfOrder(Integer[] ids,Order order) {

		return teamDao.findByIds(ids, order);
	}

	@Override
	public List<Team> findNotEmptyTeam(Integer gradeId) {
		return this.teamDao.findNotEmptyTeam(gradeId);
	}

	@Override
	public List<Object> findAllTeamOfSchool(Integer schoolId, String schoolYear) {
		List<Object> list = new ArrayList<>();
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
		Map<String, Object> gradeMap = null;
		Map<String, Object> teamMap = null;
		List<Team> teamList = null;
		String name = "";
		String regex = ".*年级.*班|.*年.*班|高.*班";
		if (gradeList != null && gradeList.size() > 0) {
			for (Grade grade : gradeList) {
				gradeMap = new HashMap<>();
				gradeMap.put("gradeId", grade.getId());
				gradeMap.put("gradeName", grade.getName());
				gradeMap.put("gradeCode", grade.getUniGradeCode());

				List<Object> teams = new ArrayList<>();
				teamList = teamDao.findTeamOfGrade(grade.getId(), schoolId);
				if (teamList != null && teamList.size() >0) {
					for (Team team : teamList) {
						name = team.getName();
						if (name.matches(regex)) {
							name = team.getTeamNumber() + "班";
						}
						teamMap = new HashMap<>();
						teamMap.put("teamId", team.getId());
						teamMap.put("teamName", team.getName());
						teamMap.put("name", name);
						teamMap.put("teamNumber", team.getTeamNumber());
						teams.add(teamMap);
					}
				}
				gradeMap.put("teamList", teams);
				list.add(gradeMap);
			}
		}
		return list;
	}

	@Override
	public Team findBySchoolGradeIdAndName(Integer schoolId, Integer gradeId, String gradeName, String teamName, String schoolYear) {
		if(schoolId==null) {
			return null;
		}
		if(gradeId==null) {
			return null;
		}
		if(teamName==null || "".equals(teamName)) {
			return null;
		}
		teamName = gradeName+"("+teamName+")班";
		
		TeamCondition condition = new TeamCondition();
		condition.setGradeId(gradeId);
		condition.setSchoolId(schoolId);
		condition.setName(teamName);
		condition.setSchoolYear(schoolYear);
		condition.setIsDelete(false);
		List<Team> result = teamDao.findTeamByCondition(condition);
		if(result.size()>0) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public Team addTeamUseSchoolGradeIdAndName(Integer schoolId, Integer gradeId, String gradeName, String teamName, String schoolYear) {
		if(schoolId==null) {
			return null;
		}
		if(gradeId==null) {
			return null;
		}
		if(teamName==null || "".equals(teamName)) {
			return null;
		}
		Team team = this.findBySchoolGradeIdAndName(schoolId, gradeId, gradeName, teamName, schoolYear);
		if(team==null) {
			Grade grade = gradeService.findGradeById(gradeId);
			team = new Team();
			Integer teamNumber = Integer.parseInt(teamName);
			
			team.setSchoolId(schoolId);
			team.setGradeId(gradeId);
			String code = grade.getCode();
			code = code+"-"+teamName;
			team.setCode(code);
			
			teamName = gradeName+"("+teamName+")班";
			team.setName(teamName);
			team.setFullName(teamName);
			team.setTeamNumber(teamNumber);
			team.setCode2(getCode2(grade.getStageCode(), schoolYear, grade.getGradeNumber(), teamNumber));
			team.setTeamType("0");
			team.setSchoolYear(schoolYear);
			team.setIsDelete(false);
			Date now = new Date();
			team.setCreateDate(now);
			team.setModifyDate(now);
			team.setBeginDate(now);
			team.setFinishDate(now);
			team.setUuid(UUID.randomUUID().toString());
			
			teamDao.create(team);
		}
		return team;
	}
	
	public String getCode2(String stageCode, String schoolYear, Integer gradeNumber, Integer teamNumber) {
		String stage = "";
		if (stageCode == "2" || "2".equals(stageCode)) {
			stage = "X";
		} else if (stageCode == "3" || "3".equals(stageCode)) {
			stage = "C";
		} else if (stageCode == "4" || "4".equals(stageCode)) {
			stage = "G";
		} else {
			stage = "O";
		}
		
		String teamNumberTemp = "";
		//班级编号不足两位的补"0",如1补0变成01
		if(teamNumber.toString().length() == 1){
			teamNumberTemp = "0" + teamNumber;
		}else{
			teamNumberTemp = teamNumber.toString();
		}
		
		Integer schoolYearTemp = Integer.parseInt(schoolYear) - gradeNumber + 1;
		String schoolYearSub = schoolYearTemp.toString().substring(2);  //获取学年的后两位
		String code2 = stage + schoolYearSub + teamNumberTemp;
		
		return code2;
	}

	@Override
	public void deleteAllInfoById(Integer teamId, final String studentType, final String parentType) {
		if(teamId==null) {
			return;
		}
		Team team = teamDao.findById(teamId);
		if(team==null) {
			return;
		}
		
		/**计算班级用户人数*/
		TeamUserCondition teamUserCondition = new TeamUserCondition();
		teamUserCondition.setTeamId(teamId);
		Long size = teamUserService.count(teamUserCondition);
		
		/**查找班级老师*/
		TeamTeacherCondition condition = new TeamTeacherCondition();
		condition.setTeamId(teamId);
		List<TeamTeacher> list = teamTeacherService.findTeamTeacherByCondition(condition, null, null);
		
		Date now = new Date();
		
		/**删除班级老师*/
		for (TeamTeacher teamTeacher : list) {
			teamTeacher.setDelete(true);
			teamTeacher.setModifyDate(now);
			teamTeacherService.modify(teamTeacher);
		}
		
		/**删除学生*/
		final List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
		
		Thread thread = new Thread(new Runnable(){  
            public void run(){  
            	for (TeamStudent teamStudent : teamStudentList) {
        			studentService.removeAllStudentInfoById(teamStudent.getStudentId(), studentType, parentType);
        		}
            }
        });  
		thread.start();  
		
		/**删除班级学生*/
		teamStudentService.deleteByTeamId(teamId);
		
		/**删除班级用户*/
		teamUserService.deleteByTeamId(teamId);
		
		/**删除班级*/
		team.setIsDelete(true);
		team.setModifyDate(now);
		teamDao.update(team);
		
		Integer gradeId = team.getGradeId();
		Grade grade = gradeService.findGradeById(gradeId);
		if(grade!=null) {
			/**更新年级人数*/
			Integer teamCount = grade.getTeamCount();
			if(teamCount!=null && size-teamCount>0) {
				Integer tmp = (int) (size-teamCount);
				grade.setTeamCount(tmp);
				grade.setModifyDate(now);
				gradeService.modify(grade);
			}
		}
		
		/**查找和删除班级课程表*/
		SyllabusCondition syllabusCondition = new SyllabusCondition();
		syllabusCondition.setTeamId(teamId);
		List<Syllabus> sysllabusList = syllabusService.findSyllabusByCondition(syllabusCondition);
		for (Syllabus syllabus : sysllabusList) {
			List<SyllabusLesson> syllabusLessonList = syllabusLessonDao.findBySyllabusId(syllabus.getId());
			for (SyllabusLesson syllabusLesson : syllabusLessonList) {
				syllabusLesson.setIsDeleted(true);
				syllabusLesson.setModifyDate(now);
				syllabusLessonDao.update(syllabusLesson);
			}
			syllabus.setIsDeleted(true);
			syllabus.setModifyDate(now);
			syllabusService.modify(syllabus);
		}
	}

	@Override
	public boolean updateAsSendSeewo(List<Team> teams) {

		Integer[] ids = new Integer[teams.size()];
		for (int i = 0; i < teams.size(); i++) {
			ids[i] = teams.get(i).getId();
		}
		return teamDao.updateAsSendSeewoByIds(ids) > 0;

	}

	@Override
	public boolean updateAsSendSeewoByIds(Integer[] ids) {
		return teamDao.updateAsSendSeewoByIds(ids) > 0;
	}

	@Override
	public List<Team> findNotSendSeewo() {
		return teamDao.findByIsSendSeewo(0);
	}

	@Override
	public List<TeamBo> findBoNotSendSeewo(){
		return teamDao.findBoByIsSendSeewo(0);
	}

	public TeamTeacherService getTeamTeacherService() {
		return teamTeacherService;
	}

	public void setTeamTeacherService(TeamTeacherService teamTeacherService) {
		this.teamTeacherService = teamTeacherService;
	}

	public TeamUserService getTeamUserService() {
		return teamUserService;
	}

	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public SyllabusService getSyllabusService() {
		return syllabusService;
	}

	public void setSyllabusService(SyllabusService syllabusService) {
		this.syllabusService = syllabusService;
	}

	public SyllabusLessonDao getSyllabusLessonDao() {
		return syllabusLessonDao;
	}

	public void setSyllabusLessonDao(SyllabusLessonDao syllabusLessonDao) {
		this.syllabusLessonDao = syllabusLessonDao;
	}

	@Override
	public List<Map<String, Object>> getNotSendSendSeewo() {
		return teamDao.findNotSendSendSeewo();
	}
}
