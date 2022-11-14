<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
    <style type="text/css">
        .ulss {
            display: flex;
            box-shadow:    0px -1px 0px 0px #cdcdcd,   /*上边阴影  红色*/
            0px 1px 0px 0px #cdcdcd,
            -1px 0px 0px 0px #cdcdcd,
            1px 0px 0px 0px #cdcdcd;
        }


        .ulss li {
            float: left;
            list-style: none; /* 将默认的列表符号去掉 */
            padding: 0; /* 将默认的内边距去掉 */
            margin: 0; /* 将默认的外边距去掉 */
            width: 70px;
            height: 70px;
            display: inline-table;
            text-align: center;
            line-height: 30px;
        }

        .cls {
            width: 90px !important;
            height: 70px !important;;
            padding-left: 0px !important;;
            box-shadow: 1px 0px 0px 0px #cdcdcd;    /*右边阴影  蓝色*/
        }

        .divs {
            display: block;
        }
        .sps{
            display: block;
            font-size: 19px;
            margin-bottom: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="接送管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        接送管理
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
                            <div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" style="width:120px;"></select> </div>

                            <input type="hidden" name="gradeId" id="gradeId"/>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>

                        <div class="divs">
                            <span class="sps">家长接送（校门闸道）</span>
                            <ul class="ulss"  id="ulss1">
                                <li id="ic1" class="cls">
                                    <img src="${ctp}/res/images/Turnstile.png" style="width: 70px; height: 70px;">
                                </li>
                            </ul>
                        </div>
                        <div class="divs">
                            <span class="sps">家长接送（停车场闸道）</span>

                            <ul class="ulss" id="ulss2">
                                <li id="ic2" class="cls">
                                    <img src="${ctp}/res/images/car.png" style="width: 70px; height: 70px;">
                                </li>
                            </ul>
                        </div>
                        <div class="divs">
                            <span class="sps">未分配</span>
                            <ul class="ulss"  id="ulss3">
                                <li id="ic3" class="cls">
                                    <img src="${ctp}/res/images/sad.png" style="width: 70px; height: 70px; text-align: center">
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    $(function () {
        getClass();

    });

    function search() {
        var teamId = $("#teamId").val();

        if(teamId == null ||teamId == ""){
            $.alert("请选择班级");
            return;
        }

        var gradeId = $("#gradeId").val();

        if(gradeId == null ||gradeId == ""){
            $.alert("年级为空");
            return;
        }
        searchByTeamId(teamId, gradeId);
    }


    /*
    *
    * 根据年级 班级查询学生接收信息
    * */
    function searchByTeamId(teamId, gradeId) {
        var url = "${pageContext.request.contextPath}/class/after/getStuLocationInfo?teamId="+teamId+"&gradeId="+gradeId;
        $.get(url, function(data,status) {
            var obj = eval("(" + data + ")");
            if(status == "success"){
                var ul1 = "";
                var ul2 = "";
                var ul3 = "";
                for(var i=0; i<obj.length;i++){
                    if (obj[i].location != null && obj[i].location != ""){
                        //接送地点 1:停车场门口 2:校门正门',
                        if (obj[i].location == 1){
                            ul1 += "<li><img src=\"${ctp}/res/images/stu.png\" style=\"width: 50px; height: 40px;\"> <div> <span style=\"text-align: center\">"+obj[i].name+"</span></div></li>";
                        } else if (obj[i].location == 2){
                            ul2 += "<li><img src=\"${ctp}/res/images/stu.png\" style=\"width: 50px; height: 40px;\"><div><span style=\"text-align: center\">"+obj[i].name+"</span></div></li>";
                        }
                    } else{
                        ul3 += "<li><img src=\"${ctp}/res/images/stu.png\" style=\"width: 50px; height: 40px;\"><div><span style=\"text-align: center\">"+obj[i].name+"</span></div></li>";
                    }
                }
                if (obj.length > 0){

                    $("#ulss2").html('<li id="ic2" class="cls"> <img src="${ctp}/res/images/car.png" style="width: 70px; height: 70px;"> </li>' + ul1);
                    $("#ulss1").html('<li id="ic1" class="cls"> <img src="${ctp}/res/images/Turnstile.png" style="width: 70px; height: 70px;"> </li>' + ul2);
                    $("#ulss3").html('<li id="ic3" class="cls"> <img src="${ctp}/res/images/sad.png" style="width: 70px; height: 70px;"> </li>' + ul3);
                    $(".ulss").trigger("create");
                }
            }
        });
    }


    function getClass(){
        var url = "${pageContext.request.contextPath}/class/after/getClassInfo";
        $.get(url,  function(data,status) {
            var obj = eval("(" + data + ")");
            if(status == "success"){
                $("#teamId").html("");
                if(obj.length > 0){
                    $("#teamId").append("<option value=''>请选择</option>");
                    // 设置年级
                    $("#gradeId").val(obj[0].gradeId);
                    for(var i=0; i<obj.length;i++){
                        if(i == 0){
                            var opt = "<option selected='selected' value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
                        }else{
                            var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
                        }
                        $("#teamId").append(opt);
                    }
                }else{
                    $("#teamId").append("<option selected='selected' value=''>请选择</option>");
                }
            }
        });
    }

</script>
</html>