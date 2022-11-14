<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <%--弃用uploadify插件改为uploadifive插件--%>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>

    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>会务申请</title>
    <style type="text/css">
        .form-horizontal .controls #zpa .img_1 {
            float: left;
            margin: 10px;
            position: relative;
            top: 0;
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 img {
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 a {
            position: absolute;
            font-size: 22px;
            font-weight: bold;
            color: #000;
            right: 0px;
            top: 0px;
            display: block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            cursor: pointer;
        }
        .form-horizontal .controls{
            margin-left: 100px;
        }
        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
        .table tr th,.table tr td{text-align:center;}
    </style>
    <style type="text/css">
        div#div3, div#div2, div#div1,div#div4 {
            position: absolute;
            height: 300px;
            width: 300px;
            background: #eee;
            border: 1px solid #d9d9d9;
            padding: 10px;
            overflow: auto;
            left: 200px;
        }


        div#div3 table button, div#div2 table button, div#div1 table button,div#div4 table button {
            width: 280px;
            height: 30px;
            border: none;
            border-bottom: 1px solid #d9d9d9;
        }


        input.input-medium {
            height: 10px;
            width: 200px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="会务申请" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <c:if test="${huiyi.id==null}">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请会务</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    </c:if>
                    <c:if test="${huiyi.id!=null}">
                    <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">修改会务</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    </c:if>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">主题<span style="color:red">*</span></label>
                                    <div>
                                        <input type="text" id="theme" name="theme" value="${huiyi.theme}" placeholder="请输入标题，少于20个中文字符" class="span6 left-stripe {required : true,maxlength:40}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            申请部门<span style="color:red">*</span>
                                        </label>
                                        <select id="departmentId" name="departmentId">

                                        </select>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            申请人<span style="color:red">*</span>
                                        </label>
                                        <c:if test="${user!=null}">
                                        <input type="text" id="applicant" style="display: none"  disabled  name="applicant" value="${user.id}" class="span6 left-stripe {required : true,maxlength:40}">
                                        <input type="text" id="applicantName"  disabled  name="applicantName" value="${user.realName}" class="span6 left-stripe {required : true,maxlength:40}">
                                        </c:if>
                                        <c:if test="${huiyi!=null}">
                                            <input type="text" id="applicant" style="display: none"  disabled  name="applicant" value="${huiyi.applicant}" class="span6 left-stripe {required : true,maxlength:40}">
                                            <input type="text" id="applicantName"  disabled  name="applicantName" value="${huiyi.applicantName}" class="span6 left-stripe {required : true,maxlength:40}">
                                        </c:if>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            会务负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="huiwufuzeId" style="display: none"   name="huiwufuzeId" value="${huiyi.huiwufuzeId}" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <input type="button" id="huiwufuzeName"  onclick="xuanze('huiwufuzeId','huiwufuzeName','div1')" value="${huiyi.huiwufuzeName}"  name="huiwufuzeName"  class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <div class="techerId" id="div1" style="display: none">
                                            <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord">
                                            <button type="button" class="btn" onclick="search2('biao1','huiwufuzeId','huiwufuzeName','div1');"><i class="fa fa-search"></i></button>
                                            <table id="biao1" style="height: 200px;overflow: auto">

                                            </table>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动负责人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" style="display: none" id="eventManager"   name="eventManager" value="${huiyi.eventManager}" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <input type="button"  onclick="xuanze('eventManager','eventManagerName','div2')" value="${huiyi.eventManagerName}" id="eventManagerName" name="eventManagerName" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <div class="techerId" id="div2" style="display: none">
                                            <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord2">
                                            <button type="button" class="btn" onclick="search3('biao2','eventManager','eventManagerName','div2');"><i class="fa fa-search"></i></button>
                                            <table id="biao2" style="height: 200px;overflow: auto">

                                            </table>
                                        </div>


                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            审批人<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="reviewer"  name="reviewer" style="display:none;" value="${huiyi.reviewer}"/>
                                        <input type="button" value="${huiyi.reviewerName}" onclick="xuanze('reviewer','reviewerName','div3')"  id="reviewerName" name="reviewerName" value="${huiyi.reviewerName}" class="span6 left-stripe {required : true,maxlength:40}"/>
                                        <div  class="techerId" id="div3" style="display: none;height: 300px">
                                            <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord3">
                                            <button type="button" class="btn" onclick="search4('biao3','reviewer','reviewerName','div3');"><i class="fa fa-search" class="span6 left-stripe {required : true,maxlength:40}"></i></button>
                                            <table id="biao3" style="height: 200px;overflow: auto">

                                            </table>
                                        </div>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                               placeholder="xxxx-xx-xx xx:xx"
                                                value="${huiyi.kaishiTime}">
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            活动场地<span style="color:red">*</span>
                                        </label>
                                        <select id="changdiId" name="changdiId">

                                        </select>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">领导席位</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="leadership" name="leadership" style="width:800px;height:100px;">${huiyi.leadership}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">活动物资</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="activitySupplies" name="activitySupplies" style="width:800px;height:100px;">${huiyi.activitySupplies}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">场地设备</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="equipment" name="equipment" style="width:800px;height:100px;">${huiyi.equipment}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">会务细则</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="meeting" name="meeting" style="width:800px;height:100px;">${huiyi.meeting}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            附件：
                                        </label>
                                        <div class="controls">
                                            <c:if test="${isCK==''||isCK==null}">
                                                <input type="hidden" id="uploader" name="uploader">
                                            </c:if>
                                            <span id="tp_queue"></span>
                                            <div id="zpa" style="display:inline-block;">
                                                <c:forEach items="${huiyi.fujian}" var="item" varStatus="i">
                                                    <div id="div_${item['uuid']}">
                                                        <input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="${item['uuid']}" />
                                                        <a href="${item['url']}">${item['fileName']}</a>
                                                        <button onclick="chehui('div_${item['uuid']}')" type="button">撤销</button>
                                                    </div>
                                                </c:forEach>

                                            </div>

                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">工作人员</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea id="staff" onfocus="hahaha('staff','div4')"  name="staff" style="width:800px;height:100px;">${huiyi.staff}</textarea>
                                                <div  class="techerId" id="div4" style="display: none;height: 300px">
                                                    <button type="button" onclick="guanbi()">X</button>
                                                    <input type="text" size="16" placeholder="名称" class="input-medium" id="searchWord4">
                                                    <button type="button" class="btn" onclick="search5('biao4','staff','div4');"><i class="fa fa-search" class="span6 left-stripe {required : true,maxlength:40}"></i></button>
                                                    <table id="biao4" style="height: 200px;overflow: auto">

                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                            <label class="control-label">其他人员</label>
                                            <div class="left">
                                                <div class="textarea">
                                                    <textarea id="qitaRenYuan" name="qitaRenYuan" style="width:800px;height:100px;">${huiyi.qitaRenYuan}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    <div class="control-group">
                                        <label class="control-label">参会人数</label>
                                        <div>
                                            <input style="position:relative;top:-12px;" type="text" id="attendanceNumber" name="attendanceNumber" value="${huiyi.attendanceNumber}" class="span3 left-stripe {maxlength:500}">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">发布</button>
                    </p>
                    <input type="hidden" id="huiyi_id" value="${huiyi.id}"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
        function guanbi() {
            $("#div4").attr("style", "display:none;");
            $("#biao4").html('');
        }
    function hahaha(idd,divs) {
        $("#"+divs).attr("style", "display:block;");
        $("#div1").attr("style", "display:none;");
        $("#div2").attr("style", "display:none;");
        $("#div3").attr("style", "display:none;");
        $("#biao1").html('');
        $("#biao2").html('');
        $("#biao3").html('');
        $("#biao4").html('');
        search5('biao4',idd,divs);
    }
    function search5(biao,idd,divs){
        $("#"+biao).html('');
        var text=$("#searchWord4").val();
        gelAllTeacher5(text,biao,idd,divs);
    }
    function gelAllTeacher5(searchWord,biao,idd,divs){
        var url="/huiyi/findByTeacher";
        if(searchWord!=null && searchWord!=''){
            url+="?text="+searchWord;
        }
        $.get(url,function (d) {
            var dd=JSON.parse(d);
            for(var i=0;i<dd.length;i++){
                var tr = "<tr><td><button type='button' id='td_"+dd[i]['user_id']+"'>"+dd[i]['name']+"</button></td></tr>";
                $("#"+biao).append(tr);
                $("#td_"+dd[i]['user_id']).attr("onclick","queding('"+dd[i]['name']+"','"+idd+"','"+divs+"','"+biao+"')");
            }
        })

    }
    function queding(teacherName,idd,divs,biao){
        var asd=$("#"+idd).val();
        if(asd==null || asd==''){
            $("#"+idd).val(teacherName);

        }else{
            $("#"+idd).val(asd+","+teacherName);
        }

       /* $("#"+divs).attr("style", "display:none;");
        $("#"+biao).html('');*/
        stopPropagation();
    }


   function xuanze(idd,namedd,divs){
       $("#"+divs).attr("style", "display:block;");
       if(divs=="div1"){
           $("#div2").attr("style", "display:none;");
           $("#div3").attr("style", "display:none;");
           $("#div4").attr("style", "display:none;");
           $("#biao1").html('');
           $("#biao2").html('');
           $("#biao3").html('');
           $("#biao4").html('');
           search2('biao1',idd,namedd,divs);
       }
       if(divs=="div2"){

           $("#div1").attr("style", "display:none;");
           $("#div3").attr("style", "display:none;");
           $("#div4").attr("style", "display:none;");
           $("#biao1").html('');
           $("#biao2").html('');
           $("#biao3").html('');
           $("#biao4").html('');
           search3('biao2',idd,namedd,divs)
       }
       if(divs=="div3"){
           $("#div2").attr("style", "display:none;");
           $("#div1").attr("style", "display:none;");
           $("#div4").attr("style", "display:none;");
           $("#biao1").html('');
           $("#biao2").html('');
           $("#biao3").html('');
           $("#biao4").html('');
           search4('biao3',idd,namedd,divs)
       }
    }

    function search4(biao,idd,namedd,divs){
        $("#"+biao).html('');
       var text=$("#searchWord3").val();
        gelAllTeacher(text,biao,idd,namedd,divs);
    }

   function search2(biao,idd,namedd,divs){
       $("#"+biao).html('');
       var text=$("#searchWord").val();
       gelAllTeacher(text,biao,idd,namedd,divs);
   }
   function search3(biao,idd,namedd,divs){
       $("#"+biao).html('');
       var text=$("#searchWord2").val();
       gelAllTeacher(text,biao,idd,namedd,divs);
   }
   function gelAllTeacher(searchWord,biao,idd,namedd,divs){
       var url="";
       if(biao=="biao3"){
           url="/huiyi/findByHuiYiShenHeYuan";
           if(searchWord!=null && searchWord!=''){
               url+="?name="+searchWord;
           }
       }else if(biao=="biao1"){
           url="/huiyi/findByHuiYiGuanLiYuan";
           if(searchWord!=null && searchWord!=''){
               url+="?name="+searchWord;
           }
       } else{
           url="/huiyi/findByTeacher";
           if(searchWord!=null && searchWord!=''){
               url+="?text="+searchWord;
           }
       }

       $.get(url,function (d) {
           var dd=JSON.parse(d);
           for(var i=0;i<dd.length;i++){
               var tr = "<tr><td><button type='button' id='td_"+dd[i]['user_id']+"'>"+dd[i]['name']+"</button></td></tr>";
               $("#"+biao).append(tr);
               $("#td_"+dd[i]['user_id']).attr("onclick","quedingqq('"+dd[i]['user_id']+"','"+dd[i]['name']+"','"+idd+"','"+namedd+"','"+divs+"','"+biao+"')");
           }
       })

   }
   function quedingqq(userid,teacherName,idd,namedd,divs,biao){
    $("#"+idd).val(userid);
    $("#"+namedd).val(teacherName);
    $("#"+divs).attr("style", "display:none;");
    $("#"+biao).html('');
       stopPropagation();
   }

    $(function() {
        uploadFile();
        addOption('/juzuAndjiaozhigong/depentAll', "departmentId", "id", "name",${huiyi.departmentId})
        addOption('/huiyi/findByChangdi', "changdiId", "id", "changdi_name",${huiyi.changdiId})
        });
   // 阻止点击冒泡
   function stopPropagation(e) {
       e = e || window.event;
       if (e.stopPropagation) {
           e.stopPropagation();
       } else {
           e.cancelBubble = true;
       }
   }

    function addOption(url, id, valProperty, namePropety,add, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(add==obj[valProperty]){
                    $("#" +id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" +id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }

            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传会务附件',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div id="div_'+$jsonObj.uuid+'"><input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="'+$jsonObj.uuid+'" /><a href="'+$jsonObj.url+'">'+$jsonObj.realFileName+'</a><button onclick="chehui(div_'+$jsonObj.url+')" type="button">撤销</button></div>';
                $("#pictureUuid").val($jsonObj.uuid);

                $("#zpa").append(img);
            },
            onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("该浏览器无法使用!");
            },
        });
    }

