<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<div class="container">
	<div class="container">
		<div class="row">
			<div class="panel panel-default panel-bright col-md-10 col-md-offset-1">
			<div class="panel-body">
			<div class="alert alert-success" role="alert" hidden="" id="submitCollectorDone"></div>
			<div class="alert alert-danger" role="alert" hidden="" id="errorCollectorFail"></div>
    			<form id="myForm" name="myForm" >
				<input type="hidden" id="id_user" value="${doctor.id_user}">
				<!-- row1 user photo -->
				<div class="container-fluid" id="photo_load">
				<div class="row">
				<div class="container-fluid col-md-4">
				<div class="thumbnail thumbnail-bright" id="test">
      				<img id="avatar" src="data:image/jpg;base64,${doctor.photo}" alt="...">
    			</div>
					<div>
      					 <button type="button" class="btn btn-custom-bright col-md-12" data-toggle="modal" data-target="#addDialog">Change photo</button>
      				</div>
    			</div>
    			<!-- row1 user data -->
    			<div class="container-fluid col-md-3">
    			 <div class="form-group" id="nameGroup">
   <div class="form-group"  id="emailGroup">
  <div class="input-group">
    
    <input id="email" type="hidden" class="form-control" name="email" placeholder="Email" value="${doctor.email}" required>
    
  </div>
  </div>
  
  <div class="form-group" id="passwordGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-lock"></i></span>
    <input id="password" type="text" class="form-control" name="password" placeholder="Password" value="${doctor.password}" required>
  </div>
  </div>
  <div class="input-group">
    <span class="input-group-addon  bg-theme"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="name" type="text" class="form-control" name="name" placeholder="Name" value="${d_name[0]}" required>
  </div>
  </div>
  
  
  
  <div class="form-group" id="surnameGroup">
  <div class="input-group">
    <span class="input-group-addon  bg-theme"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="surname" type="text" class="form-control" name="surname" placeholder="Surname" value="${d_name[1]}" required>
  </div>
  </div>
  
  <div class="form-group" id="patronymicGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-flag"></i></span>
    <input id="patronymic" type="text" class="form-control" name="patronymic" placeholder="Patronymic" value="${d_name[2]}" required>
  </div>
  </div>
  <div class="form-group" id="phoneGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-phone"></i></span>
    <input id="phone" type="text" class="form-control" name="phone" placeholder="Phone number" value="${doctor.phone}" required>
  </div>
  </div>
  
  
  <div class="form-group" id="sexGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-pushpin"></i></span>
    <select class="form-control" id="sex" name="sex">
    <option value="male">Male</option>    
    <option value="female">Female</option>
  </select>
  </div>
  </div>

  
  
  <div class="form-group" id="ageGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-hourglass"></i></span>
    <input id="age" type="text" class="form-control" name="age" placeholder="Age" value="${doctor.age}" required>
  </div>
  </div>
  
  <div class="form-group" id="priceGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-usd"></i></span>
    <input id="price" type="text" class="form-control" name="price" placeholder="Consultation price" value="${doctor.consultation_cost}" required>
  </div>
  </div>
  
    			</div>
    	<!-- end of user data -->	
    	<!-- start of text fields -->
    	<div class="container-fluid col-md-5">
    	<div class="input-group top-buffer">
    	<span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-education"></i></span>
  		<textarea class="form-control" rows="6" id="education" placeholder="A few words about your education..."></textarea>
		</div>
    	
    	<div class="input-group top-buffer15">
    	<span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-certificate"></i></span>
  		<textarea class="form-control" rows="6" id="expirience" placeholder="Describe your expirience..."></textarea>
		</div>
    		
    	<div class="input-group top-buffer15">
    	<span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-comment"></i></span>
  		<textarea class="form-control" rows="6" id="additional_info" placeholder="Some additional information..."></textarea>
		</div>
    	</div>	
    	<!-- end of textfields -->
    			</div>
    			<!-- end of row1 -->
    			</div>
 <div class="row top-buffer20">
 <div class="container-fluid"> 			
 <div class="input-group col-md-12">
 
 <div class="panel panel-default panel-bright">
  <div class="panel-heading panel-heading-custom">Your personal Schedule</div>
 <div class="panel-body">
 <div class="container-fluid col-md-12">
 <div class="panel panel-default panel-bright">
 <div class="panel-body col-md-offset-3">
<label class="checkbox-inline"><input type="checkbox" name="days" value="1">Monday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="2">Tuesday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="3">Wednesday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="4">Thursday</label>
<label class="checkbox-inline"><input type="checkbox" name="days" value="5">Friday</label>
 </div>
 </div>
 <div class="container-fluid col-md-12">
 
<div class="form-group" id="fromGroup">
  <div class="input-group">
    <span class="input-group-addon bg-theme"><i class="glyphicon glyphicon-calendar"></i></span>    
  <select class="form-control" id="from" name="from" required>
  <option value="default">From</option>
<c:forEach begin="8" end="16" step="1" varStatus="loop">    
    <option><c:out value="${loop.index}"/>:00</option>
</c:forEach>
  </select>
 
   
  <select class="form-control" id="to" name="to" required>
  <option value="default">To</option>
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
</div>
</div>
</div>
<div class="row">
<button type="submit" onclick="onCreateVerify()" class="btn btn-custom-bright col-md-12">Apply changes</button>
</div>
</form>
				</div>
    			
			</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="addDialog" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                	<div class="alert alert-danger" role="alert" hidden="" id="errorCollector"></div>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                   
                <form id="new-file" enctype="multipart/form-data">
                <div class="row">
                <div class="form-group col-md-8 col-md-offset-2">
    			<h4>
    				Select image to upload(.jpeg, .jpg, .png, .jpe):
    			</h4>
    			<input type="file" name="file" id="file">
    			<input type="hidden" id="user_id" value="${sessionScope.id}">
                </div>
                </div>
                <div class="modal-footer">
                
                <div class="row">
                
					<button class="btn btn-custom col-md-10" type="submit" onclick="onCreateVerifyPhoto()">Upload</button>
                    <button type="button" class="btn btn-custom-danger" data-dismiss="modal">Close</button>
                </div>
                
                
                </div>
				
				</form>
                </div>
            </div>

        </div>
    </div>
<script type="text/javascript" src="<c:url value="/resources/utils/ajaxHeader.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/utils/upload.js"/>"></script>
  <script type="text/javascript">
  			$(document).ready(function(){
            	var s1 = "${doctor.sex}";
            	var s2 = "male";
            	var fromArr = $("#from").val();
            	var toArr = $("#from").val();
            	
            	if(s1===s2){
            		$("#sex").val('male');
            	}else{
            		$("#sex").val('female');
            	}
            	console.log("${doctor.education}");
            	if("${doctor.education}"){
            		$("#education").val("${doctor.education}");            		
            	}
            	if("${doctor.expirience}"){
            		$("#expirience").val("${doctor.expirience}");            		
            	}
            	if("${doctor.additional_info}"){
            		$("#additional_info").val("${doctor.additional_info}");            		
            	}
            	
            	$("#from").val("${hours[0]}");
            	$("#to").val("${hours[1]}");
            	
            	var days = "${days}".split("");
            	            	
            	$('#myForm').find(':checkbox[name^="days"]').each(function () {
                    $(this).prop("checked", ($.inArray($(this).val(), days) != -1));
                });
            	
            	});
 </script>
 <script type="text/javascript" src="<c:url value="/resources/pages/profileDoctor.js"/>"></script>
</body>
</html>