package com.termpaper.medlab.model;

import com.termpaper.medlab.model.dto.UserDto;

public class User{
    
    private int id;
    private String email;
    private String password;
    private String name;
    private String sex;
    private int age;
    private String phone;
    private String photo;
    private String role;

    public User(){
    }

    public User(UserDto userDto){
	this.email = userDto.getEmail();
	this.setPassword(userDto.getPassword());
	this.name = userDto.getName() + " " +userDto.getSurname() + " " + userDto.getPatronymic();
	this.sex = userDto.getGender();
	this.age = userDto.getAge();
	this.phone = userDto.getPhone();
    }
    public User(int id, String email, String password, String name, String sex,
	    int age, String phone, String role){
	this.id = id;
	this.email = email;
	this.password = password;
	this.name = name;
	this.sex = sex;
	this.age = age;
	this.phone = phone;
	this.role = role;
    }

    public User(int id, String email, String password, String name, String sex,
	    int age, String phone, String photo, String role){
	this.id = id;
	this.email = email;
	this.password = password;
	this.name = name;
	this.sex = sex;
	this.age = age;
	this.phone = phone;
	this.photo = photo;
	this.role = role;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

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

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }


}
