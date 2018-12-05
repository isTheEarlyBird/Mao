<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/categoryList.css">
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<!-- 内容 -->
	<div id="main">
		<div class="goods">
			<c:forEach items="${pageBean.list }" var="item">
				<div class="goods-content">
					<a href="${pageContext.request.contextPath }/productInfo?id=${item.id }"> <img src="${pageContext.request.contextPath }/${item.image }">
						<div>${item.getTitle() }</div>
						<p>
							<i>&yen;</i> <span class="money">${item.getMoney() }</span>
						</p>
					</a>
				</div>
			</c:forEach>
		</div>
		<div class="paging">
			<div>
				<ul>
					<!-- 上一页 -->
					<c:if test="${pageBean.getCurrentPage() == 1 }">
						<li class="disabled"><a href="javascript:void(0)">&lt;</a></li>
					</c:if>
					<c:if test="${pageBean.getCurrentPage()  != 1 }">
						<li><a href="${pageContext.request.contextPath }/category?cid=${cid }&currentPage=${pageBean.getCurrentPage()  - 1}">&lt;</a></li>
					</c:if>
					<!-- 页数 -->
					<c:forEach begin="1" end="${pageBean.getTotalPage() }" var="i">
						<c:if test="${pageBean.getCurrentPage() == i}">
							<li class="active"><a href="javascript:void(0)">${i }</a></li>
						</c:if>
						<c:if test="${pageBean.getCurrentPage() != i}">
							<li><a href="${pageContext.request.contextPath }/category?cid=${cid }&currentPage=${i}">${i }</a></li>
						</c:if>
					</c:forEach>
					<!-- 下一页 -->
					<c:if test="${pageBean.getCurrentPage() == pageBean.getTotalPage()}">
						<li class="disabled"><a href="javascript:void(0)">&gt;</a></li>
					</c:if>
					<c:if test="${pageBean.getCurrentPage()  != pageBean.getTotalPage() }">
						<li><a href="${pageContext.request.contextPath }/category?cid=${cid }&currentPage=${pageBean.getCurrentPage()  + 1}">&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!-- 历史记录 -->
	<div class="history">
		<div class="history-top">历史记录</div>
		<div class="history-main">
			<ul>
				<c:forEach items="${historyList }" var="item">
					<li>
						<a href="${pageContext.request.contextPath }/productInfo?id=${item.id }"> <img src="${ pageContext.request.contextPath}/${item.image}">
							<p>${item.title }</p>
							<p>
								<i>&yen;</i> <span class="money">${item.money }</span>
							</p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!--<jsp:include page="/footer.jsp"></jsp:include>-->
	<script src="js/jquery-1.12.4.js"></script>
	<script src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</body>
</html>