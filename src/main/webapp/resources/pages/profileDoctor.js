$.validator.addMethod('greaterThan', function (value, el, param) {
    return parseInt(value) > parseInt(param.value);
});

$.validator.addMethod('isNotValue', function (value, el, param) {
	return value != param;
});

$.validator.addMethod('notZero', function (value, el, param) {
	return value != 0 || value !=0.0;
});

function onCreateVerify() {	
		  // Initialize form validation on the registration form.
		  // It has the name attribute "registration"
		  $("#myForm").validate({
		    // Specify validation rules
		    rules: {
		      // The key name on the left side is the name attribute
		      // of an input field. Validation rules are defined
		      // on the right side
		      email: {
		        required: true,
		        // Specify that email should be validated
		        // by the built-in "email" rule
		        email: true
		      },
		      price:{
		    	  required: true,
		    	  number:true,
		    	  notZero: true
		      },
		      password: {
		        required: true,
		      },
		      name: "required",
		      surname: "required",
		      patronymic: "required",
		      phone: {
		    	  required: true,
		    	  number: true		    	  
		      },
		      sex: {
		    	  isNotValue: "default"
		      },
		      age: {
		    	  required: true,
		    	  number: true
		      },
		      days: "required",
		      from: {
		    	  required: true,
		    	  isNotValue: "default"
		      },
		      to: {
		    	  required: true,
		    	  greaterThan: from,
		    	  isNotValue: "default"
		      }	      
		    },
		    // Specify validation error messages
		    messages: {
		    	email: {
		    		required: "Please fill the Email field!",
		    		email: "Please fill the Email field correctly!"
		    	},
		    	price: {
		    		required: "Please fill the Price field!",
		    		number: "Please the Price field correctly!",
		    		notZero: "Please fill the Price field with value greater than 0!"
		    	},
		    	password: {
		    		required: "Please fill the Password field!"
		    	},
		    	name: "Please fill the Name field!",
		    	surname: "Please fill the Surname field!",
		    	patronymic: "Please fill the Patronymic field!",
		    	phone: {
		    		required: "Please fill the PhoneNumber field!",
		    		number: "Please fill the PhoneNumber field correctly!"
		    	},
		    	sex: {
		    		isNotValue: "Please choose the Gender!",
		    	},
		    	age: {
		    		required: "Please fill the Age field!",
		    		number: "Please fill the Age field correctly!"
		    	},
		    	days: "Please choose atleast one day!",
		    	from: "Choose FROM hours",
		    	to: {
		    		required: "Choose TO hours",
		    		greaterThan: "TO value must be greater than FROM!"
		    	}
		    },
		    errorPlacement: function(error, element) {
		        var attribute = element.attr("name");
		        switch (attribute) {
		        	case "email":
		        		error.insertAfter("#emailGroup");
		        		break;
		        	case "password":
		        		error.insertAfter("#passwordGroup");
		        		break;
		        	case "name":
		        		error.insertAfter("#nameGroup");
		        		break;
		        	case "surname":
		        		error.insertAfter("#surnameGroup");
		        		break;
		        	case "patronymic":
		        		error.insertAfter("#patronymicGroup");
		        		break;
		        	case "phone":
		        		error.insertAfter("#phoneGroup");
		        		break;
		        	case "sex":
		        		error.insertAfter("#sexGroup");
		        		break;
		        	case "age":
		        		error.insertAfter("#ageGroup");
		        		break;
		        	case "days":
		        		error.insertAfter("#daysGroup");
		        		break;
		        	case "from":
		        		error.insertAfter("#fromGroup");
		        		break;
		        	case "to":
		        		error.insertAfter("#toGroup");
		        		break;
		        	case "price":
		        		error.insertAfter("#priceGroup");
		        		break;
		        
		        default:
		      }
		      },
		    // Make sure the form is submitted to the destination defined
		    // in the "action" attribute of the form when valid
		    submitHandler: function(form) {
		      //form.submit();
		      onCreateAction();
		    }
		  });
		
}
// this is called in case of creating a new item
function onCreateAction() {
	
	var item = {
			email: $("#email").val(),
			password: $("#password").val(),
		    name: $("#name").val(),
		    surname: $("#surname").val(),
		    patronymic: $("#patronymic").val(),
		    phone: $("#phone").val(),
		    gender: $("#sex").val(),
		    age: $("#age").val(),
		    days: getDaysSelected(),
		    hours: getHoursSelected(),
		    
		    price: $("#price").val(),
		    education: $("#education").val(),
		    expirience: $("#expirience").val(),
		    additional_info: $("#additional_info").val(),
		    id_user: $("#id_user").val()
	    };
	$.ajax({
    	url: '/MedLab/doctor/api/update-doctor',
    	type: 'POST',
    	data: JSON.stringify(item),
    	contentType: "application/json",
        dataType: 'json'
        }).done(function (data) {
        	$('html, body').animate({ scrollTop: 0 }, 'fast');
        	$("#submitCollectorDone").fadeIn("slow", function () {
        		$("#submitCollectorDone").html(data);
        		$(this).delay(3000).fadeOut('slow');
        	});
        }).fail(function (data) {
        	$('html, body').animate({ scrollTop: 0 }, 'fast');
        	$(function () {
      		  $('#errorCollectorFail').fadeIn('slow', function () {
      			$("#errorCollectorFail").html(data.responseText);
      		    $(this).delay(3000).fadeOut('slow');
      		  });
      		});
        });
}
function getDaysSelected() {
	var finalString = '';
	$("input:checkbox[name=days]:checked").each(function(){
	    finalString = finalString + $(this).val() + ' ';
	});
	return finalString;
}
function getHoursSelected() {
	return $("#from").val() +" "+ $("#to").val();
}