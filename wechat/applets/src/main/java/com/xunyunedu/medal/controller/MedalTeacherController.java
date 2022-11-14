package com.xunyunedu.medal.controller;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.login.dao.PjTeacherDao;
import com.xunyunedu.login.dao.UserLoginDao;
import com.xunyunedu.login.pojo.PjTeacher;
import com.xunyunedu.login.pojo.UserPojo;
import com.xunyunedu.medal.dao.PjSchoolYearDao;
import com.xunyunedu.medal.dao.PjStudentDao;
import com.xunyunedu.medal.model.Medal;
import com.xunyunedu.medal.model.MvAwardsMedal;
import com.xunyunedu.medal.model.PjSchoolYear;
import com.xunyunedu.medal.model.PjStudent;
import com.xunyunedu.medal.service.MedalService;
import com.xunyunedu.medal.vo.MedalVo;
import com.xunyunedu.personinfor.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 奖章控制层
 *
 * @description: 奖章控制层
 * @author: cmb
 * @create: 2020-10-13 16:59
 **/
@RestController
@RequestMapping("/medal")
public class MedalTeacherController {
    @Autowired
    private MedalService medalService;
    @Autowired(required = false)
    private PjTeacherDao pjTeacherDao;
    @Autowired(required = false)
    private PjSchoolYearDao pjSchoolYearDao;
    @Autowired(required = false)
    private PjStudentDao pjStudentDao;


    /**
     * 登录用户名
     */
    private String teacherName = null;
    /**
     * 学校id
     */
    private Integer schoolId = 0;

    /**
     * @Description: 创建奖章
     * @Param: * @param medal
     * @return: com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/15
     */
    @PostMapping("/create")

    public ApiResult crateMedal(@RequestBody @Valid Medal medal,@RequestParam("status") Integer status) {
        if (status != 1) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }

        medalService.crateMedal(medal);
        System.out.println(medal);
        return new ApiResult(ResultCode.CREATE_SUCCESS);
    }

    /**
     * @Description: 删除奖章
     * @Param: * @param id
     * @return: com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/15
     */
    @GetMapping("/delete")
    public ApiResult deleteMedal(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        if (status != 1) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        if (id != null) {
            this.medalService.deleteMedal(id);
            return new ApiResult(ResultCode.DELETE_SUCCESS);
        } else {
            throw new BusinessRuntimeException(ResultCode.DELETE_FAIL);
        }

    }

    /**
     * @Description: 查询奖章
     * @Param: * @param schooldId :
     * @return: : com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/14
     */
    @GetMapping("/findMedalAll")

    public ApiResult findMedalAll(@RequestParam("userName") String userName) {
        Integer status = check(userName);
        if (status != 1) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        List<Medal> medalAll = medalService.findMedalAll();
        Medal[] medals = medalAll.toArray(new Medal[medalAll.size()]);
        System.out.println(Arrays.toString(medals));
        return new ApiResult(ResultCode.FIND_SUCCESS,1, medalAll);
    }


      @GetMapping("/updataId")
      public ApiResult updateMedalById(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
          if (status != 1) {
              throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
          }
        if (id!=null){
            Medal data = medalService.findMeDalById(id);
            return new ApiResult(ResultCode.FIND_SUCCESS, data);
        }
      return null;

      }


    /**
     * @Description: 更新奖章
     * @Param: * @param medal
     * @return: com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/14
     */
    @PostMapping("/update")
    public ApiResult updateMedal(@RequestBody @Valid Medal medal,@RequestParam("status") Integer status) {
        if (status != 1) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        if (medal != null) {
            medalService.updateMedal(medal);
            return new ApiResult(ResultCode.UPDATA_SUCCESS);
        } else {
            throw new BusinessRuntimeException(ResultCode.UPDATA_FAIL);
        }
    }

    /**
     * @Description: 颁发奖章
     * @Param: * @param
     * @return: com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/14
     */
    @PostMapping("/crateMedalStudent")
    public ApiResult crateMedalStudent(@RequestBody @Valid MedalVo medalVo,@RequestParam("status") Integer status) {
        if (status != 1) {
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        List<MvAwardsMedal> awardsMedals = new ArrayList<>();

        //1.0获取学生列表
        List<StudentPojo> studentPojos = medalVo.getStudentPojos();
        PjSchoolYear year = pjSchoolYearDao.findSchoolYearBySchoolId(schoolId);
        String ss = year.getYear();
        String schoolIdTerm = medalService.getYearTerm(schoolId, ss);
        //1.1拆分学生列表
        //1.1.1遍历学生列表
        for (StudentPojo studentPojo : studentPojos) {
            MvAwardsMedal awardsMedal = new MvAwardsMedal();
            //1.1.2将数据存入一个颁发奖章类中
            awardsMedal.setStudentNumber(studentPojo.getNumber());
            awardsMedal.setAwardsStudent(studentPojo.getName());
            awardsMedal.setClasses(studentPojo.getTeamName());
            awardsMedal.setSchoolId(this.schoolId);
            awardsMedal.setYear(year.getYear());
            awardsMedal.setSchoolTerm(schoolIdTerm);
            awardsMedal.setStudentId(studentPojo.getId());
            awardsMedal.setMedalId(medalVo.getId());
            awardsMedal.setTeacherName(teacherName);
            awardsMedal.setGrade(medalVo.getName() + " " + medalVo.getGrade());

//            1.1.3添加到颁奖列表
            awardsMedals.add(awardsMedal);
        }
        medalService.crateMedalStudent(awardsMedals);

        return new ApiResult(ResultCode.CREATE_SUCCESS,1);
    }

    /**
     * @Description: 获取学生列表
     * @Param: * @param
     * @return: com.xunyunedu.exception.ApiResult
     * @Author: cmb
     * @Date: 2020/10/15
     */
    @GetMapping("/studentList")
    public ApiResult StudentList(@RequestParam("status") Integer status) {
        if (status != 1){
            throw new BusinessRuntimeException(ResultCode.NOT_FOUND);
        }
        List<PjStudent> studentAll = pjStudentDao.findStudentAll();
        return new ApiResult(ResultCode.FIND_SUCCESS, studentAll);


    }

    /**
     * @Description: 检查是否是教师端
     * @Param: * @param name
     * @return: void
     * @Author: cmb
     * @Date: 2020/10/15
     */
    public Integer check(String name) {

        if (name != null && !name.equals("")) {
            PjTeacher pjTeacher = pjTeacherDao.findPjTeacherById(name);
            this.teacherName=pjTeacher.getName();
            if (pjTeacher == null) {
              return 0;
            } else {
                this.schoolId = pjTeacher.getSchoolId();
               return  1;
            }

        }
        throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
    }


}
