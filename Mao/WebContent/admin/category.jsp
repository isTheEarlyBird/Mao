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
		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked" role="tablist">
					<li role="presentation" class="active"><a href="#category" aria-controls="category" role="tab" data-toggle="tab">分类管理</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminFillAllProduct">商品管理</a></li>
					<li role="presentation"><a href="${pageContext.request.contextPath }/admin/adminServlet?method=findAllOrder" >订单管理</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="category">
						<p>分类管理</p>
						<a class="add" href="#"  data-toggle="modal" data-target="#myModal1">添加分类</span></a>
						<!-- 弹出层 -->
						<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel">添加分类</h4>
						      </div>
						      <div class="modal-body">
						      	<form action="${pageContext.request.contextPath }/admin/adminServlet?method=saveCategory" method="post">
							        <table class="table table-bordered">
							        	<!-- TODO -->
						        		<tr>
						        			<td>
						        				<label>cid：<input type="text" name="cid"></label>
						        				<label>名称：<input type="text" name="cateName"></label>
						        			</td>
						        		</tr>
						        		<tr>
						        			<td><input type="submit" value="提交" ></td>
						        		</tr>
								     </table>
								   </form>
						      </div>
						    </div>
						  </div>
						</div>
						<!-- 弹出层结束 -->
						<!-- 显示分类 -->
						<table class="table table-bordered">
							<tr>
								<th>序号</th>
								<th>分类名称</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
							<c:forEach items="${categoryLsit }" var="category">
								<tr class="content">
									<td data-cid="${category.cid }">${category.cid }</td>
									<td data-cateName="${category.cateName }">${category.cateName }</td>
									<td>
										<a href="#"  data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-pencil"></span></a>
										<!-- 弹出层 -->
										<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
										  <div class="modal-dialog" role="document">
										    <div class="modal-content">
										      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										        <h4 class="modal-title" id="myModalLabel">添加分类</h4>
										      </div>
										      <div class="modal-body">
										      	<form action="" method="post">
											        <table class="table table-bordered">
											        	<!-- TODO -->
										        		<tr>
										        			<td>
										        				<label>cid：<input type="text" name="cid" ></label>
										        				<label>名称：<input type="text" name="cateName"></label>
										        			</td>
										        		</tr>
										        		<tr>
										        			<td><input type="submit" value="提交" ></td>
										        		</tr>
												     </table>
												   </form>
										      </div>
										    </div>
										  </div>
										</div>
										<!-- 弹出层结束 -->
									</td>
									<td><a href="${pageContext.request.contextPath }/admin/adminServlet?method=deleteCategory&cid=${category.cid }"><span class="glyphicon glyphicon-remove"></span></a></td>
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
			
			$('#myModal2').on('shown.bs.modal', function (e) {
				//获取模态框
				var $this = $(this);
				//获取按钮
				var button = $(e.relatedTarget);
				var cid = button.parents(".content").find("td[data-cid]").attr("data-cid");
				var cateName = button.parents(".content").find("td[data-cateName]").attr("data-cateName");
				$this.find("table input[name='cid']").val(cid);
				$this.find("table input[name='cateName']").val(cateName);
				
				var href="${pageContext.request.contextPath }/admin/adminServlet?method=changeCategory&oldCid="+cid+
						"+&oldCateName="+cateName+"";
				$this.find("form").attr("action", href);
			});
		})
	</script>
</body>
</html>