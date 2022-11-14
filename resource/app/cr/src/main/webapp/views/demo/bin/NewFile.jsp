<!DOCTYPE html>
<html>
<%@ include file="/views/embedded/common.jsp"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="divframe"> 
<div class="loadTitle"> 
<input type="button" value="获取数据" id="btn"/> 
</div> 
<div id="jsonTip"> 
</div> 
</div> 
<script type="text/javascript">
$.getJSON("./json.json", function (data){
	alert("ss");
    var $jsontip = $("#jsonTip");
    var strHtml = "123";
    //存储数据的变量 
    $jsontip.empty();
    //清空内容 
    $.each(data, function (infoIndex, info){
      strHtml += "姓名：" + info["name"] + "<br>";
      strHtml += "性别：" + info["sex"] + "<br>";
      strHtml += "邮箱：" + info["email"] + "<br>";
      strHtml += "<hr>" 
    }) 
    $jsontip.html(strHtml);
    //显示处理后的数据 
  }) 
</script>
</body>
</html>