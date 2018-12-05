<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admainIndex.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/adminProduct.css">

</head>
<body>
	<div class="main clearfix">
		<div class="top">管理界面</div>
		<div class="row ">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" role="tablist">
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminServlet?method=findAllCategory" >分类管理</a></li>
					<li role="presentation" class="active"><a href="#product" aria-controls="product" role="tab" data-toggle="tab">商品管理</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminServlet?method=findAllOrder" >订单管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="product">
						<p>商品管理</p>
						<a class="add" href="${pageContext.request.contextPath }/admin/addProduct.jsp">添加商品</a>
						<table class="table table-bordered">
							<tr>
								<th>序号</th>
								<th>图片</th>
								<th>商品名称</th>
								<th>价格</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
							<c:forEach items="${pageBean.list }" var="item">
								<tr class="content">
									<td data-id="${item.id }">${item.id }</td>
									<td data-image="${item.image }"><img src="${pageContext.request.contextPath }/${item.image }" ></td>
									<td data-title="${item.title }">${item.title }</td>
									<td data-money="${item.money }">${item.money }</td>
									<td>
										<a href="#myModal"  data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></a>
										<!-- 弹出层 -->
										<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
										  <div class="modal-dialog" role="document">
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										        <h4 class="modal-title" id="myModalLabel">订单详情</h4>
										      </div>
										      <div class="modal-body">
										      	<form action="${pageContext.request.contextPath }/admin/adminServlet?method=changeProduct" method="post">
											        <table class="table table-bordered">
											        	<!-- TODO -->
											        	
												     </table>
												</form>
										      </div>
										    </div>
										  </div>
										</div>
									
									</td>
									<td><a href="#" class="delete"><span class="glyphicon glyphicon-remove"></span></a></td>
								</tr>
							</c:forEach>
						</table>
						<!-- 分页 -->
						<nav aria-label="Page navigation">
						  <ul class="pagination  pagination-lg">
						    <li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="1" end="${pageBean.getTotalPage()}" var="i">
							    <c:if test="${pageBean.getCurrentPage() == i}">
									<li class="active"><a href="javascript:void(0)">${i }</a></li>
								</c:if>
								<c:if test="${pageBean.getCurrentPage() != i}">
							   		<li><a href="${pageContext.request.contextPath }/admin/adminFillAllProduct?&currentPage=${i}">${i }</a></li>
							   	</c:if>
						    </c:forEach>
						    <li>
						      <a href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//修改商品信息  弹出层内容
		$(function () {
			$('#myModal').on('shown.bs.modal', function (e) {
				//获取模态框
				var $this = $(this);
				//获取按钮
				var button = $(e.relatedTarget);
				
				var id = button.parents(".content").find("td[data-id]").attr("data-id");
				var image = button.parents(".content").find("td[data-image]").attr("data-image");
				var title = button.parents(".content").find("td[data-title]").attr("data-title");
				var money = button.parents(".content").find("td[data-money]").attr("data-money");
				
				var str = "<tr><td>序号：<input type='text' name='id'  value='"+id+"' disabled></td></tr>"+
				"<tr><td>图片地址：<input type='text' name='image'  value= '"+image+"'></td></tr>"+
				"<tr><td>商品名称：<input type='text' name='title'  value='"+title+"'></td></tr>"+
				"<tr><td>价格：<input type='text' name='money'  value='"+money+"'></td></tr>"+
				"<tr><td><input type='submit' value='提交' ></td></tr>";
				$this.find(".modal-body table").html(str);
				
				var href="${pageContext.request.contextPath }/admin/adminServlet?method=changeProduct&id="+id;
				$this.find("form").attr("action", href);
			});
		});
		// 删除商品
		$(".delete").on("click", function(){
			$this = $(this);
			var flag = confirm("确定要删除？");
			if(flag){
				var id = $this.parents(".content").find("td[data-id]").attr("data-id");
				$.post(
					"${pageContext.request.contextPath }/admin/adminServlet?method=deleteProduct",
					{"id": id},
					function(data){
						console.log(data)
						if(data.success == "success"){
							location.href="${pageContext.request.contextPath }/admin/adminFillAllProduct";
						}
					},
					"json"
				);
			}
		});
	</script>
</body>
</html>