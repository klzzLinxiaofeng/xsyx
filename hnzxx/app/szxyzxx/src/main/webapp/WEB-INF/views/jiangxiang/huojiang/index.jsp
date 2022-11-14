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
    <title>获奖管理</title>
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
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="获奖管理" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12 white">
            <div class="clearfix list-search-bar x-search">
                <div class="select_b">
                    <div class="select_div">
                        <span>主题/发布人：</span>
                        <input id="theme" name="theme" class="" style="width:200px;padding-top: 4px;"/>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                    <div class="clear"></div>
                </div>

            </div>
            <div class="content-widgets">
                <div class="widget-head">
                    <h3 class="x-head content-top">
                        <a id="my"  class="on" onclick="showOwn();">我的获奖</a>
                        <a id="all"  onclick="showAll();">待审批</a>
                        <a id="tjs"  onclick="showMe()">已审批</a>
                    </h3>
                    <input type="hidden" value="" id="searchType"/>
                </div>
                <div class="x-main">

                    <div id="applyrepair_page">
                        <jsp:include page="./list.jsp" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //查询
    function search() {
        var val = {};
        var type=$("#iddss").val();
        var theme=$("#theme").val();

        if(theme!=null && theme!=''){
            val.shenqingren=theme;
        }
        val.type=type;
        var id = "applyrepair_page";
        var url = "/huojiang/findByAll?sub=list";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage(id) {
        window.location.href="${ctp}/huojiang/createAndUpdate?type="+id;
    }


    function xiangqing(id) {
        window.location.href = "${ctp}/huojiang/huojiangxiangqing?id=" + id;
    }
    //通过审核
    function tongyi(id) {
        $.confirm("确定同意申请吗？", function() {
            tongyishenhe(id);
        });
    }
    function tongyishenhe(id) {
        $.get('/huojiang/shenhe?id='+id+'&zhuantai=1',function (d) {
            if(d=="success"){
                $.success("操作成功")
                search();
            }else{
                $.success("操作成功")

            }
        })
    }
    //驳回审核
    function bohui(id) {
        $.confirm("确定驳回申请吗？", function() {
            bohuishenhe(id);
        });
    }
    function bohuishenhe(id) {
        $.get('/huojiang/shenhe?id='+id+'&zhuantai=2',function (d) {
            if(d=="success"){
                $.success("操作成功")
                search();
            }else{
                $.success("操作成功")

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

    //未审核
    function showAll(){
        var val = {};
        val.type=0;
        var theme=$("#theme").val();
        if(theme!=null && theme!=''){
            val.shenqingren=theme;
        }

        var id = "applyrepair_page";
        var url = "/huojiang/findByAll?sub=list";
        myPagination(id, val, url);
    }
    //我的获奖
    function showOwn(){
        var val = {};
        var theme=$("#theme").val();
        val.type=2;
        if(theme!=null && theme!=''){
            val.shenqingren=theme;
        }
        var id = "applyrepair_page";
        var url = "/huojiang/findByAll?sub=list";
        myPagination(id, val, url);
    }
    //已审批
    function showMe() {
        var val = {};
        var theme=$("#theme").val();
        val.type=1;
        if(theme!=null && theme!=''){
            val.shenqingren=theme;
        }
        var id = "applyrepair_page";
        var url = "/huojiang/findByAll?sub=list";
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
</script>
</body>

</html>