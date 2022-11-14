<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title>家校反馈</title>
    <style type="text/css">
        #groupLists{
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
       #groupLists{
            right: 20%;
            top: 20%;
        }
    </style>
    <style type="text/css">
        #teacherBiao {
            /*设置相邻单元格的边框间的距离*/
            border-spacing: 0;
            /*表格设置合并边框模型*/
            border-collapse: collapse;
            text-align: center;
        }
        /*关键设置 tbody出现滚动条*/
        #thbiao {
            display: block;
            height: 220px;
            overflow-y: scroll;
        }

        #thtop tr,
        #thbiao tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
        #thtop {
            width: calc( 100% - 1em)
        }
        #thtop th {
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


        div#yixuanTeacher {
            overflow: scroll;
        }

        div#yixuanTeacher::-webkit-scrollbar {
            width: 0;
            height: 0;
        }


        #teacherBiao {
            border-spacing: 0;
            border-collapse: collapse;
            text-align: center;
            width: 98%;
            margin-left: 1%;
        }

        #thbiao {
            display: block;
            height: 220px;
            overflow: auto;
        }


        #thbiao::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="家校反馈" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        家校反馈
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
                            <div class="select_div">
                                <span>姓名:  </span>
                                <input type="text" id="name" name="name"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="请输入查询名称"
                                       value="">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>时间</th>
                                <th>班级</th>
                                <%--<th>姓名</th>
                                <th>家长姓名</th>--%>
                                <th>家长手机号</th>
                                <th>反馈内容</th>
                                <th>图片说明</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="feedback_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="feedback_list_content"/>
                            <jsp:param name="url" value="/schoolaffair/htsFeedback/index?sub=list&dm=${param.dm}"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--选择回复教师弹框--%>
<div id="groupLists"  style="display: none">
    <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">选择教师</span>
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
        <thead id="thtop">
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
<Script type="text/javascript">
    var tracher="";
    //打开堂狂
    function openteacher(id) {
        tracher=id;
        $("#groupLists").attr("style", "display:block;");
    }
    //关闭堂狂
    function closeteacher() {
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
                $("#td_"+obj[a]['id']).attr("onclick","teacherQueding('"+obj[a]['id']+"')");
            }
        })
    }
    //确定老师
    function teacherQueding(id){
        $.confirm("确定执行此次操作？", function () {
            queren(id,tracher);
        });
    }
    function queren(id,teacherid) {
        $.get('/schoolaffair/htsFeedback/updateTeacher?id='+teacherid+'&teacher='+id,function (d) {
            if(d=="success"){
                $.success("设置成功");
                $("#groupLists").attr("style", "display:none;");
            }else{
                $.error("请联系管理员");
            }
        })
    }

</Script>
</body>

<script type="text/javascript">


    
	$(function () {
        $("body").undelegate("mouseover");
        $("body").undelegate("mouseout");
        $("body").undelegate("click");
        $('.table tbody tr .caozuo button').css("z-index","-1");
        $(".table tbody tr").children(".caozuo").children("button").css("z-index","1");
        $('body').on("click",".table tbody tr", function() {
            $('.table tbody tr').removeClass("blue_1 on");
            $(this).addClass("blue_1 on");
        });
	});


	//加载查询
    function search() {
        console.log("sdsdsdsd");
        var val = {};
        var name = $("#name").val();
        if (name != null && name != "") {
            val.name = name;
        }
        var id = "feedback_list_content";
        var url = "/schoolaffair/htsFeedback/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    //加载回复
    function replyObj(id) {
        $.initWinOnTopFromLeft('回复', '${ctp}/schoolaffair/htsFeedback/viewer?id=' + id+"&type=0&isCK=", '700', '500');
    }

    //加载编辑
    function editObj(id) {
        $.initWinOnTopFromLeft('编辑','${ctp}/schoolaffair/htsFeedback/viewer?edit=1&id=' + id+"&type=1&isCK=",'700','500');
    }


    //加载详情
    function loadViewerPage(id) {
        $.initWinOnTopFromLeft('详情', '${ctp}/schoolaffair/htsFeedback/viewer?id=' + id+"&type=1&isCK=disable", '700', '500');
    }

    <%--//执行编辑--%>
    <%--function editObj(id,isReply) {--%>
    <%--    var mes = "编辑";--%>
    <%--    if (isReply == 1) {--%>
    <%--        mes = "回复";--%>
    <%--    }--%>
    <%--    $.initWinOnTopFromLeft(mes, '${ctp}/schoolaffair/htsFeedback/viewer?id=' + id+"&type=0&isCK=", '700', '500');--%>
    <%--}--%>





    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });

    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/schoolaffair/htsFeedback/delete/" + id,"post", function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
            $.refreshWin();
        });
    }


</script>
</html>