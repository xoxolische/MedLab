package com.termpaper.medlab.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.ConsultationDao;
import com.termpaper.medlab.model.Consultation;
import com.termpaper.medlab.services.ConsultationService;
@Service
@Transactional
public class ConsultationServiceImpl implements ConsultationService{
    
    @Autowired
    ConsultationDao consultationDao;

    @Override
    public void addConsultation(Consultation consultation){
	consultationDao.add(consultation);
    }

}
