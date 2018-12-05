<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/adminLogin" method="post">
		<label>用户名：
			<input type="text" name="adminUsername" >
		</label>
		<label>密码：
			<input type="password" name="adminPassword" >
		</label>
		<input type="submit" value="登录" >
	</form>
</body>
</html>