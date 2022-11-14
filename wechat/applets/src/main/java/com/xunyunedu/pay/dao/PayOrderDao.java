package com.xunyunedu.pay.dao;

import com.xunyunedu.pay.pojo.PayAmountPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.pojo.StudentPojo;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2020/10/22 11:32
 *  @Description:
 */
public interface PayOrderDao {
    PayAmountPojo getAmount(Integer id);
    PayAmountPojo getXuankeAmount(Integer id);

    List<PayAmountPojo> getAmountBySchool(PayAmountPojo payAmountPojo);

    void createOrder(PayOrderPojo payOrderPojo);
    void createxuanke(PayOrderPojo payOrderPojo);

    PayOrderPojo getOrderInfoByNumber(PayOrderPojo payOrderPojo);

    int update(PayOrderPojo payOrderPojo);
    int updateXuanke(PayOrderPojo payOrderPojo);

    PayOrderPojo getPayOrderPojo(String payNo);
    StudentPojo getStuInfo(StudentPojo studentPojo);
    /*
     * 获取所有已支付，未发送到食堂的订单
     */
    List<PayOrderPojo> findByAll();
    Integer updateasdasd(Long id);

}
