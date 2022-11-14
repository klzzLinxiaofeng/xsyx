<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="a3">
    <div class="blue"></div>
    <div class="my_weike">
        <div id="microLessonDiv" class="doc-list-area">
            <jsp:include page="./myExamList.jsp" />
        </div>
        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="microLessonDiv" />
            <jsp:param name="url"  value="/learningPlan/exam/myList?index=list&personType=${personType }" />
            <jsp:param name="pageSize" value="${page.pageSize }" />
            <jsp:param name="callback" value="checkSelected()" />
        </jsp:include>
        <div class="clear"></div>
    </div>
</div>

<script type="text/javascript">
$(function() {
    checkSelected();
});
</script>