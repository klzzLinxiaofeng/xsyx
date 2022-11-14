<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<input id="inSchoolCars" value="${inSchoolCars}" type="hidden">
<input id="parentInSchoolStuNames" value="${parentInSchoolStuNames}" type="hidden">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:choose>
    <c:when test="${direction eq 0}">
        <div class="divs">
            <span class="sps">家长接送</span>
            <div style="display: flex;align-items: center;border: 1px solid #cdcdcd">
                <div style="width: 250px; display: flex; justify-content: center;">
                    <img src="${ctp}/res/images/parent.png" style="width: 90px;height: 70px;margin: 23px;"/>
                </div>
                <ul class="ulss" style="display: flex;flex-wrap: wrap;">
                    <c:forEach items="${parentJsList}" var="stu">

                        <li style="width: 80px;" >


                            <c:choose>
                            <c:when test="${stu.headPicture==null}">
                                <img src="/res/images/no_pic.jpg" style="position: relative"
                            </c:when>
                            <c:otherwise>
                            <img src="${imgPrefix}${stu.headPicture}" style="position: relative"
                            </c:otherwise>
                            </c:choose>


                            <c:choose>
                            <c:when test="${stu.stuStatus=='已进校'}">
                                 class="userhead yjx" style="position: relative" class="userhead yjx" onclick="showPick('${stu.name}','${stu.gatePickInfo.eventTime}','${stu.gatePickInfo.doorName}','进校打卡')" >
                            </c:when>
                            <c:otherwise>
                                class="userhead wjx">
                            </c:otherwise>
                            </c:choose>


                            <c:if test="${not empty stu.leaveStartDate}">
                                <img src="/res/images/qj.png" class="qj"
                                     onclick="showLeave('${stu.name}',' <fmt:formatDate value="${stu.leaveStartDate}" pattern='yyyy-MM-dd HH:mm'/>','<fmt:formatDate value="${stu.leaveEndDate}" pattern='yyyy-MM-dd HH:mm'/>')">
                            </c:if>
                            <div><span style="text-align: center">${stu.name}</span></div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:when>
    <c:otherwise>

        <div class="divs">
            <span class="sps">校门口接送</span>
            <div style="display: flex;align-items: center;border: 1px solid #cdcdcd">
                <div style="width: 250px; display: flex; justify-content: center;">
                    <img src="${ctp}/res/images/jzjs.png" style="width: 90px;height: 70px;margin: 23px;"/>
                </div>
                <ul class="ulss" style="display: flex;flex-wrap: wrap;">
                    <c:forEach items="${grateJsList}" var="stu">

                        <li style="width: 80px;" >


                            <c:choose>
                            <c:when test="${stu.headPicture==null}">
                                <img src="/res/images/no_pic.jpg" style="position: relative"
                            </c:when>
                            <c:otherwise>
                            <img src="${imgPrefix}${stu.headPicture}" style="position: relative"
                            </c:otherwise>
                            </c:choose>

                            <c:choose>
                            <c:when test="${stu.stuStatus=='已出校'}">
                                <c:choose>
                                    <c:when test="${fn:endsWith(stu.gatePickInfo.doorName, '(手动设置出校)')}">
                                         class="userhead ycxwj"
                                    </c:when>
                                    <c:otherwise>
                                        class="userhead ycx"
                                    </c:otherwise>
                                </c:choose>
                                 onclick="showPick('${stu.name}','${stu.gatePickInfo.eventTime}','${stu.gatePickInfo.doorName}','出校打卡')" >
                            </c:when>
                            <c:otherwise>
                                class="userhead wcx">
                            </c:otherwise>
                            </c:choose>

                            <c:if test="${not empty stu.leaveStartDate}">
                                <img src="/res/images/qj.png" class="qj" onclick="showLeave('${stu.name}',' <fmt:formatDate value="${stu.leaveStartDate}" pattern='yyyy-MM-dd HH:mm'/>','<fmt:formatDate value="${stu.leaveEndDate}" pattern='yyyy-MM-dd HH:mm'/>')">
                            </c:if>
                            <div><span style="text-align: center">${stu.name}</span></div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="divs">
        <span class="sps">停车场门口接送</span>
        <div style="display: flex;align-items: center;border: 1px solid #cdcdcd">
        <div style="width: 250px; display: flex; justify-content: center;">
            <img src="${ctp}/res/images/car.png" style="width: 90px;height: 70px;margin: 23px;"/>
        </div>
        <ul class="ulss" style="display: flex;flex-wrap: wrap;">
        <c:forEach items="${parkJsList}" var="stu">

            <li style="width: 150px;" >

            <div style="position: relative">
            <c:choose>
                <c:when test="${stu.headPicture==null}">
                    <img src="/res/images/no_pic.jpg"
                </c:when>
                <c:otherwise>
                    <img src="${imgPrefix}${stu.headPicture}"
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${stu.stuStatus=='已出校'}">
                    <c:choose>
                        <c:when test="${fn:endsWith(stu.gatePickInfo.doorName, '(手动设置出校)')}">
                            class="userhead ycxwj"
                        </c:when>
                        <c:otherwise>
                            class="userhead ycx"
                        </c:otherwise>
                    </c:choose>
                    onclick="showParentPick('${stu.name}','${stu.gatePickInfo.eventTime}','${stu.gatePickInfo.doorName}','出校打卡','${stu.parentInOutCarNo}','${stu.parentInTime}','${stu.parentOutTime}')">
                </c:when>
                <c:otherwise>
                    class="userhead wcx">
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty stu.leaveStartDate}">
                    <img src="/res/images/qj.png" class="qj" onclick="showLeave('${stu.name}',' <fmt:formatDate value="${stu.leaveStartDate}" pattern='yyyy-MM-dd HH:mm'/>',' <fmt:formatDate value="${stu.leaveEndDate}" pattern='yyyy-MM-dd HH:mm'/>')">
                    <div class="parentCarStatusAndHasQj
                </c:when>
                <c:otherwise>
                    <div class="parentCarStatus
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty stu.parentOutTime}">
                    xcycx" onclick="showParentOut('${stu.name}','${stu.parentInOutCarNo}','${stu.parentInTime}','${stu.parentOutTime}')"><span>家长已出校</span></div>
                </c:when>
                <c:when test="${not empty stu.parentInTime}">
                    xcyjx" onclick="showParentIn('${stu.name}','${stu.parentInOutCarNo}','${stu.parentInTime}')"><span>家长已进校</span></div>
                </c:when>
                <c:otherwise>
                    xcwjx"><span>家长未进校</span></div>
                </c:otherwise>
            </c:choose>
            </div>

            <div><span style="text-align: center">${stu.name}</span></div>
            </li>
        </c:forEach>

        </ul>
        </div>
        </div>
    </c:otherwise>
