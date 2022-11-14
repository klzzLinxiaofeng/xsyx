package com.xunyunedu.zhishidian.dao;

import com.xunyunedu.zhishidian.vo.KnowEvaluation;
import com.xunyunedu.zhishidian.vo.KnowLedge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowLedgeDao {
    List<KnowLedge> findByAll(@Param("gradeId") Integer gradeId,
                              @Param("subject")Integer subject);


    List<KnowEvaluation> findByPinjai(@Param("knowId")Integer knowId,
                                      @Param("leven") Integer leven,
                                      @Param("parentMenu") Integer parentMenu);
    KnowEvaluation findById(@Param("ziMenu")Integer ziMenu,
                            @Param("studentId")Integer studentId);
}
