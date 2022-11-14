package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.SeatChart;
import platform.education.generalTeachingAffair.model.SeatChartItem;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.SeatChartItemService;
import platform.education.generalTeachingAffair.service.SeatChartService;
import platform.education.generalTeachingAffair.vo.SeatChartCondition;
import platform.education.generalTeachingAffair.vo.SeatChartItemVo;
import platform.education.generalTeachingAffair.vo.SeatChartVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.school.affair.model.Classroom;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.ClassroomCondition;
import platform.education.school.affair.vo.ClassroomVo;
import platform.education.user.model.Profile;
import platform.service.storage.holder.FileServiceHolder;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;




@Controller
@RequestMapping("/teach/seatChart")
public class SeatChartController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/seatChart";
	
	@Autowired
	@Qualifier("seatChartService")
	private SeatChartService seatChartService;
	
	@Autowired
	@Qualifier("seatChartItemService")
	private SeatChartItemService seatChartItemService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") SeatChartCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<SeatChart> items = this.seatChartService.findSeatChartByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 班主任设置座位表
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherSet")
	public ModelAndView teacherIndex(@CurrentUser UserInfo user,Model model){
		String viewPath = null;
		if(user.getTeacherId() != null){
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeacherId(user.getTeacherId());
			teamTeacherCondition.setSchoolYear(user.getSchoolYear());
			teamTeacherCondition.setType(1);
			List<TeamTeacherVo> teamTeacherList = this.teamTeacherService.findTeamTeacherVoByCondition(teamTeacherCondition);
			if(teamTeacherList.size()!=0){
				model.addAttribute("teamTeacherList",teamTeacherList);
				viewPath = structurePath("/teacherSetSeatChart");
			}
		}else{
			viewPath = structurePath("/noTeacher");
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SeatChart> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SeatChartCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.seatChartService.findSeatChartByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}
	/**
	 * 保存座位的操作
	 * @param seatChart
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(SeatChart seatChart, @CurrentUser UserInfo user) {
		seatChart.setSchoolId(user.getSchoolId());
		seatChart.setCreateDate(new Date());
		seatChart.setModifyDate(new Date());
		seatChart.setSeatType(1);//默认单人座
		seatChart.setRow(8);
		seatChart.setCol(6);//6列
		
		//2016-10-21 解决年级ID为空的问题
		if(seatChart != null && seatChart.getGradeId() == null && seatChart.getTeamId() != null){
			Team team = teamService.findTeamById(seatChart.getTeamId());
			if(team != null){
				seatChart.setGradeId(team.getGradeId());
			}
		}
		
		seatChart = this.seatChartService.add(seatChart);
		return seatChart != null ? new ResponseInfomation(seatChart.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 异步加载教室列表
	 */
	@RequestMapping(value = "/getAjaxClassRoomList", method = RequestMethod.POST)
	@ResponseBody
	public List<SeatChartVo> getAjaxTeamList(
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user) {
		if(teamId == null || "".equals(teamId)){
			return new ArrayList<SeatChartVo>(); 
		}
		List<SeatChart> seatChartList = this.seatChartService.findSeatChartOfTeamAndSchool(Integer.parseInt(teamId), user.getSchoolId());
		List<SeatChartVo> SeatChartVoList = new ArrayList<SeatChartVo>();
		
		List<SeatChartVo> NormalSeatChart = new ArrayList<SeatChartVo>();
		
		SeatChartVo vo = null;
		Classroom classroom = null;
		for(SeatChart seatChart :seatChartList){
			if(seatChart.getIsMainClassroom() == true){
				vo = new SeatChartVo();
				BeanUtils.copyProperties(seatChart, vo);
				vo.setClassroomTypeName("主课室");
				SeatChartVoList.add(vo);
				break;
			}
		}
		for(SeatChart seatChart :seatChartList){
			classroom = this.classroomService.findClassroomById(seatChart.getClassroomId());
			if(classroom!=null){
				vo = new SeatChartVo();
				BeanUtils.copyProperties(seatChart, vo);
				List<Map<String,Object>> result = this.jcGcCacheService.findByCondition("JY-JSLX", "value", seatChart.getClassroomType(), null,false);
				if(result.size()==1){
					Map<String,Object> map = result.get(0);
					String name = (String)map.get("name");
					vo.setClassroomTypeName(name);
					SeatChartVoList.add(vo);
				}
			}
		}
		return SeatChartVoList;
	}
	
	/**
	 * 异步加载学生列表
	 */
	@RequestMapping(value = "/getAjaxStudentList", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> getAjaxStudentList(
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user) {
		List<Student> studentList = this.studentService.findStudentOfTeam(Integer.parseInt(teamId));
		return studentList;
	}
	
	/**
	 * 设置座位的大小
	 * @param seatId
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/setSeatSize")
	public ModelAndView setSeatSize(@RequestParam(value = "seatId", required = true) String seatId,
			@CurrentUser UserInfo user,Model model) {
		this.seatChartItemService.deleteBySeatId(Integer.parseInt(seatId));
		model.addAttribute("seatId", seatId);
		return new ModelAndView(structurePath("/setSeatSize"), model.asMap());
	}
	/**
	 * 根据页面传来的数据改变座位的大小
	 * @param seatChart
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateSeatSize", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation updateSeatSize(SeatChart seatChart, @CurrentUser UserInfo user) {
		seatChart.setModifyDate(new Date());
		seatChart = this.seatChartService.modify(seatChart);
		return seatChart != null ? new ResponseInfomation(seatChart.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	/**
	 * 分配教室
	 * @param seatChart
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/setClassRoom")
	public ModelAndView setClassRoom(SeatChart seatChart,@CurrentUser UserInfo user,Model model) {
		model.addAttribute("seatChart", seatChart);
		return new ModelAndView(structurePath("/setClassRoom"), model.asMap());
	}
	/**
	 * 分配教室那里的大楼信息
	 * @param type
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getAjaxFloorList", method = RequestMethod.POST)
	@ResponseBody
	public List<Floor> getAjaxFloorList(
			@RequestParam(value = "type", required = true) String type,
			@CurrentUser UserInfo user) {
		ClassroomCondition classroomCondition = new ClassroomCondition();
		classroomCondition.setSchoolId(user.getSchoolId());
		classroomCondition.setType(type);
		classroomCondition.setIsDelete(false);
		List<Classroom> ClassRoomList = this.classroomService.findClassroomMoreByCondition(classroomCondition);
		List<Floor> floorList = new ArrayList<Floor>();
		for(Classroom cs : ClassRoomList){
			if(cs!=null){
				Floor floor = this.floorService.findFloorById(cs.getFloorId());
				floorList.add(floor);
			}
		}
		return floorList;
	}
	
	/**
	 * 分配教室页面的请求
	 * 根据教室的id获得教室的名字
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getClassroom", method = RequestMethod.POST)
	@ResponseBody
	public ClassroomVo getClassroom(
			@RequestParam(value = "id", required = true) String id,
			@CurrentUser UserInfo user) {
		Classroom classroom = this.classroomService.findClassroomById(Integer.parseInt(id));
		ClassroomVo vo = null;
		if(classroom!=null){
			vo = new ClassroomVo();
			BeanUtils.copyProperties(classroom, vo);
			Floor floor = this.floorService.findFloorById(classroom.getFloorId());
			if(floor!=null){
				vo.setFloorName(floor.getName());
			}
		}
		return vo;
	}
	
	/**
	 * 根据班级的Id和教室的类型查找分配的教室
	 * @param seatChartCondition
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getAjaxSeatChart", method = RequestMethod.POST)
	@ResponseBody
	public SeatChartVo getAjaxSeatChart(SeatChartCondition seatChartCondition,@CurrentUser UserInfo user) {
		seatChartCondition.setIsDelete(false);
		List<SeatChart> seatChartList = this.seatChartService.findSeatChartByCondition(seatChartCondition);
		SeatChart seatChart = null;
		//可能存在重复数据
		if(seatChartList.size()>=1){
			seatChart = seatChartList.get(0);
		}
		
		String floorName = "";
		SeatChartVo vo = new SeatChartVo(); 
		if(seatChart!=null){
			// 20170209修改 班级的主教室
			if(seatChart.getIsMainClassroom()){
				vo = new SeatChartVo();
				BeanUtils.copyProperties(seatChart, vo);
				Team team = this.teamService.findTeamById(seatChartCondition.getTeamId());
				vo.setClassroomName(team!=null?team.getName()+"主课室":"");
			}else{
				//不是主教室
				Classroom classroom = this.classroomService.findClassroomById(seatChart.getClassroomId());
				if(classroom!=null){
					Floor floor = this.floorService.findFloorById(classroom.getFloorId());
					if(floor!=null){
						floorName = floor.getName();
					}
				}
				vo = new SeatChartVo();
				BeanUtils.copyProperties(seatChart, vo);
				vo.setFloorName(floorName);
				vo.setClassroomStorey(classroom.getStorey());
				if(classroom.getPosition()!=null){
					vo.setClassroomPosition(classroom.getPosition());
				}else{
					vo.setClassroomPosition("");
				}
			}
			
		}
		return vo;
	}
	
	/**
	 * 保存座位表发送的请求
	 * 根据页面的json
	 * @param user
	 * @param seats
	 * @param seatId
	 * @return
	 */
	@RequestMapping(value = "/saveSeatItem", method = RequestMethod.POST)
	@ResponseBody
	public String addSeatItems(@CurrentUser UserInfo user,
			@RequestParam(value = "seats", required = true) String seats,
			@RequestParam(value = "seatId", required = true) String seatId ) {
		List<SeatChartItem> itemList = this.seatChartItemService.findBySeatId(Integer.parseInt(seatId));
		if(itemList.size()>0){
			this.seatChartItemService.deleteBySeatId(Integer.parseInt(seatId));
		}
		if(seats != null && !"".equals(seats)){
			JSONObject jsonObject = JSONObject.fromObject(seats);
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			try {
				for(int i=0; i<jsonArray.size() ;i ++){
					JSONObject json = (JSONObject) jsonArray.get(i);
					Integer position = Integer.parseInt(json.getString("position"));
					Integer studentId = Integer.parseInt(json.getString("studentId"));
				    addSeatChartItem(user.getSchoolId(), studentId, position, Integer.parseInt(seatId));
				}
				return ResponseInfomation.OPERATION_SUC;
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		return ResponseInfomation.OPERATION_FAIL;
	}
	
	public SeatChartItem addSeatChartItem(Integer schoolId, Integer studentId, Integer position, Integer seatId){
		SeatChartItem item = new SeatChartItem();
		item.setCreateDate(new Date());
	    item.setModifyDate(new Date());
	    item.setSchoolId(schoolId);
	    item.setSeatId(seatId);
	    item.setIsDelete(false);
	    item.setStudentId(studentId);
	    item.setPositionX(position/10);
	    item.setPositionY(position%10);
	    item = this.seatChartItemService.add(item);
	    return item;
	}
	
	/**
	 * 根据教室座位分配情况拿到座位
	 */
	@RequestMapping(value = "/getSeatItem", method = RequestMethod.POST)
	@ResponseBody
	public List<SeatChartItemVo> getSeatItem(@RequestParam(value = "seatId", required = true) String seatId,
			@CurrentUser UserInfo user) {
		List<SeatChartItemVo> ItemVoList = null;
		if(seatId != null && !"".equals(seatId)){
			ItemVoList = this.seatChartItemService.findSeatChartItemVoBySeatId(Integer.parseInt(seatId));
		}
		if(ItemVoList == null){
			ItemVoList = new ArrayList<SeatChartItemVo>();
		}
		return ItemVoList;
	}
	
	/**
	 * 得到一个班级没有座位的学生
	 * @param seatId
	 * @param teamId
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/loadStudentDoNotHasSeat", method = RequestMethod.POST)
	@ResponseBody
	public List<Student> loadStudentDoNotHasSeat(@RequestParam(value = "seatId", required = true) String seatId,
			@RequestParam(value = "teamId", required = true) String teamId,
			@CurrentUser UserInfo user) {
		Integer teamId1 = Integer.parseInt(teamId);
		Integer seatId1 = Integer.parseInt(seatId);
		List<Student> studentList = this.seatChartItemService.findStudentDoNotHasSeatInTeam(teamId1, seatId1);
		return studentList;
	}
	
	/**
	 * 判断一个班级不能有相同类型的教室
	 * @param user
	 * @param classroomType
	 * @param teamId
	 * @return
	 */
	@RequestMapping(value = "/checkClassroomType", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkClassroomType(
			@CurrentUser UserInfo user,
			@RequestParam(value = "classroomType", required = true) String classroomType,
			@RequestParam(value = "teamId", required = true) String teamId) {
		List<SeatChart> seatChartList = this.seatChartService.findSeatChartOfTeamAndSchool(Integer.parseInt(teamId), user.getSchoolId());
		String type = "";
		if(seatChartList.size()>0){
			for(SeatChart seat : seatChartList){
				type = seat.getClassroomType();
				if(type.equals(classroomType.trim())){
					return false;
				}
			}
			return true;
		}
		return true;
	}
	
	@RequestMapping(value = "/checkCol", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkCol(
			@CurrentUser UserInfo user,
			@RequestParam(value = "seatType", required = true) Integer seatType,
			@RequestParam(value = "col", required = true) Integer col) {
		if(seatType==1){
			return true;
		}
		if(seatType==2){
			if(col%2==0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		SeatChart seatChart = this.seatChartService.findSeatChartById(id);
		model.addAttribute("seatChart", seatChart);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		SeatChart seatChart = this.seatChartService.findSeatChartById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("seatChart", seatChart);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SeatChart seatChart) {
		if (seatChart != null) {
			seatChart.setId(id);
		}
		try {
			this.seatChartService.remove(seatChart);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SeatChart seatChart) {
		seatChart.setId(id);
		seatChart = this.seatChartService.modify(seatChart);
		return seatChart != null ? new ResponseInfomation(seatChart.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, SeatChartCondition condition) {
	}
	
	/**
	 * 公共方法利用用户的id获取图片的src
	 * @param userId
	 * @param request
	 * @return
	 */
	private String getImgSrc(Integer userId,HttpServletRequest request){
		String outPutHtml = "";
		String def = "/res/images/boby.jpg";
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null) {
			String icon = profile.getIcon();
			outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
		}
		if ("".equals(outPutHtml)) {
			outPutHtml = request.getContextPath() + def;
		}
		return outPutHtml;
	}
}