function chehui(id) {
    $("#"+id).remove();
}
    //保存或更新修改
 function saveOrUpdate() {
            var val={}
            val.theme=$("#theme").val();
            val.departmentId=$("#departmentId").val();
            val.applicant=$("#applicant").val();
            val.applicantName=$("#applicantName").val();
            val.huiwufuzeId=$("#huiwufuzeId").val();
            val.huiwufuzeName=$("#huiwufuzeName").val();
            val.eventManager=$("#eventManager").val();
            val.eventManagerName=$("#eventManagerName").val();
            val.reviewer=$("#reviewer").val();
            val.reviewerName=$("#reviewerName").val();
            val.kaishiTime=$("#kaishiTime").val();
            val.changdiId=$("#changdiId").val();
            var leadership=$("#leadership").val();
            if(leadership!=null && leadership!=''){
                 val.leadership=leadership;
            }
             var activitySupplies=$("#activitySupplies").val();
             if(activitySupplies!=null && activitySupplies!=''){
                 val.activitySupplies=activitySupplies;
             }
             var equipment=$("#equipment").val();
             if(equipment!=null && equipment!=''){
                 val.equipment=equipment;
             }
             var meeting=$("#meeting").val();
             if(meeting!=null && meeting!=''){
                 val.meeting=meeting;
             }
            var fujianid=$("#fujianId").val();
            if(fujianid!=null && fujianid!=''){
                val.fujianId=fujianid;
            }
             var staff=$("#staff").val();
             if(staff!=null && staff!=''){
                 val.staff=staff;
             }
             var attendanceNumber=$("#attendanceNumber").val();
             if(attendanceNumber!=null && attendanceNumber!=''){
                 val.attendanceNumber=attendanceNumber;
             }
             var fujianId="";
             $("input[name='fujianUuid']").each(
                 function() {
                     if (fujianId != null && fujianId != '') {
                         fujianId += "," + $(this).val();
                     } else {
                         fujianId = $(this).val();
                     };
                 }
             )
             if(fujianId!=null && fujianId!=''){
                 val.fujianId=fujianId;
             }
            var fanhui=panduan(val);
            if(fanhui==="true"){
                var  ids=$("#huiyi_id").val();

               var url="";
                if ("" != ids && ids!=null) {
                    val.id=ids;
                    url = "${ctp}/huiyi/updateHuiYi";
                }else{
                    url="/huiyi/createHuiYi"
                }
                $.post(url, val, function(data, status) {
                    if("success" === status) {
                        if("success" === data) {
                            $.success('操作成功');
                            window.location.href="${ctp}/huiyi/getByAll?id=1&sub=asd";
                        }else {
                            $.error(data);
                        }
                    }else{
                        $.error("操作失败");
                    }
                });
            }else{
                $.error(fanhui);
            }
        }

    function panduan(val) {
        if(val.theme==null || val.theme==""){
            return "主题不能为空";
        }
        if(val.departmentId==null || val.departmentId==""){
            return "部门不能为空";
        }
        if(val.applicant==null || val.applicant==""){
            return "申请人不能为空";
        }
        if(val.applicantName==null || val.applicantName==""){
            return "申请人姓名不能为空";
        }
        if(val.huiwufuzeId==null || val.huiwufuzeId==""){
            return "会务负责任人不能为空";
        }
        if(val.huiwufuzeName==null || val.huiwufuzeName==""){
            return "会务负责任人姓名不能为空";
        }
        if(val.eventManager==null || val.eventManager==""){
            return "活动负责人不能为空";
        }
        if(val.eventManagerName==null || val.eventManagerName==""){
            return "活动负责人姓名不能为空";
        }
        if(val.reviewer==null || val.reviewer==""){
            return "审核人不能为空";
        }
        if(val.reviewerName==null || val.reviewerName==""){
            return "审核人姓名不能为空";
        }
        if(val.kaishiTime==null || val.kaishiTime==""){
            return "开始时间不能为空";
        }
        if(val.changdiId==null || val.changdiId==""){
            return "场地不能为空";
        }
        return "true";
    }

</script>
</html>