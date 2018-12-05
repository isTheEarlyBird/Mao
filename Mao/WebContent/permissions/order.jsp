<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/cart.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
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
				<c:forEach items="${ cart.cartItem}" var="item">
					<tr class="item">
						<td class="img"><img src="${pageContext.request.contextPath }/${ item.value.product.image}"></td>
						<td class="title"><a href="${pageContext.request.contextPath }/productInfo?id=${ item.value.product.id}" target="_block">${ item.value.product.title}</a></td>
						<td class="money">&yen;${ item.value.product.money}</td>
						<td class="num">${ item.value.num}</td>
						<td class="subtotal">${ item.value.subtotal}</td>
					</tr>
				</c:forEach>
			</table>
			<p class="total">总金额：&yen;<i>${cart.total }</i></p>
		</div>
		
		<jsp:include page="/footer.jsp"></jsp:include>
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</body>
</html>