
package com.termpaper.medlab.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.ResearchDao;
import com.termpaper.medlab.model.Research;

@Repository
public class ResearchDaoImpl implements ResearchDao{
    
    private static final String SQL_INSERT_RESEARCH = "INSERT INTO research (cost, date, readiness, code) VALUES (?,?,?,?)";
    private static final String SQL_INSERT_RESEARCH_PATIENT = "INSERT INTO patient_research (id_patient, id_research) VALUES (?, ?)";
    private static final String SQL_SELECT_RESEARCH_BY_ID = null;
    private static final String SQL_UPDATE_READINESS = "UPDATE research SET readiness=? WHERE id=?";
    private static final String SQL_GET_DISABLED_DATES = "SELECT cast(date as date) AS ddate, COUNT(CASE WHEN cast(date as time) BETWEEN '00:00:00' AND '23:59:59' THEN 1 ELSE NULL END) AS t FROM research GROUP by ddate HAVING t>=10";
    private static final String SQL_GET_DISABLED_HRS = "SELECT cast(date as time) AS dtime FROM `research` WHERE cast(date as date)=?";
    private static final String SQL_GET_BY_CODE = "SELECT id, date, readiness, cost, code FROM research WHERE code=?";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void fillAnalisys(int id, int[] analises){
	
    }
    
    @Override
    public void addResearch(Research res, int patient_id){
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
	    
	    @Override
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_RESEARCH, new String[] { "id" });
		ps.setDouble(1, res.getCost());
		ps.setTimestamp(2, res.getDate());
		ps.setTimestamp(3, res.getReadiness());
		ps.setString(4, res.getCode());
		return ps;
	    }
	}, keyHolder);
	res.setId(keyHolder.getKey().intValue());
	
	jdbcTemplate.update(new PreparedStatementCreator(){
	    
	    @Override
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_RESEARCH_PATIENT, new String[] { "id" });
		ps.setInt(1, patient_id);
		ps.setInt(2, res.getId());
		return ps;
	    }
	});
    }
    
    @Override
    public Research getById(int id){
	List<Research> s = jdbcTemplate.query(SQL_SELECT_RESEARCH_BY_ID, new ResearchRowMapper(), id);
	return s.isEmpty() ? null : s.get(0); 
    }
      
private static class ResearchRowMapper implements RowMapper<Research> {
	
	public Research mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Research res = new Research();	    
	    
	    res.setId(rs.getInt("id"));
	    res.setCost(rs.getDouble("cost"));
	    res.setDate(rs.getTimestamp("date"));
	    res.setReadiness(rs.getTimestamp("readiness"));
	    res.setCode(rs.getString("code"));
	    return res;
	}
    }

private static class DatesRowMapper implements RowMapper<Date> {
	
	public Date mapRow(ResultSet rs, int rowNum) throws SQLException {	 
	    Date d = rs.getDate("ddate");
	    return d;
	}
   }
private static class HrsRowMapper implements RowMapper<Time> {
	
	public Time mapRow(ResultSet rs, int rowNum) throws SQLException {	 
	    Time t = rs.getTime("dtime");
	    return t;
	}
}

@Override
public void setReadiness(Research res){
    jdbcTemplate.update(SQL_UPDATE_READINESS, res.getReadiness(), res.getId());
}

@Override
public List<Date> getResearchDisabledDates(){
    return jdbcTemplate.query(SQL_GET_DISABLED_DATES, new DatesRowMapper());
}

@Override
public List<Time> getResearchDisabledHrs(String date){
    return jdbcTemplate.query(SQL_GET_DISABLED_HRS, new HrsRowMapper(), date);
}

@Override
public Research getResultsByCode(String code){
    List<Research> list = jdbcTemplate.query(SQL_GET_BY_CODE, new ResearchRowMapper(), code);
    return list.isEmpty() ? null : list.get(0);
}

}