package platform.education.rest.basic.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.rest.basic.service.TermRestService;
import platform.education.rest.basic.service.vo.MonthInfo;
import platform.education.rest.basic.service.vo.SchoolTermInfo;
import platform.education.rest.basic.service.vo.SchoolYearInfo;
import platform.education.rest.basic.service.vo.SchoolYearVo;
import platform.education.rest.basic.service.vo.WeekInfo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;

public class TermRestServiceImpl implements TermRestService{
	
	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;

	@Override
	public Object getAllSchoolYear(Integer schoolId){
		SchoolYearInfo schoolYearInfo = null;
		List<SchoolYearInfo> schoolYearInfoList = new ArrayList<SchoolYearInfo>();
		try{
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(schoolYearList != null && schoolYearList.size() > 0){
				for(SchoolYear schoolYear : schoolYearList){
					if(schoolYear != null && schoolYear.getId() != null){
						schoolYearInfo = new SchoolYearInfo();
						schoolYearInfo.setName(schoolYear.getName());
						schoolYearInfo.setSchoolYear(schoolYear.getYear());
						if(schoolTermCurrent != null){
							if(schoolTermCurrent.getSchoolYearId().equals(schoolYear.getId())){
								schoolYearInfo.setIsCurrent("1");
							}else{
								schoolYearInfo.setIsCurrent("0");
							}
						}else{
							schoolYearInfo.setIsCurrent("0");
						}
						schoolYearInfoList.add(schoolYearInfo);
					}
				}
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("根据schoolId查询不到数据");
				info.setMsg("查询结果为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数异常");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}
		return new ResponseVo<List<SchoolYearInfo>>("0", schoolYearInfoList);
	}

	@Override
	public Object getAllSchoolTerm(Integer schoolId) {
		
		List<SchoolYearVo> list = null;
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			list = new ArrayList<SchoolYearVo>();
			List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
			sortYear(schoolYearList);
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			
			if(schoolYearList != null && schoolYearList.size() > 0){
				for(SchoolYear schoolYear : schoolYearList){
					if(schoolYear != null && schoolYear.getId() != null){
						SchoolYearVo schoolYearVo = new SchoolYearVo();
						schoolYearVo.setSchoolYear(schoolYear.getYear());
						schoolYearVo.setSchoolYearName(schoolYear.getName());
						Integer number = Integer.valueOf(schoolYear.getYear().substring(2));
						String n1 = "";
						String n2 = "";
						if(number < 9){
							n1 = 0+String.valueOf(number);
							n2 = 0+String.valueOf(number+1);
						}else if(number == 9){
							n1 = 0+String.valueOf(number);
							n2 = String.valueOf(number+1);
						}else if(number == 99){
							n1 = String.valueOf(number);
							n2 ="00";
						}else{
							n1 = String.valueOf(number);
							n2 = String.valueOf(number+1);
						}
						schoolYearVo.setYearName(n1+"-"+n2+"学年");
						
						List<SchoolTermInfo> schoolTermInfoList = new ArrayList<SchoolTermInfo>();
						List<SchoolTerm> schoolTermList = schoolTermService.getSchoolTermOfYear(schoolId, schoolYear.getId());
						sortTerm(schoolTermList);
						if(schoolTermList != null && schoolTermList.size() > 0){
							for(SchoolTerm schoolTerm : schoolTermList){
								
								SchoolTermInfo schoolTermInfo = new SchoolTermInfo();
								schoolTermInfo.setSchoolTermCode(schoolTerm.getCode());
								schoolTermInfo.setSchoolTermName(schoolTerm.getName());
								schoolTermInfo.setBeginDate(sd.format(schoolTerm.getBeginDate()));
								schoolTermInfo.setFinishDate(sd.format(schoolTerm.getFinishDate()));
								
								Integer tn = Integer.valueOf(schoolTerm.getGbCode());
								String termName = "";
								if(tn == 1){
									termName = "上学期";
								}else if(tn == 2){
									termName = "下学期";
								}else if(tn == 3){
									termName = "夏季学期";
								}else if(tn == 5){
									termName = "寒假";
								}else if(tn == 6){
									termName = "暑假";
								}
								schoolTermInfo.setTermName(termName);
								schoolTermInfo.setFullName(n1+"学年"+schoolTermInfo.getTermName());
								
								if(schoolTermCurrent != null){
									if(schoolTermCurrent.getSchoolTermCode().equals(schoolTerm.getCode())){
										schoolTermInfo.setIsCurrent("1");
									}else{
										schoolTermInfo.setIsCurrent("0");
									}
								}else{
									schoolTermInfo.setIsCurrent("0");
								}
								schoolTermInfoList.add(schoolTermInfo);
							}
						}
						schoolYearVo.setSchoolTermlist(schoolTermInfoList);
						list.add(schoolYearVo);
					}
				}
				return new ResponseVo<List<SchoolYearVo>>("0", list);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("根据schoolId查询不到数据");
				info.setMsg("查询结果为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数异常");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}

	}

	@Override
	public Object getAllSchoolTerm(Integer schoolId, String jsonpCallback) {
		
		List<SchoolYearVo> list = null;
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			
			list = new ArrayList<SchoolYearVo>();
			List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
			sortYear(schoolYearList);
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			
			if(schoolYearList != null && schoolYearList.size() > 0){
				for(SchoolYear schoolYear : schoolYearList){
					if(schoolYear != null && schoolYear.getId() != null){
						SchoolYearVo schoolYearVo = new SchoolYearVo();
						schoolYearVo.setSchoolYear(schoolYear.getYear());
						schoolYearVo.setSchoolYearName(schoolYear.getName());
						Integer number = Integer.valueOf(schoolYear.getYear().substring(2));
						String n1 = "";
						String n2 = "";
						if(number < 9){
							n1 = 0+String.valueOf(number);
							n2 = 0+String.valueOf(number+1);
						}else if(number == 9){
							n1 = 0+String.valueOf(number);
							n2 = String.valueOf(number+1);
						}else if(number == 99){
							n1 = String.valueOf(number);
							n2 ="00";
						}else{
							n1 = String.valueOf(number);
							n2 = String.valueOf(number+1);
						}
						schoolYearVo.setYearName(n1+"-"+n2+"学年");
						
						List<SchoolTermInfo> schoolTermInfoList = new ArrayList<SchoolTermInfo>();
						List<SchoolTerm> schoolTermList = schoolTermService.getSchoolTermOfYear(schoolId, schoolYear.getId());
						sortTerm(schoolTermList);
						if(schoolTermList != null && schoolTermList.size() > 0){
							for(SchoolTerm schoolTerm : schoolTermList){
								
								SchoolTermInfo schoolTermInfo = new SchoolTermInfo();
								schoolTermInfo.setSchoolTermCode(schoolTerm.getCode());
								schoolTermInfo.setSchoolTermName(schoolTerm.getName());
								schoolTermInfo.setBeginDate(sd.format(schoolTerm.getBeginDate()));
								schoolTermInfo.setFinishDate(sd.format(schoolTerm.getFinishDate()));
								
								Integer tn = Integer.valueOf(schoolTerm.getGbCode());
								String termName = "";
								if(tn == 1){
									termName = "上学期";
								}else if(tn == 2){
									termName = "下学期";
								}else if(tn == 3){
									termName = "夏季学期";
								}else if(tn == 5){
									termName = "寒假";
								}else if(tn == 6){
									termName = "暑假";
								}
								schoolTermInfo.setTermName(termName);
								schoolTermInfo.setFullName(n1+"学年"+schoolTermInfo.getTermName());
								
								if(schoolTermCurrent != null){
									if(schoolTermCurrent.getSchoolTermCode().equals(schoolTerm.getCode())){
										schoolTermInfo.setIsCurrent("1");
									}else{
										schoolTermInfo.setIsCurrent("0");
									}
								}else{
									schoolTermInfo.setIsCurrent("0");
								}
								schoolTermInfoList.add(schoolTermInfo);
							}
						}
						schoolYearVo.setSchoolTermlist(schoolTermInfoList);
						list.add(schoolYearVo);
					}
				}
				String json = JSON.toJSONString(list);
				return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("根据schoolId查询不到数据");
				info.setMsg("查询结果为空");
				info.setParam("schoolId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.D$DATA_NOT_FOUND, info) + ")";
			}
			
		}catch(NumberFormatException e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数异常");
			info.setMsg("schoolId参数异常");
			info.setParam("schoolId");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";
		}
	}

