package com.termpaper.medlab.controller.rest;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.termpaper.medlab.model.Research;
import com.termpaper.medlab.model.dto.ResearchDto;
import com.termpaper.medlab.services.ResearchService;

@RestController
public class ResearchRestController{
    @Autowired
    ResearchService researchService;

    @PostMapping({"doctor/api/new-research", "patient/api/new-research"})
    public String newResearch(@RequestBody ResearchDto object) throws ParseException{
	Research res = new Research(object);
	researchService.addResearch(res, object.getPatient_id());
	researchService.addAnalysisToResearch(res);
	researchService.setReadiness(res);
	return "newResearch";
    }
    
    @GetMapping({"doctor/api/getDatesResearch/{id}", "patient/api/getDatesResearch/{id}"})
    public String[] getDates(@PathVariable("id") int patient_id) {
	List<Date> l = researchService.getResearchDisabledDates();
	String[] disabledDates = new String[l.size()];
	int counter = 0;
	for(Date d : l){
	    disabledDates[counter++] = d.toString();
	}
	return disabledDates;
    }
    
    @SuppressWarnings("deprecation")
    @GetMapping({"doctor/api/getHrsResearch/{date}", "patient/api/getHrsResearch/{date}"})
    public int[] getHrs(@PathVariable("date") String date) {
	List<Time> taken = researchService.getResearchHours(date);
	int[] hrs = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23};
	ArrayList<Integer> fin = new ArrayList<Integer>();
	
	for(Time t : taken){
	    if(!fin.contains(t.getHours())){
		fin.add(t.getHours());
	    }
	}
	for(int i : hrs){
	    if(!fin.contains(i)){
		fin.add(i);
	    }
	}
	hrs = new int[fin.size()];
	int c = 0;
	for(Integer i : fin){
	    hrs[c++] = i;
	}
	return hrs;
    }
}
