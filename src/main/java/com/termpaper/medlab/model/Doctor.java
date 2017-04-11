package com.termpaper.medlab.model;

import com.termpaper.medlab.model.dto.DoctorDto;

public class Doctor extends User{
    
    private int id;
    private String education;
    private String expirience;
    private String additional_info;
    private int id_user;
    private int schedule_id;
    private double consultation_cost;
    
    public Doctor(){
	
    }
    public Doctor(User user, Schedule schedule){
	this.id_user = user.getId();
	this.schedule_id = schedule.getId();
    }

    public Doctor(int id, String education, String expirience, int id_user, int schedule_id){
	this.id = id;
	this.education = education;
	this.expirience = expirience;
	this.id_user = id_user;
	this.schedule_id = schedule_id;
    }

    public Doctor(int id, String education, String expirience,
	    String additional_info, int id_user){
	this.id = id;
	this.education = education;
	this.expirience = expirience;
	this.additional_info = additional_info;
	this.id_user = id_user;
    }
    
    public Doctor(DoctorDto doc, Schedule schedule){
	this.additional_info = doc.getAdditional_info();
	this.consultation_cost = doc.getPrice();
	this.education = doc.getEducation();
	this.expirience = doc.getExpirience();
	this.id_user = doc.getId_user();
	this.schedule_id = schedule.getId();
	
    }
	
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEducation(){
        return education;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public String getExpirience(){
        return expirience;
    }

    public void setExpirience(String expirience){
        this.expirience = expirience;
    }

    public String getAdditional_info(){
        return additional_info;
    }

    public void setAdditional_info(String additional_info){
        this.additional_info = additional_info;
    }

    public int getId_user(){
        return id_user;
    }

    public void setId_user(int id_user){
        this.id_user = id_user;
    }

    public int getShcedule_id(){
        return schedule_id;
    }

    public void setShcedule_id(int shcedule_id){
        this.schedule_id = shcedule_id;
    }
    public double getConsultation_cost(){
        return consultation_cost;
    }
    public void setConsultation_cost(double consultation_cost){
        this.consultation_cost = consultation_cost;
    }

}
