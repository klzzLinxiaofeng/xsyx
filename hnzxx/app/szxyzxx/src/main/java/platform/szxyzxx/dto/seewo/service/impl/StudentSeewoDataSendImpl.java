package platform.szxyzxx.dto.seewo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.szxyzxx.dto.seewo.service.AbsBasicSeewoDataSender;
import platform.szxyzxx.dto.seewo.service.StudentSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.StudentSeewoDataSender;
import platform.szxyzxx.dto.seewo.SingleAndBatchSeewoDataOperateService;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.TeamStudentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxin
 */
@Service
public class StudentSeewoDataSendImpl extends AbsBasicSeewoDataSender<TeamStudentInfo> implements StudentSeewoDataSender {
    private static final Logger logger = LoggerFactory.getLogger(StudentSeewoDataSendImpl.class);
    @Autowired
    StudentService studentService;
    @Autowired
    private StudentSeewoDataOperateService operateService;

    @Override
    protected String getDataName() {
        return "学生数据";
    }

    @Override
    protected SingleAndBatchSeewoDataOperateService getDataOperator() {
        return operateService;
    }

    @Override
    protected List<TeamStudentInfo> querySendData() {
        return studentService.findBoNotSendSeewo();
    }

    @Override
    protected void updateStatus(List<TeamStudentInfo> sentDataList,List<TeamStudentInfo> sendFailList) {
        Integer[] ids=getIdArray(sentDataList,sendFailList);
        if(ids.length>0) {
            studentService.updateAsSendSeewoByIds(ids);
        }
    }

    private Integer[] getIdArray(List<TeamStudentInfo> list,List<TeamStudentInfo> failList){
        List<Integer> ignoreIds=createIdList(failList,null);
        return createIdArray(list,ignoreIds);
    }


    private Integer[] createIdArray(List<TeamStudentInfo> list,List<Integer> ignoreIds){
        List<Integer> stuIds=createIdList(list,ignoreIds);
        Integer[] ids=new Integer[stuIds.size()];
        for(int i=0;i<stuIds.size();i++){
            ids[i]=stuIds.get(i);
        }
        return ids;
    }

    private List<Integer> createIdList(List<TeamStudentInfo> list,List<Integer> ignoreIds){
        List<Integer> stuIds=new ArrayList<>();
        for (TeamStudentInfo teamStudent : list) {
            List<StudentInfo> stuList= teamStudent.getStuList();
            for (StudentInfo studentInfo : stuList) {
                if(ignoreIds==null || !ignoreIds.contains(studentInfo.getStudentCode())){
                    stuIds.add(studentInfo.getStudentCode());
                }
            }
        }
        return stuIds;
    }

}
