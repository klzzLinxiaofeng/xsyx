package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.*;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.vo.ClassSelectionCondition;
import platform.education.generalTeachingAffair.vo.CountPublicClassVo;
import platform.education.generalTeachingAffair.vo.PublicClassCondition;

import java.io.IOException;
import java.util.*;

public class PublicClassServiceImpl implements PublicClassService {

    private Logger log = LoggerFactory.getLogger(getClass());

    //开课管理
    private PublicClassDao publicClassDao;
    private PublicClassUserDao publicClassUserDao;

    private PublicClassTeacherDao publicClassTeacherDao;

    private PublicClassTimeDao publicClassTimeDao;

    private PublicClassTimeRelatedDao publicClassTimeRelatedDao;

    private PublicClassTeacherRelatedDao publicClassTeacherRelatedDao;

    private PublicClassGradeDao publicClassGradeDao;

    public void setPublicClassGradeDao(PublicClassGradeDao publicClassGradeDao) {
        this.publicClassGradeDao = publicClassGradeDao;
    }

    public void setPublicClassUserDao(PublicClassUserDao publicClassUserDao) {
        this.publicClassUserDao = publicClassUserDao;
    }

    public void setPublicClassTimeRelatedDao(PublicClassTimeRelatedDao publicClassTimeRelatedDao) {
        this.publicClassTimeRelatedDao = publicClassTimeRelatedDao;
    }

    public void setPublicClassTeacherRelatedDao(PublicClassTeacherRelatedDao publicClassTeacherRelatedDao) {
        this.publicClassTeacherRelatedDao = publicClassTeacherRelatedDao;
    }

    public void setPublicClassTimeDao(PublicClassTimeDao publicClassTimeDao) {
        this.publicClassTimeDao = publicClassTimeDao;
    }

    public void setPublicClassTeacherDao(PublicClassTeacherDao publicClassTeacherDao) {
        this.publicClassTeacherDao = publicClassTeacherDao;
    }

    public void setPublicClassDao(PublicClassDao publicClassDao) {
        this.publicClassDao = publicClassDao;
    }

    //选课管理
    private ClassSelectionDao classSelectionDao;

    public void setClassSelectionDao(ClassSelectionDao classSelectionDao) {
        this.classSelectionDao = classSelectionDao;
    }

