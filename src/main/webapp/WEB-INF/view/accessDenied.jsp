<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>

<head>
<title>Access Denied</title>



</head>

<body>

	<span style="float: right"> <a href="?lang=en"><spring:message
				code="local.en" /></a> | <a href="?lang=ru"><spring:message
				code="local.ru" /></a>
	</span>

	<h2>
		<spring:message code="local.access.denied" />
	</h2>

	<hr>


	<a href="${pageContext.request.contextPath}/"><spring:message
			code="local.on.main.page" /></a>
</body>

</html>