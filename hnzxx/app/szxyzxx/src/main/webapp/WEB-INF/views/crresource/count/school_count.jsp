<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/css/extra/houtai_content.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js" ></script>
    <title>资源统计</title>
    <style>
        .appCont select {
            width: 85px;
            height: auto;
        }
        .tip{
            font-size: 24px;
            width: 300px;
            margin-top: 170px;
            margin-left: auto;
        }

        .layui-layer-ico16, .layui-layer-loading .layui-layer-loading2:before{
            content:"正在更新统计，请稍后...";
        }
    </style>
</head>
<body >
<div  class="container-fluid bg_fff">
    <div class="app-module">
        <div class="yyjk">
            <div class="yyjk_1">
                <button class="lan_button" style="float: right;margin-right: 2%;border: 0px;" onclick="updateSchoolCount()">更新统计</button>
                <div class="appCont_top">
                    <div class="appCont" style="border-right:0 none;">
                        <div class="title">资源总量 &nbsp;<b id="schoolAllCountSize" style="font-size: 20px"></b></div>
                       <div>
                           <div class="fr cxqj" style="margin: 0 30px 10px 0;">
                               <select id="schoolAllCount" onchange="getSchoolAllCount()">
                               </select>
                           </div>
                           <div class="clear"></div>
                       </div>
                        <div id="schoolAllCount_div">
                        </div>
                    </div>
                    <div class="appCont" style="height:600px;border-right:0 none;">
                        <div class="title">资源类型分布</div>
                        <div>
                            <div class="fr" style="margin-left: 30px;margin-bottom: 25px">
                                学段：
                                <select id="stageCode" onchange="getSubject()">
                                </select>
                                学科：
                                <select id="subjectCode" onchange="getVersionCode()">
                                    <option>全部</option>
                                </select>
                                教材版本：
                                <select id="versionCode" onchange="searchResTypeSize()" style="width: 136px;">
                                    <option>全部</option>
                                </select>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <%--<div id="zyzlfb" style="width: 90%; height: 500px;"></div>--%>
                        <div id="resType_div">
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="appCont_top">
                    <div class="appCont" style="border-right:0 none;width:100%; height: 600px;">
                        <div class="title">更新统计</div>
                       <div>
                           <div class="fr cxqj" style="margin: 0 30px 10px 0;">
                               <select id="updateCount" onchange="getUpdateCount()">
                                   <option data-type="week" selected>周统计</option>
                                   <option data-type="month">月统计</option>
                                   <option data-type="year">年统计</option>
                               </select>
                           </div>
                           <div class="clear"></div>
                       </div>
                        <div id="updateCount_div">
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $('body').on('click','.shenhe_pm_choose a',function(){
        $('.shenhe_pm_choose a').removeClass('on')
        $(this).addClass('on')
    })

    $(function(){
        getStage('schoolAllCount');
        getStage('stageCode');

        getUpdateCount();
    })

    function getSchoolAllCount() {
        var stageCode = $("#schoolAllCount option:selected").attr("data-code");
        var url = '${pageContext.request.contextPath}/resource/count/countSchoolAll';
        $.get(url,{stageCode:stageCode},function (date) {
            $("#schoolAllCount_div").empty();
            $("#schoolAllCount_div").html(date);
        })
    }

    function getStage(div) {
        var url = "${pageContext.request.contextPath}/resource/school/getStageByTextbookCondition";
        $.get(url, {}, function (date) {
            $("#"+div).empty();
            var jsonData = eval('('+date+')');
            $.each(jsonData,function(k,v) {
                $("#"+div).append('<option data-code="'+k+'" >'+v+'</option>')
            })
            if(div == 'schoolAllCount'){
                getSchoolAllCount();
            }else {
                getSubject();
            }
        })
    }

    function getSubject() {
        var stageCode = $("#stageCode option:selected").attr("data-code");
        var url = "${pageContext.request.contextPath}/resource/school/getSubjectByTextbookCondition";
        var val = {
            stageCode : stageCode,
        }
        $.get(url, val, function (date) {
            $("#subjectCode").empty();
            var jsonData = eval('('+date+')');
            $.each(jsonData,function(n,value) {
                $("#subjectCode").append('<option data-code="'+value.code+'" >'+value.name+'</option>')
            })
            <%--$("#subjectCode").find("option[data-code='" + ${condition.subjectCode} + "']").attr("selected",true);--%>
            getVersionCode();
        })
    }

    function getVersionCode() {
        var stageCode = $("#stageCode option:selected").attr("data-code");
        var subjectCode = $("#subjectCode option:selected").attr("data-code");

        var url = "${pageContext.request.contextPath}/resource/school/getVersionByTextbookCondition";
        var val = {
            stageCode : stageCode,
            subjectCode : subjectCode,
        }
        $.get(url, val, function (date) {
            console.log(date)
            $("#versionCode").empty();
            var jsonData = eval('('+date+')');
            $.each(jsonData,function(n,value) {
                $("#versionCode").append('<option data-code="'+value.id+'" >'+value.name+'</option>')
            })
            searchResTypeSize()
        })
    }

    function searchResTypeSize() {
        var stageCode = $("#stageCode option:selected").attr("data-code");
        var subjectCode = $("#subjectCode option:selected").attr("data-code");
        var versionCode = $("#versionCode option:selected").attr("data-code");
        var val = {
            "stageCode" : stageCode,
            'subjectCode' : subjectCode,
            'versionCode' : versionCode,
        }
        var url = '${pageContext.request.contextPath}/resource/count/countResTypeSize';

        $.post(url,val,function (data) {
            $("#resType_div").empty();
            $("#resType_div").html(data);
        })
    }

    function getUpdateCount() {
        var countType = $("#updateCount").find("option:selected").attr("data-type");
        var url = '${pageContext.request.contextPath}/resource/count/coutUpdate';
        var val = {
            countType : countType,
        }
        $.get(url,val,function (date) {
            $("#updateCount_div").empty();
            $("#updateCount_div").html(date);
        })
    }


    function updateSchoolCount() {
        var a = layer.load(2);
        var url = '${pageContext.request.contextPath}/resource/count/updateSchoolCount';
        $.get(url,{},function (date) {
            if(date === 'success'){
                $.success("更新统计成功");
            }else {
                $.error("更新统计失败");
            }
            layer.close(a);
        })

    }
</script>
</html>