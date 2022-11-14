<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.html5.js"></script>
        <title>查看视频</title>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <input id="mediaPreviewhttpUrl_${requestScope.id}" type="hidden" value="<entity:getHttpUrl uuid="${requestScope.media.entityId}"/>" />    
                        <div id="mediaPreviewFlashDiv_${requestScope.id}"  style="">
                        </div>
                        <!--                        <div class="form-actions tan_bottom"> 
                                                    <button class="btn" onclick="$.closeWindow();" type="button">取消</button> 
                                                </div> -->
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(function () {
            callMediaPreviewJwplay("${requestScope.id}", 683, 427);
        });

        //调用JWPlayer
        function callMediaPreviewJwplay(id, width, height) {
            var httpUrl = $("#mediaPreviewhttpUrl_" + id).val();
            jwplayer("mediaPreviewFlashDiv_" + id).setup({
                width: width,
                height: height,
                primary: "flash",
                autostart: true,
                file: httpUrl,
                events: {
                    onPlay: function (event) {
                    },
                    onBeforePlay: function (event) {
                    },
                    onPause: function (event) {
                    },
                    onReady: function (event) {
                    },
                    onComplete: function (event) {
                    },
                    onSeek: function (event) {
                    }
                }
            });
            if (httpUrl == null || httpUrl == "") {
                $("#mediaPreviewFlashDiv_" + id + " p").text("没有上传视频");
            }
        }
    </script>
</html>