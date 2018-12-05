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

</head>
<body>
	<div class="main clearfix">
		<div class="top">管理界面</div>
		<div class="row ">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" role="tablist">
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminServlet?method=findAllCategory" >分类管理</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminFillAllProduct">商品管理</a></li>
					<li role="presentation" class="active"><a href="#order" >订单管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="order">
						<p>商品管理</p>
						<table class="table table-bordered">
							<tr>
								<th>订单号</th>
								<th>订单金额</th>
								<th>收货人</th>
								<th>联系方式</th>
								<th>订单状态</th>
								<th>订单详情</th>
							</tr>
							<c:forEach items="${orderList }" var="order">
								<tr class="content">
									<td data-oid="${order.oid }">${order.oid }</td>
									<td>${order.total }</td>
									<td>${order.name }</td>
									<td>${order.telephone }</td>
									<td>${order.state==0?'未付款':'已付款' }</td>
								<td>
									<a href="#"  data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-cog"></span></a>
									<!-- 弹出层 -->
									<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									        <h4 class="modal-title" id="myModalLabel">订单详情</h4>
									      </div>
									      <div class="modal-body">
									        <table class="table table-bordered">
									        	<!-- TODO -->
									        	
										     </table>
									      </div>
									    </div>
									  </div>
									</div>
									
								</td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {
			//模态框显示时，ajax，订单详情态
			$('#myModal').on('shown.bs.modal', function (e) {
				//获取模态框
				var $this = $(this);
				//获取按钮
				var button = $(e.relatedTarget);
				var oid = button.parents(".content").find("td[data-oid]").attr("data-oid");
				
			  $.post(
				  "${pageContext.request.contextPath }/admin/adminServlet?method=findOrderByOid&oid="+oid,
				  {"oid":oid},
				  function (data){
					  //重置模态框内容
					 $this.find(".modal-body table").html("");
					  for(var i = 0; i < data.length; i ++){
						  var str = "<tr>"+
						  "	<th>图片</th>"+
						  "	<th>商品详情</th>"+
						  "	<th>价格</th>"+
						  "	<th>数量</th>"+
						  "	<th>小计</th>"+
						  "</tr>"+
						  "<tr>"+
						  "	<td><img src=\"${pageContext.request.contextPath }/"+data[i].image+"\"></td>"+
						  "	<td>"+data[i].title+"</td>"+
						  "	<td>"+data[i].money+"</td>"+
						  "	<td>"+data[i].count+"</td>"+
						  "	<td>"+data[i].subtotal+"</td>"+
						  "</tr>";
						  $this.find(".modal-body table").append(str);
					  }
				  },
				  "json"
			  );
			});
		});
	</script>
</body>
</html>