package com.termpaper.medlab.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.ScheduleDao;
import com.termpaper.medlab.model.Schedule;


@Repository
public class ScheduleDaoImpl implements ScheduleDao{
    
    private static final String SQL_INSERT_SCHEDULE = "INSERT INTO doctor_schedule (days, hours) VALUES (?, ?)";
    private static final String SQL_SELECT_SCHEDULE_BY_DAYS_HOURS = "SELECT id, days, hours FROM doctor_schedule WHERE days=? AND hours=?";
    private static final String SQL_SELECT_SCHEDULE = "SELECT doctor_schedule.id, days, hours FROM doctor_schedule";
    private static final String SQL_SELECT_SCHEDULE_BY_ID = SQL_SELECT_SCHEDULE + " where id=?";
    private static final String SQL_SELECT_SCHEDULE_BY_USER_ID = SQL_SELECT_SCHEDULE + " INNER JOIN doctor ON doctor_schedule.id = doctor.schedule_id WHERE id_user=?";
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Schedule getByUserId(int user_id){
	List<Schedule> s = jdbcTemplate.query(SQL_SELECT_SCHEDULE_BY_USER_ID, new ScheduleRowMapper(), user_id);
	return s.isEmpty() ? null : s.get(0); 
    }
    
    @Override
    public Schedule getById(int id){
	List<Schedule> s = jdbcTemplate.query(SQL_SELECT_SCHEDULE_BY_ID, new ScheduleRowMapper(), id);
	return s.isEmpty() ? null : s.get(0); 
    }
    
    @Override
    public void setScheduleId(Schedule schedule){
	List<Schedule> s = jdbcTemplate.query(SQL_SELECT_SCHEDULE_BY_DAYS_HOURS, new ScheduleRowMapper(), schedule.getDays(), schedule.getHours());
	if(!s.isEmpty())
	    schedule.setId(s.get(0).getId()); 
    }

    public void add(Schedule schedule){
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_SCHEDULE, new String[] { "id" });
			ps.setString(1, schedule.getDays());
			ps.setString(2, schedule.getHours());
			return ps;
		}
	}, keyHolder);
	schedule.setId(keyHolder.getKey().intValue());
    }

    @Override
    public Schedule getSchedule(Schedule schedule){
	List<Schedule> s = jdbcTemplate.query(SQL_SELECT_SCHEDULE_BY_DAYS_HOURS, new ScheduleRowMapper(), schedule.getDays(), schedule.getHours());
	return s.isEmpty() ? null : s.get(0); 
    }
    
private static class ScheduleRowMapper implements RowMapper<Schedule> {
	
	public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Schedule schedule = new Schedule();
	    schedule.setId(rs.getInt("id"));
	    schedule.setDays(rs.getString("days"));
	    schedule.setHours(rs.getString("hours"));
	    
	    return schedule;
	}
    }

}
