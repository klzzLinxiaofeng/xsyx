package platform.education.commonResource.web.learningPlan.controller;

import com.ibm.icu.text.SimpleDateFormat;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.controller.base.BaseController;
import platform.education.commonResource.web.common.vo.ResponseInfomation;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.commonResource.web.paper.contans.PaperContans;
import platform.education.commonResource.web.resource.contans.ContansOfResource;
import platform.education.commonResource.web.resource.vo.MyResourceVo;
import platform.education.exam.model.Exam;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.TextbookCatalogService;
import platform.education.generalcode.service.TextbookVersionService;
import platform.education.generalcode.vo.ResTextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.education.learningDesign.model.*;
import platform.education.learningDesign.service.*;
import platform.education.learningDesign.vo.*;
import platform.education.micro.contants.MicroType;
import platform.education.micro.model.MicroLesson;
import platform.education.micro.model.MicroLessonBgpicture;
import platform.education.micro.service.MicroLessonBgpictureService;
import platform.education.micro.service.MicroPrepareService;
import platform.education.micro.vo.MicroLessonBgpictureCondition;
import platform.education.micro.vo.MicroLessonBgpictureVo;
import platform.education.micro.vo.MicroLessonVo;
import platform.education.paper.model.PaFavorites;
import platform.education.paper.model.PaPaper;
import platform.education.paper.service.PaFavoritesService;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.vo.*;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.model.Favorites;
import platform.education.resource.model.Resource;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceHandlerService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.tags.ResourceIconFunctions;
import platform.education.resource.tags.ThumbnailUrlFunctions;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.vo.CatalogResourceCondition;
import platform.education.resource.vo.ResourceCondition;
import platform.education.resource.vo.ResourceVo;
import platform.education.resource.vo.ResourceVoCondition;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/learningPlan")
public class LearningPlanController extends BaseController {

    @Autowired
    @Qualifier("learningPlanService")
    private LearningPlanService learningPlanService;

    @Autowired
    @Qualifier("catalogResourceService")
    private CatalogResourceService catalogResourceService;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("resourceService")
    private ResourceService resourceService;

    @Autowired
    @Qualifier("lpUnitService")
    private LpUnitService lpUnitService;

    @Autowired
    @Qualifier("resourceHandlerService")
    private ResourceHandlerService resourceHandlerService;

    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;

    @Autowired
    @Qualifier("lpCatelogService")
    private LpCatelogService lpCatelogService;

    @Autowired
    @Qualifier("lpUnitFileService")
    private LpUnitFileService lpUnitFileService;
    /**
     * 科目
     */
    @Autowired
    @Qualifier("subjectService")
    protected SubjectService subjectService;

    @Autowired
    @Qualifier("resTextbookService")
    private ResTextbookService resTextbookService;

    @Autowired
    @Qualifier("jcTextbookCatalogService")
    private TextbookCatalogService jcTextbookCatalogService;

    @Autowired
    @Qualifier("microPrepareService")
    private MicroPrepareService microPrepareService;

    @Autowired
    @Qualifier("microLessonBgpictureService")
    private MicroLessonBgpictureService microLessonBgpictureService;

    @Autowired
    @Qualifier("paPaperService")
    private PaPaperService paPaperService;

    @Autowired
    @Qualifier("lpTaskService")
    private LpTaskService lpTaskService;

    @Autowired
    @Qualifier("lpFavoritesService")
    private LpFavoritesService lpFavoritesService;

    @Autowired
    @Qualifier("paPaperCatalogService")
    private PaPaperCatalogService paPaperCatalogService;

    @Autowired
    @Qualifier("jcTextbookVersionService")
    private TextbookVersionService jcTextbookVersionService;

    @Autowired
    @Qualifier("gradeService")
    private platform.education.generalTeachingAffair.service.GradeService jwGradeService;

    @Autowired
    @Qualifier("paQuestionService")
    private PaQuestionService paQuestionService;

    @Autowired
    @Qualifier("paFavoritesService")
    private PaFavoritesService paFavoritesService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("lpTemplateService")
    private LpTemplateService lpTemplateService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    private final static String viewBasePath = "learningplan";


