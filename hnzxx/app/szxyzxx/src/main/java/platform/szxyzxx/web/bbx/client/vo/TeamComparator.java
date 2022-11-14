package platform.szxyzxx.web.bbx.client.vo;


/**
 * 班班星，手机端班级下拉框排序
 * @author huangyanchun
 *
 */
public class TeamComparator {





	
	public static java.util.Comparator getComparator() {   
	    return new java.util.Comparator() {   
	  
	      public int compare(Object o1, Object o2) {   
	         if (o1 instanceof Integer) {   
	          return compare( (Integer) o1, (Integer) o2);   
	        }else if (o1 instanceof TeamClientVo) {   
	        return compare( (TeamClientVo) o1, (TeamClientVo) o2);   
	        }else {   
	          System.err.println("未找到合适的比较器");   
	          return 1;
	        }   
	      }   
	  
	  
	      public int compare(Integer o1, Integer o2) {   
	          
	        int val1 = o1.intValue();   
	        int val2 = o2.intValue();   
	        
	        /*
	         * System.out.println("val1=="+val1+"                val2=="+val2); 
	         * 取出数组相邻的两个班级
	         */  
	        return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));   
	  
	      }   
	     
	  
	      public int compare(TeamClientVo o1, TeamClientVo o2) {   
	        
	    	Integer teamNumber1 = o1.getTeamNumber();
	    	Integer teamNumber2 = o2.getTeamNumber();
	        
	        /*return 逻辑解释如下：
	         * 谁的班级低，谁就排前面
	         */
	    	 return (compare(teamNumber1, teamNumber2) == 0 ?0 : compare(teamNumber1, teamNumber2));   
	    	
	    	
	    	
	  
	      }   
	  
	    };   
	}   

	

}
