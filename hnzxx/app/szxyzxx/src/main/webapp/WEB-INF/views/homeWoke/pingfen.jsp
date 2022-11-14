<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            评价等级：
                        </label>
                        <div class="controls">
                           <input type="text" id="dengji" value="${homeWokePojo.dengji}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            评价内容：
                        </label>
                        <div class="controls">
                            <textarea style="height:200px;width: 250px"  type="text" id="pingjia" name="pingyu" class="span4" autocomplete="off">${homeWokePojo.pingyu}</textarea>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${id}"/>
                        <input type="hidden" id="jobid" name="id" value="${jobid}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">评分
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var $id = $("#id").val();
        var pingfen=$("#dengji").val();
        var pingjia=$("#pingjia").val();
        var url = "${ctp}/home/woke/pingfen?id="+$id;
        if(pingfen!=null && pingfen!=""){
            url += "&dengji=" + pingfen;
        }
        if(pingjia!=null && pingjia!="") {
            url += "&pingyu=" + pingjia;
        }
        $.get(url, function (d) {
            if ("success" === d) {
                $.success('评价成功');
                listRefresh();
                        parent.layer.closeAll();
                    } else {
                        $.error("操作失败");
                    }
                    loader.close();
                });
    }
</script>
</body>
</html>
