<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <title>会务管理</title>
<style type="text/css">
    #groupList,#groupLists{
        background: #f5f5f8;
        position: fixed;
        text-align:center;
        width: 400px;
        height: 300px;
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
    #groupList,#groupLists{
        right: 45%;
        top: 30%;
    }
    div#div3, div#div2, div#div1, div#div4 {z-index:999}
    button.btn.btn-primary {
        height: 30px;
        margin-left: 10px;
        background: #0d7bd5;
        padding: 0 15px;
        border-radius: 3px;
    }
</style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="会务管理" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12 white">
            <div class="clearfix list-search-bar x-search">
                <div class="select_b">
                    <div class="select_div">
                        <span>主题：</span>
                        <input id="theme" name="theme" class="" style="width:200px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>申请部门：</span>
                        <%--   <input id="departmentId" name="departmentId" class=""  style="width:200px;padding-top: 4px;"/>--%>
                        <select id="departmentId" name="departmentId">
                            <option value="">请选择</option>
                        </select>
                    </div>
                    <div class="select_div">
                        <span>申请人：</span>
                        <input id="applicantName" name="applicantName" class=""  style="width:200px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>活动负责人：</span>
                        <input id="eventManagerName" name="eventManagerName" class=""  style="width:200px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>活动场地：</span>
                        <input id="changdiName" name="changdiName" class=""  style="width:200px;padding-top: 4px;"/>
                    </div>
                    <div class="select_div">
                        <span>活动时间：</span>
                        <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
                               style="width:200px;padding-top: 4px;"
                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                               placeholder="xxxx-xx-xx xx:xx"/>-
                        <input type="text" id="kaishiTime2" name="kaishiTime2" class="chzn-select" autocomplete="off"
                               style="width:200px;padding-top: 4px;"
                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                               placeholder="xxxx-xx-xx xx:xx"/>
                    </div>


                    <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                    <div class="clear"></div>
                </div>

            </div>
            <div class="content-widgets">
                <div class="widget-head">
                    <h3 class="x-head content-top">
                        <a id="my"  class="on" onclick="showOwn();">与我相关</a>
                        <a id="all"  onclick="showAll();">全部会务</a>
                        <a id="tjs"  onclick="showMe()">我发布的</a>
                    </h3>
                    <input type="hidden" value="" id="searchType"/>
                </div>
                <div class="x-main">

                    <div id="applyrepair_page">
                        <jsp:include page="./list.jsp" />
                    </div>
                </div>
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="applyrepair_page" />
                    <jsp:param name="url" value=""/>
                    <jsp:param name="pageSize" value="${page.pageSize}" />
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<div id="groupLists" style="display: none;border: 1px dashed black">
    <div class="groupListTitle">
        <div style="background-color: #2F6144;height: 30px;">
            <span style="
                         position: absolute;
                         top:5px;left:10px;color: white;">审核</span>
            <div class="off" onclick="offGroupSet();">X</div></div>
        <div class="select_div"><span>拒绝理由：</span>
            <input type="text" id="liyou" style="width: 120px;height: 30px;"/>
        </div>
        <button type="button" class="btn btn-primary" onclick="quedingshenhe2()">确定</button>
    </div>
    <input id="idssss" style="display: none"/>
