<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
    <title>班级信息管理</title>
    <style>
        .table thead tr th:first-child{
            padding-left: 33px;
        }
    </style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
    <p class="top_link">智核 >  班级信息管理  > <a href="/teach/teamAdmin/index">设置班级管理</a>  > <span>新增班级管理者</span></p>
    <div class="content_main white">
        <div class="content_top">
            <div class="f_left"><p>新增班级管理者</p></div>
            <div class="f_right"><button class="btn btn-lightGray" onclick="window.location.href='/teach/teamAdmin/index';">返回</button></div>
        </div>
        <div>
            <p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>1</i></span>选择教师</p>
            <div class="banji_info_tk2_top">
                <span>请先选择教师：</span>
                <input type="text" disabled="disabled" id="teacherKuang" data-id="0">
                <input type="hidden" id="teacherId">
                <button style="display: none" onclick="changeName()" id="changeName"></button>
                <button class="btn btn-blue" onclick="addBjController()">选择教师</button>
            </div>
        </div>
        <div style="border-top: solid 1px #e3e8ec;">
            <p class="banji_info_zt" id="two"><span><i>2</i></span>安排班级管理</p>
            <div class="banji_info_tk2_top">
                <span>请为教师安排管理的班级：</span>
            </div>
            <div class="banji_info_tk1">
                <ul>
                    <c:forEach items="${list}" var="item">
                        <li>
                            <div class="div1">
                                <span class="s1"><i class="jinzhi" data-id="${item.gradeId}"></i>${item.gradeName}</span>
                                <p style="float:right" class="cz">
                                    <span class="cz01">展开<i class="zk"></i></span>
                                    <span class="cz02" style="display:none">收起<i class="sq"></i></span>
                                </p>
                            </div>
                            <div class="div2">
                                <c:forEach items="${item.teamList}" var="team">
                                    <p><i class="jinzhi" data-id="${team.teamId}"></i>${team.teamNumber}班</p>
                                </c:forEach>
                                <div class="clear"></div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="btn_cz" style="margin-top: 30px;padding-bottom: 60px;">
            <button class="btn-lightGray1" style="cursor: not-allowed;" id="ojbk1">确定</button>
            <button class="btn  btn-blue" style="display:none" id="ojbk2" onclick="final_operation()">确定</button>
        </div>

    </div>
</div>
<div class="addbjglz_tis" style="display:none;padding: 30px;">
    <p style="text-align: center;">您是否确定安排<span style="color: #2da1f8;" id="xsmd1"></span>教师管理</p>
    <p style="text-align: center;"><span id="xsmd"></span>总共<span id="banji_num"></span>个班级？</p>

