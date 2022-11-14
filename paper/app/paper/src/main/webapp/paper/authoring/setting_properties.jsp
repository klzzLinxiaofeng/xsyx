<%-- 
    Document   : setting_properties
    Created on : 2014-2-20, 13:45:05
    Author     : Administrator
--%>

<%@page import="platform.education.paper.constants.Difficulity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%
    String id = request.getParameter("id");
    String difficulityStr = request.getParameter("difficulity");
    String subjectCode = request.getParameter("subjectCode");
    String gradeCode = request.getParameter("gradeCode");
    String volumeCode = request.getParameter("volumeCode");
    String publishCode = request.getParameter("publishCode");
    String unitCode = request.getParameter("unitCode");
    String sectionCode = request.getParameter("sectionCode");
    String bookId = request.getParameter("bookId");
    String bookUnitId = request.getParameter("bookUnitId");
    String bookSectionId = request.getParameter("bookSectionId");
    String knowledgeId = request.getParameter("knowledgeId");
    int difficulity = -00;
    if (difficulityStr != null && !"".equals(difficulityStr) && !"null".equals(difficulityStr)) {
        difficulity = Integer.parseInt(difficulityStr);
    }
%>
<!DOCTYPE html>
<div name="properties">
    <table>
        <tbody>
            <tr>
                <td>教材目录：</td>
                <td class="catalog">
                    <%--                    <select onclick="simOptionClick4IE()" id="subjectCode_<%=id%>">
                                        </select>
                                        <select onclick="simOptionClick4IE()" id="publishCode_<%=id%>">
                                        </select>
                                        <select onclick="simOptionClick4IE()" id="gradeCode_<%=id%>">
                                        </select>
                                        <select onclick="simOptionClick4IE()" id="volumeCode_<%=id%>">
                                        </select>
                                        <select onclick="simOptionClick4IE()" class="title" id="unitCode_<%=id%>" >
                                        </select>
                                        <select class="title" id="sectionCode_<%=id%>" >
                                        </select>--%>

                 <%--   <s:include value="/module/common/resources/video/select_1.jsp">
                        <s:param name="temp" value="#parameters.id[0]"/>
                    </s:include>--%>

                </td>
            </tr>
            <tr>
                <td>知&nbsp;识&nbsp;点</td>
                <td class="knowledge">
                 <%--   <s:include value="/module/common/resources/video/knowledge_select2.jsp">
                        <s:param name="temp" value="#parameters.id[0]"/>
                    </s:include>--%>
                </td>
            </tr>
            <tr>
                <td>难    &nbsp;&nbsp;          度：</td>
                <td>
                    <select id="difficulity_<%=id%>">
                        <option <%=(difficulity == Difficulity.NONE ? "selected" : "")%>  value="<%=Difficulity.NONE%>" >无</option>
                        <option <%=(difficulity == Difficulity.EASY ? "selected" : "")%>  value="<%=Difficulity.EASY%>">容易</option>
                        <option <%=(difficulity == Difficulity.MIDDLE ? "selected" : "")%>  value="<%=Difficulity.MIDDLE%>">中等</option>
                        <option <%=(difficulity == Difficulity.HARD ? "selected" : "")%>  value="<%=Difficulity.HARD%>" >困难</option>
                    </select>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
</script>
