<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
		<body>
		
<div class="container">
    <div class="row">
        <div class='col-md-8 col-md-offset-2'>
        <div class="alert alert-success" role="alert" hidden="" id="submitCollector"></div>
		<div class="alert alert-danger" role="alert" hidden="" id="errorCollector"></div>
        <form id="newResearch">
        <input type="hidden" id="patient_id" value="${patient_id}">
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
   	<table class="table">
    <thead>
      <tr class="bg-table-head">
        <th>#</th>
        <th>Name</th>
        <th>Cost</th>
        <th><span class="glyphicon glyphicon-black glyphicon-ok"></span></th>        
      </tr>
    </thead>
    <tbody>
        <c:forEach items="${analysis}" var="item" begin="0" varStatus="iterator">
        <tr class="bg-table">
        <td>${iterator.index+1}</td>
        <td>${item.name}</td>
        <td>${item.cost}</td>
        <td><input type="checkbox" id="${item.id}" name="name" value="${item.cost}"></td>
      	</tr>
		</c:forEach>
		<tr><td></td></tr>
		<tr class="bg-table-footer">
		<td>Total cost:</td>
		<td></td>
		<td><div id="total">0.0</div></td>
		<td>$</td>
		</tr>
    </tbody>
  </table>
            
            <button type="submit" onclick="onCreateVerify()" class="col-md-12 btn btn-custom-bright">Make Research</button>
        </form>
            
        </div>
    </div>
  </div>
    
        <script type="text/javascript">
            
        $('#date').datetimepicker({                    
        	locale: 'ru',
        	daysOfWeekDisabled: [0, 6],
			minDate : moment(),
			maxDate : moment().add(1, "month"),
			format: 'YYYY-MM-DD'
        });
        $('#time').datetimepicker({                    
        	locale: 'ru',
			disabledHours: [0,1,2,3,4,5,6,7,18,19,20,21,22,23],
			format: 'HH:00'
        });
            
        $("#date").on("dp.show", function (e) {
        	getDisabledDatesResearch();
        }); 
        $("#time").on("dp.show", function (e) {
        	getDisabledHrsResearch($("#dateValue").val());
        });
            
        </script>
<script type="text/javascript" src="<c:url value="/resources/utils/ajaxHeader.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/pages/newResearch.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/pages/datetimepicker_utils_doctor.js"/>"></script>
		</body>
	</html>