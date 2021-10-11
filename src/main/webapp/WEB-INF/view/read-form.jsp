<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Read news</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />

</head>
<style>
.title {
	font: 35px Arial, Helvetica, sans-serif;
	font-weight: bold;
}

.date {
	font: 20px Arial, Helvetica, sans-serif;
	font-style: italic
}

.context {
	font: 25px Arial, Helvetica, sans-serif;
	border: 1px solid #408080;
	background-color: white;
	border-radius: 12px;
	padding: 4px;
	width: 65%;
}

.requirements {
	font: 17px Arial, Helvetica, sans-serif;
	padding: 4px;
	width: 65%;
	color: red;
	font-style: italic;
}

table {
	border-collapse: collapse;
	width: 45%;
	font-weight: normal;
}

tr:nth-child(even) {
	font-size: 15px;
	padding: 10px;
	text-align: right;
}

tr:nth-child(odd) {
	font-size: 20px;
	padding: 10px;
	text-align: left;
	font-weight: normal;
}
</style>



<body>

	<p>
		<a href="list"><spring:message code="local.on.main.page" /></a>
	</p>
	<c:url var="addNewComment" value="/news/addComment">
		<c:param name="newsId" value="${news.id}" />
	</c:url>
	<c:url var="enLink" value="/news/read?lang=en">
		<c:param name="newsId" value="${news.id}" />
	</c:url>
	<c:url var="ruLink" value="/news/read?lang=ru">
		<c:param name="newsId" value="${news.id}" />
	</c:url>
	<span style="float: right"> <a href="${enLink}"><spring:message
				code="local.en" /></a> | <a href="${ruLink}"><spring:message
				code="local.ru" /></a>
	</span>
	<br>
	<br>

	<div class="title">${news.title}</div>
	<br>
	<br>
	<div class="date">${news.publishingDate}</div>
	<br>
	<br>
	<br>
	<div class="context">${news.content}</div>
	<br>
	<br>

	<div class="comments">
		<spring:message code="local.read.comments" />
	</div>
	<table id="comments">
		<c:forEach var="tempComment" items="${commentsNews}">
			<tr>
				<td><br>${tempComment.comment}</td>

			</tr>
			<tr>
				<td>${tempComment.publishingDate}</td>

			</tr>

		</c:forEach>
	</table>

	<form:form action="${addNewComment}" modelAttribute="comment"
		method="POST">

		<!-- need to associate this data with comment id -->
		<form:hidden path="id" />

		<br>
		<form:textarea path="comment"
			cssStyle="width:50%;height:50px;font: 18px Arial, Helvetica, sans-serif;border: 1px solid #408080;border-radius: 12px;padding: 4px;" />
		<br>
		<c:if test="${not empty param.errorComment}">
			<div class="requirements">
				<spring:message code="local.read.error.comment" />
			</div>
		</c:if>
		<br>

		<input type="submit"
			value="<spring:message code="local.read.button.comment"/>"
			class="add-button" />


	</form:form>

</body>
</html>