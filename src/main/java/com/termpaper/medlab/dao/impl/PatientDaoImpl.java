package com.termpaper.medlab.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.PatientDao;
import com.termpaper.medlab.model.Patient;

@Repository
public class PatientDaoImpl implements PatientDao{
    
    private static final String SQL_SELECT_PATIENT = "SELECT patient.id_user, patient.id, users.id, users.email, users.password, users.name, users.sex, users.age, users.phone, users.photo, standart.photo, roles.role FROM patient INNER JOIN users ON patient.id_user=users.id INNER JOIN roles ON users.id_role=roles.id INNER JOIN standart ON standart.id=1";

    private static final String SQL_SELECT_PATIENT_BY_USER_ID = SQL_SELECT_PATIENT + " WHERE users.id=?";

    protected static final String SQL_INSERT_PATIENT = "INSERT INTO patient (id_user) VALUES (?)";
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Patient getPatientByUserId(int user_id){
	List<Patient> s = jdbcTemplate.query(SQL_SELECT_PATIENT_BY_USER_ID, new PatientRowMapper(), user_id);
	return s.isEmpty() ? null : s.get(0); 
    }
    @Override
    public void addPatientByUserId(Patient patient){
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_PATIENT, new String[] { "id" });
			ps.setInt(1, patient.getId_user());
			return ps;
		}
	}, keyHolder);
	patient.setId(keyHolder.getKey().intValue());
    }
    
    
    
private static class PatientRowMapper implements RowMapper<Patient> {
	
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Patient p = new Patient();
	    
	    p.setName(rs.getString("users.name"));
	    p.setEmail(rs.getString("users.email"));
	    p.setPassword(rs.getString("users.password"));
	    p.setAge(rs.getInt("users.age"));
	    p.setPhone(rs.getString("users.phone"));
	    p.setSex(rs.getString("users.sex"));
	    p.setRole(rs.getString("roles.role"));
	    
	    rs.getBlob("users.photo");
	    if(rs.wasNull()){		
		try {
		    p.setPhoto(blobAsString(rs.getBlob("standart.photo")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	    }
	    else{
		try {
		    p.setPhoto(blobAsString(rs.getBlob("users.photo")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	    }   
	    
	    p.setId(rs.getInt("patient.id"));
	    p.setId_user(rs.getInt("users.id"));
	    return p;
	}
    }
private static String blobAsString(java.sql.Blob blob2) throws SQLException, UnsupportedEncodingException{
	java.sql.Blob blob = blob2;
	byte[] encodeBase64 = Base64.encodeBase64(blob.getBytes(1, (int) blob.length()));
	blob.free();
	return new String(encodeBase64, "UTF-8");
}
    
}
