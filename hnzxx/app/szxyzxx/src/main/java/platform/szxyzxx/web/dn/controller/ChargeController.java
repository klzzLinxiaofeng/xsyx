package platform.szxyzxx.web.dn.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.ChargeItemService;
import platform.education.generalTeachingAffair.service.ChargeService;
import platform.education.generalTeachingAffair.vo.ChargeVo;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;


@Controller
@RequestMapping("/dn/charge")
public class ChargeController extends BaseController {

	private final static String viewBasePath = "/dn/charge";

	@Autowired
	@Qualifier("chargeService")
	private ChargeService chargeService;

	@Autowired
    @Qualifier("chargeItemService")
	private ChargeItemService chargeItemService;

    /**
     *  收费项目主页/列表
     */
	@RequestMapping(value = "/item/index")
    public ModelAndView itemIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "name", required = false) String name,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model){
	    String viewPath = null;
        if (name != null && !"".equals(name)) {
            name = name.trim();
        }
	    List<ChargeItem> itemList = this.chargeItemService.findWithName(user.getSchoolId(), name, page, null);
        if ("list".equals(sub)) {
            viewPath = structurePath("/item_list");
        } else {
            viewPath = structurePath("/item_index");
        }
        model.addAttribute("itemList", itemList);
	    return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/item/creator", method = RequestMethod.GET)
    public ModelAndView itemCreator() {
        return new ModelAndView(structurePath("/item_input"));
    }

    /**
     * 新建项目
     */
    @RequestMapping(value = "/item/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation itemCreator(
            @CurrentUser UserInfo user,
            @RequestParam(value = "name", required = false) String name) {
        ChargeItem chargeItem = this.chargeItemService.findUniqueByName(user.getSchoolId(), name, false);
        if (chargeItem != null) {
            return new ResponseInfomation(ResponseInfomation.DATA_REPEAT);
        } else {
            chargeItem = new ChargeItem();
            chargeItem.setSchoolId(user.getSchoolId());
            chargeItem.setName(name);
            chargeItem.setObjectType("school");
            chargeItem.setObjectIds(String.valueOf(user.getSchoolId()));
            chargeItem.setIsDeleted(false);
            chargeItem = this.chargeItemService.add(chargeItem);
            //创建收费记录
            chargeService.addAllRecord(user.getSchoolId(), user.getSchoolYear(), user.getSchoolTermCode(),
                    chargeItem.getId(), chargeItem.getObjectType(), chargeItem.getObjectIds());
            return new ResponseInfomation(chargeItem.getId(), ResponseInfomation.OPERATION_SUC);
        }
    }

    @RequestMapping(value = "/item/editor", method = RequestMethod.GET)
    public ModelAndView itemEditor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        ChargeItem chargeItem = this.chargeItemService.findChargeItemById(id);
        model.addAttribute("chargeItem", chargeItem);
        return new ModelAndView(structurePath("/item_input"), model.asMap());
    }

    /**
     *  编辑项目
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation itemEdit(@PathVariable(value = "id") Integer id, ChargeItem chargeItem) {
        ChargeItem item = this.chargeItemService.findUniqueByName(chargeItem.getSchoolId(), chargeItem.getName(), false);
        if (item != null && !item.getId().equals(id)) {
            return new ResponseInfomation(ResponseInfomation.DATA_REPEAT);
        } else {
            chargeItem.setId(id);
            chargeItem = chargeItemService.modify(chargeItem);
            return new ResponseInfomation(chargeItem.getId(), ResponseInfomation.OPERATION_SUC);
        }
    }

    /**
     * 删除项目
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String itemDelete(@PathVariable(value = "id") Integer id, ChargeItem chargeItem) {
        if (chargeItem != null) {
            chargeItem.setId(id);
        }
        try {
            this.chargeItemService.removeItem(chargeItem);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @RequestMapping(value = "/item/getAll", method = RequestMethod.POST)
    @ResponseBody
    public List<ChargeItem> getAllItem(@CurrentUser UserInfo user){
        List<ChargeItem> itemList = chargeItemService.findBySchoolId(user.getSchoolId());
        return itemList;
    }





    /**
     *  收费处理、记录查询主页/列表
     */
    @RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute("charge") ChargeVo chargeVo,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
            Model model) {
		String viewPath = null;

        if ("list".equals(sub)) {
            //type: payment=收费处理, inquire=记录查询
            //收费处理：学年、学期需赋值为当前学期；记录查询：学年、学期由页面传值
            if ("payment".equals(type)) {
                chargeVo.setSchoolYear(user.getSchoolYear());
                chargeVo.setTermCode(user.getSchoolTermCode());
                viewPath = structurePath("/list");
            } else {
                chargeVo.setIsPay(true);
                viewPath = structurePath("/list1");
            }
            chargeVo.setSchoolId(user.getSchoolId());
            List<ChargeVo> chargeVoList = this.chargeService.findChargeList(chargeVo, page);
            model.addAttribute("chargeVoList", chargeVoList);
        } else {
            if ("payment".equals(type)) {
                viewPath = structurePath("/index");
            } else{
                viewPath = structurePath("/index1");
            }
        }
		return new ModelAndView(viewPath, model.asMap());
	}

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        ChargeVo chargeVo = this.chargeService.findChargeVoById(id);
        model.addAttribute("chargeVo", chargeVo);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    /**
     * 缴费/编辑
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Charge charge) {
        charge.setId(id);
        charge = this.chargeService.modifyPayment(charge);
        return charge != null ? new ResponseInfomation(charge.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, Charge charge) {
        if (charge != null) {
            charge.setId(id);
            charge.setIsPay(false);
            charge.setAmount("");
        }
        try {
            this.chargeService.modifyPayment(charge);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * 批量缴费页面
     */
    @RequestMapping(value = "/payment/index")
    public ModelAndView paymentIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            Model model) {
        String viewPath = null;
        if ("list".equals(sub)) {
            viewPath = structurePath("/payment_list");
            List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
            List<ChargeItem> itemList = chargeItemService.findBySchoolId(user.getSchoolId());
            float[][] amounts = this.chargeService.findBatchPayment(user.getSchoolId(), user.getSchoolTermCode(), teamId);
            model.addAttribute("teamStudentList", teamStudentList);
            model.addAttribute("itemList", itemList);
            model.addAttribute("amounts", amounts);
        } else {
            viewPath = structurePath("/payment_index");
        }
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * 保存批量缴费
     */
    @RequestMapping(value = "/payment/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation batchSave(
			@CurrentUser UserInfo user,
            @RequestParam(value = "amounts", required = false) String amounts,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId ) {
        List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
        List<ChargeItem> itemList = chargeItemService.findBySchoolId(user.getSchoolId());
        String[][] amountArr = getAmounts(amounts, teamStudentList.size(), itemList.size());
        try {
            chargeService.batchSetPayment(user.getSchoolId(), user.getSchoolTermCode(), gradeId, teamId,amountArr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

    //将json数组转为二维数组
    private String[][] getAmounts(String amounts, Integer studentNum, Integer itemNum){
		String[][] list = new String[studentNum][itemNum];
		JSONArray jsonArray = JSONArray.fromObject(amounts);
		for (Integer i = 0; i < studentNum; i++) {
			JSONArray array = JSONArray.fromObject(jsonArray.get(i));
			for (Integer j = 0; j < itemNum; j++) {
				String amount = "";
				if (array.get(j) != null && !"".equals(array.get(j)) ) {
					amount = array.getString(j);
				}
				list[i][j] = amount;
			}
		}
		return list;
    }

    /**
     * 统计报表
     */
    @RequestMapping(value = "/stat/index")
    public ModelAndView statIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("charge") ChargeVo chargeVo,
            Model model) {
        String viewPath = null;
        if ("list".equals(sub)) {
            viewPath = structurePath("/stat_list");

            List<ChargeVo> list = this.chargeService.findStatDate(user.getSchoolId(), chargeVo.getSchoolYear(),
                    chargeVo.getTermCode(), chargeVo.getGradeId(), chargeVo.getTeamId(), chargeVo.getItemId());
            String title = getTitle(user.getSchoolId(), chargeVo);
            model.addAttribute("list", list);
            model.addAttribute("title", title);
        } else {
            viewPath = structurePath("/stat_index");
        }
        return new ModelAndView(viewPath, model.asMap());
    }

    //统计报表标题
    private String getTitle(Integer schoolId, ChargeVo chargeVo){
        String title = "";
        if (chargeVo.getSchoolYear() != null && !"".equals(chargeVo.getSchoolYear())) {
            SchoolYear schoolYear = this.schoolYearService.findByYearAndSchoolId(schoolId, chargeVo.getSchoolYear());
            if (schoolYear != null) {
                title += schoolYear.getName();
            }
        }
        if (chargeVo.getTermCode() != null && !"".equals(chargeVo.getTermCode())) {
            SchoolTermCondition condition = new SchoolTermCondition();
            condition.setSchoolId(schoolId);
            condition.setCode(chargeVo.getTermCode());
            List<SchoolTerm> schoolTermList = this.schoolTermService.findSchoolTermByCondition(condition, null, null);
            if (schoolTermList != null && schoolTermList.size() > 0) {
                title += schoolTermList.get(0).getName();
            }
        }
        if (chargeVo.getTeamId() != null) {
            Team team = teamService.findTeamById(chargeVo.getTeamId());
            if (team != null) {
                title += team.getName() + " ";
            }
        }
        if (chargeVo.getItemId() != null) {
            ChargeItem item = chargeItemService.findChargeItemById(chargeVo.getItemId());
            if (item != null) {
                title +=  item.getName();
            }
        } else {
            title += "项目";
        }
        title += "统计报表";
        return title;
    }


	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}


}
