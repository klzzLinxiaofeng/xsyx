<%--
  Created by IntelliJ IDEA.
  User: panfei
  Date: 2017/7/5
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
    </style>
</head>
<body style="background-color: cdd4d7 !important">

</body>
<script type="text/javascript">
    $(function () {
        if("${type}"=="vd"){
            location.href = "${pageContext.request.contextPath}/learningDesign/learningPlan/edit?type=view&editable=false&id="+${resId};
        }else{
            location.href = "${pageContext.request.contextPath}/resource/viewer/${resId}";
        }

    });
</script>
</html>
