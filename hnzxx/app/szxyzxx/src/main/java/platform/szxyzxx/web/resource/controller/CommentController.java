package platform.szxyzxx.web.resource.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.message.model.Comment;
import platform.education.message.service.CommentService;
import platform.education.message.vo.CommentCondition;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.service.ResourceService;
import platform.education.user.service.UserService;
import platform.szxyzxx.contants.DefaultContans;
import platform.szxyzxx.contants.ObjectTypeContans;
import platform.szxyzxx.contants.ResResourceType;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

@Controller
@RequestMapping("/message/comment")
public class CommentController { 
	
	private final static String viewBasePath = "/crresource/comment";
	
	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;
	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "resType", required = false) Integer resType,
			@ModelAttribute("condition") CommentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		order.setAscending(true);
		if(resType!=null) {
			condition.setObjectType(compareType(resType));
		}
		List<Comment> items = this.commentService.findCommentByCondition(condition, page, order);
		Long count = this.commentService.count(condition);
		model.addAttribute("items", items);
		model.addAttribute("count", count);
		return new ModelAndView(viewBasePath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Comment> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") CommentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.commentService.findCommentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Comment comment, @CurrentUser UserInfo user, @RequestParam(value = "resType", required = true) Integer resType) {
		Integer appId = comment.getAppId();
		Integer userid = user.getId();
		comment.setObjectType(compareType(resType));
		comment.setPosterId(userid);
		comment.setPostName(user.getRealName());
		comment.setAppId(appId);
		comment.setAgrees(DefaultContans.ZERO);
		comment.setDisagrees(DefaultContans.ZERO);
		if(appId == null) {
			comment.setAppId(SysContants.SYSTEM_APP_ID);
		}
		comment = this.commentService.add(comment);
		
		return comment != null ? new ResponseInfomation(comment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Comment comment = this.commentService.findCommentById(id);
		model.addAttribute("comment", comment);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Comment comment = this.commentService.findCommentById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("comment", comment);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Comment comment) {
		if (comment != null) {
			comment.setId(id);
		}
		try {
			this.commentService.remove(comment);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Comment comment) {
		comment.setId(id);
		comment = this.commentService.modify(comment);
		return comment != null ? new ResponseInfomation(comment.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, CommentCondition condition) {
		Integer appId = condition.getAppId();
		
		if(appId == null) {
			condition.setAppId(SysContants.SYSTEM_APP_ID);
		}
	}
	
	private String compareType(Integer resType){
		if(resType == ResourceType.MICRO){
			return ObjectTypeContans.MICRO_TYPE;
		}else if(resType == ResourceType.LEARNING_DESIGN){
			return ObjectTypeContans.DESINE_TYPE;
		}else if(resType == ResourceType.EXAM){
			return ObjectTypeContans.EXAM_TYPE;
		}else if(resType == ResourceType.HOMEWORK){
			return ObjectTypeContans.HOMEWORD_TYPE;
		}else if(resType == ResourceType.MATERIAL){
			return ObjectTypeContans.METERIAL_TYPE;
		}else if(resType == ResourceType.TEACHING_PLAN){
			return ObjectTypeContans.TEACHRING_PLAN_TYPE;
		}else if(resType == ResResourceType.PAID){
			return ObjectTypeContans.RES_PAID;
		}
		else 
		return ObjectTypeContans.OTHERS;
	}
}
