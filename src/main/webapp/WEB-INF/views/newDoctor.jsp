<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<div class="container-fluid">

<div class="row">
<div class="panel panel-default col-md-8 col-md-offset-2">
  <div class="panel-body">
	<div class="container-fluid">
	<div class="alert alert-success" role="alert" hidden="" id="submitCollector"></div>
	<div class="alert alert-danger" role="alert" hidden="" id="errorCollector"></div>
<form id="myForm" name="myForm">
<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
<div class="row">
<div class="container-fluid">
<div class="col-md-10 col-md-offset-1">

  <div class="form-group"  id="emailGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
    <input id="email" type="email" class="form-control" name="email" placeholder="Email" required>
    
  </div>
  </div>
  
  <div class="form-group" id="passwordGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
    <input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
  </div>
  </div>
  
  <div class="form-group" id="nameGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="name" type="text" class="form-control" name="name" placeholder="Name" required>
  </div>
  </div>
  
  <div class="form-group" id="surnameGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="surname" type="text" class="form-control" name="surname" placeholder="Surname" required>
  </div>
  </div>
  
  <div class="form-group" id="patronymicGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="patronymic" type="text" class="form-control" name="patronymic" placeholder="Patronymic" required>
  </div>
  </div>
  
  <div class="form-group" id="phoneGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
    <input id="phone" type="text" class="form-control" name="phone" placeholder="Phone number" required>
  </div>
  </div>
  
  <div class="form-group" id="sexGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-pushpin"></i></span>
    <select class="form-control" id="sex" name="sex">
    <option selected value="default">Choose doctor`s gender...</option>
    <option value="male">Male</option>
    <option value="female">Female</option>
  </select>
  </div>
  </div>
  
  
  <div class="form-group" id="ageGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-leaf"></i></span>
    <input id="age" type="text" class="form-control" name="age" placeholder="Age" required>
  </div>
  </div>
  
  <div class="input-group">
  <div class="panel panel-default">
  <div class="panel-body">
<label class="checkbox-inline"><input type="checkbox" name="days" value="1">Monday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="2">Tuesday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="3">Wednesday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="4">Thursday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="5">Friday</label>
  </div>
  </div> 
</div>
  <div id="daysGroup">
</div>


<div class="form-group" id="fromToGroup">
<div class="form-group" id="fromGroup">
  <div class="input-group">
    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>    
  <select class="form-control" id="from" name="from" required>
  <option selected value="default">From</option>
<c:forEach begin="8" end="16" step="1" varStatus="loop">    
    <option><c:out value="${loop.index}"/>:00</option>
</c:forEach>
  </select>
  </div>
  </div>
  
  <div class="form-group" id="toGroup">
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>  
  <select class="form-control" id="to" name="to" required>
  <option selected value="default">To</option>
  <c:forEach begin="9" end="17" step="1" varStatus="loop">    
    <option><c:out value="${loop.index}"/>:00</option>
</c:forEach>
  </select>
  </div>
  </div>
  </div>
  
  
  

</div>
</div>
</div>
  <button type="submit" onclick="onCreateVerify()" class="btn btn-success">New Doctor</button>
</form>
	</div>
</div>
</div>
</div>
	
	
</div>
<script type="text/javascript" src="<c:url value="/resources/pages/newDoctor.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/utils/ajaxHeader.js"/>"></script>
</body>
</html>