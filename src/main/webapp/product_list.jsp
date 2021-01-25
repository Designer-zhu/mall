<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>


	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="${path}/productServlet?method=index">首页 &nbsp;&nbsp;&gt;&nbsp;&nbsp;</a>
				${requestScope.cname}
			</ol>
		</div>

		<c:forEach items="${requestScope.pageVo.list}" var="product">
			<div class="col-md-2" style="height:250px;">
				<a href="product_info.jsp"> <img src="${product.pimage}" width="170" height="170" style="display: inline-block;">
				</a>
				<p>
					<a href="product_info.jsp" style='color: green'>${product.pname}</a>
				</p>
				<p>
					<font color="#FF0000">商城价：&yen;${product.shop_price}</font>
				</p>
			</div>
		</c:forEach>

	</div>

	<!--分页 -->
	<%--当页面没有数据时，则展示一张“空”图片--%>
	<c:if test="${pageVo.list.size()==0}">
		<div style="width: 380px;margin: 50px auto;">
			<img src="images/cart-empty.png">
		</div>
	</c:if>

	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">

			<%--若是第一页，则不可以点击上一页--%>
			<c:if test="${pageVo.pageNow==1}">
				<li class="disabled">
					<a href="JavaScript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>

			<%--若不是第一页，则可以点击上一页--%>
			<c:if test="${pageVo.pageNow!=1}">
				<li>
					<a href="${path}/productServlet?method=viewProduct&cid=${pageVo.query1}&pname=${pageVo.query2}&pageNow=${pageVo.pageNow-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
				</li>
			</c:if>

			<%--动态处理页码--%>
			<c:forEach begin="1" end="${pageVo.myPages}" var="page">

				<%--如果是当前页，则不能被点击,显示被点击状态--%>
				<c:if test="${pageVo.pageNow == page}">
					<li class="active">
						<a href="JavaScript:void(0)">${page}</a>
					</li>
				</c:if>

				<%--如果不是当前页，则可以被点击--%>
				<c:if test="${pageVo.pageNow != page}">
					<li>
						<a href="${path}/productServlet?method=viewProduct&cid=${pageVo.query1}&pname=${pageVo.query2}&pageNow=${page}">${page}</a>
					</li>
				</c:if>
			</c:forEach>



			<%--若是最后一页，则不可以点击下一页--%>
			<c:if test="${pageVo.pageNow==pageVo.myPages}">
				<li class="disabled">
					<a href="JavaScript:void(0)" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>

			<%--若不是最后一页，则可以点击下一页--%>
			<c:if test="${pageVo.pageNow!=pageVo.myPages}">
				<li>
					<a href="${path}/productServlet?method=viewProduct&cid=${pageVo.query1}&pname=${pageVo.query2}&pageNow=${pageVo.pageNow+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
		</ul>
	</div>
	<!-- 分页结束 -->

	<!--商品浏览记录-->
	<div
		style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">
				<li
					style="width: 150px; height: 216px; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;"><img
					src="products/1/cs10001.jpg" width="130px" height="130px" /></li>
			</ul>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>