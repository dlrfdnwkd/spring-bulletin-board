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
		User user = new User();
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
}