    /*  开课管理开始    */
    @Override
    public PublicClass findPublicClassById(Integer id) {
        try {
            return publicClassDao.findById(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PublicClass add(PublicClass publicClass) {
        if (publicClass == null) {
            return null;
        }
        Date createDate = publicClass.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        publicClass.setCreateDate(createDate);
        publicClass.setModifyDate(createDate);
        publicClassDao.create(publicClass);
        // 添加课程对应的教师 和选课时间标签和年级
        publicMethod(publicClass, createDate);

        return publicClass;
    }

    /**
     * 添加课程对应的教师 和选课时间标签和年级
     *
     * @param publicvo
     * @param createDate
     */
    public void publicMethod(PublicClass publicvo, Date createDate) {
        if (publicvo != null) {
            Integer publicId = publicvo.getId();
            Integer schoolId = publicvo.getSchoolId();
            String teacherId = publicvo.getTeacherId();
            // 添加教师
            if (teacherId != null && !("").equals(teacherId) && publicId != null) {
                String[] split = teacherId.split(",");
                for (int i = 0; i < split.length; i++) {
                    PublicTeacherRelatedVo publicTeacherRelatedVo = new PublicTeacherRelatedVo();
                    publicTeacherRelatedVo.setIsDeleted(0);
                    publicTeacherRelatedVo.setCreatedAt(createDate);
                    publicTeacherRelatedVo.setModifyDate(createDate);
                    publicTeacherRelatedVo.setPublicClassId(publicId);
                    publicTeacherRelatedVo.setTeacherId(Integer.parseInt(split[i]));
                    publicTeacherRelatedVo.setSchoolId(schoolId);
                    publicClassTeacherRelatedDao.create(publicTeacherRelatedVo);
                }
            }
            // 添加时间信息
            String timeId = publicvo.getTimeId();
            if (timeId != null && !("").equals(timeId) && publicId != null) {
                String[] split = timeId.split(",");
                for (int i = 0; i < split.length; i++) {
                    PublicTimeRelatedVo publicTimeRelated = new PublicTimeRelatedVo();
                    publicTimeRelated.setCreatedAt(createDate);
                    publicTimeRelated.setModifyDate(createDate);
                    publicTimeRelated.setIsDeleted(0);
                    publicTimeRelated.setPublicClassId(publicId);
                    publicTimeRelated.setSchoolId(schoolId);
                    publicTimeRelated.setTimeId(Integer.parseInt(split[i]));
                    publicClassTimeRelatedDao.create(publicTimeRelated);
                }
            }
            // 添加年级
            String fullNameArrs = publicvo.getFullNameArr();
            if (fullNameArrs != null && !("").equals(fullNameArrs)) {
                String[] fullNameArr = fullNameArrs.split(",");
                for (int i = 0; i < fullNameArr.length; i++) {
                    String fullName = fullNameArr[i];
                    PublicGradeRelatedVo publicGradeRelatedVo = new PublicGradeRelatedVo();
                    publicGradeRelatedVo.setCreatedAt(createDate);
                    publicGradeRelatedVo.setModifyDate(createDate);
                    publicGradeRelatedVo.setIsDeleted(0);
                    publicGradeRelatedVo.setPublicClassId(publicId);
                    publicGradeRelatedVo.setGrade(stringGradeToInt(fullName));
                    publicGradeRelatedVo.setSchoolId(schoolId);
                    publicClassGradeDao.create(publicGradeRelatedVo);
                }
            }
        }
    }

    @Override
    public PublicClass modify(PublicClass publicClass) {
        if (publicClass == null) {
            return null;
        }
        Date modify = publicClass.getModifyDate();
        Date date = modify != null ? modify : new Date();
        publicClass.setModifyDate(date);
        publicClassDao.update(publicClass);
        // 删除对应的选课时间和教师中间表和年级
        Integer id = publicClass.getId();
        if (id != null) {
            removeRelated(id, date);
            // 添加
            publicMethod(publicClass, date);
        }

        return publicClass;
    }

    @Override
    public void remove(PublicClass publicClass) {
        try {
            publicClassDao.delete(publicClass);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", publicClass.getId(), e);
            }
        }
    }

    @Override
    public List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Page page, Order order) {
        return publicClassDao.findPublicClassByCondition(publicClassCondition, page, order);
    }

    @Override
    public List<PublicClass> findPublicClassByConditionLingxing(PublicClassCondition publicClassCondition, Page page, Order order, Integer leixing) {
        return publicClassDao.findPublicClassByConditionLeixing(publicClassCondition, page, order,leixing);
    }

    @Override
    public List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition) {
        return publicClassDao.findPublicClassByCondition(publicClassCondition, null, null);
    }

    @Override
    public List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Page page) {
        return publicClassDao.findPublicClassByCondition(publicClassCondition, page, null);
    }

    @Override
    public List<PublicClass> findPublicClassByCondition(PublicClassCondition publicClassCondition, Order order) {
        return publicClassDao.findPublicClassByCondition(publicClassCondition, null, order);
    }

    @Override

    public List<Grade> findGradeBySchoolId(Integer schoolId) {
        return publicClassDao.findGradeBySchoolId(schoolId);
    }


