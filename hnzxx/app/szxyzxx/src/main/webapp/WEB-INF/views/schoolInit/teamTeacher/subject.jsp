<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script src="${ctp}/res/js/common/plugin/ui/jquery-ui.js" type="text/javascript"></script>
    <title>科目信息管理</title>
    <style>
        .table tbody tr{
            cursor:pointer;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 style="height: 40px">
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();"
                               class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
                                <a href="javascript:void(0)" class="a4" onclick="loadCreatePage();">
                                    <i class="fa fa-plus"></i>新增科目
                                </a>
                        </p>
                    </h3>
                </div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>科目名称：</span>
                                <input type="text" id="name" name="name" data-id-container="name" style="margin-bottom: 0;  width: 120px; margin-right: 3px;" placeholder="" data-id="" value="">
                            </div>
                            <button onclick="search()" class="btn btn-primary" type="button">查询</button>
                            <div class="clear"></div>
                        </div>
                        <div  style="height:32px;margin-top:10px;background-color:#F8F8F8;padding:4px 15px;">
                            <span style="color:red;margin-right:10px;font:bold 14px/32px '微软雅黑'">拖动科目可以进行排序</span>
                            <button style="display: none;" id="sortButton" onclick="orderSubject();" class="btn btn-primary" type="button">保存排序</button>
                        </div>
                        <table class="responsive table table-striped"
                               id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>科目名称</th>
                                <th>课程范围</th>
                                <th class="caozuo">操作</th>
                            </tr>
                            </thead>
                            <tbody id="module_list_content">
                                <jsp:include page="../../teach/subject/list.jsp" />
                            </tbody>
                        </table>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    search();
    function search() {
        var val = {};
        var name = $("#name").val();
        if (name != null && name != "") {
            val.name = name;
        }
        var id = "module_list_content";
        var url = "/teach/subject/subjectList?sub=list&dm=${param.dm}";
//        myPagination(id, val, url);
        var loader = new loadDialog();
        loader.show();
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                $("#module_list_content").html("").html(data);
                var iframeHeight=$('body').height();
                $("#iframe_sencond",window.parent.document).height(iframeHeight+'px');
                $("#iframe_first",window.parent.parent.document).height((iframeHeight+71)+'px');
                loader.close();
            }
        });
    }

    //	加载创建角色对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('新增科目', '${pageContext.request.contextPath}/teach/subject/addSubjectPage', '600', '410');
    }

    function loadModifyPage(id){
        $.initWinOnTopFromLeft('修改科目', '${pageContext.request.contextPath}/teach/subject/modifySubject?id='+id, '600', '410');
    }

    function deleteSubject(id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(id);
        });
    }

    function executeDel(id) {
        $.post("${pageContext.request.contextPath}/teach/subject/deleteSubject?id=" + id, {"_method" : "delete"}, function(data, status) {
            data = eval("(" + data + ")");
            if("success" === status) {
                if("success" === data.info) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                    window.location.reload();
                } else if("fail" === data.info) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

    //是否显示按钮，只有排序的时候才显示
    function showButton(){
        $("#sortButton").show();
    }

    //将排序的顺序放入到文本框中
    function addSort(sortData){
        var data = $("#sort").val();
        if(data == ""){
            data = sortData;
            $("#sort").val(data);
        }else{
            data += "," + sortData;
            $("#sort").val(data);
        }
    }

    var fixHelperModified = function(e, tr) {
            var $originals = tr.children();
            var $helper = tr.clone();
            var i=$(this).index();
            $helper.children().each(function(index) {
                $(this).width($originals.eq(index).width());
            });
            return $helper;

        },
        updateIndex = function(e, ui) {
            $("#sort").val("");
            $('td.index', ui.item.parent()).each(function (i) {
                $(this).html(i + 1); // 有排序号时用于更新排序号
                addSort(ui.item.parent().children().eq(i).attr("id"));
            });
            showButton();
        };
    $("#data-table tbody").sortable({
        helper: fixHelperModified,
        stop: updateIndex
    }).disableSelection();

    function orderSubject() {
        var url = "${ctp}/teach/subject/orderSubject";
        var $requestData = {};
        $requestData.subjectIds = $("#sort").val();
        $requestData._method = "post";
        $.post(url, $requestData, function(data, status) {
            data = eval("(" + data + ")");
            if("success" === status) {
                if("success" === data.info) {
                    $.success("排序成功");
                    $("#sortButton").hide();
                } else if("fail" === data.info) {
                    $.error("排序失败");
                }
            }
        });
    }
</script>
</html>
