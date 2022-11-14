package platform.szxyzxx.web.pay.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.pay.pojo.PayOrder;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 食堂支付订单控制器
 */
@Controller
@RequestMapping("/pay/st")
public class PjPayOrderController extends BaseController {

    private final static String viewBasePath = "/pay/st";


    private Exporter<PayOrder> exporter;

    @Autowired
    private BasicSQLService basicSQLService;


    public PjPayOrderController(){
        ExcelExportParam<PayOrder> exportParam=new ExcelExportParam<>(PayOrder.class, "食堂充值订单");
        exportParam.setFixTitleRow(true);
        exportParam.setAutoFilteTitleRow(true);
        exportParam.setExcel2003(true);
        exporter=new DefaultExporter<>(exportParam);
    }

    @RequestMapping("/list")
    public String index(PayOrder payOrder, Page page, @RequestParam(value = "sub", required = false) String sub, ModelMap modelMap){
        String viewPath="/index";
        if ("list".equals(sub)) {
            viewPath = "/list";
        }
        List list=queryByObj(payOrder,page);
        modelMap.put("list",list);
        return viewBasePath+viewPath;
    }


    @RequestMapping("/export")
    public void exports(PayOrder payOrder, HttpServletResponse rep){
        Workbook wb=null;
        OutputStream out = null;
        try {
            List<PayOrder> list=convertMapToPayOrder(queryByObj(payOrder,null));
            wb=exporter.exportToNew(list);
            out=rep.getOutputStream();

            String fileName = URLEncoder.encode("食堂充值订单.xls", "UTF-8");
            rep.setContentType("application/force-download");
            rep.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            rep.addHeader("Cache-Control", "no-cache");
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<PayOrder> convertMapToPayOrder(List<Map<String,Object>> mapList){
        List<PayOrder> orderList=new ArrayList<>(mapList.size());
        for (Map<String, Object> map : mapList) {
            PayOrder order=new PayOrder();
            order.setEmpName((String) map.get("emp_name"));
            order.setEmpCard((String) map.get("emp_card"));
            order.setPayStatus((Integer) map.get("is_payed"));
            order.setPayAmount((BigDecimal) map.get("pay_amount"));
            order.setSendStatus((Integer) map.get("is_send_order"));
            order.setPayMethod((Integer)map.get("pay_type"));
            order.setOrderNo((String) map.get("pay_no"));
            order.setCreateTime((Date) map.get("create_time"));
            order.setPayTime((Date) map.get("pay_time"));
            orderList.add(order);
        }
        return orderList;
    }

    private List<Map<String,Object>> queryByObj(PayOrder payOrder,Page page){
        List<String> conditionList=new ArrayList<>(1);
        if(StringUtils.isNotEmpty(payOrder.getEmpName())){
            conditionList.add("emp_name like '%"+payOrder.getEmpName()+"%'");
        }
        if(payOrder.getPayStatus()!=null){
            conditionList.add("is_payed="+payOrder.getPayStatus());
        }
        if(payOrder.getSendStatus()!=null){
            conditionList.add("is_send_order="+payOrder.getSendStatus());
        }

        if(StringUtils.isNotEmpty(payOrder.getStartDate())){
            conditionList.add("pay_time>='"+payOrder.getStartDate()+"'");
        }
        if(StringUtils.isNotEmpty(payOrder.getEndDate())){
            //结束日期加一天
            try {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Calendar endCa=Calendar.getInstance();
                endCa.setTime(sdf.parse(payOrder.getEndDate()));
                endCa.add(Calendar.DAY_OF_MONTH,1);
                conditionList.add("pay_time<'"+sdf.format(endCa.getTime())+"'");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        String sql=buildConditionSql(conditionList);
        if(page!=null) {
            return basicSQLService.findByPaging(page, sql);
        }
        return basicSQLService.find(sql);
    }


    private String buildConditionSql(List<String> conditionList){
        StringBuilder sql=new StringBuilder("select * from pj_pay_order");

        for (int i=0;i<conditionList.size();i++) {
            if(i==0){
                sql.append(" where ");
            }else{
                sql.append(" and ");
            }
            sql.append(conditionList.get(i));
        }
        sql.append(" order by create_time desc");
        return sql.toString();
    }

}
