package platform.szxyzxx.dy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.dy.dao.DyExcellentTmMapper;
import platform.szxyzxx.dy.pojo.DyExcellentTm;
import platform.szxyzxx.dy.service.DyExcellentTmService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DyExcellentTmImpl implements DyExcellentTmService {

    @Autowired
    private DyExcellentTmMapper mapper;
    @Autowired
    private BasicSQLService basicSQLService;

    private BigDecimal dyBl=new BigDecimal("0.7");
    private BigDecimal applyBl=new BigDecimal("0.1");

    @Override
    public boolean add(DyExcellentTm d) {
        d.setCreateTime(new Date());
        return mapper.createSelective(d)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return mapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean update(DyExcellentTm d) {
        return mapper.updateByPrimaryKeySelective(d)>0;
    }

    @Override
    public DyExcellentTm findById(Integer id) {
        return mapper.findByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> getAll(DyExcellentTm d) {
        StringBuilder sql=new StringBuilder("select * from dy_excellent_tm where year='").append(d.getYear()).append("'");
        sql.append("and xq='"+d.getXq()).append("'");

        if(d.getFirstState()!=null){
            sql.append(" and first_state=").append(d.getFirstState());
        }
        if(d.getFinalState()!=null){
            sql.append(" and final_state=").append(d.getFinalState());
        }
        if(d.getUserId()!=null){
            sql.append(" and user_id=").append(d.getUserId());
        }

        if(d.getSumScore()!=null ){
            if(d.getSumScore()==1) {
                sql.append(" and sum_score is  null");
            }else{
                sql.append(" and sum_score is not null");
            }
        }
        List<Map<String, Object>> applyList=basicSQLService.find(sql.toString());

        //因为需要连接多个一对多的表，所以不使用连表查询
        List<Map<String,Object>> yrcgList=getDyScoreList(d,"dy_excellent_team_yrcg",null);
        List<Map<String,Object>> xxcjList=getDyScoreList(d,"dy_excellent_team_xxcj",null);
        List<Map<String,Object>> njpjList=getDyScoreList(d,"dy_excellent_team_njpj",null);
        List<Map<String,Object>> dkjList=getDyScoreList(d,"dy_excellent_team_dkj",null);
        List<Map<String,Object>> dxhdList=getDyScoreList(d,"dy_excellent_team_dxhd",null);
        List<Map<String,Object>> grryList=getDyScoreList(d,"dy_excellent_team_ry",0);
        List<Map<String,Object>> jtryList=getDyScoreList(d,"dy_excellent_team_ry",1);

        compute(applyList,yrcgList,"yrcg");
        compute(applyList,xxcjList,"xxcj");
        compute(applyList,njpjList,"njpj");
        compute(applyList,dkjList,"dkj");
        compute(applyList,dxhdList,"dxhd");
        compute(applyList,grryList,"grry");
        compute(applyList,jtryList,"jtry");


        for (Map<String, Object> apply : applyList) {
            BigDecimal dyFinalScore=((BigDecimal)apply.get("dySumScore")).multiply(dyBl).setScale(2,BigDecimal.ROUND_DOWN);
            Object applyScore=apply.get("sum_score");
            if(applyScore==null){
                applyScore="0";
            }
            BigDecimal applyFinalScore=new BigDecimal(applyScore.toString()).multiply(applyBl).setScale(2,BigDecimal.ROUND_DOWN);
            BigDecimal finalScore=dyFinalScore.add(applyFinalScore);
            apply.put("dyFinalScore",dyFinalScore);
            apply.put("applyFinalScore",applyFinalScore);
            apply.put("finalScore",finalScore);
        }

        if(applyList.size()>1){
            Collections.sort(applyList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    //降序
                    return ((BigDecimal)o2.get("finalScore")).compareTo(((BigDecimal)o1.get("finalScore")));
                }
            });
        }

        return applyList;
    }


    private void compute(List<Map<String,Object>> applyList,List<Map<String,Object>> dyList,String keyPrefix){
        for (Map<String, Object> apply : applyList) {
            String teamName=(String) apply.get("user_team");
            BigDecimal typeSum=findScoreByTeamName(teamName,dyList);
            BigDecimal sum=(BigDecimal) apply.get("dySumScore");
            if(sum==null){
                sum=typeSum;
            }else{
                sum=sum.add(typeSum);
            }
            apply.put(keyPrefix+"Sum",typeSum);
            apply.put("dySumScore",sum);
        }
    }

    private BigDecimal findScoreByTeamName(String teamName,List<Map<String,Object>> dyList){
        for (Map<String, Object> map : dyList) {
            if(map.get("team_name").equals(teamName)){
                return new BigDecimal(map.get("sum_score").toString());
            }
        }
        return new BigDecimal("0");
    }



    private List<Map<String,Object>> getDyScoreList(DyExcellentTm condition, String tableName, Integer type){
        String sql="select team_name,sum(sum_score) as sum_score  from "+tableName+" where xn='"+condition.getYear()+"' and xq='"+condition.getXq()+"'";
        if(type!=null){
            sql+=" and type="+type;
        }
        if(condition.getUserTeam()!=null){
            sql+=" and team_name='"+condition.getUserTeam()+"'";
        }
        sql+=" group by team_name";
        return basicSQLService.find(sql);
    }

}
