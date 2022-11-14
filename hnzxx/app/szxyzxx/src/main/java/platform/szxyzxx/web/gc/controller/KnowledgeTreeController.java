package platform.szxyzxx.web.gc.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.exam.model.Exam;
import platform.education.exam.service.ExamService;
import platform.education.generalcode.model.KnowledgeEdge;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.model.KnowledgeTree;
import platform.education.generalcode.service.KnowledgeEdgeService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.service.KnowledgeTreeService;
import platform.education.generalcode.vo.KnowledgeNodeCondition;
import platform.education.generalcode.vo.KnowledgeTreeCondition;
import platform.education.generalcode.vo.KnowledgeTreeMessVo;
import platform.education.homework.model.Homework;
import platform.education.homework.service.HomeworkService;
import platform.education.learningDesign.model.LearningDesign;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.service.LearningDesignService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.material.model.Material;
import platform.education.material.service.MaterialService;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.service.MicroLessonService;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.ResKnowledgeResource;
import platform.education.resource.model.Resource;
import platform.education.resource.service.ResKnowledgeResourceService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.ResKnowledgeResourceCondition;
import platform.education.resource.vo.ResKnowledgeResourceVo;
import platform.education.teachingPlan.model.TeachingPlan;
import platform.education.teachingPlan.service.TeachingPlanService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.gc.vo.ResourceMessageVo;
import platform.szxyzxx.web.sys.vo.TreeVo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/generalcode/knowledgetree")
public class KnowledgeTreeController { 
	
	private final static String viewBasePath = "/generalcode/knowledgetree";
	
	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;

	@Autowired
	@Qualifier("knowledgeTreeService")
	private KnowledgeTreeService knowledgeTreeService;

	@Autowired
	@Qualifier("knowledgeEdgeService")
	private KnowledgeEdgeService knowledgeEdgeService;

	@Autowired
	@Qualifier("resKnowledgeResourceService")
	private ResKnowledgeResourceService resKnowledgeResourceService;

	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;

	@Autowired
	@Qualifier("examService")
	private ExamService examService;

	@Autowired
	@Qualifier("homeworkService")
	private HomeworkService homeworkService;

	@Autowired
	@Qualifier("teachingPlanService")
	private TeachingPlanService teachingPlanService;

	@Autowired
	@Qualifier("microLessonService")
	private MicroLessonService microLessonService;

	@Autowired
	@Qualifier("learningDesignService")
	private LearningDesignService learningDesignService;

	@Autowired
	@Qualifier("materialService")
	private MaterialService materialService;

	@Autowired
	@Qualifier("learningPlanService")
	private LearningPlanService learningPlanService;


