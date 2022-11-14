package com.xunyunedu.student.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.student.dao.JcGcItemDao;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.dao.StudentHealthArchiveDao;
import com.xunyunedu.student.dao.StudentHealthArchiveTypeDao;
import com.xunyunedu.student.pojo.JcGcItemPojo;
import com.xunyunedu.student.pojo.StudentHealthArchivePojo;
import com.xunyunedu.student.pojo.StudentHealthArchiveTypePojo;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentHealthArchiveService;
import com.xunyunedu.util.ftp.FtpUtils;
import com.xunyunedu.workAttendance.dao.PjTeamTeacherDao;
import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.xunyunedu.common.pojo.EntityFile;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Eternityhua
 * @create 2020-12-09 15:59
 */
@Service
public class StudentHealthArchiveServiceImpl implements StudentHealthArchiveService {

    @Resource
    private StudentHealthArchiveDao dao;

    @Resource
    private StudentHealthArchiveTypeDao ArchiveTypeDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private UploaderDao uploaderDao;

    @Resource
    private JcGcItemDao gcItemDao;

    @Resource
    private FtpUtils ftpUtils;

    @Resource
    private PjTeamTeacherDao pjTeamTeacherDao;



    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public StudentHealthArchivePojo findStudentHealthArchiveById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
            return null;
        }


    }

    @Override
    public List<StudentHealthArchivePojo> findAllStudentHealthArchive() {
        return dao.findAllStudentHealthArchive();
        //return null;
    }

    @Override
    public void createHealth(StudentHealthArchivePojo studentHealthArchivePojo) {
//        studentHealthArchiveDao.createHealth(studentHealthArchivePojo);
    }



    @Override
    public void save(StudentHealthArchivePojo pojo) {
        if (pojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        //先判断是不是班主任，是班主任才能添加
        Integer teacherId = pojo.getTeacherId();

        List<PjTeamTeacher> teamTeacherList = pjTeamTeacherDao.findTeacherByTeacherId(teacherId);


        //假如是1 则是班主任，否则不是班主任不能添加
        if (teamTeacherList!=null) {


            Integer id =  pojo.getId();

            String uuids = null;
            List<String> uuid = pojo.getUuids();
            if (CollUtil.isNotEmpty(uuid)) {
                uuids = StringUtils.join(uuid.toArray(), ",");
            }

            StudentPojo student = studentDao.getStudentById(pojo.getStudentId());

            pojo.setCreateDate(new Date());

            pojo.setDelete(false);

            pojo.setModifyDate(new Date());

            pojo.setSchoolId(student.getSchoolId());

            pojo.setTeamId(student.getTeamId());

            pojo.setType("null");

            pojo.setUuid(uuids);

            dao.createHealth(pojo);


            List<JcGcItemPojo> types = pojo.getHealthTypes();


            if (types!=null&&types.size()>0) {
                for (JcGcItemPojo type : types) {
                    StudentHealthArchiveTypePojo typePojo = new StudentHealthArchiveTypePojo();
                    typePojo.setStudentHealthId(pojo.getId());
                    typePojo.setHealthType(type.getId());
                    ArchiveTypeDao.insert(typePojo);
                }
            }

        } else {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_INVALID);
        }


    }





    @Override
    public void delete(Integer id) {
        ArchiveTypeDao.deleteByHealthId(id);
        dao.deleteStu(id);
    }

    @Override
    @Transactional
    public void update(StudentHealthArchivePojo pojo) {
        List<JcGcItemPojo> types = pojo.getHealthTypes();
        ArchiveTypeDao.deleteByHealthId(pojo.getId());
        if (types!=null&&types.size()>0) {
            //中间表的先删除
            for (JcGcItemPojo type : types) {
                StudentHealthArchiveTypePojo typePojo = new StudentHealthArchiveTypePojo();
                typePojo.setStudentHealthId(pojo.getId());
                typePojo.setHealthType(type.getId());
                ArchiveTypeDao.insert(typePojo);
            }
        }
        List<String> uuids = pojo.getUuids();
        if (CollUtil.isNotEmpty(uuids)) {
            pojo.setUuid(StringUtils.join(uuids.toArray(), ","));
        }
        dao.updateById(pojo);

    }

    @Override
    public StudentHealthArchivePojo queryById(Integer id) {
        StudentHealthArchivePojo pojo = dao.selectById(id);
        List<Integer> typeIds = ArchiveTypeDao.selectByHealthId(id);
        if (pojo!=null) {
            if (typeIds != null && typeIds.size() > 0) {
                List<JcGcItemPojo> types = gcItemDao.selectByIds(typeIds);
                pojo.setHealthTypes(types);
            }
            String uuid = pojo.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    pojo.setUuids(lists);
                }
            }
            return pojo;
        }
        return null;

    }

    @Override
    public List<StudentHealthArchivePojo> queryAll() {
        List<StudentHealthArchivePojo> list = dao.getStudentCondition();
        for (StudentHealthArchivePojo entity : list) {
            String uuid = entity.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    entity.setUuids(lists);
                }
            }

        }
        return list;
    }

    @Override
    public void del(Integer id) {
        //ArchiveTypeDao.delHealthTypeById(id);
        dao.updateStatusById(id);
    }

    @Override
    public List<StudentHealthArchivePojo> queryByStuId(Integer stuId) {

        List<StudentHealthArchivePojo> list = dao.getStuByStuId(stuId);
        for (StudentHealthArchivePojo entity : list) {
            String uuid = entity.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    entity.setUuids(lists);
                }
            }

        }
        return list;

    }

    @Override
    public List<StudentHealthArchivePojo> queryByTeamId(Integer teamId) {
        List<StudentHealthArchivePojo> list = dao.queryByTeamId(teamId);
        for (StudentHealthArchivePojo entity : list) {
            String uuid = entity.getUuid();
            // 获取文件的UUID,根据uuid查询文件的详细地址
            if (!StrUtil.hasEmpty(uuid)) {
                String[] split = uuid.split(",");
                if (split != null && split.length > 0) {
                    ArrayList<String> lists = new ArrayList<>();

                    for (int i1 = 0; i1 < split.length; i1++) {
                        EntityFile entityFile = uploaderDao.findFileByUUID(split[i1]);
                        if (entityFile != null) {
                            lists.add(ftpUtils.relativePath2HttpUrl(entityFile));
                        }
                    }
                    entity.setUuids(lists);
                }
            }

        }
        return list;
    }
}
