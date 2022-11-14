<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${homeList}" var="item" varStatus="i">
    <tr  id="${item.id}_tr">
        <td style="text-align:center;">${i.index+1+(page.currentPage - 1) * 10}</td>
        <td style="text-align:center;">${item.teamName}</td>
        <td style="text-align:center;">${item.subjectName}</td>
        <td style="text-align:left;">${item.text}</td>
        <c:if test="${item.picturUrl!=null}">
            <td>
                <img src="${item.picturUrl}" style="width: 180px;height: 200px"></td>
        </c:if>
        <c:if test="${item.picturUrl==null}">
            <td>暂无图片</td>
        </c:if>

        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td class="caozuo">
            <c:if test="${item.isStats==0}">
                <button class="btn btn-green" type="button" onclick="shouzuoye('${item.id}');">收作业</button>
            </c:if>
            <button class="btn btn-green" type="button" onclick="chakan('${item.id}');">详情</button>
            <c:if test="${item.isStats==0}">
            <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
            </c:if>
            <button class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
        </td>
    </tr>
</c:forEach>