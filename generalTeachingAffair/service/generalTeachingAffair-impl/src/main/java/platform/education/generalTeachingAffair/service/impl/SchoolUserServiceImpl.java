package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.SchoolUserDao;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolUserServiceImpl implements SchoolUserService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private SchoolUserDao schoolUserDao;

	public void setSchoolUserDao(SchoolUserDao schoolUserDao) {
		this.schoolUserDao = schoolUserDao;
	}

	@Override
	public SchoolUser findSchoolUserById(Integer id) {
		try {
			return schoolUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public SchoolUser add(SchoolUser schoolUser) {
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setSchoolId(schoolUser.getSchoolId());//根据学校和用户id确认用户是否已经在学校存在
		schoolUserCondition.setUserId(schoolUser.getUserId());
		schoolUserCondition.setUserType(schoolUser.getUserType());
		List<SchoolUser> schoolUserList =  this.schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
		if(schoolUserList != null&&schoolUserList.size()>0){
			return schoolUserList.get(0);
		}else{
			return schoolUserDao.create(schoolUser);
		}
		
	}
	
	@Override
	public SchoolUser addSchoolUser(SchoolUser schoolUser) {
		return schoolUserDao.create(schoolUser);
	}

	@Override
	public SchoolUser modify(SchoolUser schoolUser) {
		return schoolUserDao.update(schoolUser);
	}

	@Override
	public void remove(SchoolUser schoolUser) {
		try {
			schoolUserDao.delete(schoolUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}

	}

	@Override
	public List<SchoolUser> findSchoolUserByCondition(SchoolUserCondition schoolUserCondition, Page page, Order order) {
		return schoolUserDao.findSchoolUserByCondition(schoolUserCondition, page, order);
	}

	@Override
	public Integer findSchoolIdByUserName(String userName) {
		return this.schoolUserDao.findSchoolIdByUserName(userName);
	}

	/**
	 * 功能描述：获得所有在校教师（user_type = "1"）
	 * 2016-02-19
	 * @param schoolId
	 * @return
	 */
	@Override
	public List<SchoolUser> findSchoolUserOfTeacher(Integer schoolId) {
		if(schoolId == null) {
			return new ArrayList<SchoolUser>();
		}
		return this.schoolUserDao.findSchoolUserOfTeacher(schoolId, "1");
	}

	@Override
	public int updateNotInSchoolByType(String userType, Integer[] userIds) {
		return this.schoolUserDao.updateNotInSchoolByType(userType, userIds);
	}

	@Override
	public List<SchoolUser> findSiblingOfStudentUser(Integer[] parentUserIds,
			Integer studentUserId, Integer schoolId) {
		return this.schoolUserDao.findSiblingOfStudentUser(parentUserIds, studentUserId, schoolId);
	}

	@Override
	public SchoolUser findSchoolUserByUserId(Integer userId) {
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setUserId(userId);
		List<SchoolUser> schoolUserList = schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
		if(schoolUserList.size()>0) {
			return schoolUserList.get(0);
		}
		return null;
	}
	
	@Override
	public SchoolUser findSchoolUserByUserId(Integer userId, Integer schoolId) {
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setSchoolId(schoolId);
		schoolUserCondition.setIsDeleted(false);
		List<SchoolUser> schoolUserList = schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
		if(schoolUserList.size()>0) {
			return schoolUserList.get(0);
		}
		return null;
	}

	@Override
	public SchoolUser findSchoolUserListByUserIdAndType(Integer userId, String userType) {
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setUserId(userId);
		schoolUserCondition.setUserType(userType);
		schoolUserCondition.setIsDeleted(false);
		List<SchoolUser> schoolUserList = schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
		if(schoolUserList.size()>0) {
			return schoolUserList.get(0);
		}
		return null;
	}

	@Override
	public List<SchoolUser> findSchoolUserListByUserId(Integer userid) {
		SchoolUserCondition schoolUserCondition = new SchoolUserCondition();
		schoolUserCondition.setUserId(userid);
		schoolUserCondition.setIsDeleted(false);
		List<SchoolUser> schoolUserList = schoolUserDao.findSchoolUserByCondition(schoolUserCondition, null, null);
		return schoolUserList;
	}

}
