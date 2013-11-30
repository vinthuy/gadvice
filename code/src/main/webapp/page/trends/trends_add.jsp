<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加动态</title>
<script type="text/javascript" src="${ctx}/script/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/script/li/trends.js"></script>
</head>
<body>
	<div style="text-align: center;">
		<form action="" method="post" id="userTrendsForm">
		<table>
			<tbody>
				<tr>
					<td>用户ID：</td>
					<td><input name="userId"/></td>
				</tr>
				<tr>
					<td>用户类型：</td>
					<td><input name="userType"/></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><input name="content"/></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input name="address"/></td>
				</tr>
				<tr>
					<td>评论数：：</td>
					<td><input name="replyCount"/></td>
				</tr>
				<tr>
					<td>图片：</td>
					<td><input name="icon"/></td>
				</tr>
				<tr>
					<td>评论数：：</td>
					<td>
						<select name="type">
							<option value="1">普通</option>
							<option value="2">求知</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>转发数：</td>
					<td><input name="transto"/></td>
				</tr>
			</tbody>
		</table>
		</form>
		<input type="button" value="提交" id="submitFormButton"/>
	</div>
	<div>
		<a href="../goTrends.do">列表</a>
	</div>
</body>
</html>