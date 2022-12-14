package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import platform.education.generalcode.model.ResTextbookCatalogModel;
import platform.education.generalcode.service.ResTextbookCatalogModelService;
import platform.education.generalcode.vo.ResTextbookCatalogModelCondition;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.generalcode.vo.TextbookCatalogVo;
import platform.education.generalcode.vo.ZTreeVO;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/teach/textBookMaster/catalogmodel")
public class ResTextbookCatalogModelController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/textBookMaster/catalogmodel";
	
	@Autowired
	@Qualifier("resTextbookCatalogModelService")
	private ResTextbookCatalogModelService resTextbookCatalogModelService;
	
		
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<ResTextbookCatalogModel> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") ResTextbookCatalogModelCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.resTextbookCatalogModelService.findResTextbookCatalogModelByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResTextbookCatalogModel creator(ResTextbookCatalogModel resTextbookCatalogModel, @CurrentUser UserInfo user) {
		resTextbookCatalogModel.setResLibraryId(user.getSchoolId());
		resTextbookCatalogModel = this.resTextbookCatalogModelService.add(resTextbookCatalogModel);
		return resTextbookCatalogModel;
	}

	/**
	 * ??????????????????????????????????????????????????????????????? 
	 * @param modelJson	???????????????json????????????
	 * @return ResponseInfomation
	 */
	@RequestMapping(value = "bantch/editor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation batchEditor(@RequestParam(value = "data", required = false) String data) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		try {
			this.resTextbookCatalogModelService.batchEditor(data);
		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("??????/????????????????????????",e));
			e.printStackTrace();
		}
		return responseInfomation;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ResTextbookCatalogModel resTextbookCatalogModel) {
		if (resTextbookCatalogModel != null) {
			resTextbookCatalogModel.setId(id);
		}
		try {
			this.resTextbookCatalogModelService.remove(resTextbookCatalogModel);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation modify(ResTextbookCatalogModel resTextbookCatalogModel, @CurrentUser UserInfo user) {
		resTextbookCatalogModel = this.resTextbookCatalogModelService.modify(resTextbookCatalogModel);
		return resTextbookCatalogModel != null ? new ResponseInfomation(resTextbookCatalogModel.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * ??????????????????
	 * @param user ????????????
	 * @param textBookId ??????id
	 * @param parentId ?????????id
	 * @return
	 */
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView findModelList(@CurrentUser UserInfo user,
			@RequestParam(value = "textBookId", required = false) Integer textBookId, 
			@RequestParam(value = "parentId", required = false) Integer parentId){
		ModelAndView mav = new ModelAndView();
		/**?????????????????????jsonArray*/
		List<String> treeList = this.resTextbookCatalogModelService.findModelTree(user.getSchoolId(),parentId);
		mav.setViewName(structurePath("/model_index"));
		mav.addObject("treeList", treeList);
		/**????????????*/
		mav.addObject("textBookId",textBookId);
		return mav;
	}
	
	/**
	 *?????????????????????????????????????????????
	 *@param parentId ?????????id
	 *@param nodeId ?????????????????????id
	 */
	@RequestMapping(value = "/textCatalog", method = RequestMethod.PUT)
	@ResponseBody
	public List<String> textCatalog(@CurrentUser UserInfo user, 
			@RequestParam(value = "parentId", required = false) Integer parentId, 
			@RequestParam(value = "nodeId", required = false) Integer nodeId){
		/**?????????????????????jsonArray*/
		List<String> treeList = this.resTextbookCatalogModelService.findModelTree(user.getSchoolId(),parentId);
		/**?????????????????????*/
		treeList = resTextbookCatalogService.addModel(treeList, nodeId);
		/**????????????*/
		return treeList;
	}
	
	/**
	 * ????????????
	 * @param user ????????????
	 * @param textBookId ??????id
	 * @param parentId ?????????id
	 * @return
	 */
	@RequestMapping(value = "/structrueManager", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView modelStructrue(@CurrentUser UserInfo user, 
			@RequestParam(value = "textBookId", required = false) Integer textBookId, 
			@RequestParam(value = "parentId", required = false) Integer parentId){
		ModelAndView mav = new ModelAndView();
		ResTextbookCatalogModelCondition condition = new ResTextbookCatalogModelCondition();
		condition.setResLibraryId(user.getSchoolId());
		condition.setParentId(parentId);
		/**??????parentId??????????????????*/
		List<ResTextbookCatalogModel> motherList = this.resTextbookCatalogModelService.findResTextbookCatalogModelByCondition(condition);
		
		/**??????????????????????????????*/
		ResTextbookCatalogVo root = new ResTextbookCatalogVo();
		List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
		if(textBookId != null && textBookId >0){
			root = this.resTextbookCatalogService.findResTextbookCatalogList(textBookId);
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.resTextbookCatalogService.nodeOutListExcludeQuote(root);
		}
		List<ZTreeVO> zTreeList = resTextbookCatalogService.getTree(resultList);
		mav.addObject("zTreeList", JSONArray.fromObject(zTreeList));
		
		if(resultList!=null && resultList.size()>0) {
			mav.addObject("parentCatalog", JSONObject.fromObject(resultList.get(0)));
		}
	
		mav.setViewName(structurePath("/structure_index"));
		mav.addObject("motherList", JSONArray.fromObject(motherList));
		
		/**????????????*/
		mav.addObject("textBookId",textBookId);
		return mav;
	}
	
	/**
	 * ??????????????????
	 * @param parentId ?????????id
	 * @return
	 */
	@RequestMapping(value = "/model/search", method = RequestMethod.POST)
	@ResponseBody
	public List<ResTextbookCatalogModel> findModelList(ResTextbookCatalogModelCondition condition){
		List<ResTextbookCatalogModel> lists = this.resTextbookCatalogModelService.findResTextbookCatalogModelByCondition(condition);
		return lists;
	}
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/**
	 * ????????????
	 * @param e
	 * @return
	 */
	private String getMessage(String error,Exception e) {
		String message = e.getMessage().length() >=10 ?e.getMessage().substring(0, 10):e.getMessage();
		return error+":"+message;
	}
}
