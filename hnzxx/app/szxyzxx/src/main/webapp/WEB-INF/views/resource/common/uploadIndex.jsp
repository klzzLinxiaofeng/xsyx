<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="platform.education.resource.contants.ResourceType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>上传资源</title>
        <style>
            .form-horizontal .controls {
                margin-left: 0px;
            }
            .form-horizontal .controls .weike {
                border: 0 none;
                top: 0px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="上传资源" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                上传资源
                                <p style="float:right;" class="btn_link">
                                    <a class="a4" href="${pageContext.request.contextPath}/resource/myResource?index=index&resType=<%=ResourceType.LEARNING_DESIGN%>"><i class="fa fa-reply"></i>返回我的资源</a>
                                </p>
                            </h3>
                        </div>
                        <div class="content-widgets" style="margin-bottom:0">
                            <div class="form-horizontal">
                                <div class="controls">
                                    <div class="weike">
                                        <div style="display:block;" class="upload_wk_1">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        function init() {
            var loader = new loadDialog();
            loader.show();
            var url = "${pageContext.request.contextPath}";
            //本地上传
            url = url + "/resource/upload?resType=${resType}";
            $(".upload_wk_1").load(url, function() {
                loader.close();
            });
        }

        function removeUploadMicro() {
            init();
        }

        $(function() {
            init();
        });
    </script>
</html>