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
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    班级成绩分析
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >导出班级成绩分析</a>

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
                                <option value="">全校</option>
                            </select>
                        </div>
                        <div class="select_div"><span>考试类型：</span>
                            <select id="kl" name="examType" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div" id="kaoshi" style="display: none">
                            <span>考试名称：</span>
                            <input type="text" id="ks" name="ks" style="width:200px;padding-top: 4px;"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                            <tr role="row">
                                <th>考试名称</th>
                                <th>班级名称</th>
                                <th>总分</th>
                                <th>平均分</th>
                                <th>及格率</th>
                                <th>优秀率</th>
                                <th>满分人数</th>
                                <th>优秀人数</th>
                                <th>及格人数</th>
                            </tr>
                        </thead>
                        <tbody id="publicClass_list_content">

                        </tbody>

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
    });
    function search() {
        var val = {};
        var nj = $("#nj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km =$("#km").val();
        var xl=$("#kl").val();
        var ks=$("#ks").val();
        if (xn != null && xn != "") {
            val.schoolYear = xn;
            if (xq != null && xq != "") {
                val.schoolTrem = xq;
                if (nj != null && nj != "") {
                    val.gradeId = nj;
                }
                if(xl!=null && xl !=""){
                    val.examType=xl;
                    if (km != null && km != "") {
                            val.subjectCode = km;
                        if(xl==4) {
                            if (ks != null && ks != "") {
                                val.examName = ks;
                            } else {
                                $.error("请输入考试名称关键字");
                                return;
                            }
                        }
                            var url = "/scoreFenXi/findByTeamFenXi";
                            $("#publicClass_list_content").html("");
                            $.post(url,val,function (d) {
                                var dd=JSON.parse(d);
                               for(var a=0;a<dd.length;a++){
                                  var strs="<tr><td>"+dd[a].examName+"</td>" +
                                      "<td>"+dd[a].name+"</td>" +
                                        "<td>"+dd[a].zongfen+"</td>" +
                                        "<td>"+dd[a].pingjunfen+"</td>" +
                                        "<td>"+dd[a].jgl+"%</td>" +
                                        "<td>"+dd[a].yxl+"%</td>" +
                                        "<td>"+dd[a].mfrs+"</td>" +
                                        "<td>"+dd[a].yxrs+"</td>" +
                                        "<td>"+dd[a].jgrs+"</td></tr>";
                                   $("#publicClass_list_content").append(strs);
                                }

                            })
                        }else{
                            $.error("请选择科目");
                        }
                    }else{
                        $.error("请选择考试");
                    }
            }else{
                $.error("请选择学期");
            }
        }else{
            $.error("请选择学年");
        }
    }
    function daochu() {
        var url = "/scoreFenXi/daochuTeam";
        var nj = $("#nj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km =$("#km").val();
        var xl=$("#kl").val();
        var ks=$("#ks").val();
        if (xn != null && xn != "") {
            url+="?schoolYear="+xn;
            if (xq != null && xq != "") {
                url+="&schoolTrem="+xq;
                if (nj != null && nj != "") {
                    url += "&gradeId=" + nj;
                }
                    if(xl!=null && xl !=""){
                        url+="&examType="+xl;
                        if (km != null && km != "") {
                            url+="&subjectCode="+km;
                            if(xl==4){
                                if(ks!=null && ks!=""){
                                    url+="&examName="+ ks;
                                }else{
                                    $.error("请输入考试名称关键字");
                                    return;
                                }
                            }
                            $.success('下载成功');
                            window.open(url);
                        }else{
                            $.error("请选择科目");
                        }
                    }else{
                        $.error("请选择考试");
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
            $("#nj").html('<option value="">全校</option>');
            $("#kl").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/scoreFenXi/findByGradeId?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#xq").change(function(){
            $("#kl").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            addOption2("/scoreFenXi/findByExamType?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val(), "kl", "exam_type", "exam_type")
        })

        $("#nj").change(function(){
            $("#kl").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            if($("#nj").val()!='' && $("#nj").val()!=null){
                addOption2("/scoreFenXi/findByExamType?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val() +"&gradeId="+$("#nj").val(), "kl", "exam_type", "exam_type")
            }else{
                addOption2("/scoreFenXi/findByExamType?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val(), "kl", "exam_type", "exam_type")
            }


        })

        $("#kl").change(function(){
            $("#km").html('<option value="">请选择</option>');
            if($(this).val()!="") {
                addOption("/scoreFenXi/findBySubject?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val() + "&gradeId=" +$("#nj").val() + "&type=" + $("#kl").val(), "km", "code", "name")
            }
            if($(this).val()==4){
                $("#kaoshi").attr("style", "display:block;");//显示div
            }else{
                $("#kaoshi").attr("style", "display:none;");//隐藏div
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
    function addOption2(url,id,valProperty,namePropety,callback){
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                if(obj[namePropety]==1){
                    $("#"+id).append("<option value="+obj[namePropety]+">期中考试</option>");
                }
                if(obj[namePropety]==2){
                    $("#"+id).append("<option value="+obj[namePropety]+">期末考试</option>");
                }
                if(obj[namePropety]==3){
                    $("#"+id).append("<option value="+obj[namePropety]+">平时考试</option>");
                }
                if(obj[namePropety]==4){
                    $("#"+id).append("<option value="+obj[namePropety]+">单元测试</option>");
                }
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }


</script>

</body>
</html>
