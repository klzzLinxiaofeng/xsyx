<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <title>学科素养</title>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
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
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    学科素养
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>学年：</span>
                            <select id="xn" name="xn" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div"><span>学期：</span>
                            <select id="xq" name="xq" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="teamId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>

                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                      <%--  <button type="button" class="btn btn-primary" onclick="add()">一键添加评语</button>--%>
                        <button type="button" class="btn btn-primary" onclick="donwload()">下载分数导入模板</button>
                        <button type="button" class="btn btn-primary" onclick="upload()">导入学科素养学生分数</button>

                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th><input class="user_checkbox" type="checkbox" name="ids"></th>
                            <th>序号</th>
                            <th>班级</th>
                            <th>学生姓名</th>
                            <th>科目</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                       <tbody id="publicClass_list_content">
                          <jsp:include page="./sujectlist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/Literacy/student/list?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="groupList"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">学科素养</span>
        <div class="off" onclick="closestudent();">X</div></div>
    <div class="clearfix list-search-bar x-search">
        <button onclick="shuaxin()">刷新</button>
        <div class="content-widgets">
            <div class="widget-container">
                <table class="responsive table table-striped">
                    <thead>
                    <tr role="row">
                        <th>班级</th>
                        <th>学生姓名</th>
                        <th>科目</th>
                        <th>学科素养指标</th>
                        <th>最大分值</th>
                        <th>分数</th>
                        <th>评语</th>
                        <th class="caozuo" style="max-width: 250px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="pingfenBody">

                    </tbody>
                </table>
                <div class="clear">
                    <input style="display: none" id="studentId"/>
                    <input style="display: none" id="subjectId"/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
     function closestudent() {
         $("#groupList").attr("style", "display:none;");
     }
     function pinjia(studentId,subjectId) {
         $("#studentId").val(studentId);
         $("#subjectId").val(subjectId);
         var url='/Literacy/student/pinjialist?sub=sss&studentId='+studentId+'&subjectId='+subjectId+'&schoolYear='+$("#xn").val()+'&schoolTrem='+$("#xq").val();
         $("#pingfenBody").html("");
         $.get(url,function (obj) {
             obj=JSON.parse(obj);
             for(var a=0;a<obj.length;a++){
                 var student="<tr><td>"+obj[a]['teamName']+"</td>" +
                     "<td>"+obj[a]['stuName']+"</td>" +
                     "<td>"+obj[a]['subName']+"</td>" +
                     "<td>"+obj[a]['literacyName']+"</td>" +
                     "<td>"+obj[a]['score']+"</td>" +
                     "<td>"+obj[a]['fenshu']+"</td>" +
                     "<td>"+obj[a]['pingyu']+"</td>";
                 student+="<td><button id='td_"+obj[a]['id']+"'>添加评语</button></td></tr>";
                 $("#pingfenBody").append(student);
                 $("#td_"+obj[a]['id']).attr("onclick","pingyu('"+obj[a]['id']+"')");
             }
         })
         $("#groupList").attr("style", "display:block;");
     }
     function shuaxin() {
        var studentId=$("#studentId").val();
        var subjectId=$("#subjectId").val();
         pinjia(studentId,subjectId);
     }
     function pingyu(id) {
         $.initWinOnTopFromLeft('添加评语', '/Literacy/student/findBypingyu?id='+id, '1000', '550');
     }
</script>
<script type="text/javascript">
    $(function () {
        initSelect();
    })
    function search() {
        var val = {};
        var xn = $("#xn").val();
        var nj = $("#nj").val();
        var km = $("#km").val();
        var bj = $("#bj").val();
        if (xn != null && xn != "") {
            val.xn = xn;
        }

        if (nj != null && nj != "") {
            val.nj = nj;
        }
        if (km != null && km != "") {
            val.km = km;
        }
        if (bj != null && bj != "") {
            val.bj = bj;
        }
        var id = "publicClass_list_content";
        var url = "/Literacy/student/list?sub=list";
        myPagination(id, val, url);
    }
    function  add() {
        var url="/Literacy/student/UpdatePingyuyi?sub=qw";
        var xn = $("#xn").val();
        var nj = $("#nj").val();
        var km = $("#km").val();
        var xq = $("#xq").val();
        if (xn != null && xn != "") {
           url+="&schoolYear="+xn;
        }
        if (nj != null && nj != "") {
            url+="&gradeId="+nj;
        }
        if (km != null && km != "") {
            url+="&subjectId="+km;
        }
        if (xq != null && xq != "") {
            url+="&schoolTrem="+xq;
        }
        $.success("评价成功");
        $.get(url,function (d) {

        })
        loader.close();
    }

   /* //评分录入
    function pinjia(studentId,subjectId) {
        $.initWinOnTopFromLeft('学科素养分数', '/Literacy/student/pinjialist/?sub=sss&studentId='+studentId+'&subjectId='+subjectId+'&schoolYear='+$("#xn").val()+'&schoolTrem='+$("#xq").val(), '1400', '750');
    }*/
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear=' + d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }

        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#nj").html('<option value="">请选择</option>');
            $("#xq").html('');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#nj").change(function(){
            //$("#bj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加班级
            $("#bj").html('<option value="">请选择</option>');
            //添加班级
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
            if($(this).val()!="") {
                //添加科目
                addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
            }
        })
    function addOptionxq(url,id,valProperty,namePropety,callback){
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                if(defaultTerm==obj[valProperty]) {
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                }
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }
    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

</script>
<script type="text/javascript">
    function  donwload(){
        $.initWinOnTopFromLeft('下载学科素养分数导入模板', '/Literacy/student/XiaZaiView', '1200', '650');
    }
    function  upload(){
        $.initWinOnTopFromLeft('导入学科素养分数', '/Literacy/student/DaoRuView', '1200', '650');
    }
</script>

</body>
</html>
