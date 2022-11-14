package platform.szxyzxx.web.gc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.generalTeachingAffair.model.StudentEmployment;
import platform.education.generalcode.model.Framework;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.model.Table;
import platform.education.generalcode.service.FrameworkService;
import platform.education.generalcode.service.ItemService;
import platform.education.generalcode.service.TableService;
import platform.education.generalcode.vo.FrameworkCondition;
import platform.education.generalcode.vo.ItemCondition;
import platform.education.generalcode.vo.TableCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.gc.vo.CodeBaseList;
import platform.szxyzxx.web.teach.controller.StudentCheckAttendanceController;

@Controller
@RequestMapping("/gc/codebase")
public class CodeBaseController extends BaseController{
	private static final Logger log = LoggerFactory
			.getLogger(StudentCheckAttendanceController.class);
	private final static String viewBasePath = "/generalcode/codebase";
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	@Autowired
	@Qualifier("jcFrameworkService")
	private FrameworkService frameworkService;
	
	@Autowired
	@Qualifier("jcTableService")
	private TableService tableService;
	
	@Autowired
	@Qualifier("jcItemService")
	private ItemService itemService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user, @RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("frameworkCondition") FrameworkCondition frameworkCondition,
			@ModelAttribute("tableCondition") TableCondition tableCondition,
			@ModelAttribute("itemCondition") ItemCondition itemCondition, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<Framework> frameworkList = this.frameworkService.findFrameworkByCondition(frameworkCondition, null, null);
		List<Table> tableList = this.tableService.findTableByCondition(tableCondition, null, null);
		// List<Item> itemList =
		// this.itemService.findItemByCondition(itemCondition, null, null);

		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("frameworkList", frameworkList);
		model.addAttribute("tableList", tableList);
		// model.addAttribute("itemList", itemList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public CodeBaseList jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("frameworkCondition") FrameworkCondition frameworkCondition,
			@ModelAttribute("tableCondition") TableCondition tableCondition,
			@ModelAttribute("itemCondition") ItemCondition itemCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		page = usePage ? page : null;
		List<Framework> frameworkList = this.frameworkService.findFrameworkByCondition(frameworkCondition, page, order);
		List<Table> tableList = this.tableService.findTableByCondition(tableCondition, page, order);
		List<Item> itemList = this.itemService.findItemByCondition(itemCondition, page, order);
		CodeBaseList code = new CodeBaseList(frameworkList,tableList,itemList);
		return code;
	}
	
	@RequestMapping(value = "/itemList", method = RequestMethod.POST)
	@ResponseBody
	public CodeBaseList itemList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("itemCondition") ItemCondition itemCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "tableId", required = false) String tableId,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		page = usePage ? page : null;
		itemCondition.setTableId(Integer.parseInt(tableId));