</div>
<script type="text/javascript">
    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                    $("#" +id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
    $(function() {
        addOption('/juzuAndjiaozhigong/depentAll', "departmentId", "id", "name")
    });
    function offGroupSet(){
        $("#groupLists").attr("style", "display:none;");//隐藏div
    }

    //不提供调用，请勿调用（绿色title）
    initWindowBase = function (onWhere, title, url, width, height, topVal, shift) {
        if ("top" === onWhere) {
            return window.top.layer.open({
                id:'wer',
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ topVal + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
                // time: 2000, //2秒后自动关闭
            });
        } else if ('cur' === onWhere) {
            return layer.open({
                /* extend: ['skin/myskin/style.css'], //加载您的扩展样式
                skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ top + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
            });
        }
    }

    //在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
    initWinOnTopFromLeft = function (title, url, width, height, top) {
        if (width === undefined) {
            width = $(parent.window).width() - 50;
        }
        if (height == undefined) {
            height = $(parent.window).height() - 50;
        }
        if (top == undefined) {
            /* top = '20'; */
        }
        return initWindowBase('top', title, url, width, height, top, 'left');
    }

    function search() {
        var val = {};
        var theme=$("#theme").val();
        var departmentId=$("#departmentId").val();
        var applicantName=$("#applicantName").val();
        var eventManagerName=$("#eventManagerName").val();
        var changdiName=$("#changdiName").val();
        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        val.id=$("#viewId").val();
        if(theme!=null && theme!=''){
            val.theme=theme;
        }
        if(departmentId!=null && departmentId!=''){
            val.departmentId=departmentId;
        }
        if(applicantName!=null && applicantName!=''){
            val.applicantName=applicantName;
        }
        if(eventManagerName!=null && eventManagerName!=''){
            val.eventManagerName=eventManagerName;
        }
        if(changdiName!=null && changdiName!=''){
            val.changdiName=changdiName;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.kaishiTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            val.kaishiTime2=kaishiTime2;
        }

        var id = "applyrepair_page";
        var url = "/huiyi/getByAll?sub=list";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage() {
        window.location.href="${ctp}/huiyi/createOrUpdate";
    }
    //  加载编辑对话框
    function bianji(id) {
        window.location.href = "${ctp}/huiyi/createOrUpdate?id=" + id;
    }

    function xiangqing(id) {
        window.location.href = "${ctp}/huiyi/findBiXiangqing?id=" + id;
    }
    //通过审核
    function shenhe(id) {
        $.confirm("确定通过审核吗？", function() {
            shide(id);
        });
    }
    function shide(id) {
        $.get('/huiyi/shenhehuiyi?id='+id+'&zhuangtai=1',function (d) {
            if(d=="success"){
                $.success("操作成功")
                search();
            }else{
                $.success("操作成功")

            }
        })
    }
    function bushenhe(id) {
        $("#idssss").val(id);
        $("#groupLists").attr("style", "display:block;");//显示div
    }
    function quedingshenhe2() {
        var id=$("#idssss").val();
        var isShenhe=2;
        var liyou=$("#liyou").val();
        $.get("/huiyi/shenhehuiyi?id="+id+"&liyou="+liyou+"&zhuangtai="+isShenhe,function (d) {
            if(d=="success"){
                $.success("审核成功")
                $("#groupLists").attr("style", "display:none;");//隐藏div
                $("#idss").val("")
                $("#liyou").val("");
                search();
            }else{
                $.error("审核失败")
            }
        })
    }
    // 	删除对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(id);
        });
    }

    // 	执行删除
    function executeDel(id) {
        $.post("${ctp}/huiyi/deleteHuiYi?id=" + id, function(data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    search();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

    //全部申请
    function showAll(){
        var val = {};
        var theme=$("#theme").val();
        var departmentId=$("#departmentId").val();
        var applicantName=$("#applicantName").val();
        var eventManagerName=$("#eventManagerName").val();
        var changdiName=$("#changdiName").val();
        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        val.id=1;
        if(theme!=null && theme!=''){
            val.theme=theme;
        }
        if(departmentId!=null && departmentId!=''){
            val.departmentId=departmentId;
        }
        if(applicantName!=null && applicantName!=''){
            val.applicantName=applicantName;
        }
        if(eventManagerName!=null && eventManagerName!=''){
            val.eventManagerName=eventManagerName;
        }
        if(changdiName!=null && changdiName!=''){
            val.changdiName=changdiName;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.kaishiTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            val.kaishiTime2=kaishiTime2;
        }

        var id = "applyrepair_page";
        var url = "/huiyi/getByAll?sub=list";
        myPagination(id, val, url);
    }
    //与我相关
    function showOwn(){
        var val = {};
        var theme=$("#theme").val();
        var departmentId=$("#departmentId").val();
        var applicantName=$("#applicantName").val();
        var eventManagerName=$("#eventManagerName").val();
        var changdiName=$("#changdiName").val();
        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        val.id=0;
        if(theme!=null && theme!=''){
            val.theme=theme;
        }
        if(departmentId!=null && departmentId!=''){
            val.departmentId=departmentId;
        }
        if(applicantName!=null && applicantName!=''){
            val.applicantName=applicantName;
        }
        if(eventManagerName!=null && eventManagerName!=''){
            val.eventManagerName=eventManagerName;
        }
        if(changdiName!=null && changdiName!=''){
            val.changdiName=changdiName;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.kaishiTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            val.kaishiTime2=kaishiTime2;
        }
        var id = "applyrepair_page";
        var url = "/huiyi/getByAll?sub=list";
        myPagination(id, val, url);
    }

    function showMe() {
        var val = {};
        var theme=$("#theme").val();
        var departmentId=$("#departmentId").val();
        var applicantName=$("#applicantName").val();
        var eventManagerName=$("#eventManagerName").val();
        var changdiName=$("#changdiName").val();
        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        val.id=2;
        if(theme!=null && theme!=''){
            val.theme=theme;
        }
        if(departmentId!=null && departmentId!=''){
            val.departmentId=departmentId;
        }
        if(applicantName!=null && applicantName!=''){
            val.applicantName=applicantName;
        }
        if(eventManagerName!=null && eventManagerName!=''){
            val.eventManagerName=eventManagerName;
        }
        if(changdiName!=null && changdiName!=''){
            val.changdiName=changdiName;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.kaishiTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            val.kaishiTime2=kaishiTime2;
        }
        var id = "applyrepair_page";
        var url = "/huiyi/getByAll?sub=list";
        myPagination(id, val, url);
    }
   function daoChu2(id){
       var theme=$("#theme").val();
       var departmentId=$("#departmentId").val();
       var applicantName=$("#applicantName").val();
       var eventManagerName=$("#eventManagerName").val();
       var changdiName=$("#changdiName").val();
       var kaishiTime=$("#kaishiTime").val();
       var kaishiTime2=$("#kaishiTime2").val();
       var url="/huiyi/daochuHuiYi?id="+id;
       if(theme!=null && theme!=''){
           url+="&theme="+theme;
       }
       if(departmentId!=null && departmentId!=''){
           url+="&departmentId="+departmentId;
       }
       if(applicantName!=null && applicantName!=''){
           url+="&applicantName="+applicantName;
       }
       if(eventManagerName!=null && eventManagerName!=''){
           url+="&eventManagerName="+eventManagerName;
       }
       if(changdiName!=null && changdiName!=''){
           url+="&changdiName="+changdiName;
       }
       if(kaishiTime!=null && kaishiTime!=''){
           url+="&kaishiTime="+kaishiTime;
       }
       if(kaishiTime2!=null && kaishiTime2!=''){
           url+="&kaishiTime2="+kaishiTime2;
       }
       window.open(url);
   }


   function buzhi(id) {
       $.confirm("确定布置完成吗？", function() {
           quedingbushi(id);
       });
   }
   function quedingbushi(id) {
       $.get('/huiyi/shenhehuiyi?id='+id+'&zhuangtai=3',function (d) {
           if(d=="success"){
               $.success("操作成功")
               search();
           }else{
               $.success("操作成功")

           }
       })
   }
</script>
</body>

</html>