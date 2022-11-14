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
    <title>学校应用管理</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div  class="lsjgl">
    <span style="color:#2299ee">${teacher.name}</span>老师管理的年级和班级：
    <div style="float:right">
        <button class="btn btn-red" style="margin-top: -4px;" onclick="emptyAll()">清空全部</button>
    </div>
</div>

<div class="banji_info_tk1">
    <ul>
        <c:forEach items="${list}" var="item">
            <li>
                <div class="div1">
                    <span><i class="ck" data-id="${item.gradeId}"></i>${item.gradeName}</span>
                    <p style="float:right" class="cz">
                        <span class="cz01">展开<i class="zk"></i></span>
                        <span class="cz02" style="display:none">收起<i class="sq"></i></span>
                    </p>
                </div>
                <div class="div2">
                    <c:forEach items="${item.teamList}" var="team">
                        <p><i class="ck <c:if test="${team.isChosen}">on</c:if><c:if test="${!team.isChosen and team.isOver}">jinzhi</c:if>" data-id="${team.teamId}"></i>${team.teamNumber}班</p>
                    </c:forEach>
                    <div class="clear"></div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="btn_cz">
    <button class="btn btn-blue" onclick="save();">保存</button>
    <button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
</div>
<script>
    $(".banji_info_tk1").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

    //展开和收起
    $('body').on('click','.cz01',function(){
        $(this).hide();
        $(this).next().show();
        $(this).parents('.div1').next('.div2').slideDown();
        $(this).parents('.div1').css("border-bottom","solid 1px #e4e8eb");
    })
    $('body').on('click','.cz02',function(){
        $(this).hide();
        $(this).prev().show();
        $(this).parents('.div1').next('.div2').slideUp();
        $(this).parents('.div1').css("border-bottom","none");
    })

    function emptyAll(){
        $('.ck').removeClass('on');
    }

    //单独选
    $('body').on('click','li .div2 i.ck',function(){
        if(!$(this).hasClass('jinzhi')){
            if($(this).hasClass('on')){
                $(this).removeClass('on');
                if( $(this).parents('.div2').find('.ck').length!=$(this).parents('.div2').find('.on').length){
                    $(this).parents('.div2').prev().find('.ck').removeClass('on');
                }
            }else{
                $(this).addClass('on');
//            console.log('2:'+$(this).parents('.div2').find('.on').length)
                if( $(this).parents('.div2').find('.ck').length==$(this).parents('.div2').find('.on').length){
                    $(this).parents('.div2').prev().find('.ck').addClass('on');
                }
            }
        }
    })

    //全选
    $('body').on('click','li .div1 i.ck',function(){
        if($(this).hasClass('on')){
            $(this).removeClass('on');
            $(this).parents('.div1').next().find('.ck').removeClass('on');

        }else{
            $(this).addClass('on');
            $(this).parents('.div1').next().find('.ck').addClass('on');
            //全选时，满员的班级不能选中
            $(this).parents('.div1').next().find('.jinzhi').removeClass('on');
        }
    })

    function save() {
        var addIds = getAddIds();
        var modifyIds = getModifyIds();
        var url = "${ctp}/teach/teamAdmin/modify";
        var val = {
            "id" : ${teacher.id},
            "addIds" : addIds,
            "modifyIds" : modifyIds
        }
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("操作成功")
                    if(parent.core_iframe != null) {
                        parent.core_iframe.window.location.reload();
                    } else {
                        parent.window.location.reload();
                    }
                    $.closeWindow();
                }

            } else {
                $.error("操作失败");
            }
        });


    }

    function getAddIds() {
        var oldTeams = ${teams};
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
        var oldTeams = ${teams};
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

</script>
</body>
</html>