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
    <%-- <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-datetimepicker.min.js"></script> --%>
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>获奖申请</title>
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
        <jsp:param value="获奖申请" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <c:if test="${type==1}">
                        <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请获奖</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                    </c:if>
                      <c:if test="${type==2}">
                          <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请教赛获奖</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                      </c:if>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">活动主题<span style="color:red">*</span></label>
                                    <div>
                                        <input type="text" id="theme" name="theme"  placeholder="请输入标题，少于20个中文字符" class="span6 left-stripe {required : true,maxlength:40}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖类型<span style="color:red">*</span>
                                        </label>
                                        <c:if test="${type==1}">
                                            <select id="types" name="types">
                                                <option>获奖</option>

                                            </select>
                                        </c:if>
                                        <c:if test="${type==2}">
                                        <select id="types" name="types">
                                            <option>论文</option>
                                            <option>课题</option>
                                            <option>公开课</option>
                                            <option>教师竞赛</option>
                                        </c:if>
                                        </select>

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖属性<span style="color:red">*</span>
                                        </label>
                                        <select id="shuxing" name="shuxing">
                                            <option>教育部门</option>
                                            <option>非教育部门</option>
                                        </select>
                                    </div>
                                    <c:if test="${type==1}">
                                        <div class="control-group">
                                            <label class="control-label">获奖学生</label>
                                            <div class="left">
                                                <div class="textarea">
                                                    <input onclick="openstudent()"  type="button" id="huojiangStudent" name="huojiangStudent" style="width:800px;height:100px;"/>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="control-group">
                                        <label class="control-label">获奖教师</label>
                                        <div class="left">
                                            <div class="textarea">
                                                <input type="button" id="huojiangTeacher" onclick="openteacher()" name="huojiangTeacher" style="width:800px;height:100px;"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">作品名称或荣誉称号</label>
                                        <div>
                                            <input style="position:relative;top:-12px;" type="text" id="attractName" name="attractName"  class="span3 left-stripe {maxlength:500}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖级别<span style="color:red">*</span>
                                        </label>
                                        <select id="huojiangjibie" name="huojiangjibie">

                                        </select>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            等次或发表刊物<span style="color:red">*</span>
                                        </label>
                                        <select id="dengci" name="dengci">

                                        </select>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">
                                            获奖日期<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="huojiangTime" name="huojiangTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               placeholder="xxxx-xx-xx"
                                               value="${huiyi.kaishiTime}">
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">发奖单位</label>
                                        <div>
                                            <input style="position:relative;top:-12px;" type="text" id="fajiangDanwei" name="fajiangDanwei" value="${huiyi.attendanceNumber}" class="span3 left-stripe {maxlength:500}">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            证书图片：
                                        </label>
                                        <div class="controls">
                                            <div id="zpa" style="display:inline-block;">

                                            </div>
                                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                                                <input type="hidden" id="uploader" name="uploader2">
                                            <span id="tp_queue"></span>

                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">发布</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="groupList"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">选择获奖学生</span>
        <div class="off" onclick="closestudent();">X</div></div>
    <div class="clearfix list-search-bar x-search">
        <div class="select_b">
            <div class="select_div">
                <span>年级：</span>
                <select id="gradeId" name="gradeId">
                    <option value="">全校</option>
                </select>
            </div>
            <div class="select_div">
                <span>班级：</span>
                <select id="teamId" name="teamId">
                    <option value="">全部</option>
                </select>
            </div>
            <div class="select_div">
                <span>学生姓名：</span>
                <input id="studentNames" name="studentNames" class=""  style="width:200px;padding-top: 4px;"/>
            </div>
            <button type="button" class="btn btn-primary" onclick="studentList()">查询</button>
            <div class="clear"></div>
        </div>
    </div>
    <div style="height:80px;width: 100%;">
        <div style="text-align: left">
        <span>已选获奖学生</span>
        </div>
        <div id="yixuanStudent" style="width: 100%;height: 50px;margin: 2px;position:relative">

        </div>
    </div>

      <table id="studentBiao" width="100%" border="1">
          <thead>
            <tr>
                <td>姓名</td>
                <td>班级</td>
                <td>性别</td>
                <td>在读状况</td>
                <td>操作</td>
            </tr>
          </thead>
          <tbody id="tbiao">

          </tbody>
      </table>
