<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
	<body>
	<div class="container-fluid top100">
	<div class="row">
	<div class="col-md-4 col-md-offset-4">
	<div class="panel panel-default login-style">
  <div class="panel-body">
  <div class="wrapper">	
		<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
<div class="container-fluid">
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>
	<div class="input-group">
		<span class="input-group-addon"><i class="glyphicon glyphicon-black glyphicon-user"></i></span>
		<input id="username" type="text" class="form-control" name="username" placeholder="Email" required>
	</div>
	
	<div class="input-group top5">
		<span class="input-group-addon"><i class="glyphicon glyphicon-black glyphicon-lock"></i></span>
		<input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
	</div>
	<div class="top5">
	<button type="submit" class="btn btn-custom col-md-12 col-xs-12 col-sm-12">log in</button>
	</div>
	<a href="#"class="login-text">Create new account</a>
</div>
</form>
	</div>
</div>
	</div>
	</div>
	</div>
	</div>
	

</body>
</html>