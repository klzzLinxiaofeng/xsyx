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
    <title>班级成绩统计</title>
    <style>
        #publicClass_list_content {
            font-size: 100px;
        }
    </style>
    <style type="text/css">
        #toggle-button{ display: none; }
        .button-label{
            position: relative;
            display: inline-block;
            width: 80px;
            height: 30px;
            background-color: #ccc;
            box-shadow: #ccc 0px 0px 0px 2px;
            border-radius: 30px;
            overflow: hidden;
        }
        .circle{
            position: absolute;
            top: 0;
            left: 0;
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background-color: #fff;
        }
        .button-label .text {
            line-height: 30px;
            font-size: 18px;
            text-shadow: 0 0 2px #ddd;
        }

        .on { color: #fff; display: none; text-indent: 10px;}
        .off { color: #fff; display: inline-block; text-indent: 34px;}
        .button-label .circle{
            left: 0;
            transition: all 0.3s;
        }
        #toggle-button:checked + label.button-label .circle{
            left: 50px;
        }
        #toggle-button:checked + label.button-label .on{ display: inline-block; }
        #toggle-button:checked + label.button-label .off{ display: none; }
        #toggle-button:checked + label.button-label{
            background-color: #51ccee;
        }

    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    班级成绩统计
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="daochu();" class="a2" >班级成绩导出</a>
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
                        <div class="select_div"><span>考试类型：</span>
                            <select id="kl" name="examType" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="select_div"><span>考试名称：</span>
                            <input id="ks" type="text"/>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" multiple class="chzn-select" style="width:200px;padding-top: 4px;">

                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear">
                            <div class="toggle-button-wrapper">

                            </div>
                        </div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>总分</th>
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
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        //
        var km="";


        $("#km option:selected").each(function () {
            if(km=="" || km==null){
                km=$(this).val();
            }else{
                km+=","+$(this).val();
            }

        })
        var kl =$("#kl").val();
        var xs=$("#ks").val();
        if (xn != null && xn != "") {
            val.schoolYear = xn;
            if (xq != null && xq != "") {
                val.schoolTrem = xq;
                if (nj != null && nj != "") {
                    val.gradeId = nj;
                    if (bj != null && bj != "") {
                        val.teamId = bj;
                        if(kl!=null && kl !=""){
                            val.examType=kl;
                            if (xs != null && xs != "") {
                                val.examName = xs;
                                if (km != null && km != "") {
                                    val.subjectCode = km;
                                    var url = "/scoreFenXi/findByTeamSubject";
                                       $.post(url,val,function (d) {
                                            var dd=JSON.parse(d);
                                            var strs="";
                                                //得分
                                                strs='<thead><tr role="row"><th>序号</th><th>学号</th><th>姓名</th><th>得分</th></tr></thead><tbody id="publicClass_list_content" >';
                                                for(var a=0;a<dd.length;a++){
                                                    strs+="<tr><td>"+(parseInt(a)+1)+"</td><td>"+dd[a].emp_code+"</td><td>"+dd[a].name+"</td><td>"+dd[a].zongfen+"</td>";
                                                    strs+="</tr></tbody>";
                                                }
                                            $("#data-table").html("");
                                            $("#data-table").append(strs);

                                        })
                                }else{
                                    $.error("请选择科目");
                                }
                            }else {
                                $.error("请输入考试名称");
                            }
                        }else{
                            $.error("请选择考试类型");
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
        var val = {};
        var nj = $("#nj").val();
        var bj = $("#bj").val();
        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var km="";
        $("#km option:selected").each(function () {
            if(km=="" || km==null){
                km=$(this).val();
            }else{
                km+=","+$(this).val();
            }

        })
        var kl =$("#kl").val();
        var xs=$("#ks").val();
        var url="/scoreFenXi/findByTeamSubjectDaoChu";
        if (xn != null && xn != "") {
                url+="?schoolYear="+xn;
            if (xq != null && xq != "") {
                url+="&schoolTrem="+xq;
                if (nj != null && nj != "") {
                    url+="&gradeId="+nj;
                    if (bj != null && bj != "") {
                        url+="&teamId="+bj;
                        if(kl!=null && kl !=""){
                            url+="&examType="+kl;
                            if (xs != null && xs != "") {
                                url+="&examName="+xs;
                                if (km != null && km != "") {
                                    url+="&subjectCode="+km;
                                   window.open(url);
                                }else{
                                    $.error("请选择科目");
                                }
                            }else {
                                $.error("请输入开始名称");
                            }
                        }else{
                            $.error("请选择考试类型");
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
            $("#kl").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/scoreFenXi/findByGradeId?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#xq").change(function(){
            $("#km").html('<option value="">请选择</option>');
        })

        $("#nj").change(function(){
            $("#bj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
            }
        })


        $("#bj").change(function(){
            $("#kl").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加考试
            var bjId=$("#bj").val();
            if($(this).val()!="") {
                addOption2("/scoreFenXi/findByExamType?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val() + "&teamId=" + bjId+"&gradeId="+$("#nj").val(), "kl", "id", "exam_type")
            }
        })

        $("#kl").change(function(){
            $("#km").html("");
            //添加考试
            var bjId=$("#bj").val();
            if($(this).val()!="") {
                addOption("/scoreFenXi/findBySubject?schoolYear=" + $("#xn").val() + "&schoolTrem=" + $("#xq").val() + "&teamId=" + bjId + "&type=" + $("#kl").val(), "km", "code", "name")
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
