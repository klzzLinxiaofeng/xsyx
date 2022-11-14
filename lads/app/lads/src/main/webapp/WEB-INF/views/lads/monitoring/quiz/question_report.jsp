<%-- 
    Document   : question_report_detail
    Created on : 2012-4-18, 10:10:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--表格开始-->
<div class="floattable floattab0">
    <table>
        <thead>
            <tr>
                <th width="333">题目</th>
                <th width="125">题型</th>
                <th width="356">正确答案</th>
                <th width="125">回答正确</th>
                <th width="125">回答错误</th>
                <th width="95">详细</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#request.questionList" id="qe" status="st">
                <tr>
                    <td align="left">
                        <s:property value="#st.getIndex()+1"/>.  <s:property value='direction.length()>20?(direction.substring(0,20)+"..."):direction'/>
                    </td>
                    <td align="center">
                        ${qe.type}
                    </td>
                    <td align="center">
                        <s:if test='rightAnswer==null||"".equals(rightAnswer)'>
                            -
                        </s:if>
                        <s:else>
                            <s:property value='rightAnswer.length()>20?(rightAnswer.substring(0,20)+"..."):rightAnswer'/>
                        </s:else>
                    </td>
                    <td align="center">
                        ${qe.rightAnswerCount}
                    </td>
                    <td align="center">
                        ${qe.wrongAnswerCount}
                    </td>
                    <td align="center">
                        <a href="javascript:void(0)" onclick="quizDetail('<s:property value="#request.id"/>','${qe.id}')">查看</a>
                    </td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
</div>
<!--表格结束-->