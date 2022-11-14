package platform.szxyzxx.resultsStatistical.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.resultsStatistical.dao.AnalysisStudensMapper;
import platform.szxyzxx.resultsStatistical.pojo.AnalysisStudens;
import platform.szxyzxx.resultsStatistical.pojo.vo.AsQuery;
import platform.szxyzxx.resultsStatistical.service.AnalysisStudensService;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AnalysisStudensServiceImpl implements AnalysisStudensService {
    @Autowired
    private AnalysisStudensMapper mapper;

    @Override
    public List<AnalysisStudens> findByAs(AsQuery asQuery) throws ParseException {
        List<AnalysisStudens> list=mapper.findByAs(asQuery);
        /*排名*/
        List<AnalysisStudens> list1=mapper.findByRank(asQuery);
        for(AnalysisStudens a:list){
           Integer r= findByRank(list1,a);
           if(r!=null){
               a.setRank(r);
           }
        }

        Date d = new Date();
               System.out.println(d);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        /*获取设置创建时间*/
        for(AnalysisStudens a1:list){
            String dateNowStr =sdf.format(a1.getCreateDate());
            asQuery.setCreateDate(sdf.parse(dateNowStr));
        }

        AnalysisStudens analysisStudens=mapper.findByDate(asQuery);
        if(analysisStudens==null){
            for(AnalysisStudens a2:list){
                a2.setRanking(0);
                }
            return list;
        }else {
            String ddd =sdf.format(analysisStudens.getCreateDate());
            asQuery.setCreateDate(sdf.parse(ddd));
            System.out.println(ddd);
            List<AnalysisStudens> list2=mapper.findByRanking(asQuery);
            /*比较2次考试的排名*/
            for(AnalysisStudens a2:list){
                Integer r=findByRanking(list2,a2);
                if(r!=null){
                    if(r>=0){
                        a2.setRanking(+r);
                    }else{
                        a2.setRanking(r);
                    }
                }
            }
            return list;
        }

    }

    public Integer findByRank(List<AnalysisStudens> list1,AnalysisStudens analysisStudens){
        for(AnalysisStudens b:list1){
            if(b.getExamName().equals(analysisStudens.getExamName()) & b.getGradeId()==analysisStudens.getGradeId()
            & b.getTeamId()==analysisStudens.getTeamId() & b.getSubjectCode().equals(analysisStudens.getSubjectName())
            & b.getXn().equals(analysisStudens.getXn()) & b.getXq().equals(analysisStudens.getXq())) {
                return b.getRank();
            }
        }
        return null;
    }
    /*比较排名*/
    public Integer findByRanking(List<AnalysisStudens> list2,AnalysisStudens analysisStudens){
        for(AnalysisStudens b:list2){
            if(b.getExamName().equals(analysisStudens.getExamName()) & b.getGradeId()==analysisStudens.getGradeId()
                    & b.getTeamId()==analysisStudens.getTeamId() & b.getSubjectCode().equals(analysisStudens.getSubjectName())
                    & b.getXn().equals(analysisStudens.getXn()) & b.getXq().equals(analysisStudens.getXq())
            & b.getStudentId()==analysisStudens.getStudentId()) {
                int sum=b.getRank()-analysisStudens.getRank();
                return sum;
            }
        }
        return null;
    }
}
