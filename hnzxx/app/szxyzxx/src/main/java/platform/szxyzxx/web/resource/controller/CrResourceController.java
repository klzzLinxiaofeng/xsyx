package platform.szxyzxx.web.resource.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.resource.contants.DeleteStatus;
import platform.education.resource.model.*;
import platform.education.resource.service.*;
import platform.education.resource.utils.DownloadUtil;
import platform.education.resource.utils.IconUtil;
import platform.education.resource.vo.*;
import platform.service.storage.service.FileService;
import platform.szxyzxx.resource.service.EasyResouceService;
import platform.szxyzxx.contants.ContansOfResource;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.*;
@RequestMapping("/crresource")
@Controller
public class CrResourceController {

    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
    @Autowired
    private EasyResouceService easyResouceService;
    @Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
    @Autowired
    @Qualifier("favoritesService")
    protected FavoritesService favoritesService;
    @Autowired
    @Qualifier("likesService")
    protected LikesService likesService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;

    private final static String viewBasePath = "/crresource";
    private final static String myresourcePath = "/myresource";
    private final static String commonPath = "/common";



    @RequestMapping("/fav")
    @ResponseBody
    public synchronized String fav(HttpServletRequest request, @CurrentUser UserInfo user,
                                   @RequestParam(value = "id", required=true) Integer resId,
                                   @RequestParam("isFav") boolean isFav) {
        Integer userid = user.getId();
        if (isFav) {

            if(easyResouceService.isFavorite(userid,resId)){
                return "faved";
            }else{
                easyResouceService.favoriteRes(userid,resId);
                return "success";
            }

        } else {
            favoritesService.removeByUserResource(user.getId(), resId);
            return "success";
        }
    }

    @RequestMapping("/like")
    @ResponseBody
    public synchronized String  likes(HttpServletRequest request, @CurrentUser UserInfo user,
                                      @RequestParam(value = "Id") Integer Id,
                                      @RequestParam("isLike") boolean isLike) {
        String result = "success";
        if (isLike) {
            if(easyResouceService.isLike(user.getId(), Id)){
                return "faved";
            }else{
                easyResouceService.likeRes(user.getId(), Id);
            }
        } else {
            result = likesService.removeUserLikes(user.getId(), Id);
        }
        return result;
    }



    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(HttpServletRequest request, @CurrentUser UserInfo user) throws Exception {
        try {

            Resource resource = new Resource();

            //修改参数
            String id = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            if (id != null && !id.isEmpty()) {
                resource.setId(Integer.parseInt(id));
                resource.setTitle(title);
                resource.setDescription(description);
                resourceService.modify(resource);
                return "0";
            }

            //添加参数
            //区分是个人资源还是共享资源，true:个人资源，false:共享资源
            String isPersonalStr = request.getParameter("isPersonal");
            String resType = request.getParameter("resType");
            boolean isPersonal = org.apache.commons.lang3.StringUtils.isEmpty(isPersonalStr) ? true : new Boolean(isPersonalStr);
            String fileUuid = request.getParameter("fileUuid");
            String fileSize = request.getParameter("fileSize");
            String fileUrl = request.getParameter("fileUrl");
            String fileName = request.getParameter("fileName");


            resource.setIsPersonal(isPersonal);
            resource.setUuid(fileUuid);
            if (!isPersonal && resType != null && !resType.isEmpty()) {
                resource.setResType(new Integer(resType));
            }
            resource.setObjectId(fileSize);
            resource.setThumbnailUrl(fileUrl);
            resource.setTitle(fileName);
            resource.setUserId(user.getId());
            resource.setUserName(user.getRealName());
            resource.setLikeCount(0);
            resource.setIconType(IconUtil.setIcon(fileName.substring(fileName.lastIndexOf(".") + 1)));
            resource.setIsDeleted(false);
            resource.setCreateDate(new Date());
            resource.setModifyDate(new Date());
            resource.setVerify("0");
            resourceService.add(resource);
            return "0";


        } catch (Exception e) {
            e.printStackTrace();
            return "1";
        }
    }
    
