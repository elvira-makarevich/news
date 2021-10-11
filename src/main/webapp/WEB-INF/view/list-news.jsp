<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>News list</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
</head>

<style>
input[type=submit] {
	border: 1px solid #666;
	border-radius: 5px;
	padding: 4px;
	font-size: 17px;
	font-weight: bold;
	padding: 5px 10px;
	margin-bottom: 15px;
	background: #cccccc;
}

.button_in_line {
	display: inline-block;
	float: right;
}

.answer {
	text-align: center;
	color: #408080;
	font: 22px Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
	<span style="float: right"> <a href="?lang=en&page=${page}"><spring:message
				code="local.en" /></a> | <a href="?lang=ru&page=${page}"><spring:message
				code="local.ru" /></a>
	</span>
	<br>
	<br>


	<security:authorize access="hasRole('ANONYMOUS')">
		<div class="button_in_line">
			<form:form
				action="${pageContext.request.contextPath}/showRegistrationPage"
				method="GET">

				<input type="submit"
					value="<spring:message
					code="local.register"/>  " />

			</form:form>
		</div>
		<div class="button_in_line">
			<form:form action="${pageContext.request.contextPath}/showLoginPage"
				method="GET">

				<input type="submit"
					value="<spring:message
					code="local.login"  />" />

			</form:form>

		</div>

	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<span style="float: right"><form:form
				action="${pageContext.request.contextPath}/logout" method="POST">

				<input type="submit"
					value="<spring:message
					code="local.logout"  />" />

			</form:form> </span>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<span style="float: right"><form:form
				action="${pageContext.request.contextPath}/logout" method="POST">

				<input type="submit"
					value="<spring:message
					code="local.logout"  />" />

			</form:form> </span>
	</security:authorize>
	<br>
	<div class="header">
		<spring:message code="local.news" />
	</div>
	<hr>
	<br>
	<c:if test="${not empty param.messageUpdateNewsOK}">
		<div class="answer">
			<spring:message code="local.message.update.ok" />
		</div>
	</c:if>
	
	<c:if test="${not empty param.messageDeleteNewsOK}">
		<div class="answer">
			<spring:message code="local.message.delete.ok" />
		</div>
	</c:if>
	<br>
	<form:form
		action="${pageContext.request.contextPath}/news/showFormForAdd"
		method="GET">

		<input type="submit" value="<spring:message code="local.add.news"/>" />

	</form:form>

	<table>

		<c:forEach var="tempNews" items="${news}">

			<c:url var="readLink" value="/news/read">
				<c:param name="newsId" value="${tempNews.id}" />
			</c:url>

			<c:url var="updateLink" value="/news/showFormForUpdate">
				<c:param name="newsId" value="${tempNews.id}" />
			</c:url>


			<c:url var="deleteLink" value="/news/delete">
				<c:param name="newsId" value="${tempNews.id}" />
			</c:url>

			<tr>

				<td><br>${tempNews.title}<br></td>

			</tr>
			<tr>
				<td>${tempNews.brief}<br></td>

				<td><a href="${readLink}"><spring:message code="local.read" /></a>
					<security:authorize access="hasRole('ADMIN')"> | <a href="${updateLink}"><spring:message code="local.update" /></a>
					| <a href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false"><spring:message
							code="local.delete" /></a>	</security:authorize></td>

			</tr>

		</c:forEach>
		<tr>
			<td><br> <c:forEach begin="${1}" end="${pagesCount}"
					step="1" varStatus="i">
					<c:url value="/news/list" var="url">
						<c:param name="page" value="${i.index}" />
					</c:url>
					<a href="${url}">${i.index}</a>
				</c:forEach></td>
		</tr>
	</table>

</body>
</html>