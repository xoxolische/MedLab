function onCreateVerifyPhoto() {
    $('#new-file').validate({
        rules: {
            file: {
            	required: true,
            	validFormat: true
            }
        },
        messages: {
            file:{
            	required: "Please select a photo to upload!",
            	validFormat: "Please select a photo of correct format!"
            }
        },
        errorPlacement: function(error, element) {
        	$(function () {
        		$("#errorCollector").html(error);
        		  $('#errorCollector').fadeIn('slow', function () {
        		    $(this).delay(3000).fadeOut('slow');
        		  });
        	});
	        		//error.insertAfter("#errorHandler");
        },
        submitHandler: function(form) {        		
        		onCreateActionPhoto();
        }
    });
}
// this is called in case of creating a new item
function onCreateActionPhoto() {
	
	var formData = new FormData( $('#new-file')[0]);
	var user_id = $("#user_id").val();
	//console.log($('#new-file')[0]);
    $.ajax({
    	url: "api/"+user_id+"/upload-photo",
    	type: 'POST',
    	data: formData,
    	cache: false,
        contentType: false,
        processData: false
    	}).done(function (data) {
    		//console.log(data);
    		

    		//document.getElementById("avatar").src = "data:image/jpg," + toUTF8Array(s);
    		//$('#test').load('/MedLab/doctor/doctor-update-photo-div');
    		$('#addDialog').modal('hide');
        	$('html, body').animate({ scrollTop: 0 }, 'fast');
        	$("#submitCollectorDone").fadeIn("slow", function () {
        		$("#submitCollectorDone").html("Photo updated successfuly! Reload page!");
        		$(this).delay(3000).fadeOut('slow');
        	});
        }).fail(function (data) {
        	$('#addDialog').modal('hide');
        	$('html, body').animate({ scrollTop: 0 }, 'fast');
        	$(function () {
      		  $('#errorCollectorFail').fadeIn('slow', function () {
      			$("#errorCollectorFail").html("Failed to update! "+data.responseText);
      		    $(this).delay(3000).fadeOut('slow');
      		  });
      		});
        });
}

$.validator.addMethod('validFormat', function valid(value, el, param){	
	var ext = value.split('.').pop().toLowerCase();
	if($.inArray(ext, ['jpe','png','jpg','jpeg']) == -1) {
		return false;
	}else{
		return true;
	}
});

function toUTF8Array(str) {
    var utf8 = [];
    for (var i=0; i < str.length; i++) {
        var charcode = str.charCodeAt(i);
        if (charcode < 0x80) utf8.push(charcode);
        else if (charcode < 0x800) {
            utf8.push(0xc0 | (charcode >> 6), 
                      0x80 | (charcode & 0x3f));
        }
        else if (charcode < 0xd800 || charcode >= 0xe000) {
            utf8.push(0xe0 | (charcode >> 12), 
                      0x80 | ((charcode>>6) & 0x3f), 
                      0x80 | (charcode & 0x3f));
        }
        // surrogate pair
        else {
            i++;
            // UTF-16 encodes 0x10000-0x10FFFF by
            // subtracting 0x10000 and splitting the
            // 20 bits of 0x0-0xFFFFF into two halves
            charcode = 0x10000 + (((charcode & 0x3ff)<<10)
                      | (str.charCodeAt(i) & 0x3ff));
            utf8.push(0xf0 | (charcode >>18), 
                      0x80 | ((charcode>>12) & 0x3f), 
                      0x80 | ((charcode>>6) & 0x3f), 
                      0x80 | (charcode & 0x3f));
        }
    }
    return utf8;
}
