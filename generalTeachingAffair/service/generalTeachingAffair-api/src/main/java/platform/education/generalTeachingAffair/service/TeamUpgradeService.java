package platform.education.generalTeachingAffair.service;

import java.util.Map;


/**
 * 此接口目前用于班级升级和班级毕业
 * @author Administrator
 *
 */
public interface TeamUpgradeService {

	/**
	 * 班级升级
	 * @param newGradeId
	 * @param oldTeamId
	 * @return Team 用于更新班级账号
	 */
	@SuppressWarnings("rawtypes")
	Map upgradeTeam(Integer newGradeId, Integer oldTeamId);
	
	/**
	 * 班级毕业
	 * @param oldTeamId
	 */
	void graduateTeam(Integer oldTeamId);
}
