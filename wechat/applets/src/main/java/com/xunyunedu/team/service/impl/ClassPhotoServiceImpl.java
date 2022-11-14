package com.xunyunedu.team.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.school.dao.PjSchoolDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.team.dao.ClassPhotoDao;
import com.xunyunedu.team.dao.TeamsDao;
import com.xunyunedu.team.pojo.ClassPhotoPojo;
import com.xunyunedu.team.pojo.ClassYearbookPojo;
import com.xunyunedu.team.service.ClassPhotoService;
import com.xunyunedu.team.vo.ClassYearbookVo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级相册
 *
 * @author: yhc
 * @Date: 2020/12/16 14:11
 * @Description:
 */
@Service
public class ClassPhotoServiceImpl implements ClassPhotoService {

    @Autowired
    private ClassPhotoDao classPhotoDao;

    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;

    @Autowired
    private PjSchoolDao pjSchoolDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeamsDao teamsDao;
    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 新增相册薄
     *
     * @param classYearbookVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(ClassYearbookVo classYearbookVo) {

        ClassYearbookPojo classYearBookPojo = new ClassYearbookPojo();
        BeanUtils.copyProperties(classYearbookVo, classYearBookPojo);

        //学期表pj_school_term_current
        // 获取当前的学期信息
        // 添加相册簿termCode
        System.out.println("学期"+classYearbookVo.getTermCode());
        List<Map<String,Object>> mapList=basicSQLService.find("select * from pj_school_term_current where school_term_code='"+classYearbookVo.getTermCode()+"'");
        classYearBookPojo.setTermId(Integer.parseInt(mapList.get(0).get("id").toString()));
        classYearBookPojo.setTeamId(classYearbookVo.getTeamId());
        classYearBookPojo.setCreateDate(new Date());
        classPhotoDao.createYearBook(classYearBookPojo);
        List<String> uuids = classYearBookPojo.getUuids();
        Integer id = classYearBookPojo.getId();
        if (id != null && CollUtil.isNotEmpty(uuids)) {
            // 添加相册照片
            classPhotoDao.createClassPhoto(uuids, id);
        }


//        List<ClassPhotoPojo> classPhotoPojos = classYearBookPojo.getClassPhotoPojos();
//        Integer id = classYearBookPojo.getId();
//        if (id != null && CollUtil.isNotEmpty(classPhotoPojos)) {
//            // 添加相册照片
//            classPhotoDao.createClassPhoto(classPhotoPojos, id);
//        }

        /**
         * 归档时再做学生绑定
         */
        // 获取当前班主任班级下的所有学生
//        List<Integer> listStuId = classPhotoDao.findTeamStu(termTeamVo.getTeamId());
//        if (CollUtil.isNotEmpty(listStuId)) {
//            classPhotoDao.createStuPhotos(listStuId, id, termTeamVo.getTermId());
//        }

    }

