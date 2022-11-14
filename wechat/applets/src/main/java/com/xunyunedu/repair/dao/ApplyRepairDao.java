
package com.xunyunedu.repair.dao;

import com.xunyunedu.repair.pojo.AcceptRepari;
import com.xunyunedu.repair.pojo.ApplyrepairCommentParam;
import com.xunyunedu.repair.pojo.ApplyrepairPojo;
import com.xunyunedu.repair.pojo.model.ShenHe;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 报修
 * @author chenjiaxin
 * @Date 2020/12/08
 */

public interface ApplyRepairDao {
    int add(ApplyrepairPojo applyrepairPojo);
    int update(ApplyrepairPojo applyrepairPojo);
    List<ApplyrepairPojo> selectByCondition(Object condition);

    /**
     * 评价维修单
     * @param param
     * @return
     */
    int commentRepair(ApplyrepairCommentParam param);

    /**
     * 创建维修单维修信息
     * @param acceptRepari
     */
    void addAcceptRepari(AcceptRepari acceptRepari);

    /**
     * 审核维修单
     * @param shenHe
     */
   void updateShenhe(ShenHe shenHe);
    ApplyrepairPojo findById(@Param("id") Integer id);

    void addShiJian(ApplyrepairPojo applyrepairPojo);
    void  updateShenHeLie(@Param("type") Integer type,
                          @Param("dataId") Integer dataId);
}

