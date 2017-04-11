package com.termpaper.medlab.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.termpaper.medlab.dao.UserDao;
import com.termpaper.medlab.model.PhotoToUpload;
import com.termpaper.medlab.model.User;
import com.termpaper.medlab.model.UserAuth;

@Repository
public class UserDaoImpl implements UserDao{
    private static final String SQL_SELECT_USER_AUTH_BY_EMAIL = "SELECT users.id, users.email, users.password, roles.role FROM users INNER JOIN roles ON users.id_role=roles.id WHERE users.email=?";

    private static final String SQL_UPDATE_PHOTO_TO_USER = "UPDATE users SET photo = ? WHERE id = ?";

    private static final String SQL_INSERT_USER = "INSERT INTO users (email, password, name, sex, age, phone, id_role) VALUES (?, ?, ?, ?, ?, ?, ";
   
    private static final String SQL_INSERT_USER_WITH_ROLE_DOCTOR = SQL_INSERT_USER + "(select id from roles where roles.role = 'DOCTOR'))";

    private static final String SQL_UPDATE_USER = "UPDATE users SET email=?, password=?, name=?, sex=?, age=?, phone=? WHERE email=?";
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public UserAuth getUserAuth(String email) {
	List<UserAuth> list = jdbcTemplate.query(SQL_SELECT_USER_AUTH_BY_EMAIL, new RowMapper<UserAuth>() {
	    
	    @Override
	    public UserAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserAuth(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	    }
	}, email);
	return list.isEmpty() ? null : list.get(0);
    }
    
    @Override
    public byte[] updatePhotoToUser(final PhotoToUpload photo, final int user_id) throws IOException {
	try {
	    jdbcTemplate.update(SQL_UPDATE_PHOTO_TO_USER, photo.getStream(), user_id);
	    //jdbcTemplate.update(SQL_UPDATE_PHOTO_TO_USER, new String(Base64.encodeBase64(IOUtils.toByteArray(photo.getStream())), "UTF-8"), user_id);
	    System.out.println(IOUtils.toByteArray(photo.getStream()));
	    return IOUtils.toByteArray(photo.getStream());
	} catch (DataAccessException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public void addNewUserDoctor(User user){
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {

		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER_WITH_ROLE_DOCTOR, new String[] { "id" });
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSex());
			ps.setInt(5, user.getAge());
			ps.setString(6, user.getPhone());
			return ps;
		}
	}, keyHolder);
	user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void updateUser(User user){
	jdbcTemplate.update(SQL_UPDATE_USER, user.getEmail(), user.getPassword(), user.getName(), user.getSex(),user.getAge(), user.getPhone(), user.getEmail());
    }
    
    /*
    @Override
    public PhotoToUpload getImage(){
	List<PhotoToUpload> att = jdbcTemplate.query(SQL_SELECT_ATTACHMENT, new AttachmentRowMapper());
	return att.isEmpty() ? null : att.get(0);
    }
    */
}