</div>


<div id="groupLists"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">选择获奖教师</span>
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
</body>
<script type="text/javascript">
    function chehui(id) {
        $("#"+id).remove();
    }
    //保存或更新修改
    function saveOrUpdate() {
        var val={}
        val.theme=$("#theme").val();
        val.type=$("#types").val();
        val.shuXing=$("#shuxing").val();
        if(${type==1}) {
            var studentIds = "";
            $("input[name='studentId']").each(
                function () {
                    if (studentIds != null && studentIds != '') {
                        studentIds += "," + $(this).val();
                    } else {
                        studentIds = $(this).val();
                    }
                }
            )
            val.studentIds=studentIds;
        }
        val.studentNames=$("#huojiangStudent").val();
        val.teacherNames=$("#huojiangTeacher").val();
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
        val.teacherIds=teacherIds;
        val.nameWoke=$("#attractName").val();
        val.winningLevel=$("#huojiangjibie").val();
        val.dengci=$("#dengci").val();
        val.winningTime=$("#huojiangTime").val();
        val.allocated=$("#fajiangDanwei").val();
        var pictureId="";
        $("input[name='pictureId']").each(
            function() {
                if (pictureId != null && pictureId != '') {
                    pictureId += "," + $(this).val();
                } else {
                    pictureId = $(this).val();
                };
            }
        )
        if(pictureId!=null && pictureId!=''){
            val.pictureId=pictureId;
        }
        var fanhui=panduan(val);
        if(fanhui==="true"){
            var  ids=$("#huiyi_id").val();
            var url="/huojiang/create"
            $.post(url, val, function(data, status) {
                if("success" === status) {
                    if("success" === data) {
                        $.success('操作成功');
                        $("#yixuanStudent").html("");
                        $("#yixuanTeacher").html("");
                        window.location.href="${ctp}/huojiang/findByAll?type=0&sub=asd";
                    }else if("fail" === data.info){
                        $.error("编号重复，请重新输入！");
                    } else {
                        $.error(data);
                    }
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }else{
            $.error(fanhui);
        }
    }

    function panduan(val) {
        if(val.theme==null || val.theme==""){
            return "主题不能为空";
        }
        if(val.type==null || val.type==""){
            return "获奖类型不能为空";
        }
        if(val.shuXing==null || val.shuXing==""){
            return "获奖属性不能为空";
        }
        if(${type==1}) {
            if (val.studentIds == null || val.studentIds == "") {
                return "获奖学生id不能为空";
            }
            if (val.studentNames == null || val.studentNames == "") {
                return "获奖学生姓名不能为空";
            }
        }
        if(val.teacherIds==null || val.teacherIds==""){
            return "获奖教师id不能为空";
        }
        if(val.teacherNames==null || val.teacherNames==""){
            return "获奖教师姓名不能为空";
        }
        if(val.nameWoke==null || val.nameWoke==""){
            return "作品名称不能为空";
        }
        if(val.winningLevel==null || val.winningLevel==""){
            return "获奖级别不能为空";
        }
        if(val.dengci==null || val.dengci==""){
            return "获奖等次不能为空";
        }
        if(val.winningTime==null || val.winningTime==""){
            return "获奖时间不能为空";
        }
        if(val.allocated==null || val.allocated==""){
            return "发奖单位不能为空";
        }
        return "true";
    }
    //----------------------教师堂狂------
    //打开堂狂
    function openteacher() {
        $("#groupLists").attr("style", "display:block;");
        $("#groupList").attr("style", "display:none;");
    }
    //关闭堂狂
    function closeteacher() {
        var stuname="";
        $("input[name='teaName']").each(
            function() {
                if (stuname != null && stuname != '') {
                    stuname += "," + $(this).val();
                } else {
                    stuname = $(this).val();
                };
            }
        )
        $("#huojiangTeacher").val(stuname);
        $("#groupLists").attr("style", "display:none;");
    }
    //教师表
    function teacherList(){
        var teacherNames=$("#teacherNames").val();
        var url="/huojiang/findByTeacher?sub=ad";
        if(teacherNames!=null && teacherNames!=''){
            url+="&teacherName="+teacherNames;
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
    //----------------------学生堂狂------
    //打开堂狂
    function openstudent() {
            $("#groupList").attr("style", "display:block;");
        $("#groupLists").attr("style", "display:none;");
    }
    //关闭堂狂
    function closestudent() {
        var stuname="";
        $("input[name='stuName']").each(
            function() {
                if (stuname != null && stuname != '') {
                    stuname += "," + $(this).val();
                } else {
                    stuname = $(this).val();
                };
            }
        )
        $("#huojiangStudent").val(stuname);
        $("#groupList").attr("style", "display:none;");
    }
    //学生表
    function studentList(){
        var gradeId=$("#gradeId").val();
        var teamId=$("#teamId").val();
        var studentNames=$("#studentNames").val();
        var url="/huojiang/findByStudent?sub=ad";
        if(gradeId!=null && gradeId!=''){
            url+="&gradeId="+gradeId;
        }
        if(teamId!=null && teamId!=''){
            url+="&teamId="+teamId;
        }
        if(studentNames!=null && studentNames!=''){
            url+="&studentName="+studentNames;
        }
        $.get(url,function(d){
            $("#tbiao").html("");
            var obj=JSON.parse(d);
            for(var a=0;a<obj.length;a++){
                var student="<tr><td>"+obj[a]['name']+"</td><td>"+obj[a]['team_name']+"</td>";
                if(obj[a]['sex']==0){
                    student+="<td>男</td>";
                }else if(obj[a]['sex']==1){
                    student+="<td>女</td>";
                }else{
                    student+="<td>男女男</td>";
                }
                if(obj[a]['study_state']=='01'){
                    student+="<td>在读</td>";
                }else if(obj[a]['study_state']=='07'){
                    student+="<td>毕业</td>";
                }
                student+="<td><button id='td_"+obj[a]['id']+"'>确定</button></td></tr>";
                $("#tbiao").append(student);
                $("#td_"+obj[a]['id']).attr("onclick","studentQueding('"+obj[a]['id']+"','"+obj[a]['name']+"')");
            }
        })
    }
   function studentQueding(id,name){
        var div="<div style='display: inline;float: left;position: relative;'  id='iii_"+id+"' class='studentQueDing'><div class='chexiao'  onclick='studentGuanBi("+id+")'>X</div><input  name='studentId' style='display: none' type='text' value='"+id+"'/><input readonly name='stuName' style='height: 40px;width: 80px;text-align: center;cursor: default;border: 1px solid #d9d9d9' value='"+name+"' /></div>";
        $("#yixuanStudent").append(div);
    }
    //删除选择的学生
    function studentGuanBi(id){
        $("#iii_"+id).remove();
    }
    //--------------------------end---------------------
    //初始化
    $(function() {
        teacherList();
        studentList();
        uploadFile();
        addOption('/huojiang/huoJiangJiBie', "huojiangjibie", "id", "name")
        addOption('/huojiang/huojiangdengci', "dengci", "id", "name")
        addOption('/huojiang/findByGrade', "gradeId", "id", "name")
        addOption('/huojiang/findByTeam', "teamId", "id", "name")
    });
    //绑定下拉框改变事件
    $("#gradeId").change(function(){
        var gradeId = $("#gradeId").val();
        $("#teamId").html("<option value=''>全部班级</option>")
        if(gradeId!=null && gradeId!=''){
            //添加banji
            addOption('/huojiang/findByTeam?gradeId='+gradeId,"teamId", "id", "name")
        }else{
            //添加班级
            addOption('/huojiang/findByTeam', "teamId", "id", "name")
        }


    })
    // 阻止点击冒泡
    function stopPropagation(e) {
        e = e || window.event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
    }

    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                    $("#" +id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
    function change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
    }
    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传比赛图片',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">取消</a><input name="pictureId" style="display: none" type="text" value="'+$jsonObj.uuid+'"/>' +
                    '<img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'+$jsonObj.url+'"/>&nbsp;&nbsp;&nbsp;</div>';
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

</script>
</html>