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
    <title>班级学科成绩分析</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        班级学科成绩分析
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
        layui.use([ 'table'], function () {
            table = layui.table;
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
        table.render(getTableOption())
    }

    function getTableOption(){
        var option= {
            elem: '#weekTable'
            , url: '/analysis/class/getall'
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "success", //解析提示文本
                    "count": res.page.totalRows, //解析数据长度
                    "data": res.list //解析数据列表
                };
            }
            , where: {
                xn:$("#xn").val(),
                xq:$("#xq").val(),
                teamId:$("#bj").val(),
                subjectCode:$("#km").val()
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
                {field:'exam_name', title: '考试名称',  align: 'center'}
                ,{field:'teamSize', title: '班级人数', align: 'center',width: 100}
                ,{field:'ckrs', title: '参考人数', align: 'center',width: 100}
                ,{field:'zf', title: '总分', align: 'center',width: 80,templet:function(d){
                        if(d.zf==null){
                            return "0";
                        }
                        return d.zf;
                    }}
                ,{field:'', title: '平均分',width: 80, align: 'center',templet:function(d){
                        if(d.ckrs==null || d.ckrs==0 || d.zf==null || d.zf==0){
                            return "0";
                        }
                        return (d.zf/d.ckrs).toFixed(2);
                    }}
                ,{field:'hgrs', title: '合格人数',width: 100,align: 'center'}
                ,{field:'', title: '合格率', width: 80,align: 'center',templet:function(d){
                        if(d.hgrs==null || d.hgrs==0 || d.ckrs==null || d.ckrs==0){
                            return "0%";
                        }
                        return Math.round((d.hgrs/d.ckrs)*100)+"%";
                    }}
                ,{field:'bhgrs', title: '不合格人数',width: 120,align: 'center'}
                ,{field:'', title: '不合格率',width: 100,align: 'center',templet:function(d){
                        if(d.bhgrs==null || d.bhgrs==0 || d.ckrs==null || d.ckrs==0){
                            return "0%";
                        }
                        return Math.round((d.bhgrs/d.ckrs)*100)+"%";
                    }}
                ,{field:'', title: '优生人数',width: 100, align: 'center',templet:function(d){
                        return getYsrs(d);
                    }}
                ,{field:'', title: '优生率',width: 80,align: 'center',templet:function(d){
                        var ysrs=getYsrs(d);
                        if(ysrs==0 || d.ckrs==0){
                            return "0%";
                        }
                        return Math.round((ysrs/d.ckrs)*100)+"%";
                    }}
                ,{field:'jsys', title: '90以上',align: 'center',width: 100}
                ,{field:'bszjs', title: '90以下~80',align: 'center',width: 110}
                ,{field:'qszbs', title: '80以下~70',align: 'center',width: 110}
                ,{field:'lszqs', title: '70以下~60',align: 'center',width: 110}
                ,{field:'wszls', title: '60以下~50',align: 'center',width: 110}
                ,{field:'teacher', title: '任课教师',align: 'center',width: 100}
            ]]
        }
        return option;
    }

    function getYsrs(d){
        var gradeName=$("#nj option:selected").text();
        if(gradeName=="一年级"  || gradeName=="二年级" || gradeName=="三年级"){
            return d.jsys;
        }
        //90以上+ 80-90以下 = >=80人数
        return d.jsys+d.bszjs;

    }



    function initSelect(){

        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json',"xn","year","name",function(d){
            if(d.length>0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear='+d[0].year, "nj", "id", "name")
            }
        })

        //绑定下拉框改变事件

        $("#xn").change(function(){
            $("#xq").html("");
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })

        $("#xq").change(function(){
            $("#km").html('<option value="">请选择</option>');
            var bjId=$("#bj").val();
            if(bjId!=""){
                $("#ks").html('<option value="">请选择</option>');
                //添加科目
                addOption("/analysis/class/findExamSubject?xn="+$("#xn").val()+"&xq="+$("#xq").val()+"&bj="+bjId, "km", "code", "name")
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
            //添加科目
            var bjId=$("#bj").val();
            if(bjId!="") {
                addOption("/analysis/class/findExamSubject?xn="+$("#xn").val()+"&xq="+$("#xq").val()+"&bj="+bjId, "km", "code", "name")
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
