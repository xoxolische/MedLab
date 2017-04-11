package com.termpaper.medlab.model;


public class Analysis{

    private int id;
    private String name;
    private double cost;
    private int readiness;
    
    public Analysis(){
    }
    
    public Analysis(int id, String name, double cost, int readiness){
	this.id = id;
	this.name = name;
	this.cost = cost;
	this.readiness = readiness;
    }
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public int getReadiness(){
        return readiness;
    }

    public void setReadiness(int readiness){
        this.readiness = readiness;
    }



}
