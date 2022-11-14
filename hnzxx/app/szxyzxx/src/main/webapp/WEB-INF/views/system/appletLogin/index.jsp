<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%@ include file="/views/embedded/common.jsp" %>
    <%@include file="/views/embedded/plugin/grade_selector_js.jsp" %>
    <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="登录管理" name="title"/>
        <jsp:param value="" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">

            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        小程序登录页管理
                    </h3>
                </div>
                <div class="light_grey"></div>


                <div class="content-widgets" style="margin-bottom: 0">
                    <div class="widget-container" style="padding: 20px 0 0;">
                        <form class="form-horizontal tan_form" id="supplier_form" action="javascript:void(0);">


                            <div class="control-group">
                                <label class="control-label" style="width: 17%;">
                                    加载页背景图：
                                </label>
                                <div class="controls">
                                    <img id="img" src="${empty imgPath ? '/res/images/no_picture.jpg' : imgPath }" title="点击重新上传" style="width: 400px;height: 600px;">
                                </div>
                            </div>

                            <button class="btn btn-warning" type="button" style="margin-left: 20%"
                                    onclick="saveOrUpdate();">修改
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">

    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#img' //绑定元素
            ,url: '/uploader/common2' //上传接口
            ,done: function(res){
                if(res==null || res==""){
                    layer.alert("图片上传失败");
                    return;
                }

                $("#img").attr("src",res.url);

            }
            ,error: function(){
                layer.alert("图片上传失败");
            }
        });
    });


    function saveOrUpdate(){
        var ind=layer.load(2);
        $.post("/system/applets/upBg",{path:$("#img").attr("src")},function(d){
            layer.close(ind)
            if(d==1){
                layer.msg('修改成功', {
                    icon: 1,
                    time: 2000
                });
            }else{
                layer.alert("修改失败");
            }
        })
    }

</script>

