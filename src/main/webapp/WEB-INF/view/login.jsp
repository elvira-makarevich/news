<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />

<style>
input[type=text], input[type=password] {
	padding: 7px 12px 7px 6px;
	border-radius: 6px;
	cursor: pointer;
	width: 25%;
	margin-top: 6px;
	box-sizing: border-box;
	border: 1px solid #555;
	outline: none;
	font-size: 16px;
}

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
</style>
</head>
<body>
	<a href="news/list/"><spring:message code="local.on.main.page" /></a>
	<span style="float: right"> <a href="?lang=en"><spring:message
				code="local.en" /></a> | <a href="?lang=ru"><spring:message
				code="local.ru" /></a>
	</span>
	<br>
	<br>
	<div class="header">
		<spring:message code="local.authorization" />
	</div>
	<br>
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">

		<!-- Check for login error -->

		<c:if test="${not empty param.error}">
			<span style="color: red; font-size: 17px;"> <spring:message
					code="local.error.authenticate.user" />
			</span>
		</c:if>
		
		<c:if test="${not empty param.messageRegistrationOK}">
			<span style="color: #408080; font-size: 17px;"> <spring:message
					code="local.login.message.registration.ok" />
			</span>
		</c:if>

		<p>
			<input type="text"
				placeholder="<spring:message
				code="local.username" />"
				name="username" /><br>
			<form:errors path="userName" cssClass="error" />
		</p>

		<p>
			<input type="password"
				placeholder="<spring:message
				code="local.password" />"
				name="password" /><br>
			<form:errors path="password" cssClass="error" />
		</p>

		<br>
		<div style="margin-top: 10px">
			<input type="submit"
				value="<spring:message
				code="local.login" />" />
		</div>
	</form:form>

</body>

</html>