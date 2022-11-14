<%-- 
    Document   : mediatoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td><input onkeyup="changeTitle('${requestScope.id}', this.value)" name="mediaTitle_${requestScope.id}" id="mediaTitle_${requestScope.id}" value="${requestScope.title}" type="text"/></td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td><input onkeyup="checkScore('${requestScope.id}', this)" name="mediaScore_${requestScope.id}" id="mediaScore_${requestScope.id}" value="${requestScope.mediaScore}" type="text"/></td>
    </tr>
</table>

<div class="m-upbtn">
    <div id="mediaupbtnDiv_${requestScope.id}" class="nav_div">
        <a onclick="changeMediaTab('edit', '${requestScope.id}');" class="x-btn on">上传微视频</a>
        <a onclick="changeMediaTab('upload', '${requestScope.id}');" class="x-btn">配套资料</a>
    </div>
    <input type="hidden" id="mediaEntityId_${requestScope.id}"  value="${requestScope.mediaEntityId}" />
    <input type="hidden" id="mediaEntityUrl_${requestScope.id}" value="<entity:getHttpUrl uuid="${requestScope.mediaEntityId}"/>" />
    <input type="hidden" id="mediaCacheEntityId_${requestScope.id}"   />
    <input type="hidden" id="mediaCacheEntityUrl_${requestScope.id}" />
    <div style="position:relative" id="mediaEditDiv_${requestScope.id}"  >
    </div>


    <div id="mediaUploadDiv_${requestScope.id}" >
        <input type="button" id="mediaUploadButton_${requestScope.id}" value="上传配套资料" />
        <table id="mediaUploadTable_${requestScope.id}" class="m-table" style="width:970px">
            <th>标题</th>
            <th>上传时间</th>
            <th>操作</th>
        </table>
    </div>
</div>

