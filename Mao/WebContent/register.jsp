<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
    <jsp:include page="/header.jsp"></jsp:include>
    <!-- 内容 -->
    <div class="main">
        <form action="${pageContext.request.contextPath }/register" id="register" method="post">
            <div>
                <i>会员名：</i>
                <input id="userName" type="text" name="username" maxlength="8">
            </div>
            <div>
                <i>登陆密码：</i>
                <span class="addIcon">
                    <input id="password" type="password" name="password" maxlength="15">
                </span>
            </div>
            <div>
                <i>密码确认：</i>
                <span class="addIcon">
                    <input id="rePassword" type="password" name="rePassword" maxlength="15">
                </span>
            </div>
            <div>
                <i>姓名：</i>
                <span class="addIcon">
                    <input id="name" type="text" name="name" maxlength="8">
                </span>
            </div>
            <div>
                <i>性别：</i>
                <span class="addIcon">
                    <input type="radio" name="gender" value="male" />男
                    <input type="radio" name="gender" value="female" />女
                </span><br>
                <label id="userName-error" class="error" for="gender" style="display:none">This field is required.</label>
            </div>
            <div>
                <i>邮箱：</i>
                <span class="addIcon">
                    <input id="email" type="email" name="email" maxlength="15">
                </span>
            </div>
            <div>
                <i>手机：</i>
                <span class="addIcon">
                    <input id="tel" type="tel" name="tel" maxlength="15">
                </span>
            </div>
            <div>
                <i>生日：</i>
                <span class="addIcon">
                    <input id="birthday" type="date" name="birthday" maxlength="15">
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
			return flag;
		});
    </script>
    <script src="js/register.js"></script>
</body>
</html>