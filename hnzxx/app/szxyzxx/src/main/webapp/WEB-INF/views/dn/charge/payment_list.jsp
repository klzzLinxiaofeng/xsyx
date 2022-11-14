<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">

<script>
    $(function(){
        $(".add_money").keyup(function(event){
            var keycode = event.which;
            if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
                //匹配负号，数字
                this.value = this.value.replace(/[^\d.]/g, "");
                //匹配第一个输入的字符不是小数点
                this.value = this.value.replace(/^\./g, "");
                //只能输入两个小数
                this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
                //保证.-只出现一次，而不能出现两次以上
                this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
                this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
                //保证-号只能是第一位
            }
        });

    })
</script>

<table class="responsive table table-striped table-bordered">
    <thead>
        <tr>
            <th style="width:70px;">姓名</th>
            <%--<th>性别</th>--%>
            <c:forEach items="${itemList}" var="item">
                <th>${item.name}</th>
            </c:forEach>
        </tr>
    </thead>
    <tbody class="amount">
        <c:forEach items="${teamStudentList}" var="teamStudent" varStatus="i">
            <tr>
                <td>${teamStudent.name}</td>
                <%--<td></td>--%>
                <c:forEach items="${itemList}" var="item" varStatus="j">
                    <td>
                        <c:choose>
                            <c:when test="${amounts[i.index][j.index]!=0.0}">
                                <input type="text" class="add_money" maxlength="11" value="${amounts[i.index][j.index]}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="add_money" maxlength="11">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table>


