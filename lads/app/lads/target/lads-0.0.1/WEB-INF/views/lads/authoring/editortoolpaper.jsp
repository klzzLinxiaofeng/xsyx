<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td><input onkeyup="changeTitle('${requestScope.id}', this.value)" name="editorTitle_${requestScope.id}" id="editorTitle_${requestScope.id}" value="${requestScope.title}" type="text"/></td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td><input onkeyup="checkScore('${requestScope.id}', this)" name="editorScore_${requestScope.id}" id="editorScore_${requestScope.id}" value="${requestScope.editorScore}" type="text"/></td>
    </tr>
</table>

<div class="m-upbtn">
    <div id="editorupbtnDiv_${requestScope.id}" class="nav_div">
        <a onclick="changeEditorTab('edit', '${requestScope.id}');" class="x-btn on">添加资料</a>
        <a onclick="changeEditorTab('upload', '${requestScope.id}');" class="x-btn">上传资料</a>
    </div>
    <div id="editorUploadDiv_${requestScope.id}">
        <input type="button" id="editorUploadButton_${requestScope.id}" value="上传资料" />
        <table id="editorUploadTable_${requestScope.id}" class="m-table" style="width:970px">
            <th>标题</th>
            <th>上传时间</th>
            <th>操作</th>
        </table>
    </div>

    <div id="editorEditDiv_${requestScope.id}"  >
        <textarea id="editorContent_${requestScope.id}" style="display: none" >${requestScope.editorContent}</textarea>
    </div>
</div>

<script type="text/javascript">
            function loadEditorContent(id) {
                var html = "<textarea name=\"editorTextArea_" + id + "\" id=\"editorTextArea_" + id + "\"  style=\"width:100%;height:420px;visibility:hidden;\"  ></textarea>";
                $("#editorEditDiv_" + id).append(html);
                //编辑器基本按钮数组
                var baseEditorItems = ["source", "fullscreen", "undo", "redo", "cut", "copy", "paste", "plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "clearhtml", "quickformat", "|",
                    "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "removeformat", "|",
                    "emoticons", "image", "flash", "media", "insertfile", "table", "hr", "baidumap", "pagebreak", "anchor", "link", "unlink"];
                //编辑器基本配置参数
                var baseEditorSetting = {items: baseEditorItems, filterMode: false, resizeType: 0, filePostName: "file", uploadJson: "${pageContext.request.contextPath}/lads/ke/upload"};
                var focusEditor = KindEditor.create('textarea[name="editorTextArea_' + id + '"]', baseEditorSetting);
                focusEditor.html($("#editorContent_" + id).val());
                focusEditor.focus();
            }

            function addEditorUploadTr(id, title, createDate, entityId) {
                var downUrl = "${pageContext.request.contextPath}/lads/common/download?entityId=" + entityId;
                var html = "<tr><td><i></i><a target='_blank' href='" + downUrl + "'>" + title + "</a><input type='hidden' value='" + entityId + "' /></td>"
                        + "<td>" + createDate + "</td>"
                        + "<td><a target='_blank' href='" + downUrl + "'>下载</a><a href='javascript:void(0)' onclick='$(this).parent().parent().remove()'>删除</a></td></tr>";
                if($("#editorUploadTable_" + id +" tr[name='nofile']").html()!=null){
                    $("#editorUploadTable_" + id +" tr[name='nofile']").remove();
                }
                $("#editorUploadTable_" + id).append(html);
            }

            function changeEditorTab(tabName, id) {
                if (tabName == "upload") {
                    $("#editorupbtnDiv_"+id).children().first().removeClass("on");
                    $("#editorupbtnDiv_"+id).children().last().addClass("on");
                    $("#editorUploadDiv_" + id).show();
                    $("#editorEditDiv_" + id).hide();
                } else if (tabName == "edit") {
                    $("#editorupbtnDiv_"+id).children().last().removeClass("on");
                    $("#editorupbtnDiv_"+id).children().first().addClass("on");
                    $("#editorUploadDiv_" + id).hide();
                    $("#editorEditDiv_" + id).show();
                }
            }

            function initEditorUpload(id) {
                var jsonString = '${editorUploadList}';
                var html = "<tr name='nofile'><td colspan='3'>没有资料</td></tr>";
                if (jsonString != "") {
                    var uploadList = JSON.parse(jsonString);
                    if (uploadList.length > 0) {
                        for (var i = 0; i < uploadList.length; i++) {
                            var title = uploadList[i].title;
                            var entityId = uploadList[i].entityId;
                            var createDate = uploadList[i].fmtCreateDate;
                            addEditorUploadTr(id, title, createDate, entityId);
                        }
                    } else {
                        $("#editorUploadTable_" + id).append(html);
                    }
                } else {
                    $("#editorUploadTable_" + id).append(html);
                }
                var uploadbutton = KindEditor.uploadbutton({
                    button: KindEditor('#editorUploadButton_' + id)[0],
                    fieldName: 'file',
                    url: '${pageContext.request.contextPath}/lads/common/uploader',
                    afterUpload: function(data) {
                        if (data != null) {
                            var url = KindEditor.formatUrl(data.url, 'absolute');
                            var title = data.realFileName;
                            var entityId = data.uuid;
                            var createDate = data.fmtCreateDate;
                            addEditorUploadTr(id, title, createDate, entityId);
                        } else {
                            $.alert("上传失败");
                        }
                    },
                    afterError: function(str) {
                        $.alert('自定义错误信息: ' + str);
                    }
                });
                uploadbutton.fileBox.change(function(e) {
                    uploadbutton.submit();
                });
                changeEditorTab('edit', id);
            }

            function getEditorToolSaveData(activity, id) {
                activity["editorTitle"] = $("#editorTitle_" + id).val();
                activity["editorScore"] = $("#editorScore_" + id).val();
                KindEditor.sync("textarea[id='editorTextArea_" + id + "']");
                activity["editorContent"] = $.trim($("textarea[id='editorTextArea_" + id + "']").val());
                activity["editorUploadList"] = [];
                $("#editorUploadTable_" + id + " input[type='hidden']").each(function() {
                    var entityId = $(this).val();
                    var title = $.trim($(this).prev().text());
                    var fmtCreateDate = $.trim($(this).parent().next().text());
                    var file = {"entityId": entityId, "title": title, "fmtCreateDate": fmtCreateDate};
                    activity["editorUploadList"].push(file);
                });
                return activity;
            }

            $(function() {
                //使用setIimeout方法延迟0.01秒加载是为了解决firefox下多个kindeditor编辑区点不了的bug
                setTimeout("loadEditorContent('${requestScope.id}')", 100);
                //初始化上传列表
                initEditorUpload('${requestScope.id}');
            });
</script>