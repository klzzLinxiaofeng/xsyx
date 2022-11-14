<%-- 
    Document   : discussReplyList
    Created on : 2012-10-12, 17:05:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<style>
    .add{
        width:160px;
    }
</style>
<!-- discuss start -->
<div style="float: right;padding: 7px">
    <%--    <a href="javascript:void(0)" onclick="discussBack('${requestScope.id}')" class="intelligent">
            <img src="${pageContext.request.contextPath}/res/images/common/lads/lads_back_btn.jpg"/>
        </a>--%>
    <a href="javascript:void(0)" onclick="loadDiscussReplyList('${requestScope.id}', '${requestScope.userId}')" class="intelligent">
        <img src="${pageContext.request.contextPath}/res/images/common/lads/lads_refresh_btn.jpg"/>
    </a>
</div>
<div style="border-bottom: 1px solid #CCDBEF;">
   <h1 style="color: #3B55AA;margin-bottom:10px;padding:10px 0 0 16px;font-family: '微软雅黑';font-weight: bold;">
        ${requestScope.discuss.title}
    </h1>
    <p style="font-size: 0.9em;line-height: 1.9em;text-indent: 2em;">
        ${requestScope.discuss.content}
    </p>
</div>

<div class="interaction_content">
    <div id="discussReplyDiv_${requestScope.id}"  >
        <c:forEach items="${requestScope.replyList}" var="vo" varStatus="st">
            <div  class="discuss_list">
                <c:choose>
                    <c:when test='${vo.image eq null || "" eq vo.image}'>
                        <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
                    </c:when>
                    <c:otherwise>
                        <img width="35px" height="36px" class="portrait" src="${vo.image}">
                    </c:otherwise>
                </c:choose>
                <h6 class="name">${vo.userName}
                    <!--                    <p class="a" ><a href="#">推荐</a></p>
                                        <p class="b" ><a href="#">已推荐</a></p>
                                        <span>0 分</span>-->
                </h6>
                <span class="delete1" onclick="deleteDiscussReply(this, '${vo.reply.uuid}', 'Reply')">删除</span>
                <div class="fu">
                    <c:forEach items="${vo.attachmentList}" var="attach" varStatus="stt">
                        <div class="wen">
                            <a class="wenjian" href="/common/resources/frontResourcesAction_download.action?fileId=${attach.fileId}">
                                ${attach.fileName}
                            </a>
                            <span>
                                <a onclick="discussDeleteAttachment(this, '${attach.fileId}', '${vo.reply.uuid}')" class="delete">
                                    删除
                                </a>
                            </span>
                        </div>
                    </c:forEach>
                </div>
                <p style="text-indent: 0">
                    ${vo.reply.content}
                </p>
                <br>
                <p class="fo">
                    ${st.index+1}楼&nbsp;&nbsp;
                    <span>
                        <fmt:formatDate value="${vo.reply.createDate}" pattern="MM-dd hh:mm"/>
                    </span>
                    <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'down')" >
                        回复（${fn:length(vo.childrenReply)}）
                    </a>
                    <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'up')"  style="display:none;">收起回复</a>
                </p>
                <input type="hidden" value="${vo.reply.uuid}" />
                <div class="core_reply_content" style="display:none;">
                    <ul>
                        <c:forEach items="${vo.childrenReply}" var="child" varStatus="sts">
                            <li>
                                <a class="head" href="javascript:void(0)">
                                    <c:choose>
                                        <c:when test='${child.image eq null || "" eq child.image}'>
                                            <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
                                        </c:when>
                                        <c:otherwise>
                                            <img width="35px" height="36px" class="portrait" src="${child.image}">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                <div class="lzl_cnt">
                                    <a href="javascript:void(0)">
                                        ${child.userName}
                                    </a>
                                    <span>
                                        ${child.reply.content}
                                    </span>
                                    <div class="lzl_content_reply">
                                        <span>
                                            <fmt:formatDate value="${child.reply.createDate}" pattern="MM-dd hh:mm"/>
                                        </span>
                                        <a onclick="discussReplyUserReply(this)" href="javascript:void(0)">回复</a>
                                        <a onclick="deleteDiscussReply(this, '${child.reply.uuid}', 'ReplyReply')" href="javascript:void(0)">删除</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        <li style="height:26px;">
                            <p class="j_lzl_p">
                                <a href="javascript:void(0)">我也说一句</a>
                            </p>
                        </li>
                    </ul>
                    <div class="fabiao">
                        <textarea id="discussReplyReply_${requestScope.id}" rows="1" cols="93" class="lzl_simple_wrapper"></textarea>
                        <span onclick="discussReplyReply(this)">发表</span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="interaction_footer">
    <h3>发表：</h3>
    <c:if test="${discussAllowCons eq requestScope.discussAllowUpload}">
        <input class="btn btn-success" type="file" name="uploadFile" id="discussUploadFile_${requestScope.id}" />
        <div id="discussAttachmentList_${requestScope.id}" name="discussAttachmentList_${requestScope.id}" >
        </div>
        <div id="discussUploadFileQueue_${requestScope.id}"></div>
        <script type="text/javascript">
            $(function () {
                $('#discussUploadFile_${requestScope.id}').uploadify({
                    //以下参数均是可选
                    'uploader': '/js/common/upload/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
                    'fileDataName': 'attachment', //和以下input的name属性一致
                    'queueID': 'discussUploadFileQueue_${requestScope.id}', //上传队列的div
                    'script': '/common/lads/ladsDiscussAction_uploadAttachment.action', //指定服务器端上传处理文件，默认‘upload.php’
                    'cancelImg': '/js/common/upload/cancel.png', //指定取消上传的图片，默认‘cancel.png’
                    'buttonImg': '/image/teacher/upload_button.gif', //指定上传按钮的图片
                    'auto': true, //选定文件后是否自动上传，默认false
                    //'buttonText':"选择文件",
                    //'folder' : '/uploads', //要上传到的服务器路径，默认‘/’
                    'muti': false, //是否允许同时上传多文件，默认false
                    //'fileDesc': '请上传ppt或pptx文件', //出现在上传对话框中的文件类型描述
                    //'fileExt': '*.ppt;*.pptx', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
                    'sizeLimit': 524288000, //控制上传文件的大小，单位byte
                    'simUploadLimit': 10, //多文件上传时，同时上传文件数目限制
                    'scriptData': {"toolId": "${requestScope.id}"},
                    onError: function (event, queueID, fileObj) {
                        alert("文件:" + fileObj.name + "上传失败");
                    },
                    onComplete: function (event, queueID, fileObj, response, data) {
                        if (response != null && response != "") {
                            var html = "<div class=\"chuan\">"
                                    + "<span>" + fileObj.name + "</span><input type=\"hidden\" value=\"" + response + "\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
                                    + "<a href=\"javascript:void(0)\" onclick=\"$(this).parent().remove()\" >删除</a>"
                                    + "</div>"
                            $("#discussAttachmentList_${requestScope.id}").append(html);
                        } else {
                            alert("文件:" + fileObj.name + "上传失败");
                        }
                    }
                });
            });
        </script>
    </c:if>
    <div class="editor">
        <textarea id="discussReply_${requestScope.id}" name="discussReply_${requestScope.id}" rows="11"></textarea>
        <script type="text/javascript">
            $(function () {
                //编辑器基本按钮数组
                var baseDiscussReplyEditorItems = ["source", "fullscreen", "undo", "redo", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "clearhtml", "quickformat", "|",
                    "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "removeformat", "|",
                    "emoticons", "image", "flash", "media", "insertfile", "table", "hr", "pagebreak", "anchor", "link", "unlink"];
                //编辑器基本配置参数
                var baseDiscussReplyEditorSetting = {items: baseDiscussReplyEditorItems, filterMode: false, resizeType: 0, filePostName: "file", uploadJson: "${pageContext.request.contextPath}/lads/ke/upload", width: '1040px'};
                var focusDiscussReplyEditor = KindEditor.create('textarea[name="discussReply_${requestScope.id}"]', baseDiscussReplyEditorSetting);
                focusDiscussReplyEditor.html($("#discussReply_${requestScope.id}").val());
                focusDiscussReplyEditor.focus();
            })
        </script>
    </div>
    <button onclick="discussReply(this)">马上发表</button>