    /*****************************************导学案*********************************************/
    @RequestMapping("/index")
    public Object index(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(value = "type", defaultValue = "school") String type) {
        ModelAndView model = new ModelAndView();
        model.setViewName(structurePath("/base/lp_index"));
        model.addObject("type", type);
        return model;
    }

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("page") Page page, @ModelAttribute("order") Order order,
                             @RequestParam(value = "textbookId", required = false) Integer textbookId,
                             @RequestParam(value = "code", required = false) String code, @CurrentUser UserInfo user,
                             @RequestParam(value = "isSchool", required = false, defaultValue = "0") Integer isSchool) {
        ModelAndView model = new ModelAndView();

        String property = order.getProperty();
        boolean ascending = order.isAscending();

        String[] codes = null;
        /**检索条件为全部*/
        if (textbookId - 0 != 0) {
            if (isSchool - 0 == 0) {
                /**校本教材目录*/
                codes = resTextbookCatalogService.findCodeByTextbookId(textbookId);
            } else {
                /**公共教材目录*/
                codes = jcTextbookCatalogService.findCodeByTextbookId(textbookId);
            }
        } else {
            /**检索条件为特定的目录*/
            codes = new String[1];
            codes[0] = code;
        }
        //总个数 用来初始化page  个人库暂不挂靠章节目录  所以不用区分校本还是个人
        Integer[] resourceIds = catalogResourceService.findResourceIdsByCodesAndType(codes, ResourceType.LEARNING_PLAN);
        /**默认按更新时间倒序*/
        if (property == null) {
            property = "modify_date";
            ascending = false;
            order.setProperty(property);
            order.setAscending(ascending);
        } else {
            if (ascending) {
                order.setProperty(property + " ASC, modify_date");
                order.setAscending(ascending);
            } else {
                order.setProperty(property + " DESC, modify_date");
            }
        }

        List<LearningPlan> learningPlanList = learningPlanService.findLearningPlanByCatalogCodes(codes, page, order);

        List<LearningPlanVo> result = new ArrayList<LearningPlanVo>(learningPlanList.size());
        for (LearningPlan learningPlan : learningPlanList) {
            LearningPlanVo vo = new LearningPlanVo();
            BeanUtils.copyProperties(learningPlan, vo);
            /**查找有没有被我收藏过*/
            LpFavorites fav = lpFavoritesService.findByLpIdAndPosterId(learningPlan.getId(), learningPlan.getUserId());
            if (fav != null) {
                vo.setIsFav(true);
            }
            /**判断是否是我创建的*/
            if (vo.getUserId() - user.getUserId() == 0) {
                vo.setMySelf(true);
            }
            /**判断是否已经被发布过*/
//			List<LpTask> taskList = lpTaskService.finByLpId(vo.getId());
//			if(taskList.size()>0) {
//				vo.setHasTask(true);
//			}
            //布置之后即使删除了任务 任然不给编辑
            if (vo.getUsedCount() > 0) {
                vo.setHasTask(true);
            }
            result.add(vo);
        }
        model.addObject("result", result);
        model.addObject("total", learningPlanList.size());

        model.addObject("source", "list");
        model.addObject("property", property);
        model.addObject("ascending", ascending);
        model.addObject("textbookId", textbookId);
        model.addObject("code", code);

        page.init(resourceIds.length, page.getPageSize(), page.getCurrentPage());
        model.setViewName(structurePath("/base/lp_list_by_catalog"));
        return model;
    }

    @RequestMapping(value = "/listBySubject")
    public ModelAndView listBySubject(HttpServletRequest request, HttpServletResponse response,
                                      @ModelAttribute("page") Page page, @ModelAttribute("order") Order order, @CurrentUser UserInfo user,
                                      @RequestParam(value = "subjectCode", required = false) String subjectCode,
                                      @RequestParam(value = "stageCode", required = false) String stageCode,
                                      @RequestParam(value = "type", required = false, defaultValue = "school") String type) {
        ModelAndView model = new ModelAndView();

        String property = order.getProperty();
        boolean ascending = order.isAscending();

        /**默认按更新时间倒序*/
        if (property == null) {
            property = "modify_date";
            ascending = false;
            order.setProperty(property);
            order.setAscending(ascending);
        } else {
            if (ascending) {
                order.setProperty(order.getProperty() + " ASC, modify_date");
                order.setAscending(ascending);
            } else {
                order.setProperty(property + " DESC, modify_date");
            }
        }

        List<LearningPlanVo> result = new ArrayList<LearningPlanVo>();
        //个人库没有学段 所以只有校本根据学段科目查  个人库和收藏夹都只根据科目查
        if (!"school".equals(type)) {
            if (!"0".equals(stageCode) && "0".equals(subjectCode)) {
                List<Subject> subjectList = getSubjectListBySchoolAndStageCode(user.getSchoolId(), stageCode);
                String[] subjects = new String[subjectList.size()];
                for (int i = 0; i < subjectList.size(); i++) {
                    subjects[i] = subjectList.get(i).getCode();
                }
                result = learningPlanService.findLearningPlanVoBySubjectCodesAndType(subjects, type, user.getUserId(), user.getSchoolId(), page, order);
                int totalRow = learningPlanService.findLearningPlanVoBySubjectCodesAndType(subjects, type, user.getUserId(), user.getSchoolId(), null, null).size();
                page.init(totalRow, page.getPageSize(), page.getCurrentPage());
            } else {
                result = learningPlanService.findLearningPlanVoBySubjectAndType(subjectCode, type, user.getUserId(), user.getSchoolId(), page, order);
            }
        } else {

            //lp表无学段   不同学段的科目是否唯一？ ：现在出现初中语文可以看到高中语文的
            //应该通过resource catalogResource来关联查询   catalogResource有学段科目
            result = learningPlanService.findLearningPlanVoByStageSubject(type, user.getUserId(), user.getSchoolId(),
                    stageCode, subjectCode, page, order);
        }

        for (LearningPlanVo learningPlanVo : result) {

            /**判断是否是我创建的*/
            if (learningPlanVo.getUserId() - user.getUserId() == 0) {
                learningPlanVo.setMySelf(true);
            }
            /**判断是否已经被发布过*/
//			List<LpTask> taskList = lpTaskService.finByLpId(learningPlanVo.getId());
//			if(taskList.size()>0) {
//				learningPlanVo.setHasTask(true);
//			}
            //布置之后即使删除了任务 任然不给编辑
            if (learningPlanVo.getUsedCount() > 0) {
                learningPlanVo.setHasTask(true);
            }
        }

        isFav(result, user.getUserId());

        model.addObject("total", page.getTotalRows());

        /**参数回传*/
        model.addObject("source", "listBySubject");
        model.addObject("type", type);
        model.addObject("property", property);
        model.addObject("ascending", ascending);
        model.addObject("subjectCode", subjectCode);
        model.addObject("stageCode", stageCode);


        model.setViewName(structurePath("/base/lp_list_by_subject"));
        model.addObject("result", result);

        return model;
    }

    private List<Subject> getSubjectListBySchoolAndStageCode(Integer schoolId, String stageCode) {
        SubjectCondition subjectCondition = new SubjectCondition();
        subjectCondition.setSchoolId(schoolId);
        if (!"0".equals(stageCode)) {
            subjectCondition.setStageCode(stageCode);
        }
        List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
        return subjectList;
    }

    /**
     * @param user   当前用户
     * @param lpId   导学案id
     * @param favTab 收藏(true)、取消收藏标记(false)
     * @return
     */
    @RequestMapping(value = "/fav")
    @ResponseBody
    public String operatorfav(@CurrentUser UserInfo user,
                              @RequestParam("id") Integer lpId, @RequestParam("fav") String favTab) {
        try {
            LearningPlan lp = learningPlanService.findLearningPlanById(lpId);
            if ("true".equals(favTab)) {
                LpFavorites lpFavorites = new LpFavorites();
                lpFavorites.setLpId(lpId);
                lpFavorites.setPosterId(user.getUserId());
                lpFavorites.setCreateDate(new Date());
                lp.setFavCount(lp.getFavCount() + 1);
                lpFavoritesService.add(lpFavorites);
            } else {
                LpFavorites lpFavorites = lpFavoritesService.findByLpIdAndPosterId(lpId, user.getUserId());
                lp.setFavCount(lp.getFavCount() - 1);
                lpFavoritesService.remove(lpFavorites);
            }
            learningPlanService.modify(lp);
        } catch (Exception e) {
            System.err.println("......");
        }
        return "success";
    }

    /**
     * 检查导学案是否能布置
     *
     * @param user
     * @param lpId
     * @return state 2可以进行布置  0分组为空  1分组内有单元为空
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public Object check(@CurrentUser UserInfo user, @RequestParam("id") Integer lpId) {
        Integer state = 2;
        List<LpCatelog> catalogList = lpCatelogService.findLpCatelogListByLpId(lpId);
        if (catalogList.size() > 0) {
            for (LpCatelog lpCatelog : catalogList) {
                LpUnitCondition lpUnitCondition = new LpUnitCondition();
                lpUnitCondition.setCatalogId(lpCatelog.getId());
                List<LpUnit> lpUnit = lpUnitService.findLpUnitByCondition(lpUnitCondition);
                if (lpUnit.size() == 0) {
                    state = 1;
                    break;
                }
            }
        } else {
            state = 0;
        }
        return state;
    }

    private void isFav(List<LearningPlanVo> result, Integer userId) {
        for (LearningPlanVo learningPlanVo : result) {
            LpFavorites fav = lpFavoritesService.findByLpIdAndPosterId(learningPlanVo.getId(), userId);
            if (fav != null) {
                learningPlanVo.setIsFav(true);
            } else {
                learningPlanVo.setIsFav(false);
            }
        }
    }

    /***
     * 跳转到创建导学案页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/creator", method = {RequestMethod.GET})
    public ModelAndView createView(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        ModelAndView model = new ModelAndView();
        /**查找科目*/
        SubjectCondition subjectCondition = new SubjectCondition();
        subjectCondition.setSchoolId(user.getSchoolId());
        List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);

        model.addObject("subjectList", subjectList);
        model.setViewName(structurePath("/base/lp_creator"));
        return model;
    }

    /***
     * 创建导学案
     * @param request
     * @param response
     * @param user
     * @return
     */
    @RequestMapping(value = "/creator", method = {RequestMethod.POST})
    @ResponseBody
    public Object createLearningPlan(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String catalog = request.getParameter("catalogResource");
        String title = request.getParameter("title");
        /**解析json*/
        JSONObject dataObj = JSONObject.fromObject(catalog);
        /**添加到校本资源或个人资源标志*/
        String type = dataObj.getString("type");

        Integer lpId = null;

        /**添加到校本资源*/
        if ("res".equals(type)) {
            /**把json的字段写到catalogResource中去*/
            CatalogResource catalogResource = resourceHandlerService.getCatalogResource(catalog);
            catalogResource.setAppId(this.getRelateApp(request));
            /**学段名字为空的情况*/
            if (catalogResource.getGradeName() == null || "".equals(catalogResource.getGradeName())) {
                GradeCondition gradeCondition = new GradeCondition();
                gradeCondition.setCode(catalogResource.getGradeCode());
                List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
                if (gradeList != null && gradeList.size() > 0) {
                    catalogResource.setGradeName(gradeList.get(0).getName());
                }
            }

            /**册次名称为空*/
            if (catalogResource.getVolumnName() == null || "".equals(catalogResource.getVolumnName())) {
                TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
                textbookVolumnCondition.setCode(catalogResource.getVolumnCode());
                List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
                if (volumnList != null && volumnList.size() > 0) {
                    catalogResource.setVolumnName(volumnList.get(0).getName());
                }
            }
            /**创建导学案*/
            lpId = resourceHandlerService.createLearningPlan(title.trim(), dataObj.getString("description"), catalogResource, user.getUserId(), user.getSchoolId(), type);
        } else {
            /**添加到个人资源*/
            CatalogResource catalogResource = new CatalogResource();
            catalogResource.setSubjectCode(dataObj.getString("subjectCode"));
            /**创建导学案*/
            lpId = resourceHandlerService.createLearningPlan(title.trim(), dataObj.getString("description"), catalogResource, user.getUserId(), user.getSchoolId(), type);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lpId", lpId);
        map.put("title", title.trim());
        return map;
    }

    /**
     * 跳转到导学案编辑页面
     *
     * @param request
     * @param response
     * @param user
     * @param id
     * @param type     跳转到编辑还是预览
     * @param editable 是否显示编辑按钮
     * @return
     */
    @RequestMapping(value = "/edit")
    public ModelAndView editLearningPlan(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user,
                                         @RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "type", required = false) String type,
                                         @RequestParam(value = "editable", required = false, defaultValue = "true") Boolean editable,
                                         @RequestParam(value = "jumpfrom", required = false, defaultValue = "other") String jumpfrom) {

        ModelAndView model = new ModelAndView();
        response.setCharacterEncoding("UTF-8");

        LearningPlan temp = learningPlanService.findLearningPlanById(id);

        /**不能编辑不是自己的导学案*/
        if (!"view".equals(type)) {
            if (temp == null || temp.getUserId() - user.getUserId() != 0) {
                return null;
            }
        }

        /**如果导学案已经发布，屏蔽编辑按钮*/
        LpTaskCondition lpTaskCondition = new LpTaskCondition();
        lpTaskCondition.setLpId(id);
        Long taskSize = lpTaskService.count(lpTaskCondition);
        boolean published = false;
        if (taskSize > 0) {
            published = true;
        }

        /**获取分组信息*/
        LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
        lpCatelogCondition.setLpId(id);
        lpCatelogCondition.setIsDeleted(false);
        List<LpCatelog> catalogList = lpCatelogService.findLpCatelogByCondition(lpCatelogCondition, null, Order.asc("list_order"));
        List<LpCatelogVo> voList = new ArrayList<LpCatelogVo>();

        /**返回导学案所有单元文件的列表*/
        List<Map<String, Object>> contentList = new ArrayList<Map<String, Object>>(catalogList.size());
        for (LpCatelog lpCatelog : catalogList) {
            LpCatelogVo vo = new LpCatelogVo();
            vo.setId(lpCatelog.getId());
            vo.setLpId(lpCatelog.getLpId());
            vo.setTitle(lpCatelog.getTitle());
            /**获取分组下面的单元*/
            LpUnitCondition lpUnitCondition = new LpUnitCondition();
            lpUnitCondition.setCatalogId(lpCatelog.getId());
            lpUnitCondition.setIsDeleted(false);
            List<LpUnit> lpUnitList = lpUnitService.findLpUnitByCondition(lpUnitCondition, null, Order.asc("list_order"));
            /**导学案某个单元的文件列表*/
            List<Map<String, Object>> resourceList = new ArrayList<Map<String, Object>>();

            for (LpUnit lpUnit : lpUnitList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("catalogId", lpCatelog.getId());
                map.put("unitId", lpUnit.getId());
                map.put("unitType", lpUnit.getUnitType());

                /**文本单元,文本单元只需要显示标题和内容*/
                if (LpUnitType.TEXT.equals(lpUnit.getUnitType()) || LpUnitType.ACTIVITY.equals(lpUnit.getUnitType())) {
                    map.put("title", lpUnit.getTitle());
                    map.put("content", lpUnit.getContent());
                    /**试卷单元,试卷单元根据该试卷是校本还是个人资源显示相关信息*/
                } else if (LpUnitType.EXAM.equals(lpUnit.getUnitType())) {
                    /**获取试卷（试卷单元只有一份试卷）*/
                    List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(lpUnit.getId());

                    for (LpUnitFile lpUnitFile : lpUnitFileList) {
                        /**获取试卷资源*/
                        PaPaper paper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
                        map.put("title", paper.getTitle());
                        map.put("crateTime", paper.getCreateDate());
                        map.put("paperId", paper.getId());
                        if (paper.getScore() != null && paper.getScore() - 0 != 0) {
                            map.put("score", paper.getScore());
                        } else {
                            map.put("score", 0);
                        }
                        map.put("difficulity", paper.getDifficulity());
                        map.put("questionsCount", paper.getQuestionCount());

                        /**相关科目和导学案关联，获取导学案*/
                        LearningPlan learningPlan = learningPlanService.findLearningPlanById(lpUnit.getLpId());
                        /**获取科目列表*/
                        Subject subject = subjectService.findUnique(user.getSchoolId(), learningPlan.getSubjectCode());

                        /**资源为个人资源刚只用显示标题和相关科目*/
                        if (OwnerMode.PERSONAL - paper.getOwnerMode() == 0) {
                            /**获取科目名称*/
                            map.put("subject", subject.getName());
                            map.put("personal", true);
                            /**校本资源则需要显示相关科目、版本、年级信息*/
                        } else {
                            map.put("subject", subject.getName());
                            map.put("personal", false);
                        }
                    }
                    /**微课单元, 微课单元可添加多个微课*/
                } else {
                    /**获取单元文件*/
                    List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(lpUnit.getId());

                    int size = 0;
                    for (LpUnitFile lpUnitFile : lpUnitFileList) {
                        Map<String, Object> resourceMap = new HashMap<String, Object>();
                        /**获取资源信息*/
                        Resource resource = resourceService.findResourceById(lpUnitFile.getResourceId());
                        if (resource != null) {
                            /**单元文件id*/
                            resourceMap.put("lpUnitFileId", lpUnitFile.getId());
                            /**资源id*/
                            resourceMap.put("id", resource.getId());
                            /**资源标题*/
                            resourceMap.put("title", resource.getTitle());
                            /**单元id*/
                            resourceMap.put("unitId", lpUnit.getId());
                            /**创建时间*/
                            resourceMap.put("createDate", resource.getCreateDate());
                            /**资源图片*/
                            resourceMap.put("iconType", resource.getIconType());

                            /**获取目录相关信息*/
                            String code = catalogResourceService.findCodeByResourceid(resource.getId());

                            resourceMap.put("catalogCode", code);
                            /**加入微课资源列表*/
                            resourceList.add(resourceMap);
                            size++;
                        }
                    }
                    /**单元文件的个数*/
                    map.put("size", size);
                }
                /**该单元下面的微课资源列表*/
                map.put("microList", resourceList);
                /**加入导学案所有单元文件列表*/
                contentList.add(map);
            }
            /**将单元挂到所属目录下面*/
            vo.setLpUnitList(lpUnitList);
            /**目录VO列表*/
            voList.add(vo);
        }
        /**跳转页面, 默认为编辑页面*/
        String path = structurePath("/edit/lp_edit");
        /**预览*/
        if ("view".equals(type)) {
            path = structurePath("/base/lp_preview");
        }
        /**是否显示编辑按钮*/
        model.addObject("editable", editable);
        model.addObject("jumpfrom", jumpfrom);
        model.addObject("published", published);
        /**导学案所有单元文件列表*/
        model.addObject("contentList", contentList);
        model.addObject("lpId", id);
        /**获取导学案*/
        LearningPlan lp = learningPlanService.findLearningPlanById(id);
        if (lp != null) {
            model.addObject("title", lp.getTitle());
        } else {
            model.addObject("title", "找不到标题");
        }
        /**导学案目录列表*/
        model.addObject("catalogList", voList);
        model.setViewName(path);

        //模板是否为空
        LpTemplateCondition lpTemplateCondition = new LpTemplateCondition();
        lpTemplateCondition.setUserId(user.getUserId());
        lpTemplateCondition.setIsDeleted(false);
        model.addObject("templateIsEmpty", lpTemplateService.count(lpTemplateCondition) > 0 ? false : true);

        return model;
    }

    /***
     * 更新导学案
     * @param request
     * @param response
     * @param user
     * @return
     */
    @RequestMapping(value = "/modify", method = {RequestMethod.POST})
    @ResponseBody
    public Object modifyLearningPlan(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {

        String catalog = request.getParameter("catalogResource");
        String title = request.getParameter("title");

        /**解析json*/
        JSONObject dataObj = JSONObject.fromObject(catalog);
        /**更新到校本资源或个人资源标志*/
        String type = dataObj.getString("type");
        /**导学案id*/
        String lpId = dataObj.getString("lpId");
        String resourceCatalogId = dataObj.getString("id");
        LearningPlan learningPlan = learningPlanService.findLearningPlanById(Integer.parseInt(lpId));

        /**更新到校本资源*/
        if ("res".equals(type)) {
            CatalogResource catalogResource = getCatalogResource(resourceCatalogId, catalog);

            /**学段名字为空的情况*/
            if (catalogResource.getGradeName() == null || "".equals(catalogResource.getGradeName())) {
                GradeCondition gradeCondition = new GradeCondition();
                gradeCondition.setCode(catalogResource.getGradeCode());
                List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
                if (gradeList != null && gradeList.size() > 0) {
                    catalogResource.setGradeName(gradeList.get(0).getName());
                }
            }

            /**册次名称为空*/
            if (catalogResource.getVolumnName() == null || "".equals(catalogResource.getVolumnName())) {
                TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
                textbookVolumnCondition.setCode(catalogResource.getVolumnCode());
                List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
                if (volumnList != null && volumnList.size() > 0) {
                    catalogResource.setVolumnName(volumnList.get(0).getName());
                }
            }
            /**更新目录信息*/
            catalogResourceService.modify(catalogResource);
            //导学案记录的catalogCode
            learningPlan.setCatalogCode(catalogResource.getCatalogCode());
        } else {
            learningPlan.setSubjectCode(dataObj.getString("subjectCode"));
        }

        learningPlan.setTitle(title.trim());
        learningPlan.setSubjectCode(dataObj.getString("subjectCode"));
        learningPlan.setDescription(dataObj.getString("description"));
        learningPlanService.modify(learningPlan);

        return new ResponseInfomation("0");
    }


    /**
     * 导学案属性编辑页面
     *
     * @param request
     * @param response
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/properties/get")
    @ResponseBody
    public ModelAndView learningPlanPropertiesGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id, @CurrentUser UserInfo user) {
        ModelAndView model = new ModelAndView();
        LearningPlan learningPlan = learningPlanService.findLearningPlanById(id);

        if (learningPlan != null) {
            model.addObject("learningPlan", learningPlan);
            /**获取资源信息*/
            Resource resource = resourceService.findResourceByObjectid(learningPlan.getUuid());
            if (resource != null) {
                /**校本资源*/
                if (!resource.getIsPersonal()) {
                    /**获取资源目录表信息*/
                    CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
                    catalogResourceCondition.setResourceId(resource.getId());
                    List<CatalogResource> crList = catalogResourceService.findCatalogResourceByCondition(catalogResourceCondition);
                    if (crList.size() > 0) {
                        /**返回资源目录表相关信息*/
                        model.addObject("cr", crList.get(0));
                        /**获取教材目录信息*/
                        ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
                        resTextbookCatalogCondition.setCode(crList.get(0).getCatalogCode());
                        List<ResTextbookCatalog> resTextbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
                        /**目录的层次*/
                        if (resTextbookCatalogList.size() > 0) {
                            model.addObject("level", resTextbookCatalogList.get(0).getLevel());
                        }
                    }
                    /**校本资源标志*/
                    model.addObject("type", "res");
                    /**个人资源*/
                } else {
                    /**查找科目*/
                    SubjectCondition subjectCondition = new SubjectCondition();
                    subjectCondition.setSchoolId(user.getSchoolId());
                    List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
                    model.addObject("subjectList", subjectList);
                }
            }
        }
        String path = structurePath("/base/lp_properties");
        model.setViewName(path);
        return model;
    }

    /***
     * 我创建的导学案列表
     * @param request
     * @param response
     * @param user
     * @param isPerson 1个人资源  0校本资源
     * @param page
     * @param order
     * @return
     */
    @RequestMapping(value = "/get")
    public ModelAndView learningPlanGet(HttpServletRequest request, HttpServletResponse response,
                                        @CurrentUser UserInfo user,
                                        @RequestParam(value = "index", required = false, defaultValue = "index") String index,
                                        @RequestParam(value = "isPerson", required = false, defaultValue = "0") Integer isPerson,
                                        @ModelAttribute("page") Page page, @ModelAttribute("order") Order order,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "subjectCode", required = false) String subjectCode,
                                        @RequestParam(value = "isFirst", required = false, defaultValue = "false") Boolean isFirst,
                                        @RequestParam(value = "lpId", required = false) String lpId) {
        ModelAndView model = new ModelAndView();
        /**查找导学案*/
        LearningPlanCondition learningPlanCondition = new LearningPlanCondition();
        learningPlanCondition.setIsPerson(isPerson);
        learningPlanCondition.setUserId(user.getUserId());
        learningPlanCondition.setEntityId("lp");
        if (title != null && !"".equals(title)) {
            learningPlanCondition.setTitle(title);
        }
        if (subjectCode != null && !"".equals(subjectCode)) {
            learningPlanCondition.setSubjectCode(subjectCode);
        }
        learningPlanCondition.setIsDeleted(false);
        /**按创建时候排序*/
        order.setProperty("create_date");
        List<LearningPlan> learningPlanList = learningPlanService.findMyLearningPlanByCondition(learningPlanCondition, page, order);

        /**返回值列表*/
        List<Map<String, Object>> list = null;

        /**获取导学案信息（校本）*/
        if (isPerson - 0 == 0) {
            list = getResLearningPlan(learningPlanList);
            /**获取导学案信息（个人）*/
        } else {
            list = getPersonLearningPlan(learningPlanList);
        }

        model.addObject("isPerson", isPerson);
        model.addObject("isFirst", isFirst);
        model.addObject("lpId", lpId);
        model.addObject("learningPlanList", list);

        String path = structurePath("/base/lp_index");
        if ("list".equals(index)) {
            path = structurePath("/base/lp_list");
        } else {
            /**获取学校的科目 */
            SubjectCondition subjectCondition = new SubjectCondition();
            subjectCondition.setSchoolId(user.getSchoolId());
            List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
            model.addObject("subjectList", subjectList);
        }

        model.setViewName(path);
        return model;
    }

    /**
     * 删除导学案
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public Object learningPlanDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false) Integer id) {
        /**删除导案需要删除导学案的其它关联表,目录表，资源表，任务表*/
        LearningPlan learningPlan = learningPlanService.findLearningPlanById(id);

        if (learningPlan != null) {
            /**删除导学案(包括导学案关联的目录、单元、任务)*/
            learningPlanService.delete(learningPlan);

            /**获取资源*/
            Resource resource = resourceService.findResourceByObjectid(learningPlan.getUuid());
            if (resource != null) {
                /**获取资源和目录关联表信息*/
                CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
                catalogResourceCondition.setResourceId(resource.getId());
                List<CatalogResource> catalogResourceList = catalogResourceService.findCatalogResourceByCondition(catalogResourceCondition);
                for (CatalogResource catalogResource : catalogResourceList) {
                    /**删除获取资源和目录关联表记录*/
                    catalogResourceService.remove(catalogResource);
                }
                /**删除记录*/
                resourceService.remove(resource);
            }
        }


        return new ResponseInfomation("0");
    }

    /****************************************导学案目录*****************************************/
    /***
     * 创建导学案目录
     * @param request
     * @param response
     * @param title
     * @return
     */
    @RequestMapping(value = "/catalog/create", method = {RequestMethod.POST})
    @ResponseBody
    public Object catalogAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("lpCatalog") LpCatelog lpCatalog) {
        /**创建导学案目录并将其置于末位*/
        lpCatalog = lpCatelogService.add(lpCatalog);
        /**创建成功*/
        if (lpCatalog.getId() != null) {
            return lpCatalog.getId();
        }
        return new ResponseInfomation("1");
    }

    /**
     * 更新导学标目录标题
     *
     * @param request
     * @param response
     * @param id
     * @param title
     * @return
     */
    @RequestMapping(value = "/catalog/modify", method = {RequestMethod.POST})
    @ResponseBody
    public Object catalogModify(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id, @RequestParam("title") String title) {
        /**查找导学案目录*/
        LpCatelog catalog = lpCatelogService.findLpCatelogById(id);
        catalog.setTitle(title);
        catalog.setModifyDate(new Date());
        /**更新导学案目录标题*/
        lpCatelogService.modify(catalog);
        return new ResponseInfomation("0");
    }

    /***
     * 删除导学案目录
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/catalog/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public Object catalogDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        /**查找导学案目录*/
        LpCatelog catalog = lpCatelogService.findLpCatelogById(id);

        if (catalog != null) {
            /**删除导学案目录*/
            lpCatelogService.remove(catalog);
        }

        return new ResponseInfomation("0");
    }

    /***
     * 导学案目录上移下移
     * @param request
     * @param response
     * @param catalogList
     * @return
     */
    @RequestMapping(value = "/catalog/move", method = {RequestMethod.POST})
    @ResponseBody
    public Object catalogMove(HttpServletRequest request, HttpServletResponse response, @RequestParam("catalogList") String catalogList) {
        lpCatelogService.modifyListOrder(catalogList);
        return new ResponseInfomation("0");
    }

    /***************************************导学案单元**************************************************/

    /**
     * 添加导学案单元
     *
     * @param request
     * @param response
     * @param lpUnit
     * @return
     */
    @RequestMapping(value = "/unit/create", method = {RequestMethod.POST})
    @ResponseBody
    public Object unitAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("lpUnit") LpUnit lpUnit) {
        lpUnit = lpUnitService.add(lpUnit);
        if (lpUnit.getId() != null) {
            return lpUnit.getId();
        } else {
            return new ResponseInfomation("1");
        }

    }

    /**
     * 导学案单元标题修改，如果是文本类型，还可以修改相应的文本内容
     *
     * @param request
     * @param respons
     * @param id
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/unit/modify", method = {RequestMethod.POST})
    @ResponseBody
    public Object unitAdd(HttpServletRequest request, HttpServletResponse respons, @RequestParam("id") Integer id,
                          @RequestParam(value = "title", required = false) String title,
                          @RequestParam(value = "content", required = false) String content) {
        /**查找导学案目录*/
        LpUnit lpUnit = lpUnitService.findLpUnitById(id);
        if (title != null) {
            lpUnit.setTitle(title);
        }
        if (content != null) {
            lpUnit.setContent(content);
        }
        lpUnit.setModifyDate(new Date());
        /**更新导学案目录标题*/
        lpUnitService.modify(lpUnit);
        return new ResponseInfomation("0");
    }

    /**
     * 导学案单元移动
     *
     * @param request
     * @param respons
     * @param unitList
     * @return
     */
    @RequestMapping(value = "/unit/move", method = {RequestMethod.POST})
    @ResponseBody
    public Object unitAdd(HttpServletRequest request, HttpServletResponse respons, @RequestParam("unitList") String unitList) {
        /**查找导学案目录*/
        lpUnitService.modifyListOrder(unitList);
        return new ResponseInfomation("0");
    }

    /***
     * 删除导学案目录
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/unit/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public Object unitDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        /**查找导学案单元*/
        LpUnit lpUnit = lpUnitService.findLpUnitById(id);

        if (lpUnit != null) {
            lpUnitService.remove(lpUnit);
        }
        return new ResponseInfomation("0");
    }

    /*****************************************导学案单元文件*************************************************/
    /**
     * 添加单元文件
     *
     * @param request
     * @param response
     * @param lpUnitId
     * @param resourceIds
     * @return
     */
    @RequestMapping(value = "/unit/file/add", method = {RequestMethod.POST})
    @ResponseBody
    public Object unitFileAdd(HttpServletRequest request, HttpServletResponse response, @RequestParam("lpUnitId") Integer lpUnitId,
                              @RequestParam("resourceIds") String resourceIds, @CurrentUser UserInfo user,
                              @RequestParam(value = "deleteMicro", defaultValue = "0") Integer deleteMicro) {
        LpUnit lpUnit = lpUnitService.findLpUnitById(lpUnitId);
        if (lpUnit.getIsDeleted()) {
            lpUnit.setIsDeleted(false);
            lpUnitService.modify(lpUnit);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject object = JSONObject.fromObject(resourceIds);
        /**资源的ids*/
        JSONArray ids = JSONArray.fromObject(object.getJSONArray("learningPlanList"));

        response.setCharacterEncoding("UTF-8");
        /**返回添加资源的相关信息*/
        List<Map<String, Object>> resourceList = new ArrayList<Map<String, Object>>(ids.size());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /**循环添加资源文件*/
        for (int i = 0; i < ids.size(); i++) {
            Map<String, Object> resourceMap = new HashMap<String, Object>();
            /**导学案单元文件信息*/
            LpUnitFile lpUnitFile = new LpUnitFile();
            lpUnitFile.setLpUnitId(lpUnitId);
            Integer resourceId = ids.getJSONObject(i).getInt("id");
            /**获取资源信息*/
            Resource resource = resourceService.findResourceById(resourceId);
            if (resource != null) {
                /**关联实体的uuid*/
                lpUnitFile.setObjectUuid(resource.getObjectId());
                lpUnitFile.setResourceId(resource.getId());
                lpUnitFile.setResourceType(resource.getResType());
                /**资源为试卷类型则*/
                if (resource.getResType() - ResourceType.EXAM == 0) {

                    /**微课资源*/
                } else if (resource.getResType() - ResourceType.MICRO == 0 || resource.getResType() - ResourceType.LEARNING_DESIGN == 0) {
                    if (deleteMicro - 1 == 0 && i == 0) {
                        List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(lpUnitId);
                        /**如果存在试卷文件，则删除(试卷单元只允许存在一份席卷)*/
                        for (LpUnitFile file : lpUnitFileList) {
                            lpUnitFileService.remove(file);
                        }
                    }
                    /**返回相关信息*/
                    resourceMap.put("id", resource.getId());
                    resourceMap.put("title", resource.getTitle());
                    resourceMap.put("createDate", dateFormat.format(resource.getCreateDate()));
                    /**资源目录信息*/
                    CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
                    catalogResourceCondition.setResourceId(resource.getId());
                    List<CatalogResource> list = catalogResourceService.findCatalogResourceByCondition(catalogResourceCondition);
                    if (list.size() > 0) {
                        /**获取目录全名(不包括最顶级目录)*/
                        String catalogName = resTextbookCatalogService.getFullNameByCode(list.get(0).getCatalogCode(), "/");
                        resourceMap.put("catalogName", catalogName);
                        /**获取书名,书名为最顶级目录的名称*/
                        ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
                        resTextbookCatalogCondition.setCode(list.get(0).getCatalogCode());
                        List<ResTextbookCatalog> resTextbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
                        if (resTextbookCatalogList.size() > 0) {
                            /**目录全名*/
                            ResTextbook textbook = resTextbookService.findResTextbookById(resTextbookCatalogList.get(0).getTestBookId());
                            resourceMap.put("catalogName", textbook.getName() + "/" + catalogName);
                        }
                    } else {
                        resourceMap.put("catalogName", "");
                    }

                    resourceList.add(resourceMap);
                    if (resource.getResType() - ResourceType.MICRO == 0) {
                        /**资源为微课类型*/
                        MicroLesson microLesson = microLessonService.findMicroLessonByUuid(resource.getObjectId());
                        if (microLesson != null) {
                            /**关联微课文件的uuid*/
                            lpUnitFile.setFileId(microLesson.getEntityId());
                            resourceMap.put("iconUrl", fileService.thumb2HttpUrlByUUID(microLesson.getEntityId()));
                        }
                    } else if (resource.getResType() - ResourceType.LEARNING_DESIGN == 0) {
                        LearningDesign learningDesign = this.learningDesignService.findLearningDesignByUuid(resource.getObjectId());
                        if (learningDesign != null) {
                            /**关联教材文件的uuid*/
                            lpUnitFile.setFileId(learningDesign.getEntityId());
                            String iconClass = ResourceIconFunctions.getClassName(resource.getIconType());
                            resourceMap.put("iconClass", iconClass);

                            String iconUrl = ThumbnailUrlFunctions.getConvertedUrl(resource.getThumbnailUrl(), resource.getIconType(), request.getContextPath(), request.getServerName());
                            resourceMap.put("iconUrl", iconUrl);
                        }
                    }
                }

            } else {
                /**获取该单元下的试卷文件*/
                List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(lpUnitId);
                /**如果存在试卷文件，则删除(试卷单元只允许存在一份席卷)*/
                for (LpUnitFile file : lpUnitFileList) {
                    lpUnitFileService.remove(file);
                }
                PaPaper paPaper = paPaperService.findPaPaperById(resourceId);
                /**试卷使用次数+1*/
                paPaper.setUsedCount(paPaper.getUsedCount() + 1);
                paPaperService.modify(paPaper);
                /**获取试卷相关信息*/
                map.put("paperId", paPaper.getId());
                /**关联试卷文件的uuid*/
                lpUnitFile.setFileId(paPaper.getXepFileId());
                lpUnitFile.setObjectUuid(paPaper.getPaperUuid());
                map.put("title", paPaper.getTitle());
                map.put("createTime", dateFormat.format(paPaper.getCreateDate()));

                if (paPaper != null) {
                    map.put("score", paPaper.getScore());
                    map.put("difficulity", paPaper.getDifficulity());
                    if (paPaper.getQuestionCount() == null) {
                        map.put("questionsCount", "");
                    } else {
                        map.put("questionsCount", paPaper.getQuestionCount());
                    }
                }

                LearningPlan learningPlan = learningPlanService.findLearningPlanById(lpUnit.getLpId());
                /**获取科目信息*/
                Subject subject = subjectService.findUnique(user.getSchoolId(), learningPlan.getSubjectCode());

                /**从个人资源添加，返回资源信息为标题和相关科目*/
                if (paPaper.getOwnerMode() - OwnerMode.PERSONAL == 0) {
                    map.put("subject", subject.getName());
                    map.put("personal", true);
                    /**从校本资源添加则需要返回标题、版本、相关科目、年级信息*/
                } else {
                    /**
                     PaPaperCatalog paPaperCatalog = paPaperCatalogService.findPaPaperCatalogByPaperId(resourceId);
                     ResTextbookCatalog resTextbookCatalog = resTextbookCatalogService.findResTextbookCatalogByCode(paPaperCatalog.getCatalogCode());
                     ResTextbook resTextbook = resTextbookService.findResTextbookById(resTextbookCatalog.getTestBookId());
                     Subject subject = subjectService.findUnique(user.getSchoolId(), resTextbook.getSubjectCode());
                     TextbookVersion textbookVersion = jcTextbookVersionService.findTextbookVersionById(Integer.parseInt(resTextbook.getVersion()));
                     List<Grade> gradeList = jwGradeService.findGradeByCode(resTextbook.getGradeCode());
                     */
                    map.put("subject", subject.getName());
                    map.put("personal", false);
                }
            }
            /**添加单元文件*/
            lpUnitFileService.add(lpUnitFile);
            resourceMap.put("lpFileId", lpUnitFile.getId());
        }

        map.put("resourceList", resourceList);
        return map;
    }

    /***
     * 查找导学案单元下的文件
     * @param request
     * @param response
     * @param lpUnitId
     * @return
     */
    @RequestMapping(value = "/unit/file/get", method = {RequestMethod.GET})
    @ResponseBody
    public Object unitFileGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("lpUnitId") Integer lpUnitId) {
        /**获取导学案单元文件列表*/
        LpUnitFileCondition lpUnitFileCondition = new LpUnitFileCondition();
        lpUnitFileCondition.setLpUnitId(lpUnitId);
        List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByCondition(lpUnitFileCondition);
        /**返回列表信息*/
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (LpUnitFile lpUnitFile : lpUnitFileList) {
            /**资源信息*/
            Resource resource = resourceService.findResourceById(lpUnitFile.getResourceId());
            if (resource != null) {
                /**设置资源相关的信息*/
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", resource.getId());
                map.put("objectId", resource.getObjectId());
                map.put("createDate", resource.getCreateDate());
                /**获取资源目录相关信息*/
                CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
                catalogResourceCondition.setResourceId(resource.getId());
                List<CatalogResource> catalogResourceList = catalogResourceService.findCatalogResourceByCondition(catalogResourceCondition);
                /**返回目录的code值*/
                if (catalogResourceList.size() > 0) {
                    map.put("catalogCode", catalogResourceList.get(0).getCatalogCode());
                }
            }
        }

        return list;
    }

    /***
     * 删除导学案单元文件
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "/unit/file/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public Object unitFileDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        LpUnitFile lpUnitFile = lpUnitFileService.findLpUnitFileById(id);
        if (lpUnitFile != null) {
            lpUnitFileService.remove(lpUnitFile);
        }
        return new ResponseInfomation("0");
    }


    /***********************************资源相关*************************************/
    /**
     * 跳转资源页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/resource/index", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView examIndex(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "unitId", required = false) Integer unitId,
                                  @RequestParam(value = "isNew", required = false) boolean isNew,
                                  @RequestParam(value = "type", required = false) Integer type) {

        ModelAndView model = new ModelAndView();
        model.addObject("unitId", unitId);
        model.addObject("isNew", isNew);

        if (type - ResourceType.MICRO == 0) {
            model.setViewName(structurePath("/edit/microList"));
        } else if (type - ResourceType.EXAM == 0) {
            model.setViewName(structurePath("/edit/examList"));
        } else if (type - ResourceType.LEARNING_DESIGN == 0) {
            model.setViewName(structurePath("/edit/materailList"));
        }

        return model;
    }

    /**
     * 导学案单元跳转添加资源页面
     *
     * @param request
     * @param response
     * @param resType  资源类型
     * @param dm
     * @return
     */
    @RequestMapping(value = "/resource/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView examList(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "personType", required = false) String personType,
                                 @RequestParam(value = "unitId", required = false) Integer unitId,
                                 @RequestParam(value = "resourceType", required = false) Integer resourceType,
                                 @RequestParam(value = "dm", required = false) String dm) {
        ModelAndView model = new ModelAndView();

        /**参数回传*/
        model.addObject("personType", personType);
        model.addObject("resType", resourceType);
        model.addObject("dm", dm);
        model.addObject("unitId", unitId);

        String path = structurePath("/edit/resource_seacher");
        model.setViewName(path);

        return model;
    }

    @RequestMapping(value = "/exam/myList")
    public ModelAndView myExamList(HttpServletRequest request,
                                   HttpServletResponse response, @ModelAttribute("page") Page page,
                                   @CurrentUser UserInfo user) {
        ModelAndView model = new ModelAndView();

        page.setPageSize(5);

        List<ResourceVo> vos = new ArrayList<ResourceVo>();
        List<PaPaper> result = null;

        /**个人资源标志*/
        String personType = request.getParameter("personType");
        if ("res_person".equals(personType)) {
            result = paPaperService.findMyUploadPaper(user.getUserId(), page, Order.desc("modify_date"));
        } else {
            result = paPaperService.findMyFavPaper(user.getUserId(), page, Order.desc("modify_date"));
        }

        for (PaPaper paPaper : result) {
            ResourceVo vo = new ResourceVo();
            vo.setId(paPaper.getId());
            vo.setTitle(paPaper.getTitle());
            vo.setIconType(9);
            vo.setFavCount(paPaper.getFavCount());
            vo.setCreateDate(paPaper.getCreateDate());
            vos.add(vo);
        }

        String index = request.getParameter("index");
        model.addObject("resList", vos);
        model.addObject("personType", personType);

        /**返回index还是列表*/
        if (index != null && !"list".equals(index)) {
            model.setViewName("resource/exam/myExamIndex");
        } else {
            model.setViewName("resource/exam/myExamList");
        }

        return model;
    }

    @RequestMapping(value = "micro/myList")
    public String myMicroList(HttpServletRequest request,
                              HttpServletResponse response, @ModelAttribute("page") Page page,
                              @CurrentUser UserInfo user) {
        String index = request.getParameter("index");
        /**个人资源标志*/
        String personType = request.getParameter("personType");
        String dm = request.getParameter("dm");
        /**默认拿校本资源*/
        if (personType == null) {
            personType = ContansOfResource.SCHOOLRESOURCE;
        }

        Integer relateAppId = this.getRelateApp(request);
        page.setPageSize(5);
        /**查找资源*/
        List<MyResourceVo> list = new ArrayList<MyResourceVo>();
        List<Resource> resourceList = this.resourceService.findMpFourResource(relateAppId, ResourceType.MICRO + "", personType, user.getUserId(), user.getSchoolId(), user.getSchoolName(), null, page, Order.desc("create_date"));
        /**找到相应的VO信息*/
        list = findVoByType(resourceList);
        request.setAttribute("resList", list);
        request.setAttribute("personType", personType);
        request.setAttribute("dm", dm);
        if ("list".equals(index)) {
            return "resource/micro/myMicroList";
        } else {
            return "resource/micro/myMicroIndex";
        }
    }

    @RequestMapping(value = "materail/myList")
    public String myMaterialList(HttpServletRequest request, HttpServletResponse response,
                                 @ModelAttribute("page") Page page, @CurrentUser UserInfo user) {
        Order order = new Order();
        order.setAscending(true);
        order.setProperty("create_date");
        String index = request.getParameter("index");
        String resType = request.getParameter("resType");
        String personType = request.getParameter("personType");

        if (personType == null) {
            personType = ContansOfResource.SCHOOLRESOURCE;
        }

        Integer relateAppId = this.getRelateApp(request);
        page.setPageSize(5);

        List<Resource> resourceList = this.resourceService.findMyMaterail(relateAppId, resType, personType, user.getUserId(),
                user.getSchoolId(), user.getSchoolName(), null, null, Order.desc("create_date"));

        String[] uuids = new String[resourceList.size()];
        for (int i = 0; i < resourceList.size(); i++) {
            uuids[i] = resourceList.get(i).getObjectId();
        }

        /**查找课件*/
        List<ResourceVo> vos = this.resourceService.findMaterailByUuids(uuids, true, order);
        vos = getPageList(vos, page);
        if (vos == null) {
            vos = new ArrayList<ResourceVo>();
        }

        request.setAttribute("resList", vos);
        request.setAttribute("resType", resType);
        request.setAttribute("personType", personType);

        if ("list".equals(index)) {
            return "resource/materail/myMaterailList";
        } else {
            return "resource/materail/myMaterailIndex";
        }
    }

    /****************************************资源相关*********************************/
    /**
     * 试卷预览
     *
     * @param user
     * @param request
     * @param paperId  试卷id
     * @param back     是否可以返回上一页 0不行 1可以
     * @param order    排序
     * @param property 排序字段
     * @param asc      升降序标志
     * @return
     */
    @RequestMapping(value = "/paper/viewer")
    public String getPaper(@CurrentUser UserInfo user, HttpServletRequest request,
                           @RequestParam(required = false, value = "paperId") Integer paperId,
                           @RequestParam(value = "back", defaultValue = "1") Integer back,
                           @RequestParam(required = false, value = "order") Order order,
                           @RequestParam(required = false, value = "property") String property,
                           @RequestParam(required = false, value = "asc") Boolean asc) {

        if (order == null) {
            order = new Order();
        }
        if (asc == null) {
            asc = true;
        }
        if (property == null || property.equals("")) {
            property = "nodeOrder";
        }
        order.setAscending(asc);
        order.setProperty(property);
        PaPaper paper = paPaperService.findPaPaperById(paperId);

        List<Subject> subjectList = subjectService.findAllSubjectName();
        Map<String, String> subjectMap = new HashMap<String, String>();
        for (Subject subject : subjectList) {
            subjectMap.put(subject.getCode(), subject.getName());
        }

        List<QuestionKnoledgeScoreVo> knoledgeList = paPaperService.findQuestionKnoledgeScoreByPaperId(paperId);
        String name = "暂无科目";
        if (knoledgeList != null && knoledgeList.size() > 0) {
            name = "";
            for (QuestionKnoledgeScoreVo qs : knoledgeList) {
                name = subjectMap.get(qs.getSubjectCode()) + "(" + qs.getScore() + ")" + ",";
            }
            name = name.substring(0, name.length() - 1);
        }

        List<PaperQuestionTree> list = paQuestionService.findPaperQuestionTreeByPaperId(paperId, order, 0);

        PaFavoritesCondition pfCondition = new PaFavoritesCondition();
        pfCondition.setObjectType(PaperContans.PAPER);
        pfCondition.setObjectId(paperId);
        List<PaFavorites> pflist = paFavoritesService.findPaFavoritesByCondition(pfCondition);

        boolean isFav = false;
        if (pflist != null && pflist.size() > 0) {
            isFav = true;
        }

        request.setAttribute("list", list);
        request.setAttribute("subject", name);
        request.setAttribute("paper", paper);
        request.setAttribute("asc", asc);
        request.setAttribute("property", property);
        request.setAttribute("isFav", isFav);
        request.setAttribute("back", back);

        String path = "/edit/paper_viewer";
        return viewBasePath + path;

    }

    /**
     * 预览(弹窗)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "micro/preview")
    public String preview(HttpServletRequest request, HttpServletResponse response) {
        playMicroImpl(request);
        return this.structurePath("/base/preview");
    }

    /**
     * 预览资源(不弹窗)
     *
     * @param id
     * @param request
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "viewer")
    public ModelAndView viewer(@RequestParam(value = "id") Integer id,
                               @RequestParam(value = "canReturn", defaultValue = "false", required = false) Boolean canReturn,
                               HttpServletRequest request, @CurrentUser UserInfo user, Model model) {
        Resource resource = this.resourceService.findResourceById(id);
        if (resource != null) {
            String objId = resource.getObjectId();
            Integer resType = resource.getResType();
            String entityId = null;
            if (ResourceType.EXAM == resType) {
                Exam exam = this.examService.findExamByUuid(objId);
                if (exam != null) {
                    entityId = exam.getEntityId();
                }
            } else if (ResourceType.MICRO == resType) {
                MicroLesson microLession = this.microLessonService.findMicroLessonByUuid(objId);
                if (microLession != null) {
                    entityId = microLession.getEntityId();
                }
            } else if (ResourceType.LEARNING_PLAN == resType) {
                LearningPlan learningPlan = this.learningPlanService.findLearningPlanByUuid(objId);
                if (learningPlan != null) {
                    entityId = learningPlan.getEntityId();
                }
            } else if (ResourceType.LEARNING_DESIGN == resType) {
                LearningDesign learningDesign = this.learningDesignService
                        .findLearningDesignByUuid(objId);
                if (learningDesign != null) {
                    entityId = learningDesign.getEntityId();
                }
            }
            if (entityId != null) {
                EntityFile entity = entityFileService.findFileByUUID(entityId);
                if (entity != null) {
                    model.addAttribute("entity", entity);
                }
            }
            model.addAttribute("res", resource);
        }
        model.addAttribute("canReturn", canReturn);
        if (!canReturn) {
            return new ModelAndView(structurePath("/edit/open_window_viewer"));
        }
        return new ModelAndView(structurePath("/edit/resource_viewer"));
    }

    /***
     * 查找导学案试卷或者微课资源文件
     * @param request
     * @param user
     * @param personType 查找公共资源还是校本资源
     * @param dm 菜单编号
     * @param condition 查询条件
     * @param page
     * @param order
     * @param model
     * @param isChange 是更改还是新增 0为新增  1为更改(更改时回显)
     * @param unitId 当isChange为1时，回显单元所选资源
     * @return
     */
    @RequestMapping(value = "/searcher")
    public ModelAndView seacherByCondition(HttpServletRequest request,
                                           @CurrentUser UserInfo user, @RequestParam(value = "personType") String personType,
                                           @RequestParam(value = "dm", required = false) String dm,
                                           @RequestParam(value = "unitId", required = false) Integer unitId,
                                           @ModelAttribute("condition") ResourceVoCondition condition,
                                           @ModelAttribute("page") Page page, @ModelAttribute("order") Order order, Model model) {
        /**校本资源查询条件*/
        if (personType.equals(ContansOfResource.SCHOOLRESOURCE)) {
            condition.setRelateAppId(this.getRelateApp(request));
            condition.setRelateSchoolId(user.getSchoolId());
            condition.setVerify(ContansOfResource.SCHOOLRESOURCE);
        } else {
            /**公共资源查询条件*/
            condition.setOwnerId(SysContants.SYSTEM_OWNER_ID);
            condition.setAppId(SysContants.SYSTEM_APP_ID);
            condition.setVerify(ResourceCondition.DEFAULT_UPLOAD_YES);
        }

        if (condition.getResType() != null && condition.getResType() - 0 == -1) {
            condition.setResType(null);
        }

        /**过滤查询条件*/
        conditionFilter(user, condition);
        if (order == null || order.getProperty() == null) {
            order.setProperty("create_date");
            order.setAscending(false);
        }

        List<ResourceVo> vos = new ArrayList<ResourceVo>();

        if (condition.getResType() == ResourceType.EXAM) {
            String[] codes = null;
            if (condition.getCatalogCode() != null) {
                codes = new String[]{condition.getCatalogCode()};
            } else {
                ResTextbook textbook = resTextbookService.findUniqueResTextbook(user.getSchoolId(), condition.getStageCode(), condition.getVersionCode(),
                        condition.getSubjectCode(), condition.getVolumnCode(), condition.getGradeCode());
                codes = resTextbookCatalogService.findCodeByTextbookId(textbook.getId());
            }
            List<PaPaperVo> paPaperList = paPaperService.findPaPaperByCatalogCodes(codes, page);
            int row = paPaperService.findPaPaperByCatalogCodes(codes, null).size();
            page.init(row, page.getPageSize());

            for (PaPaperVo paPaper : paPaperList) {
                ResourceVo vo = new ResourceVo();
                vo.setId(paPaper.getId());
                vo.setTitle(paPaper.getTitle());
                vo.setIconType(9);
                vo.setResType(ResourceType.EXAM);
                vo.setCatalogCode(paPaper.getCatalogCode());
                vo.setCreateDate(paPaper.getCreateDate());
                vo.setFavCount(paPaper.getFavCount());
                vos.add(vo);
            }
        } else if (condition.getResType() == ResourceType.MICRO) {
            /**查找微课*/
            vos = this.resourceService.findMpFourByMoreCondition(condition, page, order);
        } else if (condition.getResType() == ResourceType.LEARNING_DESIGN) {
            vos = resourceService.findResourceVoByCondition(condition, null, order);

            String[] uuids = new String[vos.size()];
            for (int i = 0; i < vos.size(); i++) {
                uuids[i] = vos.get(i).getObjectId();
            }

            /**查找课件*/
            vos = this.resourceService.findMaterailByUuids(uuids, false, order);
            vos = getPageList(vos, page);
            if (vos == null) {
                vos = new ArrayList<ResourceVo>();
            }
        }

        /**参数回传*/
        model.addAttribute("personType", personType);
        model.addAttribute("dm", dm);
        model.addAttribute("condition", condition);

        LpUnitFileCondition lpUnitFileCondition = new LpUnitFileCondition();
        lpUnitFileCondition.setLpUnitId(unitId);

        model.addAttribute("items", setIsFav(vos, user.getUserId(), this.getRelateApp(request)));

        return new ModelAndView(structurePath("/edit/resource_list"), model.asMap());
    }

    /*************************************业务方法*******************************************/
    private List<ResourceVo> getPageList(List<ResourceVo> tatolList, Page page) {
        if (tatolList == null || tatolList.size() == 0) {
            return null;
        }
        int totalPageNum = (tatolList.size() + page.getPageSize() - 1) / page.getPageSize();
        if (totalPageNum - page.getCurrentPage() > 0) {
            page.setPageEndRow(page.getCurrentPage() * page.getPageSize());
            page.setNext(true);
        } else if (totalPageNum - page.getCurrentPage() == 0) {
            page.setPageEndRow(tatolList.size());
            page.setNext(false);
        } else {
            page.setNext(false);
        }

        page.setTotalPages(totalPageNum);
        page.setTotalRows(tatolList.size());
        return tatolList.subList((page.getCurrentPage() - 1) * page.getPageSize(), page.getPageEndRow());
    }

    /**
     * 获取资源目录关联信息
     *
     * @param resourceCatalogId
     * @param catalog
     * @return
     */
    private CatalogResource getCatalogResource(String resourceCatalogId, String catalog) {
        CatalogResource catalogResource = catalogResourceService.findCatalogResourceById(Integer.parseInt(resourceCatalogId));

        JSONObject json = JSONObject.fromObject(catalog);
        String catalogCode = json.getString("catalogCode");
        String stageCode = json.getString("stageCode");
        String subjectCode = json.getString("subjectCode");
        String gradeCode = json.getString("gradeCode");
        String version = json.getString("version");
        String volumn = json.getString("volumn");
        String stageName = json.getString("stageName");
        String subjectName = json.getString("subjectName");
        String versionName = json.getString("versionName");

        catalogResource.setCatalogCode(catalogCode);
        catalogResource.setGradeCode(gradeCode);
        catalogResource.setVolumnCode(volumn);
        catalogResource.setVersionCode(version);
        catalogResource.setVersionName(versionName);
        catalogResource.setStageCode(stageCode);
        catalogResource.setStageName(stageName);
        catalogResource.setSubjectCode(subjectCode);
        catalogResource.setSubjectName(subjectName);
        catalogResource.setModifyDate(new Date());
        return catalogResource;
    }

    /***
     * 设置播放微课信息
     * @param request
     */
    private void playMicroImpl(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        String microId = request.getParameter("microId");
        String microPublishedId = request.getParameter("microPublishedId");
        List<MicroLessonVo> microList = new ArrayList<MicroLessonVo>();
        if (StringUtils.isEmpty(flag)) {
            flag = "0";
        }
        if (microPublishedId != null && !"".equals(microPublishedId) && (microId == null || "".equals(microId))) {
            JSONArray array = microPrepareService.getPublishedPlayJson(microPublishedId);
            for (int i = 0; i < array.size(); i++) {
                JSONObject micro = (JSONObject) array.get(i);
                MicroLesson ml = this.microLessonService.findMicroLessonByUuid((String) micro.get("id"));
                if (ml != null) {
                    MicroLessonVo vo = BeanSettings(ml);
                    List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
                    vo.setMlbs(vos);
                    microList.add(vo);
                }
            }
        } else {
            MicroLesson ml = this.microLessonService.findMicroLessonByUuid(microId);
            if (ml != null) {
                MicroLessonVo vo = BeanSettings(ml);
                List<MicroLessonBgpictureVo> vos = bgSettings(ml.getUuid());
                vo.setMlbs(vos);
                microList.add(vo);
            }
        }
        request.setAttribute("type", MicroType.MICRO_COURSE);
        request.setAttribute("microPublishedId", microPublishedId);
        request.setAttribute("microList", microList);
        request.setAttribute("flag", flag);
    }

    /**
     * 设置MicroLessonVo的属性
     *
     * @param ml
     * @return
     */
    private MicroLessonVo BeanSettings(MicroLesson ml) {
        if (ml != null) {
            MicroLessonVo vo = new MicroLessonVo();
            BeanUtils.copyProperties(ml, vo);

            String jsonEntityId = ml.getJsonEntityId();
            String mediaEntityId = ml.getMediaEntityId();
            String propertyEntityId = ml.getPropertyEntityId();
            String logoEntityId = ml.getLogoEntityId();
            if (!StringUtils.isEmpty(logoEntityId)) {
                FileResult logoFile = fileService.findFileByUUID(logoEntityId);
                if (logoFile.getEntityFile() != null) {
                    vo.setLogoPath(logoFile.getEntityFile().getRelativePath());
                }

            }

            if (!StringUtils.isEmpty(jsonEntityId)) {
                FileResult jsonFile = fileService.findFileByUUID(jsonEntityId);
                if (jsonFile.getEntityFile() != null) {
                    vo.setJsonPath(jsonFile.getEntityFile().getRelativePath());
                }
            }
            if (!StringUtils.isEmpty(mediaEntityId)) {
                FileResult mediaFile = fileService.findFileByUUID(mediaEntityId);
                if (mediaFile.getEntityFile() != null) {
                    vo.setMediaPath(mediaFile.getEntityFile().getRelativePath());
                }
            }
            if (!StringUtils.isEmpty(propertyEntityId)) {
                FileResult propertyFile = fileService.findFileByUUID(propertyEntityId);
                if (propertyFile.getEntityFile() != null) {
                    vo.setPropertyPath(propertyFile.getEntityFile().getRelativePath());
                }
            }
            return vo;
        }
        return null;
    }

    private List<MicroLessonBgpictureVo> bgSettings(String uuid) {
        //设置背景文件的路径
        MicroLessonBgpictureCondition mlbCondition = new MicroLessonBgpictureCondition();
        mlbCondition.setLessonId(uuid);
        List<MicroLessonBgpicture> mlbs = microLessonBgpictureService.findMicroLessonBgpictureByCondition(mlbCondition);
        List<MicroLessonBgpictureVo> vos = new ArrayList<MicroLessonBgpictureVo>();
        if (mlbs.size() > 0) {
            for (MicroLessonBgpicture mlb : mlbs) {
                MicroLessonBgpictureVo vo = new MicroLessonBgpictureVo();
                BeanUtils.copyProperties(mlb, vo);
                String entityId = mlb.getEntityId();
                if (entityId != null) {
                    FileResult fileResult = fileService.findFileByUUID(entityId);
                    vo.setBgPath(fileResult.getEntityFile().getRelativePath());
                }
                vos.add(vo);
            }
        }
        return vos;
    }

    /**
     * 获取校本导学案其它信息
     *
     * @param learningPlanList
     * @return
     */
    private List<Map<String, Object>> getResLearningPlan(List<LearningPlan> learningPlanList) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (LearningPlan learningPlan : learningPlanList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", learningPlan.getId());
            map.put("title", learningPlan.getTitle());
            map.put("create_date", dataFormat.format(learningPlan.getCreateDate()));

            LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
            lpCatelogCondition.setLpId(learningPlan.getId());
            List<LpCatelog> lpCatalogList = lpCatelogService.findLpCatelogByCondition(lpCatelogCondition);

            LpTaskCondition lpTaskCondition = new LpTaskCondition();
            lpTaskCondition.setLpId(learningPlan.getId());
            List<LpTask> lpTaskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

            if (lpTaskList.size() > 0) {
                map.put("hasTask", true);
            } else {
                map.put("hasTask", false);
            }

            map.put("lpList", lpCatalogList.size());
            /**查找资源*/
            Resource resource = resourceService.findResourceByObjectid(learningPlan.getUuid());
            /**查找目录相关信息*/
            if (resource != null) {
                CatalogResourceCondition catalogResourceCondition = new CatalogResourceCondition();
                catalogResourceCondition.setResourceId(resource.getId());
                List<CatalogResource> catalogResourceList = catalogResourceService.findCatalogResourceByCondition(catalogResourceCondition);
                /**存在目录信息*/
                if (catalogResourceList.size() > 0) {
                    map.put("subjectName", catalogResourceList.get(0).getSubjectName());
                    map.put("versionName", catalogResourceList.get(0).getVersionName());
                    map.put("volumnName", catalogResourceList.get(0).getVolumnName());
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 获取个人导学案其它 信息
     *
     * @param learningPlanList
     * @return
     */
    private List<Map<String, Object>> getPersonLearningPlan(List<LearningPlan> learningPlanList) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (LearningPlan learningPlan : learningPlanList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", learningPlan.getId());
            map.put("title", learningPlan.getTitle());
            map.put("create_date", dataFormat.format(learningPlan.getCreateDate()));

            LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
            lpCatelogCondition.setLpId(learningPlan.getId());
            List<LpCatelog> lpCatalogList = lpCatelogService.findLpCatelogByCondition(lpCatelogCondition);
            map.put("lpList", lpCatalogList.size());

            LpTaskCondition lpTaskCondition = new LpTaskCondition();
            lpTaskCondition.setLpId(learningPlan.getId());
            List<LpTask> lpTaskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);

            if (lpTaskList.size() > 0) {
                map.put("hasTask", true);
            } else {
                map.put("hasTask", false);
            }

            /**获取所属科目*/
            SubjectCondition subjectCondition = new SubjectCondition();
            subjectCondition.setCode(learningPlan.getSubjectCode());
            List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);

            if (subjectList.size() > 0) {
                map.put("subjectName", subjectList.get(0).getName());
            }

            list.add(map);
        }
        return list;
    }

    /**
     * 通过资源集合和类型封装
     *
     * @param resourceList
     * @param typeint
     * @return
     */
    private List<MyResourceVo> findVoByType(List<Resource> resourceList) {
        List<MyResourceVo> list = new ArrayList<MyResourceVo>();
        for (Resource r : resourceList) {
            MyResourceVo mrvo = new MyResourceVo();
            EntityFile ent = new EntityFile();
            mrvo.setResEnt(r);
            if (ResourceType.EXAM == r.getResType()) {
                Exam em = new Exam();
                em = this.examService.findExamByUuid(r.getObjectId());
                if (em != null) {

                    ent = this.entityFileService.findFileByUUID(em
                            .getEntityId());
                }
            } else if (ResourceType.MICRO == r.getResType()) {
                MicroLesson em = new MicroLesson();
                em = this.microLessonService.findMicroLessonByUuid(r
                        .getObjectId());
                if (em != null) {
                    ent = this.entityFileService.findFileByUUID(em
                            .getEntityId());
                }
            }
            if (ent != null && ent.getThumbnailUrl() != null) {
                mrvo.setThumbnailUrl(ent.getThumbnailUrl());
                System.out.println(ent.getExtension());

                mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
            } else {
                mrvo.setThumbnailUrl("");
            }
            list.add(mrvo);
        }

        return list;
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user, ResourceVoCondition condition) {
        if (condition.getGradeCode() != null && !"".equals(condition.getGradeCode().trim())) {
            if ("0".equals(condition.getGradeCode())) {
                condition.setGradeCode("");
            }
        }
    }

    private List<ResourceVo> setIsFav(List<ResourceVo> vos, Integer userId,
                                      Integer relateAppId) {
        for (ResourceVo vo : vos) {
            Favorites fav = this.favoritesService.findUnique(relateAppId, userId, vo.getId());
            vo.setFav(fav != null);
        }
        return vos;
    }


    // 添加导学案模板
    @RequestMapping("/addTemplate")
    @ResponseBody
    public String addTemplate(@CurrentUser UserInfo user, @RequestParam(value = "title") String title,
                              @RequestParam(value = "lpId") Integer lpId) {
        return lpTemplateService.addLpTemplate(user.getUserId(), lpId, title) ? "success" : "fail";

    }

    //当前导学案是否为空
    @RequestMapping("/lpIsEmpty")
    @ResponseBody
    public String lpIsEmpty(@RequestParam(value = "lpId") Integer lpId) {
        LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
        lpCatelogCondition.setLpId(lpId);
        return lpCatelogService.count(lpCatelogCondition) > 0 ? "yes" : "no";
    }

    // 导入模板
    @RequestMapping("/applyTemplate")
    @ResponseBody
    public String applyTemplate(@RequestParam(value = "lpId") Integer lpId,
                                @RequestParam(value = "templateId") Integer templateId) {
        return lpTemplateService.applyLpTemplate(lpId, templateId) ? "success" : "fail";
    }

    //删除模板
    @RequestMapping("/deleteTemplate")
    @ResponseBody
    public String applyTemplate(@RequestParam(value = "templateId") Integer templateId) {
        LpTemplate lpTemplate = new LpTemplate();
        lpTemplate.setId(templateId);
        lpTemplate.setIsDeleted(true);
        return lpTemplateService.modify(lpTemplate) != null ? "success" : "fail";
    }

    //重命名模板
    @RequestMapping("/modifyTemplate")
    @ResponseBody
    public String modifyTemplate(@RequestParam(value = "templateId") Integer templateId,
                                 @RequestParam(value = "title") String title) {
        LpTemplate lpTemplate = new LpTemplate();
        lpTemplate.setId(templateId);
        lpTemplate.setTitle(title);
        return lpTemplateService.modify(lpTemplate) != null ? "success" : "fail";
    }

    // 获取模板
    @RequestMapping("/getTemplate")
    public String getTemplate(@CurrentUser UserInfo user, HttpServletRequest request,
                              @RequestParam(value = "lpId") Integer lpId) {
        List<LpTemplateVo> templateVos = lpTemplateService.findLpTemplate(user.getUserId());
        request.setAttribute("templateVos", templateVos);
        request.setAttribute("lpId", lpId);
        return structurePath("/edit/lp_template");
    }

    // 判断是否有模板
    @RequestMapping("/templateIsEmpty")
    @ResponseBody
    public String templateIsEmpty(@CurrentUser UserInfo user) {
        LpTemplateCondition lpTemplateCondition = new LpTemplateCondition();
        lpTemplateCondition.setUserId(user.getUserId());
        lpTemplateCondition.setIsDeleted(false);
        return lpTemplateService.count(lpTemplateCondition) > 0 ? "no" : "yes";
    }


}
