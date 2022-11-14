package com.xunyunedu.resource.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.file.pojo.ResEntityFile;
import com.xunyunedu.file.service.ResEntityFileService;
import com.xunyunedu.resource.condition.ResResourceCondition;
import com.xunyunedu.resource.condition.ResourceCommentCondition;
import com.xunyunedu.resource.dao.ResResourceDao;
import com.xunyunedu.resource.param.ResourceUploadParam;
import com.xunyunedu.resource.pojo.ResResource;
import com.xunyunedu.resource.pojo.detail.ResResourceDetail;
import com.xunyunedu.resource.service.ResResourceService;
import com.xunyunedu.resource.util.IconUtil;
import com.xunyunedu.school.service.PjSchoolService;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResResourceServiceImpl implements ResResourceService {

    @Autowired
    ResEntityFileService entityFileService;

    @Autowired
    PjSchoolService schoolService;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    ResResourceDao dao;


    @Override
    public PageInfo page(PageCondition<ResResourceCondition> condition){
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List list = dao.selectByCondition(condition.getCondition());
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);

        return pageInfo;
    }

    @Override
    public PageInfo pagingQueryComment(PageCondition<ResourceCommentCondition> condition) {
        Page page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List<Map<String,Object>> list = dao.selectComment(condition.getCondition().getResId());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> map : list) {
            String userPicture=(String)map.get("userPicture");
            Timestamp commentTime=(Timestamp)map.get("commentTime");
            if(userPicture!=null && !userPicture.isEmpty()){
                map.put("userPicture",ftpUtils.relativePath2HttpUrl(userPicture));
            }
            map.put("commentTime",sdf.format(new Date(commentTime.getTime())));
        }

        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(list);

        return pageInfo;
    }

    @Override
    public ResResourceDetail getDetailById(Integer userId,Integer id) {
        ResResourceDetail detail = new ResResourceDetail();
        BeanUtils.copyProperties(dao.selectById(id),detail);
        detail.setLikeCount((int)likeCount(userId,id));
        detail.setIsLiked(isLike(userId, id));
        detail.setIsFaved(isFavorite(userId, id));
        return detail;
    }

    @Override
    public ResResource getById(Integer id){
        return dao.selectById(id);
    }



    /**
     * 上传校本资源
     * @param param
     * @return
     */
    @Override
    public ApiResult uploadResource(ResourceUploadParam param) {

        ResEntityFile entityFile = entityFileService.getByUuid(param.getFileUuid());
        if(entityFile==null){
            return ApiResult.of(ResultCode.CARD_NOT_EXISTS);
        }

        ResResource resource=new ResResource();

        resource.setIsPersonal(param.getIsPersonal());
        resource.setUuid(entityFile.getUuid());
        if(!param.getIsPersonal() && param.getResType()!=null) {
            resource.setResType(param.getResType());
        }
        resource.setObjectId(computeFileMSize(entityFile.getSize()));
        resource.setThumbnailUrl(ftpUtils.relativePath2HttpUrl(entityFile.getRelativePath()));
        resource.setTitle(entityFile.getFileName());
        resource.setUserId(param.getUserId());
        resource.setUserName(param.getUserName());
        resource.setLikeCount(0);
        resource.setIconType(IconUtil.setIcon(entityFile.getExtension()));
        resource.setIsDeleted(false);
        resource.setCreateDate(new Date());
        resource.setModifyDate(new Date());
        resource.setVerify("0");

        dao.insert(resource);

        return ApiResult.of(ResultCode.SUCCESS);
    }


    private String computeFileMSize(int byteSize){
        int m=1024*1024;
        if(byteSize<m){
            return "<1M";
        }
        return ((int)byteSize/m)+"M";
    }

    @Override
    public ApiResult add(ResResource res) {
        return dao.insert(res)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }


    @Override
    public boolean isLike(Integer userId, Integer resId) {
        String sql="select exists(select 1 from res_likes where poster_id="+userId+" and resource_id="+resId+")";
        return dao.selectUniqueResultSql(sql)>0;
    }


    @Override
    public boolean addLikeRes(Integer userId, Integer resId) {
        String sql = "insert into res_likes(poster_id,resource_id,create_date) values(" + userId + "," + resId + ",now())";
        return dao.updateBySql(sql) > 0;
    }

    @Override
    public boolean commentResource(Integer resId, Integer userId, String userName,String comment) {
        String sql="insert into sq_comment(object_id,object_type,poster_id,post_name,post_time,content,create_date,modify_date,app_id,resource_id) values('-1','-1',"+userId+",'"+userName+"',now(),'"+comment+"',now(),now(),666,"+resId+") ";
        return dao.updateBySql(sql) > 0;
    }

    @Override
    public boolean updateCancelLike(Integer userId, Integer resId) {
        String sql="delete from res_likes where poster_id="+userId+" and resource_id="+resId;
        return dao.updateBySql(sql)>0;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer resId) {
        String sql1="delete from res_resource where id="+resId;
        String sql2="delete from res_likes where resource_id="+resId;
        String sql3="delete from res_favorites where resource_id="+resId;
        dao.updateBySql(sql1);
        dao.updateBySql(sql2);
        dao.updateBySql(sql3);
        return true;
    }

    @Override
    public boolean isFavorite(Integer userId, Integer resId) {
        String sql="select exists(select 1 from res_favorites where poster_id="+userId+" and resource_id="+resId+")";
        return dao.selectUniqueResultSql(sql)>0;
    }

    @Override
    public boolean addFavoriteRes(Integer userId, Integer resId) {
        String sql="insert into res_favorites(poster_id,resource_id,create_date) values("+userId+","+resId+",now())";
        return dao.updateBySql(sql)>0;
    }

    @Override
    public boolean updateCancelFavorite(Integer userId, Integer resId) {
        String sql="delete from res_favorites where poster_id="+userId+" and resource_id="+resId;
        return dao.updateBySql(sql)>0;
    }

    @Override
    public long likeCount(Integer userId, Integer resId) {
        String sql="select count(*) from res_likes where resource_id="+resId;
        return dao.selectUniqueResultSql(sql);
    }


}
