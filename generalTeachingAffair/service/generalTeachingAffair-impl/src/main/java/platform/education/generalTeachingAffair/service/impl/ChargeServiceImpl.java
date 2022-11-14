package platform.education.generalTeachingAffair.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ChargeCondition;
import platform.education.generalTeachingAffair.dao.ChargeDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.ChargeVo;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;

public class ChargeServiceImpl implements ChargeService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ChargeDao chargeDao;

	public void setChargeDao(ChargeDao chargeDao) {
		this.chargeDao = chargeDao;
	}

	private ChargeItemService chargeItemService;

	private GradeService gradeService;

	private TeamService teamService;

	private StudentService studentService;

	private TeamStudentService teamStudentService;

	public void setChargeItemService(ChargeItemService chargeItemService) {
		this.chargeItemService = chargeItemService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}

	@Override
	public Charge findChargeById(Integer id) {
		try {
			return chargeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Charge add(Charge charge) {
		if(charge == null) {
    		return null;
    	}
    	Date createDate = charge.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	charge.setCreateDate(createDate);
    	charge.setModifyDate(createDate);
		return chargeDao.create(charge);
	}

	@Override
	public Charge modify(Charge charge) {
		if(charge == null) {
    		return null;
    	}
    	Date modify = charge.getModifyDate();
    	charge.setModifyDate(modify != null ? modify : new Date());
		return chargeDao.update(charge);
	}
	
	@Override
	public void remove(Charge charge) {
		try {
			chargeDao.delete(charge);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", charge.getId(), e);
			}
		}
	}
	
	@Override
	public List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Page page, Order order) {
		return chargeDao.findChargeByCondition(chargeCondition, page, order);
	}
	
	@Override
	public List<Charge> findChargeByCondition(ChargeCondition chargeCondition) {
		return chargeDao.findChargeByCondition(chargeCondition, null, null);
	}
	
	@Override
	public List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Page page) {
		return chargeDao.findChargeByCondition(chargeCondition, page, null);
	}
	
	@Override
	public List<Charge> findChargeByCondition(ChargeCondition chargeCondition, Order order) {
		return chargeDao.findChargeByCondition(chargeCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.chargeDao.count(null);
	}

	@Override
	public Long count(ChargeCondition chargeCondition) {
		return this.chargeDao.count(chargeCondition);
	}


	/**
	 * 	初始化收费记录(每个学期)
	 */
	@Override
	public void addAllRecord(Integer schoolId, String schoolYear, String termCode, Integer itemId, String objectType, String objectIds){
		if (objectType == null || "".equals(objectType)) {
			objectType = "school";
		}
		TeamStudentCondition condition = new TeamStudentCondition();
		condition.setInState(true);

		//判断收费对象是学校、年级还是班级，objectIds可以是多个
		String[] str = objectIds.split(",");
		if ("school".equals(objectType)) {
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
			for (Grade grade : gradeList) {
				condition.setGradeId(grade.getId());
				List<TeamStudent> teamStudentList = teamStudentService.findTeamStudentByCondition(condition, null, null);
				addStudentRecord(schoolId, termCode, itemId, teamStudentList);
			}
		} else if ("grade".equals(objectType)) {
			for (String gradeId : str){
				condition.setGradeId(Integer.valueOf(gradeId));
				List<TeamStudent> teamStudentList = teamStudentService.findTeamStudentByCondition(condition, null, null);
				addStudentRecord(schoolId, termCode, itemId, teamStudentList);
			}
		} else if ("team".equals(objectType)) {
			for (String teamId : str) {
				List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(Integer.parseInt(teamId));
				addStudentRecord(schoolId, termCode, itemId, teamStudentList);
			}
		}

	}

	private void addStudentRecord(Integer schoolId, String termCode, Integer itemId, List<TeamStudent> teamStudentList){
		Charge charge = null;
		for (TeamStudent teamStudent : teamStudentList) {
			charge = new Charge();
			charge.setItemId(itemId);
			charge.setSchoolId(schoolId);
			charge.setTermCode(termCode);
			charge.setGradeId(teamStudent.getGradeId());
			charge.setTeamId(teamStudent.getTeamId());
			charge.setStudentId(teamStudent.getStudentId());
			charge.setIsPay(false);
			charge.setAmount(null);
			charge.setIsDeleted(false);
			charge.setCreateDate(new Date());
			charge.setModifyDate(new Date());
			chargeDao.create(charge);
		}
	}

	/**
	 * 点击缴费/编辑时获取的收费信息
	 * @param id
	 * @return
	 */
	@Override
	public ChargeVo findChargeVoById(Integer id) {
		return chargeDao.findChargeVoById(id);
	}

	/**
	 * 缴费/修改缴费金额
	 * 删除记录也使用该接口，Controller中对象只存id,isPay(false)和Amount("")
	 * @param charge
	 * @return
	 */
	@Override
	public Charge modifyPayment(Charge charge) {
		if (charge == null) {
			return null;
		}
		if (charge.getAmount() == null || "".equals(charge.getAmount()) || "0".equals(charge.getAmount())) {
			charge.setIsPay(false);
			charge.setAmount("");
		} else {
			charge.setIsPay(true);
		}
		charge.setModifyDate(new Date());
		return chargeDao.update(charge);
	}

	/**
	 * 收费处理列表主页的接口
	 * 主要参数为schoolId, schoolYear, termCode, gradeId, teamId, itemId, isPay, studentName
	 * 其中schoolId、schoolYear和termCode默认当前学校、当前学年、当前学期，其余5个自选，均可为null
	 * 记录查询列表也使用该接口，除schoolId、schoolYear和isPay(true)外，其余皆可为null
	 * @param chargeVo
	 * @param page
	 * @return
	 */
	@Override
	public List<ChargeVo> findChargeList(ChargeVo chargeVo, Page page) {
		if (chargeVo == null) {
			return null;
		}
		String name = chargeVo.getStudentName();
		if (name != null && !"".equals(name)) {
			name = name.trim();
		}
		return chargeDao.findByChargeVo(chargeVo, page);
	}

	/**
	 * 获取批量缴费的数据
	 * @param teamId
	 * @return
	 */
	@Override
	public float[][] findBatchPayment(Integer schoolId, String termCode, Integer teamId){
		float[][] list = null;
		List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
		List<ChargeItem> itemList = chargeItemService.findBySchoolId(schoolId);
		List<ChargeVo> amountList = null;
		String amount = "";
		if (teamStudentList != null && itemList != null) {
			list = new float[teamStudentList.size()][itemList.size()];

			for (int i = 0; i < teamStudentList.size(); i++) {
				amountList = chargeDao.findAllItemByStudent(schoolId, termCode, teamId, teamStudentList.get(i).getStudentId());
				for (int j = 0; j < amountList.size(); j++) {
					amount = amountList.get(j).getAmount();
					if (amount == null || "".equals(amount)) {
						amount = "0";
					}
					list[i][j] = Float.valueOf(amount);
				}
			}
		}
		return list;
	}

	/**
	 * 保存批量缴费
	 * @param schoolId
	 * @param termCode
	 * @param gradeId
	 * @param teamId
	 * @param amounts
	 */
	@Override
	public void batchSetPayment(Integer schoolId, String termCode, Integer gradeId, Integer teamId, String[][] amounts){
		if (amounts != null) {
			//JSONArray jsonArray = JSONArray.fromObject(amounts);
			List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
			List<ChargeItem> itemList = chargeItemService.findBySchoolId(schoolId);
			Charge charge = null;
			Integer studentId = null;
			Integer itemId = null;

			for (int i = 0; i < teamStudentList.size(); i++) {
				//先按班级学生解析数据
				//JSONArray array = JSONArray.fromObject(jsonArray.get(i));
				studentId = teamStudentList.get(i).getStudentId();
				for (int j = 0; j < itemList.size(); j++) {
					String amount = "";
					itemId = itemList.get(j).getId();
					boolean isPay = true;
					//再按项目解析数据
//					if (array.get(j) != null && !"".equals(array.get(j)) ) {
//						amount = array.get(j);
//					}
					amount = amounts[i][j];
					if (amount == null || "".equals(amount) || "0".equals(amount)) {
						isPay = false;
						amount = "";
					}

					//判断记录是否存在，有则修改，无则添加
					charge = chargeDao.findUniqueStudent(itemId, termCode, teamId, studentId);
					if (charge != null) {
						charge.setIsPay(isPay);
						charge.setAmount(amount);
						charge.setModifyDate(new Date());
						chargeDao.update(charge);
					} else {
						charge = new Charge();
						charge.setSchoolId(schoolId);
						charge.setItemId(itemId);
						charge.setTermCode(termCode);
						charge.setGradeId(gradeId);
						charge.setTeamId(teamId);
						charge.setStudentId(studentId);
						charge.setIsPay(isPay);
						charge.setAmount(amount);
						charge.setIsDeleted(false);
						charge.setCreateDate(new Date());
						charge.setModifyDate(new Date());
						chargeDao.create(charge);
					}
				}
			}


		}
	}

	private void getAmounts(String amounts){
//		Integer teamStudentListSize = 10;
//		Integer itemListSize = 10;
//		String[][] list = new String[teamStudentListSize][itemListSize];
//		JSONArray jsonArray = JSONArray.fromObject(amounts);
//		for (Integer i = 0; i < teamStudentListSize; i++) {
//			JSONArray array = JSONArray.fromObject(jsonArray.get(i));
//			for (Integer j = 0; j < itemListSize; j++) {
//				String amount = "";
//				if (array.get(j) != null && !"".equals(array.get(j)) ) {
//					amount = array.get(j);
//				}
//				list[i][j] = amount;
//			}
//		}
	}

	/**
	 *	统计报表页面接口
	 */
	@Override
	public List<ChargeVo> findStatDate(Integer schoolId, String schoolYear, String termCode, Integer gradeId, Integer teamId, Integer itemId) {
		return chargeDao.findStatDate(schoolId, schoolYear, termCode, gradeId, teamId, itemId);
	}
}
