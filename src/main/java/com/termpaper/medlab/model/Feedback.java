package com.termpaper.medlab.model;

import java.sql.Date;

public class Feedback{

    private int id;
    private int id_patient;
    private int id_doctor;
    private Date datetime;
    private int mark;
    private String feedback;
    
    public Feedback(){
    }

    public Feedback(int id, int id_patient, int id_doctor, Date datetime,
	    int mark, String feedback){
	this.id = id;
	this.id_patient = id_patient;
	this.id_doctor = id_doctor;
	this.datetime = datetime;
	this.mark = mark;
	this.feedback = feedback;
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

    public Date getDatetime(){
        return datetime;
    }

    public void setDatetime(Date datetime){
        this.datetime = datetime;
    }

    public int getMark(){
        return mark;
    }

    public void setMark(int mark){
        this.mark = mark;
    }

    public String getFeedback(){
        return feedback;
    }

    public void setFeedback(String feedback){
        this.feedback = feedback;
    }

}
