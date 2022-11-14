package com.xunyunedu.team.dao;

import com.xunyunedu.team.pojo.ClassPhotoPojo;
import com.xunyunedu.team.pojo.ClassStudentPhotoPojo;
import com.xunyunedu.team.pojo.ClassYearbookPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级相册
 *
 * @author: yhc
 * @Date: 2020/12/16 14:13
 * @Description:
 */
public interface ClassPhotoDao {


    void createYearBook(ClassYearbookPojo classYearBookPojo);

    void createClassPhoto(@Param("uuids") List<String> uuids, @Param("id") Integer id);

    List<Integer> findTeamStu(@Param("teamId") Integer teamId, @Param("schoolId") Integer schoolId);

    void createStuPhotos(@Param("listStuId") List<Integer> listStuId, @Param("id") Integer id, @Param("termId") Integer termId);

    void updateYearBook(ClassYearbookPojo classYearBookPojo);

    void updateClassPhoto(@Param("id") Integer id);

    List<ClassYearbookPojo> findYearBook(ClassYearbookPojo classYearBookPojo);

    List<ClassPhotoPojo> getClassPhoto(@Param("id") Integer id);

    ClassStudentPhotoPojo findStuPhotoArchive(ClassStudentPhotoPojo classStudentPhotoPojo);
}
