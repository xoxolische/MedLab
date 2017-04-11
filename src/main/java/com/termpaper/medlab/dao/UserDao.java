package com.termpaper.medlab.dao;


import java.io.IOException;

import com.termpaper.medlab.model.PhotoToUpload;
import com.termpaper.medlab.model.User;
import com.termpaper.medlab.model.UserAuth;


public interface UserDao{
    
    public UserAuth getUserAuth(String email);

    byte[] updatePhotoToUser(PhotoToUpload photo, int user_id) throws IOException;

    public void addNewUserDoctor(User user);

    public void updateUser(User user);

}
