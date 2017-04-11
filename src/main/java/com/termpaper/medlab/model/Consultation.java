package com.termpaper.medlab.model;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.termpaper.medlab.model.dto.ConsultationDto;


public class Consultation{
    private int id;
    private int id_patient;
    private int id_doctor;
    private Date date;
    private Time time;
    private double cost;

    public Consultation(){
    }

    public Consultation(int id, int id_patient, int id_doctor, Date date, Time time, double cost){
	this.id = id;
	this.id_patient = id_patient;
	this.id_doctor = id_doctor;
	this.date = date;
	this.time = time;
	this.cost = cost;
    }

    public Consultation(ConsultationDto cons) throws ParseException{
	this.id_doctor = cons.getId_doctor();
	this.cost= cons.getCost();
	setDate(cons.getDatetime());
    }

    public void setDate(String date) throws ParseException{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	java.util.Date parsedDate = dateFormat.parse(date.split(" ")[0]);
	Time parsedTime = new Time(timeFormat.parse(date.split(" ")[1]).getTime());
	java.sql.Date sql = new java.sql.Date(parsedDate.getTime());
	this.date = sql;
	this.time = parsedTime;
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

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time){
        this.time = time;
    }

}
