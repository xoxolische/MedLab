function sendCode(){
	$.ajax({
        url: 'api/code/'+$("#code").val(),
        dataType: 'json'
    }).done(function (data) {
    	//console.log(data['id']);
    	console.log(data);
    	$("#result").fadeIn("slow", function () {});
    	//$("#res").html(data['id']+'<br>'); 
    	$("#res").html("some info about research with id="+data['id']); 
    }).fail(function () {
        console.log("fail");
    });
	
}