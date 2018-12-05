<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/adminAddProduct.css">
</head>
<body>
	<div class="main clearfix">
		<div class="top">添加商品</div>
		<form class="container"  action="${pageContext.request.contextPath }/admin/adminAddProduct" method="post" enctype="multipart/form-data" >
			<table class="table table-bordered">
				<tr>
					<td>
						<span>商品标题：</span><input type="text" name="title" >
					</td>
				</tr>
				<tr>
					<td>
						<span>价格：</span><input type="text" name="money">
					</td>
				</tr>
				<tr>
					<td>
						<span>分类：</span>
						<select class="productCategory" name="cid">
							<!-- TODO -->
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span>图片：</span><input type="file" name="image" multiple="multiple">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="提交" ></td>
				</tr>
			</table>
		</form>
	</div>
	<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//加载分类
		$(function () {
			$.post(
					"${pageContext.request.contextPath}/admin/adminServlet?method=findAllCategory&return=addProduct",
					function(data){
						var str = "<option>--请选择分类--</option>";
						for(var i = 0; i < data.length; i ++) {
							str += "<option value='"+data[i].cid+"'>"+data[i].cateName+"</option>";
						}
						$(".productCategory").html(str);
					},
					"json"
			);
		})
	</script>
</body>
</html>