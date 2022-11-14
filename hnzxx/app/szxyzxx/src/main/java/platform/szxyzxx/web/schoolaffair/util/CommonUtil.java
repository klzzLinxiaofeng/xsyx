package platform.szxyzxx.web.schoolaffair.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.service.DormitoryService;
import platform.education.school.affair.service.FloorService;
import platform.education.school.affair.vo.DormitoryCondition;
import platform.education.school.affair.vo.DormitoryVo;
import platform.szxyzxx.web.schoolaffair.vo.DistinctDormitoryVo;

public class CommonUtil {
	
	
	
	public static List<DistinctDormitoryVo>distnctList(FloorService floorService,DormitoryService dormitoryService,DormitoryCondition condition){
		
		List<Dormitory> dormitory = dormitoryService
				.findDistinct(condition);
		Map<String,DistinctDormitoryVo>distincMaps = new HashMap();
		
		if(dormitory.size()>0){
			DistinctDormitoryVo ddv = null;	
			for(Dormitory d: dormitory){
				Floor f = floorService.findFloorByCode(d.getSchoolId(), d.getFloorCode());
				if(f!=null){
					ddv = new DistinctDormitoryVo();
					ddv.setFloorCode(f.getCode());
					ddv.setFloorName(f.getName());
					distincMaps.put(d.getFloorCode(), ddv);
					
				}
			}
		}
		
		return new ArrayList<DistinctDormitoryVo>(distincMaps.values());     
	}
	
	
public static List<DistinctDormitoryVo>uniquetList(FloorService floorService,DormitoryService dormitoryService,DormitoryCondition condition){
		
		List<Dormitory> dormitory = dormitoryService.findDormitoryUnique(condition);
				
		Map<String,DistinctDormitoryVo>uniqueMaps = new HashMap();
		if(dormitory.size()>0){
			DistinctDormitoryVo ddv = null;	
			for(Dormitory d: dormitory){
				Floor f = floorService.findFloorByCode(d.getSchoolId(), d.getFloorCode());
				if(f!=null){
					ddv = new DistinctDormitoryVo();
					ddv.setFloorCode(f.getCode());
					ddv.setFloorName(f.getName());
					uniqueMaps.put(d.getFloorCode(), ddv);
					
				}
			}
		}
		
		return new ArrayList<DistinctDormitoryVo>(uniqueMaps.values());    
	}
	

}
