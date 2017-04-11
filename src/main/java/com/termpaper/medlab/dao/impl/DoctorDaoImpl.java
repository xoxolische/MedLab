package com.termpaper.medlab.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.DoctorDao;
import com.termpaper.medlab.model.Doctor;
import com.termpaper.medlab.model.dto.DoctorDto;

@Repository
public class DoctorDaoImpl implements DoctorDao{
    
    private static final String SQL_SELECT_DOCTOR = "SELECT doctor.id_user, doctor.id, doctor.education, doctor.expirience, doctor.additional_info, doctor.consultation_cost, doctor.schedule_id, users.email, users.password, users.name, users.sex, users.age, users.phone, users.photo, standart.photo, roles.role FROM doctor INNER JOIN users ON doctor.id_user=users.id INNER JOIN roles ON users.id_role=roles.id INNER JOIN standart ON standart.id=1 WHERE roles.role='DOCTOR'";
    
    private static final String SQL_SELECT_ALL_DOCTORS_BUT_THIS_ID = SQL_SELECT_DOCTOR + " AND users.id<>?";
    
    private static final String SQL_SELECT_DOCTOR_BY_ID = SQL_SELECT_DOCTOR + " AND users.id = ?";
    
    private static final String SQL_SELECT_PAGING = " limit ? offset ?";
    
    private static final String SQL_INSERT_DOCTOR = "INSERT INTO doctor (schedule_id, id_user) VALUES (?, ?)";
    
    private static final String SQL_UPDATE_DOCTOR = "UPDATE doctor SET schedule_id=?, education=?, expirience=?, additional_info=?, consultation_cost=? WHERE id_user =?";
    
    private static final String SQL_SELECT_ALL_DOCTORS_BUT_THIS_ID_PAGING = SQL_SELECT_ALL_DOCTORS_BUT_THIS_ID + SQL_SELECT_PAGING;
    
    private static final String SQL_SELECT_DOCTOR_DTO = "SELECT doctor.id_user, doctor.id, doctor.education, doctor.expirience, doctor.additional_info, doctor.consultation_cost, doctor.schedule_id, users.email, users.password, users.name, users.sex, users.age, users.phone, users.photo, standart.photo, roles.role, doctor_schedule.days, doctor_schedule.hours FROM doctor INNER JOIN users ON doctor.id_user=users.id INNER JOIN roles ON users.id_role=roles.id INNER JOIN standart ON standart.id=1 INNER JOIN doctor_schedule ON doctor.schedule_id=doctor_schedule.id WHERE roles.role='DOCTOR'";
    
    //private static final String SQL_SELECT_ALL_DOCTORS_DTO_BUT_THIS_ID = SQL_SELECT_DOCTOR_DTO + " AND users.id<>?";
    
    private static final String SQL_SELECT_DOCTOR_DTO_BY_ID = SQL_SELECT_DOCTOR_DTO + " AND users.id = ?";
    
    private static final String SQL_SELECT_DOCTOR_HOURS_BY_ID_DATE = "SELECT time FROM consultation INNER JOIN doctor ON consultation.id_doctor=doctor.id INNER JOIN users ON doctor.id_user=users.id WHERE users.id=? AND date=?";
    
    private static final String SQL_SELECT_DOCTOR_DAYS_DISABLED = "SELECT date, COUNT(time) FROM consultation INNER JOIN doctor ON consultation.id_doctor=doctor.id WHERE doctor.id_user=? GROUP by date HAVING COUNT(time)>=?";
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void update(Doctor doctor){
	jdbcTemplate.update(SQL_UPDATE_DOCTOR, doctor.getShcedule_id(), doctor.getEducation(), doctor.getExpirience(), doctor.getAdditional_info(), doctor.getConsultation_cost(), doctor.getId_user());
    }
    
    @Override
    public List<Doctor> getDoctorsByPage(int limit, int offset, int id){
	return jdbcTemplate.query(SQL_SELECT_ALL_DOCTORS_BUT_THIS_ID_PAGING, new DoctorRowMapper(), id, limit, offset);
    }  
    @Override
    public List<DoctorDto> getDoctorDto(int user_id){
	return jdbcTemplate.query(SQL_SELECT_DOCTOR_DTO_BY_ID, new DoctorDtoRowMapper(), user_id);
    }  
    