    /**
     * 修改相册
     * 修改照片
     *
     * @param classYearbookVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ClassYearbookVo classYearbookVo) {
        ClassYearbookPojo classYearBookPojo = new ClassYearbookPojo();
        BeanUtils.copyProperties(classYearbookVo, classYearBookPojo);
        // 判断当前是否已经归档
        classYearBookPojo.setIsArchiving(0);
        List<ClassYearbookPojo> list = classPhotoDao.findYearBook(classYearBookPojo);
        if (CollUtil.isEmpty(list)) {
            throw new BusinessRuntimeException(ResultCode.Is_ARCHIVING);
        }
        classYearBookPojo.setModifyDate(new Date());
        // 修改相册薄和相册
        classPhotoDao.updateYearBook(classYearBookPojo);
        classPhotoDao.updateClassPhoto(classYearBookPojo.getId());

        List<String> uuids = classYearBookPojo.getUuids();
        Integer id = classYearBookPojo.getId();
        if (id != null && CollUtil.isNotEmpty(uuids)) {
            // 添加相册照片
            classPhotoDao.createClassPhoto(uuids, id);
        }
    }

    /**
     * 获取发布历史
     *
     * @param classYearbookPojo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ClassYearbookPojo> findClassYearBook(ClassYearbookPojo classYearbookPojo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassYearbookPojo> list = classPhotoDao.findYearBook(classYearbookPojo);
        if (CollUtil.isNotEmpty(list)) {
            getUrl(list);
            PageInfo<ClassYearbookPojo> objectPageInfo = new PageInfo<>(list);
            return objectPageInfo;
        }
        return null;
    }

    /**
     * 获取详细信息
     *
     * @param classYearbookPojo
     * @return
     */
    @Override
    public ClassYearbookPojo findClassYearBookById(ClassYearbookPojo classYearbookPojo) {
        List<ClassYearbookPojo> list = classPhotoDao.findYearBook(classYearbookPojo);
        if (CollUtil.isNotEmpty(list)) {
            ClassYearbookPojo pojo = list.get(0);
            String uuid = pojo.getUuid();
            if (StrUtil.isNotEmpty(uuid)) {
                EntityFile file = uploaderDao.findFileByUUID(uuid);
                if (file != null) {
                    pojo.setHttpUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            return pojo;
        }
        return null;
    }


    /**
     * 获取相册薄下的所有照片
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ClassPhotoPojo> getClassPhotoList(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ClassPhotoPojo> classPhotoPojos = classPhotoDao.getClassPhoto(id);
        if (CollUtil.isNotEmpty(classPhotoPojos)) {
            for (int i = 0; i < classPhotoPojos.size(); i++) {
                String uuid1 = classPhotoPojos.get(i).getUuid();
                EntityFile file = uploaderDao.findFileByUUID(uuid1);
                if (file != null) {
                    classPhotoPojos.get(i).setHttpUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
            return new PageInfo<>(classPhotoPojos);
        }
        return null;
    }


    /**
     * 删除相册薄和相册
     *
     * @param classYearbookVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeClassYearBook(ClassYearbookVo classYearbookVo) {
        ClassYearbookPojo classYearBookPojo = new ClassYearbookPojo();
        BeanUtils.copyProperties(classYearbookVo, classYearBookPojo);
        classYearBookPojo.setModifyDate(new Date());
        classYearBookPojo.setIsDelete(1);
        classPhotoDao.updateYearBook(classYearBookPojo);
        classPhotoDao.updateClassPhoto(classYearBookPojo.getId());
    }


    /**
     * 指定学期
     * 获取学生端相册薄
     *
     * @param stuId
     * @param schoolId
     * @return
     */
    /*@Override
    public Map<String, List<ClassYearbookPojo>> findStuClassYearBook(Integer stuId, Integer schoolId) {

        // 获取当前学生的信息
        StudentPojo pojo = new StudentPojo();
        pojo.setId(stuId);
        pojo.setSchoolId(schoolId);
        StudentPojo studentPojo = studentDao.findStuTeamById(pojo);
        Integer teamId = studentPojo.getTeamId();
        if (studentPojo == null || teamId == null) {
            throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
        }
        Map<String, List<ClassYearbookPojo>> map = new HashMap<>();

        // 获取现在的学期信息
        TermTeamVo teamVo = new TermTeamVo();
        teamVo.setSchoolId(schoolId);
        SchoolTermPojo schoolTermPojo = pjSchoolDao.findTermBycontidion(teamVo);
        // 查询所有归档和未归档的相册薄
        ClassYearbookPojo classYearbookPojo = new ClassYearbookPojo();
        classYearbookPojo.setSchoolId(schoolId);
        classYearbookPojo.setTeamId(teamId);
        List<ClassYearbookPojo> list = classPhotoDao.findYearBook(classYearbookPojo);

        if (CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                // 设置封面
                String uuid = list.get(i).getUuid();
                if (StrUtil.isNotEmpty(uuid)) {
                    EntityFile file = uploaderDao.findFileByUUID(uuid);
                    if (file != null) {
                        list.get(i).setHttpUrl(ftpUtils.relativePath2HttpUrl(file));
                    }
                }
                // 组合相册薄
                Integer termId = schoolTermPojo.getId();
                Integer tid = list.get(i).getTermId();
                if (teamId != null && tid != null && tid.equals(termId)) {
                    String key = studentPojo.getTeamName() + schoolTermPojo.getName();
                    // 同一个学期
                    compose(map, key, list.get(i));
                } else {
                    // 获取归档学生相册表
                    ClassStudentPhotoPojo classStudentPhotoPojo = new ClassStudentPhotoPojo();
                    classStudentPhotoPojo.setClassYearbookId(list.get(i).getId());
                    classStudentPhotoPojo.setStudentId(stuId);
                    ClassStudentPhotoPojo photoArchive = classPhotoDao.findStuPhotoArchive(classStudentPhotoPojo);
                    if (photoArchive != null) {
                        // 获取学期信息
                        SchoolTermPojo stp = new SchoolTermPojo();
                        stp.setId(photoArchive.getTermId());
                        List<SchoolTermPojo> termPojoList = pjSchoolDao.findTerm(stp);
                        // 获取学生以往的班级
                        Integer oldTeam = photoArchive.getTeamId();
                        TeamPojo teamPojo = new TeamPojo();
                        teamPojo.setId(oldTeam);
                        List<TeamPojo> teamList = teamsDao.getTeamList(teamPojo);
                        if (CollUtil.isNotEmpty(teamList) && CollUtil.isNotEmpty(termPojoList)) {
                            String key = teamList.get(0).getName();
                            compose(map, key + termPojoList.get(0).getName(), list.get(i));
                        }
                    }
                }
            }
        }
        return map;
    }*/


    /**
     * 不指定学期
     * 获取学生端相册薄
     *
     * @param stuId
     * @param schoolId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ClassYearbookPojo> findStuClassYearBook(Integer stuId, Integer schoolId, Integer pageNum, Integer pageSize) {
        // 获取当前学生的信息
        StudentPojo pojo = new StudentPojo();
        pojo.setId(stuId);
        pojo.setSchoolId(schoolId);
        StudentPojo studentPojo = studentDao.findStuTeamById(pojo);
        Integer teamId = studentPojo.getTeamId();
        if (studentPojo == null || teamId == null) {
            throw new BusinessRuntimeException(ResultCode.USER_NOT_FONUD);
        }
        // 查询所有归档和未归档的相册薄
        ClassYearbookPojo classYearbookPojo = new ClassYearbookPojo();
        classYearbookPojo.setSchoolId(schoolId);
        classYearbookPojo.setTeamId(teamId);
        PageHelper.startPage(pageNum, pageSize);
        List<ClassYearbookPojo> list = classPhotoDao.findYearBook(classYearbookPojo);
        if (CollUtil.isNotEmpty(list)) {
            getUrl(list);
            return new PageInfo<>(list);
        }
        return null;
    }

    public void getUrl(List<ClassYearbookPojo> list) {
        for (int i = 0; i < list.size(); i++) {
            String uuid = list.get(i).getUuid();
            if (StrUtil.isNotEmpty(uuid)) {
                EntityFile file = uploaderDao.findFileByUUID(uuid);
                if (file != null) {
                    list.get(i).setHttpUrl(ftpUtils.relativePath2HttpUrl(file));
                }
            }
        }
    }
}
