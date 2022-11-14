<%-- 
    Document   : question_report_detail
    Created on : 2012-4-18, 10:10:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="quizQuesionDetailDiv_${id}" class="quizShowDiv">
    <a class="close" href="javascript:void(0)" onclick="overlayObj.close();" ></a>
    ${detail}
    <%--   <div class="frame1">
           <div class="word">
               <b>我是中国人</b>
               aaaaa
           </div>
       </div>
       <div class="frame2">
           <div class="word">
               <table><tr><td width="30"></td><td>正确</td></tr></table>
           </div>
           <hr />
           <div class="word">
               <a href="javascript:void(0)">何帆</a>
           </div>
       </div>
       <div class="frame2 frameBg">
           <div class="word">
               <table><tr><td width="30"></td><td>错误</td></tr></table>
           </div>
           <hr />
           <div class="word"><a href="javascript:void(0)">何帆</a></div>
           <hr />
           <div class="word"><a href="javascript:void(0)">何帆</a></div>
       </div>--%>
    <script type="text/javascript">
        $(function(){
            $("#quizQuesionDetailDiv_${id} .frame2 a").each(function(){
                var href = $(this).attr("href");
                $(this).attr("href", href+"&sysType="+$("#sysType").val());
            });
        })
    </script>
</div>