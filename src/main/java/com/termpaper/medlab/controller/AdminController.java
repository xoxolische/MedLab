package com.termpaper.medlab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")
@RequestMapping("admin")
public class AdminController{
    
    @GetMapping("new-doctor")
    public String newDoctor(){
	return "newDoctor";
    }
    
    @GetMapping({"home", "", "/"})
    public String home(){
	return "info";
    }

}
