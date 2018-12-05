<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--顶部-->
	<nav class="mao_top">
		<div class="mao_contain">
			<span>喵，欢迎来天猫</span> 
				<c:if test="${empty sessionScope.user }">
					<a href="${pageContext.request.contextPath }/login.jsp">请登录</a>
					<a href="${pageContext.request.contextPath }/register.jsp">免费注册</a>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
					${sessionScope.user.username }&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }/logout">退出登陆</a>
				</c:if>
			<div class="top_right">
				<ul>
					<li><a class="bg" href="javascript:;">我的淘宝<i
							class="triangle"></i></a></li>
					<li><span class="icon-cart mao_red"></span> <a
						href="${pageContext.request.contextPath }/permissions/addToCar">购物车<strong>${cart.cartItem.size() }</strong>件
					</a></li>
					<li><a class="bg" href="javascript:;">收藏夹</a></li>
					<li class="divider"><span>|</span></li>
					<li><span class="icon-phone mao_red"></span> <a
						href="javascript:;">手机版</a></li>
					<li><a class="bg" href="javascript:;">淘宝网</a></li>
					<li><a href="${pageContext.request.contextPath }/permissions/orderlist">订单</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--logo和搜索栏-->
	<header>
		<div class="mao_contain">
			<div class="logo">
				<h1>
					<a href="${pageContext.request.contextPath }/index"> <img src="${pageContext.request.contextPath }/images/mao-bg.png">
					</a>
				</h1>
			</div>
			<div class="search">
				<input type="search" placeholder="搜索 天猫 商品/品牌/店铺">
				<button type="submit">搜索</button>
			</div>
		</div>
	</header>