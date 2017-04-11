function onCreateVerify() {	
		  $("#newResearch").validate({
		    rules: {		     
		    	name: "required",
		    	dateValue: "required",
		    	timeValue: "required"
		    },
		    messages: {
		    	name: "Please choose atleast one test!",
		    	dateValue: "Please choose the Date!",
		    	timeValue: "Please choose the Time!"
		    },
		    errorPlacement: function(error, element) {
		        var attribute = element.attr("name");
		        switch (attribute) {
		        	case "name":
		        		$('html, body').animate({ scrollTop: 0 }, 'fast');
		            	$("#errorCollector").fadeIn("slow", function () {
		            		$("#errorCollector").html(error);
		            		$(this).delay(3000).fadeOut('slow');
		            	});
		        		break;
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
			patient_id: $("#patient_id").val(),
			date: $("#dateValue").val() + " " + $("#timeValue").val(),
			analises: getTestsSelected(),
			total_cost: $("#total").text()			
	    };
	$.ajax({
    	url: './api/new-research',
    	type: 'POST',
    	data: JSON.stringify(item),
    	contentType: "application/json",
        dataType: 'json'
        }).done(function (data) {
        	$('html, body').animate({ scrollTop: 0 }, 'fast');
        	$("#submitCollector").fadeIn("slow", function () {
        		$("#submitCollector").html("You were registered for research successfuly!");
        		$(this).delay(3000).fadeOut('slow');
        	});
        		
        	$("#newResearch").each(function(){
        		this.reset();
        	});
        	$("#total").html(0);
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
$("input:checkbox[name=name]").change(function () {
	var current = parseFloat($("#total").text());
	if($(this).is(":checked")){		
		//console.log(typeof());
		var temp = current + parseFloat($(this).val());
		$("#total").html(temp.toFixed(2));
	}else{
		var temp = current - parseFloat($(this).val());
		$("#total").html(temp.toFixed(2));
	}
});
function getTestsSelected(){
	var final = [];
	var index = 0;
	$("input:checkbox[name=name]:checked").each(function(){
		final[index] = $(this).attr("id");
		index++;
	});
	return final;
}