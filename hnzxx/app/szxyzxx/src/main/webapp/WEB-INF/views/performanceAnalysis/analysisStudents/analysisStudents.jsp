<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript" defer="defer" src="${pageContext.request.contextPath}/res/plugin/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/extra/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>学生成绩分析</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        学生成绩分析
                    </h3>
                </div>
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

                            <button type="button" class="btn btn-primary" id="search">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="layui-hide" id="weekTable" lay-filter="weekTable"></table>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var position = '#weekTable';
    var util = layui.util;
    var tableOption=null;
    var table=null
    $(function () {
        initSelect();

        layui.use(['element', 'table', 'rate'], function () {
            var $ = layui.jquery, element = layui.element, rate = layui.rate;
            var laydate = layui.laydate;
            table = layui.table;
            //执行一个laydate实例
            laydate.render({
                elem: '#month' //指定元素
                , type: 'month'
            });
            table.render(getTableOption())
        });


    });
    // 查询功能
    $("#search").bind("click", function () {
        if($("#km").val()==""){
            layer.msg("请选择班级和科目");
            return;
        }
        reloadTable();
    });

    function reloadTable(){
        if(tableOption==null) {
            tableOption = getTableOption();
        }
        var where=tableOption.where;
        if(where.queryId!=undefined && where.queryId!=null){
            where.previousQueryId=where.queryId;
        }
        where.examSubjectId=$("#km").val();
        where.examName=$("#ks").val();
        where.subjectName=$("#km option:selected").text();
        where.queryId=genQueryId();
        table.render(tableOption)
    }

    function getTableOption(){
        var option= {
            elem: '#weekTable'
            , url: '/analysis/students/getall'
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "success", //解析提示文本
                    "count": res.page.totalRows, //解析数据长度
                    "data": res.list //解析数据列表
                };
            }
            , where: {
                examSubjectId:$("#km").val(),
                examName:$("#ks").val()
            }, request: {
                pageName: 'currentPage',
                limitName: 'pageSize'
                //页码和显示数量
            }, page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                , groups: 3 //只显示 1 个连续页码
            }
            , cols: [[
                {field:'name', title: '学生姓名',  align: 'center'}
                ,{field:'examName', title: '考试名称', align: 'center'}
                ,{field:'subjectName', title: '科目', align: 'center'}
                ,{field:'score', title: '分数', align: 'center',templet:function(d){
                    if(d.score==null || d.score==0.0){
                        return "暂无成绩";
                    }
                    return d.score;
                    }}
                ,{field:'rank', title: '排名',align: 'center'}
                ,{field:'proficiency', title: '排名波动', align: 'center',templet:function(d){
                    if(d.rank=="-" || d.preRank=="-"){
                        return "-";
                    }
                    var change= parseInt(d.preRank)-parseInt(d.rank);
                    if(change==0){
                        return "-";
                    }
                    if(change>0){
                        return '<span style="color: green">+'+change+'</span>';
                    }else{
                        return '<span style="color: red">'+change+'</span>';
                    }
                    }}
            ]]
        }
        return option;
    }
    function initSelect(){
        $("#xn").change(function(){
            $("#xq").html("");
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#xq").change(function(){
            $("#ks").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');

            var bjId=$("#bj").val();

            if(bjId!=""){
                $("#ks").html('<option value="">请选择</option>');
                //添加考试
                addOption("/analysis/students/queryExamName?xn="+$("#xn").val()+"&xq="+$("#xq").val()+"&bj="+bjId, "ks", "examName", "examName")
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
                addOption("/analysis/students/queryExamName?xn=" + $("#xn").val() + "&xq=" + $("#xq").val() + "&bj=" + bjId, "ks", "examName", "examName")
            }
        })

        $("#ks").change(function(){
            $("#km").html('<option value="">请选择</option>');
            var bjId=$("#bj").val();
            if($(this).val()!="") {
                addOption("/analysis/students/queryExamSubject?xn=" + $("#xn").val() + "&xq=" + $("#xq").val() + "&bj=" + bjId + "&examName=" + $("#ks").val(), "km", "id", "name")
            }
        })


        //初始填充
        addOption('/teach/schoolYear/list/json',"xn","year","name",function(d){
            if(d.length>0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                addOption('/teach/grade/list/json?schoolYear='+d[0].year, "nj", "id", "name")
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


    function genQueryId(){
        return Math.random().toString(36).slice(-8)
    }

</script>
</body>
</html>
