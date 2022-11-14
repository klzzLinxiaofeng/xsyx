package platform.education.commonResource.web.common.ftlUtil.dayTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.OperationWeekcountService;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.service.ResourceOperationService;
import platform.education.resource.service.ResourceService;

public class SqlTask {
	
	private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");  
	private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
    @Qualifier("resourceLibraryService")
    protected ResourceLibraryService resourceLibraryService;
	
	@Autowired
	@Qualifier("operationWeekcountService")
	private OperationWeekcountService operationWeekcountService;
	
	@Autowired
	@Qualifier("resourceOperationService")
	private ResourceOperationService resourceOperationService;
	
	@Autowired
    @Qualifier("resourceService")
    protected ResourceService resourceService;
	
	public void process(){
		System.out.println("===========开始数据处理=========");
		List<ResourceLibrary> libraryUuidList = this.resourceLibraryService.findResourceLibraryByCondition(null);
		for(ResourceLibrary library : libraryUuidList){
			this.resourceOperationService.createMicroOperationWeekCount(getCurrentWeekDayStartTime(), library.getUuid());
			this.resourceOperationService.createResOperationWeekCount(getCurrentWeekDayStartTime(), library.getUuid());
		}
		this.operationWeekcountService.deleteByDate(getCurrentDayStartTime());
		System.out.println("===========数据处理完毕=========");
	}
	
	/** 
     * 获得本周的第一天，周一 
     *  
     * @return 
     */  
    public static Date getCurrentWeekDayStartTime() {  
        Calendar c = Calendar.getInstance();  
        try {  
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;  
            c.add(Calendar.DATE, -weekday);  
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return c.getTime();  
    }  
    
    /** 
     * 获得本天的开始时间，即2012-01-01 00:00:00 
     *  
     * @return 
     */  
    public static Date getCurrentDayStartTime() {  
        Date now = new Date();  
        try {  
            now = shortSdf.parse(shortSdf.format(now));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;  
    } 
}
