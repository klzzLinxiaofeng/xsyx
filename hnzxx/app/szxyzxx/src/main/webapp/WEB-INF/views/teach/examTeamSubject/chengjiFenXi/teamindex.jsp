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
    <title>班级成绩分析</title>
    <style>
        #publicClass_list_content {
            font-size: 100px;
        }
    </style>
</head>
<body>
<%--<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>--%>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    班级题目成绩分析
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出班级题目得分分析</a>

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
                                    style="width:200px;padding-top: 4px;">
                            </select>
                        </div>
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>班级：</span>
                            <select id="bj" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>考试名称：</span>
                            <select id="ks" name="examName" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">


                    </table>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        initSelect();
        var strs='<thead><tr role="row"><th>题号</th><th>分值</th><th>班级得分率</th><th>满分率</th><th>优秀率</th><th>合格率</th></tr></thead><tbody id="publicClass_list_content"></tbody>';
        $("#data-table").append(strs);
    });
    function search() {
        var val = {};
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km =$("#km").val();
        var xs=$("#ks").val();
        if (xn != null && xn != "") {
            val.schoolYear = xn;
            if (xq != null && xq != "") {
                val.schoolTrem = xq;
                if (nj != null && nj != "") {
                    val.gradeId = nj;
                    if (bj != null && bj != "") {
                        val.teamId = bj;
                        if(xs!=null && xs !=""){
                            val.examId=xs;
                            if (km != null && km != "") {
                                val.subjectCode = km;
                                var url = "/scoreFenXi/findByTiMuFenXi";
                                $.post(url,val,function (d) {
                                    var dd=JSON.parse(d);
                                    var strs="<thead><tr role='row'><th>题号</th><th>分值</th><th>班级得分率</th><th>满分率</th><th>优秀率</th><th>合格率</th></tr></thead><tbody id='publicClass_list_content'>";

                                    for(var a=0;a<dd.length;a++){
                                        strs+="<tr><td>"+dd[a].ordere+"</td><td>"+dd[a].fenzhi+"</td><td>"+dd[a].bjdl+"%</td><td>"+dd[a].mfl+"%</td><td>"+dd[a].yxl+"%</td><td>"+dd[a].hgl+"%</td></tr>";
                                    }
                                    strs+="</tbody>";
                                    $("#data-table").html("");
                                    $("#data-table").append(strs);
                                })
                            }else{
                                $.error("请选择科目");
                            }
                        }else{
                            $.error("请选择考试");
                        }
                    }else{
                        $.error("请选择班级");
                    }

                }else{
                    $.error("请选择年级");
                }
            }else{
                $.error("请选择学期");
            }
        }else{
            $.error("请选择学年");
        }
    }
    function daochu() {
        var url = "/scoreFenXi/daochuTeamFenXi";
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km =$("#km").val();
        var xs=$("#ks").val();
        if (xn != null && xn != "") {
            url+="?schoolYear="+xn;
            if (xq != null && xq != "") {
                url+="&schoolTrem="+xq;
                if (nj != null && nj != "") {
                    url+="&gradeId="+nj;
                    if (bj != null && bj != "") {
                        url+="&teamId="+bj;
                        if(xs!=null && xs !=""){
                            url+="&examId="+xs;
                            if (km != null && km != "") {
                                url+="&subjectCode="+km;
                                $.success('下载成功');
                                window.open(url);
                            }else{
                                $.error("请选择科目");
                            }
                        }else{
                            $.error("请选择考试");
                        }
                    }else{
                        $.error("请选择班级");
                    }
                }else{
                    $.error("请选择年级");
                }
            }else{
                $.error("请选择学期");
            }
        }else{
            $.error("请选择学年");
        }
    }

    function initSelect(){
        $("#xn").change(function(){
            $("#xq").html("");
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/scoreFenXi/findByGradeId?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#xq").change(function(){
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');

            var bjId=$("#bj").val();

            if(bjId!=""){
                $("#ks").html('<option value="">请选择</option>');
                //添加考试
                addOption("/analysis/students/queryExamName?xn="+$("#xn").val()+"&xq="+$("#xq").val()+"&bj="+bjId, "ks", "id", "examName")
            }


        })

        $("#nj").change(function(){
            $("#bj").html('<option value="">请选择</option>');
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
            }
        })


        $("#bj").change(function(){
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加考试
            var bjId=$("#bj").val();
            if($(this).val()!="") {
                addOption("/analysis/students/queryExamName?xn=" + $("#xn").val() + "&xq=" + $("#xq").val() + "&bj=" + bjId, "ks", "id", "examName")
            }
        })

        $("#ks").change(function(){
            $("#km").html('<option value="">请选择</option>');
            var bjId=$("#bj").val();
            if($(this).val()!="") {
                addOption("/analysis/students/queryExamSubject?xn=" + $("#xn").val() + "&xq=" + $("#xq").val() + "&bj=" + bjId + "&examName=" + $("#ks").find("option:selected").text(), "km", "code", "name")
            }
        })


        //初始填充
        addOption('/teach/schoolYear/list/json',"xn","year","name",function(d){
            if(d.length>0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //添加年级
                addOption('/scoreFenXi/findByGradeId?schoolYear='+d[0].year, "nj", "id", "name")

            }
        })



    }
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
    function addOption(url,id,valProperty,namePropety,callback){
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                $("#"+id).append("<option value="+obj[valProperty]+">"+obj[namePropety]+"</option>");
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }


</script>

</body>
</html>
