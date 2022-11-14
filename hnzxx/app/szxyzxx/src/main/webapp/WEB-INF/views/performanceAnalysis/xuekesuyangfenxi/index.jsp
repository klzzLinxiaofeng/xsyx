<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>年级学科素养分析</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet"/>

</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    年级学科素养分析
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
                                     style="width:200px;padding-top: 4px;">
                             </select>
                         </div>
                        <div class="select_div"><span>年级：</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">

                            </select>
                        </div>
                        <div class="select_div"><span>科目：</span>
                            <select id="km" name="subjectCode" class="chzn-select" style="width:200px;padding-top: 4px;">
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead id="row" role="row">
                        <%--<tr id="row" role="row">

                        </tr>--%>
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
    function search() {
        var val = {};
        var nj = $("#nj").val();
        var km = $("#km").val();
        //学年
        var xn = $("#xn").val();
        //学期
        var xq = $("#xq").val();
        if (xq != null && xq != "") {
            val.xq = xq;
        }
        if (xn != null && xn != "") {
            val.xn = xn;
        }
        if (km != null && km != "") {
            val.km = km;
        }
        if (nj != null && nj != "") {
            val.nj = nj;
        }
        biaoge();
        chaxun();
        /*var url="/Literacy/student/getAllSubjectZhibiao?xn="+xn+"&xq="+xq+"&gradeId="+nj+"&subjectId="+km;
       $.get(url, function (d) {
           sd = JSON.parse(d);
           alert(sd.length);
           var changdu=sd.length;
           document.getElementById("row").innerHTML = "";
           $("#row").append("<tr><th style='border-right:#0b391a solid 1px;' rowspan='2'>班级</th><th style='border-right:#0b391a solid 1px;' colspan='"+changdu+"'>平均值</th><th colspan='"+changdu+"'>离散度</th></tr>");
           $("#row").append("<tr id='biaotou'></tr>")
           for(var b=0;b<2;b++){
               for (var i = 0; i < sd.length; i++) {
                   var obj = sd[i];
                   $("#biaotou").append("<th>" + obj['literacy_name'] + "</th>");
               }
           }
       })*/
    }

    $(function () {
        initSelect();
    });
    //table展示 查询
        function chaxun() {
            var nj = $("#nj").val();
            var km = $("#km").val();
            //学年
            var xn = $("#xn").val();
            //学期
            var xq =$("#xq").val();
            var url="/Literacy/student/feindBySubjectFenxin?xn="+xn+"&xq="+xq+"&gradeId="+nj+"&subjectId="+km;
            $.get(url, function (d) {
                var sd = JSON.parse(d);
                var changdu=sd.length;
                //alert(changdu);
                document.getElementById("publicClass_list_content").innerHTML = "";
                // $("#row").append("<tr><th rowspan='2'>班级</th><th colspan='"+changdu+"'>平均值</th><th colspan='"+changdu+"'>离散度</th></tr>");
                    for (var i = 0; i < sd.length; i++) {
                        var obj = sd[i];
                        $("#publicClass_list_content").append("<tr id='tr_"+i+"'><td>" + obj['teamName'] + "</td></tr>");
                        var fenshu=obj['fenshu'];
                        var lisanduList=obj['lisanduList'];

                        for(var j=0;j<fenshu.length;j++){
                            var svg=fenshu[j]
                            $("#tr_"+i).append("<td>"+svg+"</td>");
                        }
                        for(var o=0;o<lisanduList.length;o++){
                            var lisandu=lisanduList[o]
                            //alert(lisandu)
                            $("#tr_"+i).append("<td>"+lisandu+"</td>");
                        }
                    }
            })

        }
    //biaoge显示
    function biaoge() {
        var nj = $("#nj").val();
        var km = $("#km").val();
        //学年
        var xn = $("#xn").val();
        //学期
        var xq =$("#xq").val();
        var url="/Literacy/student/getAllSubjectZhibiao?xn="+xn+"&xq="+xq+"&gradeId="+nj+"&subjectId="+km;
        $.get(url, function (d) {
           var sd = JSON.parse(d);
            //alert(sd.length);
            var changdu=sd.length;
            document.getElementById("row").innerHTML = "";
            $("#row").append("<tr><th style='border-right:#0b391a solid 1px;' rowspan='2'>班级</th><th style='border-right:#0b391a solid 1px;' colspan='"+changdu+"'>平均值</th><th colspan='"+changdu+"'>离散度</th></tr>");
            $("#row").append("<tr id='biaotou'></tr>")
           // $("#row").append("<tr><th rowspan='2'>班级</th><th colspan='"+changdu+"'>平均值</th><th colspan='"+changdu+"'>离散度</th></tr>");
            for(var b=0;b<2;b++) {
                for (var i = 0; i < sd.length; i++) {
                    var obj = sd[i];
                    $("#biaotou").append("<th>" + obj['literacy_name'] + "</th>");
                }
            }
        })
    }
    function initSelect() {

        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name",function (e) {
                    //添加科目
                    addOption("/literacy/findExamSubject?nj="+e[0].id, "km", "subjectId", "subjectName",function (e) {
                        biaoge();
                        chaxun();
                    });

                })
            }
        })
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#xq").html('');
        $("#nj").html('<option value="">请选择</option>');
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })
    //绑定下拉框改变事件
    $("#xq").change(function(){
        //添加年级
        $("#nj").html('<option value="">请选择</option>');
        addOption('/teach/grade/list/json?schoolYear='+$("#xn").val(), "nj", "id", "name")
    })

    $("#nj").change(function(){
        //添加科目
        $("#km").html('<option value="">请选择</option>');
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

</script>
</body>

</html>
