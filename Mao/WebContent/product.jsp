<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/product.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<!--分类-->
	<div class="category">
		<div class="cateContent">
			<div class="allCate f_l">所有产品分类</div>
			<div class="cateRight f_l">
				<ul>
					<li><a href="${pageContext.request.contextPath }/index">首页</a></li>
					<li><a href="javascript:;">量贩装</a></li>
					<li><a href="javascript:;">生鲜区</a></li>
					<li><a href="javascript:;">企业采购</a></li>
					<li><a href="javascript:;">大家电</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="content">
		<!--详述-->
		<div class="detail clearfix">
			<!--商品图片-->
			<div class="detail-left f_l">
				<div class="show">
					<!--小图-->
					<div class="smallImg">
						<img src="${pageContext.request.contextPath }/${product.image}"> <span></span>
						<!--遮罩层-->
					</div>
					<!--大图-->
					<div class="bigImg">
						<img src="${pageContext.request.contextPath }/${product.image}">
					</div>
					<!--选择图片-->
					<div class="select clearfix">
						<ul>
							<c:forEach items="${product.imgs }" var="imgsrc">
							<li class="now">
								<a href="javascript:void(0);">
									<img src="${pageContext.request.contextPath }/${imgsrc}">
								</a>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="collection">
						<p>
							<a href="javascipt:;"><span class="icon-share"></span>分享</a> <a
								href="javascipt:;"><span class="icon-star"></span>收藏商品</a> <span>(5683人气)</span>
						</p>
					</div>
				</div>
			</div>
			<!--商品详情-->
			<div class="detail-middle f_l">
				<div class="goods-title">${product.title }</div>
        		<div class="goods-price">价格<i>&yen;</i><span>${product.money }</span></div>
				<div class="transport">
					<i>运费</i> <a href="javascript:;" class="province">广东<i
						class="icon-location"></i></a> <a href="javascript:;" class="city">广州<i
						class="icon-location"></i></a> <span>满88(20Kg内)包邮</span> <a href="#">配送规则</a>
				</div>
				<div class="panel">
					<a href="#">累计评价<span>554</span></a> <a href="#">送天猫积分<i>6</i></a>
				</div>
				<div class="num">
					数量 <input name="num" type="text" value="1"> <span
						class="amount-btn"> <span class="icon-up"></span> <span
						class="icon-down"></span>
					</span> 件
				</div>
				<div class="addCart"><a href="javascript:void(0);" onclick="addCart()">加入购物车</a></div>
				<p class="deliver">
					<span>配送范围</span> <span>送货范围仅限汕头、云浮、汕尾、揭阳、中山、珠海、东莞、梅州、茂名、清远、广州、湛江、惠州、深圳、佛山、阳江、河源、肇庆、韶关、江门、潮州地区(生鲜类别仅限部分地区)</span>
				</p>
			</div>
			<div class="detail-right"></div>
		</div>
	</div>
	
	<jsp:include page="/footer.jsp"></jsp:include>
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/product.js"></script>
	<script type="text/javascript">
			//添加到购物车
			function addCart(){
				var num = $(".num input").val();
				location.href = "${pageContext.request.contextPath }/permissions/addToCar?id=${product.id}&num="+num;
			}	
	</script>
</body>
</html>