    @Override
    public void add(Doctor doctor){
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
	    
	    @Override
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(SQL_INSERT_DOCTOR, new String[] { "id" });
		ps.setInt(1, doctor.getShcedule_id());
		ps.setInt(2, doctor.getId_user());
		return ps;
	    }
	}, keyHolder);
	doctor.setId(keyHolder.getKey().intValue());
    }
    
    @Override
    public Doctor getById(int id){
	List<Doctor> s = jdbcTemplate.query(SQL_SELECT_DOCTOR_BY_ID, new DoctorRowMapper(), id);
	return s.isEmpty() ? null : s.get(0); 
    }
    
    @Override
    public List<Doctor> getDoctorsButThis(int user_id){
	List<Doctor> list = jdbcTemplate.query(SQL_SELECT_ALL_DOCTORS_BUT_THIS_ID, new DoctorRowMapper(), user_id);
	return list;
    }        
    
    private static class DoctorRowMapper implements RowMapper<Doctor> {
	
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Doctor doctor = new Doctor();
	    
	    doctor.setId(rs.getInt("doctor.id"));
	    doctor.setEmail(rs.getString("email"));
	    doctor.setPassword(rs.getString("password"));
	    doctor.setName(rs.getString("name"));
	    doctor.setSex(rs.getString("sex"));
	    doctor.setAge(rs.getInt("age"));
	    doctor.setPhone(rs.getString("phone"));
	    doctor.setRole(rs.getString("role"));
	    doctor.setConsultation_cost(rs.getDouble("consultation_cost"));
	    
	    rs.getBlob("users.photo");
	    if(rs.wasNull()){		
		try {
		    doctor.setPhoto(blobAsString(rs.getBlob("standart.photo")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	    }
	    else{
		try {
		    doctor.setPhoto(blobAsString(rs.getBlob("users.photo")));
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	    }
	    
	    doctor.setEducation(rs.getString("education"));
	    doctor.setExpirience(rs.getString("expirience"));
	    doctor.setAdditional_info(rs.getString("additional_info"));
	    doctor.setId_user(rs.getInt("id_user"));
	    doctor.setShcedule_id(rs.getInt("schedule_id"));
	    
	    return doctor;
	}
    }
    
    private static class DoctorDtoRowMapper implements RowMapper<DoctorDto> {
	
	public DoctorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	    DoctorDto doctor = new DoctorDto();
	    
	    doctor.setEmail(rs.getString("email"));
	    doctor.setPassword(rs.getString("password"));
	    String[] name = rs.getString("name").split(" ");
	    
	    doctor.setName(name[0]);
	    doctor.setSurname(name[1]);
	    doctor.setPatronymic(name[2]);
	    doctor.setPhone(rs.getString("phone"));
	    doctor.setGender(rs.getString("sex"));
	    doctor.setAge(rs.getInt("age"));
	    doctor.setDays(rs.getString("days"));
	    doctor.setHours(rs.getString("hours"));
	    doctor.setPrice(rs.getDouble("consultation_cost"));
	    doctor.setEducation(rs.getString("education"));
	    doctor.setExpirience(rs.getString("expirience"));
	    doctor.setAdditional_info(rs.getString("additional_info"));
	    doctor.setId_user(rs.getInt("id_user"));
	    
	    return doctor;
	}
    }
    
    private static class HoursRowMapper implements RowMapper<Time> {
	
	public Time mapRow(ResultSet rs, int rowNum) throws SQLException {	 
	    Time t = rs.getTime("time");
	    return t;
	}
    }
    
    private static class DatesRowMapper implements RowMapper<Date> {
	
	public Date mapRow(ResultSet rs, int rowNum) throws SQLException {	 
	    Date d = rs.getDate("date");
	    return d;
	}
    }
    
    private static String blobAsString(java.sql.Blob blob2) throws SQLException, UnsupportedEncodingException{
	java.sql.Blob blob = blob2;
	byte[] encodeBase64 = Base64.encodeBase64(blob.getBytes(1, (int) blob.length()));
	blob.free();
	return new String(encodeBase64, "UTF-8");
    }
    @Override
    public List<Time> getDoctorHours(int user_id, String date){
	List<Time> list = jdbcTemplate.query(SQL_SELECT_DOCTOR_HOURS_BY_ID_DATE, new HoursRowMapper(), user_id, date);
	return list;
    }
    @Override
    public List<Date> getDoctorDisabledDates(int user_id, int hrs){
	List<Date> list = jdbcTemplate.query(SQL_SELECT_DOCTOR_DAYS_DISABLED, new DatesRowMapper(), user_id, hrs);
	return list;
    }
    
    @Override
    public List<Doctor> getAllDoctors(){
	List<Doctor> list = jdbcTemplate.query(SQL_SELECT_DOCTOR, new DoctorRowMapper());
	return list;
    }
}
