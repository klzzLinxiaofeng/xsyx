package platform.szxyzxx.web.moral.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.dy.pojo.DyExcellentTeamXxcj;
import platform.szxyzxx.util.PageUtils;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@RequestMapping("/dy/ex/ana")
@Controller
public class DyExcellentTeamAnalysisController {

    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/weekIndex")
    public String weekAnalysisPage(){
        return "/moral/excellentTeamAnan/weekIndex";
    }

    @RequestMapping("/xqIndex")
    public String xqAnalysisPage(){
        return "/moral/excellentTeamAnan/xqIndex";
    }

    @RequestMapping("/weekList")
    @ResponseBody
    public StuVo weekList(Page page, String queryId,String previousQueryId,DyExcellentTeamXxcj condition, HttpSession session){
        StuVo stuVo=new StuVo();
        if(condition.getXqz()==null && condition.getXqy()==null){
            stuVo.setList(new ArrayList(0));
            page.setTotalRows(0);
            stuVo.setPage(page);
            return stuVo;
        }
        if(StringUtils.isNotEmpty(previousQueryId)) {
            session.removeAttribute(previousQueryId);
        }
        List cache=(List) session.getAttribute(queryId);
        if(cache==null){
            List<Map<String,Object>> anaList=new ArrayList<>();
            //因为需要连接多个一对多的表，所以不使用连表查询
            List<Map<String,Object>> yrcgList=getDyScoreList(condition,"dy_excellent_team_yrcg",null);
            List<Map<String,Object>> xxcjList=getDyScoreList(condition,"dy_excellent_team_xxcj",null);
            List<Map<String,Object>> njpjList=getDyScoreList(condition,"dy_excellent_team_njpj",null);

            compute(anaList,yrcgList,"yrcg","0.8");
            compute(anaList,xxcjList,"xxcj","0.1");
            compute(anaList,njpjList,"njpj","0.1");

            sort(anaList);

            cache=anaList;
            session.setAttribute(queryId,cache);
        }
        return PageUtils.pagingGetStuVoByList(cache,page);
    }

    @RequestMapping("/xqList")
    @ResponseBody
    public StuVo xqList(Page page, String queryId,String previousQueryId,DyExcellentTeamXxcj condition, HttpSession session){
        StuVo stuVo=new StuVo();
        if(StringUtils.isNotEmpty(previousQueryId)) {
            session.removeAttribute(previousQueryId);
        }
        List cache=(List) session.getAttribute(queryId);
        if(cache==null){
            List<Map<String,Object>> anaList=new ArrayList<>();
            //因为需要连接多个一对多的表，所以不使用连表查询
            List<Map<String,Object>> yrcgList=getDyScoreList(condition,"dy_excellent_team_yrcg",null);
            List<Map<String,Object>> xxcjList=getDyScoreList(condition,"dy_excellent_team_xxcj",null);
            List<Map<String,Object>> njpjList=getDyScoreList(condition,"dy_excellent_team_njpj",null);
            List<Map<String,Object>> dkjList=getDyScoreList(condition,"dy_excellent_team_dkj",null);
            List<Map<String,Object>> dxhdList=getDyScoreList(condition,"dy_excellent_team_dxhd",null);
            List<Map<String,Object>> grryList=getDyScoreList(condition,"dy_excellent_team_ry",0);
            List<Map<String,Object>> jtryList=getDyScoreList(condition,"dy_excellent_team_ry",1);

            compute(anaList,yrcgList,"yrcg","0.3");
            compute(anaList,xxcjList,"xxcj","0.1");
            compute(anaList,njpjList,"njpj","0.2");
            compute(anaList,dkjList,"dkj","0.1");
            compute(anaList,dxhdList,"dxhd","0.1");
            compute(anaList,grryList,"grry","0.1");
            compute(anaList,jtryList,"jtry","0.1");

            sort(anaList);

            cache=anaList;
            session.setAttribute(queryId,cache);
        }
        return PageUtils.pagingGetStuVoByList(cache,page);
    }





    private void compute(List<Map<String,Object>> anaList,List<Map<String,Object>> dyList,String keyPrefix,String bl){
        for (Map<String, Object> dyMap : dyList) {
            String teamName=(String) dyMap.get("team_name");
            Map<String,Object> anaMap=findAnanysisObjByTeamName(anaList,teamName);
            if(anaMap==null){
                anaMap=new HashMap<>();
                anaMap.put("teamName",teamName);
                anaList.add(anaMap);
            }
            BigDecimal typeSum=new BigDecimal(dyMap.get("sum_score").toString());
            BigDecimal typeBl=new BigDecimal(bl);
            //计算结果取小数点后两位，多余小数直接舍去
            BigDecimal typeScore=typeSum.multiply(typeBl).setScale(2,BigDecimal.ROUND_DOWN);
            BigDecimal sum=(BigDecimal) anaMap.get("sumScore");
            if(sum==null){
                sum=typeScore;
            }else{
                sum=sum.add(typeScore);
            }
            anaMap.put(keyPrefix+"Sum",typeSum);
            anaMap.put(keyPrefix+"Bl",typeBl);
            anaMap.put(keyPrefix+"Score",typeScore);
            anaMap.put("sumScore",sum);
        }
    }



    private List<Map<String,Object>> getDyScoreList(DyExcellentTeamXxcj condition,String tableName,Integer type){
        String sql="select team_name,sum(sum_score) as sum_score  from "+tableName+" where xn='"+condition.getXn()+"' and xq='"+condition.getXq()+"'";
        if(condition.getXqz()!=null){
            sql+=" and xqz="+condition.getXqz();
        }
        if(condition.getXqy()!=null){
            sql+=" and xqy="+condition.getXqy();
        }

        if(type!=null){
            sql+=" and type="+type;
        }
        sql+=" group by team_name";
        return basicSQLService.find(sql);
    }




    private Map<String,Object> findAnanysisObjByTeamName(List<Map<String,Object>> list,String teamName){
        for (Map<String,Object> map : list) {
            if(map.get("teamName").equals(teamName)){
                return map;
            }
        }
        return null;
    }


    private void sort(List<Map<String, Object>> anaList){
        Collections.sort(anaList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                //降序
                return ((BigDecimal)o2.get("sumScore")).compareTo(((BigDecimal)o1.get("sumScore")));
            }
        });
    }



}
