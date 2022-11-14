package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 13:24
 * @Description:
 */
public interface MicroManagerDao {
    /**
     * 获取所有微课
     *
     * @param schooldId
     * @return
     */
    List<MicroManagerPojo> getMicroByCondition(CommonPojo common);

    List<UserCommentsPojo> getMicroCommentsMsg(UserCommentsPojo userCommentsPojo);

    void addComments(UserCommentsPojo userCommentsPojo);

    List<MicroTypePojo> getMicroTypeBySchoolId(@Param("schooldId") Integer schooldId);

    List<GradePojo> getMicroGradeBySchoolId(@Param("schooldId") Integer schooldId, @Param("schoolYear") String schoolYear);

    CollectionCommentsPojo getCollectType(CollectionCommentsPojo collectionCommentsPojo);

    void updateCollectionByCondition(CollectionCommentsPojo collectionCommentsPojo);

    void addCollection(CollectionCommentsPojo collectionCommentsPojo);

    void updateAddMicro(MicroManagerPojo microManagerPojo);

    List<MicroManagerPojo> getMyCollectionMicroByCondition(CommonPojo common);
}