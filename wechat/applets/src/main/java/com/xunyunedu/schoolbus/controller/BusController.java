package com.xunyunedu.schoolbus.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.schoolbus.pojo.BusParentPick;
import com.xunyunedu.schoolbus.pojo.ManualOutParam;
import com.xunyunedu.schoolbus.pojo.GatePickInfo;
import com.xunyunedu.schoolbus.pojo.PickInfo;
import com.xunyunedu.schoolbus.service.BetterBusThirtyPartyApiService;
import com.xunyunedu.schoolbus.service.BusApiService;
import com.xunyunedu.schoolbus.service.BusParentPickService;
import com.xunyunedu.student.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 校车接送管理
 */
@RequestMapping("/schoolBus")
@RestController
public class BusController {
    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private BusParentPickService parentPickService;

    @Autowired
    private BusApiService busApiService;

    @Autowired
    private BetterBusThirtyPartyApiService betterBusThirtyPartyApiService;

    /**
     * 获取当天请假的学生列表
     * @return
     */
    @GetMapping("/leaveList")
    public List<Map<String, Object>> list(){
        String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String sql="SELECT s.team_name as teamName, s.`name` as stuName, sa.begin_date as beginDate, sa.end_date as endDate FROM pj_student_asking sa LEFT JOIN pj_student s ON sa.student_id = s.id WHERE sa.india_status = 2 and '"+nowDate+"' between sa.begin_date and sa.end_date";
        return basicSQLService.find(sql);
    }

