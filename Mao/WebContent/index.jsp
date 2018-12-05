<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<!--顶部-->
	<nav class="mao_top">
		<div class="mao_contain">
			<div class="top_left">
				<span>喵，欢迎来天猫</span> 
				<c:if test="${empty sessionScope.user }">
					<a href="${pageContext.request.contextPath }/login.jsp">请登录</a>
					<a href="${pageContext.request.contextPath }/register.jsp">免费注册</a>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
					${sessionScope.user.username }
					<a href="${pageContext.request.contextPath }/logout">退出登陆</a>
				</c:if>
			</div>
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
					<a href="${pageContext.request.contextPath}/index"> <img src="images/logo.gif">
					</a>
				</h1>
			</div>
			<div class="search">
				<input type="search" placeholder="搜索 天猫 商品/品牌/店铺">
				<button type="submit">搜索</button>
				<div class="hotWord">
					<ul>
						<li><a href="javascript:;">针织衫</a></li>
						<li><a class="mao_red" href="javascript:;">连衣裙</a></li>
						<li><a href="javascript:;">四件套</a></li>
						<li><a class="mao_red" href="javascript:;">摄像头</a></li>
						<li><a href="javascript:;">客厅灯</a></li>
						<li><a href="javascript:;">口红</a></li>
						<li><a class="mao_red" href="javascript:;">手机</a></li>
						<li><a href="javascript:;">运动鞋</a></li>
						<li><a class="mao_red" href="javascript:;">牛奶</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<!--内容-->
	<div class="mao_content">
		<!--导航栏-->
		<div class="main_nav">
			<span><i class="icon-list mr_l mr_r"></i>商品分类</span> <a href="#"><img
				src="images/market.png"></a> <a href="#"><img
				src="images/international.png"></a> <a href="#" target="_blank">天猫会员</a>
			<a href="#" target="_blank">电器城</a> <a href="#" target="_blank">喵鲜生</a>
			<a href="#" target="_blank">医药馆</a> <a href="#" target="_blank">营业厅</a>
		</div>
		<!--轮播图-->
		<div class="banner">
			<div class="banner-img">
				<ul>
					<!--TODO-->

				</ul>
			</div>
			<div class="dots">
				<ul>
					<!--TODO-->
				</ul>
			</div>
		</div>
		<!--分类-->
		<div class="category">
			<ul>
				<c:forEach items="${categoryList }" var="category">
					<li><i class="icon-gift"></i><a href="${pageContext.request.contextPath }/category?cid=${category.cid}">${category.cateName }</a></li>
				</c:forEach>
			</ul>
		</div>
		<!--商品区域-->
		<div class="goods">
			<!--区域1-->
			<div class="item">
				<div class="goods_top">
					<div class="top_left f_l">
						<img src="images/mart.png">
					</div>
					<div class="top_right f_r">
						<a href="javascript:;">进口食品</a>
						<a href="javascript:;">食品饮料</a> 
						<a href="javascript:;">粮油副食</a>
						<a href="javascript:;">美容洗护</a>
						<a href="javascript:;">家居家电</a>
						<a href="javascript:;">家庭清洁</a>
						<a href="javascript:;">母婴用品</a>
						<a href="javascript:;">生鲜水果</a>
					</div>
				</div>
				<div class="goods_main mr_t">
					<div class="big_banner f_l">
						<a href="javascript:;"> <img src="images/big1.jpg">
						</a>
					</div>
					<div class="middle middleFirst">
						<!--TODO-->
						<c:forEach items="${marketList }" var="product">
							<div class="goods-content">
								<a href="${pageContext.request.contextPath }/productInfo?id=${product.id }"> <img src="${pageContext.request.contextPath }/${product.image }">
									<div>${product.title }</div>
									<p>
										<i>&yen;</i> <span class="money">${product.money }</span>
									</p>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!--区域2-->
			<div class="item">
				<div class="goods_top">
					<div class="top_left f_l">
						<img src="images/hk.png">
					</div>
					<div class="top_right f_r">
						<a href="javascript:;">口红</a> <a href="javascript:;">YSL</a> <a
							href="javascript:;">面膜</a> <a href="javascript:;">防晒</a> <a
							href="javascript:;">奥迪</a>
					</div>
				</div>
				<div class="goods_main mr_t">
					<div class="big_banner f_l">
						<a href="javascript:;"> <img src="images/small.png"> <img
							src="images/small1.png">
						</a>
					</div>
					<div class="middle middleSecond">
						<!--TODO-->
						<c:forEach items="${tmallHKList }" var="product">
							<div class="goods-content">
								<a href="${pageContext.request.contextPath }/productInfo?id=${product.id }"> <img src="${pageContext.request.contextPath }/${product.image }">
									<div>${product.title }</div>
									<p>
										<i>&yen;</i> <span class="money">${product.money }</span>
									</p>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>

	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/handlebars-v4.0.12.js"></script>
	<script src="js/common.js"></script>
	<script src="js/index.js"></script>
	<script>
		Handlebars.registerHelper("isfirst", function(value, options) {
			if (value == 0) {
				return options.fn(this);
			}
		});
	</script>
	<!--handlebars/轮播图-->
	<script type="text/x-handlebars-template" id="bannerImg">
        {{#data}}
        <li {{#isfirst @index}} class="now" {{/isfirst}}>
            <a href="#">
                <img src="{{src}}">
            </a>
        </li>
        {{/data}}
    </script>
	<!--handlebars/轮播方形（圆点）-->
	<script type="text/template" id="bannerDots">
        {{#data}}
        <li {{#isfirst @index}} class="now" {{/isfirst}} data-id="{{@index}}"></li>
        {{/data}}
    </script>
	<!--template/商品区域1-->
	<!-- <script type="text/template" id="temp-goods1">
        {{#data}}
        <div class="goods-content">
            <a href="product.jsp?pid={{id}}">
                <img src="{{src}}" alt="">
                <div>{{title}}</div>
                <p>
                    <i>&yen;</i>
                    <span class="money">{{money}}</span>
                </p>
            </a>
        </div>
        {{/data}}
    </script> -->
	<!--template/商品区域2-->
	<!--<script type="text/template" id="temp-goods2">
        {{#data}}
        <div class="goods-content">
            <a href="product.jsp?pid={{id}}">
                <img src="{{src}}" alt="">
                <div>{{title}}</div>
                <p>
                    <i>&yen;</i>
                    <span class="money">{{money}}</span>
                </p>
            </a>
        </div>
        {{/data}}
    </script>  -->
</body>
</html>