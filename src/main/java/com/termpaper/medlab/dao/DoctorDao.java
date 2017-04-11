package com.termpaper.medlab.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.model.dto.DoctorDto;

public interface DoctorDao{

    public Doctor getById(int id);
    
    public List<Doctor> getDoctorsButThis(int user_id);

    public void add(Doctor doctor);

    public void update(Doctor doctor);

    List<Doctor> getDoctorsByPage(int limit, int offset, int id);

    List<DoctorDto> getDoctorDto(int user_id);

    public List<Doctor> getAllDoctors();

    List<Time> getDoctorHours(int user_id, String date);

    List<Date> getDoctorDisabledDates(int user_id, int hrs);
    
}
