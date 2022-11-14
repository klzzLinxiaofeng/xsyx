<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<h3>${requestScope.title}</h3>

<div id="editorToolTab_${requestScope.id}" class="xm-tab-list">
</div>

<div id="editorUploadDiv_${requestScope.id}" style="display: none">
    <div class="table">
        <table border="1" >
            <thead>
                <tr>
                    <th><i></i>教学资料</th>
                    <th>上传时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<div id="editorEditDiv_${requestScope.id}"  style="display: none">
    <textarea id="editorContent_${requestScope.id}" style="display: none" >${requestScope.editorContent}</textarea>
</div>


<script type="text/javascript">
    function initEditorContent(id) {
        var uploadJson = '${editorUploadList}';
        var content = $.trim($("#editorContent_" + id).val());
        var uploadList = "";
        if (uploadJson != "") {
            uploadList = JSON.parse(uploadJson);
        }
        if (uploadList != null && uploadList != "" && uploadList.length > 0 && content != null && content != "") {
                    
            var html = "<a class=\"ydzz\" onclick=\"changeEditorTab('edit', '" + id + "');\">阅读资料</a>"
                    +  "<a class=\"xzzz\" onclick=\"changeEditorTab('upload', '" + id + "');\">下载资料</a>";
            $("#editorToolTab_" + id).append(html);
        }
        if (uploadList != null && uploadList != "" && uploadList.length > 0) {
            for (var i = 0; i < uploadList.length; i++) {
                var title = uploadList[i].title;
                var createDate = uploadList[i].fmtCreateDate;
                var entityId = uploadList[i].entityId;
                var downUrl = "${pageContext.request.contextPath}/lads/common/download?entityId=" + entityId;
                var tr = "<tr><td><i></i><a target='_blank' href='" + downUrl + "'>" + title + "</a></td>"
                        +"<td>"+createDate+"</td>"
                        +"<td><a target='_blank' href='" + downUrl + "'>下载</a></td></tr>";
                $("#editorUploadDiv_" + id + " table tbody").append(tr);
            }
        }
        if (content != null && content != "") {
            $("#editorEditDiv_" + id).append(content);
        }
        if (uploadList != null && uploadList != "" && uploadList.length > 0) {
            changeEditorTab("upload", id);
        } else {
            changeEditorTab("edit", id);
        }
    }

    function changeEditorTab(tabName, id) {
        if (tabName == "upload") {
            $("#editorUploadDiv_" + id).show();
            $("#editorEditDiv_" + id).hide();
            $("#editorToolTab_"+id+" .xzzz").addClass("on");
            $("#editorToolTab_"+id+" .ydzz").removeClass("on");
        } else if (tabName == "edit") {
            $("#editorUploadDiv_" + id).hide();
            $("#editorEditDiv_" + id).show();
            $("#editorToolTab_"+id+" .xzzz").removeClass("on");
            $("#editorToolTab_"+id+" .ydzz").addClass("on");
        }
    }


    $(function() {
        //初始化内容
        initEditorContent("${requestScope.id}");

        //预览模式不需要计算学习时间
        if (!previewMode) {
            $.ajax({
                url: "${pageContext.request.contextPath}/lads/tool/editor/saveUserStatus",
                data: {"toolId": "${requestScope.id}", "editorScore": "${requestScope.editorScore}", "userId": "<lads:getEmbedUser />"},
                type: 'POST', //请求方式
                async: true,
                cache: false,//是否缓存
                success:function(){
                    //暂时的规则是只要打开过就已完成
                    if($("#button_${requestScope.id} b .status").length <= 0){
                       $("#button_${requestScope.id} b").append("<span class=\"status finish\">&nbsp;</span>");
                    }
                }
            });
        }
        //如果视频和图片过长将会缩小宽度
        var articleWidth = $("#rightContent_${requestScope.id}").width();
        $("#rightContent_${requestScope.id} img").each(function() {
            if ($(this).width() > articleWidth) {
                $(this).attr("width", articleWidth);
            }
        });
    });
</script>

