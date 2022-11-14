<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>

<head>
    <title>资产排查编辑</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
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
        #groupList,#groupLists{
            background: #f5f5f8;
            position: fixed;
            text-align:center;
            width: 60%;
            height: 50%;
            box-shadow: 0 3px 4px 0 #00000020, 0 4px 12px #00000020;
            border: 1px solid #d9d9d9;
        }
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
        .off{
            position:absolute;
            top:5px;
            right:10px;
            color: white;
        }
        /*屏幕中间*/
        #groupList,#groupLists{
            right: 20%;
            top: 20%;
        }
    </style>
    <style type="text/css">
        table {
            /*设置相邻单元格的边框间的距离*/
            border-spacing: 0;
            /*表格设置合并边框模型*/
            border-collapse: collapse;
            text-align: center;
        }
        /*关键设置 tbody出现滚动条*/
        table tbody {
            display: block;
            height: 220px;
            overflow-y: scroll;
        }

        table thead tr,
        tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
        table thead {
            width: calc( 100% - 1em)
        }
        table thead th {
            background: #ccc;
        }

    </style>
    <style type="text/css">
        button.btn.btn-primary {
            height: 30px;
            background: #01316c;
            padding: 0 20px;
            border-radius: 3px;
        }

        div#yixuanStudent {
            overflow: scroll;
        }
        div#yixuanTeacher {
            overflow: scroll;
        }
        div#yixuanStudent::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
        div#yixuanTeacher::-webkit-scrollbar {
            width: 0;
            height: 0;
        }

        .chexiao {
            right: 5px;
            top: 1px;
            position: absolute;
            cursor: pointer;
        }

        table {
            border-spacing: 0;
            border-collapse: collapse;
            text-align: center;
            width: 98%;
            margin-left: 1%;
        }

        table tbody {
            display: block;
            height: 220px;
            overflow: auto;
        }

        table#studentBiao tbody::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
        table#teacherBiao tbody::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="资产排查编辑" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">资产排查编辑</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">
                                        排查人<span style="color:red">*</span>：
                                    </label>
                                    <div class="textarea">
                                        <input type="button" id="paichaName" onclick="openteacher()" name="paichaName" style="width:800px;height:100px;" value="${screening.paichaUserName}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            排查时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="paichaTime" name="paichaTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               value="${screening.paichaTime}"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                               placeholder="xxxx-xx-xx xx:xx:xx">

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            排查区域<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input type="text" value="${screening.screeningArea}" id="screeningArea"  name="screeningArea"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            水电<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="waterElectricity"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="waterElectricity" value="1"/>
                                            不正常 </label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            安全隐患<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="trouble"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="trouble" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            建筑质量<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="construction"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="construction" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            设施设备<span style="color:red">*</span>：
                                        </label>
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="facilities"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="facilities" value="1"/>
                                            不正常 </label>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            其他：
                                        </label>
                                        <input type="text" id="qitaName" value="${screening.qitaName}" />
                                        <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="qita"
                                                   value="0" Checked />
                                            正常 </label>
                                        <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                            <input type="radio" name="qita" value="1"/>
                                            不正常 </label>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            备注：
                                        </label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea style="width:200px;height:100px;padding-top: 4px;"  type="text" id="beizhu" name="beizhu" class="span4" autocomplete="off">${screening.beizhu}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            附件：
                                        </label>
                                        <div class="controls">
                                            <div id="zpa" style="display:inline-block;">
                                                <c:forEach items="${screening.list}" var="item" varStatus="i">
                                                    <div id="div_${item['uuid']}">
                                                        <input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="${item['uuid']}" />
                                                        <a href="${item['url']}">${item['fileName']}</a>
                                                        <button onclick="chehui('div_${item['uuid']}')" type="button">撤销</button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <input type="hidden" id="uploader" name="uploader2">
                                            <span id="tp_queue"></span>

                                            <div class="clear"></div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">确定</button>
                    </p>
                    <input type="hidden" id="screening_id" value="${screening.id}"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="groupLists"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">选择排查人员</span>
        <div class="off" onclick="closeteacher();">X</div></div>
    <div class="clearfix list-search-bar x-search">
        <div class="select_b">
            <div class="select_div">
                <span>教师姓名：</span>
                <input id="teacherNames" name="teacherNames" class=""  style="width:200px;padding-top: 4px;"/>
            </div>
            <button type="button" class="btn btn-primary" style="color: chartreuse" onclick="teacherList()">查询</button>
            <div class="clear"></div>
        </div>
    </div>
    <div>
        <div style="text-align: left">
            <span>已选获奖教师</span>
        </div>
        <div id="yixuanTeacher" style="width: 100%;height: 50px;margin: 2px;position:relative">

        </div>
    </div>
    <table id="teacherBiao" width="100%" border="1">
        <thead>
        <tr>
            <td>姓名</td>
            <td>性别</td>
            <td>联系方式</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody id="thbiao">

        </tbody>
    </table>
