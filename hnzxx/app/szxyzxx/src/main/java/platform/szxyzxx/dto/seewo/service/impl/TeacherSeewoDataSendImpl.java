package platform.szxyzxx.dto.seewo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.szxyzxx.dto.seewo.service.AbsBasicSeewoDataSender;
import platform.szxyzxx.dto.seewo.service.TeacherCardSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeacherSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.TeacherSeewoDataSender;
import platform.szxyzxx.dto.seewo.BatchSeewoDataOperateService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaxin
 */
@Service
public class TeacherSeewoDataSendImpl extends AbsBasicSeewoDataSender<Teacher> implements TeacherSeewoDataSender {
    @Autowired
    TeacherService teacherService;
    @Autowired
    private TeacherSeewoDataOperateService operateService;
    @Autowired
    private TeacherCardSeewoDataOperateService teacherCardSeewoDataOperateService;
    @Override
    protected String getDataName() {
        return "教师数据";
    }

    @Override
    protected BatchSeewoDataOperateService<Teacher> getDataOperator() {
        return null;
    }

    @Override
    protected List<Teacher> querySendData() {
        return teacherService.findNotSendSeewo();
    }

    @Override
    protected void updateStatus(List<Teacher> sentDataList,List<Teacher> sendFailList) {
        List<Teacher> succeedList=getSuccessfulList(sentDataList,sendFailList);
        if(succeedList.size()==0){
            return;
        }
        Integer[] succeedIds=createIdArray(succeedList,null);
        if(succeedIds.length>0) {
            teacherService.updateSeewoStatusByIds(succeedIds);
            //设置老师一卡通卡号
            teacherCardSeewoDataOperateService.batchAdd(sentDataList);
        }
    }


    private Integer[] createIdArray(List<Teacher> list,List<Integer> ignoreIds){
        List<Integer> stuIds=createIdList(list,ignoreIds);
        Integer[] ids=new Integer[stuIds.size()];
        for(int i=0;i<stuIds.size();i++){
            ids[i]=stuIds.get(i);
        }
        return ids;
    }

    private List<Integer> createIdList(List<Teacher> list,List<Integer> ignoreIds){
        List<Integer> idList=new ArrayList<>();
        for (Teacher teacher : list) {
            if(ignoreIds==null || !ignoreIds.contains(teacher.getId())){
                idList.add(teacher.getId());
            }
        }
        return idList;
    }

    private List<Teacher> getSuccessfulList(List<Teacher> list,List<Teacher> failList){
        if(failList==null || failList.size()==0){
            return list;
        }
        List<Teacher> successfulList=new ArrayList<>();
        for (Teacher teacher : list) {
            if(!contain(failList,teacher)){
                successfulList.add(teacher);
            }

        }
        return successfulList;
    }

    private boolean contain(List<Teacher> list,Teacher teacher){
        for (Teacher t : list) {
            if(t.getId().equals(teacher)){
                return true;
            }
        }
        return false;
    }

}
