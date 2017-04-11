
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
import com.termpaper.medlab.services.DoctorService;
import com.termpaper.medlab.services.PatientService;
import com.termpaper.medlab.services.ResearchService;
import com.termpaper.medlab.services.UserService;


@Controller
@SessionAttributes("id")
@RequestMapping("patient")
public class PatientController{
    
    @Autowired
    UserService userService;
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    ResearchService researchService;	
    
    @GetMapping({"", "home", "patient"})
    public String patientHomePage(Model model, HttpSession session){
	return "info";
    }
    
    @GetMapping("new-research")
    public String research(Model model, HttpSession session){
	Patient p = patientService.getPatientByUserId((int) session.getAttribute("id"));
	model.addAttribute("analysis", researchService.getAnalysis());
	model.addAttribute("patient_id", p.getId());
	return "newResearch";
    }
    
    @GetMapping("/doctors/{page}")
    public String consultations(Model model, HttpSession session, @PathVariable int page) throws IOException {
	int limit=1;  
	int offset = (page-1) * limit;
	List<Doctor> list = doctorService.getAllDoctors();
	model.addAttribute("doctors", doctorService.getDoctorsByPage(limit, offset, (int) session.getAttribute("id")));  
	model.addAttribute("size", list.size()/limit);
	model.addAttribute("user_id", (int) session.getAttribute("id"));
	model.addAttribute("page", page);
	return "patientDoctors";
    }
}