<%-- 
    Document   : setting_ex
    Created on : 2014-2-20, 13:45:47
    Author     : Administrator
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String id = request.getParameter("id");
    String pos = request.getParameter("pos");
    String explanationEditorNo = request.getParameter("explanationEditorNo");
    String explanation = request.getParameter("explanation");
%>
<!DOCTYPE html>
<div name="explanation">
    <div id="explanationRealContent_<%=id%>_<%=pos%>"  style="display: none">
        <%=explanation%>
    </div>
    <textarea id="explanationContent_<%=id%>_<%=pos%>" 
              name="explanationEditor_<%=explanationEditorNo%>"
              style="width:99.7%;height:150px;visibility:hidden;"></textarea>
    <script type="text/javascript">
        $(function() {
            var explanationEditor = KindEditor.create('textarea[name="explanationEditor_' + explanationEditorNo + '"]', baseEditorSetting);
            explanationEditors["explanationEditor_" + explanationEditorNo] = explanationEditor;
            explanationEditor.focus();
            explanationEditor.html($("div[id^='explanationRealContent_<%=id%>']").html())
            var focusExplanation = $("div[id^='questionContentDiv_<%=id%>'] div[name='explanation']").find("iframe").contents().find("body")
            focusExplanation.unbind('click').click(function() {
                setFocusEditor("explanationEditor_" + explanationEditorNo, "explanation");
            });
            //先点击一下内容区避免用户选不中内容区
            focusExplanation.click();
        });
    </script>
</div>