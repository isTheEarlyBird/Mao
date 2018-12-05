<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付页面</title>
</head>
<body>
	模拟支付面
	<form action="${pageContext.request.contextPath }/permissions/pay" method="post">
		<input type="submit" value="确认支付" />
	</form>
</body>
</html>