	/**
	 * @function 知识点列表管理
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param request
	 * @param order
     * @param model
     * @return
     */
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") KnowledgeTreeCondition condition,
			@ModelAttribute("page") Page page,HttpServletRequest request,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;

		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<KnowledgeTree> items = this.knowledgeTreeService.findKnowledgeTreeByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * @function 知识点数据列表
	 * @param user
	 * @param condition
	 * @param page
	 * @param order
	 * @param usePage
     * @return
     */
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeTree> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") KnowledgeTreeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.knowledgeTreeService.findKnowledgeTreeByCondition(condition, page, order);
	}

	/**
	 * @function 知识点列表创建页面
	 * @return
     */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * @function 知识点创建保存
	 * @param knowledgeTree
	 * @param user
     * @return
     */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			KnowledgeTree knowledgeTree,
			@CurrentUser UserInfo user) {
		KnowledgeTreeCondition knowledgeTreeCondition = new KnowledgeTreeCondition();
		knowledgeTreeCondition.setStageCode(knowledgeTree.getStageCode());
		knowledgeTreeCondition.setSubjectCode(knowledgeTree.getSubjectCode());
		knowledgeTreeCondition.setIsDeleted(false);
		List<KnowledgeTree> list= knowledgeTreeService.findKnowledgeTreeByCondition(knowledgeTreeCondition);
		if(list != null && list.size() > 0){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		knowledgeTree = this.knowledgeTreeService.add(knowledgeTree);
		return knowledgeTree != null ? new ResponseInfomation(knowledgeTree.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
	}

	/**
	 * @function 知识点编辑页面
	 * @param id
	 * @param model
     * @return
     */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		KnowledgeTree knowledgeTree = this.knowledgeTreeService.findKnowledgeTreeById(id);
		model.addAttribute("knowledgeTree", knowledgeTree);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * @function 知识点查看页面
	 * @param id
	 * @param model
     * @return
     */
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		KnowledgeTree knowledgeTree = this.knowledgeTreeService.findKnowledgeTreeById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("knowledgeTree", knowledgeTree);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * @function 知识点列表删除
	 * @param id
	 * @param knowledgeTree
     * @return
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, KnowledgeTree knowledgeTree) {
		if (knowledgeTree != null) {
			knowledgeTree.setId(id);
		}
		try {
			this.knowledgeTreeService.remove(knowledgeTree);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * @function 知识点编辑
	 * @param id
	 * @param knowledgeTree
     * @return
     */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			@PathVariable(value = "id") Integer id,
			KnowledgeTree knowledgeTree) {
		KnowledgeTree kt = knowledgeTreeService.findKnowledgeTreeById(id);
		if(knowledgeTree != null){
			KnowledgeTreeCondition knowledgeTreeCondition = new KnowledgeTreeCondition();
			knowledgeTreeCondition.setStageCode(knowledgeTree.getStageCode());
			knowledgeTreeCondition.setSubjectCode(knowledgeTree.getSubjectCode());
			List<KnowledgeTree> list= knowledgeTreeService.findKnowledgeTreeByCondition(knowledgeTreeCondition);
			if(list != null && list.size() > 0){
				if(list.size() == 1 && list.get(0).getId().equals(id)){

				}else{
					return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
				}
			}
		}
		knowledgeTree.setId(id);
		knowledgeTree = this.knowledgeTreeService.modify(knowledgeTree);
		return knowledgeTree != null ? new ResponseInfomation(knowledgeTree.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * @function 去到树节点创建及页面
	 * @param treeId
	 * @param model
     * @return
     */
	@RequestMapping(value = "/toTreeViewsPage")
	public ModelAndView toTreeViewsPage(
			@RequestParam(value = "treeId") Integer treeId,
			Model model) {
		model.addAttribute("treeId",treeId);
		List<KnowledgeNode> knowledgeNodes = knowledgeNodeService.findKnowledgeNodeByTreeId(treeId,null,null);
		if(knowledgeNodes != null && knowledgeNodes.size() > 0){
			model.addAttribute("isCanAdd",false);
		}else{
			model.addAttribute("isCanAdd",true);
		}
		return new ModelAndView(structurePath("/tree_index"),model.asMap());
	}

	/**
	 * @function 检查是否已经存在同科目、同学段、同名称的知识点
	 * 			 如果已经存在。则不需要创建，返回true
	 * 			 如果不存在，则可以创建，返回false
	 * @param treeId
	 * @param stageCode
	 * @param subjectCode
     * @return
     */
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkName(
			@RequestParam(value = "treeId") Integer treeId,
			@RequestParam(value = "stageCode") String stageCode,
			@RequestParam(value = "subjectCode") String subjectCode
			) {
			if (stageCode == null || "".equals(stageCode)){
				return true;
			}
			if (subjectCode == null || "".equals(stageCode)){
				return true;
			}

			//根据条件查询是否存在对应的记录
			KnowledgeTreeCondition knowledgeTreeCondition = new KnowledgeTreeCondition();
			knowledgeTreeCondition.setDisable(false);
			knowledgeTreeCondition.setStageCode(stageCode);
			knowledgeTreeCondition.setSubjectCode(subjectCode);
			List<KnowledgeTree> knowledgeTreeList = knowledgeTreeService.findKnowledgeTreeByCondition(knowledgeTreeCondition);

			if (knowledgeTreeList == null){
				return false;
			}
			if (treeId != null && knowledgeTreeList != null && knowledgeTreeList.size() == 1 && knowledgeTreeList.get(0).getId()+"" == treeId+""){
				return false;
			}
		return true;
	}

	/**
	 * @function 知识列表数据
	 * @param condition
	 * @return
     */
	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(@ModelAttribute("condition") KnowledgeNodeCondition condition) {
		if(condition != null && condition.getParentId() == null){
			condition.setParentId(0);
		}
		condition.setIsDeleted(false);
		List<KnowledgeNode> list = knowledgeNodeService.findKnowledgeNodeByCondition(condition,null,Order.asc("list_order"));
		return knowledgeNodeToTreeVo(list);
	}

	/**
	 * @function 树节点的创建页面
	 * @param parentId
	 * @param treeId
	 * @param level
	 * @param model
     * @return
     */
	@RequestMapping(value = "/tree/create")
	public ModelAndView createTreeNodePage(
			@RequestParam(value = "parentId")Integer parentId,
			@RequestParam(value = "treeId")Integer treeId,
			@RequestParam(value = "level")Integer level,
			Model model) {
		KnowledgeTree knowledgeTree = knowledgeTreeService.findKnowledgeTreeById(treeId);
		KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(parentId);
		model.addAttribute("parentId",parentId);
		model.addAttribute("level",level);
		model.addAttribute("knowledgeTree",knowledgeTree);
		model.addAttribute("knowledgeNodeParent",knowledgeNode);
		return new ModelAndView(structurePath("/tree_input"),model.asMap());
	}

	/**
	 * @function 树节点的创建
	 * @param knowledgeNode
	 * @return
     */
	@RequestMapping(value = "/treeNode/creator",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation createTreeNode(KnowledgeNode knowledgeNode) {
		if (knowledgeNode != null){
			knowledgeNodeService.add(knowledgeNode);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * @function 根据条件获取数据
	 * @param user
	 * @param condition
	 * @param page
	 * @param order
	 * @param usePage
     * @return
     */
	@RequestMapping(value = "/treeList/json", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeNode> jsonTreeList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") KnowledgeNodeCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.knowledgeNodeService.findKnowledgeNodeByCondition(condition, page, order);
	}

	/**
	 * @function 树节点的修改页面
	 * @param id
	 * @param model
     * @return
     */
	@RequestMapping(value = "/treeNode/update")
	public ModelAndView updateTreeNodePage(@RequestParam(value="id") Integer id,Model model) {
		KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(id);
		KnowledgeNode knowledgeNodeParent = knowledgeNodeService.findKnowledgeNodeById(knowledgeNode.getParentId());
		model.addAttribute("knowledgeNode",knowledgeNode);
		model.addAttribute("knowledgeNodeParent",knowledgeNodeParent);
		return new ModelAndView(structurePath("/tree_input"),model.asMap());
	}

	/**
	 * @function 树节点的修改，code值也会一起更新
	 * @param knowledgeNode
	 * @return
     */
	@RequestMapping(value = "/treeNode/update/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateTreeNode(KnowledgeNode knowledgeNode) {
		if (knowledgeNode != null && knowledgeNode.getId() != null){
			if("".equals(knowledgeNode.getSubjectCode())){
				knowledgeNode.setSubjectCode(null);
			}
			knowledgeNodeService.modify(knowledgeNode);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * @function 树节点的删除
	 * @param id
	 * @return
     */
	@RequestMapping(value = "/treeNode/deleted",method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseInfomation deletedTreeNode(@RequestParam(value = "id")Integer id) {
		if(id != null){
			batchDeltedTreeNode(id);
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * @function 递归 批量删除，根据父节点删除所有自节点
	 * @param treeNodeId
	 * @return
     */
	public String batchDeltedTreeNode(Integer treeNodeId){
		if(treeNodeId == null){
			return null;
		}
		KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(treeNodeId);
		if(knowledgeNode != null){
			knowledgeNode.setIsDeleted(true);
			knowledgeNodeService.modify(knowledgeNode);
		}
		List<KnowledgeNode> knowledgeNodelist = knowledgeNodeService.findKnowledgeNodeByParentId(treeNodeId,null,null);
		if(knowledgeNodelist != null){
			for(KnowledgeNode kn : knowledgeNodelist){
				if(kn != null && kn.getId() !=null){
					batchDeltedTreeNode(kn.getId());
				}
			}
		}
		return "success";
	}

	/**
	 * @function 树拖动排序功能
	 * @param ids
	 * @return
     */
	@RequestMapping(value = "/editOrder",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation editOrder(@RequestParam(value = "ids") String ids){
		if(ids == null || "".equals(ids)){
			return null;
		}
		String[] nodeIds = ids.split(",");
		int order = 1;
		if(nodeIds != null && nodeIds.length > 0){
			for(String id : nodeIds){
				KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(Integer.valueOf(id));
				if(knowledgeNode != null){
					knowledgeNode.setModifyDate(new Date());
					knowledgeNode.setListOrder(order++);
					knowledgeNodeService.modify(knowledgeNode);
				}
			}
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * @function 知识树生成树结构
	 * @param knowledgeNodes
     * @return
     */
	private List<TreeVo> knowledgeNodeToTreeVo(List<KnowledgeNode> knowledgeNodes) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		for (KnowledgeNode knowledgeNode : knowledgeNodes) {
			TreeVo vo = new TreeVo();
			vo.setId(String.valueOf(knowledgeNode.getId()));
			vo.setpId(String.valueOf(knowledgeNode.getParentId()));
			vo.setName(knowledgeNode.getName());
			List list = knowledgeNodeService.findKnowledgeNodeByParentId(knowledgeNode.getId(),null,null);
			vo.setIsParent(list != null && list.size() > 0 ? true : false);
			treeVos.add(vo);
		}
		return treeVos;
	}

	/**
	 * @function 去到数据导向图界面
	 * @param treeId
	 * @param model
     * @return
     */
	@RequestMapping(value = "/toDatagramPage")
	public ModelAndView DatagramePage(@RequestParam(value="treeId") Integer treeId,Model model){
		if (treeId == null){
			return null;
		}
		model.addAttribute("treeId",treeId);
		return new ModelAndView(structurePath("/force-Directed_Graph"),model.asMap());
	}

	/**
	 * @function 根据树ID去获取生成导向图的JSON数据
	 * @param treeId
	 * @return
     */
	@RequestMapping(value = "/getDataOfJSON")
	@ResponseBody
	public String getData(@RequestParam(value="treeId") Integer treeId){
		HashMap<String,Object> data = new HashMap<String, Object>();
		ArrayList<Object> dataListNodes = new ArrayList<Object>();
		ArrayList<Object> dataListLinks = new ArrayList<Object>();
		if(treeId == null){
			data.put("nodes",dataListNodes);
			data.put("links",dataListLinks);
			return new JSONObject(data).toString();
		}
		List<KnowledgeNode> knowledgeNodesList = knowledgeNodeService.findKnowledgeNodeByTreeId(treeId,null,null);
		if(knowledgeNodesList != null && knowledgeNodesList.size() > 0){
			for(KnowledgeNode knowledgeNode : knowledgeNodesList){
				if(knowledgeNode != null && knowledgeNode.getId() != null){
					HashMap<String,String> nodes = new HashMap<String, String>();
					nodes.put("id",knowledgeNode.getId()+"");
					nodes.put("name",knowledgeNode.getName());
					nodes.put("describe",knowledgeNode.getDescribe());
					dataListNodes.add(nodes);
				}
			}
			data.put("nodes",dataListNodes);
		}

		List<KnowledgeEdge> knowledgeEdgesList = knowledgeEdgeService.findKnowledgeEdgeByTreeId(treeId,null,null);
		KnowledgeNode beginNode = null;
		KnowledgeNode endNode = null;
		if(knowledgeEdgesList != null && knowledgeEdgesList.size() > 0){
			for(KnowledgeEdge knowledgeEdge : knowledgeEdgesList){
				if(knowledgeEdge != null && knowledgeEdge.getId() != null){
					HashMap<String,Integer> links = new HashMap<String, Integer>();
					links.put("source",knowledgeEdge.getBeginNodeId());
					links.put("target",knowledgeEdge.getEndNodeId());
					dataListLinks.add(links);
				}
			}
			data.put("links",dataListLinks);
		}
		return new JSONObject(data).toString();
	}

	/**
	 * @function 获取每个类型的文件数量
	 * 			 2017-6-1 修改为获取总数
	 * @param nodeId
	 * @return
     */
	@RequestMapping(value="/getNodeMessage")
	@ResponseBody
	public String getNodeMessage(@RequestParam(value="nodeId") Integer nodeId){
		if(nodeId == null){
			nodeId = 0;
		}
		HashMap<String,Integer> map = new HashMap<String,Integer>();
//		Integer micro = 0;
//		Integer design = 0;
//		Integer homework = 0;
//		Integer exam = 0;
//		Integer plan = 0;
//		Integer material = 0;
//		Integer learning = 0;
//		Integer paper = 0;
//		Integer question = 0;
//		Integer other = 0;
		Integer all = 0;
		List<ResKnowledgeResource> resKnowledgeResourcesList = resKnowledgeResourceService.findResKnowledgeResourceByNodeId(nodeId,null,null);
		if(resKnowledgeResourcesList != null){
			all = resKnowledgeResourcesList.size();
//			for(ResKnowledgeResource resKnowledgeResource : resKnowledgeResourcesList){
//				if(ResourceType.MICRO == resKnowledgeResource.getOwnerType()){
//					micro ++;
//				}else if(ResourceType.LEARNING_DESIGN == resKnowledgeResource.getOwnerType()){
//					design ++;
//				}else if(ResourceType.HOMEWORK == resKnowledgeResource.getOwnerType()){
//					homework++;
//				}else if(ResourceType.EXAM == resKnowledgeResource.getOwnerType()){
//					exam++;
//				}else if(ResourceType.TEACHING_PLAN == resKnowledgeResource.getOwnerType()){
//					plan++;
//				}else if(ResourceType.MATERIAL == resKnowledgeResource.getOwnerType()){
//					material++;
//				}else if(ResourceType.LEARNING_PLAN == resKnowledgeResource.getOwnerType()){
//					learning++;
//				}else{
//					other++;
//				}
//			}
		}
//		map.put("MICRO_LESSON",micro);
//		map.put("LEARNING_DESIGN",design);
//		map.put("HOMEWORK",homework);
//		map.put("EXAM",exam);
//		map.put("TEACHING_PLAN",plan);
//		map.put("MATERIAL",material);
//		map.put("LEARING_PLAN",learning);
//		map.put("OTHER",other);
		map.put("ALL",all);

		return new JSONObject(map).toString();
	}

	/**
	 * @function 去到数据导向图界面
	 * @param treeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDatagramPage1")
	public ModelAndView DatagramePage1(@RequestParam(value="treeId") Integer treeId,Model model){
		if (treeId == null){
			return null;
		}
		model.addAttribute("treeId",treeId);
		return new ModelAndView(structurePath("/knowledgeForTree"),model.asMap());
	}

	/**
	 * @function 去到数据导向图界面
	 * @param treeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDatagramPage2")
	public ModelAndView DatagramePage2(@RequestParam(value="treeId") Integer treeId,Model model){
		if (treeId == null){
			return null;
		}
		model.addAttribute("treeId",treeId);
		return new ModelAndView(structurePath("/RadicalTree"),model.asMap());
	}

	/**
	 * @function 根据树节点ID获取知识点的JSON父子结构数据
	 * @param treeId
	 * @return
     */
	@RequestMapping(value="/getDataOfTree")
	@ResponseBody
	public String getDataOfTree(@RequestParam(value="treeId") Integer treeId){
		if(treeId == null){
			return null;
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		List<KnowledgeTreeMessVo> knowledgeTreeMessVoList = new ArrayList<>();
		KnowledgeTreeMessVo knowledgeTreeMessVo = new KnowledgeTreeMessVo();
		KnowledgeNodeCondition knowledgeNodeCondition = new KnowledgeNodeCondition();
		knowledgeNodeCondition.setParentId(0);
		knowledgeNodeCondition.setTreeId(treeId);
		knowledgeNodeCondition.setIsDeleted(false);
		List<KnowledgeNode> knowledgeNodesList = knowledgeNodeService.findKnowledgeNodeByCondition(knowledgeNodeCondition);
		if(knowledgeNodesList != null && knowledgeNodesList.size() > 0){
			knowledgeTreeMessVoList = knowledgeNodeService.getKnowledgeNodeTreeByNodeId(knowledgeNodesList.get(0).getId());
			knowledgeTreeMessVo.setId(knowledgeNodesList.get(0).getId());
			knowledgeTreeMessVo.setName(knowledgeNodesList.get(0).getName());
			knowledgeTreeMessVo.setDescribe(knowledgeNodesList.get(0).getDescribe());
			knowledgeTreeMessVo.setChildrens(knowledgeTreeMessVoList);
		}
		map.put("data",knowledgeTreeMessVo);
		return knowledgeTreeMessVo.toString();
	}

	/**
	 * @function 去到获取具体节点资源信息的页面
	 * @param nodeId
	 * @param model
     * @return
     */
	@RequestMapping(value = "/toDetailedPage",method = RequestMethod.GET)
	public ModelAndView toDetailedPage(@RequestParam(value="nodeId") Integer nodeId,Model model){
		model.addAttribute("nodeId",nodeId);
		return new ModelAndView(structurePath("/detailedIndex"),model.asMap());
	}

	/**
	 * @function 去到获取具体节点资源信息的页面
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getKnowledgeDetailed")
	public ModelAndView getKnowledgeDetailed(@ModelAttribute("condition") ResKnowledgeResourceCondition condition,
											 @ModelAttribute("page") Page page,
											 @ModelAttribute("order") Order order,
											 Model model)
	{
		List<ResKnowledgeResourceVo> resKnowledgeResourcesList = resKnowledgeResourceService.findResKnowledgeResourceMoreByNodeId(condition.getKnowledgeNodeId(),condition.getOwnerType(),order,page);
		model.addAttribute("items",resKnowledgeResourcesList);
		return new ModelAndView(structurePath("/detailedList"),model.asMap());
	}

	/**
	 * @function 根据知识点获取相关资源，可以在页面指定获取的数量，如果资源存在有这个数量
	 * @param nodeId
	 * @param pageSize
     * @return
     */
	@RequestMapping(value = "getResourceByKnoledge",method = RequestMethod.POST)
	public ModelAndView getResourceByKnoledge(
			@RequestParam(value="nodeId") Integer nodeId,
			@RequestParam(value="resourceNumber") Integer pageSize,
			Model model
	){
		if(pageSize == null){
			pageSize = 3;
		}
		Page page = new Page();
		page.setPageSize(pageSize);

		List<ResourceMessageVo> resourceMessageVoList = new ArrayList<ResourceMessageVo>();
		Resource resource = null;
		List<ResKnowledgeResource> resKnowledgeResourceList = resKnowledgeResourceService.findResKnowledgeResourceByNodeId(nodeId,null,page);
		if(resKnowledgeResourceList != null && resKnowledgeResourceList.size() > 0){
			for(ResKnowledgeResource resKnowledgeResource : resKnowledgeResourceList){
				resource = resourceService.findResourceByObjectid(resKnowledgeResource.getOwnerUuid());
				if(resource != null){
					ResourceMessageVo messageVo = new ResourceMessageVo();
					String title = "";
					String entryId = "";
					String objectId = "";
					Integer resId = resource.getId();
					Date createDate = null;
					String uuid = resource.getObjectId();
					if(resource.getResType().equals(ResourceType.EXAM)){
						Exam exam = examService.findExamByUuid(uuid);
						if(exam != null){
							title = exam.getTitle();
							entryId = exam.getEntityId();
							objectId = exam.getUuid();
							createDate = exam.getCreateDate();
						}
						messageVo.setType(ResourceType.EXAM);
					}else if(resource.getResType().equals(ResourceType.HOMEWORK)){
						Homework homework = homeworkService.findHomeworkByUuid(uuid);
						if(homework != null){
							title = homework.getTitle();
							entryId = homework.getEntityId();
							objectId = homework.getUuid();
							createDate = homework.getCreateDate();
						}
						messageVo.setType(ResourceType.HOMEWORK);
					}else if(resource.getResType().equals(ResourceType.LEARNING_DESIGN)){
						LearningDesign learningDesign = learningDesignService.findLearningDesignByUuid(uuid);
						if(learningDesign != null){
							title = learningDesign.getTitle();
							entryId = learningDesign.getEntityId();
							objectId = learningDesign.getUuid();
							createDate = learningDesign.getCreateDate();
						}
						messageVo.setType(ResourceType.LEARNING_DESIGN);
					}else if(resource.getResType().equals(ResourceType.LEARNING_PLAN)){
						LearningPlan learningPlan = learningPlanService.findLearningPlanByUuid(uuid);
						if(learningPlan != null){
							title = learningPlan.getTitle();
							entryId = learningPlan.getEntityId();
							objectId = learningPlan.getUuid();
							createDate = learningPlan.getCreateDate();
						}
						messageVo.setType(ResourceType.LEARNING_PLAN);
					}else if(resource.getResType().equals(ResourceType.MATERIAL)){
						Material material = materialService.findMaterialByUuid(uuid);
						if(material != null){
							title = material.getTitle();
							entryId = material.getEntityId();
							objectId = material.getUuid();
							createDate = material.getCreateDate();
						}
						messageVo.setType(ResourceType.MATERIAL);
					}else if(resource.getResType().equals(ResourceType.MICRO)){
						MicroLesson microLesson = microLessonService.findMicroLessonByUuid(uuid);
						if(microLesson != null){
							title = microLesson.getTitle();
							entryId = microLesson.getEntityId();
							objectId = microLesson.getUuid();
							createDate = microLesson.getCreateDate();
						}
						messageVo.setType(ResourceType.MICRO);
					}else if(resource.getResType().equals(ResourceType.TEACHING_PLAN)){
						TeachingPlan teachingPlan = teachingPlanService.findTeachingPlanByUuid(uuid);
						if(teachingPlan != null){
							title = teachingPlan.getTitle();
							entryId = teachingPlan.getEntityId();
							objectId = teachingPlan.getUuid();
							createDate = teachingPlan.getCreateDate();
						}
						messageVo.setType(ResourceType.TEACHING_PLAN);
					}

					messageVo.setCreateDate(createDate);
					messageVo.setEntryId(entryId);
					messageVo.setTitle(title);
					messageVo.setObjectId(objectId);
					messageVo.setResId(resId);
					resourceMessageVoList.add(messageVo);
				}
			}
		}
		model.addAttribute("resourceMessageVoList",resourceMessageVoList);
		return new ModelAndView(structurePath("/resourcePage"),model.asMap());
	}

	/**
	 * @function 去到资源播放弹窗页
	 * @param resId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toShowPage")
	public ModelAndView showPage(@RequestParam(value="resId") Integer resId,
								 @RequestParam(value="type") String type,Model model){
		model.addAttribute("resId",resId);
		model.addAttribute("type",type);
		return new ModelAndView(structurePath("/checkResourcePage"),model.asMap());
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, KnowledgeTreeCondition condition) {
		condition.setIsDeleted(false);
	}
}
