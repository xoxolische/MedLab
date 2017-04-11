package com.termpaper.medlab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.AnalysisDao;
import com.termpaper.medlab.model.Analysis;
import com.termpaper.medlab.model.Research;

@Repository
public class AnalysisDaoImpl implements AnalysisDao{
    
    private static final String SQL_SELECT_ALL_ANALYSIS = "SELECT id, name, cost, readiness FROM analysis";
    
    private static final String SQL_SELECT_ANALYSIS_FROM_RESEARCH_ID = "SELECT analysis.id, analysis.name, analysis.cost, analysis.readiness FROM analysis WHERE analysis.id IN (SELECT id_analysis FROM research_analysis WHERE research_analysis.id_research=?)";
    
    private static final String SQL_INSERT_ANALYSIS_TO_RESEARCH = "INSERT INTO research_analysis (id_research, id_analysis) VALUES (?, ?)";
    
    @Autowired 
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Analysis> getAllAnalysis(){
	return jdbcTemplate.query(SQL_SELECT_ALL_ANALYSIS, new AnalysisRowMapper());
    }
    
    @Override
    public List<Analysis> getAnalysisByResearchId(int research_id){
	return jdbcTemplate.query(SQL_SELECT_ANALYSIS_FROM_RESEARCH_ID, new AnalysisRowMapper(), research_id);
    }
    
    private static class AnalysisRowMapper implements RowMapper<Analysis> {
	
	public Analysis mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Analysis a = new Analysis();	    
	    
	    a.setId(rs.getInt("id"));
	    a.setName(rs.getString("name"));
	    a.setCost(rs.getDouble("cost"));
	    a.setReadiness(rs.getInt("readiness"));
	    
	    return a;
	}
    }
    
    @Override
    public void addAnalysisToResearch(Research res){
	/*
	jdbcTemplate.update(new PreparedStatementCreator() {	    
	    
	    @Override
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_ANALYSIS_TO_RESEARCH, new String[] { "id" });
		int[] analysis = res.getAnalysis();
		for(int i=0; i<analysis.length; i++){
		    
		    ps.setInt(1, res.getId());
		    ps.setInt(2, analysis[i]);
		    ps.addBatch();
		}
		return ps.executeBatch();
	    }
	 
	});
	 */
	jdbcTemplate.batchUpdate(SQL_INSERT_ANALYSIS_TO_RESEARCH, new BatchPreparedStatementSetter() {
	    
	    public void setValues(PreparedStatement ps, int i)
		    throws SQLException {
		ps.setInt(1, res.getId());
		ps.setInt(2, res.getAnalysis()[i]);
	    }
	    
	    public int getBatchSize() {
		return res.getAnalysis().length;
	    }
	});
    } 
    
}
