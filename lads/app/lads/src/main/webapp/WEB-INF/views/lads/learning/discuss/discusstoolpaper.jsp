<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<!-- discuss start -->
<div class="interaction_header">
    <h3>主题：${requestScope.title}</h3>
    <p>${requestScope.discussContent}</p>
</div>
<div style="float: right;padding: 7px">
    <a href="javascript:void(0)" onclick="loadDiscussReply('${requestScope.id}')" class="intelligent">
        <img src="${pageContext.request.contextPath}/res/images/common/lads/lads_refresh_btn.jpg"/>
    </a>
</div>
<div class="interaction_content" style="margin-top:40px;">
    <div id="discussReplyDiv_${requestScope.id}" >
        <c:forEach varStatus="st" items="${requestScope.replyList}" var="vo">
            <div class="discuss_list">
                <c:choose>
                    <c:when test='${vo.image eq null || "" eq vo.image}'>
                        <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
                    </c:when>
                    <c:otherwise>
                        <img width="35px" height="36px" class="portrait" src="${vo.image}">
                    </c:otherwise>
                </c:choose>
                <h6>${vo.userName}：</h6>
                <div class="fu">
                    <c:forEach items="${vo.attachmentList}" var="at" >
                        <div class="wen">
                            <a class="wenjian" href="${pageContext.request.contextPath}/common/resources/frontResourcesAction_download?fileId=${at.fileId}">
                                ${at.fileName}
                            </a>
                            <c:if test="${vo.canDelete}">
                                <span>
                                    <a onclick="discussDeleteAttachment(this, '${at.fileId}', '${vo.reply.uuid}')" class="delete">
                                        删除
                                    </a>
                                </span>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <p style="text-indent: 0">
                    ${vo.reply.content}
                </p>
                <br>
                <p class="fo">
                    ${st.index+1}楼&nbsp;&nbsp;<span>
                        <fmt:formatDate pattern="MM-dd hh:mm" value="${vo.reply.createDate}"></fmt:formatDate>
                        </span>
                        <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'down')"  >回复（${fn:length(vo.childrenReply)}）</a>
                    <a href="javascript:void(0)" onclick="discussHideReplyReply(this, 'up')" style="display:none;">收起回复</a>
                </p>
                <input type="hidden" value="${vo.reply.uuid}" />
                <div class="core_reply_content" style="display:none;">
                    <ul>
                        <c:forEach items="${vo.childrenReply}" var="cr" >
                            <li>
                                <a class="head" href="javascript:void(0)">
                                    <c:choose>
                                        <c:when test='${cr.image eq null || "" eq cr.image}'>
                                            <img width="35px" height="36px" class="portrait" src="${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg">
                                        </c:when>
                                        <c:otherwise>
                                            <img width="35px" height="36px" class="portrait" src="${cr.image}">
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                                <div class="lzl_cnt">
                                    <a href="javascript:void(0)">${cr.userName}</a>
                                    <span>${cr.reply.content}</span>
                                    <div class="lzl_content_reply">
                                        <span>
                                            <fmt:formatDate pattern="MM-dd hh:mm" value="${cr.reply.createDate}"></fmt:formatDate>
                                            </span>
                                            <a onclick="discussReplyUserReply(this)" href="javascript:void(0)">回复</a>
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
<div style="float: right;padding: 7px">
    <a href="javascript:void(0)" onclick="loadDiscussReply('${requestScope.id}')" class="intelligent">
        <img src="${pageContext.request.contextPath}/res/images/common/lads/lads_refresh_btn.jpg"/>
    </a>
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
                    'script': '${pageContext.request.contextPath}/lads/tool/discuss/uploadAttachment', //指定服务器端上传处理文件，默认‘upload.php’
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
                var baseDiscussReplyEditorSetting = {items: baseDiscussReplyEditorItems, filterMode: false, resizeType: 0, filePostName: "file", uploadJson: "${pageContext.request.contextPath}/lads/ke/upload", width: '770px'};
                var focusDiscussReplyEditor = KindEditor.create('textarea[name="discussReply_${requestScope.id}"]', baseDiscussReplyEditorSetting);
                focusDiscussReplyEditor.html($("#discussReply_${requestScope.id}").val());
                focusDiscussReplyEditor.focus();
            })
        </script>
    </div>
    <button onclick="discussReply(this)">马上发表</button>
