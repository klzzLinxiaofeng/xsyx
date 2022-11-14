<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/views/embedded/taglib.jsp" %>
<%@ include file="/views/embedded/textBookCatalogScript.jsp"%>

				<span class="text_ml">学段:
				<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode','${type}');">
				<c:forEach items="${stageCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
				</select>
				</span>
				
				<span class="text_ml">科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('publisherId','${type}');">
				<option value="">请选择</option>
				</select></span>
				
				
				
				<span class="text_ml">版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCodeVolumn','${type}');">
				<option value="">请选择</option>
				</select></span>

				<span class="text_ml">年级册次:<select id="gradeCodeVolumn" name="gradeCodeVolumn" class="chzn-select"style="width:160px;"  onchange="findTextBookCatalog(this,'0','0','${type}');">
				
				<option value="">请选择</option>
				</select></span>
				
<script type="text/javascript">
  $(function() {
        findTextBook("stageCode");
  });
</script>