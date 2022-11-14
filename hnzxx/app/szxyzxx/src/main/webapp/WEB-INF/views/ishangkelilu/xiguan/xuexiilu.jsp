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
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>学习习惯评价记录</title>
    <style>
        #downLoadExcel {
            padding: 10px 20px;
            background-color: #e88a05;
            color: white;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    学习习惯评价记录
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >课堂评价导出</a>
                        <a id="downLoadExcel3"  class="a2" href="#" onclick="daochupingjia();" class="a2" >课堂评价公示导出</a>


                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div"><span>学年：</span>
                            <select id="schoolYear" name="schoolYear" class="chzn-select" style="width:200px;padding-top: 4px;">

                            </select>
                        </div> <div class="select_div"><span>学期：</span>
                        <select id="schoolTrem" name="schoolTrem" class="chzn-select" style="width:200px;padding-top: 4px;">

                        </select>
                    </div>

                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <div class="select_div"><span>关键字：</span>
                                <input type="text" id="keyword" name="keyword"
                                       style="width:200px;padding-top: 4px;" class="span4" placeholder="学生姓名"
                                       value="">
                        </div>
                        <div class="select_div">
                            <span>发布时间：</span>
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="endDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                   value="">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>班级</th>
                            <th>学生姓名</th>
                            <th>科目</th>
                            <th>评价分数</th>
                            <th>评价类型</th>
                            <th>评语</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./xuexijilulist.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/study/habits/StudyXiGuan?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function search() {
        var val = {};
        var xn =$("#schoolYear").val();
        var xq =$("#schoolTrem").val();

        var km =$("#km").val();
        var nj =$("#nj").val();
        var bj =$("#bj").val();
        var startTime=$("#startDate").val();
        var endTime=$("#endDate").val();
        var keyword =$("#keyword").val();
        val.schoolYear=xn;
        val.schoolTrem=xq;
        if (nj != null && nj != "") {
            val.gradeId = nj;

            if (bj != null && bj != "") {
                val.teamId = bj;
            }
            else{
                $.error("请选择班级");
            }
        }
        if (km != null && km != "") {
            val.subjectId = km;
        }
        if (keyword != null && keyword != "") {
            val.studentName = keyword;
        }
        if(startTime!=null && startTime !=""){
            val.startTime=startTime+" 00:00:00";
        }
        if(endTime!=null && endTime !=""){
            var data=new Date(endTime);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate();
            val.endTime=year+"-"+mounth+"-"+day+" 23:59:59";
        }
        var id = "publicClass_list_content";
        var url = "/study/habits/StudyXiGuan?sub=list&qwe=asd";
        myPagination(id, val, url);

    }
 function daochupingjia(){
     $.initWinOnTopFromLeft('课堂评价公示导出', '/KeTangGongShi/daochuview', '800', '650');
 }

    $(function ()  {
        initSelect();
        var asd=${stats};
        if(!asd){
        $.error("登陆帐号非教师账号");
        }else{

        }
    })
    function pingjia(id){
        var url="/study/habits/StudyBianJi?id="+id;
        $.initWinOnTopFromLeft('学习习惯评价', url, '800', '650');
    }

    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name",function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "schoolTrem", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }

    //绑定下拉框改变事件
    $("#schoolYear").change(function(){
        $("#schoolTrem").html("");
        $("#nj").html('<option value="">全部</option>');
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$("#schoolYear").val(), "schoolTrem", "code", "name");
        addOption('/teach/grade/list/json?schoolYear=' + $("#schoolYear").val(), "nj", "id", "name")


    })
    //绑定下拉框改变事件
    $("#nj").change(function(){
        $("#bj").html('<option value="">全部</option>');
        addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
    })
    $("#bj").change(function(){
        $("#km").html('<option value="">全科目</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
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

    function daochu() {

        $.initWinOnTopFromLeft('学习习惯评价记录导出', '/study/habits/daochuTiaoZhuan', '800', '650');
    }


</script>
</body>
</html>
