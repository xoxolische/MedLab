package com.termpaper.medlab.dao;

import java.util.List;

import com.termpaper.medlab.model.Analysis;
import com.termpaper.medlab.model.Research;

public interface AnalysisDao{

    List<Analysis> getAllAnalysis();

    List<Analysis> getAnalysisByResearchId(int id);

    void addAnalysisToResearch(Research res);

}
