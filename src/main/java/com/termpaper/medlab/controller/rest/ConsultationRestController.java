package com.termpaper.medlab.controller.rest;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.termpaper.medlab.model.Consultation;
import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.model.Schedule;
import com.termpaper.medlab.model.dto.ConsultationDto;
import com.termpaper.medlab.services.ConsultationService;
import com.termpaper.medlab.services.DoctorService;
import com.termpaper.medlab.services.PatientService;
import com.termpaper.medlab.services.ScheduleService;
import com.termpaper.medlab.services.UserService;


@RestController
public class ConsultationRestController{
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    ConsultationService consultationService;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ScheduleService scheduleService;
    
    
    @GetMapping({"doctor/doctors/api/getDoc/{doctorId}", "patient/doctors/api/getDoc/{doctorId}"})
    public Doctor getDoctor(@PathVariable int doctorId) {
	return doctorService.getDoctorById(doctorId);
    }
    
    
    @GetMapping({"doctor/doctors/api/getHrs/{date}/{id}", "patient/doctors/api/getHrs/{date}/{id}"})
    public int[] getHrs(@PathVariable("date") String date, @PathVariable("id") int user_id) {
	List<Time> taken = doctorService.getDoctorHours(user_id, new Date(Long.parseLong(date)).toString().split(" ")[0]);
	Schedule diapason = scheduleService.getByUserId(user_id);
	int from = Integer.parseInt(diapason.getHours().split(" ")[0].split(":")[0]);
	int to = Integer.parseInt(diapason.getHours().split(" ")[1].split(":")[0]);
	int[] hrs = new int[to-from];
	int counter = 0;
	for(int i = from; i< (to); i++){
	    hrs[counter++] = i;
	}
	Set<Integer> goodHrs = new HashSet<Integer>();
	for(int i : hrs){
	    goodHrs.add(i);
	}
	Set<Integer> badHrs = new HashSet<Integer>();
	for(Time t : taken){
	    badHrs.add(Integer.parseInt(t.toString().split(":")[0]));
	}
	goodHrs.removeAll(badHrs);
	
	counter=0;
	hrs = new int[goodHrs.size()];
	for(Integer i : goodHrs){
	    hrs[counter++] = i;
	}
	return hrs;
    }
    
    @GetMapping({"doctor/doctors/api/getDates/{id}", "patient/doctors/api/getDates/{id}"})
    public String[] getDates(@PathVariable("id") int user_id) {
	Schedule schedule = scheduleService.getByUserId(user_id);
	List<Date> l = doctorService.getDoctorDisabledDates(user_id, (Integer.parseInt(schedule.getHours().split(" ")[1].split(":")[0]) - Integer.parseInt(schedule.getHours().split(" ")[0].split(":")[0])));
	String[] disabledDates = new String[l.size()];
	int counter = 0;
	for(Date d : l){
	    disabledDates[counter++] = d.toString();
	}
	return disabledDates;
    }
    
    @GetMapping({"doctor/doctors/api/getDaysOfWeek/{id}", "patient/doctors/api/getDaysOfWeek/{id}"})
    public int[] getDaysOfWeek(@PathVariable("id") int user_id) {
	Schedule schedule = scheduleService.getByUserId(user_id);
	int[] dis = new int[] {0,1,2,3,4,5,6};
	int[] daysOfWeek = new int[schedule.getDays().split(" ").length];
	int counter = 0;
	for(String i : schedule.getDays().split(" ")){
	    daysOfWeek[counter++] = Integer.parseInt(i);
	}
	Set<Integer> goodDays = new HashSet<Integer>();
	for(int i : daysOfWeek){
	    goodDays.add(i);
	}
	Set<Integer> badDays = new HashSet<Integer>();
	for(int i : dis){
	    badDays.add(i);
	}
	badDays.removeAll(goodDays);
	dis = new int[badDays.size()];
	counter = 0;
	for(int i : badDays){
	    dis[counter++] = i;
	}
	return dis;
    }    
    
    @PostMapping({"doctor/doctors/api/new-consultation", "patient/doctors/api/new-consultation"})
    public String newCons(@RequestBody ConsultationDto cons) throws ParseException{
	Consultation consultation = new Consultation(cons);
	consultation.setId_patient(patientService.getPatientByUserId(cons.getId_patient()).getId());
	consultationService.addConsultation(consultation);
	return "doctors"; 	 
    }
    
}
