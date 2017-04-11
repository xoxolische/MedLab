package com.termpaper.medlab.services;

import com.termpaper.medlab.model.Schedule;

public interface ScheduleService{
    
    void add(Schedule schedule);

    void setScheduleId(Schedule schedule);
    
    Schedule getById(int id);

    Schedule getByUserId(int user_id);
}
