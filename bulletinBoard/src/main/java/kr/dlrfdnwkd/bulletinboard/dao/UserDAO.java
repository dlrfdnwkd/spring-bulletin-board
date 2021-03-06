package kr.dlrfdnwkd.bulletinboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.dlrfdnwkd.bulletinboard.model.User;

@Repository
public class UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSources) {
		this.jdbcTemplate = new JdbcTemplate(dataSources);
	}
	
	public User login(String id) {
		String query = "select * from user where id ='"+id+"';";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					User user = new User();
					user.setId(rs.getString("id"));
					user.setPw(rs.getString("pw"));
					user.setName(rs.getString("name"));
					user.seteMail(rs.getString("email"));
					return user;
				}
			});
		}catch(EmptyResultDataAccessException e){
			user = null;
			return user;
		}
		return user;
	}
	public boolean userIdCheck(String id) {
		String query = "select * from user where id ='"+id+"';";
		try {
			User user = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					User SampleUser = new User();
					SampleUser.setId(rs.getString("id"));
					SampleUser.setPw(rs.getString("pw"));
					SampleUser.setName(rs.getString("name"));
					SampleUser.seteMail(rs.getString("email"));
					return SampleUser;
				}
			});
		}catch(EmptyResultDataAccessException e) {
			return true;
		}
		return false;
	}
	public boolean userEmailCheck(String email) {
		String query = "select * from user where email ='"+email+"';";
		try {
			User user = jdbcTemplate.queryForObject(query, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					User SampleUser = new User();
					SampleUser.setId(rs.getString("id"));
					SampleUser.setPw(rs.getString("pw"));
					SampleUser.setName(rs.getString("name"));
					SampleUser.seteMail(rs.getString("email"));
					return SampleUser;
				}
			});
		}catch(EmptyResultDataAccessException e) {
			return true;
		}
		return false;
	}
	public int updateUserInfo(User user) {
		return jdbcTemplate.update("update user set id=?, name=?, pw=? where email=?", user.getId(), user.getName(), user.getPw(), user.geteMail());
	}
	public int deleteUserInfo(User user) {
		return jdbcTemplate.update("delete from user where email=?",user.geteMail());
	}
	public int insertUserInfo(String email, String id, String pw) {
		return jdbcTemplate.update("insert into user(email,id,pw) values(?,?,?)",email,id,pw);
	}
	public int insertUserInfo(String email, String id, String pw, String name) {
		return jdbcTemplate.update("insert into user values(?,?,?,?)",id,pw,name,email);
	}
}
