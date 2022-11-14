<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('.pagination').jqPagination({
			  //link_string : '/?page={page_number}',
			  current_page: 1, //设置当前页 默认为1
			  max_page : '${page.totalPages}', //一共有多少页
			  page_string : '当前第{current_page}页,共{max_page}页,共${page.totalRows}条',
			  paged : function(page) {
				  var myLayer;
				  myLayer = parent != null ? parent.layer : layer;
				  var loadi = myLayer.load('加载中', 0);
				  var url = "${param.url}";
				  var zyDm = $("#current_opera_zyDm", window.top.document).val();
				  if(url.indexOf("?") != -1) {
					  if(url.indexOf("&dm=") == -1 && url.indexOf("?dm=") == -1) {
				          url = url + "&dm=" + zyDm;
					  }
				  } else {
					  url = url + "?dm=" + zyDm; 
				  }
				  var requestData = {};

				  if(typeof getPagingParam !="undefined"){
					  requestData=getPagingParam();
				  }

				  requestData.currentPage = page;
				  requestData.pageSize = "${param.pageSize == null ? 0 : param.pageSize}";

			  	  $("#${param.id}").load("${ctp}"+url, requestData, function() {
			  		myLayer.close(loadi);
			  		checkItems();
                                                                  //添加回调方法
                                                                  <c:if test='${param.callback!=null && "" ne param.callback}'>
                                                                          ${param.callback};
                                                                  </c:if>
			  	  });
			   }
		});
	});
</script>
<div class="pagination">
	<a href="#" class="first" data-action="first">&laquo;</a> <a href="#"
		class="previous" data-action="previous">&lsaquo;</a> <input
		type="text" style="width: 150px" readonly="readonly" data-max-page="60" /> <a href="#"
		class="next" data-action="next">&rsaquo;</a> <a href="#" class="last"
		data-action="last">&raquo;</a>
</div>