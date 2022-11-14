package com.xunyunedu.personinfor.controller;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.config.pay.bean.WxMiniApp;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.pay.pojo.OrderResultsPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.personinfor.pojo.KeXuanKe;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import com.xunyunedu.personinfor.pojo.QuestionnairePojo;
import com.xunyunedu.personinfor.service.KeXuanKeService;
import com.xunyunedu.personinfor.service.MyinforService;
import com.xunyunedu.personinfor.service.impl.LockClassService;
import com.xunyunedu.util.redis.RedisPoolUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 首页信息
 *
 * @author: yhc
 * @Date: 2020/9/18 15:07
 * @Description: 小程序授权登录
 */
@RequestMapping("/home")
@RestController
@Api(value = "/home", description = "首页接口")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MyinforService myinforService;

    @Autowired
    private LockClassService lockClassService;
    @Autowired
    private PayService payService;

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private WxMiniApp wxMiniApp;
    @Autowired
    private KeXuanKeService keXuanKeService;

    /**
     * 选课功能
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return
     */
    @PostMapping("/chooseClass")
    @ApiOperation(value = "选课功能", httpMethod = "POST")
    /*@Authorization*/
    public ApiResult chooseClass(@ApiParam(required = true, value = "课程id", name = "cid") @RequestParam Integer cid
            , @ApiParam(required = true, value = "学生id", name = "stuId") @RequestParam Integer stuId
            , @ApiParam(required = true, value = "学校id", name = "schooldId") @RequestParam Integer schooldId
            , @ApiParam(required = true, value = "是否教材料费", name = "isJiao") @RequestParam Integer isJiao
            ) {
        if (cid == null || stuId == null || schooldId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
            Map<String, Object> status = new HashMap<>(1);
            //选课是否是成功1:选课成功 0 选课失败 2：人数已满
        //isJiao  0教材料费  1不教材料费  2免费
        if(isJiao==null){
            isJiao=2;
        }
        PublicClassPojo publicClass=myinforService.counservt(cid, schooldId);
        Date d = new Date();
      if(d.getTime()<=publicClass.getExpiryDate().getTime() && d.getTime()>=publicClass.getBeginDate().getTime()){
          //logger.info("xuefei"+publicClass.getXuefei());
          //logger.info("xuefei是不是"+(publicClass.getXuefei()==0));
          if(publicClass.getXuefei()==0){
              Integer flg = lockClassService.addChooseClass(cid, stuId, schooldId,isJiao);
              if (flg == 1) {
                  status.put("status", true);
                  status.put("zhifu",false);
                  return new ApiResult(ResultCode.SUCCESS,status);
                  /*     status.put("status", true);*/
                  /*  return new ApiResult(ResultCode.SUCCESS, status);*/
              } else if (flg == 0) {
                  //选课失败
                  status.put("status", false);
                  return new ApiResult(ResultCode.CHOOSE_CLASS_ERROR, status);
              } else if (flg == 2) {
                  //人数已满
                  status.put("status", false);
                  return new ApiResult(ResultCode.CHOOSE_FULL_ERROR, status);
              }
          }else{
              Integer flg = lockClassService.addChooseClassShouFei(cid, stuId, schooldId);
              /*return new ApiResult(ResultCode.INTERNAL_SERVER_ERROR, status);*/
              if (flg == 1) {
                  status.put("status", true);
                  status.put("zhifu",true);
                  return new ApiResult(ResultCode.SUCCESS,status);
                  /*     status.put("status", true);*/
                  /*  return new ApiResult(ResultCode.SUCCESS, status);*/
              } else if (flg == 0) {
                  //选课失败
                  status.put("status", false);
                  return new ApiResult(ResultCode.CARD_NOT_ASD, status);
              } else if (flg == 2) {
                  //人数已满
                  status.put("status", false);
                  return new ApiResult(ResultCode.CHOOSE_FULL_ERROR, status);
              }
          }
          status.put("status", false);
          return new ApiResult(ResultCode.INTERNAL_SERVER_ERROR, status);
        }else{
          status.put("status", false);
          return new ApiResult(ResultCode.CHOOSE_CLASS_ERROR, status);
      }
    }
    /**
     * 选课支付功能
     *
     * @return
     */

    @PostMapping("/chooseClassZhifu")
    @ApiOperation(value = "选课支付功能", httpMethod = "POST")
    public ApiResult XuangeZhifu(@RequestBody PayOrderPojo payOrderPojo){
        logger.info("");
        //微信支付
        ApiResult apiResult=new ApiResult();
        logger.info("数据"+payOrderPojo.getPayAmount());
        if (payOrderPojo == null || payOrderPojo.getUserId() == null || payOrderPojo.getPayAmount() == null || payOrderPojo.getOpenid() == null
                || payOrderPojo.getSchoolId() == null || payOrderPojo.getUserType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        OrderResultsPojo orderResultPojo = new OrderResultsPojo();
        WxPayUnifiedOrderRequest orderRequest = null;
        // 生成订单信息
        try {
            orderRequest = payService.configXuanke(payOrderPojo);
            WxPayMpOrderResult order = null;
            // 微信服务器下单
            order = wxPayService.createOrder(orderRequest);
            if (null != order) {
                BeanUtils.copyProperties(order, orderResultPojo);
                orderResultPojo.setOutTradeNo(orderRequest.getOutTradeNo());
            }
           /* // 根据课程id修改已报名人数
            myInforDao.modifyByEnrollNumber(cid, schooldId);
            // 选课成功 保存信息
            myInforDao.addChoose(cid, stuId, schooldId,isJiao);*/
            logger.info("统一下单--下单成功订单: {}", orderRequest.getOutTradeNo());

        } catch (Exception ee) {
            logger.error("生成支付订单信息失败: {}", ee.toString());
            // 修改下单状态
            String outTradeNo = orderRequest.getOutTradeNo();
            PayOrderPojo pay = new PayOrderPojo();
            pay.setOrderNumber(outTradeNo);
            //下单状态 0失败  1成功
            pay.setPlaceOrderState(0);
            // 下单失败删除订单
            Jedis jedis = null;
            try {
                jedis = RedisPoolUtil.getConnect();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    RedisPoolUtil.closeConnect(jedis);
                }
            }
            throw new BusinessRuntimeException(ResultCode.PAY_ORDER_ERROR);
        }
        return new ApiResult(ResultCode.SUCCESS, orderResultPojo);
    }

    @PostMapping("/paySuccess")
    @ApiOperation(value = "支付成功修改绑定", httpMethod = "POST")
    public ApiResult paySuccess(@ApiParam(required = true, value = "课程id", name = "cid") @RequestParam Integer cid
            , @ApiParam(required = true, value = "学生id", name = "stuId") @RequestParam Integer stuId
            , @ApiParam(required = true, value = "学校id", name = "schooldId") @RequestParam Integer schooldId
            , @ApiParam(required = true, value = "是否教材料费", name = "isJiao") @RequestParam Integer isJiao
    ){
        Map<String, Object> status = new HashMap<>(1);
        Integer num=lockClassService.paySuccess(cid, stuId, schooldId, isJiao);
        if(num>0){
            status.put("status",true);
            return new ApiResult(ResultCode.SUCCESS,status);
        }
        status.put("status",false);
        return new ApiResult(ResultCode.CHOOSE_CLASS_ERROR,status);
    }

    /**
     * 微信回调接口
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=23_8&index=6
     * ● 商户系统收到支付结果通知，需要在5秒内返回应答报文，否则微信支付认为通知失败，后续会重复发送通知。
     * ● 同样的通知可能会多次发送给商户系统，商户系统必须能够正确处理重复的通知。如果已处理过，直接给微信支付返回成功。
     *
     * @param xmlData
     * @return
     * @throws WxPayException
     */
    @RequestMapping("/notify")
    public String submit(@RequestBody String xmlData) {
        logger.debug("微信支付回调开始...");
        return payService.payNotifyXuanke(xmlData);
    }



    /**
     * 查询学生可选课程信息
     * 查询课程信息：包含当前学生已经选择和未选择的课程，已选择课程会优先展示
     *
     * @param grade     当前学生的年级
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return
     */
    @GetMapping("/courseInfo")
    /*@Authorization*/
    public ApiResult getChildren(@RequestParam Integer grade, @RequestParam Integer stuId, @RequestParam Integer schooldId,
                                 @RequestParam Integer coursetype) {
      logger.warn("type"+coursetype);
        if (grade == null || schooldId == null || stuId == null || coursetype==null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<PublicClassPojo> list = myinforService.getChildren(grade, schooldId, stuId,coursetype);
        return new ApiResult(ResultCode.SUCCESS, list);
    }
    /**
     * 课程详情
     *
     * @param cid       课程id
     * @param schooldId 学校id
     * @param stuId     学生id
     * @param type      选课类型 1：历史查看详情 2：正常选课查看详情
     * @return
     */
    @GetMapping("/courseDetail")
    @Authorization
    public ApiResult courseDetail(@RequestParam Integer cid, @RequestParam Integer schooldId, @RequestParam Integer stuId, @RequestParam Integer type) {
        if (cid == null || schooldId == null || stuId == null || type == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PublicClassPojo list = myinforService.courseDetails(cid, schooldId, stuId, type);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 取消选课功能
     *
     * @param cid       课程id
     * @param stuId     学生id
     * @param schooldId 学校id
     * @return
     */
    @DeleteMapping("/cancelChoose")
    @ApiOperation(value = "取消选课功能", httpMethod = "DELETE")
    @Authorization
    public ApiResult cancelChoose(@ApiParam(required = true, value = "课程id", name = "cid") @RequestParam Integer cid
            , @ApiParam(required = true, value = "学生id", name = "stuId") @RequestParam Integer stuId
            , @ApiParam(required = true, value = "学校id", name = "schooldId") @RequestParam Integer schooldId) {
        Map<String, Boolean> status = new HashMap<>(1);
        status.put("status", false);
        if (cid == null || stuId == null || schooldId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        lockClassService.modifyChooseNum(cid, stuId, schooldId);

        status.put("status", true);
        return new ApiResult(ResultCode.SUCCESS, status);
    }

    /**
     * 所有选课信息
     *
     * @param schooldId 暂时不需要学校id
     * @return
     */
    @GetMapping("/allCoursesInfo")
    @ApiOperation(value = "所有选课信息", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 10001, message = "参数为空"),
    })
    @Authorization
    public ApiResult allCoursesInfo(@ApiParam(required = false, value = "暂时不需要学校id", name = "schooldId") @RequestParam(required = false) Integer schooldId) {
        // 暂时不判断，获取所有学校的课程
//        if (schooldId == null) {
//            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
//        }
        // 获取所有年级的选课课程
        Map<String, List<PublicClassPojo>> map = myinforService.getAllCourse(schooldId);

        return new ApiResult(ResultCode.SUCCESS,map);
    }

    /**
     * 选课历史记录
     *
     * @param schooldId 学校id
     * @param stuId     学生id
     * @return
     */
    @GetMapping("/allCoursesHistory")
    public ApiResult allCoursesHistory(@RequestParam Integer schooldId, @RequestParam Integer stuId,
                                       @RequestParam Integer coursetype) {
        if (schooldId == null || stuId == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 获取所有年级的选课课程
        List<PublicClassPojo> list = myinforService.getAllCourseHistory(schooldId, stuId,coursetype);

        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 获取问卷信息
     *
     * @param questionnairePojo
     * @return
     */
    @GetMapping("/getQuestions")
    public ApiResult getQuestion(QuestionnairePojo questionnairePojo, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (questionnairePojo == null || questionnairePojo.getObject() == null) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        // 获取所有需要填写的问卷
        PageInfo<QuestionnairePojo> list = myinforService.findBySchooldIdAndObject(questionnairePojo, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }

    /**
     * 家长接送- 获取接送地点列表
     *
     * @param schooldId 学校id
     * @param stuId     家长下的所有孩子id,数组形式
     * @param location  家长选择的接送地点
     * @return
     */
    @PostMapping("/afterSchoolLocation")
    public ApiResult afterSchool(@RequestParam Integer schooldId, @RequestParam Integer[] stuId, @RequestParam Integer location) {
        if (schooldId == null || stuId == null || location == null || stuId.length == 0) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<Integer> integers = Arrays.asList(stuId);

        myinforService.addLocation(schooldId, integers, location);

        return new ApiResult(ResultCode.SUCCESS);
    }


    /**
     * 家长接送-修改接送地点
     *
     * @param schooldId 学校id
     * @param stuId     家长下的所有孩子id,数组形式
     * @param location  家长选择的接送地点
     * @return
     */
    @PutMapping("/updateLocation")
    public ApiResult updateLocation(@RequestParam Integer schooldId, @RequestParam Integer[] stuId, @RequestParam Integer location) {
        if (schooldId == null || stuId == null || location == null || stuId.length == 0) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<Integer> integers = Arrays.asList(stuId);

        myinforService.updateLocation(schooldId, integers, location);

        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * @param gradeId 学校id
     * @return
     */
    @RequestMapping("/KeXuanKe")
    @ApiOperation(value = "是否可选课", httpMethod = "GET")
    /*@Authorization*/
    public ApiResult keXuanKe(@ApiParam(required = true, value = "年级id", name = "gradeId") @RequestParam Integer gradeId
    ) {
        if (gradeId == null ) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<KeXuanKe> list=keXuanKeService.findByGradeIdKeXuanKes(gradeId);
        if(list.size()<=0){
            return new ApiResult(ResultCode.FIND_FAIL);
        }else {
            if(list.get(0).getZhuantai()==0){
                return new ApiResult(ResultCode.SUCCESS);
            }else{
                return new ApiResult(ResultCode.FIND_FAIL);
            }
        }
    }

}
