<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<style>
    p{
        display: inline;
    }

    div#leave_list_content ul {
        margin-top: 30px;
    }

    li.asaa {
        border-bottom: 1px solid #d9d9d9;
        margin: 10px;
    }

    .detail {
        margin-left: 20px;
    }

    .detail div {
        margin-bottom: 10px;
    }

    .detail div span {
        color: #666;
        font-weight: bolder;
    }

    .detail div .p2 {
        margin-top: 10px;
        color: #257bda;
        font-weight: bolder;
    }

    .detail div .p2 span {
        font-weight: normal;
        color: #888;
    }

    div.p3 p {
        margin-right: 20px;
    }

    div.p3 span {
        color: #145c98;
    }
</style>
<div id="contan">
    <div class="widget-container">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </div>
    <input type="text" style="display: none" id="viewId" value="${id}"/>
    <button class="btn btn-success right" type="button" id="sqbutton" onclick="daoChu2('${id}');">导出会务</button>
    <button class="btn btn-success right" type="button" id="sqbutton2" onclick="loadCreatePage();">申请会务</button>
</div>


<c:if test="${id==0}">
    <span>与我相关的</span>
</c:if>
<c:if test="${id==1}">
    <span>全部的会议</span>
</c:if>
<c:if test="${id==2}">
    <span>我申请的会议</span>
</c:if>
<ul>
    <c:forEach items="${list}" var="item" varStatus="i">
        <li class="asaa" id="li_${item.id}" style="height: 280px;border-top: 0px solid red;">
            <div class="touxiang" style="float: left;width: 100px;height: 100px">
                <img
                        src="<avatar:avatar userId='116600' ></avatar:avatar>" style="width: 100px;height: 100px"/>
            </div>
            <div class="detail" style="float: left">
                    <div>
                        <span>${item.applicantName}</span>发布的会务
                <div class="p2" style="font-size: 20px">
                        ${item.theme }
                        <c:if test="${item.isAudit==0 }">
                            <span>[待审批]</span>
                        </c:if>
                        <c:if test="${item.isAudit==1 }">
                            <span>[待布置]</span>
                        </c:if>
                        <c:if test="${item.isAudit==2 }">
                            <span>[审批未通过]</span>
                        </c:if>
                        <c:if test="${item.isAudit==3 }">
                            <span>[已完成]</span>
                        </c:if>
                </div>
                    </div>
            <%--    <div class="p5" style="word-wrap:break-word;">${item.detail }</div>--%>
                <c:if test="${item.departmentId!=null }">
                    <div class="p3">
                        <i class="fa  fa-sitemap"></i>
                        <p class="p_div">申请部门</p>
                        <span>${item.departmentName }</span>
                    </div>
                </c:if>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">活动负责人</p>
                    <span>${item.eventManagerName}</span>
                </div>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">会务负责人</p>
                    <span>${item.huiwufuzeName}</span>
                </div>
                <div class="p3">
                    <p class="p_div">活动时间</p>
                    <span>${item.kaishiTime}</span>
                </div>
                <div class="p3">
                    <p class="p_div">活动场地</p>
                    <span>${item.changdiName}</span>
                </div>
            </div>
           <div class="caozuo" style="float: right">
                   <%--登录用户的id=审核人的userid--%>
                  <c:if test="${userInfo.id==item.reviewer }">
                      <c:if test="${item.isAudit==0 }">
                          <button style="text-align:center" class="btn btn-green" type="button" onclick="shenhe('${item.id}');">通过审核</button>
                          <button style="text-align:center" class="btn btn-green" type="button" onclick="bushenhe('${item.id}');">不通过审核</button>
                      </c:if>

                  </c:if>
                           <%--登录用户的id=负责人的userid--%>
                    <c:if test="${userInfo.id==item.huiwufuzeId }">
                        <c:if test="${item.isAudit==1 }">
                                <button style="text-align:center" class="btn btn-green" type="button" onclick="buzhi('${item.id}');">确认布置完成</button>
                        </c:if>
                    </c:if>

                       <c:if test="${userInfo.id==item.applicant }">
                        <c:if test="${item.isAudit==0 }">
                                <button style="text-align:center" class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
                                <button style="text-align:center" class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
                        </c:if>
                    </c:if>
                <button style="text-align:center" class="btn btn-green" type="button" onclick="xiangqing('${item.id}');">详情</button>
            </div>
        </li>

    </c:forEach>
    <c:if test="${list.size()<=0}">
        <div class="empty">
                    <p>暂无会务</p>
        </div>
    </c:if>
</ul>
