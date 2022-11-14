package com.xunyunedu.htsfeedback.service;

import com.xunyunedu.htsfeedback.pojo.HtsFeedbackPojo;
import com.xunyunedu.student.pojo.ParentPojo;
import com.xunyunedu.student.pojo.StudentPojo;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 0:49
 */
public interface HtsFeedbackService {
    void addFeedback(HtsFeedbackPojo htsFeedbackPojo);

    List<HtsFeedbackPojo> getFeedbackMsg(HtsFeedbackPojo htsFeedbackPojo);

    HtsFeedbackPojo findFeedbackById(Integer stuId);


    StudentPojo getStudent(Integer stuId);

    ParentPojo getParent(Integer parentUserId);

    ParentPojo getParentByStuId(Integer stuId);

    List<HtsFeedbackPojo> queryByStuId(Integer stuId);
}
