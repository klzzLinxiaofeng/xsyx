package platform.szxyzxx.dto.meal.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.szxyzxx.dto.meal.service.MealService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class MealServiceImpl implements MealService {

    private static Logger logger= LoggerFactory.getLogger(MealServiceImpl.class);

    @Autowired
    private BasicSQLService basicSQLService;

    @Override
    public void syncLastDayReportMx() {
        try {
            String lastDay=getLastDay();
            String jsonStr=HttpClientUtils.get("http://10.170.76.29:8090/api/meal/MealReportMx/listAsync?meal_dateBegin="+lastDay+"&meal_dateEnd="+lastDay+"&PageIndex=1&PageSize=9999999");
            if(jsonStr!=null) {
                JSONArray rows = JSONUtil.parseObj(jsonStr).getJSONObject("data").getJSONArray("rows");
                if (rows.size() > 0) {
                    basicSQLService.update(buildInsertSql(rows));
                }
            }
        } catch (Exception e) {
            logger.error("同步食堂消费明细失败",e);
        }

    }

    private String buildInsertSql(JSONArray rows){
        StringBuilder insertSql=new StringBuilder("INSERT INTO `meal_report_mx` (`id`, `emp_id`, `emp_code`, `emp_name`, `job_code`, `job_name`, `company_code`, `dept_name_full`, `dept_code`, `dept_name`, `emp_mealtype`, `emp_leaveflagUI`, `emp_workdate`, `emp_card`, `title_name`, `mac_code`, `mac_name`, `hotel_code`, `hotel_name`, `meal_date`, `sign_date`, `meal_time`, `meal_type`, `meal_code`, `meal_name`, `meal_money`, `meal_money02`, `meal_moneybt`, `meal_moneynet`) VALUES ");
        for(int i=0;i<rows.size();i++){
            insertSql.append("(");
            JSONObject row=rows.getJSONObject(i);
            insertSql.append(getVal(row,"id")).append(",");
            insertSql.append(getVal(row,"emp_id")).append(",");
            insertSql.append(getStrVal(row,"emp_code")).append(",");
            insertSql.append(getStrVal(row,"emp_name")).append(",");
            insertSql.append(getStrVal(row,"job_code")).append(",");
            insertSql.append(getStrVal(row,"job_name")).append(",");
            insertSql.append(getStrVal(row,"company_code")).append(",");
            insertSql.append(getStrVal(row,"dept_name_full")).append(",");
            insertSql.append(getStrVal(row,"dept_code")).append(",");
            insertSql.append(getStrVal(row,"dept_name")).append(",");
            insertSql.append(getStrVal(row,"emp_mealtype")).append(",");
            insertSql.append(getStrVal(row,"emp_leaveflagUI")).append(",");
            insertSql.append(getStrVal(row,"emp_workdate")).append(",");
            insertSql.append(getStrVal(row,"emp_card")).append(",");
            insertSql.append(getStrVal(row,"title_name")).append(",");
            insertSql.append(getStrVal(row,"mac_code")).append(",");
            insertSql.append(getStrVal(row,"mac_name")).append(",");
            insertSql.append(getStrVal(row,"hotel_code")).append(",");
            insertSql.append(getStrVal(row,"hotel_name")).append(",");
            insertSql.append(getStrVal(row,"meal_date")).append(",");
            insertSql.append(getStrVal(row,"sign_date")).append(",");
            insertSql.append(getStrVal(row,"meal_time")).append(",");
            insertSql.append(getStrVal(row,"meal_type")).append(",");
            insertSql.append(getStrVal(row,"meal_code")).append(",");
            insertSql.append(getStrVal(row,"meal_name")).append(",");
            insertSql.append(getStrVal(row,"meal_money")).append(",");
            insertSql.append(getStrVal(row,"meal_money02")).append(",");
            insertSql.append(getStrVal(row,"meal_moneybt")).append(",");
            insertSql.append(getStrVal(row,"meal_moneynet"));
            insertSql.append(")");
            if(i!=rows.size()-1){
                insertSql.append(",");
            }
        }
        return insertSql.toString();
    }

    private String getStrVal(JSONObject obj,String key){
        Object val=obj.get(key);
        if(val==null || val instanceof JSONNull || val.equals("null")){
            return "null";
        }
        return "'"+val+"'";
    }

    private String getVal(JSONObject obj,String key){
        Object val=obj.get(key);
        if(val==null || val instanceof JSONNull || val.equals("null")){
            return "null";
        }
        return val.toString();
    }

    private String getLastDay(){
        Calendar now=Calendar.getInstance();
        now.setTime(new Date());
        now.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDay=now.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(lastDay);
    }
}
