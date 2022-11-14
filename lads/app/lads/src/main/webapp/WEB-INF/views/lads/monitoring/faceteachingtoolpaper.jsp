<%-- 
    Document   : faceteachingtoolpaper
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
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>分数：</b><s:property value="#request.faceTeachingScore"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>时间：</b><s:property value="#request.faceTeachingStartTime"/> - <s:property value="#request.faceTeachingStopTime"/>
    </li>
    <li style="float: right;padding: 0 10px">
        <a style="color:blue;text-decoration: underline ;font-size: 12px" onclick="faceTeachingDetail('<s:property value="#request.id"/>')"
           href="javascript:void(0)">地址介绍信息>></a>
    </li>
    <li class="rr">

    </li>
</ul>
<div class="statistics"><strong>活动统计</strong>
    <div>总人数：<b><s:property value="#request.voList.size()"/>人</b>
        全勤：<b id="faceTeachingFullAttendance_<s:property value="#request.id"/>" ><s:property value="#request.faceTeachingFullAttendance"/>人</b>公假：<b id="faceTeachingPublicHoliday_<s:property value="#request.id"/>" ><s:property value="#request.faceTeachingPublicHoliday"/>人</b>
        病事假：<b id="faceTeachingSickLeave_<s:property value="#request.id"/>"><s:property value="#request.faceTeachingSickLeave"/>人</b>旷课：<b id="faceTeachingAbsenteetsm_<s:property value="#request.id"/>"><s:property value="#request.faceTeachingAbsenteetsm"/>人</b>
    </div>
    <span></span></div>
<div class="search"> <span>姓名：</span>
    <input name="faceTeachingRealName_<s:property value="#request.id"/>" id="faceTeachingRealName_<s:property value="#request.id"/>" type="text"/>
    <a href="javascript:void(0)" onclick="faceteachingNameSearch('<s:property value="#request.id"/>')" class="confirm">
        <img alt="搜索" title="搜索" src="/image/common/lads/lads_confirm_btn.jpg"/>
    </a>
    <!--    <a href="#" class="intelligent">
            <img src="/image/common/lads/lads_intelligent_btn.jpg"/>
        </a>-->
</div>
<table id="faceteachingTable_<s:property value="#request.id"/>" class="rtable">
    <thead>
        <tr>
            <th width="100">序号</th>
            <th width="300">学生姓名</th>
            <th width="300">成绩</th>
            <th>出勤情况</th>
        </tr>
    </thead>
    <tbody>
        <s:iterator status="st" value="#request.voList" id="vo" >
            <tr>
                <td><s:property value="#st.getIndex()+1"/></td>
                <td class="faceteachingRealNameTitle" title="<s:property value="realName"/>"><s:property value="realName"/></td>
                <td>
                    <span id="faceTeachingEditScoreSpan_<s:property value="status.id"/>">
                        <s:property value="status.score"/>
                    </span>分
                    <a href="javascript:void(0)" onclick="faceTeachingScore('<s:property value="status.id"/>')" >
                        <img alt="评分" title="评分" src="/image/common/lads/lads_pen.png"/>
                    </a>
                </td>
                <td width="380" id="faceTeachingStatusTd_<s:property value="status.id"/>">
                    <a href="javascript:void(0)"
                       onclick="faceTeachingSaveStatus(this,'<s:property value="status.id"/>','<s:property value="@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@FULL_ATTENDANCE"/>','<s:property value="#request.id"/>')"
                       class="btn1
                       <s:if test='status.status.equals(@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@FULL_ATTENDANCE)'> hover</s:if>">
                       </a>
                       <a href="javascript:void(0)"
                          onclick="faceTeachingSaveStatus(this,'<s:property value="status.id"/>','<s:property value="@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@PUBLIC_HOLIDAY"/>','<s:property value="#request.id"/>')"
                       class="btn2
                       <s:if test='status.status.equals(@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@PUBLIC_HOLIDAY)'> hover</s:if>">
                       </a>
                       <a href="javascript:void(0)"
                          onclick="faceTeachingSaveStatus(this,'<s:property value="status.id"/>','<s:property value="@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@SICK_LEAVE"/>','<s:property value="#request.id"/>')"
                       class="btn3
                       <s:if test='status.status.equals(@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@SICK_LEAVE)'> hover</s:if>">
                       </a>
                       <a href="javascript:void(0)"
                          onclick="faceTeachingSaveStatus(this,'<s:property value="status.id"/>','<s:property value="@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@ABSENTEEISM"/>','<s:property value="#request.id"/>')"
                       class="btn4
                       <s:if test='status.status.equals(@com.gzxtjy.lads.service.impl.FaceTeachingToolServiceImpl@ABSENTEEISM)'> hover</s:if>">
                       </a>
                    </td>
                </tr>
        </s:iterator>
    </tbody>
