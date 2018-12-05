<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/cart.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/orderinfo.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath }/permissions/orderinfo" method="post">
	<input type="hidden"  name="oid" value="${order.oid }"/>
		<div class="cart">
			<p>订单详情</p>
			<table border="1" cellspacing="0">
				<tr class="row">
					<td width="8%">图片</td>
					<td width="50%">商品</td>
					<td width="10%">价格</td>
					<td width="8%">数量</td>
					<td width="16%">小计</td>
				</tr>
				<c:forEach items="${order.orderItemList }" var="orderItem">
					<tr class="item">
						<td class="img"><img src="${pageContext.request.contextPath }/${orderItem.product.image}"></td>
						<td class="title"><a href="${pageContext.request.contextPath }/productInfo?id=${orderItem.product.id}" target="_block">${orderItem.product.title} </a></td>
						<td class="money">&yen;${orderItem.product.money}</td>
						<td class="num">${orderItem.count }</td>
						<td class="subtotal">${ orderItem.subtotal}</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
		<div class="userinfo">
			<label>联系人：&nbsp;&nbsp;&nbsp;
				<input type="text" name="name" maxlength="8"><br>
			</label>
			<label>联系电话：
				<input type="text" name="telephone" value="${order.telephone }" /><br>
			</label>
			<label>联系地址：
				<input type="text" placeholder="请输入地址" name ="address"/><br>
			</label>
			<p class="total">总金额：&yen;<i>${order.total }</i></p>
		</div>
		<p class="btn"><input type="submit" value="确认订单" /></p>
	</form>
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</body>
</html>