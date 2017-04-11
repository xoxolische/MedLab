
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
import com.termpaper.medlab.model.dto.UserDto;
import com.termpaper.medlab.services.DoctorService;
import com.termpaper.medlab.services.ScheduleService;
import com.termpaper.medlab.services.UserService;



@RestController
public class AdminRestController{
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    UserService userService;
    
    @Autowired 
    ScheduleService scheduleService;
    
    
   
    @SuppressWarnings("rawtypes")
    @PostMapping("admin/api/new-doctor")
    public ResponseEntity addNewDoctor(@RequestBody UserDto newDoctor){
	User user = new User(newDoctor);
	try{	    
	    userService.addNewUserDoctor(user);
	    Schedule schedule = new Schedule(newDoctor);
	    scheduleService.setScheduleId(schedule);
	    if(schedule.getId()==0){		
		scheduleService.add(schedule);
	    }
	    
	    Doctor doctor = new Doctor(user, schedule);
	    doctor.setConsultation_cost(0);
	    doctorService.add(doctor);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfuly!");
	}
	catch(Exception e){
	    
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists!");
	}
    }
}