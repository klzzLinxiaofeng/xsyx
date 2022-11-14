package platform.szxyzxx.dto.seewo.service.impl;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.service.AbsSingleAndBatchSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.service.StudentSeewoDataOperateService;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.StudentParentInfo;
import platform.education.generalTeachingAffair.bo.TeamStudentInfo;
import platform.szxyzxx.dto.seewo.pojo.seewo.StudentApiBindStudentToClassParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.StudentApiBindStudentToClassRequest;

import java.util.ArrayList;
import java.util.List;


/**
 * @author jiaxin
 */
@Service
public class StudentSeewoDataOperateServiceImpl extends AbsSingleAndBatchSeewoDataOperateService<TeamStudentInfo> implements StudentSeewoDataOperateService {

    @Override
    public OpenApiRequest createSingleAddRequest(TeamStudentInfo data) {

        StudentApiBindStudentToClassParam param = new StudentApiBindStudentToClassParam();
        StudentApiBindStudentToClassParam.JSONRequestBody requestBody = StudentApiBindStudentToClassParam.JSONRequestBody.builder().build();
        //设置班级信息
        StudentApiBindStudentToClassParam.MisThirdStudentQueryDto query = StudentApiBindStudentToClassParam.MisThirdStudentQueryDto.builder()
                .classId(data.getTeamId().toString())
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        //设置班级学生列表
        List<StudentInfo> studentList=data.getStuList();
        List<StudentApiBindStudentToClassParam.MisThirdStudentDto> stuDtoList=new ArrayList<>();
        for (StudentInfo stu : studentList) {
            StudentApiBindStudentToClassParam.MisThirdStudentDto.MisThirdStudentDtoBuilder builder= StudentApiBindStudentToClassParam.MisThirdStudentDto.builder()
                    .studentCode(stu.getStudentCode().toString())
                    .studentName(stu.getStudentName())
                    .cardCode(stu.getCardCode());
            if(stu.getParentList()!=null && stu.getParentList().size()>0){
                StudentParentInfo parent=stu.getParentList().get(0);
                builder.parentName(parent.getParentName())
                        .parentPhone(parent.getParentPhone());
            }

            stuDtoList.add(builder.build());
        }
        query.setStudents(stuDtoList);
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new StudentApiBindStudentToClassRequest(param);
    }
}
