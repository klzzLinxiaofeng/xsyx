package platform.szxyzxx.web.moral.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.WeekStarStuService;
import platform.education.generalTeachingAffair.vo.*;
import platform.service.storage.contants.FileStatusCode;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.EntityFileVo;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.moral.dao.StudentWeekStarDao;
import platform.szxyzxx.web.moral.service.StudentWeekStarService;
import platform.szxyzxx.web.moral.vo.ClassTeacherVo;
import platform.szxyzxx.web.moral.vo.StarWeekStuVo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Service
/**
 *  @author: yhc
 *  @Date: 2021/5/8 18:01
 *  @Description:
 */
public class StudentWeekStarServiceImpl implements StudentWeekStarService {

    @Autowired
    private StudentWeekStarDao studentWeekStarDao;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private WeekStarStuService weekStarStuService;

    @Autowired
    private BasicSQLService basicSQLService;


    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    Logger log = LoggerFactory.getLogger(StudentWeekStarServiceImpl.class);

    List<Object> excelImporter = new ArrayList<>(2);

    public StudentWeekStarServiceImpl() {

        // ?????????????????????excel??????
        ExcelImportParam<StarWeekStuVo> param = new ExcelImportParam<>(StarWeekStuVo.class, 1);
        param.setStartRowIndex(2);
        Importer<StarWeekStuVo> excelImporter1 = new DefaultImporter<>(param);
        excelImporter.add(excelImporter1);

        ExcelImportParam<ClassTeacherVo> param2 = new ExcelImportParam<>(ClassTeacherVo.class, 1);
        param2.setTitleRowIndex(2);
        param2.setStartRowIndex(3);
        Importer<ClassTeacherVo> excelImporter2 = new DefaultImporter<>(param2);
        excelImporter.add(excelImporter2);

    }


    /**
     * ???????????????????????????????????????
     *
     * @param file
     * @param request
     * @param user
     * @return
     */
    @Override
    public synchronized ResposeLyauiVO readExcelFile(MultipartFile file, HttpServletRequest request, UserInfo user) {
        String name = request.getParameter("name");
        Integer schoolTermId = user.getSchoolTermId();
        if (schoolTermId == null || name == null || ("").equals(name)) {
            return new ResposeLyauiVO(208, "????????????");
        }
        // ??????name ???????????????????????????????????????????????????????????????????????????????????????
        List<Integer> weekIds = weekStarStuService.findCount(name, user.getTeacherId(), user.getSchoolTermId());
        Integer weekId = null;
        if (CollUtil.isNotEmpty(weekIds)) {
            // ???????????????????????????id??????????????????????????????????????????????????????pj_week_stu_term
            weekId = weekIds.get(0);
            if (weekIds.size() > 1) {
                return new ResposeLyauiVO(208, "???????????????????????????????????????????????????????????????????????????");
            }
        }

        //  ???????????????????????????????????????????????????????????????????????????????????????
        List<Map<String, Object>> stuMap = teacherService.findStuByTeacherId(user.getSchoolId(), user.getTeacherId(), user.getSchoolYear());
        if (CollUtil.isEmpty(stuMap)) {
            return new ResposeLyauiVO(208, "??????????????????????????????????????????");
        }
        // ????????????excel????????????????????????????????????????????????????????? ??????
        HashMap<String, Map<String, Object>> teamStuMap = new HashMap<>(stuMap.size());
        for (Map<String, Object> map : stuMap) {
            String name1 = (String) map.get("name");
            String number = String.valueOf(map.get("number"));
            teamStuMap.put(name1.trim() + number.trim(), map);
        }

        List<StarWeekStuVo> list = null;
        try {
            Importer<StarWeekStuVo> starWeekStuVoImporter = (Importer<StarWeekStuVo>) excelImporter.get(0);
            list = starWeekStuVoImporter.importBy(file.getInputStream());
            if (CollUtil.isEmpty(list)) {
                return new ResposeLyauiVO(208, "?????????????????????");
            }
        } catch (IOException e) {
            log.error("??????excel?????? {}", e);
            return new ResposeLyauiVO(500, "????????????");
        }

        // ?????????????????????????????????
        boolean flgScore = false;

        Object[] obj = new Object[list.size()];

        // ??????????????????????????????????????? ????????????????????????????????????????????? ???????????? ?????????hashset??? ??????for ?????????????????????????????????????????????
        for (int i = 0; i < list.size(); i++) {
            StarWeekStuVo starWeekStuVo = list.get(i);
            String name1 = starWeekStuVo.getName();
            String number = starWeekStuVo.getNumber();
            if (StrUtil.isEmpty(name1) && StrUtil.isEmpty(number)) {
                return new ResposeLyauiVO(500, "???????????????????????????");
            }
            String str = name1.trim() + number.trim();
            if (!teamStuMap.containsKey(str)) {
                return new ResposeLyauiVO(500, "????????????????????????");
            }
            WeekStuVo weekStuVo = new WeekStuVo();
            weekStuVo.setCreateDate(new Date());
            Map<String, Object> stuInfoMap = teamStuMap.get(str);
            Integer teamId = (Integer) stuInfoMap.get("teamId");
            Integer gradeId = (Integer) stuInfoMap.get("gradeId");
            Integer id = Integer.parseInt(String.valueOf(stuInfoMap.get("id")));
            if (teamId == null || id == null) {
                return new ResposeLyauiVO(500, "?????????????????????");
            }
            weekStuVo.setStudentId(id);
            weekStuVo.setTeamId(teamId);
            weekStuVo.setGradeId(gradeId);
            // ????????????
            try {
                Integer score = sumStudentScore(starWeekStuVo);
                if (score != null && score != 0) {
                    flgScore = true;
                }
                weekStuVo.setScore(score);
            } catch (IllegalAccessException e) {
                log.error("??????excel?????? {}", e);
                return new ResposeLyauiVO(500, "????????????");
            }

            obj[i] = weekStuVo;
        }
        if (!flgScore) {
            return new ResposeLyauiVO(208, "?????????????????????");
        }
        // ??????????????????????????????
        EntityFileVo upload = upload(file);
        if (upload != null) {
            String uuid = upload.getUuid();
            if (StrUtil.isEmpty(uuid)) {
                return new ResposeLyauiVO(500, "??????????????????");
            }
            WeekStuTermVo weekStuTermVo = new WeekStuTermVo();
            weekStuTermVo.setCreateDate(new Date());
            weekStuTermVo.setName(name);
            weekStuTermVo.setTeacherId(user.getTeacherId());
            weekStuTermVo.setYearTermId(schoolTermId);
            weekStuTermVo.setUuid(uuid);
            weekStarStuService.createWeekStarStu(weekStuTermVo, obj, weekId);
        }


        return new ResposeLyauiVO(200, "success");
    }

