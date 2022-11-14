<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String eqId = request.getParameter("eqId");
    String fileName = "";
    String codeUrl = "";
    if (eqId == null || "".equals(eqId)) {
        eqId = "";
    } else {
        codeUrl = request.getParameter("codeUrl");
        fileName = codeUrl.substring(codeUrl.lastIndexOf("/") + 1, codeUrl.lastIndexOf("."));
    }
%>
<!DOCTYPE html>
<style type="text/css">
    .totaltable {border-left:1px solid #a9ccde;border-right:1px solid #a9ccde;}
    .totaltable table {margin:0;color:#1a5189; line-height:150%;width:100%;}
    .totaltable table tr,
    .totaltable table th,
    .totaltable table td {border-bottom:1px solid #a9ccde;padding:5px 0 5px 0;}
    .totaltable table caption {text-align:left;border-top:1px solid #a9ccde;border-bottom:1px solid #a9ccde;font-weight:bold;background-position:0 -270px;background:url(/css/teacher/skin/images/bg_res_nav.gif) repeat-x 0 -79px; line-height:28px;padding-left:10px;font-size:14px;}
    .totaltable table th {background:#f4f4f4;color:#999;}
    .totaltable table tr,
    .totaltable table th {border-bottom:0px;}
    .tablediv1 table tr,
    .tablediv1 table td,
    .tablediv1 table th {border:0;color:#666;}
    .tablediv1 {border:0;}
    .tablediv1 table td.tdbold {font-size:14px;font-weight:bold;color:#000;}
    .tablediv1 table td {padding-left:5px; padding-right:5px;}
    .addjoon-save{border-top:1px solid #dddffb;padding:30px 0px 30px 0px;text-align:center;}
    .bgother {background:url(/css/teacher/skin/images/bg_other.gif) repeat-x 0 0;}
    .btnlink {display:inline-block;padding:4px 10px 4px 10px;background-position:0 -236px;border:1px solid #cf7200;color:#333;font-size:12px; font-weight:bold;margin-left:5px;height:20px}
</style>
<script type="text/javascript">
    function triggerOK(){
        document.getElementById('mathInputSwf').sendData("/module/common/lads/authoring/quiz/equation/imageCapture.jsp?uploadPath="+$("#uploadPath_"+chooseQuiz).val()+"&fileName=<%=fileName%>");
        return false;
    }
    function imageReceived(source){
        var swf = getChooseQuizSwf();
        source = source.replace(/\s/gm,"");
        var json = eval("("+source+")");
        $("#absPath_"+chooseQuiz).val(json.absPath);
        saveCode(json.urlPath);
        if(""=="<%=eqId%>"){
            swf.FileSelected(json.urlPath);
        }else{
            var name = json.urlPath.substring(json.urlPath.lastIndexOf("/")+1);
            swf.EquationEdited("<%=eqId%>",name);
            closeOverlay();
        }
    }
    function saveCode(source){
        source = source.substring(source.lastIndexOf("/")+1,source.lastIndexOf("."));
        var code = document.getElementById('mathInputSwf').getCode();
        var url = "/module/common/lads/authoring/quiz/equation/imageCapture.jsp";
        var path = null;
        $.ajax({
            url:url,
            type: "POST",
            data:{"code":code,"fileName":source,"uploadPath":$("#uploadPath_"+chooseQuiz).val()},
            cache: false,
            async: false,
            success:function(html){
                path = html;
            }
        });
    }
    function ajaxGetStartCode(txtUrl){
        if(txtUrl==null||txtUrl==""){
            return ;
        }
        var url =  "/module/common/lads/authoring/quiz/equation/codeCapture.jsp";
        var code = null;
        $.ajax({
            url:url,
            type: "POST",
            data:{"filePath":txtUrl},
            cache: false,
            async: false,
            success:function(html){
                code = html;
            }
        });
        code = code.replace(/\s/gm,"");
        return code;
    }
</script>
<div class="addjoon-open1 totaltable tablediv1" style="background-color: white;width: 1050px;padding: 10px;border: 5px solid #999;">
    <table id="contentTable">
        <tbody>
            <tr>
                <td align="center" valign="top" class="tdbold">
                    <div id="mathInputSwf"></div>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="addjoon-save">
        <a onclick="triggerOK()" class="bgother btnlink">确定</a>
        <a onclick="closeOverlay()" class="bgother btnlink">取消</a>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        var code = ajaxGetStartCode("<%=codeUrl%>");
        var flashvars;
        if(code!=null){
            flashvars = {
                code: code
            }
        }else{
            flashvars = {
            }
        }
        var params = {};
        params.allowfullscreen = "true";
        params.allowScriptAccess = "always";
        swfobject.embedSWF("/js/common/ckeditor/plugins/fmath_fangjin/MathInput.swf", "mathInputSwf", "1000", "400", "10.0.0", "expressInstall.swf", flashvars,params);
    });
</script>
