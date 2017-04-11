package com.termpaper.medlab.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.termpaper.medlab.model.dto.ResearchDto;

public class Research{
    
    private int id;
    private double cost;
    private Timestamp date;
    private Timestamp readiness;
    private String code;
    private int[] analysis;

    public Research(){
    }

    public Research(int id, double cost, Timestamp date, Timestamp readiness, String code){
	this.id = id;
	this.cost = cost;
	this.readiness = readiness;
	this.code = code;
	this.date = date;
    }

    public Research(ResearchDto object) throws ParseException{
	this.id = object.getId();
	this.cost = object.getTotal_cost();
	this.code = UUID.randomUUID().toString();
	this.analysis = object.getAnalises();
	this.date = setDate(object.getDate());
    }
   
    
    public Timestamp setDate(String date) throws ParseException{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	Date parsedDate = dateFormat.parse(date);
	return new java.sql.Timestamp(parsedDate.getTime());	
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getCost(){
        return cost;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public int[] getAnalysis(){
        return analysis;
    }

    public void setAnalysis(int[] analysis){
        this.analysis = analysis;
    }

    public Timestamp getDate(){
        return date;
    }

    public void setDate(Timestamp date){
        this.date = date;
    }

    public Timestamp getReadiness(){
        return readiness;
    }

    public void setReadiness(Timestamp readiness){
        this.readiness = readiness;
    }

}