</c:choose>

<c:forEach items="${carList}" var="car">
    <div class="divs">
        <span class="sps">${car.schoolBusCard}</span>
        <div style="display: flex;align-items: center;border: 1px solid #cdcdcd">
            <div style="width: 250px; display: flex; justify-content: center;  position: relative;">
                <img src="${ctp}/res/images/schoolBus.png"
                     onclick="showCarInfo('${car.routeNum}','${car.schoolBusCard}','${car.schoolBusNumber}','${car.driverName}','${car.caretakerName}','${car.driverPhone}','${car.caretakerPhone}','${car.companyName}')"
                     style="width: 90px;height: 70px;margin: 23px;"/>
                <c:choose>
                    <c:when test="${car.status=='已进校'}">
                        <div class="carStatus xcyjx" onclick="showBusIn('${car.schoolBusCard}','${car.inTime}')"><span>已进校</span></div>
                    </c:when>
                    <c:when test="${car.status=='已出校'}">
                        <div class="carStatus xcycx" onclick="showBusOut('${car.schoolBusCard}','${car.inTime}','${car.outTime}')"><span>已出校</span></div>
                    </c:when>
                    <c:otherwise>
                        <div class="carStatus xcwjx"><span>未进校</span></div>
                    </c:otherwise>
                </c:choose>

            </div>
            <ul class="ulss" style="display: flex;flex-wrap: wrap;">
                <c:forEach items="${car.stuList}" var="stu">
                    <c:choose>
                        <c:when test="${not empty stu.leaveStartDate}">
                            <li style="width: 80px;">
                        </c:when>
                        <c:otherwise>
                            <li style="width: 80px;">
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${stu.headPicture==null}">
                            <img src="/res/images/no_pic.jpg" style="position: relative"
                        </c:when>
                        <c:otherwise>
                            <img src="${imgPrefix}${stu.headPicture}" style="position: relative"
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${stu.stuStatus=='已上车'}">
                            class="userhead ysc" onclick="showSc('${stu.name}','${stu.upTime}','${stu.upPlace}')" >
                        </c:when>
                        <c:when test="${stu.stuStatus=='已下车'}">
                            class="userhead yxc" onclick="showXc('${stu.name}','${stu.upTime}','${stu.upPlace}','${stu.downTime}','${stu.downPlace}')" >
                        </c:when>
                        <%--                        未上车，如果是上学，校车已进校标红，如果是放学，校车已出校标红--%>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${ (direction==0 && not empty car.status && car.status!='未进校') || (direction==1 && car.status=='已出校')}">
                                    class="userhead redStatus">
                                </c:when>
                                <c:otherwise>
                                    class="userhead wsc">
                                </c:otherwise>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty stu.leaveStartDate}">
                        <img src="/res/images/qj.png" class="qj"
                             onclick="showLeave('${stu.name}',' <fmt:formatDate value="${stu.leaveStartDate}" pattern='yyyy-MM-dd HH:mm'/>','<fmt:formatDate value="${stu.leaveEndDate}" pattern='yyyy-MM-dd HH:mm'/>')">
                    </c:if>
                    <div><span style="text-align: center">${stu.name}</span></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:forEach>
<c:if test="${direction eq 1}">
    <div class="divs">
        <span class="sps">未分配</span>
        <div style="display: flex;align-items: center;border: 1px solid #cdcdcd">
            <div style="width: 250px; display: flex; justify-content: center;">
                <img src="${ctp}/res/images/sad.png" style="width: 90px;height: 70px;margin: 23px;"/>
            </div>
            <ul class="ulss" style="display: flex;flex-wrap: wrap;">
                <c:forEach items="${noUsedList}" var="stu">
                    <li style="width: 80px;" >

                    <c:choose>
                        <c:when test="${stu.headPicture==null}">
                            <img src="/res/images/no_pic.jpg" style="position: relative" class="userhead">
                        </c:when>
                        <c:otherwise>
                            <img src="${imgPrefix}${stu.headPicture}" style="position: relative" class="userhead">
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${not empty stu.leaveStartDate}">
                        <img src="/res/images/qj.png" class="qj" onclick="showLeave('${stu.name}',' <fmt:formatDate value="${stu.leaveStartDate}" pattern='yyyy-MM-dd HH:mm'/>','<fmt:formatDate value="${stu.leaveEndDate}" pattern='yyyy-MM-dd HH:mm'/>')">
                    </c:if>
                    <div><span style="text-align: center">${stu.name}</span></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>