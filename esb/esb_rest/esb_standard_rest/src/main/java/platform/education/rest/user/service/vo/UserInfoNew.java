package platform.education.rest.user.service.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class UserInfoNew {

	private Integer userId;
	
	private String name;
	
	private String iconUrl;
	
	private Integer schoolId;
	
	private String schoolName;
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public List<TeamInfo> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<TeamInfo> teamList) {
		this.teamList = teamList;
	}

	
	private List<TeamInfo> teamList;
	
	
	public static void main(String[] args) {
		
		UserInfoNew u = new UserInfoNew();
		
		u.setUserId(798);
		u.setName("黄老师");
		u.setIconUrl("http://cdn.test.studyo.cn/develop/test/2017-3-31/fd79fc293e2b19ceeb780c25c77a7a91.jpg");
		u.setSchoolId(101);
		u.setSchoolName("烂学校");
		
		List<TeamInfo> teamList = new ArrayList<TeamInfo>();
		List<SubjectInfo>subjectList = new ArrayList<SubjectInfo>();
		TeamInfo t = new TeamInfo();
		SubjectInfo subjectInfo = new SubjectInfo();
		subjectInfo.setCode("1");
		subjectInfo.setName("语文");
		subjectList.add(subjectInfo);
		t.setTeamId(1);
		t.setSubjectList(subjectList);
		t.setTeamName("高一（1）班");
		teamList.add(t);
		TeamInfo t1 = new TeamInfo();
		List<SubjectInfo>subjectList1 = new ArrayList<SubjectInfo>();
		SubjectInfo subjectInfo1 = new SubjectInfo();
		SubjectInfo subjectInfo4 = new SubjectInfo();
		
		t1.setTeamId(2);
		t1.setTeamName("高一（2）班");
		subjectInfo1.setCode("1");
		subjectInfo1.setName("语文");
		subjectInfo4.setCode("2");
		subjectInfo4.setName("数学");
		subjectList1.add(subjectInfo1);
		subjectList1.add(subjectInfo4);
		t1.setSubjectList(subjectList1);
		teamList.add(t1);
		
		List<SubjectInfo>subjectList2 = new ArrayList<SubjectInfo>();
		SubjectInfo subjectInfo2 = new SubjectInfo();
		SubjectInfo subjectInfo5 = new SubjectInfo();
		SubjectInfo subjectInfo6 = new SubjectInfo();
		TeamInfo t2 = new TeamInfo();
		t2.setTeamId(3);
		t2.setTeamName("高一（3）班");
		teamList.add(t2);
		
		subjectInfo2.setCode("1");
		subjectInfo2.setName("语文");
		subjectInfo5.setCode("2");
		subjectInfo5.setName("数学");
		subjectInfo6.setCode("3");
		subjectInfo6.setName("化学");
		subjectList2.add(subjectInfo2);
		subjectList2.add(subjectInfo5);
		subjectList2.add(subjectInfo6);
		t2.setSubjectList(subjectList2);
		
		u.setTeamList(teamList);
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(u));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
