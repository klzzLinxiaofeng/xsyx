package platform.szxyzxx.web.oa.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.*;
import platform.education.notice.model.Notice;
import platform.education.notice.model.NoticeFile;
import platform.education.notice.vo.NoticeCondition;
import platform.education.oa.service.SchoolNoticeService;
import platform.education.user.model.UserRole;
import platform.education.user.service.UserRoleService;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.notice.service.NewNoticeService;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.oa.vo.Notices;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.oa.contans.ContansOfOa;
import platform.szxyzxx.wechat.template.NoticeWechatMessageTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公告控制类
 * 
 * @author sky
 * @version 1.0 2015-6-10
 */

@Controller
@RequestMapping(value = "/office/notice")
public class NoticeController extends BaseController {
	private final static String BASE_PATH = "oa/notice/";

	@Autowired
	private SchoolNoticeService schoolnoticeService;
	@Autowired
	@Qualifier("asyncWechatNoticeService")
	private SystemWechatNotifyService notifyService;

	@Autowired
	TeamUserService teamUserService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	TeamService teamService;

	@Autowired
	TeamStudentService teamStudentService;

	@Autowired
	StudentService studentService;

	@Autowired
	TeamTeacherService teamTeacherService;
	@Autowired
	private BasicSQLService basicSQLService;

	@Autowired
	private NewNoticeService newNoticeService;

	/**
	 * 通知首页
	 * 
	 * 
	 *
	 */

	@RequestMapping(value = "newIndex")
	public ModelAndView newList(
			@RequestParam(value = "sub", required = false) String sub,
			NoticeCondition condition,
			@ModelAttribute("page") Page page,
			Model model,
			@CurrentUser UserInfo user) {

		//区分去首页还是数据列表页面
		String path="newIndex";
		if("list".equals(sub)){
			path="newList";
		}
		page.setPageSize(4);
		String sql=buildQuerySql(condition.getReceiverType(),user.getId(),condition.getTitle());
		List<Map<String,Object>> items=basicSQLService.findByPaging(page,sql);
		model.addAttribute("cuurenLogin", user.getId());
		model.addAttribute("items", items);
		model.addAttribute("sub", sub);
		model.addAttribute("receiverType",condition.getReceiverType());
		model.addAttribute("totalSize", page.getTotalRows());
		model.addAttribute("isTeacher", teamTeacherService.userIsTeamManager(user.getId()));
		return new ModelAndView(BASE_PATH + path, model.asMap());
	}


	private String buildQuerySql(String receiverType,Integer currUserId,String title){
		String sql=null;
		if(receiverType!=null && !receiverType.isEmpty()){
			if("own".equals(receiverType)){
				sql= "select n.*  from pub_notice n where n.poster_id="+currUserId;
			}else{
				sql="SELECT n.* FROM pub_notice n LEFT JOIN pub_notice_receiver r ON r.notice_id = n.id AND r.receiver_id = "+currUserId+" WHERE n.receiver_type ='"+receiverType+"' AND r.id IS NOT NULL";
			}
			if(title!=null && !title.isEmpty()){
				sql+=" and n.title like '%"+title+"%'";
			}
			sql+=" order by n.create_date desc";
 
		}
		return sql;
	}


	/**
	 * 去往发通知页面
	 * 
	 * 
	 *
	 */
	@RequestMapping(value = "createNotice")
	public ModelAndView createNotice(@CurrentUser UserInfo user,Model model) {

		List<TeamUser> teamUsers =teamUserService.findByUserIdAndIsMaster(user.getId(),true);
		//查询用户班级id列表
		if(teamUsers != null && teamUsers.size() != 0){
			List<Integer> teamIds = new ArrayList<>();
			for (TeamUser teamUser : teamUsers) {
				teamIds.add(teamUser.getTeamId());
			}
			model.addAttribute("teamIds",teamIds);
		}
		model.addAttribute("isSchoolOperator", isSchoolOperator(user));
		return new ModelAndView(BASE_PATH + "newInput", model.asMap());
	}



	/**
	 * 判断用户是否为运营人员的角色
	 * @param user
	 * @return
	 */
	public boolean isSchoolOperator(UserInfo user){
		List<UserRole> userRoles = userRoleService.findByUserId(user.getId());
		for (UserRole userRole : userRoles) {
			if(userRole.getRole().getCode().equals("SCHOOL_OPERATOR")){
				return true;
			}
		}
		return false;
	}

