<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<div class="container-fluid top120">
	<div class="row">
	<div class="alert alert-info col-md-6 col-md-offset-3" role="alert" hidden="" id="result"><div id="res"></div></div>
	<div class="panel panel-default col-md-6 col-md-offset-3">
  		<div class="panel-body">
  		<form>
  		<div class="form-group row">
  		<input type="text" class="form-control col-md-10" id="code">
		</div>
		<button type="button" class="btn btn-custom col-md-12" onclick="sendCode()">Get By Code</button>
  		</form>
  		</div>
	</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/resources/utils/ajaxHeader.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/pages/codePage.js"/>"></script>
</body>
</html>