    @RequestMapping(value = "/myResource")
    public String myResource(HttpServletRequest request,
                             HttpServletResponse response, @ModelAttribute("page") Page page,
                             @CurrentUser UserInfo user) {

        page.setPageSize(5);

        String index = request.getParameter("index");
        String title = request.getParameter("title");
        String resType = request.getParameter("resType");
        String personType = request.getParameter("personType");
        String isFaved = request.getParameter("isFaved");
        Integer faved=null;
        String isMySchool = request.getParameter("isMySchool");
        ResourceCondition condition=new ResourceCondition();

        //共享资源（校本资源）
        if (ContansOfResource.SCHOOLRESOURCE.equals(personType)) {
            condition.setIsPersonal(false);
            if(resType!=null && !resType.isEmpty()){
                condition.setResType(Integer.valueOf(resType));
            }
            if("true".equals(isFaved)){
                faved=1;
                condition.setUserId(user.getId());
            }
        }else{
            //个人资源
            condition.setUserId(user.getId());
            //是否我上传的共享资源
            if(isMySchool!=null && !isMySchool.isEmpty()){
                condition.setClickCount(1);
            }


        }

        //是否收藏的资源
        condition.setFavCount(faved);
        condition.setTitle(title);

        List<Resource> resourceList=this.easyResouceService.pagingFindBy(page,condition);

        request.setAttribute("resList", resourceList);
        request.setAttribute("resType", resType);
        request.setAttribute("personType", personType);
        request.setAttribute("isFaved", isFaved);
        if (index != null && !"".equals(index)) {
            return viewBasePath + myresourcePath + "/myResourceIndex";
        } else {
            return viewBasePath + myresourcePath + "/myResourceList";
        }
    }
    

