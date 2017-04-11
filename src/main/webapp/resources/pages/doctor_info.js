function set(b){
        	var docId = document.getElementById("docTemp");
        	docTemp.value = b.value;
        	getDoc();
        }

function f(){
	return document.getElementById("docTemp").value;
}

function getDoc() {
    $.ajax({
        url: './api/getDoc/'+f(),
        dataType: 'json'
    }).done(function (data) {
    	console.log(data['id']);
    	console.log(data);
        //var object = data;
    	$("#docName").html(data['name']);
    	$("#docNum").html(data['phone']);
    	$("#docEmail").html(data['email']);
    	$("#docCost").html(data['consultation_cost']);
    	$("#docId").val(data['id']);
    	//console.log($("#docId").val());
    	$("#consCost").val(data['consultation_cost']);
    	//document.getElementById("#docNum").value = data['phone'];
    	//document.getElementById("#docEmail").value = data['email'];
    	//document.getElementById("#docCost").value = data['consultation_cost'];
    }).fail(function () {
        console.log("fail");
    });
}


function onCreateVerifyConsultation() {	
	  $("#new-consultation").validate({
	    rules: {		     
	    	dateValue: "required",
	    	timeValue: "required"
	    },
	    messages: {
	    	dateValue: "Please choose the Date!",
	    	timeValue: "Please choose the Time!"
	    },
	    errorPlacement: function(error, element) {
	        var attribute = element.attr("name");
	        switch (attribute) {
	        	case "timeValue":
	        		error.insertAfter("#time");
	        		break;
	        	case "dateValue":
	        		error.insertAfter("#date");
	        		break;
	        	   
	        default:
	      }
	      },
	    submitHandler: function(form) {
	      onCreateAction();
	    }
	  });
	
}
function onCreateAction() {

var item = {

		    id_patient: $("#user_id").val(),
		    id_doctor: $("#docId").val(),
		    datetime: $("#dateValue").val() + " " + $("#timeValue").val(),
		    cost: $("#consCost").val()		
  };
$.ajax({
	url: './api/new-consultation',
	type: 'POST',
	data: JSON.stringify(item),
	contentType: "application/json",
  dataType: 'json'
  }).done(function (data) {
	  /*
	$('#date').datetimepicker('setDate', new Date());
	$('#date').datetimepicker('update');
	$('#date').val('');
	*/
	//$("#date").data('DateTimePicker').setDefault();
	$('#addDialog').modal('toggle');
  	$("#submitCollector").fadeIn("slow", function () {
  		$('html, body').animate({ scrollTop: 0 }, 'fast');
  		$("#submitCollector").html("You were registered for consultation successfuly!");
  		$(this).delay(3000).fadeOut('slow');
  		//$('#date').data("DateTimePicker").defaultDate(moment());
		//$('#timeValue').val("");
  		setTimeout(location.reload.bind(location), 3000);
  	});
  	
  }).fail(function (data) {
  	$('html, body').animate({ scrollTop: 0 }, 'fast');
  	$(function () {
		  $('#errorCollector').fadeIn('slow', function () {
			$("#errorCollector").html(data.responseText);
		    $(this).delay(3000).fadeOut('slow');
		  });
		});
  });
}