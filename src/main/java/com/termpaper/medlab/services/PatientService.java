package com.termpaper.medlab.services;

import com.termpaper.medlab.model.Patient;

public interface PatientService{

    Patient getPatientByUserId(int user_id);

    void addPatientByUserId(Patient patient);

}