    @RequestMapping(value = "/uploadIndex")
    public String uploadIndex(HttpServletRequest request,
                              HttpServletResponse response, @CurrentUser UserInfo user) {
        String resType = request.getParameter("resType");
        String resourceType = request.getParameter("resourceType");
        String dm = request.getParameter("dm");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        if (resourceType != null && !"".equals(resourceType)) {
            if(resourceType.equals(ContansOfResource.PUBLICRESOURCE)){
                resourceType = ContansOfResource.PUBLICRESOURCE;
            }else if(resourceType.equals(ContansOfResource.SCHOOLRESOURCE)){
                resourceType = ContansOfResource.SCHOOLRESOURCE;
            }
        } else {
            resourceType = ContansOfResource.PUBLICRESOURCE;
        }

//		List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
//		request.setAttribute("slist",slist);
        //request.setAttribute("dm", dm);
        request.setAttribute("resType",resType);
        request.setAttribute("resourceType", resourceType);
        return viewBasePath + commonPath + "/uploadIndex";
    }

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request,
                         HttpServletResponse response, @CurrentUser UserInfo user) {
        String publish = request.getParameter("publish");
        String resType = request.getParameter("resType");
        String dm = request.getParameter("dm");
        // 区分校本资源和个人资源
        String resourceType = request.getParameter("resourceType");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        if (publish != null && !"".equals(publish)) {
            request.setAttribute("publish", publish);
        }

        List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
        request.setAttribute("slist",slist);
        request.setAttribute("dm", dm);
        request.setAttribute("resourceType", resourceType);
        return viewBasePath + commonPath + "/upload";
    }


    @RequestMapping(value = "/public/uploadIndex")
    public String uploadIndex1(HttpServletRequest request,
                               HttpServletResponse response, @CurrentUser UserInfo user) {
        String resType = request.getParameter("resType");
        String resourceType = request.getParameter("resourceType");
        String dm = request.getParameter("dm");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        if (resourceType != null && !"".equals(resourceType)) {
            if(resourceType.equals(ContansOfResource.PUBLICRESOURCE)){
                resourceType = ContansOfResource.PUBLICRESOURCE;
            }else if(resourceType.equals(ContansOfResource.SCHOOLRESOURCE)){
                resourceType = ContansOfResource.SCHOOLRESOURCE;
            }
        } else {
            resourceType = ContansOfResource.PUBLICRESOURCE;
        }

        List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
        request.setAttribute("slist",slist);
        request.setAttribute("dm", dm);
        request.setAttribute("resourceType", resourceType);
        return viewBasePath + commonPath + "/respublic_uploadIndex";
    }

    @RequestMapping(value = "/public/upload")
    public String publicUpload(HttpServletRequest request,
                               HttpServletResponse response, @CurrentUser UserInfo user) {
        String publish = request.getParameter("publish");
        String resType = request.getParameter("resType");
        String dm = request.getParameter("dm");
        // 区分校本资源和个人资源
        String resourceType = request.getParameter("resourceType");
        if (resType != null && !"".equals(resType)) {
            request.setAttribute("resType", resType);
        }
        if (publish != null && !"".equals(publish)) {
            request.setAttribute("publish", publish);
        }

        List<Subject>slist=	this.subjectService.findSubjectsOfSchool(user.getSchoolId());
        request.setAttribute("slist",slist);
        request.setAttribute("dm", dm);
        request.setAttribute("resourceType", resourceType);
        request.setAttribute("user", user);
        return viewBasePath + commonPath + "/respublic_upload";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(HttpServletRequest request,
                         HttpServletResponse response, @CurrentUser UserInfo user) {

        String id = request.getParameter("Id");
        easyResouceService.deleteById(Integer.valueOf(id));
        return DeleteStatus.DELETE_SUCCESS + "";
    }



    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) {
        String personType = request.getParameter("personType");
        String resType = request.getParameter("resType");
        String id = request.getParameter("id");
        String dm = request.getParameter("dm");
        CatalogResource cr = new CatalogResource();
        Resource r = this.resourceService.findResourceById(Integer.valueOf(id));
        request.setAttribute("personType", personType);
        request.setAttribute("cr", cr);
        request.setAttribute("item", r);
        request.setAttribute("id", id);
        request.setAttribute("resType", resType);
        request.setAttribute("dm", dm);
        return viewBasePath + myresourcePath + "/myResourceEdit";
    }

    @RequestMapping(value = "/public/edit")
    public String edit1(HttpServletRequest request, HttpServletResponse response,
                        @ModelAttribute("condition") ResourceVoCondition condition,
                        @CurrentUser UserInfo user) {
        String resKind = request.getParameter("resKind");
        String resType = request.getParameter("resType");
        String personType = request.getParameter("personType");
        String id = request.getParameter("id");
        String userType = request.getParameter("userType");
        Resource r = this.resourceService.findResourceById(Integer.valueOf(id));
        request.setAttribute("item", r);
        request.setAttribute("id", id);
        request.setAttribute("resType", resType);
        request.setAttribute("resKind", resKind);
        request.setAttribute("userType", userType);
        request.setAttribute("personType", personType);
        return viewBasePath + "/resource_edit";
    }

    @RequestMapping(value = "/Play")
    public ModelAndView LearningPlanPlay(@CurrentUser UserInfo user, HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        Resource mrvo = resourceService.findResourceById(Integer.parseInt(id));
        FavoritesCondition fc=new FavoritesCondition();
        fc.setPosterId(user.getId());
        fc.setResourceId(mrvo.getId());
        model.addAttribute("faved", easyResouceService.isFavorite(user.getId(),mrvo.getId()));
        LikesCondition lc=new LikesCondition();
        lc.setPosterId(user.getId());
        lc.setResourceId(mrvo.getId());
        model.addAttribute("liked",easyResouceService.isLike(user.getId(),mrvo.getId()));
        model.addAttribute("res", mrvo);
        return new ModelAndView(viewBasePath + myresourcePath + "/play");
    }

    

    @RequestMapping("/download")
    public void download(HttpServletRequest request,
                         HttpServletResponse response, @CurrentUser UserInfo user)
            throws IOException {
        String Id = request.getParameter("Id");
        Resource r = this.resourceService.findResourceById(Integer.valueOf(Id));
        String downloadTitle = r.getTitle();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // downloadTitle = downloadTitle == null || "".equals(downloadTitle)
        // ? ent.getDiskFileName() : "default";
        String filename = DownloadUtil.encodeFilenameForDownload(request,
                URLDecoder.decode(downloadTitle, "UTF-8"));
        response.addHeader("content-type", "application/x-download");
        response.setContentType("application/x-download");
        //response.setContentLength(ent.getSize().intValue());
        response.setHeader("Content-Disposition", "attachment;filename="
                + filename);
        OutputStream out = response.getOutputStream();
        // Integer flag = this.fileUploadService.downloadEntity(entId, out);
        String result = this.fileService.download(r.getUuid(), out);
        
    }





}
