package com.xunyunedu.personinfor.service.impl;

import com.xunyunedu.personinfor.service.MyinforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yhc
 * @Date: 2020/9/28 9:35
 * @Description: 解决在同时开启事务和加锁的问题
 */
@Service
public class LockClassService {

    @Autowired
    private MyinforService myinforService;

    /**
     * 取消选课功能
     *
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return 1:选课成功 0 选课失败 2：人数已满
     */
    public void modifyChooseNum(Integer cid, Integer stuId, Integer schooldId) {

        synchronized (LockClassService.class) {
            myinforService.modifyChooseNum(cid, stuId, schooldId);
        }
    }

    /**
     * 选课功能   免费
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     */
    public Integer addChooseClass(Integer cid, Integer stuId, Integer schooldId,Integer isJiao) {
        synchronized (LockClassService.class) {
            return myinforService.addChooseClass(cid, stuId, schooldId,isJiao);
        }
    }


    /**
     * 选课功能   收费
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     */
    public Integer addChooseClassShouFei(Integer cid, Integer stuId, Integer schooldId) {
        synchronized (LockClassService.class) {
            return myinforService.addChooseClassShouFei(cid, stuId, schooldId);
        }
    }


    public Integer getxaunkes(Integer cid, Integer stuId, Integer schooldId) {
        synchronized (LockClassService.class) {
            return myinforService.getxuankes(stuId,cid, schooldId);
        }
    }

    /*
    * 支付成功修改状态
    */
    public Integer paySuccess(Integer cid, Integer stuId, Integer schooldId,Integer isJiao) {
        synchronized (LockClassService.class) {
            return myinforService.paySuccess(cid, stuId, schooldId,isJiao);
        }
    }


    /**
     * 选课功能
     *
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return  选课已成功，超过30秒未支付，删除订单，并释放占有名额
     */
    public Boolean deleteChooseClass(Integer cid, Integer stuId, Integer schooldId) {
        synchronized (LockClassService.class) {
            return myinforService.updateXuanke(cid, stuId, schooldId);
        }
    }


}
