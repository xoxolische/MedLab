package com.termpaper.medlab.model;

import com.termpaper.medlab.model.dto.UserDto;

public class Schedule{

    private int id;
    private String days;
    private String hours;
    
    public Schedule(){
	
    }
    
    public Schedule(String days, String hours){
	this.days = days;
	this.hours = hours;
    }
    public Schedule(UserDto userDto){
	this.days = userDto.getDays().trim();
	this.hours = userDto.getHours().trim();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDays(){
        return days;
    }

    public void setDays(String days){
        this.days = days;
    }

    public String getHours(){
        return hours;
    }

    public void setHours(String hours){
        this.hours = hours;
    }
    public boolean hasId(){
	if(this.id != 0){	    
	    return true;
	}
	return false;
    }
    
    public int[] parseDays(){
	int[] days = new int[this.days.trim().length()];
	String[] s_days = this.days.trim().split(" ");
	
	for(int i=0; i<s_days.length; i++){
	    days[i] = Integer.parseInt(s_days[i]);
	}
	return days;
    }
    public String parseStrDays(){
	String[] s_days = this.days.trim().split(" ");
	String s = "";
	for(int i=0; i<s_days.length; i++){
	    s = s + s_days[i];
	}
	return s;
    }
    
    public String[] parseHours(){
	return this.hours.trim().split(" ");
    }

}
