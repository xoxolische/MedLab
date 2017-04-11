package com.termpaper.medlab.model.dto;

import java.sql.Time;

public class ResearchDto{
    
    int id;
    int patient_id;
    String date;
    Time readiness;
    int[] analises;
    double total_cost;
    

    public ResearchDto(){	
    }
    
    public ResearchDto(int id, int patient_id, String date, int[] analises, double total_cost){
	this.id = id;
	this.patient_id = patient_id;
	this.date = date;
	this.analises = analises;
	this.total_cost = total_cost;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + date.hashCode();
	result = prime * result + patient_id;
	return result;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPatient_id(){
        return patient_id;
    }

    public void setPatient_id(int patient_id){
        this.patient_id = patient_id;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Time getReadiness(){
        return readiness;
    }

    public void setReadiness(Time readiness){
        this.readiness = readiness;
    }

    public int[] getAnalises(){
        return analises;
    }

    public void setAnalises(int[] analises){
        this.analises = analises;
    }

    public double getTotal_cost(){
        return total_cost;
    }

    public void setTotal_cost(double total_cost){
        this.total_cost = total_cost;
    }

}