	/**
	 * 发通知
	 * 
	 * 
	 *
	 */
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addNotice(Notices oaNotice,
										String deptIds,
										String teacherIds,
										String uuids,
										Integer teamId,
										String sfhz,
										String targetTypes,
										String textContent,
										@CurrentUser UserInfo user) {
		try {
			String[] s = new String[0];
			if(uuids!=null && !uuids.isEmpty()) {
				s=uuids.split(",");
			}
			oaNotice.setPostTime(new Date());
			oaNotice.setPosterId(user.getId());
			//部门通知发送者为部门名称
			if(oaNotice.getReceiverType().equals("dept")){
				oaNotice.setReceiverName(oaNotice.getAppKey());
				oaNotice.setAppKey(user.getRealName());
			}else{
				oaNotice.setPosterName(user.getRealName());
			}
			oaNotice.setReadCount(0);
			oaNotice.setUserCount(0);
			List<String> noticeFiles = new ArrayList<String>();
			if (s.length > 0) {
				for (String uid : s) {
					if (!uid.equals("")) {
						noticeFiles.add(uid);
					}
				}
			}

			String[] targetTypeArr = targetTypes.split(",");
			List<String> insertReceiverSqlList=new ArrayList<>();

			String nowYear=basicSQLService.getNowSchoolYear();

			//部门和个人人员列表需要去重
			if(targetTypes.contains(ContansOfOa.NoticeType_dept) && targetTypes.contains(ContansOfOa.NoticeType_person)){
				insertReceiverSqlList.add("INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) SELECT #noticeId, '老师' AS receiver_type, t.user_id, t.`name`, now(), now(), 0 FROM pj_teacher t WHERE t.school_id=215 and t.is_delete=0 and ( t.id IN("+teacherIds+") OR exists(select 1 from pj_department_teacher dt where dt.teacher_id=t.id and dt.is_deleted=0 and dt.department_id in ("+deptIds+") ) )");
			}else {
				for (String targetType : targetTypeArr) {
					String insertReceiverSql = null;
					//设置通知接收人
					//班级
					if (targetType.equals(ContansOfOa.NoticeType_team)) {
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, '学生' AS receiver_type, s.user_id, s.`name`, now() , now(), 0 FROM pj_student s inner join yh_user yu on yu.id=t.user_id  inner JOIN pj_team_student ts ON ts.student_id = s.id inner join pj_team t on t.id=ts.team_id WHERE s.school_id=215 and s.is_delete = 0 and ts.is_delete=0 and t.school_year='"+nowYear+"' and t.id =" + teamId;
					} else if (targetType.equals(ContansOfOa.NoticeType_dept)) {
						//部门
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, '老师' AS receiver_type, t.user_id, t.`name`, now(), now(), 0 FROM pj_teacher t inner join yh_user yu on yu.id=t.user_id WHERE yu.state=0 and t.school_id=215 and t.is_delete=0 and exists(select 1 from pj_department_teacher dt where dt.teacher_id=t.id and dt.is_deleted=0 and dt.department_id in ("+deptIds+") )";
					} else if (targetType.equals(ContansOfOa.NoticeType_person)) {
						//个人（指定的老师）
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, '老师' AS receiver_type, t.user_id, t.`name`, now() , now(), 0 FROM pj_teacher t WHERE t.id IN (" + teacherIds + ")";
					} else if (targetType.equals(ContansOfOa.NoticeType_school)) {
						//所有人（所有老师和学生）
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, t.receiver_type, t.user_id, t.`name`, now() , now(), 0 FROM ( SELECT s.user_id, s.`name`, '学生' AS receiver_type FROM pj_student s inner JOIN pj_team_student ts ON ts.student_id = s.id inner join pj_team tm on tm.id=ts.team_id WHERE s.school_id=215 and s.is_delete = 0 and ts.is_delete=0 and tm.school_year='"+nowYear+"'  UNION SELECT te.user_id, te.`name`, '老师' AS receiver_type FROM pj_teacher te WHERE te.school_id=215 and te.is_delete=0 ) t";
					} else if (targetType.equals(ContansOfOa.NoticeType_allTeacher)) {
						//所有老师
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, '老师' AS receiver_type, t.user_id, t.`name`, now() , now(), 0 FROM pj_teacher t inner join yh_user yu on yu.id=t.user_id WHERE yu.state=0 and t.school_id=215 and t.is_delete=0";
					} else if (targetType.equals(ContansOfOa.NoticeType_allStudent)) {
						//所有学生
						insertReceiverSql = "INSERT INTO pub_notice_receiver (notice_id, receiver_type, receiver_id, receiver_name, create_date , modify_date, is_deleted) " +
								" SELECT #noticeId, '学生' AS receiver_type, s.user_id, s.`name`, now() , now(), 0 FROM pj_student s inner JOIN pj_team_student ts ON ts.student_id = s.id inner join pj_team t on t.id=ts.team_id WHERE s.school_id=215 and s.is_delete = 0 and ts.is_delete=0 and t.school_year='"+nowYear+"'";
					} else {
						return new ResponseInfomation("参数receiverType有误",
								ResponseInfomation.OPERATION_ERROR);
					}
					insertReceiverSqlList.add(insertReceiverSql);
				}
			}
			oaNotice=newNoticeService.addNoticeAndReceiver(oaNotice,sfhz,textContent,noticeFiles,insertReceiverSqlList);
			/*if(uuids!=null && !uuids.isEmpty()) {
				NoticeFile noticeFile = new NoticeFile();
				noticeFile.setNoticeId(oaNotice.getId());
				noticeFile.setFileUuid(uuids);
				noticeFile.setIsDeleted(true);
				noticeFile.setCreateDate(new Date());
				noticeFile.setModifyDate(new Date());
				newNoticeService.create(noticeFile);
			}*/
			//发送微信通知 之前的
			// 2022.08，08 zy修改 微信通知应该需要审核通过后在进行发送
			//sendWechatNotice(oaNotice);
			return new ResponseInfomation(oaNotice.getId(),
					ResponseInfomation.OPERATION_SUC);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}

	}


