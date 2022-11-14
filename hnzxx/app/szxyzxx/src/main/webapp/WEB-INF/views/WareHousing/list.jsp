<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${wareHousingList}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.shenqingName}</td>
        <td>
            <c:if test="${item.type==1}">
                办公用品
            </c:if>
            <c:if test="${item.type==2}">
                书籍
            </c:if>
            <c:if test="${item.type==3}">
                防疫物资
            </c:if>
            <c:if test="${item.type==4}">
                其他
            </c:if>
        </td>
        <td>${item.name}</td>
        <td>${item.createDate}</td>
        <td>${item.shenheName}</td>
        <td>
            <c:if test="${item.zhuangtai==0}">
                待审批
            </c:if>
            <c:if test="${item.zhuangtai==1}">
                <c:if test="${item.isGuihuan==0}">
                    无需归还
                </c:if>
                <c:if test="${item.isGuihuan==1}">
                    待归还
                </c:if>
            </c:if>
            <c:if test="${item.zhuangtai==2}">
                审批驳回
            </c:if>
            <c:if test="${item.zhuangtai==3}">
                归还待审批
            </c:if>
            <c:if test="${item.zhuangtai==4}">
                已归还
            </c:if>
            <c:if test="${item.zhuangtai==5}">
                归还驳回
            </c:if>
        </td>
        <td class="caozuo">
            <c:if test="${userInfo.id==item.shenheId}">
                <c:if test="${item.zhuangtai==0}">
                    <button class="btn btn-green" type="button" onclick="shenpi('${item.id}');">审批</button>
                </c:if>
            </c:if>
            <c:if test="${userInfo.id==item.shenqingren}">
                <c:if test="${item.zhuangtai==5 && item.isGuihuan==1}">
                    <button class="btn btn-green" type="button" onclick="chongxinguihuan('${item.id}');">重新归还</button>
                </c:if>
            </c:if>
            <c:if test="${userInfo.id==item.shenqingren}">
                <c:if test="${item.zhuangtai==1 && item.isGuihuan==1}">
                    <button class="btn btn-green" type="button" onclick="guihuan('${item.id}');">归还</button>
                </c:if>
            </c:if>
            <c:if test="${userInfo.id==item.fuzeren}">
                <c:if test="${item.zhuangtai==3 && item.isGuihuan==1}">
                    <button class="btn btn-green" type="button" onclick="guihuanShenpi('${item.id}');">审批归还</button>
                </c:if>
            </c:if>
            <button class="btn btn-green" type="button" onclick="xiangqing('${item.id}');">详情</button>
            <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
            <button class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
        </td>
    </tr>
</c:forEach>