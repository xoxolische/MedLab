var arr = [];

function getEnabledDates() {
    $.ajax({								
    	url: '/MedLab/patient/api/doctors/'+1+'/disabledDates',
    	dataType: 'json',
        async: false,
        success: function (data) {
                 arr=data;
                 console.log(data);
              }
           }); 
    return arr;
};