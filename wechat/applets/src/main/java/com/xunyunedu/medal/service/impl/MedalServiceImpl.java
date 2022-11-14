package com.xunyunedu.medal.service.impl;

import com.xunyunedu.medal.dao.MedalDao;
import com.xunyunedu.medal.dao.PjSchoolTermDao;
import com.xunyunedu.medal.model.Medal;
import com.xunyunedu.medal.model.MvAwardsMedal;
import com.xunyunedu.medal.model.PjSchoolTerm;
import com.xunyunedu.medal.service.MedalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 奖章业务
 * @author: cmb
 * @create: 2020-10-13 16:53
 **/
@Service("MedalService")
public class MedalServiceImpl implements MedalService {
    @Autowired
    private MedalDao medalDao;
    @Autowired
    private PjSchoolTermDao pjSchoolTermDao;
    @Override
    /**
    * @Description: 创建奖章
    * @Param: [medal]
    * @return: void
    * @Author: cmb
    * @Date: 2020/10/13
    */ 
    public void crateMedal(Medal medal) {
        this.medalDao.crateMedal(medal);
    }
    /**
    * @Description: 删除奖章
    * @Param: * @param id
    * @return: void
    * @Author: cmb
    * @Date: 2020/10/14
    */
    @Override
    public void deleteMedal(Integer id) {
     this.medalDao.deleteMedal(id);
    }
/**
* @Description: 更新奖章信息
* @Param: * @param medal
* @return: void
* @Author: cmb
* @Date: 2020/10/14
*/
    @Override
    public void updateMedal(Medal medal) {
        this.medalDao.updateMedal(medal);
    }

    /**
    * @Description: 通过id查询奖章信息
    * @Param: * @param id
    * @return: com.xunyunedu.medal.model.Medal
    * @Author: cmb
    * @Date: 2020/10/14
    */
    @Override
    public Medal findMeDalById(Integer id) {
        return this.medalDao.findMeDalById(id);
    }
/**
* @Description: 创建学生奖章
* @Param: * @param medals
* @return: void
* @Author: cmb
* @Date: 2020/10/14
*/
    @Override
    public void crateMedalStudent(List<MvAwardsMedal> awardsMedals) {

       this.medalDao.crateMedalStudent(awardsMedals);
    }
/**
* @Description: 通过学生id查询学生所获取锐意
* @Param: * @param name
* @return: java.util.List<com.xunyunedu.medal.model.MvAwardsMedal>
* @Author: cmb
* @Date: 2020/10/23
*/
    @Override
    public List<MvAwardsMedal> findMvAwardsMedalByStudent(Integer studentid) {
        return medalDao.findMvAwardsMedalByStudent(studentid);
    }
/**
* @Description: 查询学期
* @Param: * @param schoolId
 * @param year
* @return: java.lang.String
* @Author: cmb
* @Date: 2020/11/4
*/

    @Override
    public String getYearTerm(Integer schoolId, String year) {
        if (schoolId!=null && year!=null && !year.equals("")){
            Map map=new HashMap();
            map.put("schoolId",schoolId);
            map.put("year",year);
            PjSchoolTerm yearTerm = pjSchoolTermDao.findYearTerm(map);
            if (yearTerm!=null){
                return yearTerm.getName();
            }
        }

         return null;
    }
/**
* @Description: 通过奖章id及学生id查询详情表
* @Param: * @param id
 * @param studentId
* @return: com.xunyunedu.medal.model.MvAwardsMedal
* @Author: cmb
* @Date: 2020/11/5
*/

    @Override
    public MvAwardsMedal findMvAwardsMedalByStudentAndMeDalById(Integer id, Integer studentId) {
        Map map=new HashMap();
        map.put("studentId",studentId);
        map.put("medalId",id);
        return medalDao.findMvAwardsMedalByStudentAndMeDalById(map);
    }

    /**
* @Description:查询所有奖章信息
* @Param:
* @return:  * @return : java.util.List<com.xunyunedu.medal.model.Medal>
* @Author: cmb
* @Date: 2020/10/14
*/
    @Override
    public List<Medal> findMedalAll() {
        return this.medalDao.findMedalAll();
    }


}