<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<table>
  <tr>
    <td style="padding: 0; border: 0 none;"><input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
      <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
      <input
              type="hidden" id="totalPages" name="totalPages"
              value="${page.totalPages}"/></td>
  </tr>
</table>
<input type="hidden" value="${all}" id="all"/>
<ul>
  <c:forEach items="${items}" var="item" varStatus="i">
    <div style="margin: 40px; display: flex; justify-content: space-between; align-items: center">
      <div>
        <div style="display: flex">

          <div style="width: 50px; height: 50px; margin-right: 10px">
            <img alt="" src="${pageContext.request.contextPath}/res/images/no_pic.jpg"/>
          </div>
          <span style="font-weight: bold; color: #666666;  margin-right: 10px">${item.applicantName}</span>
          <span style="color: #7f7f94">发起的调课</span>
        </div>
        <div style="margin-left: 60px">
          <div>${item.reason}</div>
          <div style="margin: 10px 0 16px 0">
            <span><i class="fa fa-user" style="color: #509fe5; margin-right: 8px"></i>申请人</span>
            <span style="margin-left: 40px; color: #1a5f99">${item.applicantName}</span>
          </div>
          <div>
            <div style="font-size: 14px"><i class="fa fa-clock-o"
                                            style="color: #509fe5; margin-right: 8px"></i>
              任课时间 <fmt:formatDate value="${item.applyDate}" pattern="MM-dd"/> (${item.applyWeek})
              （第${item.applyLessonOne}节）
              <c:if test="${item.applyLessonTwo != null}">
                , <fmt:formatDate value="${item.applyDate}"
                                  pattern="MM-dd"/> (${item.applyWeek}) （第${item.applyLessonTwo}节）
              </c:if>
            </div>
            <div style="margin: 10px 0; font-size: 14px"><i class="fa fa-clock-o"
                                                            style="color: #509fe5; margin-right: 8px"></i>
              申请调到 <fmt:formatDate value="${item.setDate}" pattern="MM-dd"/> (${item.setWeek}) （第${item.setLessonOne}节）
              <c:if test="${item.applyLessonTwo != null}">
                , <fmt:formatDate value="${item.setDate}" pattern="MM-dd"/> (${item.setWeek}) （第${item.setLessonTwo}节）
              </c:if>
            </div>
            <span><i class="fa fa-eye" style="color: #509fe5; margin-right: 8px"></i>审核人 </span>
            <span style="margin-left: 40px; color: #1a5f99">${item.approverName}</span>
            <p><fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></p>
          </div>
        </div>
      </div>
      <c:if test="${item.approveFlag == true}">
        <div>
          <button type="button" class="btn btn-primary btn-lg" onclick="agreeObj('${item.id}')">同意</button>
          <button type="button" class="btn btn-danger" onclick="rejectObj('${item.id}')" id="prompt_button">驳回</button>
        </div>
      </c:if>
      <c:if test="${item.status == 1}">
        <div style="background-color: #aaaaaa; color: #fff; text-align: center; padding: 9px">
          <div style="font-weight: bold; font-size: 32px; margin-bottom: 9px">已同意</div>
          <div style="font-weight: bold; font-size: 18px"><fmt:formatDate value="${item.approveDate}"
                                                                          pattern="yyyy/MM/dd HH:mm"/></div>
        </div>
      </c:if>
      <c:if test="${item.status == 2}">
        <div style="background-color: #aaaaaa; color: #fff; text-align: center; padding: 9px">
          <div style="font-weight: bold; font-size: 32px; margin-bottom: 9px">已驳回</div>
          <div style="font-weight: bold; font-size: 18px"><fmt:formatDate value="${item.approveDate}"
                                                                          pattern="yyyy/MM/dd HH:mm"/></div>
        </div>
      </c:if>
    </div>
  </c:forEach>
  <c:if test="${items.size()<=0}">
    <div class="empty">
      <c:choose>
        <c:when test="${sub=='list' }">
          <p>搜索结果为空</p>
        </c:when>
        <c:otherwise>
          <p>暂无调课</p>
        </c:otherwise>
      </c:choose>
    </div>
  </c:if>
</ul>