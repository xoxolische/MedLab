package com.termpaper.medlab.model.dto;


public class ConsultationDto{
    private int id;
    private int id_patient;
    private int id_doctor;
    private String datetime;
    private double cost;

    public ConsultationDto(){
    }

    public ConsultationDto(int id, int id_patient, int id_doctor, String datetime, double cost){
	this.id = id;
	this.id_patient = id_patient;
	this.id_doctor = id_doctor;
	this.datetime = datetime;
	this.cost = cost;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId_patient(){
        return id_patient;
    }

    public void setId_patient(int id_patient){
        this.id_patient = id_patient;
    }

    public int getId_doctor(){
        return id_doctor;
    }

    public void setId_doctor(int id_doctor){
        this.id_doctor = id_doctor;
    }

    public String getDatetime(){
        return datetime;
    }

    public void setDatetime(String datetime){
        this.datetime = datetime;
    }

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

}
