<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/orderlist.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="main">
		<p>我的订单</p>
		<c:if test="${empty orderList }">
			<p>没有订单，看看<a href="${pageContext.request.contextPath }/permissions/orderinfo.jsp">下单</a>了没</p>
		</c:if>
		<c:if test="${!empty orderList }">
			<c:forEach items="${orderList }" var="order">
				<table border="1" cellspacing="0">
					<tbody>
						<tr>
							<th colspan="5"><p>订单编号：${order.oid } &nbsp;&nbsp;<span>${order.state == 0?"未付款":"已付款" }</span></p></th>
						</tr>
						<tr class="row">
							<td width="8%">图片</td>
							<td width="50%">商品</td>
							<td width="10%">价格</td>
							<td width="8%">数量</td>
							<td width="16%">小计</td>
						</tr>
						<c:forEach items="${order.orderItemList }" var="orderitem">
							<tr class="item">
								<td class="img"><img src="${pageContext.request.contextPath }/${orderitem.product.image }"></td>
								<td class="title">${orderitem.product.title }</td>
								<td class="money">&yen;${orderitem.product.money }</td>
								<td class="num">${orderitem.count }</td>
								<td class="subtotal">${ orderitem.subtotal}</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="total"  colspan="5"> 总价：${order.total }</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>