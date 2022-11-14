<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm">
        <b>标题：</b>${requestScope.title}
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>分数：</b>${requestScope.mediaScore}
    </li>
    <li style="float: right;padding: 0 10px">
        <a style="color:blue;text-decoration: underline ;font-size: 12px" 
           href="javascript:void(0)" onclick="previewMediaTool('${requestScope.id}')" >查看微课>></a>
    </li>
    <li class="rr">

    </li>
</ul>
<div class="statistics"><strong>活动统计</strong>
    <div>总人数：
        <b>
            ${fn:length(requestScope.voList)}人
        </b>
        已学：
        <b>
            ${requestScope.finish}人
        </b>
        未学：
        <b>
            ${requestScope.notFinish}人
        </b>
    </div>
    <span></span></div>
<div class="search"> <span>姓名：</span>
    <input name="mediaRealName_${requestScope.id}" id="mediaRealName_${requestScope.id}" type="text"/>
    <a href="javascript:void(0)" onclick="mediaNameSearch('${requestScope.id}')" class="confirm">
        <img alt="搜索" title="搜索" src="${pageContext.request.contextPath}/res/images/common/lads/lads_confirm_btn.jpg"/>
    </a>
    <!--    <a href="#" class="intelligent">
            <img src="/image/common/lads/lads_intelligent_btn.jpg"/>
        </a>-->
</div>
<table id="mediaTable_${requestScope.id}" class="rtable">
    <thead>
        <tr>
            <th width="100">序号</th>
            <th width="300">学生姓名</th>
            <th width="300">成绩</th>
            <th>观看次数</th>
            <th>最后一次播放进度</th>
            <th>完成状况</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach varStatus="st" items="${requestScope.voList}" var="vo">
            <tr>
                <td>${st.index+1}</td>
                <td class="mediaRealNameTitle" title="${vo.realName}">${vo.realName}</td>
                <td>
                    <c:choose>
                        <c:when test="${!(vo.status eq null)}">
                            <span id="mediaScoreSpan_${vo.status.uuid}" >
                                ${vo.status.score}
                            </span>分
                            <a href="javascript:void(0)" onclick="mediaScore('${vo.status.uuid}')" >
                                <img alt="评分" title="评分" src="${pageContext.request.contextPath}/res/images/common/lads/lads_pen.png"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <span id="mediaScoreSpan_${vo.userId}" >
                                0
                            </span>分
                            <a href="javascript:void(0)" onclick="mediaScore('${vo.userId}', 'noStatus', '${requestScope.id}')" >
                                <img alt="评分" title="评分" src="${pageContext.request.contextPath}/res/images/common/lads/lads_pen.png"/>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${!(vo.status eq null)}">
                            ${vo.status.studyTime}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    ${(vo.formatLastPlayTime==null||"" eq vo.formatLastPlayTime)?"---":vo.formatLastPlayTime}
                </td>
                <td>
                    <c:choose>
                        <c:when test="${!(vo.status eq null)&&(vo.status.status eq requestScope.mediaFinishedCons)}">
                            <img src ="${pageContext.request.contextPath}/res/images/common/lads/finished.png"/>
                        </c:when>
                        <c:otherwise>
                            <img src ="${pageContext.request.contextPath}/res/images/common/lads/unfinished.png"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
    <script type="text/javascript">
        function mediaSaveScore(id) {
            var score = $("#mediaEditScore_" + id).val();
            if (!checkMediaScore(score)) {
                return;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/media/editUserScore",
                type: "POST",
                data: {"score": score, "statusId": id},
                async: false
            });
            $("#mediaScoreSpan_" + id).html(score);
        }
        function mediaSaveNoStatusScore(id, toolId) {
            var score = $("#mediaEditScore_" + id).val();
            if (!checkMediaScore(score)) {
                return;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/media/editUserScore",
                type: "POST",
                data: {"score": score, "toolId": toolId, "userId": id},
                async: false
            });
            $("#mediaScoreSpan_" + id).html(score);
        }
        function checkMediaScore(score) {
            var flag = true;
            if (!(/^-?\d+\.?\d*$/.test(score))) {
                $.alert("请输入有效实数");
                flag = false;
                return flag;
            }
            return flag;
        }
        function mediaScore(id, flag, toolId) {
            if ($("#mediaEditScore_" + id).html() != null) {
                if (flag == "noStatus") {
                    mediaSaveNoStatusScore(id, toolId);
                } else {
                    mediaSaveScore(id);
                }
            } else {
                var pre_score = $("#mediaScoreSpan_" + id).html().replace(/[^0-9.]/ig, "");
                $("#mediaScoreSpan_" + id).empty();
                $("#mediaScoreSpan_" + id).append("<input style=\"width:100px\" onkeyup=\"checkScore('"+toolId+"', this)\" name='score' id='mediaEditScore_" + id + "' value='" + pre_score + "' type='text' class='score_input'/>");
            }
        }
        function mediaNameSearch(id) {
            var value = $("#mediaRealName_" + id).val();
            if (value == null || value == "") {
                $.alert("请输入搜索姓名");
                return;
            }
            var scrollFlag = true;
            var findedFlag = false;
            $("#mediaTable_" + id + " td[class='mediaRealNameTitle']").each(function () {
                var name = $.trim($(this).html());
                $(this).parent().removeAttr("style");
                if (name.indexOf(value) != -1) {
                    $(this).parent().css("border", "2px solid #FF0000");
                    if (scrollFlag) {
                        $('html, body').scrollTop($(this).parent().offset().top);
                    }
                    scrollFlag = false;
                    findedFlag = true;
                }
            });
            if(!findedFlag){
                $.alert("搜索不到学员姓名");
            }
        }

        function previewMediaTool(id) {
            var mes = "查看微课";
            $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/lads/tool/media/previewMonitorMedia?toolId=' + id, '700', '500');
        }
    </script>
</table>


