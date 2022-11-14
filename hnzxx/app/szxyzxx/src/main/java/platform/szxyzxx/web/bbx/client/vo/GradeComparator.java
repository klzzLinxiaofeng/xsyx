package platform.szxyzxx.web.bbx.client.vo;

import platform.szxyzxx.web.bbx.vo.BBXTeamTeacherVo;


/**
 * 班班星  手机端 年级下拉框排序 
 * @author huangyanchun
 *
 */

public class GradeComparator {



	
	public static java.util.Comparator getComparator() {   
	    return new java.util.Comparator() {   
	  
	      public int compare(Object o1, Object o2) {   
	        if (o1 instanceof String) {   
	          return compare( (String) o1, (String) o2);   
	        }else if (o1 instanceof Integer) {   
	          return compare( (Integer) o1, (Integer) o2);   
	        }else if (o1 instanceof GradeClientVo) {   
	        return compare( (GradeClientVo) o1, (GradeClientVo) o2);   
	        }else if (o1 instanceof BBXTeamTeacherVo){
	        	return compare((BBXTeamTeacherVo)o1, (BBXTeamTeacherVo) o2);
	        }else {   
	          System.err.println("未找到合适的比较器");   
	          return 1;
	        }   
	      }   
	  
	      public int compare(String o1, String o2) {   
	     
	        Integer val1 = Integer.valueOf(o1);   
	        Integer val2 = Integer.valueOf(o2);   
	     /*
	      * System.out.println("s1=="+s1+"                s2=="+s2); 
	      * 取出数组相邻的两个学段
	      */  
	        return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1)); 
	      }   
	  
	      public int compare(Integer o1, Integer o2) {   
	          
	        int val1 = o1.intValue();   
	        int val2 = o2.intValue();   
	        
	        /*
	         * System.out.println("val1=="+val1+"                val2=="+val2); 
	         * 取出数组相邻的两个年级
	         */  
	        return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));   
	  
	      }   
	     
	  
	      public int compare(GradeClientVo o1, GradeClientVo o2) {   
	        
	    	String stageCode1 = o1.getStageCode();
	    	String stageCode2 = o2.getStageCode();
	    	
	    	Integer gradeNumber1 = o1.getGradeNumber();
	    	Integer gradeNumber2 = o2.getGradeNumber();
	        
	        /*return 逻辑解释如下：
	         * 谁的学段低，谁就排前面
	         * 谁的年级低，谁就排前面
	         
	         */
	    	 return (compare(stageCode1, stageCode2) == 0 ?(compare(gradeNumber1, gradeNumber2) == 0 ?0 :  
	              compare(gradeNumber1, gradeNumber2)) : compare(stageCode1, stageCode2));   
	    	
	    	
	    	
	  
	      }  
	      
	      
	      public int compare(BBXTeamTeacherVo o1, BBXTeamTeacherVo o2) {   
		    	String stageCode1 = o1.getStageCode();
		    	String stageCode2 = o2.getStageCode();
		    	
		    	Integer gradeNumber1 = o1.getGradeNumber();
		    	Integer gradeNumber2 = o2.getGradeNumber();
		    	
		    	Integer teamNumber1 = o1.getTeamNumber();
		    	Integer teamNumber2 = o2.getTeamNumber();
		    	
		        
		        /*return 逻辑解释如下：
		         * 谁的学段低，谁就排前面
		         * 谁的年级低，谁就排前面
		         * 谁的班级低，谁就排前面
		         
		         */
		    	
		    	
		    	
				return compare(stageCode1, stageCode2) == 0 ? (compare(
						gradeNumber1, gradeNumber2) == 0 ? (compare(
						teamNumber1, teamNumber2) == 0 ? 0 : compare(
						teamNumber1, teamNumber2)) : compare(gradeNumber1,
						gradeNumber2)) : compare(stageCode1, stageCode2);
		  
		      } 
	      
	      
	  
	    };   
	}   

	
}
