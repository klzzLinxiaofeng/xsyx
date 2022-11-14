package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import platform.education.generalTeachingAffair.contants.MembershipContans;
import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.service.MembershipService;
import platform.education.generalTeachingAffair.service.SchoolMembershipService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalTeachingAffair.vo.MembershipVo;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/membership")
public class MembershipController { 
	
	private static final Logger log = LoggerFactory.getLogger(MembershipController.class);
	
	private final static String viewBasePath = "/teach/membership";
	
	@Autowired
	@Qualifier("membershipService")
	private MembershipService membershipService;
	
	@Resource
	private SchoolMembershipService schoolMembershipService;
	
	@Resource
	private SchoolService schoolService;

	
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MembershipCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");

		String viewPath = null;
		//查询总校
		List<MembershipVo> items = this.membershipService.findMembershipVoByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<School> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MembershipCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage,
			@RequestParam(value = "ownerId", required = true) String ownerId,
			@RequestParam(value = "ownerType", required = true) String  ownerType
			) {
//		conditionFilter(user, condition);
		page = usePage ? page : null;
		
		/*首先根据条件查找到对应总校id（parentId）
		 * 再根据parentId查找到该总校下的所有分校区
		 */
		condition.setOwnerId(ownerId);
		condition.setOwnerType(ownerType);
		condition.setIsDeleted(false);
		//根据条件查找到这所学校是否为某一总校下的分校
		List<Membership>mainSchool = this.membershipService.findMembershipByCondition(condition);
		
		//需要返回的学校信息
		Map<Integer,School>schoolMaps = new HashMap();
		
		if(mainSchool.size()>0){
			Integer parentId = mainSchool.get(0).getParentId();
			//查找这所学校的总校区下的所有分校
			List<Membership>branchSchool = this.membershipService.findByTermList(parentId, null, null, false);
			
			if(branchSchool.size()>0){
				
				for(Membership m : branchSchool){
					School s = this.schoolService.findSchoolById(Integer.parseInt(m.getOwnerId()));
					if(s!=null){
						schoolMaps.put(s.getId(), s);
					}
					
				}
			}
			
		}else{
			School school = this.schoolService.findSchoolById(user.getSchoolId());
			if(school!=null){
				schoolMaps.put(school.getId(), school);
			}
			
		}
		
		
		return  new ArrayList(schoolMaps.values());
		
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Membership membership, @CurrentUser UserInfo uer) {
		
		membership = this.schoolMembershipService.addGeneral(membership.getName(),SysContants.EDUCLOUD_KEY);
		
		return membership != null ? new ResponseInfomation(membership.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Membership membership = this.membershipService.findMembershipById(id);
		List<Membership>memberSchool = this.membershipService.findByTermList(id, null,  null, false);
		if(memberSchool.size()>0){
			for(Membership m :memberSchool){
				m.setName(this.schoolService.findSchoolById(Integer.parseInt(m.getOwnerId())).getName());
			}
		}
		
		
		model.addAttribute("memberSchool", memberSchool);
		model.addAttribute("membership", membership);
		return new ModelAndView(structurePath("/editor"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Membership membership = this.membershipService.findMembershipById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("membership", membership);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Membership membership) {
		if (membership != null) {
			membership.setId(id);
		}
		try {
			this.membershipService.remove(membership);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(
			@RequestParam(value = "membershipId", required = true) Integer membershipId,
			@RequestParam(value = "ownerIds", required = true) String ownerIds,
			@RequestParam(value = "name", required = true) String name
			) {
		try {
			
			Membership mship = this.membershipService.findMembershipById(membershipId);
			if(mship!=null){
				mship.setName(name);
				mship.setModifyDate(new Date());
				this.membershipService.modify(mship);
			}
			
			
			List<Membership>membership = this.membershipService.findByTermList(membershipId, null,  MembershipContans.OWNER_TYPE_BRANCHSCHOOL, false);
			if(membership.size()>0){
				for(Membership m :membership){
					this.schoolMembershipService.removeSchoolBranchFromGeneral(Integer.parseInt(m.getOwnerId()), m.getParentId());
				}
			}
			
			if(!"".equals(ownerIds)){
				String ownerId [] = ownerIds.split(",");
				Map<Integer,String>map = new HashMap();
				
				if(ownerId.length>0){
					for(int i=0;i<ownerId.length;i++){
						map.put(Integer.parseInt(ownerId[i]), ownerId[i]);
					}
				}
				
				if(map.keySet().size()>0){
					 for (Integer key : map.keySet()) {
						   this.schoolMembershipService.assignSchoolBranchToGeneral (key, membershipId,SysContants.EDUCLOUD_KEY);
						  }
				}
				
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
			
		}
		
		return  ResponseInfomation.OPERATION_SUC;
	}
	
	
	@RequestMapping(value = "/getSchoolList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>schoolLsit(
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "district", required = true) String district,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "membershipId", required = true) String membershipId
			){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		SchoolCondition schoolCondition = new SchoolCondition();
		schoolCondition.setProvince(province);
		schoolCondition.setCity(city);
		schoolCondition.setDistrict(district);
		schoolCondition.setName(name);
		schoolCondition.setDelete(false);
		//全部的学校
		List<School>schoolList = this.schoolService.findSchoolByCondition(schoolCondition, null, null);
		Map<Integer,School>allSchoolMap = new HashMap();
			if (schoolList.size() > 0) {
				for (School school : schoolList) {
					allSchoolMap.put(school.getId(), school);
				}
			
			}
		
		//全部的分校    
		List<Membership>allMembershipList = this.membershipService.findByTermList(null, null,  MembershipContans.OWNER_TYPE_BRANCHSCHOOL, false);
		
		//某个总校的分校
		List<Membership>oneMembershipList = this.membershipService.findByTermList(Integer.parseInt(membershipId), null, MembershipContans.OWNER_TYPE_BRANCHSCHOOL, false);
		
		/**
		 * 首先拿到全部的学校，然后排除全部已经做了分校的学校
		 * 再把当前总校下的分校区添加进去
		 */
		//排除全部已经做了分校的学校
		if(allMembershipList.size()>0){
			for(Membership ams :allMembershipList) {
				School s = this.schoolService.findSchoolById(Integer.parseInt(ams.getOwnerId()));
				allSchoolMap.remove(Integer.parseInt(ams.getOwnerId()));
				}
		}
		
		
		//把当前总校区下的分校区添加进去
			if(oneMembershipList.size()>0){
					   for(Membership ms :oneMembershipList) {
						   School s = this.schoolService.findSchoolById(Integer.parseInt(ms.getOwnerId()));
						   allSchoolMap.put(s.getId(), s);
						   
					   	}
					       
					  }
		
		                                
		map.put("schoolList", new ArrayList(allSchoolMap.values()));
		map.put("oneMembershipList", oneMembershipList);
		return map;
	}
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, MembershipCondition condition) {
	
		
	}
}