	@Override
	public Object getWeek(String beginDate, String endDate) {
		try{
			if (beginDate == null || "".equals(beginDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (endDate == null || "".equals(endDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			List<WeekInfo> weekList = new ArrayList<WeekInfo>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bd = sdf.parse(beginDate);
			Date ed = sdf.parse(endDate);
			Date today = new Date();
			Boolean flag = false;
			if(bd.getTime() <= today.getTime() && today.getTime() < (ed.getTime()+86400000)){
				flag = true;
			}
			
			int day = (int) ((ed.getTime()-bd.getTime())/86400000)+1;
			int week = 0;
			int first = bd.getDay();
			if(day >= 0){
				if(day > (7-first)){
					if((day-(7-first))%7 != 0){
						week = (day-(7-first))/7 + 2;
					}else{
						week = (day-(7-first))/7 + 1;
					}
				}else{
					week = 1;
				}
				
				Date week1 = new Date();
				Date week2 = new Date();
				for(int i=1; i<=week; i++){
					if(i == 1){
						week1 = bd;
						week2.setTime(bd.getTime() + (7-first-1)*86400000);
					}else if(i < week){
						week1.setTime(week2.getTime() + 86400000);
						week2.setTime(week1.getTime() +6*86400000);
					}else{
						week1.setTime(week2.getTime() + 86400000);
						week2 = ed;
					}
					
					WeekInfo weekInfo = new WeekInfo();
					weekInfo.setPeriodCode(String.valueOf(i));
					weekInfo.setName("第"+i+"周");
					weekInfo.setBeginDate(sdf.format(week1));
					weekInfo.setEndDate(sdf.format(week2));
					if(flag){
						if(week1.getTime() <= today.getTime() && today.getTime() < (week2.getTime()+86400000)){
							weekInfo.setIsCurrent(1);
						}else{
							weekInfo.setIsCurrent(0);
						}
					}else{
						weekInfo.setIsCurrent(0);
					}
					
					weekList.add(weekInfo);
				}
				return new ResponseVo<List<WeekInfo>>("0", weekList);
				
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据不正确");
				info.setMsg("输入的数据不正确");
				info.setParam("beginDate,endDate");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}
	
	public void sortYear(List<SchoolYear> yearList){
		Collections.sort(yearList, new Comparator<SchoolYear>(){  
			public int compare(SchoolYear d1, SchoolYear d2) {  
				Integer n1 = Integer.parseInt(d1.getYear());
				Integer n2 = Integer.parseInt(d2.getYear());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}
	
	public void sortTerm(List<SchoolTerm> termList){
		Collections.sort(termList, new Comparator<SchoolTerm>(){  
			public int compare(SchoolTerm d1, SchoolTerm d2) {  
				Integer n1 = Integer.parseInt(d1.getGbCode());
				Integer n2 = Integer.parseInt(d2.getGbCode());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}

	@Override
	public Object getMonth(String beginDate, String endDate) {
		
		List<MonthInfo> monthList = null;
		try {
			if (beginDate == null || "".equals(beginDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (endDate == null || "".equals(endDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] months = {"一月份", "二月份", "三月份", "四月份", "五月份", "六月份", "七月份", "八月份", "九月份", "十月份", "十一月份", "十二月份"};
			Date bd = sdf.parse(beginDate);
			Date ed = sdf.parse(endDate);
			Date today = new Date();
			long time = today.getTime();
			int by = bd.getYear()+1900;
			int ey = ed.getYear()+1900;
			int bm = bd.getMonth()+1;
			int em = ed.getMonth()+1;
			
			if(bd.getTime() > ed.getTime()){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据不正确");
				info.setMsg("输入的数据不正确");
				info.setParam("beginDate,endDate");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			Boolean flag = false;
			if(bd.getTime() <= time && (ed.getTime()+86400000) > time){
				flag = true;
			}
			
			monthList = new ArrayList<MonthInfo>();
			if(ey == by){
				for(int i=bm; i<=em; i++){
					MonthInfo month = getMonthInfo(months, i, by, flag, time);
					if(i == bm){
						month.setBeginDate(beginDate);
					}
					if(i == em){
						month.setEndDate(endDate);
					}
					monthList.add(month);
				}
			}else if(ey > by){
				for(int i=bm; i<=12; i++){
					MonthInfo month = getMonthInfo(months, i, by, flag, time);
					if(i == bm){
						month.setBeginDate(beginDate);
					}
					monthList.add(month);
				}
				if(ey-by > 1){
					for(int j=by+1; j<ey; j++){
						for(int i=1; i<=12; i++){
							MonthInfo month = getMonthInfo(months, i, j, flag, time);
							monthList.add(month);
						}
					}
				}
				for(int i=1; i<=em; i++){
					MonthInfo month = getMonthInfo(months, i, ey, flag, time);
					if(i == em){
						month.setEndDate(endDate);
					}
					monthList.add(month);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		return new ResponseVo<List<MonthInfo>>("0", monthList);
	}
	
	public MonthInfo getMonthInfo(String[] months, int i, int by, Boolean flag, long time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MonthInfo month = new MonthInfo();
		month.setName(months[i-1]);
		if(i<10){
			month.setDate(by+"-0"+i);
		}else{
			month.setDate(by+"-"+i);
		}
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, by);
        cal1.set(Calendar.MONTH, i-1);
        cal1.set(Calendar.DAY_OF_MONTH,cal1.getMinimum(Calendar.DATE));
        String begin = sdf.format(cal1.getTime());
        
        Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, by);
		cal2.set(Calendar.MONTH, i-1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DATE));
		String end = sdf.format(cal2.getTime());
		
		month.setBeginDate(begin);
		month.setEndDate(end);
		
		if(flag){
			if(sdf.parse(begin).getTime() <= time && (sdf.parse(end).getTime()+86400000) > time){
				month.setIsCurrent(1);
			}else{
				month.setIsCurrent(0);
			}
		}else{
			month.setIsCurrent(0);
		}
		return month;
	}


	@Override
	public Object getSchoolTerm(Integer schoolId) {
		if(schoolId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("schoolId参数必填");
			info.setMsg("schoolId参数不能为空");
			info.setParam("schoolId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
		}

		List<Object> list = new ArrayList<>();
		try {
			List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			String termCode = "";
			String year = "";
			if (schoolTermCurrent != null) {
				termCode = schoolTermCurrent.getSchoolTermCode();
				year = schoolTermCurrent.getSchoolYear();
			}
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			if(schoolYearList != null && schoolYearList.size() > 0){
				for (SchoolYear schoolYear : schoolYearList) {
					List<Object> termList = new ArrayList<>();
					List<SchoolTerm> schoolTermList = schoolTermService.getSchoolTermOfYear(schoolId, schoolYear.getId());
					if(schoolTermList != null && schoolTermList.size() > 0){
						for (SchoolTerm schoolTerm : schoolTermList) {
							Map<String, Object> termMap = new HashMap<>();
							termMap.put("termCode", schoolTerm.getCode());
							termMap.put("termName", schoolTerm.getName());
							termMap.put("beginDate", sd.format(schoolTerm.getBeginDate()));
							termMap.put("endDate", sd.format(schoolTerm.getFinishDate()));
							if (termCode.equals(schoolTerm.getCode())) {
								termMap.put("isCurrent", "1");
							} else {
								termMap.put("isCurrent", "0");
							}
							termList.add(termMap);
						}
					}

					Map<String, Object> yearMap = new HashMap<>();
					yearMap.put("schoolYear", schoolYear.getYear());
					yearMap.put("schoolYearName", schoolYear.getName());
					yearMap.put("termList", termList);
					if (year.equals(schoolYear.getYear())) {
						yearMap.put("isCurrent", "1");
					} else {
						yearMap.put("isCurrent", "0");
					}
					list.add(yearMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, new ResponseInfo("接口调用异常", "接口调用异常", ""));
		}
		return new ResponseVo<Object>("0", list);
	}

}
