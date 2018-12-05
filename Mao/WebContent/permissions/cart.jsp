<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/cart.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<c:if test="${!empty cart.cartItem }">
		<div class="cart">
			<p>订单详情</p>
			<div class="clear"><a href="javascript:void(0)" onclick="clearCart()">清空购物车</a></div>
			<table border="1" cellspacing="0">
				<tr class="row">
					<td width="8%">图片</td>
					<td width="50%">商品</td>
					<td width="10%">价格</td>
					<td width="8%">数量</td>
					<td width="16%">小计</td>
					<td width="8%">操作</td>
				</tr>
				<c:forEach items="${ cart.cartItem}" var="item">
					<tr class="item">
						<td class="img"><img src="${pageContext.request.contextPath }/${ item.value.product.image}"></td>
						<td class="title"><a href="${pageContext.request.contextPath }/productInfo?id=${ item.value.product.id}" target="_block">${ item.value.product.title}</a></td>
						<td class="money">&yen;${ item.value.product.money}</td>
						<td class="num">${ item.value.num}</td>
						<td class="subtotal">${ item.value.subtotal}</td>
						<td class="del"><a href="javascript:void(0)" onclick="deleteItem('${ item.value.product.id}')">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<p class="total">总金额：&yen;<i>${cart.total }</i></p>
			<div class="btn clearfix"><a href="${pageContext.request.contextPath}/permissions/orderSubmit">提交订单</a></div>
		</div>
	</c:if>
	<c:if test="${empty cart.cartItem }">
		<div class="cart">
			<div class="empty"><p>没有商品</p></div>
		</div>
	</c:if>
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		//删除某项商品
		function deleteItem(id){
			if(confirm("是否要删除该项")){
				location.href="${pageContext.request.contextPath}/permissions/deleteItemCart?id="+id;
			}
		}
		//清空购物车
		function clearCart() {
			if(confirm("是否要清空购物车")){
				location.href="${pageContext.request.contextPath}/permissions/clearCart";
			}
		}
	</script>
	
</body>
</html>