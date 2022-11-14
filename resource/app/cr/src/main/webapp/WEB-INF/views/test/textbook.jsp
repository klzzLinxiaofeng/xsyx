<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/views/embedded/taglib.jsp" %>
<%@ include file="/views/embedded/textBookCatalogScript.jsp"%>
<div class="jjmu">
    <p>教材版本：</p>
    <ul>
        <li>
            <i>*</i><span>学段</span>
            <select id="stageCode" name="stageCode" onchange="findTextBook('subjectCode', '${param.type}');">
                <c:forEach items="${stageCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
            </select>
        </li>
        <li>
            <i>*</i><span>科目</span>
            <select id="subjectCode" name="subjectCode" onchange="findTextBook('publisherId', '${param.type}');">
                <option value="">请选择</option>
            </select>
        </li>
        <li>
            <i>*</i><span>版本</span>
            <select id="publisherId" name="publisherId" onchange="findTextBook('gradeCodeVolumn', '${param.type}');">
                <option value="">请选择</option>
            </select>
        </li>
        <li>
            <i>*</i><span>册次</span>
            <select id="gradeCodeVolumn" name="gradeCodeVolumn" onchange="findResTextBookCatalog(this,'0','0','${param.type}');">
                <option value="">请选择</option>
            </select>
        </li>
    </ul>
</div>
<script type="text/javascript">
  $(function() {
	  var type = '${param.type}';
      findTextBook("stageCode",type);
  });
</script>