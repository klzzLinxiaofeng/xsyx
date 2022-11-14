package platform.education.commonResource.serivce.unit;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Integer> {  
	  
    Map<Integer, Object> base;  
    public ValueComparator(Map<Integer, Object> base) {  
        this.base = base;  
    }  
  
    public int compare(Integer a, Integer b) {  
    	if(base.get(a) instanceof Float){
    		if ((Float)base.get(a) >= (Float)base.get(b)) {  
    			return -1;  
    		} else {  
    			return 1;  
    		} // returning 0 would merge keys  
    	}else{
    		if ((Double)base.get(a) >= (Double)base.get(b)) {  
    			return -1;  
    		} else {  
    			return 1;  
    		} // returning 0 would merge keys  
    	}
    }  
}
