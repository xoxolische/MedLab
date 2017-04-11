function setHoursForDate(date){
	$.ajax({
		url: './api/getHrs/'+date+'/'+document.getElementById("docTemp").value,
		dataType: 'json'
	}).done(function (data) {
		console.log(data);
		$('#time').data("DateTimePicker").enabledHours(data);
	}).fail(function () {
		console.log("fail");
	});	
}
function getDisabledHrsResearch(data){
	$.ajax({
		url: './api/getHrsResearch/'+data,
		dataType: 'json'
	}).done(function (data) {
		console.log(data);
		$('#time').data("DateTimePicker").disabledHours(data);
	}).fail(function () {
		console.log("fail");
	});	
}
function getDisabledDates(){
	$.ajax({
		url: './api/getDates/'+document.getElementById("docTemp").value,
		dataType: 'json'
	}).done(function (data) {
		$('#date').data("DateTimePicker").disabledDates(data);
	}).fail(function () {
		console.log("fail");
	});	
}

function getDisabledDatesResearch(){
	$.ajax({
		url: './api/getDatesResearch/'+document.getElementById("patient_id").value,
		dataType: 'json'
	}).done(function (data) {
		console.log(data);
		$('#date').data("DateTimePicker").disabledDates(data);
	}).fail(function () {
		console.log("fail");
	});	
}

function getDisabledDaysOfWeek(){
	$.ajax({
		url: './api/getDaysOfWeek/'+document.getElementById("docTemp").value,
		dataType: 'json'
	}).done(function (data) {
		console.log(data);
		$('#date').data("DateTimePicker").daysOfWeekDisabled(data);
	}).fail(function () {
		console.log("fail");
	});	
}