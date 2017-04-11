
package com.termpaper.medlab.controller.rest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.termpaper.medlab.model.PhotoToUpload;
import com.termpaper.medlab.services.UserService;


@RestController
public class UploadController{
    @Autowired 
    UserService userService;
    
    @PostMapping({"doctor/api/{user_id}/upload-photo", "patient/api/{user_id}/upload-photo"})
    public String uploadPhoto(@RequestParam MultipartFile file, @PathVariable int user_id) throws IOException {
	InputStream inputStream =  new BufferedInputStream(file.getInputStream());
	PhotoToUpload photo = new PhotoToUpload(inputStream);
	//return new String(Base64.encodeBase64(userService.updatePhotoToUser(photo, user_id)), "UTF-8");
	return userService.updatePhotoToUser(photo, user_id).toString();
	
    }
}