<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lads" uri="/WEB-INF/lads" %>
<!DOCTYPE html>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm"><b>活动状态</b></li>
    <li class="rr"></li>
</ul>
<!-- discuss start -->
<div style="float: right;padding: 7px">
    <a href="javascript:void(0)" onclick="loadDiscussReply('<s:property value="#request.id"/>')" class="intelligent">
        <img src="/image/common/lads/lads_refresh_btn.jpg"/>
    </a>
</div>
<h1 style="padding:10px 0;color:#1e3a9e;border-bottom:1px solid #c4d6ec"><s:property escape="false" value="#request.title"/></h1>
<div id="discussReplyDiv_<s:property value="#request.id"/>" >
    <!--    <div class="more">
            <div class="portrait"><img src="#"/></div>
            <div class="name">王老师<span>2012-08-08 11:35:36 发表</span></div>
            <div class="box">
                <div class="box">
                    <div class="box">
                        <div class="box">放课程导入我课程导入我对开放课程导入我对开放课程导</div>
                        放课程导入我对开放课程导放课放课程导入我对开放课程导</div>
                    放课程导导入课程导入我对开放课程导</div>
                放课程导入我放课程导</div>
            <div class="word">
                <div class="word">
                    <div class="word">
                        <div class="word">放课程导入我对开放课程导入我对开放课程导入我对开放课程导</div>
                        放课程导入课程导入我对开放课程导</div>
                    放课程导入我对开放课程程导入我对开放课程导</div>
                放课程导入我对开放课程导入我对开放课程导入我对开放课程导</div>
            <div class="reply_btn"><a onclick="" href="javascript:void(0)">回复</a></div>
            <div class="postBody">
                <form id="quoteForm" action="" method="post">
                    <textarea name="body" rows="8" cols="80"></textarea>
                </form>
                <a href="javascript:void(0)" class="submit">马上发表</a>
                <br style="clear:both"/>
            </div>
        </div>-->
    <!--<div class="more">
        <div class="portrait"><img src="#"/></div>
        <div class="name">王老师<span>2012-08-08 11:35:36 发表</span></div>
        <div class="word">放课程导入我对开放课程导入我对开放课程导入我对开放课程导</div>
        <div class="reply_btn"><a href="javascript:void(0)">回复</a></div>
        <div class="postBody" style="display:none">
            <form id="quoteForm" action="" method="post">
                <textarea name="body" rows="8" cols="80"></textarea>
            </form>
            <a href="javascript:void(0)" class="submit">马上发表</a>
            <br style="clear:both"/>
        </div>
    </div>-->
    <s:iterator value="#request.replyList" id="vo">
        <div class="more">
            <div class="portrait"><img src="<s:property value="image"/>"/></div>
            <div class="name"><s:property value="userName"/>
                <span><s:date name="reply.createTime" format="MM.dd hh:mm"/> 发表</span>
            </div>
            <div class="word"><s:property escape="false" value="reply.content"/></div>
        </div>
    </s:iterator>
</div>
<table class="reply">
    <tr>
        <td align="right" width="60" valign="top">回　复：</td>
        <td>
            <textarea id="discussReply_<s:property value="#request.id"/>" name="discussReply_<s:property value="#request.id"/>" rows="11"></textarea>
            <script type="text/javascript">
                //CKEDITOR.config.pasteuploadpic_type='';
                var mainEditor = CKEDITOR.instances['discussReply_<s:property value="#request.id"/>'];
                if(mainEditor!=null){
                    mainEditor.destroy(true);
                }
                mainEditor = CKEDITOR.replace( 'discussReply_<s:property value="#request.id"/>');
                CKEDITOR.config.height="200";
                CKEDITOR.config.resize_enabled = false;
                CKFinder.setupCKEditor( mainEditor, '/js/common/ckfinder/' ) ;
            </script>
        </td>
    </tr>
    <tr>
        <td colspan="2" height="35">
            <a onclick="reply(this)" href="javascript:void(0)" class="submit">马上发表</a>
        </td>
    </tr>
</table>
<br style="clear:both"/>
<script type="text/javascript">
    function createReply(content,obj,contentId){
        var now = new Date();
        now = changeTimeToString(now);
        var html = "<div class=\"more\">"
            +"<div class=\"portrait\"><img src=\"/image/login/noPhoto.jpg\"/></div>"
            +"<div class=\"name\">我<span>"+now+" 发表</span></div>"
            +"<div class=\"word\">"+content+"</div>"
            +"</div>";
        $(obj).parent().parent().parent().parent().prev().prepend(html);
        CKEDITOR.instances[contentId].setData("");
    }

    function loadDiscussReply(id){
        $("#discussReplyDiv_"+id).html('<img src="/image/teacher/loading.gif" title="加载中" alt="加载中"/>');
        var url = "/common/lads/ladsDiscussAction_getLearningReplyList.action?toolId="+id+"&sysType="+$("#sysType").val();
        $("#discussReplyDiv_"+id).load(url);
    }
    
    function reply(obj){
        var contentId = $(obj).parent().parent().parent().parent().find("textarea").attr("id");
        var content = CKEDITOR.instances[contentId].getData();
        var toolId = contentId.substring(contentId.indexOf("_")+1);
        if(content==null||content==""){
            alert("请输入回复内容");
            return;
        }
        //预览模式不需要把回复内容存进数据库
        if(!previewMode){
            $.ajax({
                url:"/common/lads/ladsDiscussAction_reply.action",
                type: "POST",
                data:{"toolId":toolId,"reply":content,"userId":"<lads:getEmbedUser sysType="${requestScope.sysType}"/>"},
                async: true
            });
        }
        createReply(content,obj,contentId);
    }
</script>
<!-- discuss end -->