    @Override
    public List<WeekStarResonseVo> findWeekStarStu(WeekStarParamVo weekStarParamVo, Page page, Integer type) {
        // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
        // ???????????????
        if (type == 1) {
            List<WeekStarResonseVo> weekStarStu = weekStarStuService.findWeekStarStu(weekStarParamVo, page);
            // ??????????????????
            if (CollUtil.isNotEmpty(weekStarStu)) {
                for (WeekStarResonseVo weekStarResonseVo : weekStarStu) {
                    String uuid = weekStarResonseVo.getUuid();
                    FileResult file = fileService.findFileByUUID(uuid);
                    if (file != null) {
                        weekStarResonseVo.setHref(file.getHttpUrl());
                    }
                }
            }
            return weekStarStu;
        } else if (type == 2) {
            // ???????????????
            List<WeekStarResonseVo> monthStarList = weekStarStuService.findMonthStar(weekStarParamVo, page);
            getStarStudent(monthStarList);
            return monthStarList;

        } else if (type == 3) {
            // ????????????
            List<WeekStarResonseVo> termStarList = weekStarStuService.findTermStar(weekStarParamVo, page);
            getStarStudent(termStarList);
            return termStarList;
        }
        return null;


    }

    /**
     * ???????????????????????????--?????????????????????????????????????????????xx??????id
     *
     * @param monthStarList
     */
    public void getStarStudent(List<WeekStarResonseVo> monthStarList) {
        for (WeekStarResonseVo weekStarResonseVo : monthStarList) {
            String ids = weekStarResonseVo.getIds();
            if (StrUtil.isNotEmpty(ids)) {
                String[] id = ids.split(",");
                List<Map<String, Object>> list = weekStarStuService.findMonthStarStu(id, 1);
                if (CollUtil.isNotEmpty(list)) {
                    Map<String, Object> map = list.get(0);
                    if (CollUtil.isNotEmpty(map)) {
                        weekStarResonseVo.setStudentName((String) map.get("name"));
                        weekStarResonseVo.setGradeNumber(Integer.parseInt(String.valueOf(map.get("gradeNumber"))));
                        weekStarResonseVo.setTeamName((String) map.get("teaName"));
                    }
                }
            }
        }
    }

    @Override
    public List<Map<String, Object>> findWeekStarStuById(Integer id, String ids, Integer type) {
        if (type == 1) {
            return weekStarStuService.findWeekStarStuById(id);
        } else if (type == 2) {
            String[] idArray = ids.split(",");
            List<Map<String, Object>> list = weekStarStuService.findMonthStarStu(idArray, 1000);
            return list;
        }
        return null;
    }

    @Override
    public void deleteWeekStarStu(Integer id) {
        studentWeekStarDao.deleteWeekStarStu(id);
    }

    @Override
    public List<Map<String, Object>> findTheTeacherScoreList(Integer type, String year, Integer schoolId, Page page,String xq,String name) {
       return weekStarStuService.findTheTeacherScoreList(type, year, schoolId, page,xq,name);
    }

