<%-- 
    Document   : mediatoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<h3>${requestScope.title}</h3>
<input type="hidden" id="mediaEntityId_${requestScope.id}"  value="${requestScope.mediaEntityId}" />
<input type="hidden" id="mediaScore_${requestScope.id}"  value="${requestScope.mediaScore}" />
<input type="hidden" id="mediaEntityUrl_${requestScope.id}" value="<entity:getHttpUrl uuid="${requestScope.mediaEntityId}"/>" />
<div id="mediaContentDiv_${requestScope.id}">
    <div class="fr video-right">
        <div  class="video" style="margin-bottom:10px;">
            <div id="mediaPlayerDiv_${requestScope.id}"></div>
        </div>
        <div class="table">
            <table border="1">
                <thead>
                    <tr>
                        <th><i></i>配套资料</th>
                        <th>上传时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="mediaUploadTable_${requestScope.id}">
                </tbody>
            </table>
        </div>

    </div>
</div>


<script type="text/javascript">

    var currentMediaPlayer;

    function initMediaContent(id) {
        initMediaPlay(id);
        var jsonString = '${requestScope.mediaUploadList}';
        var html = "<tr><td colspan='3'>没有资料</td></tr>";
        if (jsonString != "") {
            var uploadList = JSON.parse(jsonString);
            if (uploadList.length > 0) {
                for (var i = 0; i < uploadList.length; i++) {
                    var title = uploadList[i].title;
                    var entityId = uploadList[i].entityId;
                    var createDate = uploadList[i].fmtCreateDate;
                    addMediaUploadTr(id, title, createDate, entityId);
                }
            } else {
                $("#mediaUploadTable_" + id).append(html);
            }
        }
    }

    function initMediaPlay(id) {
        var httpUrl = $.trim($("#mediaEntityUrl_" + id).val());
        var width = 772;
        var height = 452;
        currentMediaPlayer = jwplayer("mediaPlayerDiv_" + id).setup({
            width: width,
            height: height,
            primary: "flash",
            autostart: true,
            //image: "/image/teacher/music2.jpg",
            file: httpUrl,
            events: {
                onPlay: function(event) {

                },
                onBeforePlay: function(event) {
                },
                onPause: function(event) {
                },
                onReady: function(event) {

                },
                onComplete: function(event) {
                    //非预览模式提交学习进度
                    if (!previewMode) {
                        saveMediaUserRecord(id, "complete");
                        clearInterval(statusInterval);
                    }
                },
                onSeek: function(event) {
                }
            }
        });
        if(httpUrl==null||httpUrl==""){
           $("#mediaPlayerDiv_"+id+" p").text("没有上传视频");   
        }
    }

    function saveMediaUserRecord(id, finishedFlag) {
        var data = {};
        data.finishedFlag = finishedFlag;
        data.toolId = id;
        data.mediaScore = $("#mediaScore_"+id).val();
        data.userId = "<lads:getEmbedUser />";
        data.lastPlayTime = currentMediaPlayer.getPosition();
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/tool/media/saveUserStatus",
            type: "POST",
            data: data,
            async: true,
            success: function(result) {
                if(finishedFlag!=null&&finishedFlag=="complete"){
                   if($("#button_"+id+" b .status").length <= 0){
                      $("#button_"+id+" b").append("<span class=\"status finish\">&nbsp;</span>");
                   }
                }
            }
        });
    }


    function addMediaUploadTr(id, title, createDate, entityId) {
        var downUrl = "${pageContext.request.contextPath}/lads/common/download?entityId=" + entityId;
        var html = "<tr><td><i></i><a target='_blank' href='" + downUrl + "'>" + title + "</a><input type='hidden' value='" + entityId + "' /></td>"
                + "<td>" + createDate + "</td>"
                + "<td><a target='_blank' href='" + downUrl + "'>下载</a></td></tr>";
        $("#mediaUploadTable_" + id).append(html);
    }

    $(function() {
        //初始化内容
        initMediaContent("${requestScope.id}");

        //预览模式不需要计算学习时间
        if (!previewMode) {
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/media/saveUserStatus",
                data: {"toolId": "${requestScope.id}", "mediaScore": "${requestScope.mediaScore}", "userId": "<lads:getEmbedUser />"},
                type: 'POST', //请求方式
                async: true,
                cache: false//是否缓存
            });
            //轮询记录当前播放进度,15秒记录一次
            //statusInterval在learningpaper里定义为学习记录定时器     media工具学习记录定时器
            statusInterval = window.setInterval('saveMediaUserRecord(${requestScope.id})', 15000);
        }
    });
</script>