</div>
<script type="text/javascript">
    //打开堂狂
    function openteacher() {
        $("#groupLists").attr("style", "display:block;");
    }
    //关闭堂狂
    function closeteacher() {
        var stuname="";
        $("input[name='teaName']").each(
            function() {
                stuname += "," + $(this).val();
            }
        )
        $("#paichaName").val(stuname);
        $("#groupLists").attr("style", "display:none;");
    }
    //教师表
    function teacherList(){
        var teacherNames=$("#teacherNames").val();
        var url="/Screening/paiChaRenYuan";
        if(teacherNames!=null && teacherNames!=''){
            url+="?name="+teacherNames;
        }
        $.get(url,function(d){
            $("#thbiao").html("");
            var obj=JSON.parse(d);
            for(var a=0;a<obj.length;a++){
                var teacher="<tr><td>"+obj[a]['name']+"</td>";
                if(obj[a]['sex']==1){
                    teacher+="<td>男</td>";
                }else if(obj[a]['sex']==2){
                    teacher+="<td>女</td>";
                }else {
                    teacher+="<td>男女男</td>";
                }
                teacher+="<td>"+obj[a]['mobile']+"</td><td><button id='td_"+obj[a]['id']+"'>确定</button></td></tr>";
                $("#thbiao").append(teacher);
                $("#td_"+obj[a]['id']).attr("onclick","teacherQueding('"+obj[a]['id']+"','"+obj[a]['name']+"')");
            }
        })
    }
    //确定老师
    function teacherQueding(id,name){
        var a=0;
        $("input[name='teachId']").each(
            function () {
                if(id==$(this).val()){
                    a++;
                }
            }
        )
        if(a==0){
            var div="<div style='display: inline;float: left;position: relative;' id='ddd_"+id+"'><div onclick='teacherGuanBi("+id+")' class='chexiao'>X</div><input name='teachId' style='display: none' type='text' value='"+id+"'/><input readonly style='height: 40px;width: 80px;text-align: center;cursor: default;border: 1px solid #d9d9d9' name='teaName' value='"+name+"' /></div>";
            $("#yixuanTeacher").append(div);
        }
    }
    function teacherGuanBi(id){
        $("#ddd_"+id).remove();
    }

    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传资产排查附件',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div id="div_'+$jsonObj.uuid+'"><input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="'+$jsonObj.uuid+'" /><a href="'+$jsonObj.url+'">'+$jsonObj.realFileName+'</a><button onclick="chehui(div_'+$jsonObj.url+')" type="button">撤销</button></div>';
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
        var val={};
        var loader = new loadLayer();
        val.paichaUserName=$("#paichaName").val();
        val.screeningArea=$("#screeningArea").val();
        var teacherIds = "";
        $("input[name='teachId']").each(
            function () {
                if (teacherIds != null && teacherIds != '') {
                    teacherIds += "," + $(this).val();
                } else {
                    teacherIds = $(this).val();
                }
            }
        )
        val.paichaUserId=teacherIds;
        val.paichaTime=$("#paichaTime").val();
        /*水电*/
        var obj = document.getElementsByName("waterElectricity");
        for(var i = 0; i < obj.length; i ++) {
            if (obj[i].checked) {
                val.waterElectricity= obj[i].value;
            }
        }
        /*安全隐患*/
        var trouble = document.getElementsByName("trouble");
        for(var i = 0; i < trouble.length; i ++) {
            if (trouble[i].checked) {
                val.trouble= trouble[i].value;
            }
        }
        /*建筑质量*/
        var construction = document.getElementsByName("construction");
        for(var i = 0; i < construction.length; i ++) {
            if (construction[i].checked) {
                val.construction= construction[i].value;
            }
        }
        /*设施设备*/
        var facilities = document.getElementsByName("facilities");
        for(var i = 0; i < facilities.length; i ++) {
            if (facilities[i].checked) {
                val.facilities= facilities[i].value;
            }
        }
        /*其他*/
        var qita = document.getElementsByName("qita");
        for(var i = 0; i < qita.length; i ++) {
            if (qita[i].checked) {
                val.qita= qita[i].value;
            }
        }
        val.qitaName=$("#qitaName").val();
        var pictureId="";
        $("input[name='fujianUuid']").each(
            function() {
                if (pictureId != null && pictureId != '') {
                    pictureId += "," + $(this).val();
                } else {
                    pictureId = $(this).val();
                };
            }
        )
        val.fujianUuid=pictureId;
        var beizhu=$("#beizhu").val();
        var panduans=panduan(val);
        if(panduans=='true'){
            val.beizhu=beizhu;
            var url="/Screening/update";
            var id=$("#screening_id").val();
            val.id=id;
            loader.show();
            $.post(url, val, function (data, status) {
                if (status=="success") {
                    if ("success" === data) {
                        $.success('操作成功');
                        window.location.href="${ctp}/Screening/findByAll?sub=asd";
                    } else {
                        $.error("操作失败");
                    }
                }
                loader.close();
            });
        }else{
            $.error(panduans);
        }

    }
    function panduan(val) {
        if(val.paichaUserId==null || val.paichaUserId==""){
            return "排查人id不能为空";
        }
        if(val.paichaUserName==null || val.paichaUserName==""){
            return "排查人姓名不能为空";
        }
        if(val.screeningArea==null || val.screeningArea==""){
            return "排查地址不能为空";
        }
        if(val.paichaTime==null || val.paichaTime==""){
            return "排查时间不能为空";
        }
        if(val.waterElectricity==null || val.waterElectricity==""){
            return "水电不能为空";
        }
        if(val.trouble==null || val.trouble==""){
            return "安全隐患不能为空";
        }
        if(val.construction==null || val.construction==""){
            return "建筑质量不能为空";
        }
        if(val.facilities==null || val.facilities==""){
            return "设施设备不能为空";
        }
        return "true";
    }
    $(function () {
        teacherList();
        chushi();
        uploadFile();
        var waterElectricity=${screening.waterElectricity};
        if(waterElectricity==0){
            $("input[name='waterElectricity']:radio:first").attr("checked","checked");
        }else{
            $("input[name='waterElectricity']:radio:last").attr("checked","checked");
        }

        var trouble=${screening.trouble};
        if(trouble==0){
            $("input[name='trouble']:radio:first").attr("checked","checked");
        }else{
            $("input[name='trouble']:radio:last").attr("checked","checked");
        }

        var construction=${screening.construction};
        if(construction==0){
            $("input[name='construction']:radio:first").attr("checked","checked");
        }else{
            $("input[name='construction']:radio:last").attr("checked","checked");
        }

        var facilities=${screening.facilities};
        if(facilities==0){
            $("input[name='facilities']:radio:first").attr("checked","checked");
        }else{
            $("input[name='facilities']:radio:last").attr("checked","checked");
        }
        var qita=${screening.qita};
        if(qita==0){
            $("input[name='qita']:radio:first").attr("checked","checked");
        }else{
            $("input[name='qita']:radio:last").attr("checked","checked");
        }
    })
    function chushi() {
        var benren="${screening.paichaUserId}";
        var benrenName="${screening.paichaUserName}";
        var arr=benren.split(",");
        var arrName=benrenName.split(",");
        for(var i=0;i<arr.length;i++){
            var div="<div style='display: inline;float: left;position: relative;' id='ddd_"+arr[i]+"'><input name='teachId' style='display: none' type='text' value='"+arr[i]+"'/><input readonly style='height: 40px;width: 80px;text-align: center;cursor: default;border: 1px solid #d9d9d9' name='teaName' value='"+arrName[i]+"'/></div>";
            $("#yixuanTeacher").append(div);
        }

    }
</script>
</body>
</html>
