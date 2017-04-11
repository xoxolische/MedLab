package com.termpaper.medlab.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.PatientDao;
import com.termpaper.medlab.model.Patient;
import com.termpaper.medlab.services.PatientService;
@Service
@Transactional
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientDao patientDao;
    
    @Override
    public Patient getPatientByUserId(int user_id){
	return patientDao.getPatientByUserId(user_id);
    }

    @Override
    public void addPatientByUserId(Patient patient){
	patientDao.addPatientByUserId(patient);
    }

}
