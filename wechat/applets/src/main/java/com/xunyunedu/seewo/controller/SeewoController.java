package com.xunyunedu.seewo.controller;

import com.seewo.open.sdk.OpenApiRequest;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.seewo.client.SeewoOperateClient;
import com.xunyunedu.seewo.dto.AttendanceServiceListClassStudentRecordsParam;
import com.xunyunedu.seewo.dto.AttendanceServiceListClassStudentRecordsRequest;
import com.xunyunedu.seewo.dto.AttendanceServiceListEventRecordsByUserDateParam;
import com.xunyunedu.seewo.dto.AttendanceServiceListEventRecordsByUserDateRequest;
import com.xunyunedu.seewo.pojo.SeewoClassAttendanceQueryParam;
import com.xunyunedu.seewo.pojo.SeewoResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 希沃SDK（考勤）
 * @author chenjiaxin
 */
@RestController
@RequestMapping("/seewo")
public class SeewoController {


    @Autowired
    private BasicSQLService basicSQLService;


    /**
     * 查询学生/老师指定日期的考勤记录
     * @param attendDate 考勤日期
     * @param userCode 用户标识，学生id/老师手机号
     * @param userCodeType 用户标识类型，2：学生（默认），5：老师
     * @return
     */
    @GetMapping("/attendance")
    public ApiResult getAttendance(String attendDate,String userCode,
                                   @RequestParam(name = "userCodeType",defaultValue = "2") Integer userCodeType){

        if(userCode==null){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("userCode不可为空");
            return r;
        }
        AttendanceServiceListEventRecordsByUserDateParam param = new AttendanceServiceListEventRecordsByUserDateParam();
        AttendanceServiceListEventRecordsByUserDateParam.RequestBody requestBody = AttendanceServiceListEventRecordsByUserDateParam.RequestBody.builder()
                .appId(SeewoOperateClient.getAppId())
                .schoolUid(SeewoOperateClient.getSchoolUid())
                .attendDate(attendDate)
                .userCode(userCode)
                .userCodeType(userCodeType)
                .build();
        param.setRequestBody(requestBody);
        AttendanceServiceListEventRecordsByUserDateRequest request = new AttendanceServiceListEventRecordsByUserDateRequest(param);
        return invokeRequest(request);
    }

    /**
     * 分页查询班级异常考勤记录
     * @param pageCondition
     * @return
     */
    @PostMapping("/teamAttendance")
    public ApiResult teamAttendance(@RequestBody PageCondition<SeewoClassAttendanceQueryParam> pageCondition){
        SeewoClassAttendanceQueryParam param=pageCondition.getCondition();
        if(StringUtils.isEmpty(param.getAttendDate()) || param.getTeacherId()==null){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("条件参数不可为空");
            return r;
        }

        String sql = "SELECT g.grade_number,t.team_number FROM pj_team_teacher tt left join pj_team t on t.id=tt.team_id left join pj_grade g on g.id=t.grade_id WHERE tt.teacher_id="+param.getTeacherId()+" AND tt.is_delete = 0 AND tt.`type` = '1'";

        List<Map<String,Object>> teamInfoList=basicSQLService.find(sql);
        if(teamInfoList==null || teamInfoList.size()==0){
            ApiResult r=new ApiResult();
            r.setCode(400);
            r.setMsg("teacherId不是班主任");
            return r;
        }
        //考勤状态： 0正常，1迟到，2早退，3未打卡，4未开始，5无需考勤，6请假
        List<Integer> statusList=new ArrayList<>(6);
        statusList.add(1);
        statusList.add(2);
        statusList.add(3);
        statusList.add(4);
        statusList.add(5);
        statusList.add(6);

        AttendanceServiceListClassStudentRecordsParam requestParam = new AttendanceServiceListClassStudentRecordsParam();
        AttendanceServiceListClassStudentRecordsParam.RequestBody requestBody = AttendanceServiceListClassStudentRecordsParam.RequestBody.builder()
                .build();
        requestParam.setRequestBody(requestBody);
        AttendanceServiceListClassStudentRecordsParam.Query query = AttendanceServiceListClassStudentRecordsParam.Query.builder()
                .appId(SeewoOperateClient.getAppId())
                .schoolUid(SeewoOperateClient.getSchoolUid())
                .attendDate(param.getAttendDate())
                //考勤类型
                // 1 - 事件（默认）
                //2 - 课程
                //0 - 同时查询事件+课程考勤信息
                .attendType(0)
                //.eventId("")
                //.classUid("")
                .grade((Integer) teamInfoList.get(0).get("grade_number"))
                .clazz((Integer) teamInfoList.get(0).get("team_number"))
                .status(statusList)
                .page(pageCondition.getPageNum())
                .size(pageCondition.getPageSize())
                .build();
        requestBody.setQuery(query);
        requestParam.setRequestBody(requestBody);
        AttendanceServiceListClassStudentRecordsRequest request = new AttendanceServiceListClassStudentRecordsRequest(requestParam);
        return invokeRequest(request);
    }


    private ApiResult invokeRequest( OpenApiRequest request){
        SeewoResponseResult seewoResult= SeewoOperateClient.invoke(request);
        ApiResult result = new ApiResult();
        if(seewoResult.getStatusCode().equals(200) && seewoResult.getCode().equals("000000")){
            result.setStatus(ResultCode.SUCCESS.getCode());
            result.setMsg(seewoResult.getMessage());
            result.setData(seewoResult.getData());
            result.setCode(200);
        }else{
            result.setCode(Integer.valueOf(seewoResult.getCode()));
            result.setStatus(seewoResult.getStatusCode());
            result.setMsg(seewoResult.getMessage());
        }

        return result;
    }



}
