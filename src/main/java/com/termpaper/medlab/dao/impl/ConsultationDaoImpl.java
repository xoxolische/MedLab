package com.termpaper.medlab.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.ConsultationDao;
import com.termpaper.medlab.model.Consultation;

@Repository
public class ConsultationDaoImpl implements ConsultationDao{
    
    private static final String SQL_INSERT_CONSULTATION = "INSERT INTO consultation (id_patient, id_doctor, date, time, cost) VALUES (?, ?, ?, ?, ?)";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void add(Consultation consultation){
	
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
	    
	    @Override
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_CONSULTATION, new String[] { "id" });
		ps.setInt(1, consultation.getId_patient());
		ps.setInt(2, consultation.getId_doctor());
		ps.setDate(3, consultation.getDate());
		ps.setTime(4, consultation.getTime());
		ps.setDouble(5, consultation.getCost());
		return ps;
	    }
	}, keyHolder);
	consultation.setId(keyHolder.getKey().intValue());
	
    }
    
}
