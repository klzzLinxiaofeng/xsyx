package platform.szxyzxx.web.schoolaffair.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.DormitoryPerson;
import platform.education.school.affair.vo.DormitoryPersonCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 保存批量调换、批量分配人员 
 * @author huangyanchun
 *
 */

@Controller
@RequestMapping("/schoolaffair/dormitoryPerson/save")
public class DormitoryPersonSaveController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(DormitoryPersonSaveController.class);
	
	
	
	/**
	 * 保存分配宿舍后的学生信息
	 * @param user
	 * @param year
	 * @param floorCode
	 * @param dormitoryCode
	 * @param studentId0
	 * @param studentId1
	 * @param liveSex
	 * @return
	 */
	@RequestMapping("/saveOrUpdateStudentInfo")
	@ResponseBody
	public String saveOrUpdateStudentInfo(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = true) String year,
			@RequestParam(value = "floorCode", required = true) String floorCode,
			@RequestParam(value = "dormitoryCode", required = true) String dormitoryCode,
			@RequestParam(value = "studentId0", required = true) String studentId0,
			@RequestParam(value = "studentId1", required = true) String studentId1,
			@RequestParam(value = "liveSex", required = true) String liveSex
			){
		try{
			//根据year得到学年的id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setYear(year);
			syCondition.setSchoolId(user.getSchoolId());
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			
			//对应的宿舍信息
			Dormitory dormitory = new Dormitory();
			dormitory.setSchoolId(user.getSchoolId());
			dormitory.setFloorCode(floorCode);
			dormitory.setDormitoryCode(dormitoryCode);
			
			
			//入住
			DormitoryPersonCondition dormitoryPersonCondition1 = new DormitoryPersonCondition();
			dormitoryPersonCondition1.setSchoolYearId(sy.getId());
			dormitoryPersonCondition1.setSchoolId(user.getSchoolId());
			dormitoryPersonCondition1.setDormitoryId(this.dormitoryService.findByDormitory(dormitory).getId());
			dormitoryPersonCondition1.setDormitoryCode(dormitoryCode);
			List<DormitoryPerson>dormitoryPersonList1 = this.dormitoryPersonService.findDormitoryPersonByCondition(dormitoryPersonCondition1,null,null);
			if(!dormitoryPersonList1.isEmpty()){
				for(DormitoryPerson dormitoryPerson1:dormitoryPersonList1){
					dormitoryPersonService.deletedAll(dormitoryPerson1);
				}
			}
			
			
			String[] studentId1_ = studentId1.split(",");
			DormitoryPerson dormitoryPerson1 = null;
			boolean flag1 = studentId1_[0]=="";
			if(!flag1){
				Map<String, String> idMaps1 = new HashMap<String, String>();
				for(int k=0;k<studentId1_.length;k++){
					idMaps1.put(studentId1_[k], studentId1_[k]);
				}
				for (String studentId : idMaps1.keySet()) {
					dormitoryPerson1 = new DormitoryPerson();
					Student student1 = this.studentService.findStudentById(Integer.parseInt(idMaps1.get(studentId)));
				if(student1.getSex().equals(liveSex)){
					if(student1.getTeamId()!=null){
						
						Dormitory dormitory1 = this.dormitoryService.findDormitory(dormitory);
						DormitoryPerson liveDormitoryPerson1 = this.dormitoryPersonService.findLive(user.getSchoolId(), sy.getId(), dormitory1.getId(), Integer.parseInt(studentId));
						if(liveDormitoryPerson1==null){
							Team team1 = this.teamService.findTeamById(student1.getTeamId());
							dormitoryPerson1.setSchoolId(user.getSchoolId());
							dormitoryPerson1.setSchoolYearId(sy.getId());
							dormitoryPerson1.setDormitoryId(dormitory1.getId());
							dormitoryPerson1.setDormitoryCode(dormitoryCode);
							dormitoryPerson1.setGradeId(team1.getGradeId());
							dormitoryPerson1.setTeamNumber(student1.getTeamId());
							dormitoryPerson1.setStudentId(student1.getId());
							dormitoryPerson1.setStudentName(student1.getName());
							dormitoryPerson1.setStudentNumber(student1.getStudentNumber()==null?"":student1.getStudentNumber());
							dormitoryPerson1.setCapacity(dormitory1.getCapacity());
							dormitoryPerson1.setSex(dormitory1.getSex());
							dormitoryPerson1.setIsDeleted(0);
							this.dormitoryPersonService.add(dormitoryPerson1);
							
						}else{
							
							if(liveDormitoryPerson1.getIsDeleted()==1){
								liveDormitoryPerson1.setModifyDate(new Date());
								liveDormitoryPerson1.setIsDeleted(0);
								this.dormitoryPersonService.modify(liveDormitoryPerson1);
							}
							
						}
						
						
						
					}else{
						System.out.println("studentId1[k]性别不为:"+liveSex+"所以无法添加");
					}
					}
					
			
		
				}
			}
			
			
			//搬出
			
			DormitoryPersonCondition dormitoryPersonCondition0 = new DormitoryPersonCondition();
			dormitoryPersonCondition0.setSchoolYearId(sy.getId());
			dormitoryPersonCondition0.setSchoolId(user.getSchoolId());
			dormitoryPersonCondition0.setDormitoryId(this.dormitoryService.findByDormitory(dormitory).getId());
			dormitoryPersonCondition0.setDormitoryCode(dormitoryCode);
			
			List<DormitoryPerson>dormitoryPersonList0 = this.dormitoryPersonService.findDormitoryPersonByCondition(dormitoryPersonCondition0,null,null);
			if(!dormitoryPersonList0.isEmpty()){
				for(DormitoryPerson dormitoryPerson0:dormitoryPersonList0){
					dormitoryPersonService.deletedAll(dormitoryPerson0);
				}
			}
			
			
			String[] studentId0_ = studentId1.split(",");
			DormitoryPerson dormitoryPerson0 = null;
			boolean flag0 = studentId0_[0]=="";
			if(!flag0){
				Map<String, String> idMaps0 = new HashMap<String, String>();
				for(int j=0;j<studentId0_.length;j++){
					idMaps0.put(studentId1_[j], studentId1_[j]);
				}
				for (String studentIds : idMaps0.keySet()) {
					dormitoryPerson0 = new DormitoryPerson();
					Student student0 = this.studentService.findStudentById(Integer.parseInt(idMaps0.get(studentIds)));
					if(student0.getSex().equals(liveSex)){
						if(student0.getTeamId()!=null){
							
							Dormitory dormitory0 = this.dormitoryService.findDormitory(dormitory);
							
							DormitoryPerson liveDormitoryPerson0 = this.dormitoryPersonService.findLive(user.getSchoolId(), sy.getId(), dormitory0.getId(), Integer.parseInt(studentIds));
							if(liveDormitoryPerson0==null){
								Team team0 = this.teamService.findTeamById(student0.getTeamId());
								dormitoryPerson0.setSchoolId(user.getSchoolId());
								dormitoryPerson0.setSchoolYearId(sy.getId());
								dormitoryPerson0.setDormitoryId(dormitory0.getId());
								dormitoryPerson0.setDormitoryCode(dormitoryCode);
								dormitoryPerson0.setGradeId(team0.getGradeId());
								dormitoryPerson0.setTeamNumber(student0.getTeamId());
								dormitoryPerson0.setStudentId(student0.getId());
								dormitoryPerson0.setStudentName(student0.getName());
								dormitoryPerson0.setStudentNumber(student0.getStudentNumber()==null?"":student0.getStudentNumber());
								dormitoryPerson0.setCapacity(dormitory0.getCapacity());
								dormitoryPerson0.setSex(dormitory0.getSex());
								dormitoryPerson0.setIsDeleted(0);
								this.dormitoryPersonService.add(dormitoryPerson0);
							}else{
								
								if(liveDormitoryPerson0.getIsDeleted()==1){
									liveDormitoryPerson0.setModifyDate(new Date());
									liveDormitoryPerson0.setIsDeleted(0);
									this.dormitoryPersonService.modify(liveDormitoryPerson0);
								}
									
								}
								
							}
							
						}
					
			}
				}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("保存宿舍分配后的学生信息异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 保存调换后的学生信息
	 * @param user
	 * @param year
	 * @param floorCode0
	 * @param dormitoryCode0
	 * @param floorCode1
	 * @param dormitoryCode1
	 * @param studentId0
	 * @param studentId1
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(
			@CurrentUser UserInfo user,
			@RequestParam(value = "year", required = true) String year,
			@RequestParam(value = "floorCode0", required = true) String floorCode0,
			@RequestParam(value = "dormitoryCode0", required = true) String dormitoryCode0,
			@RequestParam(value = "floorCode1", required = true) String floorCode1,
			@RequestParam(value = "dormitoryCode1", required = true) String dormitoryCode1,
			@RequestParam(value = "studentId0", required = true) String studentId0,
			@RequestParam(value = "studentId1", required = true) String studentId1
			){
		try{
			//根据year得到学年的id
			SchoolYearCondition syCondition = new SchoolYearCondition();
			syCondition.setSchoolId(user.getSchoolId());
			syCondition.setYear(year);
			syCondition.setSchoolId(user.getSchoolId());
			SchoolYear sy = this.schoolYearService.findSchoolYearByYear(syCondition);
			
			//没调换宿舍对应的宿舍信息
			Dormitory dormitory0 = new Dormitory();
			dormitory0.setSchoolId(user.getSchoolId());
			dormitory0.setFloorCode(floorCode0);
			dormitory0.setDormitoryCode(dormitoryCode0);
			
			
			//入住、搬出
			DormitoryPersonCondition dormitoryPersonCondition0 = new DormitoryPersonCondition();
			dormitoryPersonCondition0.setSchoolYearId(sy.getId());
			dormitoryPersonCondition0.setSchoolId(user.getSchoolId());
			dormitoryPersonCondition0.setDormitoryId(this.dormitoryService.findByDormitory(dormitory0).getId());
			dormitoryPersonCondition0.setDormitoryCode(dormitoryCode0);
			List<DormitoryPerson>dormitoryPersonList0 = this.dormitoryPersonService.findDormitoryPersonByCondition(dormitoryPersonCondition0, null,null);
			
			
			if(!dormitoryPersonList0.isEmpty()){
				for(DormitoryPerson dormitoryPerson0:dormitoryPersonList0){
					dormitoryPersonService.deletedAll(dormitoryPerson0);
					
				}
					
			String []studentId0_ = studentId0.split(",");
			DormitoryPerson dormitoryPerson0 = null;
			boolean flag0 = studentId0_[0]=="";
			if(!flag0){
				for(int i=0;i<studentId0_.length;i++){
					dormitoryPerson0 = new DormitoryPerson();
					String stId0 = studentId0_[i];
					Student student0_ = this.studentService.findStudentById(Integer.parseInt(stId0));
					Dormitory dormitory0_ = this.dormitoryService.findDormitory(dormitory0);
					DormitoryPerson liveDormitoryPerson0_ = this.dormitoryPersonService.findLive(user.getSchoolId(), sy.getId(), dormitory0_.getId(), Integer.parseInt(stId0));
					
					if(liveDormitoryPerson0_==null){
						Team team0 = this.teamService.findTeamById(student0_.getTeamId());
						dormitoryPerson0.setSchoolId(user.getSchoolId());
						dormitoryPerson0.setSchoolYearId(sy.getId());
						dormitoryPerson0.setDormitoryId(dormitory0_.getId());
						dormitoryPerson0.setDormitoryCode(dormitory0_.getDormitoryCode());
						dormitoryPerson0.setGradeId(team0.getGradeId());
						dormitoryPerson0.setTeamNumber(student0_.getTeamId());
						dormitoryPerson0.setStudentId(student0_.getId());
						dormitoryPerson0.setStudentName(student0_.getName());
						dormitoryPerson0.setStudentNumber(student0_.getStudentNumber()==null?"":student0_.getStudentNumber());
						dormitoryPerson0.setCapacity(dormitory0_.getCapacity());
						dormitoryPerson0.setSex(dormitory0_.getSex());
						dormitoryPerson0.setIsDeleted(0);
						this.dormitoryPersonService.add(dormitoryPerson0);
					}else{
						liveDormitoryPerson0_.setIsDeleted(0);
						liveDormitoryPerson0_.setModifyDate(new Date());
						this.dormitoryPersonService.modify(liveDormitoryPerson0_);
					}
					
					
				}
			}
			
			//调换的对应宿舍的学生信息
			Dormitory dormitory1 = new Dormitory();
			dormitory1.setSchoolId(user.getSchoolId());
			dormitory1.setFloorCode(floorCode1);
			dormitory1.setDormitoryCode(dormitoryCode1);
			
			DormitoryPersonCondition dormitoryPersonCondition1 = new DormitoryPersonCondition();
			dormitoryPersonCondition1.setSchoolYearId(sy.getId());
			dormitoryPersonCondition1.setSchoolId(user.getSchoolId());
			dormitoryPersonCondition1.setDormitoryId(this.dormitoryService.findByDormitory(dormitory1).getId());
			dormitoryPersonCondition1.setDormitoryCode(dormitoryCode1);
			List<DormitoryPerson>dormitoryPersonList1 = this.dormitoryPersonService.findDormitoryPersonByCondition(dormitoryPersonCondition1, null,null);
			
			if(!dormitoryPersonList1.isEmpty()){
				for(DormitoryPerson dormitoryPerson1:dormitoryPersonList1){
					dormitoryPersonService.deletedAll(dormitoryPerson1);
				}
			}
			
			
			String[] studentId1_ = studentId1.split(",");
			DormitoryPerson dormitoryPerson1 = null;
			boolean flag1 = studentId1_[0]=="";
			if(!flag1){
				for(int k=0;k<studentId1_.length;k++){
					dormitoryPerson1 = new DormitoryPerson();
					String stId1 = studentId1_[k];
					
					Student student1_ = this.studentService.findStudentById(Integer.parseInt(stId1));
					Dormitory dormitory1_ = this.dormitoryService.findDormitory(dormitory1);
					DormitoryPerson liveDormitoryPerson1_ = this.dormitoryPersonService.findLive(user.getSchoolId(), sy.getId(), dormitory1_.getId(), Integer.parseInt(stId1));
					
					if(liveDormitoryPerson1_==null){
						Team team1 = this.teamService.findTeamById(student1_.getTeamId());
						
						dormitoryPerson1.setSchoolId(user.getSchoolId());
						dormitoryPerson1.setSchoolYearId(sy.getId());
						dormitoryPerson1.setDormitoryId(dormitory1_.getId());
						dormitoryPerson1.setDormitoryCode(dormitory1_.getDormitoryCode());
						dormitoryPerson1.setGradeId(team1.getGradeId());
						dormitoryPerson1.setTeamNumber(student1_.getTeamId());
						dormitoryPerson1.setStudentId(student1_.getId());
						dormitoryPerson1.setStudentName(student1_.getName());
						dormitoryPerson1.setStudentNumber(student1_.getStudentNumber()==null?"":student1_.getStudentNumber());
						dormitoryPerson1.setCapacity(dormitory1_.getCapacity());
						dormitoryPerson1.setSex(dormitory1_.getSex());
						dormitoryPerson1.setIsDeleted(0);
						this.dormitoryPersonService.add(dormitoryPerson1);
					}else{
						liveDormitoryPerson1_.setModifyDate(new Date());
						liveDormitoryPerson1_.setIsDeleted(0);
						this.dormitoryPersonService.modify(liveDormitoryPerson1_);
					}
					
				}
			}		
					
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("保存宿舍调换后的学生信息异常");
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
}	
