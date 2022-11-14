package platform.education.commonResource.web.resource.common.service;

import framework.generic.dao.Page;
import platform.education.resource.model.Resource;
import platform.education.resource.vo.ResourceCondition;

import java.util.List;

public interface EasyResouceService {

    List<Resource> pagingFindBy(Page page, ResourceCondition condition);
    boolean isLike(Integer userId,Integer resId);
    boolean likeRes(Integer userId,Integer resId);
    boolean cancelLike(Integer userId,Integer resId);
    boolean isFavorite(Integer userId,Integer resId);
    boolean favoriteRes(Integer userId,Integer resId);
    boolean cancelFavorite(Integer userId,Integer resId);
    boolean deleteById(Integer resId);
}
