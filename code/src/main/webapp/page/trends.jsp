<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态</title>
<script type="text/javascript" src="${ctx}/script/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/script/li/trends.js"></script>
<style type="text/css">
	.listContent{
		width: 100%;
		margin: 20px auto;
	}
	td{
		text-align: center;
	}
</style>
</head>
<body>
	<div class="listContent">
		<div >用户ID：
			<input id="searchUserId" value="1"/>
			条数：<input id="searchTrendscount" value="10"/>
			<span style="margin-left: 20px;"><input type="button" value="查找" onclick="searchTrends()"/></span>
		</div>
		<table width="100%">
			<thead>
				<tr>
					<th width="30%">ID</th>
					<th>内容</th>
					<th>地址</th>
					<th>评论数</th>
					<th>创建时间</th>
					<th>类型</th>
					<th>用户ID</th>
				</tr>
			</thead>
			<tbody id="trendsList"></tbody>
		</table>
	</div>
</body>
</html>