	private void sendWechatNotice(Notices sysNotice){
		//用户openId以及家长openId
		List<Map<String,Object>> notifyUserList=basicSQLService.find("SELECT pu.open_id AS parent_open_id, u.open_id FROM pub_notice_receiver r LEFT JOIN yh_user u ON u.id = r.receiver_id LEFT JOIN pj_parent_student ps ON ps.student_user_id = r.receiver_id AND ps.is_delete = 0 AND ps.rank = 1 LEFT JOIN yh_user pu ON pu.id = ps.parent_user_id WHERE r.notice_id = "+sysNotice.getId()+" AND (u.open_id IS NOT NULL OR pu.open_id IS NOT NULL)");
		if(notifyUserList!=null && notifyUserList.size()>0) {
			NoticeWechatMessageTemplate messageTemplate = new NoticeWechatMessageTemplate(sysNotice.getReceiverType(), sysNotice.getTitle());

			for (Map<String, Object> map : notifyUserList) {
				//此处要区分学生和老师，老师直接发送给本人，学生发送给家长
				String openId=(String) map.get("open_id");
				String parentOpenId=(String) map.get("parent_open_id");
				if(StringUtils.isNotEmpty(parentOpenId)){
					openId=parentOpenId;
				}
				if(StringUtils.isEmpty(openId)){
					continue;
				}
				notifyService.sendWechatNotice(messageTemplate,openId,null);
			}
		}
	}




	/**
	 * 删除通知
	 * 
	 * 
	 *
	 */
	@RequestMapping(value = "delNotice/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteNotice(@PathVariable(value = "id") Integer id) {
		try {
			basicSQLService.updateBatch("delete from pub_notice where id="+id,"delete from pub_notice_file where notice_id="+id,"delete from pub_notice_read where notice_id="+id);
			return ResponseInfomation.OPERATION_SUC;
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}

	}

	/**
	 * 查看通知详情
	 * 
	 * 
	 *
	 */

