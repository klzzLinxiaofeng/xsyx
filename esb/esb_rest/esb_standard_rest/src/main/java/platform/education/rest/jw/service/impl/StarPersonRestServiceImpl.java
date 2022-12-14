package platform.education.rest.jw.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.ApsMedal;
import platform.education.generalTeachingAffair.model.ApsStuSummary;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.StarPersonRestService;
import platform.education.rest.jw.service.constant.StarPersonConstants;
import platform.education.rest.jw.service.vo.StarPersonNumVo;
import platform.education.rest.jw.service.vo.StarPersonVo;

public class StarPersonRestServiceImpl implements StarPersonRestService{
	@Autowired
	@Qualifier("studentApsService")
	private StudentApsService studentApsService;
	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;
		

	
	@Override
	public Object batchsetStarReachCount(String datas,Integer schoolId) {
		try{
			List<EvaluationMedalData> evaluationMedalDatas=new ArrayList<EvaluationMedalData>();
			Integer schoolCount=0;
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId????????????");
				info.setMsg("schoolId??????????????????");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			if(datas == null || datas.equals("")){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("datas????????????");
				info.setMsg("datas??????????????????");
				info.setParam("datas");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) ;
			}
			JSONArray jsonArray = JSONArray.fromObject(datas);
			for(int i=0; i<jsonArray.size(); i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				EvaluationMedalData data=new EvaluationMedalData();
						
				StarPersonNumVo vo=	JSON.parseObject(jsonObject.toString(), StarPersonNumVo.class);
				if(vo.getGradeCount()==null)
				{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("gradeCount????????????");
					info.setMsg("gradeCount??????????????????");
					info.setParam("gradeCount");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				if(vo.getTeamCount()==null)
				{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("teamCount????????????");
					info.setMsg("teamCount??????????????????");
					info.setParam("teamCount");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				if(vo.getGradeId()==null){

					ResponseInfo info = new ResponseInfo();
					info.setDetail("gradeId????????????");
					info.setMsg("gradeId??????????????????");
					info.setParam("gradeId");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				if(vo.getSchoolCount()==null){

					ResponseInfo info = new ResponseInfo();
					info.setDetail("schoolCount????????????");
					info.setMsg("schoolCount??????????????????");
					info.setParam("schoolCount");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
				 schoolCount=vo.getSchoolCount();
				 data.setGradeId(vo.getGradeId());
				 data.setGradeCount(vo.getGradeCount());
				 data.setTeamCount(vo.getTeamCount());
				 
				evaluationMedalDatas.add(data);			
			}
			studentApsService.setSchoolStarReachCount(schoolId,schoolCount);
			this.studentApsService.batchsetStarReachCount(evaluationMedalDatas);
			return new ResponseVo<String>("0", "????????????");
			
		}catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("datas,schoolId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object batchgetStarReachCount(Integer schoolId, String termCode) {
		try{
			List<EvaluationMedalData> dates = new ArrayList<EvaluationMedalData>();
			if(schoolId == null || "".equals(schoolId)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId????????????");
				info.setMsg("schoolId??????????????????");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode????????????");
				info.setMsg("termCode??????????????????");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			ApsMedal apsMedal = this.studentApsService
					.getSchooltStarReachCount(schoolId);
			if(apsMedal==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("????????????????????????????????????");
				info.setMsg("????????????????????????????????????");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			int schoolnum = apsMedal.getReachCount();
			List<StarPersonNumVo> numVos=new ArrayList<StarPersonNumVo>();
			dates=this.studentApsService.batchgetStarReachCount(schoolId, termCode);
			if(dates!=null){
				for(EvaluationMedalData e :dates){
                  StarPersonNumVo vo=new StarPersonNumVo();
                  vo.setGradeId(e.getGradeId());
                  vo.setGradeName(e.getGradeName());
                  vo.setGradeCount(e.getGradeCount());
                  vo.setTeamCount(e.getTeamCount());
                  vo.setSchoolCount(schoolnum);
                  numVos.add(vo);
     			}
			}
			return new ResponseVo<List<StarPersonNumVo>>("0",numVos);
		}catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("schoolId, termCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}



	@Override
	public Object getEvaluateStar(String termCode, Integer objectId,
			String type, String checkDate,String schoolYear) {
		try{
			List<ApsStuSummary> starEvaluateDatas = new ArrayList<ApsStuSummary>();
			Date[] dates=new Date[2];
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear????????????");
				info.setMsg("schoolYear??????????????????");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode????????????");
				info.setMsg("termCode??????????????????");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(objectId == null ){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("objectId????????????");
				info.setMsg("objectId??????????????????");
				info.setParam("objectId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(type == null || "".equals(type)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("type????????????");
				info.setMsg("type??????????????????");
				info.setParam("type");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(type.equals("1")||type.equals("2")||type.equals("3")){
				
				if(checkDate == null || "".equals(checkDate)){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("checkDate????????????");
					info.setMsg("checkDate??????????????????");
					info.setParam("checkDate");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int flag=0;
			String periodCode="";
			String userType="";
			if(type.equals(StarPersonConstants.MONTH_TEAM)){
				userType=StarPersonConstants.TEAM;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.MONTH_GRADE)){
				userType=StarPersonConstants.GRADE;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.MONTH_SCHOOL)){
				userType=StarPersonConstants.SCHOOL;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.TERM_TEAM)){
				userType=StarPersonConstants.TEAM;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			if(type.equals(StarPersonConstants.TERM_GRADE)){
				userType=StarPersonConstants.GRADE;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			if(type.equals(StarPersonConstants.TERM_SCHOOL)){
				userType=StarPersonConstants.SCHOOL;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			starEvaluateDatas = this.studentApsService.getEvaluateStar(termCode, objectId, userType, dates[0], dates[1],periodCode);
			 List<StarPersonVo> personVos=new ArrayList<StarPersonVo>();
			 
			 if(starEvaluateDatas==null||starEvaluateDatas.size()==0){
				 starEvaluateDatas=	this.studentApsService.getPersonalStar(schoolYear,termCode, objectId, userType, dates[0], dates[1]);
					if(starEvaluateDatas!=null){
						
						for(ApsStuSummary apsStuSummary:starEvaluateDatas){
							 StarPersonVo vo=new StarPersonVo();
							 vo.setAddScore(apsStuSummary.getAddScore());
							 vo.setNormalScore(apsStuSummary.getNormalScore());
							 vo.setRank(apsStuSummary.getRank());
							 vo.setStudentName(apsStuSummary.getStudentName());
							 vo.setTeamName(apsStuSummary.getTeamName());
							 vo.setTotalScore(apsStuSummary.getTotalScore());
							 vo.setFlag(flag);
							 vo.setDate(null);
							 personVos.add(vo);
						}
					}
							
			 }else{
				 flag=1;

					for(ApsStuSummary apsStuSummary:starEvaluateDatas){
						 StarPersonVo vo=new StarPersonVo();
						 vo.setAddScore(apsStuSummary.getAddScore());
						 vo.setNormalScore(apsStuSummary.getNormalScore());
						 vo.setRank(apsStuSummary.getRank());
						 vo.setStudentName(apsStuSummary.getStudentName());
						 vo.setTeamName(apsStuSummary.getTeamName());
						 vo.setTotalScore(apsStuSummary.getTotalScore());
						 vo.setFlag(flag);
						 vo.setDate(sdf.format(apsStuSummary.getCreateDate()));
						 personVos.add(vo);
					}
					
			 }
			 return new ResponseVo<List<StarPersonVo>>("0", personVos);
			 
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("termCode,objectId,type,schoolYear");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
			
		}
	}

	@Override
	public Object evaluatePersonalStar(String schoolYear, String termCode,
			Integer objectId, String type,String checkDate
			) {
		try{
			
			Date[] dates=new Date[2];
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear????????????");
				info.setMsg("schoolYear??????????????????");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode????????????");
				info.setMsg("termCode??????????????????");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(objectId == null ){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("objectId????????????");
				info.setMsg("objectId??????????????????");
				info.setParam("objectId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(type == null || "".equals(type)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("type????????????");
				info.setMsg("type??????????????????");
				info.setParam("type");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
		  if(type.equals("1")||type.equals("2")||type.equals("3")){
				
				if(checkDate == null || "".equals(checkDate)){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("checkDate????????????");
					info.setMsg("checkDate??????????????????");
					info.setParam("checkDate");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
			}
			String periodCode="";
			String userType="";
			if(type.equals(StarPersonConstants.MONTH_TEAM)){
				userType=StarPersonConstants.TEAM;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.MONTH_GRADE)){
				userType=StarPersonConstants.GRADE;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.MONTH_SCHOOL)){
				userType=StarPersonConstants.SCHOOL;
				periodCode = monthnum(checkDate, termCode);
				dates=monthTime(checkDate);
			}
			if(type.equals(StarPersonConstants.TERM_TEAM)){
				userType=StarPersonConstants.TEAM;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			if(type.equals(StarPersonConstants.TERM_GRADE)){
				userType=StarPersonConstants.GRADE;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			if(type.equals(StarPersonConstants.TERM_SCHOOL)){
				userType=StarPersonConstants.SCHOOL;
				periodCode = termnum(termCode);
				dates=findTermDate(termCode);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ApsStuSummary> dataList = this.studentApsService.evaluatePersonalStar(schoolYear,termCode, objectId, userType, dates[0], dates[1],periodCode);
			List<StarPersonVo> list = new ArrayList<StarPersonVo>();
			if(dataList != null && dataList.size() > 0){
				for(ApsStuSummary data : dataList){
					StarPersonVo vo = new StarPersonVo();
					vo.setAddScore(data.getAddScore());
					vo.setNormalScore(data.getNormalScore());
					vo.setRank(data.getRank());
					vo.setStudentName(data.getStudentName());
					vo.setTeamName(data.getTeamName());
					vo.setTotalScore(data.getTotalScore());
					vo.setFlag(1);
					vo.setDate(sdf.format(data.getCreateDate()));
					list.add(vo);
				}
			}
			return new ResponseVo<List<StarPersonVo>>("0", list);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????");
			info.setMsg("??????????????????????????????");
			info.setParam("termCode,objectId,type,schoolYear");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
			
			
		}
	}
	// ?????????????????????????????????????????????
	public Date[] monthTime(String month) throws ParseException{//??????  2016???6??? 2016-06
		String year = month.substring(0,4);
		String month1 = month.substring(5);
		int yearDateTime = Integer.parseInt(year);
		int monthDateTime = Integer.parseInt(month1);
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd ");
		
		//???????????????
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, yearDateTime);
        cal1.set(Calendar.MONTH, monthDateTime-1);
        cal1.set(Calendar.DAY_OF_MONTH,cal1.getMinimum(Calendar.DATE));
        String str1 = new SimpleDateFormat( "yyyy-MM-dd ").format(cal1.getTime());
        Date beginDate = sdf.parse(str1);
        
		//??????????????????
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, yearDateTime);
		cal2.set(Calendar.MONTH, monthDateTime-1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DATE));
		String str2 = new SimpleDateFormat( "yyyy-MM-dd ").format(cal2.getTime());
		Date endDate = sdf.parse(str2);
        
        Date[] monthDate = {beginDate,endDate};
        
		return monthDate;
	}

		// ????????????
		public String termnum(String termCode) {

			String term = "";
			term = "T0" + termCode.substring(termCode.length() - 1);

			return term;
		}

		// ????????????
		public String monthnum(String date, String termCode) {

			String term = "";
			int realmonth = 0;
			int year= Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5));
			SchoolTermCondition condition = new SchoolTermCondition();
			condition.setCode(termCode);
			List<SchoolTerm> st = schoolTermService.findSchoolTermByCondition(
					condition, null, null);
			int month1 = st.get(0).getBeginDate().getMonth() + 1;
			int year1 = st.get(0).getBeginDate().getYear() + 1900;
			if (year == year1) {
				realmonth = month - month1 + 1;
			} else {
				realmonth = 12 - month1 + month + 1;
			}
			term = "M0" + realmonth;

			return term;
		}
  /**
   * ????????????????????????????????????
   * 
   */
		public Date[] findTermDate(String termCode){
			SchoolTermCondition condition = new SchoolTermCondition();
			condition.setCode(termCode);
			List<SchoolTerm> st = schoolTermService
					.findSchoolTermByCondition(condition, null, null);
		Date[] dates=	new Date[2];
		dates[0] = st.get(0).getBeginDate();
		dates[1] = st.get(0).getFinishDate();
			return dates;
		}
}
