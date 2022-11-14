<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm">
        <b>标题：</b><s:property value="#request.title"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>分数：</b><s:property value="#request.powerPointScore"/>
    </li>
    <li style="float: right;padding: 0 10px">
        <a onclick="if('<s:property value="#request.powerPointFileId"/>'==''){alert('没有上传PPT');return false;}else{return true;}" style="color:blue;text-decoration: underline ;font-size: 12px" target="_blank"
           href="/common/resources/frontResourcesAction_conver.action?fileId=<s:property value="#request.powerPointFileId"/>&width=952&height=600">PPT 内容>></a>
    </li>
    <li class="rr">

    </li>
</ul>
<div class="statistics"><strong>活动统计</strong>
    <div>总人数：<b><s:property value="#request.voList.size()"/>人</b>
        已学：<b><s:property value="#request.finish"/>人</b>未学：<b><s:property value="#request.notFinish"/>人</b></div>
    <span></span></div>
<div class="search"> <span>姓名：</span>
    <input name="powerPointRealName_<s:property value="#request.id"/>" id="powerPointRealName_<s:property value="#request.id"/>" type="text"/>
    <a href="javascript:void(0)" onclick="powerPointNameSearch('<s:property value="#request.id"/>')" class="confirm">
        <img alt="搜索" title="搜索" src="/image/common/lads/lads_confirm_btn.jpg"/>
    </a>
    <!--    <a href="#" class="intelligent">
            <img src="/image/common/lads/lads_intelligent_btn.jpg"/>
        </a>-->
</div>
<table id="powerPointTable_<s:property value="#request.id"/>" class="rtable">
    <thead>
        <tr>
            <th width="100">序号</th>
            <th width="300">学生姓名</th>
            <th width="300">成绩</th>
            <th>学习次数</th>
        </tr>
    </thead>
    <tbody>
        <s:iterator status="st" value="#request.voList" id="vo" >
            <tr>
                <td><s:property value="#st.getIndex()+1"/></td>
                <td class="powerPointRealNameTitle" title="<s:property value="realName"/>"><s:property value="realName"/></td>
                <td>
                    <s:if test='status!=null'>
                        <span id="powerPointScoreSpan_<s:property value="status.id"/>" >
                            <s:property value="status.score"/>
                        </span>分
                    </s:if>
                    <s:else>
                        <span id="powerPointScoreSpan_<s:property value="userId"/>" >
                            0
                        </span>分
                    </s:else>
                    <s:if test='status!=null'>
                        <a href="javascript:void(0)" onclick="powerPointScore('<s:property value="status.id"/>')" >
                            <img alt="评分" title="评分" src="/image/common/lads/lads_pen.png"/>
                        </a>
                    </s:if>
                    <s:else>
                        <a href="javascript:void(0)" onclick="powerPointScore('<s:property value="userId"/>','noStatus','<s:property value="#request.id"/>')" >
                            <img alt="评分" title="评分" src="/image/common/lads/lads_pen.png"/>
                        </a>
                    </s:else>

                </td>
                <td>
                    <s:if test='status!=null'>
                        <s:property value="status.studyTime"/>
                    </s:if>
                    <s:else>
                        0
                    </s:else>
                </td>
            </tr>
        </s:iterator>
    </tbody>
    <script type="text/javascript">
        function editorSaveScore(id){
            var score = $("#powerPointEditScore_"+id).val();
            if(!checkEditorScore(score)){
                return;
            }
            $.ajax({
                url: "/common/lads/ladsPowerPointAction_editUserScore.action",
                type: "POST",
                data:{"score":score,"statusId":id},
                async: false
            });
            $("#powerPointScoreSpan_"+id).html(score);
        }
        function editorSaveNoStatusScore(id,toolId){
            var score = $("#powerPointEditScore_"+id).val();
            if(!checkEditorScore(score)){
                return;
            }
            $.ajax({
                url: "/common/lads/ladsPowerPointAction_editUserScore.action",
                type: "POST",
                data:{"score":score,"toolId":toolId,"userId":id},
                async: false
            });
            $("#powerPointScoreSpan_"+id).html(score);
        }
        function checkPowerPointScore(score){
            var flag = true;
            if(!(/^-?\d+\.?\d*$/.test(score))){
                alert("请输入有效实数");
                flag = false;
                return flag;
            }
            return flag;
        }
        function powerPointScore(id,flag,toolId){
            if($("#powerPointEditScore_"+id).html()!=null){
                if(flag=="noStatus"){
                    powerPointSaveNoStatusScore(id,toolId);
                }else{
                    powerPointSaveScore(id);
                }
            }else{
                var pre_score = $("#powerPointScoreSpan_"+id).html().replace(/[^0-9.]/ig,"");
                $("#powerPointScoreSpan_"+id).empty();
                $("#powerPointScoreSpan_"+id).append("<input name='score' id='powerPointEditScore_"+id+"' value='"+pre_score+"' type='text' class='score_input'/>");
            }
        }
        function editorNameSearch(id){
            var value = $("#powerPointRealName_"+id).val();
            if(value==null||value==""){
                alert("请输入搜索名字");
                return;
            }
            var scrollFlag = true;
            $("#powerPointTable_"+id+" td[class='powerPointRealNameTitle']").each(function(){
                var name = $.trim($(this).html());
                $(this).parent().removeAttr("style");
                if(name.indexOf(value)!=-1){
                    $(this).parent().css("border","2px solid #FF0000");
                    if(scrollFlag){
                        $('html, body').scrollTop($(this).parent().offset().top);
                    }
                    scrollFlag = false;
                }
            });
        }
    </script>
</table>


