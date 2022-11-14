package platform.education.rest.learningPlan.service;

import java.util.List;
import java.util.Map;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.user.model.User;
import platform.service.storage.vo.FileResult;

public interface LearningPlanBaseService {

	void initExamInterscore(Integer taskId, int taskExamUnitId, Integer unitId, Integer teamId);

	int[] randomArray(int min, int max, int n);

	Map<String, Object> getCatalogMap(ResTextbookCatalogVo vo);

	List<Map<String, Object>> getLearningPlan(Integer catalogId, Integer userId, Integer schoolId);

	Map<String, String> getSubjectMap(Integer schoolId);

	List<Map<String, Object>> getUnitList(Integer learningPlanID, Integer catalogId, Integer userId, Integer taskId);

	Map<String, FileResult> getFileResult(List<LpTaskUserActivity> lpTaskUserActivityList);

	Map<Integer, Person> getPersonMapByLpTaskUserList(List<?> list);

	Map<Integer, Person> getPersons(List<User> userList);

	Page getPage(Integer pageSize, Integer pageNumber);
	
	String parseFiles(String files);

	List<Map<String, Object>> getExamUnitList(Integer taskId, Integer lpId, String subjectCode);

	List<String> findUserQuestionFileList(Integer taskId, Integer unitId, Integer userId);

	String getMicroIconUrl(String objectId);

	Map<String, Object> getCatalogInfo(ResTextbookCatalog resTextbookCatalog);

	String handlerImg(String content);
	
}
