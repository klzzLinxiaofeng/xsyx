<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%--<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />--%>
<%--<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />--%>
<table class="table table-striped table1" style="border:1px solid #e4e8eb;">
    <thead><tr><th style="width:90px;">班级</th><th data-code="" data-type="1">班主任</th></tr></thead>
    <tbody>
        <c:forEach items="${teamList}" var="team" varStatus="status">
            <tr data-grade-id="${team.gradeId}" data-team-id="${team.id}">
                <td>${team.name}</td>
                <td>
                    <span  class="tean" title="${classMasterList[status.index].name}">${classMasterList[status.index].name}</span>
                    <c:choose>
                        <c:when test="${empty classMasterList[status.index].id}">
                            <i class="add" onclick="addPage(this);"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="edit" onclick="editPage('${classMasterList[status.index].id}');"></i>
                            <i class="delete" onclick="deletePage('${classMasterList[status.index].id}');"></i>
                        </c:otherwise>
                    </c:choose>
                <%--<c:forEach items="${classMasterList}" var="item">--%>
                    <%--<c:if test="${item.teamId eq team.id}">${item.name}--%>
                        <%--<i class="edit"></i><i class="delete"></i>--%>
                    <%--</c:if>--%>
                <%--</c:forEach>--%>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div class="fd_table">
    <table class="table table-striped" style="max-width:10000%">
        <thead>
        <tr>
            <c:forEach items="${subjectList}" var="item" varStatus="status">
                <th data-code="${item.subjectCode}" data-type="2">${item.subjectName}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${subjectTeacherList}" var="subjectTeacher" varStatus="status">
                <tr>
                    <c:forEach items="${subjectTeacher}" var="item" varStatus="status">
                        <td>
                           <span class="tean" title="${item.name}">${item.name}</span>
                            <c:choose>
                                <c:when test="${empty item.id}">
                                    <i class="add" onclick="addPage(this);"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="edit" onclick="editPage('${item.id}');"></i><i class="delete" onclick="deletePage('${item.id}');"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(function(){
        var w=$(".all_table").width();
        var w1=w-300;
        $(".fd_table").width(w1);
        var i=$(".fd_table table th").length;
        if(w1>130*i){
            $(".fd_table table").width(w1);
        }else{
            $(".fd_table table").width(130*i);
        }
        $(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
        //子
        $(".fd_table table tr td").hover(function(){
            var j=$(this).parent().index();
            $(".table1 tr").removeClass("blue_1");
            $(".table1 tbody tr").eq(j).addClass("blue_1");
            $(this).children('.edit').css("z-index","1");
            $(this).children('.delete').css("z-index","1");
        },function(){
            var j=$(this).parent().index();
            $(".table1 tbody tr").eq(j).removeClass("blue_1");
            $(this).children('.edit').css("z-index","-1");
            $(this).children('.delete').css("z-index","-1");
        });

        //父
        $(".table1 tr td").hover(function(){
            var k=$(this).parent().index();
            $(".fd_table table tr").removeClass("blue_1");
            $(".fd_table table tbody tr").eq(k).addClass("blue_1");
            $(this).children('.edit').css("z-index","1");
            $(this).children('.delete').css("z-index","1");
        },function(){
            var k=$(this).parent().index();
            $(".fd_table table tbody tr").eq(k).removeClass("blue_1");
            $(this).children('.edit').css("z-index","-1");
            $(this).children('.delete').css("z-index","-1");
        });
    });
    $(window).resize(function(){
        var w=$(".all_table").width();
        var w1=w-300;
        $(".fd_table").width(w1);
        var i=$(".fd_table table th").length;
        if(w1>130*i){
            $(".fd_table table").width(w1);
        }else{
            $(".fd_table table").width(130*i);
        }
        $(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    });

</script>
