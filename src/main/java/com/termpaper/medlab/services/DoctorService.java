package com.termpaper.medlab.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.termpaper.medlab.model.Doctor;

public interface DoctorService{

    public Doctor getDoctorById(int id);
    
    public List<Doctor> getAllDoctorsButThis(int user_id);

    public void add(Doctor doctor);

    public void update(Doctor doctor);

    public List<Doctor> getDoctorsByPage(int limit, int offset, int attribute);

    public List<Doctor> getAllDoctors();

    public List<Time> getDoctorHours(int user_id, String string);

    public List<Date> getDoctorDisabledDates(int user_id, int i);
     
    
}
