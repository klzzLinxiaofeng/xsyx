package platform.szxyzxx.web.teach.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.szxyzxx.web.common.controller.base.BaseController;


/**
 * 添加学生考勤信息的数字部分的验证
 * @author huangyanchun
 *
 */
@Controller
@RequestMapping("/teach/studentCheckAttendance/ajax")
public class StudentCheckAttendanceAjaxController extends BaseController {

	//private static final Logger log = LoggerFactory.getLogger(StudentCheckAttendanceAjaxController.class);
			
	//private final static String viewBasePath = "/teach/studentCheckAttendance";
	
	
	
	//验证天数
	@SuppressWarnings("unused")
	@RequestMapping(value = "checkDayNumber", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkDayNumber(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "inDayNumber") String inDayNumber,
			@RequestParam(value = "id", required = false) Integer id){
		
		boolean isDayNumber = true;
		if("inDayNumber".equals(dxlx)){
			BigDecimal dayNumber = null;
			try {
				dayNumber = new BigDecimal(inDayNumber);
			} catch (Exception e) {
				isDayNumber = false;
			}
		}else{
			isDayNumber = false;
		}
		
		return isDayNumber;
	}
	
	
	//验证节数
	@SuppressWarnings("unused")
	@RequestMapping(value = "checkNodeNumber", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkNodeNumber(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "inNodeNumber") String inNodeNumber,
			@RequestParam(value = "id", required = false) Integer id){
		
		boolean isNodeNumber = true;
		if("inNodeNumber".equals(dxlx)){
			BigDecimal nodeNumber = null;
			try {
				nodeNumber = new BigDecimal(inNodeNumber);
			} catch (Exception e) {
				isNodeNumber = false;
			}
		}else{
			isNodeNumber = false;
		}
		
		return isNodeNumber;
	}
	
	//验证次数
		@SuppressWarnings("unused")
		@RequestMapping(value = "checkOrderumber", method = RequestMethod.GET)
		@ResponseBody
		public boolean checkOrderumber(
				@RequestParam(value = "dxlx", required = false) String dxlx,
				@RequestParam(value = "inOrderNumber") String inOrderNumber,
				@RequestParam(value = "id", required = false) Integer id){
			
			boolean isOrderNumber = true;
			if("inOrderNumber".equals(dxlx)){
				BigDecimal orderNumber = null;
				try {
					orderNumber = new BigDecimal(inOrderNumber);
				} catch (Exception e) {
					isOrderNumber = false;
				}
			}else{
				isOrderNumber = false;
			}
			
			return isOrderNumber;
		}
}
