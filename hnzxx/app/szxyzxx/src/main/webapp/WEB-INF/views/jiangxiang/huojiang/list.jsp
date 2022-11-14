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
    <input id="iddss" value="${id}" style="display:none;"/>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </div>
    <c:if test="${flag}">
    <button class="btn btn-success right" type="button" id="sqbutton" onclick="loadCreatePage(2)">申请教师竞赛</button>
    <button class="btn btn-success right" type="button" id="sqbutton2" onclick="loadCreatePage(1);">申请获奖</button>
    </c:if>
</div>


<c:if test="${id==2}">
    <span>我的获奖</span>
</c:if>
<c:if test="${id==0}">
    <span>待审批</span>
</c:if>
<c:if test="${id==1}">
    <span>已审批</span>
</c:if>
<ul>
    <c:forEach items="${list}" var="item" varStatus="i">
        <li class="asaa" id="li_${item.id}" style="height: 280px;border-top: 0px solid red;">
            <div class="touxiang" style="float: left;width: 100px;height: 100px">
                <img
                        src="<avatar:avatar userId='${item.shenqingren}' ></avatar:avatar>" style="width: 100px;height: 100px"/>
            </div>
            <div class="detail" style="float: left">
                <div>
                    <span>${item.shenqingName}</span>发布的申请
                    <div class="p2" style="font-size: 20px">
                            ${item.theme }
                        <c:if test="${item.isAuit==0 }">
                            <span>[待审批]</span>
                        </c:if>
                        <c:if test="${item.isAuit==1 }">
                            <span>[同意]</span>
                        </c:if>
                        <c:if test="${item.isAuit==2 }">
                            <span>[驳回]</span>
                        </c:if>
                    </div>
                </div>
                <div class="p5" style="word-wrap:break-word;">${item.type}</div>
                    <div class="p3">
                        <i class="fa  fa-sitemap">获奖教师</i>
                        <p class="p_div"></p>
                        <span>${item.teacherNames}</span>
                    </div>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">获奖时间</p>
                    <span>${item.winningTime}</span>
                </div>
                <div class="p3">
                    <i class="fa fa-clock-o"></i>
                    <p class="p_div">发奖单位</p>
                    <span>${item.allocated}</span>
                </div>
                <div class="p3">
                    <p class="p_div">获奖级别</p>
                    <span>${item.winningLevelName}</span>
                </div>
                <div class="p3">
                    <p class="p_div">获奖等次</p>
                    <span>${item.dengciName}</span>
                </div>
            </div>
            <div class="caozuo" style="float: right">
                    <%--登录用户的id=审核人的userid--%>
                    <c:if test="${item.isAuit==0}">
                        <button style="text-align:center" class="btn btn-green" type="button" onclick="tongyi('${item.id}');">同意</button>
                        <button style="text-align:center" class="btn btn-green" type="button" onclick="bohui('${item.id}');">驳回</button>
                    </c:if>


               <%-- <c:if test="${userInfo.id==item.shenqingName }">--%>
                        <button style="text-align:center" class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
            <%--    </c:if>--%>
                <button style="text-align:center" class="btn btn-green" type="button" onclick="xiangqing('${item.id}');">详情</button>
            </div>
        </li>

    </c:forEach>
    <c:if test="${list.size()<=0}">
        <div class="empty">
            <p>暂无记录</p>
        </div>
    </c:if>
</ul>

<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
    <jsp:param name="id" value="applyrepair_page" />

    <jsp:param name="url" value="/huojiang/findByAll?sub=list&type=${id}" />
    <jsp:param name="pageSize" value="3" />
</jsp:include>
