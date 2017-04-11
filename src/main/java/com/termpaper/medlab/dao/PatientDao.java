package com.termpaper.medlab.dao;

import com.termpaper.medlab.model.Patient;

public interface PatientDao{

    Patient getPatientByUserId(int user_id);

    void addPatientByUserId(Patient patient);

}
