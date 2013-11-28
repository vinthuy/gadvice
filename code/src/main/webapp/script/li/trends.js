$(function(){
	var postUserTrends = function(){
		$.ajax({
            cache: true,
            type: "POST",
            url: "../trends/post.do",
            data: $('#userTrendsForm').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
            	var result = eval("(" + data + ")");
        		if(result.code == 1){
        			alert("添加成功！");
        			$('#userTrendsForm')[0].reset();
        		}
            }
        });
	};
	$('#submitFormButton').unbind("click").click(function(){
		postUserTrends();
	});
});
/**查找对于用户的最新动态*/
function searchTrends(){
	$.post("trends/user/search.do", {userId : $('#searchUserId').val(), count : $('#searchTrendscount').val()}, function(data){
		var result = eval("(" + data + ")");
		if(result.code == 1){
			var iHtml = "";
			$.each(result.data, function(i, item){
				iHtml += "<tr><td>" + item.id + "</td><td>" + item.content + "</td><td>" + item.address  + "</td><td>" +
					item.replyCount + "</td><td>" + (item.createTime == undefined ? "" : item.createTime) + "</td><td>" + (item.type == 1 ? "普通" : item.type == 2 ? "求知" : "未知")+
					"</td><td>" + item.userId + "</td></tr>";
			});
			$('#trendsList').html(iHtml);
		} else{
			alert("查询错误！");
		}
	});
}