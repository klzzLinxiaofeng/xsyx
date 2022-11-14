<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<style>
    .m_content_table tbody td a{
        color: #3B55AA;
    font-weight: bold;
    }
</style>
<div class="module">
    <div class="m_title">
        <h3 class="stare">学员记录</h3>
        <div class="m_search">
            姓名：
            <input id="recordSearch" type="text">
            <input onclick="searchUser()" type="button" value="查找" style="border: 1px #ccc solid;background-color: #fff;width: 50px;font-family: '黑体';color: #666;">
        </div>
        <div class="clear"></div>
    </div>
    <div class="m_content">
        <table class="m_content_table">
            <thead>
                <tr>
                    <th>姓名</th>
                    <th>教学资料模块</th>
                    <!--                    <th>面授导学模块</th>-->
                    <th>主题讨论模块</th>
                    <!--                    <th>课程练习模块</th>
                                        <th>调查问卷模块</th>
                                        <th>幻灯片PPT模块</th>-->
                    <th>微课模块</th>
                    <th>完成进度</th>
                    <th>完成状况</th>
                    <th>总成绩</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sc" items="${requestScope.scoreList}">
                    <tr>
                        <td>
                            <a title="查看详细记录" onclick="searchUserRecord('${sc.userVo.userId}', '${sc.userVo.realName}')" href="javascript:void(0)">
                                ${sc.userVo.realName}
                            </a>
                        </td>
                        <td><fmt:formatNumber value="${sc.editorScore+ 0.0001}" pattern="#,###,###,###"/></td>
                        <%--                        <td><fmt:formatNumber value="${sc.faceteachingScore+ 0.0001}" pattern="#,###,###,###"/></td>--%>
                        <td><fmt:formatNumber value="${sc.discussScore+ 0.0001}" pattern="#,###,###,###"/></td>
                        <%--                        <td><fmt:formatNumber value="${sc.quizScore+ 0.0001}" pattern="#,###,###,###"/></td>
                                                <td><fmt:formatNumber value="${sc.surveyScore+ 0.0001}" pattern="#,###,###,###"/></td>
                                                <td><fmt:formatNumber value="${sc.powerpointScore+ 0.0001}" pattern="#,###,###,###"/></td>--%>
                        <td><fmt:formatNumber value="${sc.mediaScore+ 0.0001}" pattern="#,###,###,###"/></td>
                        <td><img src="${pageContext.request.contextPath}/lads/monitoring/finishedImage?percent=${sc.finishedPercent}"/></td>
                        <td>   
                            <c:choose>
                                <c:when test="${sc.finishedPercent<100}">
                                    <img src ="${pageContext.request.contextPath}/res/images/common/lads/unfinished.png"/>
                                </c:when>
                                <c:otherwise>
                                    <img src ="${pageContext.request.contextPath}/res/images/common/lads/finished.png"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="total"><fmt:formatNumber value="${sc.userVo.score+ 0.0001}" pattern="#,###,###,###"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function searchUser() {
        var value = $("#recordSearch").val();
        if (value != null && value != "") {
            var scrollFlag = true;
            var findedFlag = false;
            $(".m_content_table tr > td > a").each(function () {
                var name = $.trim($(this).text());
                $(this).parent().parent().css("border", "1px solid #9E9E9E");
                if (name.indexOf(value) != -1) {
                    var tr = $(this).parent().parent();
                    tr.css("border", "2px solid red");
                    if (scrollFlag) {
                        $('html, body').scrollTop(tr.offset().top);
                    }
                    scrollFlag = false;
                    findedFlag = true;
                }
            });
            if(!findedFlag){
                $.alert("搜索不到学员姓名");
            }
        } else {
            $.alert("请输入要搜索的学员姓名");
        }
    }

    function searchUserRecord(userId, userName) {
        $("#learnerRecord").hide();
        $("#singleRecord").show();
        $("#singleRecord").html("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
        $("#singleRecord").load("${pageContext.request.contextPath}/lads/monitoring/singleRecord", {"userId": userId, "userName": userName});
    }
</script>