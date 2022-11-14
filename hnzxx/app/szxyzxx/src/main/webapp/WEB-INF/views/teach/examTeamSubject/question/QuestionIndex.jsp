<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>题号</th>
                                <th>分值</th>
                            </tr>
                            </thead>
                            <tbody id="examTeamSubject_list_content">

                            </tbody>
                        </table>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            <input type="text" id="ids" style="display:none;" value="${examId}"/>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
 function  bianji(id){
   var score=$("#td_"+id).val();
   if(score!=null && score!="") {
       $.get("/examquestion/updateFenzhi?examId="+id+"&fenzhi="+score, function (d) {
           if (d == "success") {
               seach();
           }else{
               alert(d)
               $.error("失败")
           }
       })
   }
 }
 function seach(){
     $("#examTeamSubject_list_content").html("");
     var id=$("#ids").val();
     $.get("/examquestion/findByAll?examId="+id,function (d) {
        var dd=JSON.parse(d);
        for(var i=0;i<dd.length;i++){
            var str="<tr><td>第"+dd[i].order+"题</td><td><input  id='td_"+dd[i].id+"' value='"+dd[i].fenzhi+"'/></td></tr>";
            $("#examTeamSubject_list_content").append(str);
            $("#td_"+dd[i].id).attr("onchange","bianji('"+dd[i].id+"')");
        }
     })
 }
 $(function () {
     seach();
 })

</script>
</html>