	@RequestMapping(value = "readContext")
	public ModelAndView readContext(Model model,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "receiverType", required = false) String receiverType,
			@CurrentUser UserInfo user) {
		String path = "newContext";
		Notice notice = this.schoolnoticeService.findNoticeById(id);
		List<NoticeFile> noticefiles = this.schoolnoticeService
				.findNoticeFiles(notice, null, null);
		List<EntityFile> entitys = new ArrayList<EntityFile>();
		if (noticefiles != null) {
			for (NoticeFile noticefile : noticefiles) {
				EntityFile entity = this.entityFileService
						.findFileByUUID(noticefile.getFileUuid());
				if (entity != null) {
					entitys.add(entity);
				}
			}
		}

		//更新已读状态
		if (!"own".equals(receiverType)) {
			Object rid=basicSQLService.findUnique("select id from pub_notice_receiver where notice_id="+id+" and receiver_id="+user.getId()+" and `read`=0");
			if(rid!=null){
				String sql="update pub_notice_receiver set `read`=1,modify_date=now() where `read`=0 and id="+rid+";update pub_notice set read_count=read_count+1 where id="+id+" and read_count<user_count";
				basicSQLService.updateBatchByStr(sql);
			}
		}
		//没有源码，又不想多写一个mapper.xml
		if(notice!=null) {
			List<Map<String, Object>> mapList = basicSQLService.find("select is_reply from pub_notice where id=" + notice.getId());
			model.addAttribute("sfhz",mapList.get(0).get("is_reply"));
		}
		model.addAttribute("entity", entitys);
		model.addAttribute("notice", notice);
		return new ModelAndView(BASE_PATH + path, model.asMap());

	}

	/**
	 * 查看浏览详情
	 * 
	 * 
	 *
	 */
	@RequestMapping(value = "readDetail")
	public ModelAndView readDetail(Model model,Integer noticeId,Page page,Integer read,String receiverName,Boolean isQuery) {
		String sql="select receiver_name,receiver_type,modify_date,`read` from pub_notice_receiver where notice_id="+noticeId;
		if(read==null){
			read=0;
		}
		sql+=" and `read`="+read;
		if(receiverName!=null && !receiverName.isEmpty()){
			sql+=" and receiver_name like '%"+receiverName+"%'";
		}
		List list=basicSQLService.findByPaging(page,sql);
		model.addAttribute("list",list);
        model.addAttribute("noticeId",noticeId);
        model.addAttribute("isQuery",isQuery);
		return new ModelAndView(BASE_PATH + "newReadDetail", model.asMap());
	}

	/**
	 * 通知审批首页
	 *
	 *
	 *
	 */

	@RequestMapping(value = "/newShenpiList")
	public ModelAndView newShenpiList(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "zhuangtai",required = false) Integer zhuangtai,
			@RequestParam(value = "name",required = false) String name,
			@ModelAttribute("page") Page page,
			Model model,
			@CurrentUser UserInfo user) {
		String sql1="select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+user.getId();
		List<Map<String,Object>> list=basicSQLService.find(sql1);
		int a=0;
		for(Map<String,Object> bb:list){
			if(bb.get("code").toString().equals("GONGGAO_GUANLIYUAN")){
				model.addAttribute("quanxian", true);
				a=1;
			}
		}
		if(a==0){
			model.addAttribute("quanxian", false);
		}
		//区分去首页还是数w据列表页面
		String path="/oa/notice/shenpi/index";
		if("list".equals(sub)){
			path="/oa/notice/shenpi/list";
		}
		page.setPageSize(4);
		String sql="select *  from pub_notice where is_deleted=0 and receiver_name='tzsp' ";
		if(zhuangtai!=null && !zhuangtai.equals("")){
			sql+="  and zhuangtai="+zhuangtai;
		}
		if(name!=null && !name.equals("")){
			sql+="  and title like '%"+name+"%'";
		}
		sql+=" order by create_date desc";
		List<Map<String,Object>> items=basicSQLService.findByPaging(page,sql);
		model.addAttribute("cuurenLogin", user.getId());
		model.addAttribute("items", items);
		model.addAttribute("sub", sub);
		model.addAttribute("totalSize", page.getTotalRows());
		model.addAttribute("isTeacher", teamTeacherService.userIsTeamManager(user.getId()));
		return new ModelAndView(path, model.asMap());
	}
	/**
	 * 审批通知
	 *
	 *
	 *
	 */
	@RequestMapping(value = "/shenpi")
	@ResponseBody
	public String updateShenpi(@RequestParam(value = "id") Integer id,
							   @RequestParam(value = "zhuangtai") Integer zhuangtai) {
		Notices notices=newNoticeService.findById(id);
		if(zhuangtai==1){
			newNoticeService.updateShenpi( id, zhuangtai);
			sendWechatNotice(notices);
		}else{
			newNoticeService.updateShenpi(id, zhuangtai);
		}
		return "success";
	}

	/**
	 * 修改通知
	 *
	 *
	 *
	 */
	@RequestMapping(value = "/updateNotice")
	@ResponseBody
	public String  updateNotice(Notices oaNotice) {
			Integer num=newNoticeService.updateNotice(oaNotice);
			if(num>0){
				return "success";
			}else{
				return "error";
			}
	}

	/**
	 * 去往发通知页面
	 *
	 *
	 *
	 */
	@RequestMapping(value = "/updateNoticesView")
	public ModelAndView updateNoticesView(@RequestParam Integer id,Model model) {
		Notices notices=newNoticeService.findById(id);
		model.addAttribute("notices", notices);
		return new ModelAndView("oa/notice/shenpi/input", model.asMap());
	}

/*	private void sendWechatNotice(Integer userId){
		//用户openId以及家长openId
				//此处要区分学生和老师，老师直接发送给本人，学生发送给家长
				String openId=(String) map.get("open_id");
				String parentOpenId=(String) map.get("parent_open_id");
				if(StringUtils.isNotEmpty(parentOpenId)){
					openId=parentOpenId;
				}
				if(StringUtils.isEmpty(openId)){
					continue;
				}
				notifyService.sendWechatNotice(messageTemplate,openId,null);
			}
		}
	}*/



}
