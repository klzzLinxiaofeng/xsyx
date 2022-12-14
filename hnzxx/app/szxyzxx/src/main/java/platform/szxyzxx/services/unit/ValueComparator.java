package platform.szxyzxx.services.unit;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Integer> {  
	  
    Map<Integer, Object> base;  
    public ValueComparator(Map<Integer, Object> base) {  
        this.base = base;  
    }  
  
    // Note: this comparator imposes orderings that are inconsistent with equals.      
    public int compare(Integer a, Integer b) {  
    	if(base.get(a) instanceof Float){
    		if ((float)base.get(a) >= (float)base.get(b)) {  
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