//		itemCondition.setDisable(0);
		List<Item> itemList = this.itemService.findItemByCondition(itemCondition, page, order.asc("sort_order"));
		CodeBaseList code = new CodeBaseList(null,null,itemList);
		return code;
	}
	
	/**   ------------------------framework-----------------------------   */
	@RequestMapping(value = "/createFrameWork", method = RequestMethod.GET)
	public ModelAndView createFrameWork() {
		return new ModelAndView(structurePath("/frameAdd"));
	}
	
	@RequestMapping(value = "/createFrameWork", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation createFrameWork(@CurrentUser UserInfo user,
			Framework framework) {
		
		framework = this.frameworkService.add(framework);
		return framework != null ? new ResponseInfomation(framework.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editFrameWork/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation editFrameWork(@PathVariable(value = "id") Integer id, Framework framework) {
		framework.setId(id);
		framework = this.frameworkService.modify(framework);
		return framework != null ? new ResponseInfomation(framework.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editFramework", method = RequestMethod.GET)
	public ModelAndView editFramework(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Framework framework = this.frameworkService.findFrameworkById(id);
		model.addAttribute("framework", framework);
		return new ModelAndView(structurePath("/frameAdd"), model.asMap());
	}
	
	@RequestMapping(value = "/editFrameWork/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteFrameWork(@PathVariable(value = "id") Integer id, Framework framework) {
		if (framework != null) {
			framework.setId(id);
		}
		try {
			this.frameworkService.remove(framework);
			//物理删table
			TableCondition tableCondition = new TableCondition();
			tableCondition.setFrameworkId(id);
			List<Table> tableList = this.tableService.findTableByCondition(tableCondition, null, null);
			for (Table table : tableList) {
				Integer tableId = table.getId();
				this.tableService.remove(table);
				//物理删item
				ItemCondition itemCondition = new ItemCondition();
				itemCondition.setTableId(tableId);
				List<Item> itemList = this.itemService.findItemByCondition(itemCondition, null, null);
				for (Item item : itemList) {
					this.itemService.remove(item);
				}
			}
			
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**   ------------------------table-----------------------------   */
	@RequestMapping(value = "/createTable", method = RequestMethod.GET)
	public ModelAndView createTable(@RequestParam(value = "frameworkId", required = true) Integer frameworkId, Model model) {
		model.addAttribute("frameworkId", frameworkId);
		return new ModelAndView(structurePath("/tableAdd"),model.asMap());
	}
	
	@RequestMapping(value = "/createTable", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation createTable(@CurrentUser UserInfo user,
			Table table) {
		
		table = this.tableService.add(table);
		return table != null ? new ResponseInfomation(table.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editTable/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation editTable(@PathVariable(value = "id") Integer id, Table table) {
		table.setId(id);
		table = this.tableService.modify(table);
		return table != null ? new ResponseInfomation(table.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editTable", method = RequestMethod.GET)
	public ModelAndView editTable(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Table table = this.tableService.findTableById(id);
		model.addAttribute("table", table);
		return new ModelAndView(structurePath("/tableAdd"), model.asMap());
	}
	
	@RequestMapping(value = "/showTable", method = RequestMethod.GET)
	public ModelAndView showTable(
			@RequestParam(value = "frameworkId", required = true) Integer frameworkId, Model model) {
//		Table table = this.tableService.findTableById(frameworkId);
		TableCondition tableCondition = new TableCondition();
		tableCondition.setFrameworkId(frameworkId);
		List<Table> tableList = this.tableService.findTableByCondition(tableCondition, null, null);
		model.addAttribute("tableList", tableList);
		return new ModelAndView(structurePath("/tableEdit"), model.asMap());
	}
	
	@RequestMapping(value = "/editTable/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteTable(@PathVariable(value = "id") Integer id, Table table) {
		if (table != null) {
			table.setId(id);
		}
		try {
			this.tableService.remove(table);
			ItemCondition itemCondition = new ItemCondition();
			itemCondition.setTableId(id);
			List<Item> itemList = this.itemService.findItemByCondition(itemCondition, null, null);
			for (Item item : itemList) {
				this.itemService.remove(item);
			}
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**   ------------------------item-----------------------------   */
	@RequestMapping(value = "/createItem", method = RequestMethod.GET)
	public ModelAndView createItem(@RequestParam(value = "tableId", required = true) Integer tableId, Model model) {
		model.addAttribute("tableId", tableId);
		return new ModelAndView(structurePath("/itemAdd"),model.asMap());
	}
	
	@RequestMapping(value = "/createItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation createItem(@CurrentUser UserInfo user,
			Item item) {
		Table table = this.tableService.findTableById(item.getTableId());
		item.setTableCode(table.getCode());
		
		ItemCondition itemCondition = new ItemCondition();
		itemCondition.setTableId(item.getTableId());
		List<Item> itemList = itemService.findItemByCondition(itemCondition, null, null);
		
		List<Integer> numList = new ArrayList<>();
		for (Item it : itemList) {
			numList.add(it.getSortOrder());
		}
		
		for (int i = 1; i <= numList.size() + 2; i++) {
			if(!numList.contains(i)){
				item.setSortOrder(i);
				break;
			}
		}
		
		if(item.getLevel() == null || "".equals(item.getLevel())){
			item.setLevel(0);
		}
		if(item.getDisable() == null || "".equals(item.getDisable())){
			item.setDisable(0);
		}
		
		item = this.itemService.add(item);
		return item != null ? new ResponseInfomation(item.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editItem/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation editItem(@PathVariable(value = "id") Integer id, Item item) {
		item.setId(id);
		if(item.getDisable() == null){
			item.setDisable(0);
		}
		item = this.itemService.modify(item);
		return item != null ? new ResponseInfomation(item.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editItem", method = RequestMethod.GET)
	public ModelAndView editItem(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		Item item = this.itemService.findItemById(id);
		model.addAttribute("item", item);
		return new ModelAndView(structurePath("/itemAdd"), model.asMap());
	}
	
	/**   ------------------------itemSort-----------------------------   */
	@RequestMapping(value = "/itemSort", method = RequestMethod.POST)
	@ResponseBody
	public Object itemSort(
			@CurrentUser UserInfo user, 
			@ModelAttribute("itemCondition") ItemCondition itemCondition,
			HttpSession session,HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "itemList", required = true) Object itemList,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		page = usePage ? page : null;
		
		JSONArray jsonArray = JSONArray.fromObject(itemList);
		String id1 = jsonArray.getJSONObject(0).getString("id");
		String sortOrder1 = jsonArray.getJSONObject(0).getString("sortOrder");
		Item item1 = new Item();
		item1.setId(Integer.parseInt(id1));
		item1.setSortOrder(Integer.parseInt(sortOrder1));
		item1 = this.itemService.modify(item1);
		
		String id2 = jsonArray.getJSONObject(1).getString("id");
		String sortOrder2 = jsonArray.getJSONObject(1).getString("sortOrder");
		Item item2 = new Item();
		item2.setId(Integer.parseInt(id2));
		item2.setSortOrder(Integer.parseInt(sortOrder2));
		item2 = this.itemService.modify(item2);
		
		itemCondition.setTableId(item1.getTableId());
//		itemCondition.setDisable(0);
		List<Item> iemList = this.itemService.findItemByCondition(itemCondition, page, order.asc("sort_order"));
		CodeBaseList code = new CodeBaseList(null,null,iemList);
		return code;
	}
	
}
