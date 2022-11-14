package platform.education.generalTeachingAffair.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.DictClient;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeachClientService;
import platform.education.generalTeachingAffair.service.TeamService;

public class TeachClientServiceImpl implements TeachClientService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	//当前学期
	private SchoolTermCurrentService schoolTermCurrentService;
	//年级
	private GradeService gradeService;
	//班级
	private TeamService teamService;
	
	
	
	@Override
	public List<Grade> findGradeAndTeamInfoBySchool(Integer schoolId) {
		List<Grade> gradeList = null;
		try{
			SchoolTermCurrent schoolTermCurrent =	schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(schoolTermCurrent != null){
				String schoolYear = schoolTermCurrent.getSchoolYear();
				gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
				if(gradeList != null && gradeList.size() > 0){
					for(Grade grade : gradeList){
						List<Team> teamList = teamService.findTeamOfGradeAndSchool(grade.getId(), schoolId);
						grade.setTeamList(teamList);
					}
				}
			}
		}catch(Exception e){
			log.info("客户端通过学校查询当前学校下的年级，班级异常");
			e.printStackTrace();
		}
		return gradeList;
	}
	
	public DictClient findAllDictList(){
		
		return null;
	}

	public SchoolTermCurrentService getSchoolTermCurrentService() {
		return schoolTermCurrentService;
	}

	public void setSchoolTermCurrentService(
			SchoolTermCurrentService schoolTermCurrentService) {
		this.schoolTermCurrentService = schoolTermCurrentService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

}
