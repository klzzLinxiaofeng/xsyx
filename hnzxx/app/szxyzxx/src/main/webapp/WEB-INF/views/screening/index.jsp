<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>资产排查</title>
    <style type="text/css">
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    资产排查管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <c:if test="${flag}">
                            <a id="downLoadExcel"  class="a2" href="#" onclick="loadCreatePage();" class="a2" >资产排查登记</a>
                        </c:if>
                        <a   class="a2" href="#" onclick="daochu();" class="a2" >导出资产排查登记</a>

                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">
                        <div class="select_div">
                            <span>排查人：</span>
                            <input id="paichaUserName" name="paichaUserName" class="" style="width:180px;padding-top: 4px;"/>
                        </div>
                        <div class="select_div">
                            <span>排查区域：</span>
                            <input id="screeningArea" name="screeningArea" class=""  style="width:180px;padding-top: 4px;"/>
                        </div>
                        <div class="select_div">
                            <span>排查时间：</span>
                            <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
                                   style="width:180px;padding-top: 4px;"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                   placeholder="xxxx-xx-xx"/>-
                            <input type="text" id="kaishiTime2" name="kaishiTime2" class="chzn-select" autocomplete="off"
                                   style="width:180px;padding-top: 4px;"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                   placeholder="xxxx-xx-xx"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>排查人员</th>
                            <th>排查区域</th>
                            <th>排查时间</th>
                            <th>水电</th>
                            <th>安全隐患</th>
                            <th>建筑质量</th>
                            <th>设施设备</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/Screening/findByAll?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //查询
    function search() {
        var val = {};
        var paichaUserName=$("#paichaUserName").val();
        var screeningArea=$("#screeningArea").val();

        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        if(paichaUserName!=null && paichaUserName!=''){
            val.paichaName=paichaUserName;
        }
        if(screeningArea!=null && screeningArea!=''){
            val.screeningArea=screeningArea;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            val.startTime=kaishiTime;
        }
        if(kaishiTime2!=null && kaishiTime2!=''){
            var data=new Date(kaishiTime2);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate()+1;
            val.endTime=year+"-"+mounth+"-"+day;
        }
        var id = "publicClass_list_content";
        var url = "/Screening/findByAll?sub=list";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage() {
        window.location.href="${ctp}/Screening/createOrUpdate";
    }
    //  加载编辑对话框
    function bianji(id) {
        window.location.href = "${ctp}/Screening/createOrUpdate?id=" + id;
    }
    //  加载详情对话框
    function xiangqing(id) {
        window.location.href = "${ctp}/Screening/fingById?id=" + id;
    }

    // 	删除对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(id);
        });
    }

    // 	执行删除
    function executeDel(id) {
        $.post("${ctp}/Screening/delete?id=" + id, function(data, status) {
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

    function daochu() {
        var url="/Screening/findByWareHousingExport?url=asdf";
        var shenqingName=$("#shenqingName").val();
        if(shenqingName!=null && shenqingName!=""){
            url+="&shenqingrenName="+shenqingName;
        }
        var type=$("#type").val();
        if(type!=null && type!=""){
            url+="&type="+type;
        }
        var name=$("#name").val();
        if(name!=null && name!=""){
            url+="&name="+name;
        }
        var kaishiTime=$("#kaishiTime").val();
        if(kaishiTime!=null && kaishiTime!=""){
            url+="&startTime="+kaishiTime;
        }
        var kaishiTime2=$("#kaishiTime2").val();
        if(kaishiTime2!=null && kaishiTime2!=""){
            var data=new Date(kaishiTime2);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate()+1;
            url+="&endTime="+year+"-"+mounth+"-"+day;
        }
        var zhuantai=$("#zhuantai").val();
        if(zhuantai!=null && zhuantai!=""){
            url+="&zhuangtai="+zhuantai;
        }
        $.success("导出成功");
        window.open(url);
    }
</script>
</body>

</html>