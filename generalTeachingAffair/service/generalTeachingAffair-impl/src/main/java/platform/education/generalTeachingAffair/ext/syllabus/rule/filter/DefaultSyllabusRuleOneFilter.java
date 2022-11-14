package platform.education.generalTeachingAffair.ext.syllabus.rule.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.constants.RuleResponseInfo;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.DataCarrier;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;
import platform.education.generalTeachingAffair.service.SyllabusService;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.SyllabusVoCondition;

public class DefaultSyllabusRuleOneFilter implements SyllabusRuleFilter<SyllabusVo>{
	
	@Autowired
	@Qualifier("syllabusService")
	private SyllabusService syllabusService;
	
	public ResponseDataCarrier doFilter(DataCarrier<SyllabusVo> dc) {
		SyllabusVo syllabusVo = dc.getData();
		ResponseDataCarrier responseData = new ResponseDataCarrier();
		
		if(syllabusVo == null) {
			throw new RuntimeException("DataCarrier is not null");
		}
		
		SyllabusVoCondition condition = new SyllabusVoCondition();
		
		condition.setSchoolYear(syllabusVo.getSchoolYear());
		condition.setTermCode(syllabusVo.getTermCode());
		condition.setDayOfWeek(syllabusVo.getDayOfWeek());
		condition.setLesson(syllabusVo.getLesson());
		condition.setSchoolId(syllabusVo.getSchoolId());
		condition.setTeacherId(syllabusVo.getTeacherId());
		
		Integer currentLessonId = syllabusVo.getLessonId();
		List<SyllabusVo> syllabusVos = syllabusService.findSyllabusVoByCondition(condition, null, null);
		//判断某个教师在同一个学期内同一天某一节课是否已经安排了课程，如果大于0说明已经安排
		if(syllabusVos != null && syllabusVos.size() > 0) {
			for(SyllabusVo voTmp : syllabusVos) {
				if(currentLessonId != null) {
					//为编辑操作,忽略与自己的数据比对
					Integer tempLessonId = voTmp.getLessonId();
					if(!currentLessonId.equals(tempLessonId)) {
						responseData.setInfo(RuleResponseInfo.RULE_ONE);
						responseData.setResponseData(voTmp);
						return responseData;
					}
				} else {
					responseData.setInfo(RuleResponseInfo.RULE_ONE);
					responseData.setResponseData(voTmp);
					return responseData;
				}
			}
		}
		responseData.setInfo(EXECUTE_NEXT_FILTER);
		return responseData;
	}
	
	
	
}
