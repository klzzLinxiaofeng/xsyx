package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.UserFile;
import platform.education.paper.service.UserFileService;
import platform.education.paper.vo.UserFileCondition;
import platform.education.paper.dao.UserFileDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class UserFileServiceImpl implements UserFileService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UserFileDao userFileDao;

	public void setUserFileDao(UserFileDao userFileDao) {
		this.userFileDao = userFileDao;
	}
	
	@Override
	public UserFile findUserFileById(Integer id) {
		try {
			return userFileDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public UserFile add(UserFile userFile) {
		if(userFile == null) {
    		return null;
    	}
    	Date createDate = userFile.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	userFile.setCreateDate(createDate);
    	userFile.setModifyDate(createDate);
		return userFileDao.create(userFile);
	}

	@Override
	public UserFile modify(UserFile userFile) {
		if(userFile == null) {
    		return null;
    	}
    	Date modify = userFile.getModifyDate();
    	userFile.setModifyDate(modify != null ? modify : new Date());
		return userFileDao.update(userFile);
	}
	
//	@Override
//	public List<UserFile> findUserFileByUserQuestionIds(Integer[] userQuestionIds) {
//		if(userQuestionIds==null || userQuestionIds.length==0) {
//			return null;
//		}
//		return userFileDao.findUserFileByUserQuestionIds(userQuestionIds);
//	}
	
	@Override
	public void remove(UserFile userFile) {
		try {
			userFileDao.delete(userFile);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", userFile.getId(), e);
			}
		}
	}
	
	@Override
	public List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Page page, Order order) {
		return userFileDao.findUserFileByCondition(userFileCondition, page, order);
	}
	
	@Override
	public List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition) {
		return userFileDao.findUserFileByCondition(userFileCondition, null, null);
	}
	
	@Override
	public List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Page page) {
		return userFileDao.findUserFileByCondition(userFileCondition, page, null);
	}
	
	@Override
	public List<UserFile> findUserFileByCondition(UserFileCondition userFileCondition, Order order) {
		return userFileDao.findUserFileByCondition(userFileCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.userFileDao.count(null);
	}

	@Override
	public Long count(UserFileCondition userFileCondition) {
		return this.userFileDao.count(userFileCondition);
	}

	@Override
	public void processUserFileAndOrUpdate(List<UserFile> userFileList) {
		if(userFileList != null && userFileList.size() > 0){
			
			for(UserFile userFile:userFileList){
				
				if(userFile.getId() != null){ //新增
					
					this.userFileDao.update(userFile);
				}else{
					
					this.userFileDao.create(userFile);
				}
			}
		}
		
	}

	@Override
	public Integer batchUpdateMarkedFile(List<UserFile> userFiles) {
		if(userFiles!=null && userFiles.size()>0) {
			return this.userFileDao.batchUpdateMarkedFile(userFiles);
		}
		return 0;
	}

	@Override
	public List<UserFile> findUserFileByUserPaperIds(Integer[] userPaperIds) {
		if(userPaperIds.length==0){
			return new ArrayList<UserFile>();
		}
		return userFileDao.findUserFileByUserPaperIds(userPaperIds);
	}

	@Override
	public List<UserFile> findUserFileByUserQuestionUuIds(String[] userQuestionIds) {
		if(userQuestionIds.length==0){
			return new ArrayList<UserFile>();
		}
		return userFileDao.findUserFileByUserQuestionUuIds(userQuestionIds);
	}

}
