<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <c:choose>
        	<c:when test="${newGroupStudentList.size() > 0 }">
	        	<ul class="ul1 sortul">
	        		<c:forEach items="${newGroupStudentList }" var="list">
        				<li class="ui-state-default">
		                    <div class="title_area"><span>组<b>${list.groupNumber }</b></span> <i class="fr add02"></i></div>
		                    	<a href="javascript:void(0)" class="add01" style="display:none;">点击右上角按钮添加学生</a>
			                    <div class="ul2_div" style="display:block;">
			                        <ul class="ul2 connectedSortable">
												<c:forEach items="${list.student }" var="s">
													<li class="ui-state-default" studentid="${s.studentId }">${s.studentName }</li>
												</c:forEach>
			                        </ul>
			                    </div>
		                    
		                    <div class="clear"></div>
		                </li>
        			</c:forEach>
        		</ul>
        	</c:when>
        	<c:otherwise>
        		<ul class="ul1 sortul">
        		<c:forEach var="i" begin="1" end="10">
	                <li class="ui-state-default">
	                    <div class="title_area"><span>组<b>${i }</b></span> <i class="fr add02"></i></div>
	                    <a href="javascript:void(0)" class="add01">点击右上角按钮添加学生</a>
	                    <div class="ul2_div">
	                        <ul class="ul2 connectedSortable">
	                        </ul>
	                    </div>
	                    <div class="clear"></div>
	                </li>
        		</c:forEach>
	            </ul>
        	</c:otherwise>
        </c:choose>
	<div class="clear"></div>
	<script>
	 //小ul移动
    $( ".connectedSortable" ).sortable({
        connectWith: ".connectedSortable"
    }).disableSelection();
    //大ul移动
    $( ".sortul" ).sortable({
        cursor: "move",
        items :"li",                        //只是li可以拖动
        update : function(event, ui){       //更新排序之后
            var left_index = $('.gl_div2 li a.on').parent('li').index();
            $('.grouping_right .gr_div2').eq(left_index).find('.sortul>li').each(function(i){
                $(this).find('b').text(i+1)
            })
        }
    });
	</script>