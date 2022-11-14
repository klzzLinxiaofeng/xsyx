package platform.education.commonResource.web.paper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.model.PaQuestionCatalog;
import platform.education.paper.service.PaFavoritesService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaPaperTreeService;
import platform.education.paper.service.PaQuestionCatalogService;
import platform.education.paper.service.PaQuestionKnoledgeService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionCatalogCondition;
import platform.education.paper.vo.PaQuestionVo;

@Controller
@RequestMapping("/paper/")
public class PaperController {

	private final static String viewBasePath = "/paper/";

	private final static String QuestionPath = "/paper/pc/question/";
	
	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("paFavoritesService")
	private PaFavoritesService paFavoritesService;
	
	@Autowired
	@Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	
	@Autowired
	@Qualifier("paPaperTreeService")
	private PaPaperTreeService paPaperTreeService;
	
	@Autowired
	@Qualifier("paQuestionKnoledgeService")
	private PaQuestionKnoledgeService paQuestionKnoledgeService;
	
	@Autowired
	@Qualifier("paQuestionCatalogService")
	private PaQuestionCatalogService paQuestionCatalogService;
	
	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;
	
	
	@RequestMapping(value = "/index")
	public String index(@RequestParam(value = "sub", required = false) String sub,
			HttpServletRequest request,
			@RequestParam(value = "isPersonal", required = false, defaultValue = "false") Boolean isPersonal
			) {
		//是否跳转到个人库
		if(isPersonal) {
			request.setAttribute("isPersonal", true);
		}
		//试卷还是组卷主页
		return "pc".equals(sub) ? structurePath("/pc/index") : structurePath("/index");
	}
	
