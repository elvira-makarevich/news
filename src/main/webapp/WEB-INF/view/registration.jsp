<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />

<style>
.error {
	color: red
}

input[type=text], input[type=email], input[type=password] {
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
.requirements {
	font: 17px Arial, Helvetica, sans-serif;
	padding: 4px;
	width: 65%;
	color:red;
	font-style: italic;
}
</style>

</head>
<body>
	<a href="news/list/"><spring:message code="local.on.main.page" /></a>
	<span style="float: right"> <a href="showRegistrationPage?lang=en"><spring:message
				code="local.en" /></a> | <a href="showRegistrationPage?lang=ru"><spring:message
				code="local.ru" /></a>
	</span>
	<br>
	<br>
	<div class="header">
		<spring:message code="local.registration.page" />
	</div>

	<br>
	<c:if test="${not empty messageErrorLoginOrEmailExists}">
		<span style="color: red;"> <spring:message
				code="local.registration.login.or.email.exists" /><br /> <br />
		</span>
	</c:if>

	<form:form action="${pageContext.request.contextPath}/register"
		modelAttribute="user" method="POST">

		<br>
		<input type="text"
			placeholder="<spring:message
				code="local.registration.firstname" />"
			name="firstName" />
		<br>
		<div class="requirements">
			<form:errors path="firstName" cssClass="error" />
		</div>

		<br>
		<input type="text"
			placeholder="<spring:message
				code="local.registration.lastname" />"
			name="lastName" />
		<br>
		<div class="requirements">
			<form:errors path="lastName" cssClass="error" />
		</div>

		<br>
		<input type="text"
			placeholder="<spring:message
				code="local.username" />"
			name="userName" />
		<br>
		<div class="requirements">
			<form:errors path="userName" cssClass="error" />
		</div>

		<br>
		<input type="email" placeholder="Email"
			name="<spring:message
				code="local.registration.email" />" />
		<br>
		<div class="requirements">
			<form:errors path="email" cssClass="error" />
		</div>


		<br>
		<input type="password"
			placeholder="<spring:message
				code="local.password" />"
			name="password" />
		<br>
		<div class="requirements">
			<form:errors path="password" cssClass="error" />
		</div>

		<br>
		<input type="submit"
			value="<spring:message
					code="local.register"/>  " />


	</form:form>

</body>
</html>