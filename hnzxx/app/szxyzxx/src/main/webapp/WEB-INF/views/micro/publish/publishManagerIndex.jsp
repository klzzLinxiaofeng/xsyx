<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

.widget-container .kemu a{
	    float: left;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:126px;
}
</style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>微课管理</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="微课管理" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include> 
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                微课管理
                            </h3>
                        </div>
                        <div class="content-widgets">
                            <div class="widget-container">
                                <div class="kemu">
                                    <c:forEach items="${classGradeMap}" var="cm">
                                        <c:forEach items="${cm.value}" var="teamMap">
                                            <c:forEach items="${teamMap}" var="team">
                                                <a onclick="changeClass('${team.key}','${fn:split(team.value,'&&')[1]}');" href="javascript:void(0)" id="class_${team.key}" class="active" style="width:126px" data-type="${fn:split(team.value,'&&')[1]}">${fn:split(team.value,"&&")[0]}</a>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:forEach>
                                </div>
                                <div id="microDataDiv" style="margin-top:10px;">
                                    <jsp:include page="./publishManager.jsp" />
                                </div>
                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="microDataDiv" />
                                    <jsp:param name="url" value="/micropublish/publishManager" />
                                    <jsp:param name="pageSize" value="" />
                                </jsp:include>

                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                                    function changeClass(classId,relateType) {
                                                        $(".kemu a").each(function() {
                                                            if (classId == $(this).attr("id").split("_")[1] && relateType==$(this).attr("data-type")) {
                                                                $(this).addClass("active");
                                                            } else {
                                                                $(this).removeClass("active");
                                                            }
                                                        });
                                                        var url = "/micropublish/publishManager";
                                                        var data = {
                                                        		"relateId": classId,
                                                        		"relateType":relateType
                                                        };
                                                        myPagination("microDataDiv", data, url);
                                                    }

                                                    function resetDate(mid, startDate, finishedDate) {
                                                        var mes = "修改时间";
                                                        var relateId = $(".kemu .active").attr("id").split("_")[1];
                                                        $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/micropublish/resetDate?publishId=' + mid + '&relateId=' + relateId + '&startDate=' + startDate + '&finishedDate=' + finishedDate, '700', '190');
                                                    }

                                                    function deletePublish(mid) {
                                                        $.confirm("确定删除这条已发布的微课吗?", function() {
                                                            $.ajax({
                                                                url: "${pageContext.request.contextPath}/micropublish/deletePublished",
                                                                type: "POST",
                                                                data: {"publishId": mid, "relateId": $(".kemu .active").attr("id").split("_")[1]},
                                                                async: false,
                                                                success: function(html) {
                                                                    location.href = "${pageContext.request.contextPath}/micropublish/publishManagerIndex?relateId=" + $(".kemu .active").attr("id").split("_")[1];
                                                                }
                                                            });
                                                        });
                                                    }

                                                    $(function() {
        <c:choose>
            <c:when test="${relateId!=null}">
                var relateId = "${relateId}";
                var relateType = "${relateType}";
            </c:when>
            <c:otherwise>
                    var relateId = $(".kemu a").first().attr("id").split("_")[1];
                    var relateType = $(".kemu a").first().attr("data-type");
            </c:otherwise>
        </c:choose>
                changeClass(relateId,relateType);
            });
    </script>
</html>