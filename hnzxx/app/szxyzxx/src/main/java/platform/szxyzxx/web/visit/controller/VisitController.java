package platform.szxyzxx.web.visit.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.generalTeachingAffair.service.BasicSQLService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap,Page page){
        list(modelMap,page,null,null,null,null,null);
        return "visit/index";
    }
    @RequestMapping("/list")
    public String list(ModelMap modelMap, Page page, Integer status, String beginDate, String endDate, String visitorName, String visitUserName){
        StringBuilder sql=new StringBuilder("select * from visit_visit_apply");
        sql.append(buildWhereSql(status, beginDate, endDate, visitorName, visitUserName));
        sql.append(" order by create_date desc");
        List<Map<String, Object>> list=basicSQLService.findByPaging(page,sql.toString());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String nowStr=sdf.format(new Date());
        for (Map<String, Object> map : list) {
            if(map.get("visit_status").toString().equals("3")){
                Date visitDate=(Date) map.get("visit_date");
                if(nowStr.compareTo(sdf.format(visitDate))==1){
                    map.put("visit_status",6);
                }
            }
        }
        modelMap.put("list",list);
        return "visit/list";
    }


    private String buildWhereSql(Integer status,String beginDate,String endDate,String visitorName,String visitUserName){
        List<String> list=new ArrayList<>();
        String nowStr=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(status!=null){
            if(status==3){
                list.add("visit_status=3 and visit_date >='"+nowStr+"'");
            }else if(status==6){
                list.add("visit_status=3 and visit_date < '"+nowStr+"'");
            }else{
                list.add("visit_status="+status);
            }
        }

        if(StringUtils.isNotEmpty(beginDate) && StringUtils.isNotEmpty(endDate)){
            list.add("visit_date between '"+beginDate+"' and '"+endDate+"'");
        }

        if(StringUtils.isNotEmpty(visitorName)){
            list.add("applicant_name like '%"+visitorName+"%'");
        }

        if(StringUtils.isNotEmpty(visitUserName)){
            list.add("visit_user_name like '%"+visitUserName+"%'");
        }

        StringBuilder whereSql=new StringBuilder("");
        for (int i=0;i<list.size();i++) {
            if(i==0){
                whereSql.append(" where ");
            }else{
                whereSql.append(" and ");
            }
            whereSql.append(list.get(i));
        }

        return whereSql.toString();
    }


}
