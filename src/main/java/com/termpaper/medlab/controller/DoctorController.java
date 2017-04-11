
package com.termpaper.medlab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.model.Patient;
import com.termpaper.medlab.model.Schedule;
import com.termpaper.medlab.services.DoctorService;
import com.termpaper.medlab.services.PatientService;
import com.termpaper.medlab.services.ResearchService;
import com.termpaper.medlab.services.ScheduleService;
import com.termpaper.medlab.services.UserService;


@Controller
@SessionAttributes("id")
@RequestMapping("doctor")
public class DoctorController{
    
    @Autowired
    UserService userService;
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    ScheduleService scheduleService;
    
    @Autowired
    ResearchService researchService;
    
    @Autowired
    PatientService patientService;
    
    
    @GetMapping({"", "home", "doctor"})
    public String doctorHomePage(Model model, HttpSession session) throws IOException {
	Patient p = patientService.getPatientByUserId((int) session.getAttribute("id"));
	if(p==null){
	    p = new Patient();
	    p.setId_user((int) session.getAttribute("id"));
	    patientService.addPatientByUserId(p);
	    p = patientService.getPatientByUserId((int) session.getAttribute("id"));
	}
	return "info";
    }
    
    @GetMapping("/doctors/{page}")
    public String consultations(Model model, HttpSession session, @PathVariable int page) throws IOException {
	int limit=2;  
	int offset = (page-1) * limit;
	List<Doctor> list = doctorService.getAllDoctorsButThis((int) session.getAttribute("id"));
	model.addAttribute("doctors", doctorService.getDoctorsByPage(limit, offset, (int) session.getAttribute("id")));  
	model.addAttribute("size", list.size()/limit);
	model.addAttribute("user_id", (int) session.getAttribute("id"));
	model.addAttribute("page", page);
	return "doctors";
    }
    
    @GetMapping("doctor-profile")
    public String profile(Model model, HttpSession session) throws IOException {
	//doctorService.getDoctorById((int)session.getAttribute("id"));
	Doctor doctor = doctorService.getDoctorById((int)session.getAttribute("id"));
	Schedule schedule = scheduleService.getById(doctor.getShcedule_id());
	model.addAttribute("doctor", doctor);
	model.addAttribute("days", schedule.parseStrDays());
	model.addAttribute("hours", schedule.parseHours());
	model.addAttribute("d_name", doctor.getName().split(" "));
	return "profileDoctor";
    }
    
    @GetMapping("new-research")
    public String research(Model model, HttpSession session){
	Patient p = patientService.getPatientByUserId((int) session.getAttribute("id"));
	if(p==null){
	    p = new Patient();
	    p.setId_user((int) session.getAttribute("id"));
	    patientService.addPatientByUserId(p);
	    p = patientService.getPatientByUserId((int) session.getAttribute("id"));
	}
	model.addAttribute("analysis", researchService.getAnalysis());
	model.addAttribute("patient_id", p.getId());
	return "newResearch";
    }
}