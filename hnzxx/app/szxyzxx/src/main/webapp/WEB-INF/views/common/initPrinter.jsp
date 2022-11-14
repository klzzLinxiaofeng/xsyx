<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function initPrinter() {
		$(".hide").hide();
	 	$("#qs_printer").printHtml_Preview( {
	 		"taskName" : "签收单",
	     	"htmlBody" : "<link href='${ctp}/res/plugin/falgun/css/table.css' rel='stylesheet'>" + $("#data-div").html()
	     } );
	 }
</script>