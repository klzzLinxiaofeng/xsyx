<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.teacherName}</td>
        <td>${item.fangshiHao}</td>
        <td><fmt:formatDate value="${item.shangBaoTime2}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${item.area}</td>
        <td>${item.hotWater}</td>
        <td>${item.coldWater}</td>
        <td>${item.electricity}</td>
        <td class="caozuo">
                <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>

                <button class="btn btn-red" type="button" onclick="shanchu('${item.id}');" >删除</button>
            <button class="btn btn-green" type="button" onclick="xiangqing('${item.id}');">详情</button>
        </td>
    </tr>
</c:forEach>