(function($){  
    //插件  
    $.extend($,{  
        //命名空间  
        sortTable:{  
            sort:function(tableId,Idx){  
                var table = document.getElementById(tableId);  
                var tbody = table.tBodies[0];  
                var tr = tbody.rows;   
          
                var trValue = new Array();  
                for (var i=0; i<tr.length; i++ ) {  
                    trValue[i] = tr[i];  //将表格中各行的信息存储在新建的数组中  
                }  
                
                var arrows = $("#span_"+Idx).text();
                $(".up").text("");
                
                trValue.sort(function(tr1, tr2){  
                    var value1 = tr1.cells[Idx].innerText;  
                    var value2 = tr2.cells[Idx].innerText;
                    value1 = value1.replace("分","");
                    value2 = value2.replace("分","");
                   
                    if(value1 == ""){
                    	value1 = 0;
                    }
                    if(value2 == ""){
                    	value2 = 0;
                    }
                    if(value1 < 0){
                    	value1 = 0-value1;
                    }
                    if(value2 < 0){
                    	value2 = 0-value2;
                    }
                    value1 = value1.toString();
                    value2 = value2.toString();
                    if(arrows == "" || arrows == "▲"){
                    	$("#span_"+Idx).text("▼");
                    	return -(value1.localeCompare(value2)); 
                    }else if(arrows == "▼"){
                    	$("#span_"+Idx).text("▲");
                    	return value1.localeCompare(value2); 
                    }
//                    return value1.localeCompare(value2);  
                });  
                
          
                var fragment = document.createDocumentFragment();  //新建一个代码片段，用于保存排序后的结果  
                for (var i=0; i<trValue.length; i++ ) {  
                    fragment.appendChild(trValue[i]);  
                }  
          
                tbody.appendChild(fragment); //将排序的结果替换掉之前的值  
                tbody.sortCol = Idx;
                
                //排序左侧表格
                var obj = $("#"+tableId).find("tr");
                var Html = "";
                $.each(obj,function(i, v){
                	var data_id = $(this).attr("data-id");
                	var ht = $("#"+data_id).html();
                	Html = Html + "<tr id='" + data_id + "'>"+ht+"</tr>";
                });
                $(".table_3").html("").append(Html);
                
            }  
        }  
    });         
})(jQuery);  