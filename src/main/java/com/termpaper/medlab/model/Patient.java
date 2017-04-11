package com.termpaper.medlab.model;

public class Patient extends User{
    
    private int id;
    private int id_user;

    public Patient(){
    }

    public Patient(int id, String additional_info, int id_user){
	this.id = id;
	this.id_user = id_user;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId_user(){
        return id_user;
    }

    public void setId_user(int id_user){
        this.id_user = id_user;
    }

}
