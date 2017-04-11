package com.termpaper.medlab.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.ScheduleDao;
import com.termpaper.medlab.model.Schedule;
import com.termpaper.medlab.services.ScheduleService;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleDao scheduleDao;

    @Override
    public void setScheduleId(Schedule schedule){
	scheduleDao.setScheduleId(schedule);
    }

    @Override
    public void add(Schedule schedule){
	scheduleDao.add(schedule);
    }

    @Override
    public Schedule getById(int id){
	return scheduleDao.getById(id);
    }
    @Override
    public Schedule getByUserId(int user_id){
	return scheduleDao.getByUserId(user_id);
    }
}
