<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>

<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="a3">
    <div class="blue"></div>
    <div class="my_weike">
        <div id="microLessonDiv" class="doc-list-area">
            <jsp:include page="./myLearningDesignList.jsp" />
        </div>
        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="microLessonDiv" />
            <jsp:param name="url"  value="/ladspublish/myLearningDesign" />
            <jsp:param name="pageSize" value="${page.pageSize }" />
            <jsp:param name="callback" value="checkSelected()" />
        </jsp:include>
        <div class="clear"></div>
    </div>
</div>
<script type="text/javascript">
    function deleteMyLads(id) {
        var url = "${pageContext.request.contextPath}/ladspublish/delete?resId=" + id;
        $.confirm("确定删除此课件吗？", function () {
            $.ajax({
                url: url,
                type: "POST",
                async: false,
                success: function (data) {
                    $("#lddl_"+id).remove();
                }
            });
        });
    }

    $(function () {
        checkSelected();
    });
</script>