package platform.szxyzxx.resource.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.resource.model.Favorites;
import platform.education.resource.model.Likes;
import platform.education.resource.model.Resource;
import platform.education.resource.service.FavoritesService;
import platform.education.resource.service.LikesService;
import platform.education.resource.vo.LikesCondition;
import platform.education.resource.vo.ResourceCondition;
import platform.szxyzxx.resource.dao.EasyResourceDao;
import platform.szxyzxx.resource.service.EasyResouceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EasyResouceServiceImpl implements EasyResouceService {

    @Autowired
    private LikesService likeService;
    @Autowired
    private FavoritesService favoriteService;
    @Autowired
    private EasyResourceDao easyResourceDao;
    @Autowired
    private BasicSQLService sqlService;

    @Override
    public List<Resource> pagingFindBy(Page page, ResourceCondition condition) {
        return easyResourceDao.findResourceByCondition(condition,page, Order.desc("create_date"));
    }

    @Override
    public boolean isLike(Integer userId, Integer resId) {
        LikesCondition condition=new LikesCondition();
        condition.setPosterId(userId);
        condition.setResourceId(resId);
        return likeService.count(condition)>0;
    }

    @Override
    public boolean likeRes(Integer userId, Integer resId) {
        Likes likes=new Likes();
        likes.setPosterId(userId);
        likes.setResourceId(resId);
        likeService.add(likes);
         return  true;
    }

    @Override
    public boolean isFavorite(Integer userId, Integer resId) {
        String sql="select exists(select 1 from res_favorites where resource_id="+resId+" and poster_id="+userId+")";
        return sqlService.findUniqueLong(sql)>0;
    }

    @Override
    public boolean favoriteRes(Integer userId, Integer resId) {
        Favorites f=new Favorites();
        f.setPosterId(userId);
        f.setResourceId(resId);
        favoriteService.add(f);
        return true;
    }

    @Override
    public boolean cancelLike(Integer userId, Integer resId) {
        likeService.removeUserLikes(userId,resId);
        return true;
    }

    @Override
    public boolean cancelFavorite(Integer userId, Integer resId) {
        favoriteService.removeByUserResource(userId,resId);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer resId) {
        String sql1="delete from res_resource where id="+resId;
        String sql2="delete from res_likes where resource_id="+resId;
        String sql3="delete from res_favorites where resource_id="+resId;
        Map<String,String> map1=new HashMap<String,String>();
        Map<String,String> map2=new HashMap<String,String>();
        Map<String,String> map3=new HashMap<String,String>();
        map1.put("sql",sql1);
        map2.put("sql",sql2);
        map3.put("sql",sql3);
        easyResourceDao.updateBySql(map1);
        easyResourceDao.updateBySql(map2);
        easyResourceDao.updateBySql(map3);
        return true;
    }
}