    /**
     * 接送学生
     * @param busParentPick 接送信息
     * @return
     */
    @PostMapping("/parentPick")
    public ApiResult parentPick(@RequestBody BusParentPick busParentPick){
        String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(nowIsPicked(busParentPick.getStudentId(),busParentPick.getDirection())){
            ApiResult apiResult=new ApiResult();
            apiResult.setCode(400);
            apiResult.setMsg("请勿重复接送");
            return apiResult;
        }
        //坐了校车不能接送
        if(basicSQLService.findUniqueLong("select exists( select 1 from bus_student_sign where stu_card='"+busParentPick.getStuEmpCard()+"' and sign_date='"+nowDate+"' and direction="+busParentPick.getDirection()+")") >0 ){
            ApiResult apiResult=new ApiResult();
            apiResult.setCode(400);
            apiResult.setMsg("已乘坐校车，无法切换为家长接送");
            return apiResult;
        }
        busParentPick.setPickDate(nowDate);
        if(busParentPick.getCreateType()==null){
            busParentPick.setCreateType(0);
        }
        parentPickService.add(busParentPick);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 获取学生校车信息
     * @param stuId 学生id
     * @return
     */
    @GetMapping("/getBusInfo")
    public ApiResult getBusInfo(Integer stuId){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        //是否在乘坐校车学生名单中
        if(basicSQLService.findUniqueLong("select exists (select 1 from pj_school_bus_manger where student_id=(select id from pj_student where id="+stuId+")) e")>0){
            String lastBusCarNo=(String) basicSQLService.findUnique("select last_by_car_no from pj_student where id="+stuId);
            if(lastBusCarNo!=null){
                //由于报错，先注释2021
                //apiResult.setData(busApiService.getByCarNo(lastBusCarNo));
            }
        }
        return apiResult;
    }

    /**
     * 获取学生打卡信息列表
     * @param stuUserId 学生userId
     * @param stuEmpCard 学生empCard
     * @param direction 方向（0：：上学，1：放学）
     * @return
     */
    @GetMapping("/getStuPickInfo")
    public ApiResult<List<PickInfo>> getStuPickInfo(Integer stuUserId,String stuEmpCard,Integer direction){
            //校车报废，直接注释 2022，10，12
        /*  String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        GatePickInfo gatePickInfo=betterBusThirtyPartyApiService.getStuPickInfo(stuUserId,nowDate,direction,direction==0 ? 1:0);
        List list=null;
        if(gatePickInfo!=null){
            list=createPickInfoBy(gatePickInfo);
        }else{
            List<Map<String,Object>> signList=basicSQLService.find("select sign_time,sign_address from bus_student_sign where stu_card='"+stuEmpCard+"' and sign_date='"+nowDate+"' and direction="+direction);
            list=createPickInfoBy(signList,direction);
        }*/
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(null);
        return apiResult;
    }





    /**
     * 获取当前日期已接送信息
     * @param stuId 学生id
     * @param direction 方向（0：上学，1：放学）
     * @return
     */
    @GetMapping("/getPickedAddress")
    public ApiResult getPicked(Integer stuId,Integer direction){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        apiResult.setData(basicSQLService.find("select id,direction,place from bus_parent_pick where student_id="+stuId+" and pick_date='"+nowDate+"' and direction="+direction+""));
        return apiResult;
    }

    /**
     * 切换接送位置
     * @param id 接送id
     * @param place 新的接送位置
     * @return
     */
    @GetMapping("/updatePlace")
    public ApiResult updatePlace(String place,Integer id){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        basicSQLService.update("update  bus_parent_pick set place='"+place+"' where id="+id);
        return apiResult;
    }

    /**
     * 删除接送（切换为校车接送）
     * @param id 接送id
     * @return
     */
    @GetMapping("/delete")
    public ApiResult delete(Integer id){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        basicSQLService.update("delete from bus_parent_pick where id="+id);
        return apiResult;
    }


    /**
     * 查询今日放学家长接送的学生
     * @return
     */
    @PostMapping("/queryTodayParentPickStuByName")
    public ApiResult queryTodayParentPick(@RequestBody PageCondition<StudentPojo> condition) {
        StudentPojo studentPojo=condition.getCondition();
        PageInfo pageInfo =basicSQLService.findByPaging("SELECT s.`name`, s.team_name AS teamName, s.id FROM bus_parent_pick p INNER JOIN pj_student s ON p.student_id = s.id WHERE p.is_ruxiao=1 and p.pick_date = '"+getNowDate()+"' AND p.direction = 1 AND s.`name` LIKE '%"+studentPojo.getName()+"%'",condition.getPageNum(),condition.getPageSize());
        return new ApiResult(ResultCode.SUCCESS, pageInfo);
    }

    /**
     *手动设置家长接送学生已出校
     * @return
     */
    @PostMapping("/manualOut")
    public ApiResult forgetCardOut(@RequestBody ManualOutParam param){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        /*List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+param.getOperatorUserId());
        for(Map<String,Object> aa:mapList2){*/
            System.out.println("手动出校开始");
          /*  if(aa.get("code").toString().equals("CLASS_MASTER") || aa.get("name").toString().equals("班主任")){*/
                Date now=new Date();
                String nowDateTime=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(now);
                String nowDate=nowDateTime.substring(0,10);
                List<Map<String,Object>> list=basicSQLService.find("select id,manual_out_operator from bus_parent_pick where student_id="+param.getStuId()+" and pick_date='"+nowDate+"' and direction=1");
                if(list.size()==0){
                    apiResult=new ApiResult();
                    apiResult.setCode(400);
                    apiResult.setMsg("请先添加该学生家长接送信息");
                    return apiResult;
                }else if(list.size()==1){
                    String manualOutOperator=list.get(0).get("manual_out_operator").toString();
                    String id=list.get(0).get("id").toString();
                    if(!manualOutOperator.equals("0")){
                        apiResult=new ApiResult();
                        apiResult.setCode(400);
                        apiResult.setMsg("请勿重复操作");
                        return apiResult;
                    }
                    basicSQLService.update("update bus_parent_pick set manual_out_operator="+param.getOperatorUserId()+",manual_out_time='"+nowDateTime+"',is_ruxiao=2 where id="+id);
                    return apiResult;
                }
                apiResult=new ApiResult();
                apiResult.setCode(400);
                apiResult.setMsg("家长接送数据有误");
                return apiResult;
         /*   }
        }*/
        //apiResult=new ApiResult(ResultCode.USER_ROLE_ERROR);
     //return apiResult;
    }


    private  List<PickInfo> createPickInfoBy(GatePickInfo gatePickInfo){
        PickInfo pickInfo=new PickInfo();
        pickInfo.setType(1);
        pickInfo.setStatus(gatePickInfo.getInAndOutType()==1 ? "进校打卡":"出校打卡");
        pickInfo.setPlace(gatePickInfo.getDoorName());
        pickInfo.setTime(formatEventTime(gatePickInfo.getEventTime()));

        List<PickInfo> list=new ArrayList<>(1);
        list.add(pickInfo);
        return list;
    }
    private  List<PickInfo> createPickInfoBy(List<Map<String,Object>> signList,Integer direction) {
        if(signList==null || signList.size()==0){
            return null;
        }
        if(signList.size()>1) {
            signList.sort(Comparator.comparing(o -> o.get("sign_time").toString()));
        }
        List<PickInfo> list=new ArrayList<>();

        PickInfo upPick=new PickInfo();
        upPick.setType(0);
        upPick.setStatus("上车打卡");
        upPick.setPlace(signList.get(0).get("sign_address").toString());
        upPick.setTime(signList.get(0).get("sign_time").toString());
        list.add(upPick);

        if(direction==1 && signList.size()>1){
            PickInfo downPick=new PickInfo();
            downPick.setType(0);
            downPick.setStatus("下车打卡");
            downPick.setPlace(signList.get(signList.size()-1).get("sign_address").toString());
            downPick.setTime(signList.get(signList.size()-1).get("sign_time").toString());
            list.add(downPick);
        }
        return list;
    }


    private String formatEventTime(String eventTime){
        //2018-08-14T19:49:10+08:00;
        if(eventTime!=null && eventTime.length()==25){
            return eventTime.substring(0,10)+" "+eventTime.substring(11,19);
        }
        return eventTime;

    }


    private boolean nowIsPicked(Integer stuId,Integer direction){
        String nowDate=getNowDate();
        return basicSQLService.findUniqueLong("select exists(select 1 from bus_parent_pick where student_id="+stuId+" and pick_date='"+nowDate+"' and direction="+direction+")")>0;
    }

    private String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }



}
