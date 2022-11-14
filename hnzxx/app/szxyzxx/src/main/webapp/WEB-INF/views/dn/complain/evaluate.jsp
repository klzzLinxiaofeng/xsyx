<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>请假</title>
    <style type="text/css">
        a:hover{
            transition: none 0s ease-in-out;
        }
    </style>
    <script>
        //star
        var level = 0;
        $(document).ready(function(){
            var stepW = 30;
            var description = new Array("太差","不好","一般","还不错","完美解决");
            var stars = $(".stars > li");
            var descriptionTemp;
            var option = $(".option");
            $(".showb").css("width",0);
            stars.each(function(i){
                $(stars[i]).click(function(e){
                    var n = i+1;
                    $(".showb").css({"width":stepW*n});
                    descriptionTemp = description[i];
                    $(this).find('a').blur();
                    level = n;
                    return stopDefault(e);
                    return descriptionTemp;
                });
            });
            stars.each(function(i){
                $(stars[i]).hover(
                    function(){
                        $(".description").text(description[i]);
                        option.find(".option-con:eq(" + $(this).index() + ")").show().siblings().hide();
                        $(".prompt").hide();
                    },
                    function(){
                        if(descriptionTemp != null){
                            $(".description").text(descriptionTemp);
                        }else{
                            $(".description").text(" ");
                            option.find(".option-con").hide();
                            $(".prompt").show();
                        }


                    }
                );
            });
        });
        function stopDefault(e){
            if(e && e.preventDefault)
                e.preventDefault();
            else
                window.event.returnValue = false;
            return false;
        };
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
                            <label class="control-label">评分：</label>
                            <div class="controls p7">
                                <div class="xzw_starSys">
                                    <div class="xzw_starBox">
                                        <ul class="star stars">
                                            <li><a href="javascript:void(0)" title="1" class="one-star">1</a></li>
                                            <li><a href="javascript:void(0)" title="2" class="two-stars">2</a></li>
                                            <li><a href="javascript:void(0)" title="3" class="three-stars">3</a></li>
                                            <li><a href="javascript:void(0)" title="4" class="four-stars">4</a></li>
                                            <li><a href="javascript:void(0)" title="5" class="five-stars">5</a></li>
                                        </ul>
                                        <div class="current-rating showb" style="width: 0px;"></div>
                                    </div>
                                    <!--评价文字-->
                                    <div class="description"> </div>

                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">评价内容：</label>
                            <div class="controls">
                                <textarea  style="height:75px;padding:5px;width:350px;" id="content" name="content"></textarea>
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
                "content" : {
                    maxlength: 200
                }
            },
            messages : {
                "content" : {
                    maxlength: "最长不能超过200个字符"
                }
            }
        });
    }

    function save() {
        if (checker.form()) {
            var loader = new loadLayer();
            var val = {};
            var content = $("#content").val();
            var id = $("#id").val();
            var url = "${ctp}/dn/complain/" + id;
            val._method = "put";
            val.content = content;
            val.isDispose = null;
            val.isEvaluate = true;
            val.level = level;
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