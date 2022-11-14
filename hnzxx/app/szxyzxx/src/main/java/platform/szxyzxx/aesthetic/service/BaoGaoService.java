package platform.szxyzxx.aesthetic.service;

import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.BaoGaoQuery;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

public interface BaoGaoService {
    public Map<String, Object> findByCharacterAll(UserInfo userInfo,BaoGaoQuery baoGaoQuery);
    public List<Map<String, Object>> findByAllXueXi(UserInfo userInfo,BaoGaoQuery baoGaoQuery);
    public IndicatorsStudent findByAllTiZhi(UserInfo userInfo, BaoGaoQuery baoGaoQuery);
    public PracticeInnovation findByAllShijian(UserInfo userInfo, BaoGaoQuery baoGaoQuery);
    public AestheticPojo findByAllAestheticPojo(UserInfo userInfo, BaoGaoQuery baoGaoQuery);
    public List<Map<String, Object>> findByAllsubject(UserInfo userInfo, BaoGaoQuery baoGaoQuery,Integer subjectId);
    //获取所有有指标的科目
    public List<Map<String, Object>> findByAllsubjectId(UserInfo userInfo, BaoGaoQuery baoGaoQuery);
    //获取学生期末，期中考试的分差值
    public List<Map<String, Object>> findByAllstudentIdChengJi(UserInfo userInfo, BaoGaoQuery baoGaoQuery,Integer studnetId);
}