</table>
<div id="faceTeachingDetailDiv_<s:property value="#request.id"/>" class="quizShowDiv" style="display: none;padding:25px 15px 15px">
    <a href="#" class="close"></a>
    <div class="popTitle"><strong>面授教学</strong></div>
    <table style="margin:10px 0;line-height:35px;font-size:14px">
        <tr>
            <td align="right" width="100"><strong>活动标题：</strong></td>
            <td><span style="color:#0f6aa3"><s:property value="#request.title"/></span></td>
        </tr>
        <tr>
            <td align="right"><strong>活动时间：</strong></td>
            <td><s:property value="#request.faceTeachingStartTime"/> - <s:property value="#request.faceTeachingStopTime"/></td>
        </tr>
        <tr>
            <td align="right"><strong>活动地点：</strong></td>
            <td><s:property value="#request.faceTeachingAddress"/></td>
        </tr>
        <tr>
            <td align="right"><strong>活动介绍：</strong></td>
            <td><s:property value="#request.faceTeachingDescription"/></td>
        </tr>
        <tr>
            <td align="right"><strong>活动分数：</strong></td>
            <td><s:property value="#request.faceTeachingScore"/> 分</td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function faceTeachingSaveStatus(obj,id,status,toolId){
        if($(obj).attr("class").indexOf("hover")==-1){
            var oldClass;
            $(obj).parent().children().each(function(){
                if($(this).attr("class").indexOf("hover")!=-1){
                    oldClass = $.trim($(this).attr("class").replace("hover",""));
                }
            });
            $.ajax({
                url: "/common/lads/ladsFaceTeachingAction_editUserStatus.action",
                type: "POST",
                data:{"statusId":id,"status":status},
                async: false
            });
            $("#faceTeachingStatusTd_"+id+" a").each(function(){
                if($(this).attr("class").indexOf("hover")!=-1){
                    var className = $(this).attr("class").replace("hover","");
                    $(this).attr("class",className);
                }
            })
            $(obj).attr("class",$(obj).attr("class")+" hover");
            if(status=="全勤"){
                var count = $("#faceTeachingFullAttendance_"+toolId).text().replace("人","");count++;
                $("#faceTeachingFullAttendance_"+toolId).text(count+"人");
            }else if(status =="公假"){
                var count = $("#faceTeachingPublicHoliday_"+toolId).text().replace("人","");count++;
                $("#faceTeachingPublicHoliday_"+toolId).text(count+"人");
            }else if(status =="旷课"){
                var count = $("#faceTeachingAbsenteetsm_"+toolId).text().replace("人","");count++;
                $("#faceTeachingAbsenteetsm_"+toolId).text(count+"人");
            }else if(status =="病事假"){
                var count = $("#faceTeachingSickLeave_"+toolId).text().replace("人","");count++;
                $("#faceTeachingSickLeave_"+toolId).text(count+"人");
            }
            if(oldClass=="btn1"){
                var count = $("#faceTeachingFullAttendance_"+toolId).text().replace("人","");count--;
                $("#faceTeachingFullAttendance_"+toolId).text(count+"人");
            }else if(oldClass=="btn2"){
                var count = $("#faceTeachingPublicHoliday_"+toolId).text().replace("人","");count--;
                $("#faceTeachingPublicHoliday_"+toolId).text(count+"人");
            }else if(oldClass=="btn3"){
                var count = $("#faceTeachingSickLeave_"+toolId).text().replace("人","");count--;
                $("#faceTeachingSickLeave_"+toolId).text(count+"人");
            }else if(oldClass=="btn4"){
                var count = $("#faceTeachingAbsenteetsm_"+toolId).text().replace("人","");count--;
                $("#faceTeachingAbsenteetsm_"+toolId).text(count+"人");
            }
        }
    }
    function faceTeachingSaveScore(id){
        var score = $("#faceTeachingEditScore_"+id).val();
        if(!checkFaceTeachingScore(score)){
            return;
        }
        $.ajax({
            url: "/common/lads/ladsFaceTeachingAction_editUserScore.action",
            type: "POST",
            data:{"score":score,"statusId":id},
            async: false
        });
        $("#faceTeachingEditScoreSpan_"+id).html(score);
    }
    function checkFaceTeachingScore(score){
        var flag = true;
        if(!(/^-?\d+\.?\d*$/.test(score))){
            alert("请输入有效实数");
            flag = false;
            return flag;
        }
        return flag;
    }
    function faceTeachingScore(id){
        if($("#faceTeachingEditScore_"+id).html()!=null){
            faceTeachingSaveScore(id);
        }else{
            var pre_score = $("#faceTeachingEditScoreSpan_"+id).html().replace(/[^0-9.]/ig,"");
            $("#faceTeachingEditScoreSpan_"+id).empty();
            $("#faceTeachingEditScoreSpan_"+id).append("<input name='score' id='faceTeachingEditScore_"+id+"' value='"+pre_score+"' type='text' class='score_input'/>");
        }
    }
    function faceteachingNameSearch(id){
        var value = $("#faceTeachingRealName_"+id).val();
        if(value==null||value==""){
            alert("请输入搜索名字");
            return;
        }
        var scrollFlag = true;
        $("#faceteachingTable_"+id+" td[class='faceteachingRealNameTitle']").each(function(){
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
    var faceTeachingOverlayObj;
    function openFaceTeachingOverlay(elm, close_onclick, one_instance) {
        if (close_onclick == undefined) {
            close_onclick = true;
        }
        if (one_instance == undefined) {
            one_instance = true;
        }
        var top = (document.body.scrollTop + document.body.clientHeight / 2 - 600 / 2) + "px";
        var left = (document.body.scrollLeft + document.body.clientWidth / 2 - 800 / 2);
        faceTeachingOverlayObj = $(elm).overlay({
            api : true,
            closeOnClick : close_onclick,
            oneInstance: one_instance,
            left: left,
            top: '10%',
            expose: {
                color: '#333',
                opacity: 0.7,
                zIndex:10000
            }
        });
        faceTeachingOverlayObj.load();
        $(elm).show();
    }
    function faceTeachingDetail(id){
        openFaceTeachingOverlay("#faceTeachingDetailDiv_"+id, "closeOverlay", false);
    }
</script>