package com.termpaper.medlab.services.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.AnalysisDao;
import com.termpaper.medlab.dao.DoctorDao;
import com.termpaper.medlab.dao.ResearchDao;
import com.termpaper.medlab.model.Analysis;
import com.termpaper.medlab.model.Research;
import com.termpaper.medlab.services.ResearchService;

@Service
@Transactional
public class ResearchServiceImpl implements ResearchService{

    @Autowired 
    ResearchDao researchDao;
    
    @Autowired
    AnalysisDao analysisDao;
    
    @Autowired
    DoctorDao doctorDao;
    
    @Override
    public void addResearch(Research res, int patient_id){
	researchDao.addResearch(res, patient_id);
    }

    @Override
    public void fillAnalisys(int id, int[] analises){
	researchDao.fillAnalisys(id, analises);
    }

    @Override
    public List<Analysis> getAnalysis(){
	return analysisDao.getAllAnalysis();
    }

    @Override
    public void setReadiness(Research res){
	List<Analysis> l = analysisDao.getAnalysisByResearchId(res.getId());
	long s = 0;
	for(Analysis a : l){
	    s = s + a.getReadiness()*60*60*1000;
	}
	res.setReadiness(new Timestamp(res.getDate().getTime() + s));
	researchDao.setReadiness(res);
    }

    @Override
    public void addAnalysisToResearch(Research res){
	analysisDao.addAnalysisToResearch(res);
    }

    @Override
    public List<Date> getResearchDisabledDates(){
	return researchDao.getResearchDisabledDates();
    }

    @Override
    public List<Time> getResearchHours(String date){
	return researchDao.getResearchDisabledHrs(date);
    }

}
