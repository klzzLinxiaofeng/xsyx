package platform.education.lads.web.controller;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.action;
//
//import com.gzxtjy.common.service.HibernateBaseService;
//import com.gzxtjy.common.web.action.BaseAction;
//import com.gzxtjy.lads.constants.EmbedSystem;
//import com.gzxtjy.lads.util.LadsCommonUtils;
//import com.gzxtjy.portal.session.SessionManager;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author Administrator
// */
//@Controller("ladsImageAction")
//@Scope("prototype")
//public class ImageAction extends BaseAction {
//
//    @Resource
//    private HibernateBaseService hibernateBaseServiceImpl;
//
//    public String loadPersonalImageList() {
//        HttpServletRequest request = super.getRequest();
//        String sysType = request.getParameter("sysType");
//        String toolId = request.getParameter("toolId");
//        if (sysType.equals(EmbedSystem.XTJY)) {
//            DetachedCriteria criteria = DetachedCriteria.forClass(com.gzxtjy.resources.entities.Resource.class);
//            criteria.createAlias("centerUser", "centerUser");
//            criteria.add(Restrictions.eq("centerUser.userId", SessionManager.getUser().getId()));
//            // 教材目录筛选
//            //criteria = CatalogUtil.setDirectoryInCriteria(request, criteria);
//            criteria.createAlias("resFile", "resFile");
//            criteria.add(Restrictions.or(Restrictions.or(
//                    Restrictions.eq("resFile.suffix", "jpg").ignoreCase(),
//                    Restrictions.eq("resFile.suffix", "jpeg").ignoreCase()
//                    ),Restrictions.or(
//                    Restrictions.eq("resFile.suffix", "png").ignoreCase(),
//                    Restrictions.eq("resFile.suffix", "gif").ignoreCase())));
////            if (keyWord != null && !"".equals(keyWord)) {
////                criteria.add(Restrictions.like("title", "%" + keyWord + "%"));
////            }
//            criteria.addOrder(Order.desc("createTime"));
//            List<com.gzxtjy.resources.entities.Resource> imageList = this.hibernateBaseServiceImpl.findByCriteria(pagination, criteria);
//            request.setAttribute("urlPara", LadsCommonUtils.createUrlPara(request));
//            request.setAttribute("imageList", imageList);
//        }
//        request.setAttribute("id", toolId);
//
//        return "author_image_personal_list";
//    }
//}
