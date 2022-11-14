
package com.xunyunedu.repair.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.repair.pojo.AcceptRepari;
import com.xunyunedu.repair.pojo.ApplyrepairCommentParam;
import com.xunyunedu.repair.pojo.ApplyrepairCondition;
import com.xunyunedu.repair.pojo.ApplyrepairPojo;
import com.xunyunedu.repair.pojo.model.ShenHe;

public interface RepairService {

    /**
     * 添加
     * @param repairPojo
     */
    void add(ApplyrepairPojo repairPojo);

    /**
     * 分页查询维修单和评价维修信息
     * @param condition
     * @return
     */
    PageInfo pagingList(PageCondition<ApplyrepairCondition> condition);

    ApplyrepairPojo getDetailById(Integer id);

    /**
     * 评论维修单
     * @param param
     */
    void comment(ApplyrepairCommentParam param);

    void addAcceptRepari(AcceptRepari acceptRepari,String state);
    void updateShenhe(ShenHe shenHe);
    ApplyrepairPojo findById( Integer id);
    void addShiJian(ApplyrepairPojo applyrepairPojo);

    void updateShenHeLie(Integer type,Integer dataId);

}

