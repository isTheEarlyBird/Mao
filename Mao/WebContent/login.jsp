<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
 <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="main">
		<form action="${pageContext.request.contextPath }/login" id="register" method="post">
	           <div>
	               <i>会员名：</i>
	               <input id="userName" type="text" name="username" maxlength="8">
	           </div>
	           <div>
	               <i>登陆密码：</i>
	               <span class="addIcon">
	                   <input id="password" type="password" name="password" maxlength="15">
	                   <span class="error"><c:if test="${!empty param.error }">密码错误</c:if></span>
	               </span>
	           </div>
	           <button type="submit">提交</button>
	       </form>
	     </div>
       <jsp:include page="/footer.jsp"></jsp:include>
		<script src="js/jquery-1.12.4.js"></script>
		<script src="js/common.js"></script>
   		 <script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<script type="text/javascript">
			 $.validator.addMethod("isExist", function(value, element, params) {
				var flag = false;
				// 校验username是否存在
				$.ajax({
					"async":false,
					"url":"${pageContext.request.contextPath}/checkUsername",
					"data":{"username":value},
					"type":"POST",
					"dataType":"json",
					"success":function(data){
						flag = data.isExist;
					}
				});
				return !flag;
			});
			$("#register").validate({
				rules : {
					"username" : {
						"required" : true,
						"isExist" : true
					},
					"password" : {
						"required" : true,
						"rangelength" : [ 6, 12 ]
					}
				},
				messages : {
					"username" : {
						"required" : "会员名不能为空",
						"isExist": "会员不存在"
					},
					"password" : {
						"required" : "密码不能为空",
						"rangelength" : "密码长度为6~12位"
					}
				}
			});
		</script>
</body>
</html>