//	@Override
//	public Long count() {
//		return this.publicClassDao.count(null);
//	}

    @Override
    public Long count(PublicClassCondition publicClassCondition) {
        return this.publicClassDao.count(publicClassCondition);
    }

    public void removeRelated(Integer id, Date modify){
        publicClassTeacherRelatedDao.deleteTeacherRelated(id, modify);
        publicClassTimeRelatedDao.deleteTimeRelated(id, modify);
        PublicGradeRelatedVo publicGradeRelatedVo = new PublicGradeRelatedVo();
        publicGradeRelatedVo.setIsDeleted(1);
        publicGradeRelatedVo.setPublicClassId(id);
        publicGradeRelatedVo.setModifyDate(modify);
        publicClassGradeDao.deleteGradeRelated(id, modify);
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     */
    @Override
    public String abandon(PublicClass publicClass) {
        if (publicClass != null) {
            publicClass.setIsDelete(true);
            try {
                publicClass = this.publicClassDao.update(publicClass);
                if (publicClass != null) {
                    Integer id = publicClass.getId();
                    if (id != null) {
                        Date modify = new Date();
                        removeRelated(id, modify);
                    }

                    //当某一门公开课作废时，关联的把相关的一报名的同学的信息也作废
                    ClassSelectionCondition condition = new ClassSelectionCondition();
                    condition.setPublicClassId(publicClass.getId());
                    condition.setIsDelete(false);
                    List<ClassSelection> classSectionList = this.classSelectionDao.findClassSelectionByCondition(condition, null, null);
                    if (classSectionList.size() > 0) {
                        for (ClassSelection classSelection : classSectionList) {
                            classSelection.setIsDelete(true);
                            classSelection = this.classSelectionDao.update(classSelection);
                        }
                    }

                    return PublicClassService.OPERATE_SUCCESS;
                }
            } catch (Exception e) {
                if (log.isInfoEnabled()) {
                    log.info("废弃 -> {} 失败，异常信息为 {}", publicClass.getId(), e);
                }
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }

    /*  开课管理结束    */

    /* 选课管理开始*/
    @Override
    public ClassSelection findClassSelectionById(Integer id) {
        try {
            return classSelectionDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public ClassSelection add(ClassSelection classSelection) {
        if (classSelection == null) {
            return null;
        }
        Date createDate = classSelection.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        classSelection.setCreateDate(createDate);
        classSelection.setModifyDate(createDate);
        return classSelectionDao.create(classSelection);
    }

    @Override
    public ClassSelection modify(ClassSelection classSelection) {
        if (classSelection == null) {
            return null;
        }
        Date modify = classSelection.getModifyDate();
        classSelection.setModifyDate(modify != null ? modify : new Date());
        return classSelectionDao.update(classSelection);
    }

    @Override
    public void remove(ClassSelection classSelection) {
        try {
            classSelectionDao.delete(classSelection);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", classSelection.getId(), e);
            }
        }
    }

    @Override
    public List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Page page, Order order) {
        return classSelectionDao.findClassSelectionByCondition(classSelectionCondition, page, order);
    }

    @Override
    public List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition) {
        return classSelectionDao.findClassSelectionByCondition(classSelectionCondition, null, null);
    }

    @Override
    public List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Page page) {
        return classSelectionDao.findClassSelectionByCondition(classSelectionCondition, page, null);
    }

    @Override
    public List<ClassSelection> findClassSelectionByCondition(ClassSelectionCondition classSelectionCondition, Order order) {
        return classSelectionDao.findClassSelectionByCondition(classSelectionCondition, null, order);
    }

//	@Override
//	public Long count() {
//		return this.classSelectionDao.count(null);
//	}

    @Override
    public Long count(ClassSelectionCondition classSelectionCondition) {
        return this.classSelectionDao.count(classSelectionCondition);
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     */
    @Override
    public String abandon(ClassSelection classSelection) {
        if (classSelection != null) {
            classSelection.setIsDelete(true);
            try {
                classSelection = this.classSelectionDao.update(classSelection);
                if (classSelection != null) {
                    return PublicClassService.OPERATE_SUCCESS;
                }
            } catch (Exception e) {
                if (log.isInfoEnabled()) {
                    log.info("废弃 -> {} 失败，异常信息为 {}", classSelection.getId(), e);
                }
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }

    @Override
    public ClassSelection findClassSelectionByPartCondition(
            ClassSelectionCondition classSelectionCondition) {
        try {
            return classSelectionDao.findClassSelectionByPartCondition(classSelectionCondition);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在记录");
        }
        return null;
    }

    /**
     * 查询所有教师信息
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @Override
    public List<PublicTeacherVo> findPublicClassTeacherInfoByCondition(PublicTeacherVo condition, Page page, Order order) {
        return publicClassTeacherDao.findPublicClassTeacherInfoByCondition(condition, page, order);
    }
//    @Override
//    public List<PublicTeacherVo> findPublicClassTeacherInfo(PublicTeacherVo condition) {
//        return publicClassTeacherDao.findPublicClassTeacherInfo(condition);
//    }


    /**
     * 添加教师详细信息
     *
     * @param publicTeacher
     * @return
     */
    @Override
    public void addTeacher(PublicTeacherVo publicTeacher) {
        if (publicTeacher == null) {
            return;
        }
        Date createDate = publicTeacher.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        publicTeacher.setCreateDate(createDate);
        publicTeacher.setModifyDate(createDate);
        publicTeacher.setIsDelete(0);
        publicClassTeacherDao.create(publicTeacher);

    }

    @Override
    public void modifyTeacher(PublicTeacherVo publicTeacher) {
        if (publicTeacher == null) {
            return;
        }
        Date modify = publicTeacher.getModifyDate();
        publicTeacher.setModifyDate(modify != null ? modify : new Date());
        publicClassTeacherDao.update(publicTeacher);

    }

    @Override
    public PublicTeacherVo findByTeacherId(Integer id) {
        try {
            return publicClassTeacherDao.findByTeacherId(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public String abandonTeacher(String ids) {
        if (ids != null && !("").equals(ids)) {
            PublicTeacherVo publicTeacher = new PublicTeacherVo();
            publicTeacher.setIsDelete(1);
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                Date modify = publicTeacher.getModifyDate();
                publicTeacher.setModifyDate(modify != null ? modify : new Date());
                publicTeacher.setId(Integer.parseInt(split[i]));
                publicClassTeacherDao.update(publicTeacher);
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }

    @Override
    public List<PublicTimeVo> findPublicClassTimeInfoByCondition(PublicTimeVo condition, Page page, Order order) {
        return publicClassTimeDao.findPublicClassTimeInfoByCondition(condition, page, order);
    }

    @Override
    public Map addTime(PublicTimeVo publicTimeVo) {
        Map map=new HashMap();
        if (publicTimeVo == null) {
            //参数为空
            map.put("code","xxxxx");
            return map;
        }
        Date createDate = publicTimeVo.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        publicTimeVo.setCreateDate(createDate);
        publicTimeVo.setModifyDate(createDate);
        publicTimeVo.setIsDelete(0);

        PublicTimeVo publicTimeVo1=
                publicClassTimeDao.findByTimeAll(publicTimeVo.getWeekDate(),publicTimeVo.getClassTimeStart(),
                                                 publicTimeVo.getClassTimeEnd());
        if(publicTimeVo1!=null){
            map.put("code","yyyyy");
            return map;
        }
        publicClassTimeDao.create(publicTimeVo);
        log.info("11111");
        if(publicTimeVo.getId()!=null){
            log.info("2222");
            map.put("code","success");
        }else{
            map.put("code","asdfg");
        }
        return map;
    }

    @Override
    public void modifyTime(PublicTimeVo publicTimeVo) {
        if (publicTimeVo == null) {
            return;
        }
        Date modify = publicTimeVo.getModifyDate();
        publicTimeVo.setModifyDate(modify != null ? modify : new Date());
        publicClassTimeDao.update(publicTimeVo);
    }

    @Override
    public PublicTimeVo findByTimeId(Integer id) {
        try {
            return publicClassTimeDao.findByTimeId(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;

    }

    @Override
    public String abandonTime(String ids) {
        if (ids != null && !("").equals(ids)) {
            PublicTimeVo publicTimeVor = new PublicTimeVo();
            publicTimeVor.setIsDelete(1);
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                Date modify = publicTimeVor.getModifyDate();
                publicTimeVor.setModifyDate(modify != null ? modify : new Date());
                publicTimeVor.setId(Integer.parseInt(split[i]));
                publicClassTimeDao.update(publicTimeVor);
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }

    @Override
    public List<PublicTimeVo> findTimeInfoByClassId(Integer id, Integer schoolId) {
        return publicClassTimeDao.findTimeInfoByClassId(id, schoolId);
    }

    /**
     * 查询已经报名的学生信息
     *
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public List<Integer> findPublicUserByIdAndSchoolId(Integer id, Integer schoolId) {
        return publicClassDao.findPublicUserByIdAndSchoolId(id, schoolId);
    }

    @Override
    public void createPublicClassStu(PublicClass publicClass) {
        String studentId = publicClass.getStudentId();
        if (studentId != null && !("").equals(studentId)) {
            // 删除已经保存的学生
            // 删除之前还要查询有没有
//            List<PublicUserVo> list = publicClassUserDao.findByClassId(publicClass.getId());
//            if (list != null && list.size() > 0) {
//                PublicUserVo publicUser = new PublicUserVo();
//                publicUser.setPublicClassId(publicClass.getId());
//                publicUser.setSchoolId(publicClass.getSchoolId());
//                publicClassUserDao.delete(publicUser);
//            }
            String[] split = studentId.split(",");
            for (String s : split) {
                // 判断当前学生是否已经选择这个课程
                Integer stuId = Integer.parseInt(s);
                List<PublicUserVo> list = publicClassUserDao.findByClassId(publicClass.getId(), stuId);
                if (list != null && list.size() > 0) {
                    continue;
                }
                PublicUserVo publicUserVo = new PublicUserVo();
                publicUserVo.setCreatedAt(new Date());
                publicUserVo.setPublicClassId(publicClass.getId());
                publicUserVo.setStudentId(stuId);
                publicUserVo.setSchoolId(publicClass.getSchoolId());
                publicUserVo.setIsDelete(0);
                publicClassUserDao.create(publicUserVo);
                // 添加成功之后修改人数
                PublicClass pc = new PublicClass();
                pc.setEnrollNumberFlg(1);
                pc.setId(publicClass.getId());
                publicClassDao.update(pc);
            }
        }
    }

    @Override
    public void removePublicClassStu(PublicClass publicClass) {
        String studentId = publicClass.getStudentId();
        if (studentId != null && !("").equals(studentId)) {
            String[] split = studentId.split(",");
            for (String s : split) {
                PublicUserVo publicUserVo = new PublicUserVo();
                publicUserVo.setCreatedAt(new Date());
                publicUserVo.setPublicClassId(publicClass.getId());
                publicUserVo.setStudentId(Integer.parseInt(s));
                publicUserVo.setSchoolId(publicClass.getSchoolId());
                publicClassUserDao.delete(publicUserVo);
                // 添加成功之后修改人数
                PublicClass pc = new PublicClass();
                pc.setEnrollNumberFlg(2);
                pc.setId(publicClass.getId());
                publicClassDao.update(pc);
            }
        }
    }

    /**
     * 获取当前课程对应的年级
     *
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public List<PublicGradeRelatedVo> findGradeInfoByClassId(Integer id, Integer schoolId) {
        return publicClassGradeDao.findGradeInfoByClassId(id, schoolId);
    }

    @Override
    public List<CountPublicClassVo> findCountClass(CountPublicClassVo condition) {
        return publicClassDao.findCountClass(condition);
    }

    @Override
    public List<CountPublicClassVo> findChoseClassStu(CountPublicClassVo condition) {
        return publicClassDao.findChoseClassStu(condition);
    }

    @Override
    public List<CountPublicClassVo> findNoChoseClassStu(CountPublicClassVo condition) {
        return publicClassDao.findNoChoseClassStu(condition);
    }

    /**
     * 根据课程获取对应的教师信息
     *
     * @param id
     * @param schoolId
     * @return
     */
    @Override
    public List<PublicTeacherVo> findByClassId(Integer id, Integer schoolId) {
        return publicClassTeacherDao.findByClassId(id, schoolId);
    }


    /**
     * @Author：cmb
     * @Params：[key]
     * @Date：14:12 2020/9/23
     * @Description：1.0将年级年级字符串转成数字返回
     */
    private Integer stringGradeToInt(String fullName) {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(PublicClassServiceImpl.class.getClassLoader().getResourceAsStream("config/properties/custom/grade.properties"));
            property = properties.getProperty(fullName);

//			System.err.println("this is properties"+property);
        } catch (IOException e) {
            log.debug("grade_properties获取失败：" + e.getStackTrace());
        }
        return Integer.valueOf(property);
    }

}