</div>
<div id="discussReplyLoading_${requestScope.id}" style="display: none"><img   src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/></div>
<div id="discussReplyReplyLoading_${requestScope.id}" style="display: none"><img   src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/></div>
<script type="text/javascript">
    //主题讨论回复的对象触发器
    var discussReplyTigger;
    function discussHideReplyReply(obj, flag) {
        if (flag == "up") {
            $(obj).prev().show();
        } else if (flag == "down") {
            $(obj).next().show();
        }
        $(obj).hide();
        $(obj).parent().parent().find(".core_reply_content").toggle();
    }

    function discussReplyUserReply(obj) {
        var replyName = $(obj).parent().parent().children().first().text();
        var textarea = $(obj).parent().parent().parent().parent().next().find("textarea");
        textarea.val("回复  " + replyName + " :");
        textarea.focus();
    }

    function createDiscussReply(content, obj, contentId, replyId, attachmentJson) {
        var toolId = contentId.substring(contentId.indexOf("_") + 1);
        var now = new Date();
        now = changeTimeToString(now);
        var replyDiv = $(obj).parent().prev().find("div[id^='discussReplyDiv']");
        var attachment = ""
        if (attachmentJson["attachment"] != null && attachmentJson["attachment"].length > 0) {
            attachment = "<div class=\"fu\">"
            for (var h = 0; h < attachmentJson["attachment"].length; h++) {
                attachment = attachment
                        + "<div class=\"wen\">"
                        + "<a class=\"wenjian\" href=\"/common/resources/frontResourcesAction_download.action?fileId=" + attachmentJson["attachment"][h].fileId + "\">"
                        + attachmentJson["attachment"][h].fileName
                        + "</a>"
                        + "<span><a onclick=\"discussDeleteAttachment(this,'" + attachmentJson["attachment"][h].fileId + "','" + replyId + "')\" class=\"delete\">删除</a></span>"
                        + "</div>"
            }
            attachment = attachment + "</div>";
        }
        var html = "<div class=\"discuss_list\">"
                + "<img class=\"portrait\" width=\"35px\" height=\"36px\" src=\"${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg\"/>"
                + "<h6>我：</h6>"
                + "<span class=\"delete1\" onclick=\"deleteDiscussReply(this, '" + replyId + "','Reply')\">删除</span>"
                + attachment
                + "<p style=\"text-indent: 0\">" + content + "</p>"
                + "<br>"
                + "<p class=\"fo\">" + (replyDiv.children().size() + 1) + "楼&nbsp;&nbsp;<span>" + now + "</span></p>"
                + "<input type=\"hidden\" value=\"" + replyId + "\" />"
                + "<div class=\"core_reply_content\">"
                + "<ul>"
                + "<li style=\"height:26px;\">"
                + "<p class=\"j_lzl_p\">"
                + "<a href=\"javascript:void(0)\">我也说一句</a>"
                + "</p>"
                + "</li>"
                + "</ul>"
                + "<div class=\"fabiao\">"
                + "<textarea id=\"discussReplyReply_" + toolId + "\" rows=\"1\" cols=\"93\" class=\"lzl_simple_wrapper\"></textarea>"
                + "<span onclick=\"discussReplyReply(this)\">发表</span>"
                + "</div>"
                + "</div>";
        replyDiv.append(html);
        //清除内容
        $(obj).parent().find("iframe").contents().find("body").html("");
        $("#discussAttachmentList_" + toolId).html("");
        //使用setIimeout方法延迟1秒加载
        setTimeout("discussReplyImgWidthCon('" + toolId + "')", 200);
    }

    function discussDeleteAttachment(obj, fileId, replyId) {
        if (!confirm("确定删除附件?")) {
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/tool/discuss/deleteAttachment",
            type: "POST",
            data: {"fileId": fileId, "replyId": replyId},
            async: true,
            success: function (cb) {
            }
        });
        $(obj).parent().parent().remove();
    }

    function deleteDiscussReply(obj, replyId, type) {
        $.confirm("确定删除回复?", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/discuss/deleteReply",
                type: "POST",
                data: {"replyId": replyId},
                async: true,
                success: function (cb) {
                }
            });
            if (type == "ReplyReply") {
                $(obj).parent().parent().parent().remove();
            } else if (type == "Reply") {
                $(obj).parent().remove();
            }
        });

    }

    function createDiscussReplyReply(content, obj, replyId) {
        var now = new Date();
        now = changeTimeToString(now);
        var replyDiv = $(obj).parent().prev();
        var html = "<li>"
                + "<a class=\"head\" href=\"javascript:void(0)\">"
                + "<img width=\"35px\" height=\"36px\" class=\"portrait\" src=\"${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg\">"
                + "</a>"
                + "<div class=\"lzl_cnt\">"
                + "<a href=\"javascript:void(0)\">我  </a>"
                + "<span>" + content + "</span>"
                + "<div class=\"lzl_content_reply add\">"
                + "<span>" + now + "</span>"
                + "<a onclick=\"discussReplyUserReply(this)\" href=\"javascript:void(0)\">回复</a>"
                + "<a onclick=\"deleteDiscussReply(this,'" + replyId + "','ReplyReply')\" href=\"javascript:void(0)\">删除</a>"
                + "</div></div></li>"
        replyDiv.children().last().before(html);
        var countObj = $(obj).parent().parent().parent().find("p[class='fo'] a").first();
        var countText = countObj.text();
        var countNo = countText.substring(countText.indexOf("（") + 1, countText.indexOf("）"));
        countObj.text("回复（" + (parseInt(countNo) + 1) + "）");
        $(obj).prev().val("");
    }

    function loadDiscussReply(id) {
        $("#discussReplyDiv_" + id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
        var url = "${pageContext.request.contextPath}/lads/tool/discuss/getMonitoringReplyList?toolId=" + id;
        $("#discussReplyDiv_" + id).load(url);
    }

    function discussReplyReply(obj) {
        discussReplyTigger = obj;
        var contentId = $(obj).prev().attr("id");
        var content = $.trim($(obj).prev().val());
        var toolId = contentId.substring(contentId.indexOf("_") + 1);
        if (content == null || content == "") {
            $.alert("请输入回复内容");
            return;
        }
        openDiscussLoadingOverlay("ReplyReply", toolId);
    }

    function discussReplyImgWidthCon(id) {
        //如果图片过长将会缩小宽度
        var articleWidth = $("#rightContent_" + id).width();
        $("#rightContent_" + id + " #discussReplyDiv_" + id + " img").each(function () {
            if ($(this).width() > articleWidth) {
                $(this).attr("width", (articleWidth - 350));
            }
            if ($(this).height() > 200) {
                $(this).attr("height", 200);
            }
        });
    }

    function ajaxDiscussReplyReply() {
        var contentId = $(discussReplyTigger).prev().attr("id");
        var content = $.trim($(discussReplyTigger).prev().val());
        var toolId = contentId.substring(contentId.indexOf("_") + 1);
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/tool/discuss/reply",
            type: "POST",
            data: {"toolId": toolId, "reply": content, "userId": "<lads:getEmbedUser />",
                "parentReply": $(discussReplyTigger).parent().parent().parent().find("input[type='hidden']").val()},
            async: true,
            success: function (replyId) {
                createDiscussReplyReply(content, discussReplyTigger, replyId);
                discussLoadingOverlayObj.close();
            }
        });
    }

    function ajaxDiscussReply() {
        var contentId = $(discussReplyTigger).prev().find("textarea[id^='discussReply_']").attr("id");
        KindEditor.sync("textarea[id='" + contentId + "']");
        var content = $.trim($("textarea[id='" + contentId + "']").val());
        var toolId = contentId.substring(contentId.indexOf("_") + 1);
        var attachmentJson = {"attachment": []};
        $("#discussAttachmentList_" + toolId).children().each(function () {
            var attach = {"fileName": $(this).find("span").text(), "fileId": $(this).find("input").val()};
            attachmentJson["attachment"].push(attach);
        });
        var replyId = "";
        $.ajax({
            url: "${pageContext.request.contextPath}/lads/tool/discuss/reply",
            type: "POST",
            data: {"toolId": toolId, "reply": content, "userId": "<lads:getEmbedUser />", "attachmentJson": JSON.stringify(attachmentJson)},
            async: true,
            success: function (cb) {
                replyId = cb;
                createDiscussReply(content, discussReplyTigger, contentId, replyId, attachmentJson);
                discussLoadingOverlayObj.close();
            }
        });
    }

    function discussReply(obj) {
        discussReplyTigger = obj;
        var contentId = $(obj).prev().find("textarea[id^='discussReply_']").attr("id");
        KindEditor.sync("textarea[id='" + contentId + "']");
        var content = $.trim($("textarea[id='" + contentId + "']").val());
        var toolId = contentId.substring(contentId.indexOf("_") + 1);
        var attachmentJson = {"attachment": []};
        if (content == null || content == "") {
            $.alert("请输入回复内容");
            return;
        }
        $("#discussAttachmentList_" + toolId).children().each(function () {
            var attach = {"fileName": $(this).find("span").text(), "fileId": $(this).find("input").val()};
            attachmentJson["attachment"].push(attach);
        });
        openDiscussLoadingOverlay("Reply", toolId);
    }

    //overlay弹出层公用函数
    var discussLoadingOverlayObj;
    function openDiscussLoadingOverlay(type, toolId) {
        discussLoadingOverlayObj = new loadLayer();
        discussLoadingOverlayObj.show();
        if (type == "ReplyReply") {
            ajaxDiscussReplyReply();
        } else if (type == "Reply") {
            ajaxDiscussReply();
        }
    }

</script>
