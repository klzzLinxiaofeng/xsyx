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
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>分数：</b>${requestScope.editorScore}
    </li>
    <li style="float: right;padding: 0 10px">
        <a style="color:blue;text-decoration: underline ;font-size: 12px"  target="_blank"
           href="${pageContext.request.contextPath}/lads/tool/editor/seeContent?toolId=${requestScope.id}">学习内容>></a>
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
    <input name="editorRealName_${requestScope.id}" id="editorRealName_${requestScope.id}" type="text"/>
    <a href="javascript:void(0)" onclick="editorNameSearch('${requestScope.id}')" class="confirm">
        <img alt="搜索" title="搜索" src="${pageContext.request.contextPath}/res/images/common/lads/lads_confirm_btn.jpg"/>
    </a>
    <!--    <a href="#" class="intelligent">
            <img src="/image/common/lads/lads_intelligent_btn.jpg"/>
        </a>-->
</div>
<table id="editorTable_${requestScope.id}" class="rtable">
    <thead>
        <tr>
            <th width="100">序号</th>
            <th width="300">学生姓名</th>
            <th width="300">成绩</th>
            <th>学习次数</th>
            <th>完成状况</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach varStatus="st" items="${requestScope.voList}" var="vo">
            <tr>
                <td>${st.index+1}</td>
                <td class="editorRealNameTitle" title="${vo.realName}">${vo.realName}</td>
                <td>
                    <c:choose>
                        <c:when test="${!(vo.status eq null)}">
                            <span id="editorScoreSpan_${vo.status.uuid}" >
                               ${vo.status.score}
                            </span>分
                            <a href="javascript:void(0)" onclick="editorScore('${vo.status.uuid}')" >
                                <img alt="评分" title="评分" src="${pageContext.request.contextPath}/res/images/common/lads/lads_pen.png"/>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <span id="editorScoreSpan_${vo.userId}" >
                               0
                            </span>分
                            <a href="javascript:void(0)" onclick="editorScore('${vo.userId}','noStatus','${requestScope.id}')" >
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
                    <c:choose>
                        <c:when test="${!(vo.status eq null)&&(vo.status.status eq requestScope.editorFinishedCons)}">
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
        function editorSaveScore(id){
            var score = $("#editorEditScore_"+id).val();
            if(!checkEditorScore(score)){
                return;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/editor/editUserScore",
                type: "POST",
                data:{"score":score,"statusId":id},
                async: false
            });
            $("#editorScoreSpan_"+id).html(score);
        }
        function editorSaveNoStatusScore(id,toolId){
            var score = $("#editorEditScore_"+id).val();
            if(!checkEditorScore(score)){
                return;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/editor/editUserScore",
                type: "POST",
                data:{"score":score,"toolId":toolId,"userId":id},
                async: false
            });
            $("#editorScoreSpan_"+id).html(score);
        }
        function checkEditorScore(score){
            var flag = true;
            if(!(/^-?\d+\.?\d*$/.test(score))){
                $.alert("请输入有效实数");
                flag = false;
                return flag;
            }
            return flag;
        }
        function editorScore(id,flag,toolId){
            if($("#editorEditScore_"+id).html()!=null){
                if(flag=="noStatus"){
                    editorSaveNoStatusScore(id,toolId);
                }else{
                    editorSaveScore(id);
                }
            }else{
                var pre_score = $("#editorScoreSpan_"+id).html().replace(/[^0-9.]/ig,"");
                $("#editorScoreSpan_"+id).empty();
                $("#editorScoreSpan_"+id).append("<input style=\"width:100px\" onkeyup=\"checkScore('"+toolId+"', this)\" name='score' id='editorEditScore_"+id+"' value='"+pre_score+"' type='text' class='score_input'/>");
            }
        }
        function editorNameSearch(id){
            var value = $("#editorRealName_"+id).val();
            if(value==null||value==""){
                $.alert("请输入搜索姓名");
                return;
            }
            var scrollFlag = true;
            var findedFlag = false;
            $("#editorTable_"+id+" td[class='editorRealNameTitle']").each(function(){
                var name = $.trim($(this).html());
                
                $(this).parent().removeAttr("style");
                if(name.indexOf(value)!=-1){
                    $(this).parent().css("border","2px solid #FF0000");
                    if(scrollFlag){
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
    </script>
</table>


