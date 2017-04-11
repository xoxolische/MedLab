package com.termpaper.medlab.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.model.Schedule;
import com.termpaper.medlab.model.User;
import com.termpaper.medlab.model.dto.DoctorDto;
import com.termpaper.medlab.services.ConsultationService;
import com.termpaper.medlab.services.DoctorService;
import com.termpaper.medlab.services.PatientService;
import com.termpaper.medlab.services.ScheduleService;
import com.termpaper.medlab.services.UserService;

@RestController
public class DoctorRestController{
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    ConsultationService consultationService;
    
    @Autowired
    UserService userService;
    
    @Autowired 
    ScheduleService scheduleService;
    
    @SuppressWarnings("rawtypes")
    @PostMapping("doctor/api/update-doctor")
    public ResponseEntity updateDoctor(@RequestBody DoctorDto newDoctor){
	User user = new User(newDoctor);
	
	try{	    
	    userService.updateUser(user);
	    Schedule schedule = new Schedule(newDoctor);
	    scheduleService.setScheduleId(schedule);
	    if(schedule.getId()==0){		
		scheduleService.add(schedule);
	    }
	    
	    Doctor doctor = new Doctor(newDoctor, schedule);
	    doctorService.update(doctor);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body("User`s profile updated successfuly!");
	}
	catch(Exception e){
	    
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failed to update!");
	}
    }
    
}
