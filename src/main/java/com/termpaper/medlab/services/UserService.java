package com.termpaper.medlab.services;

import java.io.IOException;

import com.termpaper.medlab.model.PhotoToUpload;
import com.termpaper.medlab.model.User;

public interface UserService{
    
    public byte[] updatePhotoToUser(PhotoToUpload photo, int user_id) throws IOException;

    public void addNewUserDoctor(User user);

    public void updateUser(User user);

}
