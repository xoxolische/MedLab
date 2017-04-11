package com.termpaper.medlab.model.dto;

public class UserDto{
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String gender;
    private int age;
    private String days;
    private String hours;
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getPatronymic(){
        return patronymic;
    }
    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
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

}
