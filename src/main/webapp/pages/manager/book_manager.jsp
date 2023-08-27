<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function (){
			$("a.deleteClass").click(function (){

				//返回true为点击，返回false为取消
				return confirm("确认删除 【" + $(this).parent().parent().find("td:first").text() + "】")
			})
		})
	</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@ include file="/pages/common/manager.menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="book?action=getBook&id=${book.id}&method=update">修改</a></td>
					<td><a class="deleteClass" href="book?action=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="book?action=page&pageNo=1">首页</a>
				<a href="book?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			【${requestScope.page.pageNo}】

			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="book?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="book?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 查询
			<input value="${param.pageNo < 1 ? 1 : (param.pageNo > requestScope.page.pageTotal ? requestScope.page.pageTotal : param.pageNo) }"
				   name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
		</div>

	</div>

	<script type="text/javascript">
		$(function (){
			$("#searchPageBtn").click(function (){
				var pageNo = $("#pn_input").val();
				location.href = "http://localhost:8080/bai/book?action=page&pageNo=" + pageNo
			})
		})
	</script>

	<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>