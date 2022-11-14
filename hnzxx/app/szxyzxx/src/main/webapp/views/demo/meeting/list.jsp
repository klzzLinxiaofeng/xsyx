<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr style="display:none"><td>
<input type="hidden" id="currentPage" name="currentPage" value="1" />
<input type="hidden" id="totalPages" name="totalPages" value="1" />
</td>
</tr>
	<tr>
		<td>讨论课</td>
		<td>
			2015-08-27 12:00
		</td>
		<td>
			2015-08-27 13:00
		</td>	
		<td class="caozuo">
				<button class="btn btn-info update_permission" type="button" onclick="openWin()">进入</button>
				<button class="btn btn-blue update_permission" type="button" onclick="loadEditPage(this);">编辑</button>
				<button class="btn btn-warning update_permission" type="button" onclick="deleteObj(this, 1);">删除</button>
		</td>
	</tr>
<script type="text/javascript">

	function openWin() {
		window.open("${pageContext.request.contextPath}/views/demo/meeting/open.jsp");
	}
	
</script>