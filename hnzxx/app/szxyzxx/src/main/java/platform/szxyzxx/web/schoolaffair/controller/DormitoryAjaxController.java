package platform.szxyzxx.web.schoolaffair.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;


/**
 * 发送ajax请求得到对应的宿舍号
 * @author huangyanchun
 *
 */
@Controller
@RequestMapping("/schoolaffair/dormitoryCodeListAjax")
public class DormitoryAjaxController extends BaseController{

	
	/**
	 * 异步获取寝室编号
	 * @param user
	 * @param floorCode
	 * @return
	 */
	@RequestMapping(value = "/getDormitoryCodeList", method = RequestMethod.POST)
	@ResponseBody
	public List <Dormitory>getDormitoryCodeList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "floorCode", required = true) String floorCode
			
			){
		DormitoryCondition condition = new DormitoryCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setFloorCode(floorCode);
		condition.setIsDeleted(0);
		List<Dormitory>dormitoryCodeList  = this.dormitoryService.findDormitoryByCondition(condition,null,null);
		
		return dormitoryCodeList;
	}
	
	
	//验证入住
			@SuppressWarnings("unused")
			@RequestMapping(value = "checkCapacity", method = RequestMethod.GET)
			@ResponseBody
			public boolean checkOrderumber(
					@RequestParam(value = "dxlx", required = false) String dxlx,
					@RequestParam(value = "inCapacity") String inCapacity,
					@RequestParam(value = "id", required = false) Integer id){
				
				boolean isCapacity = true;
				if("inCapacity".equals(dxlx)){
					BigDecimal capacity = null;
					try {
						capacity = new BigDecimal(inCapacity);
					} catch (Exception e) {
						isCapacity = false;
					}
				}else{
					isCapacity = false;
				}
				
				return isCapacity;
			}
}
