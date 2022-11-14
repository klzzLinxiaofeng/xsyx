package platform.szxyzxx.web.bbx.util;

import platform.education.clazz.model.TeamAccount;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.bbx.client.vo.GradeClientVo;

import java.util.ArrayList;
import java.util.List;

public class DistinctUtil {

    /**
     * 根据gradeId去重
     *
     * @param tList
     * @return
     */

    public static List<GradeClientVo> distinctGrade(List<GradeClientVo> tList) {
        List<GradeClientVo> list = new ArrayList<GradeClientVo>();
        for (int i = 0; i < tList.size(); i++) {
            boolean flag = true;
            for (GradeClientVo t2 : list) {
                if (tList.get(i).getGradeId().equals(t2.getGradeId())) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(tList.get(i));
            }
        }
        return list;
    }

    /**
     * 根据teamId去重
     *
     * @param tList
     * @return
     */

    public static List<TeamTeacherVo> distinctTeacher(List<TeamTeacherVo> tList) {
        List<TeamTeacherVo> list = new ArrayList<TeamTeacherVo>();
        for (int i = 0; i < tList.size(); i++) {
            boolean flag = true;
            for (int i1 = 0; i1 < list.size(); i1++) {
                if (tList.get(i).getTeamId().equals(list.get(i1).getTeamId())) {
                    if (tList.get(i).getType() == 1) {
                        list.remove(i1);
                        list.add(i1, tList.get(i));
                    }
                    flag = false;
                }
            }
            if (flag) {
                list.add(tList.get(i));
            }
        }
        return list;
    }

    /**
     * 根据gradeId去重
     *
     * @param
     * @return
     */

    public static List<TeamAccount> distinctTeamAccountGrade(
            List<TeamAccount> gList) {
        List<TeamAccount> list = new ArrayList<TeamAccount>();
        for (int i = 0; i < gList.size(); i++) {
            boolean flag = true;
            for (TeamAccount t2 : list) {
                if (gList.get(i).getGradeId().equals(t2.getGradeId())) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(gList.get(i));
            }
        }
        return list;
    }

    /**
     * @return List<Teacher>
     * @function 根据teacherId 去掉List<Teacher> 中的重复值
     * <Teacher>
     */
    public static List<Teacher> distinctTeacherOfTeacherId(List<Teacher> tList) {
        List<Teacher> list = new ArrayList<Teacher>();
        if (tList.size() > 0) {
            for (int i = 0; i < tList.size(); i++) {
                boolean flag = true;
                if (list.size() > 0) {
                    for (Teacher t2 : list) {
                        if (tList.get(i).getId().equals(t2.getId())) {
                            flag = false;
                        }

                    }
                }
                if (flag) {
                    list.add(tList.get(i));
                }
            }
        }
        return list;
    }

}