    @RequestMapping(value = "/list")
    public String listPaper(HttpServletRequest request, @CurrentUser UserInfo user, @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "libType", required = false) String libType,
            @RequestParam(value = "stageCode", required = false) String stageCode,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "textbookId", required = false) String textbookId) {
    	
        String viewPath = "pc".equals(sub) ? structurePath("/pc/list") : structurePath("/list");
        
        List<PaPaperVo> plist = paPaperService.findPaPaperByConditions(user.getUserId(), user.getSchooUUID(), libType,
                stageCode, subjectCode, type, code, textbookId, page, order);
      
        // 是否已收藏
        for (PaPaperVo paper : plist) {
            paper.setIsDeleted(paFavoritesService.isFav(paper.getId(), user.getUserId(), PaperContans.PAPER));
        }
        
        request.setAttribute("paperlist", plist);
        return viewPath;
    }

    @RequestMapping(value = "/question/index")
    public String questionIndex() {
        return QuestionPath+"index";
    }
    
    @RequestMapping(value = "/question/list")
    public String listQuestion(HttpServletRequest request, @CurrentUser UserInfo user,
            @ModelAttribute("page") Page page, @ModelAttribute("order") Order order,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "libType", required = false) String libType,
            @RequestParam(value = "difficulity", required = false) String difficulity,
            @RequestParam(value = "questionType", required = false) String questionType,
            @RequestParam(value = "stageCode", required = false) String stageCode,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "textbookId", required = false) String textbookId) {
    	
		List<PaQuestionVo> qlist = paQuestionService.findPaQuestionList(user.getUserId(), user.getSchooUUID(), libType,
				difficulity, questionType, stageCode, subjectCode, type, code, textbookId, page, order);
		
		request.setAttribute("questionlist", qlist);
		return QuestionPath + "list";
    }
    
    @RequestMapping(value = "/question/detail", method = RequestMethod.GET)
    public String showQuestionDetail(HttpServletRequest request, @CurrentUser UserInfo user,
    		@RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "uuid", required = true) String uuid) {
    	
    	PaQuestionVo questionVo = paQuestionService.findQuestionDetail(id);
    	
    	//学段
    	PaQuestionCatalogCondition paQuestionCatalogCondition=new PaQuestionCatalogCondition();
    	paQuestionCatalogCondition.setQuestionId(id);
    	List<PaQuestionCatalog> catalogList=paQuestionCatalogService.findPaQuestionCatalogByCondition(paQuestionCatalogCondition);
    	if(catalogList!=null&&!catalogList.isEmpty()) {
    		questionVo.setStageCode(catalogList.get(0).getStageCode());
    	}
    	
        // 是否已收藏
        questionVo.setIsFav(paFavoritesService.isFav(questionVo.getId(), user.getUserId(), PaperContans.QUESTION));
        
        // 根据知识点相关推荐1道题
        PaQuestionVo relatedQuestion = new PaQuestionVo();
        List<PaQuestionVo> relatedQuestionList = paQuestionService.relatedQuestion(id, 1);
        if (!relatedQuestionList.isEmpty()) {
            relatedQuestion = relatedQuestionList.get(0);
        }
        
        // 还浏览了：做过这道题的人还做过  如果为空则按知识点推荐5道
        List<PaQuestionVo> historyQuestionList =paQuestionService.historyQuestion(uuid,5);
        if (historyQuestionList.isEmpty()) {
            historyQuestionList = paQuestionService.relatedQuestion(id, 5);
        }
        
        request.setAttribute("question", questionVo);
        request.setAttribute("relatedQuestion", relatedQuestion);
        request.setAttribute("historyQuestionList", historyQuestionList);
        return QuestionPath + "detail";
    }
    
	@RequestMapping(value = "/myIndex")
	public String myIndex(HttpServletRequest request,
			@RequestParam(value = "type", required = false, defaultValue = "question") String type,
			@RequestParam(value = "libType", required = false, defaultValue = "personal") String libType) {
		request.setAttribute("libType", libType);
		return "paper".equals(type) ? structurePath("/pc/myPaperIndex") : structurePath("/pc/myQuestionIndex");
	}
    
	@RequestMapping(value = "/listMyPaper")
	public String listMyPaper(HttpServletRequest request, @CurrentUser UserInfo user, @ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, @RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "libType", required = false) String libType,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode) {
		List<PaPaperVo> plist = paPaperService.findMyPaper(user.getUserId(), libType, stageCode, subjectCode, page,
				order);
		// 是否已收藏
		for (PaPaperVo paper : plist) {
			if(paper.getUsedCount()>0) {
				paper.setHasTask(true);
			} else {
				paper.setHasTask(false);
			}
			paper.setIsDeleted(paFavoritesService.isFav(paper.getId(), user.getUserId(), PaperContans.PAPER));
		}
		request.setAttribute("paperlist", plist);
		request.setAttribute("libType", libType);
		return structurePath("/pc/myPaperList");
	}
    
    @RequestMapping(value = "/listMyQuestion")
    public String listMyQuestion(HttpServletRequest request, @CurrentUser UserInfo user, @ModelAttribute("page") Page page,
    		@ModelAttribute("order") Order order, 
    		@RequestParam(value = "dm", required = false) String dm,
    		@RequestParam(value = "libType", required = false) String libType,
    		@RequestParam(value = "stageCode", required = false) String stageCode,
    		@RequestParam(value = "subjectCode", required = false) String subjectCode) {
    	
    	List<PaQuestionVo> qlist = paQuestionService.findMyQuestion(user.getUserId(),libType,stageCode, subjectCode, page, order);
    	
    	request.setAttribute("questionlist", qlist);
    	return structurePath("pc/myQuestionList");
    }
    
	@RequestMapping(value = "/doFav")
	@ResponseBody
	public String doFavorite(@CurrentUser UserInfo user, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "type") Integer type, @RequestParam("fav") String addFav) {
		return paFavoritesService.doFavorite(id, type, addFav, user.getUserId()) ? "success" : "error";
	}
    
	
    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
    
    @RequestMapping(value = "/basket/getBasketJson")
    @ResponseBody
    private String getBasketJson(@RequestParam(value = "paperId") Integer paperId) {
    	return paPaperTreeService.findBasketJsonByPaperId(paperId);
    }
    
}