</div>
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
        var replyDiv = $(obj).parent().prev().prev().find("div[id^='discussReplyDiv']");
        var attachment = ""
        if (attachmentJson["attachment"] != null && attachmentJson["attachment"].length > 0) {
            attachment = "<div class=\"fu\">"
            for (var h = 0; h < attachmentJson["attachment"].length; h++) {
                attachment = attachment
                        + "<div class=\"wen\">"
                        + "<a class=\"wenjian\" href=\"${pageContext.request.contextPath}/lads/common/download?entityId=" + attachmentJson["attachment"][h].fileId + "\">"
                        + attachmentJson["attachment"][h].fileName
                        + "</a>"
                        + "<span><a onclick=\"discussDeleteAttachment(this,'" + attachmentJson["attachment"][h].fileId + "','" + replyId + "')\" class=\"delete\">删除</a></span>"
                        + "</div>"
            }
            attachment = attachment + "</div>"
        }
        var html = "<div class=\"discuss_list\">"
                + "<img class=\"portrait\" width=\"35px\" height=\"36px\" src=\"${pageContext.request.contextPath}/res/images/common/lads/noPhoto.jpg\"/>"
                + "<h6>我：</h6>"
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
        setTimeout("discussReplyImgWidthCon('"+toolId+"')", 200);
    }

    function discussDeleteAttachment(obj, fileId, replyId) {
        if (!previewMode) {
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/discuss/deleteAttachment",
                type: "POST",
                data: {"fileId": fileId, "replyId": replyId},
                async: true,
                success: function (cb) {
                }
            });
        }
        $(obj).parent().parent().remove();
    }

    function createDiscussReplyReply(content, obj, contentId) {
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
                + "<div class=\"lzl_content_reply\">"
                + "<span>" + now + "</span>"
                + "<a onclick=\"discussReplyUserReply(this)\" href=\"javascript:void(0)\">回复</a>"
                + "</div></div></li>"
        replyDiv.children().last().before(html);
        var countObj = $(obj).parent().parent().parent().find("p[class='fo'] a").first();
        var countText = countObj.text();
        var countNo = countText.substring(countText.indexOf("（") + 1, countText.indexOf("）"));
        countObj.text("回复（" + (parseInt(countNo) + 1) + "）")
        $(obj).prev().val("");
    }

    function loadDiscussReply(id) {
        var timestmp = (new Date()).valueOf();//将时间戳追加到url上面
        $("#discussReplyDiv_" + id).html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" title="加载中" alt="加载中"/>');
        var url = "${pageContext.request.contextPath}/lads/tool/discuss/getLearningReplyList?toolId=" + id + "&sessionUserId=<lads:getEmbedUser />";
        $("#discussReplyDiv_" + id).load(url + "&t=" + timestmp,function(){
            if($.trim($("#discussReplyDiv_"+id).html())==""){
                //没内容时不需要刷新按钮
                $("#rightContent_${requestScope.id} a[class='intelligent']").eq(1).hide();
            }else{
                discussReplyImgWidthCon(id);
            }
        });
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
        //预览模式不需要把回复内容存进数据库
        if (!previewMode) {
            openDiscussLoadingOverlay("ReplyReply", toolId);
        } else {
            createDiscussReplyReply(content, obj, contentId);
        }
    }

    function discussReplyImgWidthCon(id) {
        //如果图片过长将会缩小宽度
        var articleWidth = $("#rightContent_"+id).width();
        $("#rightContent_"+id+" #discussReplyDiv_"+id+" img").each(function () {
            if ($(this).width() > articleWidth) {
                $(this).attr("width", (articleWidth-80));
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
            success: function () {
                createDiscussReplyReply(content, discussReplyTigger, contentId);
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
                //发表过内容就算完成
                if($("#button_"+toolId+" b .status").length <= 0){
                   $("#button_"+toolId+" b").append("<span class=\"status finish\">&nbsp;</span>");
                }
                
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
        //预览模式不需要把回复内容存进数据库
        if (!previewMode) {
            openDiscussLoadingOverlay("Reply", toolId);
        } else {
            createDiscussReply(content, obj, contentId, "", attachmentJson);
        }
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

    $(function () {
        //预览模式不需要读取回复内容
        if (!previewMode) {
            loadDiscussReply("${requestScope.id}");
        } else {
            //预览模式把刷新按钮去掉
            $("#rightContent_${requestScope.id} a[class='intelligent']").hide();
        }
    });

</script>
<!-- discuss end -->
