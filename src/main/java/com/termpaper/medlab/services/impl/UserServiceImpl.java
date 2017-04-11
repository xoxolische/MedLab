package com.termpaper.medlab.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.termpaper.medlab.dao.UserDao;
import com.termpaper.medlab.model.PhotoToUpload;
import com.termpaper.medlab.model.User;
import com.termpaper.medlab.services.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public byte[] updatePhotoToUser(PhotoToUpload photo, int user_id) throws IOException{
	return userDao.updatePhotoToUser(photo, user_id);
    }

    @Override
    public void addNewUserDoctor(User user){
	userDao.addNewUserDoctor(user);
    }

    @Override
    public void updateUser(User user){
	userDao.updateUser(user);
	
    }
    
    
    

}
