<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>处理</title>
    <style type="text/css">
        input[type="radio"]{
            margin:0 5px;
            position:relative;
            top:-1px;
        }
    </style>
    <script>
        $(function(){
            $(".up_img ul li").hover(function(){
                $(this).children("a").show();
            },function(){
                $(this).children("a").hide();
            });
            $("body").on("click",".up_img ul li a",function(){
                $(this).parent().remove();
            })
        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets">
                <div class="yc_sq">
                    <form class="form-horizontal" action="javascript:void(0);" style="padding-bottom: 0;" id="form">
                        <div class="control-group">
                            <label class="control-label">处理方式：</label>
                            <div class="controls">
                                <textarea  style="height:75px;padding:5px;width:350px;" id="way" name="way">${complainVo.disposeWay}</textarea>
                            </div>
                        </div>
                        <div class="caozuo" style="text-align:center;">
                            <input type="hidden" id="id" name="id" value="${complainVo.id}" />
                            <button class="btn btn-primary" onclick="save();">确定</button> <button class="btn" onclick="$.closeWindow();">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var checker;
    $(function () {
        checker = initValidator();
    });

    function initValidator() {
        return $("#form").validate({
            rules : {
                "way" : {
                    maxlength: 200
                }
            },
            messages : {
                "way" : {
                    maxlength: "最长不能超过200个字符"
                }
            }
        });
    }

    function save() {
        if (checker.form()) {
            var loader = new loadLayer();
            var val = {};
            var disposeWay = $("#way").val();
            var id = $("#id").val();
            var url = "${ctp}/dn/complain/" + id;
            val._method = "put";
            val.disposeWay = disposeWay;
            val.isDispose = true;
            loader.show();
            $.post(url, val, function(data, status) {
                if("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if("success" === data.info) {
                        if(parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

</script>
</body>
</html>