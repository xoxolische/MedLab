package com.termpaper.medlab.model.dto;

public class DoctorDto extends UserDto{
    
    private double price;
    private String education;
    private String expirience;
    private String additional_info;
    private int id_user;
    
    public DoctorDto(){
	
    }
    
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
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
    
    

}
