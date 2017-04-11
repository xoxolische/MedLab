package com.termpaper.medlab.dao;

import com.termpaper.medlab.model.Schedule;

public interface ScheduleDao{
    
    void setScheduleId(Schedule schedule);
    
    void add(Schedule schedule);

    Schedule getSchedule(Schedule schedule);

    Schedule getById(int id);

    Schedule getByUserId(int user_id);

}
