package platform.szxyzxx.web.oa.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.oa.model.AcceptRepari;
import platform.education.oa.model.ApplyRepair;
import platform.education.oa.vo.AcceptRepariCondition;
import platform.education.oa.vo.AcceptRepariVo;
import platform.education.oa.vo.ApplyRepairCondition;
import platform.education.oa.vo.ApplyRepairVo;
import platform.education.school.affair.vo.FloorCondition;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.oa.service.WeiXiuTypeService;
import platform.szxyzxx.oa.vo.WeiXiuDaoChu;
import platform.szxyzxx.oa.vo.WeiXiuGong;
import platform.szxyzxx.oa.vo.WeiXiuType;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.RepairStatus;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oa/applyrepair")
public class ApplyRepairController extends BaseController{ 
	
	private final static String viewBasePath = "/oa/applyRepair";

	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;
	@Resource
	private BasicSQLService basicSQLService;
	@Autowired
	private WeiXiuTypeService weiXiuTypeService;
	@Autowired
	@Qualifier("asyncWechatNoticeService")
	private SystemWechatNotifyService notifyService;

	//维修工账号列表
	private String maintenanceWorkerAccountList="'7164219523','5383751330','7036398827','0835690738','6440679550'";

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "own", required = false) String own,
			@ModelAttribute("condition") ApplyRepairCondition condition,
			@ModelAttribute("page") Page page,
			@RequestParam(value = "approval", required = false) String approval,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setShcoolId(user.getSchoolId());
		condition.setIsDelete(false);
		if(own!=null && !"".equals(own)){
			condition.setProposerId(user.getId());
		}
		page.setPageSize(4);
		//2015-10-26 修改  首次进入页面不做数据查询操作，因为在页面又重新调用了一次数据的查询 所以将查询放置在list
		List<ApplyRepairVo> items = null;
		model.addAttribute("typeList",basicSQLService.find("select `name`,`id` from jc_gc_item where table_code='GB-BXLX' and `disable`=0"));
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			if(approval != null && approval.equals("approval")){
				Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
				items = this.applyRepairService.findRepairHasApprovalByTeacherId(teacher == null ? -1 : teacher.getId(),page,order);
			}else{
				items = this.applyRepairService.findApplyAndAcceptRepairByCondition(condition, page, order);
			}
			for(ApplyRepairVo aa:items){
				if(aa.getPictureId()!=null){
					FileResult file = fileService.findFileByUUID(aa.getPictureId());
					if (file != null) {
						aa.setPictureUrl(file.getHttpUrl());
					}
				}
			}
		} else {
			viewPath = structurePath("/index");
		}
		long count = this.applyRepairService.count(condition);
		model.addAttribute("approval", approval);
		model.addAttribute("items", items);
		model.addAttribute("count", count);
		model.addAttribute("ownType", own);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	public List<ApplyRepair> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ApplyRepairCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		List<ApplyRepair> list= this.applyRepairService.findApplyRepairByCondition(condition, page, order);
		for(ApplyRepair aa:list){
			if(aa.getPictureId()!=null){
				FileResult file = fileService.findFileByUUID(aa.getPictureId());
				if (file != null) {
					aa.setPictureUrl(file.getHttpUrl());
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user,Model model) {
		FloorCondition floorCondition = new FloorCondition();
		floorCondition.setIsDelete(false);
		floorCondition.setSchoolId(user.getSchoolId());
		model.addAttribute("typeList",basicSQLService.find("select `name`,`id` from jc_gc_item where table_code='GB-BXLX' and `disable`=0"));
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	public ResponseInfomation creator(ApplyRepair applyRepair, @CurrentUser UserInfo user) {
		applyRepair.setShcoolId(user.getSchoolId());
		applyRepair.setStatus(RepairStatus.apply);
		applyRepair.setProposerId(user.getId());
		applyRepair.setProposerName(user.getRealName());
		if(applyRepair.getAppointmentDate()==null){
			applyRepair.setAppointmentDate(new Date());
		}
		List<Teacher> teachers=teacherService.findTeacherByUserId(user.getId());
		if(teachers!=null && teachers.size()>0){
			applyRepair.setPhone(teachers.get(0).getMobile());
		}
		applyRepair = this.applyRepairService.add(applyRepair);
		//发送审核通知
		sendWechatNotice(applyRepair);

		return applyRepair != null ? new ResponseInfomation(applyRepair.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}


	private void sendWechatNotice(ApplyRepair applyRepair){
		List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyRepair.getShenherenId()+" and open_id is not null");
		if(notifyUserList!=null && notifyUserList.size()>0) {
			WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核通知","您有新的维修任务待审核");
			notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
		}
	}


	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		ApplyRepair applyRepair = this.applyRepairService.findApplyRepairById(id);
		if(applyRepair.getPictureId()!=null){
			FileResult file = fileService.findFileByUUID(applyRepair.getPictureId());
			if (file != null) {
				applyRepair.setPictureUrl(file.getHttpUrl());
			}
		}
		model.addAttribute("applyRepair", applyRepair);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ApplyRepair applyRepair = this.applyRepairService.findApplyRepairById(id);
		if(applyRepair.getPictureId()!=null){
			FileResult file = fileService.findFileByUUID(applyRepair.getPictureId());
			if (file != null) {
				applyRepair.setPictureUrl(file.getHttpUrl());
			}
		}
		model.addAttribute("isCK", "disable");
		model.addAttribute("applyRepair", applyRepair);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") Integer id, ApplyRepair applyRepair) {
		if (applyRepair != null) {
			applyRepair.setId(id);
		}
		try {
			this.applyRepairService.remove(applyRepair);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ApplyRepair applyRepair) {
		applyRepair.setId(id);
		applyRepair = this.applyRepairService.modify(applyRepair);
		return applyRepair != null ? new ResponseInfomation(applyRepair.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	//评价
	@RequestMapping(value = "/pjRepair/{id}", method = RequestMethod.PUT)
	public ResponseInfomation pjedit(@PathVariable(value = "id") Integer id,
			ApplyRepair applyRepair,@RequestParam(value = "appraise", required = false) String appraise) {
		applyRepair.setId(id);
		applyRepair = this.applyRepairService.modify(applyRepair);
		if(applyRepair.getPictureId()!=null){
			FileResult file = fileService.findFileByUUID(applyRepair.getPictureId());
			if (file != null) {
				applyRepair.setPictureUrl(file.getHttpUrl());
			}
		}
		AcceptRepariCondition acc = new AcceptRepariCondition();
		acc.setIsDelete(false);
		acc.setRepariId(id);
		List<AcceptRepari> list = acceptRepariService.findAcceptRepariByCondition(acc);
		Integer applyId = null;
		if(list.size() > 0){
			applyId = list.get(0).getId();
		}
		AcceptRepari acceptRepari = new AcceptRepari();
		acceptRepari.setId(applyId);
		acceptRepari.setAppraise(Integer.parseInt(appraise));
		//acceptRepari.setRemark(remark);
		acceptRepariService.modify(acceptRepari);
		return applyRepair != null ? new ResponseInfomation(applyRepair.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	//评价
	@RequestMapping(value = "/appraise", method = RequestMethod.POST)
	public ModelAndView appraise(
			@RequestParam(value = "applyId", required = true) Integer id,
			@RequestParam(value = "isCK", required = false) String isCK,
			Model model) {
		ApplyRepair applyRepair = this.applyRepairService.findApplyAndAcceptRepairById(id);
		if(applyRepair.getPictureId()!=null){
			FileResult file = fileService.findFileByUUID(applyRepair.getPictureId());
			if (file != null) {
				applyRepair.setPictureUrl(file.getHttpUrl());
			}
		}
		if(applyRepair.getWholePicture() != null){
			FileResult file = fileService.findFileByUUID(applyRepair.getWholePicture());
			if(file != null){
				String imgUrl = file.getHttpUrl();
				model.addAttribute("imgUrl", imgUrl);
			}
		}
		model.addAttribute("applyRepair", applyRepair);
		model.addAttribute("isCK", isCK);
		return new ModelAndView(structurePath("/pjInput"), model.asMap());
	}
	
	//申请
	@RequestMapping(value = "/myApply", method = RequestMethod.POST)
	public ModelAndView myApply(
			@RequestParam(value = "applyId", required = true) Integer id,
			@CurrentUser UserInfo user,
			Model model) {
		ApplyRepair applyRepair = this.applyRepairService.findApplyRepairById(id);
		if(applyRepair.getPictureId()!=null){
			FileResult file = fileService.findFileByUUID(applyRepair.getPictureId());
			if (file != null) {
				applyRepair.setPictureUrl(file.getHttpUrl());
			}
		}
		model.addAttribute("applyRepair", applyRepair);
		return new ModelAndView(structurePath("/sqInput"), model.asMap());
	}
	
	//统计
	@RequestMapping(value = "/applyCount", method = RequestMethod.POST)
	public ModelAndView applyCount(Model model,@CurrentUser UserInfo user,
			@RequestParam(value = "selectMonth", required = false) String selectMonth) {
		long tatol = 0;
		long already = 0;
		long wait = 0;
		long being = 0;
		long notreapair = 0;
		int all = 0;
		String url = structurePath("/repairCount");
		String month[] = selectMonth.split("-");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟  
		Date lastDate = null;
		Date firstDate = null;
		try {
			firstDate = sdf.parse(month[0]);
			lastDate = sdf.parse(month[1]);
		} catch (ParseException e) {
			return new ModelAndView(url, model.asMap());
		}  
		
		ApplyRepairCondition applyRepairCondition = new ApplyRepairCondition();
		applyRepairCondition.setShcoolId(user.getSchoolId());
		applyRepairCondition.setBeginDate(firstDate);
		applyRepairCondition.setEndDate(lastDate);
		
		tatol = applyRepairService.count(applyRepairCondition); 
		
		applyRepairCondition.setStatus(RepairStatus.apply);
		wait = applyRepairService.count(applyRepairCondition);
		
		applyRepairCondition.setStatus(RepairStatus.receive);
		being = applyRepairService.count(applyRepairCondition);
		
		applyRepairCondition.setStatus(RepairStatus.finish);
		already = applyRepairService.count(applyRepairCondition);
		
		applyRepairCondition.setStatus(RepairStatus.notReceive);
		notreapair = applyRepairService.count(applyRepairCondition);
		
		AcceptRepariCondition acceptRepariCondition = new AcceptRepariCondition();
		acceptRepariCondition.setIsDelete(false);
		acceptRepariCondition.setSchoolId(user.getSchoolId());
		acceptRepariCondition.setBeginDate(firstDate);
		acceptRepariCondition.setEndDate(lastDate);
		List<AcceptRepariVo> list = this.acceptRepariService.findAcceptRepariByConditionAndGroup(acceptRepariCondition);
		
		if(list.size() > 0){
			for(int i = 0;i < list.size(); i++){
				if(list.get(i).getAppraiseNum()!=null && !"".equals(list.get(i).getAppraiseNum())){
					all += list.get(i).getAppraiseNum();
				}
			}
		}
		
		if(list.size() > 0){
			all =  Math.round(all/list.size());
			if(all==0){
				all=-1;
			}
		}else{
			all = 0;
		}
		
		model.addAttribute("all",all);
		model.addAttribute("tatol", tatol);
		model.addAttribute("already", already);
		model.addAttribute("wait", wait);
		model.addAttribute("being", being);
		model.addAttribute("notreapair", notreapair);
		model.addAttribute("items", list);
		return new ModelAndView(url, model.asMap());
	}
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, ApplyRepairCondition condition) {
	}

	//-------------------------新增维修类型自定义以及维修工绑定-------------------
	/*
	 * 维修列表
	 */
	@RequestMapping("/typeAll")
	public List<WeiXiuType> fingByaLLwEIxIY(){
		return weiXiuTypeService.findByAll();
	}

	/*
	* 添加维修类型
	*/
	@RequestMapping("/createWeiType")
	public String createWeiType(WeiXiuType weiXiuType){
		List<Map<String,Object>> map=basicSQLService.find("select `name`,`id` from jc_gc_item where table_code='GB-BXLX' and `disable`=0");
		weiXiuType.setSortOrder(map.size());
		weiXiuType.setValue(map.size());
		return weiXiuTypeService.create(weiXiuType);
	}
	/*
	 * 删除维修类型
	 */
	@RequestMapping("/deleteWeiType")
	public String createWeiType(@RequestParam Integer id){
		return weiXiuTypeService.updateDelete(id);
	}

	/*
	 * 修改维修类型
	 */
	@RequestMapping("/updateWeiType")
	public String updateWeiType(WeiXiuType weiXiuType){
		return weiXiuTypeService.update(weiXiuType);
	}
	/*
	 * 根据id查询
	 */
	@RequestMapping("/findByWeiXiuId")
	public WeiXiuType findByWeiXiuId(@RequestParam Integer id){
		return weiXiuTypeService.findById(id);
	}
	//-----绑定维修工----
	/*
	 * 根据id查询
	 */
	@RequestMapping("/findByWeiXiuGongAll")
	public List<WeiXiuGong> findByWeiXiuGongAll(@RequestParam Integer atId){
		return weiXiuTypeService.findByWeiXiuGongAll(atId);
	}
	/*
	 * 添加维修工
	 */
	@RequestMapping("/createWeiXiuGong")
	public String createWeiXiuGong(WeiXiuGong weiXiuGong){
		return weiXiuTypeService.createWeiXiuGong(weiXiuGong);
	}
	/*
	 * 删除维修工
	 */
	@RequestMapping("/updateWeiXiuGongDelete")
	public String updateWeiXiuGongDelete(@RequestParam Integer id){
		return weiXiuTypeService.updateWeiXiuGongDelete(id);
	}

	/*
	* 维修工下拉框
	*/
	@RequestMapping("/findBaoXiuRen")
	public List<Map<String,Object>> findBaoXiuRen(@RequestParam Integer typeId){
		List<Map<String,Object>> list=basicSQLService.find("select zw.*,pt.name as teacherName from zy_weixiugong zw inner join pj_teacher pt on pt.user_id=zw.teacher_id  where zw.at_id="+typeId+" and zw.is_delete=0");
		//116712
		return list;
	}

	/*
	 * 审核人下拉框
	 */
	@RequestMapping("/findshenheren")
	public List<Map<String,Object>> findshenheren(){
		List<Map<String,Object>> list=basicSQLService.find(" select pt.* from yh_user_role yur" +
				"        inner join yh_role yr on yr.id=yur.role_id" +
				"        inner join pj_teacher pt on pt.user_id=yur.user_id" +
				"        where 1=1 and yr.code='SCHOOL_SUPPORT_STAFF'" +
				"        and yr.group_id=264");
		return list;
	}

	/*
	 * 维修工列表
	 */
	@RequestMapping("/weixiugongLie")
	public ModelAndView weixiugongLie(@RequestParam Integer id,
									  @RequestParam(value = "name",required =false) String name,
									  @RequestParam(value = "sub",required =false) String sub,
									  @ModelAttribute Page page,Model model){
		List<Teacher> teacher =weiXiuTypeService.findByjiaoshiliebiao(name,page);
		String batUrl="";
		if(sub!=null){
			if(sub.equals("list")){
				batUrl="/oa/applyRepair/weixiurenlist";
			}else{
				batUrl="/oa/applyRepair/weixiuren";
			}
		}else{

			batUrl="/oa/applyRepair/weixiuren";
		}
		model.addAttribute("id",id);
		model.addAttribute("list",teacher);
		return new ModelAndView(batUrl,model.asMap());
	}



	/*
	 * 审核列表
	 */
	@RequestMapping("/shenheView")
	public ModelAndView shenheView(@RequestParam(value = "shenqingren",required =false) String shenqingren,
								   @RequestParam(value = "shenherenId",required =false) Integer shenherenId,
								   @RequestParam(value = "typeId",required =false) Integer typeId,
								   @RequestParam(value = "isShenhe",required =false) Integer isShenhe,
								   @RequestParam(value = "sub",required =false) String sub,
								   @CurrentUser UserInfo userInfo,
								   @ModelAttribute Page page,Model model){
		List<ApplyRepair> applyRepairs =applyRepairService.findByshenhe(shenqingren,shenherenId,typeId,isShenhe,page);

		for(ApplyRepair aa:applyRepairs){
			if(aa.getPictureId()!=null){
				FileResult file = fileService.findFileByUUID(aa.getPictureId());
				if(file != null){
					String imgUrl = file.getHttpUrl();
					aa.setPictureUrl(imgUrl);
				}
			}
		}
		String batUrl="";
		if(sub!=null){
			if(sub.equals("list")){
				batUrl="/oa/applyRepair/weixiushenhelist";
			}else{
				batUrl="/oa/applyRepair/weixiushenhe";
			}
		}else{
			batUrl="/oa/applyRepair/weixiuren";
		}
		model.addAttribute("weixiuList",applyRepairs);
		model.addAttribute("userid",userInfo.getId());

		return new ModelAndView(batUrl,model.asMap());
	}
		/*
		* 查看详情跳转
		*/

	@RequestMapping("/shenhexiangqing")
	public ModelAndView shenheView(@RequestParam Integer id,
								   Model model){
		ApplyRepair applyRepairs =applyRepairService.findByshenheId(id);

			if(applyRepairs.getPictureId()!=null){
				FileResult file = fileService.findFileByUUID(applyRepairs.getPictureId());
				if(file != null){
					String imgUrl = file.getHttpUrl();
					applyRepairs.setPictureUrl(imgUrl);
				}
			}
		String batUrl="/oa/applyRepair/weixiuxiangqing";
		model.addAttribute("weixiuList",applyRepairs);
		return new ModelAndView(batUrl,model.asMap());
	}
	/*
	* 审核
	*/
	@RequestMapping("/shenhe")
	public String shenheView(@RequestParam Integer id,
							 @RequestParam(value = "weixiugong",required = false) Integer weixiugong,
							 @RequestParam(value = "liyou",required = false) String  liyou,
							 @RequestParam Integer isShenHe
								  ){
		String str=applyRepairService.updateShenhe(id,weixiugong,liyou,isShenHe);
		ApplyRepair applyRepairs =applyRepairService.findByshenheId(id);
		if(isShenHe==1){
			//审核成功
			List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyRepairs.getShenherenId()+" and open_id is not null");
			if(notifyUserList!=null && notifyUserList.size()>0) {
				WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您的一个维修申请已通过");
				notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
			}
		}else{
			//审核失败通知
			List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+applyRepairs.getShenherenId()+" and open_id is not null");
			System.out.println(notifyUserList.size());
			if(notifyUserList!=null && notifyUserList.size()>0) {
				WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("审核结果通知","您的一个维修申请未通过");
				notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
			}
		}
     return str;
	}

	/*
	* 导出
	*/
	@RequestMapping("/finddaochuWeixiu")
	public String finddaochuWeixiu(
			                       @RequestParam String qwer,
								   @RequestParam(value = "weixiuren",required = false) String weixiuren,
								   @RequestParam(value = "typeId",required = false) Integer typeId,
								   @RequestParam(value = "startTime",required = false) String startTime,
								   @RequestParam(value = "endTime",required = false) String endTime,
								   HttpServletResponse response, HttpServletRequest request){
		List<Object> list = new ArrayList();
		List<WeiXiuDaoChu> list2 = weiXiuTypeService.findByDaoChu(weixiuren,typeId,startTime,endTime);
		ParseConfig config = SzxyExcelTookit.getConfig("WeiXiu");
		StringBuffer excelName = new StringBuffer();
		excelName.append("维修记录表.xls");
		String filename = excelName.toString();
		try {
			for (WeiXiuDaoChu weiXiuDaoChu : list2) {
				if (weiXiuDaoChu.getIsHaoCai() != null) {
					if (weiXiuDaoChu.getIsHaoCai()==1){
						weiXiuDaoChu.setHaoCai("是");
					}else{
						weiXiuDaoChu.setHaoCai("否");
					}
					list.add(weiXiuDaoChu);
				}
			}
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}







}
