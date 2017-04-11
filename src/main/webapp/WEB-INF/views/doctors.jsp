<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
		<body>
		
		<div class="container-fluid">
		
<div class="row">
  <div class="col-md-8 col-md-offset-2">
  <div class="alert alert-success" role="alert" hidden="" id="submitCollector"></div>
  <div class="alert alert-danger" role="alert" hidden="" id="errorCollector"></div>
  <input type="hidden" id="user_id" value="${user_id}">
  <input type="hidden" id="docTemp" value="">
  <c:forEach items="${doctors}" var="doctor" varStatus="item">
    <div class="thumbnail doctors-style">
    <div class="row container-fluid">
    <div class="caption col-md-4">
    <div class="panel panel-default">
    <div class="panel-body">
    <div class="container-fluid">
      <img class="img" src="data:image/jpg;base64,${doctor.photo}"  alt="...">
    </div>
      <div>
      <p>Name: ${doctor.name}</p>
      <p>Sex: ${doctor.sex}</p>
      <p>Age: ${doctor.age}</p>
      <p>Phone: ${doctor.phone}</p>
      <p>Email: ${doctor.email}</p>
      </div>
     </div>
     </div>
        <p><button value="${doctor.id_user}" onclick="set(this)" class="btn btn-custom col-md-12" role="button" data-toggle="modal" data-target="#addDialog">get Consultation</button></p>
     </div>
     
      <div class="caption col-md-8">
      <div class="panel panel-default">
      <div class="panel-body">
      <h3>Education:</h3>
      <p>${doctor.education}</p>
      <h3>Expirience:</h3>
      <p>${doctor.expirience}</p>
      <h3>Additional information:</h3>
      <p>${doctor.additional_info}</p>
      
   		
   		
  </div>
</div>
        </div>        
     </div>     
    </div>
	</c:forEach>
    
  </div>
</div>	

<div class="container">
<nav class="col-md-offset-4" aria-label="...">
<ul class="pagination">
  <li class="previous"><a href="#">Previous</a></li>
  <c:forEach begin="1" end="${size}" step="1" varStatus="item">
  <c:choose>
  <c:when test="${item.index==page}">
  <li class="active"><a href="<c:url value="./${item.index}"/>">${item.index}</a></li>
  </c:when>
  <c:otherwise>
  <li><a href="<c:url value="./${item.index}"/>">${item.index}</a></li>
  </c:otherwise>
  </c:choose>
  </c:forEach>
  <li class="next"><a href="#">Next</a></li>
</ul>
</nav>
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
                   
                <form id="new-consultation">
               
               <div class="row">
            <div class="form-group col-md-6">
            <label class="control-label">Choose comfortable date: </label>
                <div class='input-group date' id='date'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-black glyphicon-calendar"></span>
                    </span>
                    <input type='text' id="dateValue" name="dateValue" class="form-control" />
                </div>
            </div>
            <div class="form-group col-md-6">
            	<label class="control-label">Choose comfortable time: </label>
                <div class='input-group date' id='time'>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-black glyphicon-calendar"></span>
                    </span>
                    <input type='text' id="timeValue" name="timeValue" class="form-control" />
                </div>
            </div>
            </div>
              
            <div class="panel panel-default">
  			
  			<div class="panel-body">
  			
  			<div class="panel panel-default">
  			<div class="panel-heading">Doctor`s name: </div>
  			<div class="panel-body"><h5 id="docName"></h5></div>
			</div>
  				
  			<div class="panel panel-default">
  			<div class="panel-heading"><div>Doctor`s contact phone: </div></div>
  			<div class="panel-body"><h5 id="docNum"></h5></div>
			</div>
			
			<div class="panel panel-default">
  			<div class="panel-heading"><div>Doctor`s contact email: </div></div>
  			<div class="panel-body"><h5 id="docEmail"></h5></div>
			</div>
			
			<div class="panel panel-default">
  			<div class="panel-heading"><div>total cost: </div></div>
  			<div class="panel-body"><h5 id="docCost"></h5></div>
  			<input type="hidden" id="consCost" value="">
  			<input type="hidden" id="docId" value="">
			</div>
  				<p></p>
  				
  				<p></p>
  				
  				<p></p>
  					
  				<p></p>
  			</div>
			</div> 
                
                <div class="modal-footer">
                
                <div class="row">
                
					<button class="btn btn-custom col-md-10" type="submit" onclick="onCreateVerifyConsultation()">Confirm</button>
                    <button type="button" class="btn btn-custom-danger" data-dismiss="modal">Close</button>
                
                </div>
                </div>
                
				
				</form>
                </div>
            </div>

        </div>
    </div>
		</body>
    <script type="text/javascript">
        
        $('#date').datetimepicker({                    
        	useCurrent: true,
        	locale: 'ru',
        	daysOfWeekDisabled: [0, 6],
        	//defaultDate: "04/1/2017",
        	//defaultDate: moment(),
			minDate : moment(),
			maxDate : moment().add(1, "month"),
			format: 'YYYY-MM-DD'
        });
        $('#time').datetimepicker({                    
        	useCurrent: false,
        	locale: 'ru',
			//disabledHours: [0,1,2,3,4,5,6,7,18,19,20,21,22,23],
			//enabledHours: setHoursForDate(date),
			format: 'HH:00'
        });
          
        $("#date").on("dp.change", function (e) {
        	//setHoursForDate($("#dateValue").val());
        	setHoursForDate(e.date);
        });
        $("#date").on("dp.show", function (e) {
        	getDisabledDaysOfWeek();
        	//setHoursForDate($("#dateValue").val());
        	//setHoursForDate(e.date);
        	//disabledDates: getDisabledDates(),
        	getDisabledDates();
        });
            
        </script>
        <script type="text/javascript" src="<c:url value="/resources/utils/ajaxHeader.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/pages/doctor_info.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/pages/datetimepicker_utils_doctor.js"/>"></script>

	</html>