package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsMedalWinner;


import java.util.Date;
import java.util.List;


public interface ApsMedalWinnerDao extends GenericDao<ApsMedalWinner, java.lang.Integer> {
	
	ApsMedalWinner findById(Integer id);
	
	List<ApsMedalWinner> findApsMedalWinners(Integer medalID,Integer schoolID,String termCode,Date startDate,Date finishDate);
	
	//唯一获奖者
	ApsMedalWinner findUniqueWinner(Integer medalID ,Integer schoolID, String termCode, String periodCode, String objectType, Integer objectId);
}
