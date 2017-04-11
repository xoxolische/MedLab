package com.termpaper.medlab.services.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.DoctorDao;
import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.services.DoctorService;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{
    @Autowired 
    DoctorDao doctorDao;

    @Override
    public Doctor getDoctorById(int id){
	return doctorDao.getById(id);
    }

    @Override
    public List<Doctor> getAllDoctorsButThis(int user_id){
	return doctorDao.getDoctorsButThis(user_id);
    }

    @Override
    public void add(Doctor doctor){
	doctorDao.add(doctor);
    }

    @Override
    public void update(Doctor doctor){
	doctorDao.update(doctor);
    }

    @Override
    public List<Doctor> getDoctorsByPage(int limit, int offset, int attribute){
	return doctorDao.getDoctorsByPage(limit, offset, attribute);
    }

    @Override
    public List<Doctor> getAllDoctors(){
	return doctorDao.getAllDoctors();
    }

    @Override
    public List<Time> getDoctorHours(int user_id, String string){
	return doctorDao.getDoctorHours(user_id, string);
    }

    @Override
    public List<Date> getDoctorDisabledDates(int user_id, int i){
	return doctorDao.getDoctorDisabledDates(user_id, i);
    }

}
