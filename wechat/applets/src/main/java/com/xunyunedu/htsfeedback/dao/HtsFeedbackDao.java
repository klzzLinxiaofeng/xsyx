package com.xunyunedu.htsfeedback.dao;

import com.xunyunedu.htsfeedback.pojo.HtsFeedbackPojo;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 0:47
 */
public interface HtsFeedbackDao {




    void addFeedback(HtsFeedbackPojo htsFeedbackPojo);

    List<HtsFeedbackPojo> getFeedbackMsg(HtsFeedbackPojo htsFeedbackPojo);

    HtsFeedbackPojo findFeednackById(Integer stuId);

    List<HtsFeedbackPojo> queryByStuId(@Param("stuId") Integer stuId);
}