<script type="text/javascript">
    function initMediaContent(id) {
        var entityId = $("#mediaEntityId_" + id).val();
        if (entityId != null && entityId != "") {
            initPlayMedia(id);
        } else {
            if (typeof (Worker) !== "undefined") {
                var uploadHtml = "<div id=\"mediaUploadDivPart1_" + id + "\" class=\"upload_wk\">"
                        + "<div class=\"u_wk\">"
                        + "<div class=\"upload_file\" id=\"mediaUploader_" + id + "\">"
                        + "<div id=\"picker_" + id + "\"></div>"
                        + "<p style=\"color:red;text-align: center;line-height: 40px;font-size: 14px;\">请上传数据速率为7954kbps、总比特率为8051kbps、帧速率为24帧/秒、格式为MP4的视频文件</p>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "<div id=\"mediaUploadDivPart2_" + id + "\" class=\"ul_wk\" style=\"display: none\">"
                        + "<div class=\"u_message\">"
                        + "<div class=\"jindu\" id=\"progress_" + id + "\" style=\"width:635px;display:block;float:left\">"
                        + "</div>"
                        + "<a onclick=\"removeMediaEntity('" + id + "')\" class=\"no\" href=\"javascript:void(0)\">取消上传</a>"
                        + "<div class=\"clear\"></div>"
                        + "<div class=\"sudu\">"
                        + "</div>"
                        + "</div>"
                        + "</div>";
                $("#mediaEditDiv_" + id).html(uploadHtml);
                $("#mediaUploader_" + id).fadeIn("slow");
                $("#mediaUploader_" + id).xyUploader({
                    basePath: "${pageContext.request.contextPath}",
                    formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                    picker: {id: "#picker_" + id, innerHTML: "上传视频", multiple: false},
                    progress: "progress_" + id,
                    referId: id,
                    accept: {title: 'MEDIA', extensions: "*.*", allowExts: "mp4"},
                    removeTimeout: 20,
                    uploadSuccess: function (file, response) {
                        return uploadSucCallBack(response);
                    },
                    uploadError: function (code) {
                        $.alert(code);
                    }
                });
            }
            // 上传回调
            var uploadSucCallBack = function (json) {
                if (json.finishedFlag === 1) {
                    $("#mediaCacheEntityId_" + id).val(json.uuid);
                    $("#mediaCacheEntityUrl_" + id).val(json.url);
                }
            };
        }
    }

    function saveMediaInfo(id) {
        var mediaFileName = $.trim($("#mediaEditDiv_" + id + " .file_name").text());
        mediaFileName = mediaFileName.substring(0, mediaFileName.lastIndexOf("."));
        $("#mediaTitle_" + id).val(mediaFileName);
        $("#mediaEntityId_" + id).val($("#mediaCacheEntityId_" + id).val());
        $("#mediaEntityUrl_" + id).val($("#mediaCacheEntityUrl_" + id).val());
        $("#mediaCacheEntityId_" + id).val("");
        $("#mediaCacheEntityUrl_" + id).val("")
        changeTitle(id, mediaFileName);
        initPlayMedia(id);
    }

    function removeMediaEntity(id) {
        $("#mediaEntityId_" + id).val("");
        $("#mediaEntityUrl_" + id).val("");
        $("#mediaCacheEntityId_" + id).val("");
        $("#mediaCacheEntityUrl_" + id).val("");
        initMediaContent(id);
    }

    function initPlayMedia(id) {
        $("#mediaEditDiv_" + id).html("<a href=\"javascript:void(0)\" style=\"position:relative;top:0;right:0;margin-right:4px;\" class=\"m-delete\" onclick=\"removeMediaEntity('" + id + "')\" >删除</a><div style=\"width:900px;margin:0 auto;\"><div id=\"mediaPlayerDiv_" + id + "\"></div></div>");
        var httpUrl = $.trim($("#mediaEntityUrl_" + id).val());
        var width = 900;
        var height = 500;
        var currentPlayer = jwplayer("mediaPlayerDiv_" + id).setup({
            width: width,
            height: height,
            primary: "flash",
            autostart: true,
            //image: "/image/teacher/music2.jpg",
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
    }

    function addMediaUploadTr(id, title, createDate, entityId) {
        var downUrl = "${pageContext.request.contextPath}/lads/common/download?entityId=" + entityId;
        var html = "<tr><td><i></i><a target='_blank' href='" + downUrl + "'>" + title + "</a><input type='hidden' value='" + entityId + "' /></td>"
                + "<td>" + createDate + "</td>"
                + "<td><a target='_blank' href='" + downUrl + "'>下载</a><a href='javascript:void(0)' onclick='$(this).parent().parent().remove()'>删除</a></td></tr>";
        if ($("#mediaUploadTable_" + id + " tr[name='nofile']").html() != null) {
            $("#mediaUploadTable_" + id + " tr[name='nofile']").remove();
        }
        $("#mediaUploadTable_" + id).append(html);
    }

    function changeMediaTab(tabName, id) {
        if (tabName == "upload") {
            $("#mediaupbtnDiv_" + id).children().first().removeClass("on");
            $("#mediaupbtnDiv_" + id).children().last().addClass("on");
            $("#mediaUploadDiv_" + id).show();
            $("#mediaEditDiv_" + id).hide();
        } else if (tabName == "edit") {
            $("#mediaupbtnDiv_" + id).children().last().removeClass("on");
            $("#mediaupbtnDiv_" + id).children().first().addClass("on");
            $("#mediaUploadDiv_" + id).hide();
            $("#mediaEditDiv_" + id).show();
        }
    }

    function initMediaUpload(id) {
        var jsonString = '${mediaUploadList}';
        var html = "<tr name='nofile'><td  colspan='3'>没有资料</td></tr>";
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
        } else {
            $("#mediaUploadTable_" + id).append(html);
        }
        var uploadbutton = KindEditor.uploadbutton({
            button: KindEditor('#mediaUploadButton_' + id)[0],
            fieldName: 'file',
            url: '${pageContext.request.contextPath}/lads/common/uploader',
            afterUpload: function (data) {
                if (data != null) {
                    var url = KindEditor.formatUrl(data.url, 'absolute');
                    var title = data.realFileName;
                    var entityId = data.uuid;
                    var createDate = data.fmtCreateDate;
                    addMediaUploadTr(id, title, createDate, entityId);
                } else {
                    $.alert("上传失败");
                }
            },
            afterError: function (str) {
                $.alert('自定义错误信息: ' + str);
            }
        });
        uploadbutton.fileBox.change(function (e) {
            uploadbutton.submit();
        });
    }

    function getMediaToolSaveData(activity, id) {
        activity["mediaTitle"] = $("#mediaTitle_" + id).val();
        activity["mediaScore"] = $("#mediaScore_" + id).val();
        activity["mediaEntityId"] = $("#mediaEntityId_" + id).val();
        activity["mediaUploadList"] = [];
        $("#mediaUploadTable_" + id + " input[type='hidden']").each(function () {
            var entityId = $(this).val();
            var title = $.trim($(this).prev().text());
            var fmtCreateDate = $.trim($(this).parent().next().text());
            var file = {"entityId": entityId, "title": title, "fmtCreateDate": fmtCreateDate};
            activity["mediaUploadList"].push(file);
        });
        return activity;
    }

    $(function () {
        //使用setIimeout方法延迟1秒加载是为了解决webupload第一次点不了的bug
        setTimeout("initMediaContent('${requestScope.id}')", 500);
        //初始化上传列表
        initMediaUpload('${requestScope.id}');
        changeMediaTab("edit", '${requestScope.id}');
    });
</script>