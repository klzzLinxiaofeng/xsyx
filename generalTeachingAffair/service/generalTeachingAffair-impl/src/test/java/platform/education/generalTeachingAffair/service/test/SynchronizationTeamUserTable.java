package platform.education.generalTeachingAffair.service.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;

/**
 * 用于同步TeamUser表数据，该表由于很多学生数据在开始有班级的情况下没有存进数据，导致缺失大量数据
 * 现用程序进行同步
 */
public class SynchronizationTeamUserTable extends BaseTest{
	
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;
	
	@Autowired
	@Qualifier("parentStudentService")
	private ParentStudentService parentStudentService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	/**
	 * 同步学生数据
	 * 1、获取学生表非删除、有班级 状态的数据
	 * 2、遍历  判断teamUser表中是否存在 学生的userId且isStudent=true
	 * 3、不存在则创建，存在更新
	 */
	@Test
	public void simpeTest(){
		List<Teacher> list = teacherService.findTeacherListBySchoolId(1);
		//输出测试
		System.out.println("----------==============="+list.size()+"=============----------------");
	}
	
	@Test
	public void synchronizationData(){
		try{
			//同步教师数据
			SynchronizationTeacher();
			
			//同步学生家长数据
			SynchronizationStudentAndParent();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//同步完成
		System.out.println("----------===============数据同步完成=============----------------");
	}
	
	public void SynchronizationStudentAndParent(){
		StudentCondition studentCondition = new StudentCondition();
		studentCondition.setIsDelete(false);
		System.out.println("正在查询学生数据。。。");
		List<Student> allStudentList = studentService.findStudentByCondition(studentCondition, null, null);
		if(allStudentList != null && allStudentList.size() > 0){
			for(Student student : allStudentList){
				if(student.getTeamId() != null && !"".equals(student.getTeamId())){
					//开始同步学生数据
					System.out.println("...正在同步学生  "+student.getName()+"的数据");
					TeamUser teamUser = addOrModify(student.getTeamId(),student.getUserId(),student.getName(),student.getSex(),"s");
					System.out.println("同步完成  "+teamUser.getId()+"，"+student.getUserId()+","+student.getName());
					writerTxt(teamUser.getId(),student.getUserId(),student.getName(),"s");
					
					//同步家长数据
					List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByStudentUserId(student.getUserId());
					if(parentStudentList != null && parentStudentList.size() > 0){
						for(ParentStudent parentStudent : parentStudentList){
							if(parentStudent != null && parentStudent.getId() != null){
								Parent parent = parentService.findUniqueByUserId(parentStudent.getParentUserId());
								if(parent != null && parent.getId() != null){
									Person person = personService.findPersonById(parent.getPersonId());
									if(person != null){
										System.out.println("...正在同步家长  "+parent.getName()+"的数据");
										teamUser = addOrModify(student.getTeamId(),parent.getUserId(),parent.getName(),person.getSex(),"p");
										System.out.println("同步完成  "+teamUser.getId()+"，"+parent.getUserId()+","+parent.getName());
										writerTxt(teamUser.getId(),parent.getUserId(),parent.getName(),"p");
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 同步教师数据
	 */
	public void SynchronizationTeacher(){
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setDelete(false);
		System.out.println("正在查询教师数据。。。");
		List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		if(teamTeacherList != null && teamTeacherList.size() > 0){
			Teacher teacher = null;
			TeamUser teamUser = null;
			for(TeamTeacher teamTeacher : teamTeacherList){
				if(teamTeacher != null){
					teacher = teacherService.findTeacherById(teamTeacher.getTeacherId());
					if(teacher != null){
						if(teamTeacher.getType()==1){
							System.out.println("...正在同步班主任  "+teacher.getName()+"的数据");
							teamUser = addOrModify(teamTeacher.getTeamId(),teacher.getUserId(),teacher.getName(),teacher.getSex(),"m");
							System.out.println("同步完成  "+teamUser.getId()+"，"+teacher.getUserId()+","+teacher.getName());
							writerTxt(teamUser.getId(),teacher.getUserId(),teacher.getName(),"m");
						}else if(teamTeacher.getType()==2){
							System.out.println("...正在同步任课教师  "+teacher.getName()+"的数据");
							teamUser = addOrModify(teamTeacher.getTeamId(),teacher.getUserId(),teacher.getName(),teacher.getSex(),"t");
							System.out.println("同步完成  "+teamUser.getId()+"，"+teacher.getUserId()+","+teacher.getName());
							writerTxt(teamUser.getId(),teacher.getUserId(),teacher.getName(),"t");
						}
					}
				}
			}
		}
	}
	
	
	public TeamUser addOrModify(Integer teamId,Integer userId,String name,String sex,String type){
		Boolean isM = false;
		Boolean isP = false;
		Boolean isS = false;
		Boolean isT = false;
		
		if(type == "m"){
			isM = true;
		}else if(type == "p"){
			isP = true;
		}else if(type == "s"){
			isS = true;
		}else if(type == "t"){
			isT = true;
		}
		
		Date date = new Date();
		
		TeamUser teamUser = teamUserService.findUnique(teamId, userId);
		if(teamUser == null){
			teamUser = new TeamUser();
			teamUser.setCreateDate(date);
			teamUser.setIsMaster(isM);
			teamUser.setIsParent(isP);
			teamUser.setIsStudent(isS);
			teamUser.setIsTeacher(isT);
			teamUser.setModifyDate(date);
			teamUser.setName(name);
			teamUser.setSex(sex);
			teamUser.setTeamId(teamId);
			teamUser.setUserId(userId);
			teamUser = teamUserService.add(teamUser);
		}else{
			if(type == "m"){
				teamUser.setIsMaster(isM);
			}else if(type == "p"){
				teamUser.setIsParent(isP);
			}else if(type == "s"){
				teamUser.setIsStudent(isS);
			}else if(type == "t"){
				teamUser.setIsTeacher(isT);
			}
			teamUser.setModifyDate(date);
			teamUser.setName(name);
			teamUser.setTeamId(teamId);
			teamUser = teamUserService.modify(teamUser);
		}
		return teamUser;
	}
	//将有修改的东西写进文件
	public void writerTxt(Integer id,Integer userId,String name,String type){
		if(type == "s"){
			type="student";
		}else if(type=="p"){
			type="parent";
		}else if(type=="m"){
			type="master";
		}else if(type=="t"){
			type="teacher";
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("E:\\data\\data.txt"),true));
			String txt = "teamUserId is : "+id+", userId is : "+userId+", and userType is : "+type +"";
			writer.write(new String(txt.getBytes(),"UTF-8"));
			writer.newLine();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
