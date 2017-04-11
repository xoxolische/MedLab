package com.termpaper.medlab.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.termpaper.medlab.model.Analysis;
import com.termpaper.medlab.model.Research;

public interface ResearchService{

    void addResearch(Research res, int patient_id);

    void fillAnalisys(int id, int[] analises);

    List<Analysis> getAnalysis();

    void setReadiness(Research res);

    void addAnalysisToResearch(Research res);

    List<Date> getResearchDisabledDates();

    List<Time> getResearchHours(String date);

}