    @Override
    public synchronized ResposeLyauiVO uploadTeacherExcel(MultipartFile file, HttpServletRequest request, UserInfo user) {
        Integer teacherId = Integer.valueOf(request.getParameter("teacherId"));
        String year = request.getParameter("year");

        String xq = request.getParameter("xq");
        String type = request.getParameter("type");

        if(basicSQLService.findUniqueLong("select exists(select 1 from pj_the_teacher_main where teacher_id="+teacherId+" and school_year='"+year+"' and type="+type+") e")>0){
             return new ResposeLyauiVO(208, "??????????????????");
        }


        List<ClassTeacherVo> list = null;
        try {
            // ??????excel??????
            Importer<ClassTeacherVo> classTeacherVoImporter = (Importer<ClassTeacherVo>) excelImporter.get(1);
            list = classTeacherVoImporter.importBy(file.getInputStream());
            if (CollUtil.isEmpty(list)) {
                return new ResposeLyauiVO(208, "??????????????????");
            }
            BigDecimal sumZP = new BigDecimal(0);
            BigDecimal sumXZP = new BigDecimal(0);
            BigDecimal sumXP = new BigDecimal(0);
            for (ClassTeacherVo classTeacherVo : list) {
                String zp = classTeacherVo.getZp();
                String xzp = classTeacherVo.getXzp();
                String xp = classTeacherVo.getXp();
                if(org.apache.commons.lang3.StringUtils.isEmpty(zp) && org.apache.commons.lang3.StringUtils.isEmpty(xzp) && org.apache.commons.lang3.StringUtils.isEmpty(xp)){
                    continue;
                }
                if (zp != null && !("").equals(zp)) {
                    sumZP = sumZP.add(new BigDecimal(zp));
                }
                if (xzp != null && !("").equals(xzp)) {
                    sumXZP = sumXZP.add(new BigDecimal(xzp));
                }
                if (xp != null && !("").equals(xp)) {
                    sumXP = sumXP.add(new BigDecimal(xp));
                }
            }

            BigDecimal sum=sumZP.add(sumXZP).add(sumXP);
            if (sum.intValue()==0) {
                return new ResposeLyauiVO(208, "?????????????????????");
            }
            // ??????????????????????????????
            EntityFileVo upload = upload(file);
            String path=null;
            if (upload != null) {
                path=upload.getUrl();
            }
            Date createDate = new Date();
            studentWeekStarDao.createClassTeacherScore(year, path, teacherId, sumZP, sumXZP, sumXP, createDate,xq,Integer.parseInt(type),sum.intValue());
        } catch (IOException e) {
            log.error("??????excel?????? {}", e);
            return new ResposeLyauiVO(500, "?????????????????????????????????");
        }


        return new ResposeLyauiVO(200, "success");
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @return
     */
    public static Integer sumStudentScore(StarWeekStuVo starWeekStuVo) throws IllegalAccessException {
        Class<? extends StarWeekStuVo> aClass = starWeekStuVo.getClass();
        Field[] fields = aClass.getDeclaredFields();
        int sumScore = 0;
        for (Field field : fields) {
            String name = field.getName();
            // ??????????????????
            if (name.equals("name") || name.equals("number") || name.equals("serialVersionUID")) {
                continue;
            }
            field.setAccessible(true);
            int score = field.getInt(starWeekStuVo);
            sumScore += score;
        }
        return sumScore;
    }


    /**
     * ??????????????????
     *
     * @param file
     * @return
     * @throws IOException
     */
    public EntityFileVo upload(MultipartFile file) {
        EntityFileVo fileVo = new EntityFileVo();
        try {
            if (file != null) {
                String uploadFileName = file.getOriginalFilename();
                FileResult result = this.fileService.upload(file.getInputStream(), StringUtils.getFilenameExtension(uploadFileName), file.getContentType(), uploadFileName, String.valueOf(SysContants.SYSTEM_APP_ID));
                if (result != null && FileStatusCode.UPLOAD_SUCCESS.equals(result.getStatusCode())) {
                    EntityFile entityFile = result.getEntityFile();
                    copyEntityFileProperties(entityFile, fileVo);
                    fileVo.setUrl(result.getHttpUrl());
                }
            }
        } catch (IOException e) {
            log.error("??????????????????Excel: ", e);
        }
        return fileVo;
    }

    private void copyEntityFileProperties(EntityFile entityFile,
                                          EntityFileVo entityFileVo) {
        entityFileVo.setFileName(entityFile.getDiskFileName());
        entityFileVo.setCreateDate(entityFile.getCreateDate());
        entityFileVo.setId(entityFile.getId());
        entityFileVo.setMd5Code(entityFile.getMd5());
        entityFileVo.setRealFileName(entityFile.getFileName());
        entityFileVo.setSize(entityFile.getSize());
        entityFileVo.setSuffix(entityFile.getExtension());
        entityFileVo.setUuid(entityFile.getUuid());
    }
}
