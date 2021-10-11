<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Add news</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />

<style>
.requirements {
	font: 17px Arial, Helvetica, sans-serif;
	padding: 4px;
	width: 65%;
	color: red;
	font-style: italic;
}
</style>
</head>
<body>

	<p>
		<a href="list"><spring:message code="local.on.main.page" /></a>
	</p>
	<c:if test="${command == 'update'}">
		<c:url var="enLink" value="/news/showFormForUpdate?lang=en">
			<c:param name="newsId" value="${news.id}" />
		</c:url>
		<c:url var="ruLink" value="/news/showFormForUpdate?lang=ru">
			<c:param name="newsId" value="${news.id}" />
		</c:url>
		<span style="float: right"> <a href="${enLink}"><spring:message
					code="local.en" /></a> | <a href="${ruLink}"><spring:message
					code="local.ru" /></a>
		</span>
	</c:if>

	<c:if test="${command == 'add'}">
		<c:url var="enLink" value="/news/showFormForAdd?lang=en">
			<c:param name="newsId" value="${news.id}" />
		</c:url>
		<c:url var="ruLink" value="/news/showFormForAdd?lang=ru">
			<c:param name="newsId" value="${news.id}" />
		</c:url>
		<span style="float: right"> <a href="${enLink}"><spring:message
					code="local.en" /></a> | <a href="${ruLink}"><spring:message
					code="local.ru" /></a>
		</span>
	</c:if>
	<br>
	<br>

	<div class="header">
		<spring:message code="local.save.news" />
	</div>
	<hr>
	<br>
	<br>

	<form:form action="${command}" modelAttribute="news" method="POST">

		<!-- need to associate this data with news id -->
		<form:hidden path="id" />

		<div class="label">
			<spring:message code="local.news.title" />
		</div>
		<br>
		<form:input path="title"
			cssStyle="width:60%;height:35px;font: 19px Arial, Helvetica, sans-serif;border: 1px solid #408080;border-radius: 12px;padding: 4px;" />
		<div class="requirements">
			<form:errors path="title" cssClass="error" />
		</div>
		<br>
		<br>


		<div class="label">
			<spring:message code="local.news.brief" />
		</div>
		<br>
		<form:input path="brief"
			cssStyle="width:60%;height:35px;font: 19px Arial, Helvetica, sans-serif;border: 1px solid #408080;border-radius: 12px;padding: 4px;" />
		<div class="requirements">
			<form:errors path="brief" cssClass="error" />
		</div>
		<br>
		<br>


		<div class="label">
			<spring:message code="local.news.content" />
		</div>
		<br>
		<div class="label"></div>
		<form:textarea path="content"
			cssStyle="width:60%;height:200px;font: 18px Arial, Helvetica, sans-serif;border: 1px solid #408080;border-radius: 12px;padding: 4px;" />
		<div class="requirements">
			<form:errors path="content" cssClass="error" />
		</div>
		<br>

		<br>
		<br>
		<input type="submit" value="<spring:message code="local.save"/>"
			class="add-button" />


	</form:form>

</body>
</html>