</div>
<script>

    $(".banji_info_tk1").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    $('body').on('click','.banji_info_top ul li',function(){
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
    });

    function addBjController(){
        $.initWinOnTopFromLeft_qyjx("人员选择", '${pageContext.request.contextPath}/teach/teamAdmin/teacher/select', '600', '630');
    }

    $('body').on('click','.cz01',function(){
        $(this).hide();
        $(this).next().show();
        $(this).parents('.div1').next('.div2').slideDown();
        $(this).parents('.div1').css("border-bottom","solid 1px #e4e8eb");
    });

    $('body').on('click','.cz02',function(){
        $(this).hide();
        $(this).prev().show();
        $(this).parents('.div1').next('.div2').slideUp();
        $(this).parents('.div1').css("border-bottom","none");
    });

    //单选
    $('body').on('click','li .div2 i.ck',function(){
        if(!$(this).hasClass('jinzhi')){
            if($(this).hasClass('on')){
                $(this).removeClass('on');

                if( $(this).parents('.div2').find('.ck').length!=$(this).parents('.div2').find('.on').length){
                    $(this).parents('.div2').prev().find('.ck').removeClass('on');
                }
            }else{
                $(this).addClass('on');
                if( $(this).parents('.div2').find('.ck').length==$(this).parents('.div2').find('.on').length){
                    $(this).parents('.div2').prev().find('.ck').addClass('on');
                }
            }
            if($('li .div2 i.ck.on').length>0){
                $('#ojbk1').hide();
                $('#ojbk2').show();
            }else{
                $('#ojbk1').show();
                $('#ojbk2').hide();
            }
        }
    });

    //全选
    $('body').on('click','li .div1 i.ck',function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on');
            $(this).parents('.div1').next().find('.ck').removeClass('on');

        }else{
            $(this).addClass('on');
            $(this).parents('.div1').next().find('.ck').addClass('on');
            $(this).parents('.div1').next().find('.jinzhi').removeClass('on');
        }
        if( $('li .div2 i.ck.on').length>0){
            $('#ojbk1').hide();
            $('#ojbk2').show();
        }else{
            $('#ojbk1').show();
            $('#ojbk2').hide();
        }
    });

    function final_operation(){

        $('#xsmd1').text($('#teacherKuang').val())
        var s1 ='';
        var qb1='';
        $('.banji_info_tk1 ul li').each(function(){

            if($(this).find('.div1 .ck').hasClass('on')){
                var nj = $(this).find('.div1 span.s1').text();
                s1 = '全部班级';
                qb1= qb1 + ('"'+ nj +'"'+s1)+" 、";
            }else {

                if($(this).find('.div2 .ck').hasClass('on')){

                    var nj = $(this).find('.div1 span.s1').text();
                    $(this).find('.div2 .on').each(function(){
                        s1 = s1 +$(this).parent('p').text()+'、';
                    })
                    qb1 = qb1 + ('"'+ nj +'"'+ "<i style='color: #2da1f8;font-style: normal;'>"+s1+"</i>");
                }

            }
            $('#xsmd').html(qb1);
            var baj_num = $('.banji_info_tk1 ul li .div2 .on').length;
            $('#banji_num').text(baj_num);
            s1 = "";

        });

        layer.open({
            type: 1,
            shade:  [0.5, '#000'],
            shadeClose : true,
            //offset: '20px',
            area: ['390px', '205px'],
            title: '提示', //不显示标题
            content: $('.addbjglz_tis'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(){
                layer.close();
            },
            btn: ['确定','取消'],//按钮
            btn1: function(index, layero){
                save();
            }
        });
    }

    var oldTeams;
    function changeName(){
        var url = "${ctp}/teach/teamAdmin/teacher/team";
        var val = {"id" : $("#teacherId").val()};
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    oldTeams = data.responseData;
                    console.log(oldTeams)
                    $(".div2 .ck").each(function () {
                        var id = $(this).data("id");
                        for (var i=0; i<oldTeams.length; i++) {
                            if (id == oldTeams[i]) {
                                $(this).addClass("on");
                                break;
                            }
                        }
                    })
                }
            }
        });
    }
    function save() {
        var addIds = getAddIds();
        var modifyIds = getModifyIds();
        var url = "${ctp}/teach/teamAdmin/modify";
        var val = {
            "id" : $("#teacherId").val(),
            "addIds" : addIds,
            "modifyIds" : modifyIds
        };
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("操作成功")
                    window.location.href = '${pageContext.request.contextPath}/teach/teamAdmin/index';
                }

            } else {
                $.error("操作失败");
            }
        });
    }

    function getAddIds() {
        var addIds = "";
        $(".div1").each(function () {
            var gradeId = $(this).find(".ck").data("id");
            var teams = "";         //选中，且需新增的班级
            var isChosen = false;   //是否有班级选中

            $(this).next().find(".ck").each(function () {
                if ($(this).hasClass("on")) {
                    var teamId = $(this).data("id");

                    var isIn = false;   //是否在原班级
                    //与原有班级比较，不在原有班级的为新增，相同的则不处理
                    for (var i in oldTeams) {
                        if(oldTeams[i] == teamId) {
                            isIn = true;
                            break;
                        }
                    }
                    if (!isIn) {
                        isChosen = true;
                        if (teams != "") {
                            teams += "," + teamId;
                        } else {
                            teams += teamId;
                        }
                    }
                }
            });
            if (isChosen) {
                if (addIds != "") {
                    addIds += ";" + gradeId + ":" + teams;
                } else {
                    addIds += gradeId + ":" + teams;
                }
            }
        });
        return addIds;
    }

    function getModifyIds() {
        var modifyIds = "";
        //原有班级有，而选中班级没有的，则删除
        var newTeams = [];
        $(".div2 i.ck").each(function () {
            if ($(this).hasClass("on")) {
                newTeams.push($(this).data("id"));
            }
        });
        for (var i in oldTeams) {
            var isIn = false;
            for(var j in newTeams) {
                if (oldTeams[i] == newTeams[j]) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                if (modifyIds != "") {
                    modifyIds += "," + oldTeams[i];
                } else {
                    modifyIds += oldTeams[i];
                }
            }
        }
        return modifyIds;
    }

    <%--function save() {--%>
        <%--var teacherId = $("#teacherId").val();--%>
        <%--var addIds = getAddIds();--%>
        <%--var val = {--%>
            <%--"teacherId" : teacherId,--%>
            <%--"addIds" : addIds--%>
        <%--}--%>
        <%--var url = "${ctp}/teach/teamAdmin/creator";--%>
        <%--$.post(url, val, function(data, status) {--%>
            <%--if ("success" === status) {--%>
                <%--data = eval("(" + data + ")");--%>
                <%--if("success" === data.info) {--%>
                    <%--$.success("操作成功");--%>
                    <%--window.location.href = '${pageContext.request.contextPath}/teach/teamAdmin/index';--%>
                <%--} else {--%>
                    <%--$.error("操作失败");--%>
                <%--}--%>
            <%--} else {--%>
                <%--$.error("操作失败");--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--function getAddIds() {--%>
        <%--var addIds = "";--%>
        <%--$(".div1").each(function () {--%>
            <%--var gradeId = $(this).find(".ck").data("id");--%>
            <%--var teams = "";         //选中，且需新增的班级--%>

            <%--var isChosen = false;   //是否有班级选中--%>

            <%--$(this).next().find(".ck").each(function () {--%>
                <%--if ($(this).hasClass("on")) {--%>
                    <%--var teamId = $(this).data("id");--%>
                    <%--isChosen = true;--%>
                    <%--if (teams != "") {--%>
                        <%--teams += "," + teamId;--%>
                    <%--} else {--%>
                        <%--teams += teamId;--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>
            <%--if (isChosen) {--%>
                <%--if (addIds != "") {--%>
                    <%--addIds += ";" + gradeId + ":" + teams;--%>
                <%--} else {--%>
                    <%--addIds += gradeId + ":" + teams;--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
        <%--return addIds;--%>
    <%--}--%>
</script>
</body>
</html>