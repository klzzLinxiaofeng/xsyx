package platform.education.rest.bp.service.vo;

import platform.education.clazz.model.BBXNotice;
import platform.education.clazz.vo.BpBwPictureAlbumVo;
import platform.education.clazz.vo.SchoolInfoVo;

import java.io.Serializable;
import java.util.List;

public class BpIndexInfoVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<BpBwPictureAlbumVo> albumList;
	
	private List<ClientTeamAwardVo> clientTeamAwardVoList;
	
	private List<BBXNotice> noticeList;
	
	private List<CommonHomework> homeworkList;
	//校园信息
	private List<SchoolInfoVo> schoolInfoList;
	//班级成员
	private List<PersonVo> studentList;
	
	private String teamIntroduce;

	public List<BpBwPictureAlbumVo> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<BpBwPictureAlbumVo> albumList) {
		this.albumList = albumList;
	}

	public List<ClientTeamAwardVo> getClientTeamAwardVoList() {
		return clientTeamAwardVoList;
	}

	public void setClientTeamAwardVoList(
			List<ClientTeamAwardVo> clientTeamAwardVoList) {
		this.clientTeamAwardVoList = clientTeamAwardVoList;
	}

	public List<BBXNotice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<BBXNotice> noticeList) {
		this.noticeList = noticeList;
	}

	public List<CommonHomework> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(List<CommonHomework> homeworkList) {
		this.homeworkList = homeworkList;
	}



	public List<SchoolInfoVo> getSchoolInfoList() {
		return schoolInfoList;
	}

	public void setSchoolInfoList(List<SchoolInfoVo> schoolInfoList) {
		this.schoolInfoList = schoolInfoList;
	}

	public List<PersonVo> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<PersonVo> studentList) {
		this.studentList = studentList;
	}

	public String getTeamIntroduce() {
		return teamIntroduce;
	}

	public void setTeamIntroduce(String teamIntroduce) {
		this.teamIntroduce = teamIntroduce;
	} 

	
	
}
