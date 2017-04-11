<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>

	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<!-- ... -->
	<link rel="icon" href="<c:url value="/resources/style/img/ml_logo.png"/>">
 	<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap.min.css"/>" >
	<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap-theme.min.css"/>" >
	<link rel="stylesheet" href="<c:url value="/resources/style/styles.css"/>" >
	<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap-datetimepicker.css"/>" >
	
	<script src="<c:url value="/resources/js/moment.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-2.2.4.js"/>"></script>
	
	<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	
	<script src="<c:url value="/resources/js/bootstrap-datetimepicker.js"/>"></script>
	<script src="<c:url value="/resources/utils/utils.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/ru.js"/>"></script>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	

	<title>MedLab</title>
</head>
<body>
<form action="/MedLab/logout" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

	<nav class="navbar navbar-inverse navbar-custom navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>                        
				</button>
				<a href="<c:url value="/home"/>" class="navbar-brand">MedLab</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
				
					<security:authorize access="hasRole('ADMIN')">
					<li><a href="<c:url value="/admin/home"/>">Home</a></li>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
					<li><a href="<c:url value="/admin/new-doctor"/>">New Doctor</a></li>
					</security:authorize>
					<security:authorize access="hasRole('DOCTOR')">
					<li><a href="<c:url value="/doctor/"/>">Home</a></li>
					</security:authorize>
					<security:authorize access="hasRole('DOCTOR')">
					<li><a href="<c:url value="/doctor/doctors/1"/>">Get Consultation</a></li>
					</security:authorize>
					<security:authorize access="hasRole('DOCTOR')">
					<li><a href="<c:url value="/doctor/new-research"/>">Get Research</a></li>
					</security:authorize>
					<security:authorize access="hasRole('DOCTOR')">
					<li><a href="<c:url value="/doctor/my-consultations"/>">My Consultations</a></li>
					</security:authorize>
					<security:authorize access="hasRole('PATIENT')">
					<li><a href="<c:url value="/patient/doctors/1"/>">Get Consultation</a></li>
					</security:authorize>
					<security:authorize access="hasRole('PATIENT')">
					<li><a href="<c:url value="/patient/new-research"/>">Get Research</a></li>
					</security:authorize>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="isAnonymous()">
					<li><a href="./registration"><span class="glyphicon glyphicon-user"></span> Registration</a></li>
					</security:authorize>
					<security:authorize access="hasRole('DOCTOR')">
					<li><a href="<c:url value="/doctor/doctor-profile"/>"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
					</security:authorize>
					<security:authorize access="hasRole('PATIENT')">
					<li><a href="<c:url value="/patient/patient-profile"/>"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
					</security:authorize>
					<security:authorize access="isAnonymous()">
					<li><a href="./login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
					<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>
