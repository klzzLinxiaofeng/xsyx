package platform.szxyzxx.literacy.service;

import framework.generic.dao.Page;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface LiteracyService {
    List<LiteracyVo> findByAll(String xq, Integer xn, Integer nj, Integer km, Page page);
    Boolean create(LiteracyVo literacyVo, UserInfo userInfo);
     LiteracyVo findById(Integer id);
     Boolean update(LiteracyVo literacyVo);

     Boolean updatefenshu(Integer id,Integer score,String pingyu);

    Boolean updatedaoruXuigai(Integer StudentId,Integer literId,Integer fenshu);

    Integer updatestudent(Integer literacyId);

    List<LiteracyVo> findByName(LiteracyVo literacyVo);
    LiteracyVo findBySchoolYearAndSchoolTrem(String year,String schoolTrem,Integer gardeId,Integer subject,String name);
}
