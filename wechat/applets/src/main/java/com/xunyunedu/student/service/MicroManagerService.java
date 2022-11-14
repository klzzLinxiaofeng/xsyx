package com.xunyunedu.student.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.student.pojo.*;

import java.util.List;

/**
 *  小程序微课管理
 *
 *  @author: yhc
 *  @Date: 2020/10/16 13:23
 *  @Description:
 */
public interface MicroManagerService{


    PageInfo<MicroManagerPojo> getMicroByCondition(CommonPojo common, Integer pageNum, Integer pageSize);


    PageInfo<UserCommentsPojo> getMicroCommentsMsg(UserCommentsPojo userCommentsPojo, Integer pageNum, Integer pageSize);

    void addComments(UserCommentsPojo userCommentsPojo);

    List<MicroTypePojo> getMicroTypeBySchoolId(Integer schooldId);

    List<GradePojo> getMicroGradeBySchoolId(Integer schooldId);

    CollectionCommentsPojo getCollectType(CollectionCommentsPojo collectionCommentsPojo);

    void addKeepCollect(CollectionCommentsPojo collectionCommentsPojo);

    PageInfo<MicroManagerPojo> getMyCollection(CommonPojo common, Integer pageNum, Integer pageSize);
}
