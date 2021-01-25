<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="order_list.jsp">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${path}/productServlet?method=index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categorys">
					<%--动态添加--%>
				</ul>

				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search" id="search" name="pname" value="${requestScope.pageVo.query2}">
					</div>
					<input type="button" class="btn btn-success" value="Submit" onclick="query()"/>
				</form>

			</div>
		</div>
	</nav>
</div>
<script>
	var data = "";
	$.ajax({
		type:"get",
		url:"${path}/categoryServlet?method=header",
		dataType:"json",
		success:function (rs) {
			for (var i in rs) {
				data += "<li><a href='${path}/productServlet?method=viewProduct&cid="+rs[i].cid+"'>"+rs[i].cname+"</a></li>"
			}
			$("#categorys").html(data);
		}
	});
</script>

<script>
	function query() {
		window.location.href="${path}/productServlet?method=viewProduct&cid=${pageVo.query1}&pname="+$("#search").val();
	}
</script>