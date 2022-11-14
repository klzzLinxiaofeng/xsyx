package platform.education.generalTeachingAffair.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.vo.scoreAnalysis.ScoreSortVo;


/**
 * @功能描述:成绩分析工具类 
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年2月11日下午12:36:22
 */
public class ScoreAnalysisUtil {
	
	//单例
	 private volatile static ScoreAnalysisUtil instance;
	    private ScoreAnalysisUtil(){
	        System.out.println("Singleton has loaded");
	    }
	    public static ScoreAnalysisUtil getInstance(){
	        if(instance==null){
	            synchronized (ScoreAnalysisUtil.class){
	                if(instance==null){
	                    instance=new ScoreAnalysisUtil();
	                }
	            }
	        }
	        return instance;
	    }
	
	/**
	 * 根据得分获取班级排名
	 * @param list 
	 * @return
	 */
	public  List<ScoreSortVo> getRankByScore(List<ScoreSortVo> list) {

		 Comparator<ScoreSortVo> comparator=new Comparator<ScoreSortVo>() {
	            @Override
	            public int compare(ScoreSortVo o1, ScoreSortVo o2) {
	                if(o1.getScore()<o2.getScore()){
	                    return 1;
	                }else if(o1.getScore()==o2.getScore()){
	                    return 0;
	                }else{
	                    return -1;
	                }
	            }
	        };
	        Collections.sort(list,comparator); //JDK 1.7
	       // list.sort(comparator);//JDK 1.8
	        for(int i=0,s=list.size();i<s;i++){
	            if(i>0 && list.get(i).getScore()==list.get(i-1).getScore()){
	            	list.get(i).setTeamRank(list.get(i-1).getTeamRank());
	            }else{
	            	list.get(i).setTeamRank(i+1);
	            }
	        }
		
		return list;
		
	}

	
	public Map<String,Object> getAvergeScoreByScore(List<ScoreSortVo> list,float highScore,float lowScore,float passScore) {
		Map<String,Object> scoreMap = null;
		if(list != null && list.size() > 0) {
			float totalScore = 0f;
			float highestScore = list.get(0).getScore();
			float lowestScore = list.get(0).getScore();
			scoreMap = new HashMap<String,Object>();
			int highCount = 0;
			int lowCount = 0;
			int passCount = 0;
			for(ScoreSortVo scoreSortVo:list) {
				float score = scoreSortVo.getScore();
				totalScore += score;
				if(score > highestScore) {
					// 判断最大值
					highestScore = score  ;
				}     
				if(score < lowestScore) {
					// 判断最小值 
					lowestScore = score;  
				}   
				
				if (score >= highScore) {
					highCount = highCount + 1;
				}
				if (score >= lowScore) {
					lowCount = lowCount + 1;
				}
				if (score >= passScore) {
					passCount = passCount + 1;
				}
				
			}
			
			
			float avergeScore = totalScore / list.size();
			scoreMap.put("totalScore", totalScore);
			scoreMap.put("avergeScore", avergeScore);
			
			scoreMap.put("highestScore", highestScore);
			scoreMap.put("lowestScore", lowestScore);
			
			scoreMap.put("highCount", highCount);
			scoreMap.put("lowCount", lowCount);
			scoreMap.put("passCount", passCount);
			
			
		}
		return scoreMap;
	}
	
	/**
	 * 更新全科总分班级排名
	 * @param list
	 * @return
	 */
	
	public List<ExamStatMajorStudent> getRankByTotalScore(List<ExamStatMajorStudent> list,String type) {
		Comparator<ExamStatMajorStudent> comparator=new Comparator<ExamStatMajorStudent>() {
			@Override
			public int compare(ExamStatMajorStudent o1, ExamStatMajorStudent o2) {
					if(o1.getTotalScore()<o2.getTotalScore()){
						return 1;
					}else if(o1.getTotalScore()==o2.getTotalScore()){
						return  0;
					}else {
						return -1;
					}
			}
		};
		Collections.sort(list,comparator); //JDK 1.7
		if("TEAMRANK".equals(type)) {
			// list.sort(comparator);//JDK 1.8
			for(int i=0,s=list.size();i<s;i++){
				if(i>0 && list.get(i).getTotalScore()==list.get(i-1).getTotalScore()){
					list.get(i).setTeamRank(list.get(i-1).getTeamRank());
				}else{
					list.get(i).setTeamRank(i+1);
				}
			}
		}else {
			for(int i=0,s=list.size();i<s;i++){
				if(i>0 && list.get(i).getTotalScore()==list.get(i-1).getTotalScore()){
					list.get(i).setGradeRank(list.get(i-1).getGradeRank());
				}else{
					list.get(i).setGradeRank(i+1);
				}
			}
		}
		
		return list;
		
	}
	
}
