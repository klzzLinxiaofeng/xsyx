///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.vo.quizToolVo;
//
//import com.gzxtjy.lads.entities.LadsQuizResultTool;
//import java.util.List;
//
///**
// *
// * @author Administrator
// */
//public class LadsQuizUserResultVo implements Comparable{
//
//    private String quizId;
//    private String quizTitle;
//    private String realName;
//    private List<LadsQuizResultTool> resultList;
//
//    public String getQuizId() {
//        return quizId;
//    }
//
//    public void setQuizId(String quizId) {
//        this.quizId = quizId;
//    }
//
//    public String getRealName() {
//        return realName;
//    }
//
//    public void setRealName(String realName) {
//        this.realName = realName;
//    }
//
//    public String getQuizTitle() {
//        return quizTitle;
//    }
//
//    public void setQuizTitle(String quizTitle) {
//        this.quizTitle = quizTitle;
//    }
//
//    public List<LadsQuizResultTool> getResultList() {
//        return resultList;
//    }
//
//    public void setResultList(List<LadsQuizResultTool> resultList) {
//        this.resultList = resultList;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        LadsQuizUserResultVo vo = (LadsQuizUserResultVo) o;
//        if(vo.getResultList()!=null&&vo.getResultList().size()>0){
//            return 1;
//        }else{
//            return -1;
//        }
//    }
//}
