package com.xunyunedu.team.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.team.pojo.ClassPhotoPojo;
import com.xunyunedu.team.pojo.ClassYearbookPojo;
import com.xunyunedu.team.service.ClassPhotoService;
import com.xunyunedu.team.vo.ClassYearbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 班级相册
 * <p>
 * 教师发布相册，到对应班级的每个学生，学生端可以查看相册，每个学期对相册进行一个归档
 *
 * @author: yhc
 * @Date: 2020/12/15 19:45
 * @Description:
 */
@RestController
@RequestMapping("/classPhoto")
public class ClassPhotoController {

    @Autowired
    private ClassPhotoService classPhotoService;


    /**
     * 教师发布相册薄
     *
     * @param classYearbookVo
     * @return
     */
    @PostMapping("/releasePhoto")
    @Authorization
    public ApiResult releasePhoto(@RequestBody ClassYearbookVo classYearbookVo) {
        if (classYearbookVo == null || classYearbookVo.getSchoolId() == null || classYearbookVo.getTeacherId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        classPhotoService.create(classYearbookVo);
        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 教师修改相册薄
     *
     * @param classYearbookVo
     * @return
     */
    @PostMapping("/updatePhoto")
    @Authorization
    public ApiResult updatePhoto(@RequestBody ClassYearbookVo classYearbookVo) {
        if (classYearbookVo == null || classYearbookVo.getSchoolId() == null ||
                classYearbookVo.getId() == null || classYearbookVo.getTeacherId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        classPhotoService.update(classYearbookVo);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 教师获取发布历史
     *
     * @param classYearbookPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getClassPhotos")
    @Authorization
    public ApiResult getClassPhotos(ClassYearbookPojo classYearbookPojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (classYearbookPojo == null || classYearbookPojo.getSchoolId() == null || classYearbookPojo.getTeacherId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<ClassYearbookPojo> classYearBookPojo = classPhotoService.findClassYearBook(classYearbookPojo, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, classYearBookPojo);
    }


    /**
     * 获取相册薄详情
     *
     * @param classYearbookPojo
     * @return
     */
    @GetMapping("/getClassPhotoInfo")
    @Authorization
    public ApiResult getClassPhotoInfo(ClassYearbookPojo classYearbookPojo) {
        if (classYearbookPojo == null || classYearbookPojo.getId() == null || classYearbookPojo.getTeacherId() == null
                || classYearbookPojo.getSchoolId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        ClassYearbookPojo classYearBookPojo = classPhotoService.findClassYearBookById(classYearbookPojo);
        return new ApiResult(ResultCode.SUCCESS, classYearBookPojo);
    }

    /**
     * 教师删除相册薄
     *
     * @param classYearBookVo
     * @return
     */
    @PostMapping("/deletePhoto")
    @Authorization
    public ApiResult deletePhoto(@RequestBody ClassYearbookVo classYearBookVo) {
        if (classYearBookVo == null || classYearBookVo.getSchoolId() == null || classYearBookVo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        classPhotoService.removeClassYearBook(classYearBookVo);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 教师端和学生端通用-获取相册薄详情下的所有照片
     *
     * <p>
     * 教师端和学生端通用
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getClassPhotoList")
    @Authorization
    public ApiResult getClassPhotoList(@RequestParam Integer id, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<ClassPhotoPojo> classYearBookPojo = classPhotoService.getClassPhotoList(id, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, classYearBookPojo);
    }

    /**
     * 学生端获取相册列表
     *
     * @param stuId
     * @param schoolId
     * @return
     */
    @GetMapping("/stu/getClassPhotoList")
    @Authorization
    public ApiResult getStuClassPhotoList(@RequestParam Integer stuId, @RequestParam Integer schoolId, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (stuId == null || schoolId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 获取归档的和没有归档的组合起来
        // 没有归档的需要指定班级
        // 归档的相册，去学生相册归档表查看，年级升级功能之后，原来的年级和班级学生还会保存，归档的相册功能，需要查询班级表，保存对应的shcool_year学年 pj_school_term_current id
        PageInfo<ClassYearbookPojo> list = classPhotoService.findStuClassYearBook(stuId, schoolId, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


}
