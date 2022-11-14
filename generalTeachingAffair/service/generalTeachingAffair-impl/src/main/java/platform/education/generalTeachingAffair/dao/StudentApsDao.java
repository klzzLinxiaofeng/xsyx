package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.vo.StarEvaluateData;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */
public interface StudentApsDao extends GenericDao<ApsTaskScore, Integer> {

    List<StarEvaluateData> findPersonalStarScore(Integer taskId, Integer schoolId, String schoolYear, Integer gradeId, Integer teamId, Integer studentId, Date beginDate, Date endDate);
}
