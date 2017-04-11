package com.termpaper.medlab.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.termpaper.medlab.model.Research;

public interface ResearchDao{

    void fillAnalisys(int id, int[] analises);

    void addResearch(Research res, int patient_id);

    Research getById(int id);

    void setReadiness(Research res);

    List<Date> getResearchDisabledDates();

    List<Time> getResearchDisabledHrs(String date);

    Research getResultsByCode(String code);

}
