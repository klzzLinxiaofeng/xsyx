<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/views/embedded/taglib.jsp" %>
<%@ include file="/views/embedded/textBookCatalogScript.jsp"%>

				<span class="text_ml">&nbsp; 学段: &nbsp;
					<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findPublicTextBook('subjectCode', '${param.type}',${resourceType eq 'res_region' ? 1 : 0});">
						<c:forEach items="${stageCodeMap}" var="map">
							<option value="${map.value }">${map.key }</option>
						</c:forEach>
					</select>
				</span>
				
				<span class="text_ml">&nbsp; 科目: &nbsp;
					<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findPublicTextBook('publisherId', '${param.type}',${resourceType eq 'res_region' ? 1 : 0});">
						<option value="">请选择</option>
					</select>
				</span>
				
				<span class="text_ml">&nbsp; 版本: &nbsp;
					<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findPublicTextBook('gradeCodeVolumn', '${param.type}',${resourceType eq 'res_region' ? 1 : 0});">
						<option value="">请选择</option>
					</select>
				</span>

				<span class="text_ml">册次:
					<select id="gradeCodeVolumn" name="gradeCodeVolumn" class="chzn-select"style="width:160px;" onchange="findResTextBookCatalog(this,'0','0','${param.type}');">
					<option value="">请选择</option>
				</select></span>
				
<script type="text/javascript">
  $(function() {
	  var type = '${param.type}';
	  if(${resourceType eq 'res_region'}){
          findPublicTextBook("stageCode",type,1);
      }else {
          findPublicTextBook("stageCode",type,0);
      }


  }
  );
</script>