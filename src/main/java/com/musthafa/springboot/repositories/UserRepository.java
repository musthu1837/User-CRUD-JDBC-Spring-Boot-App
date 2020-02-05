package com.musthafa.springboot.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.musthafa.springboot.exceptions.UserAlreadyExistsException;
import com.musthafa.springboot.exceptions.UserNotFoundException;
import com.musthafa.springboot.models.UserEntity;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from user_entity", (rs, rowNum) -> new UserEntity(rs.getInt("user_id"),
				rs.getInt("user_age"), rs.getString("user_name"), rs.getDouble("user_salary")));
	}

	public int save(UserEntity user) {
		// TODO Auto-generated method stub
		jdbcTemplate.query("select * from user_entity where user_id=" + user.getUserId(), (rs) -> {
			if (rs.next()) {
				throw new UserAlreadyExistsException("User already exists with userId = " + user.getUserId());
			} else
				return null;
		});
		return jdbcTemplate.update(
				"insert into user_entity (user_id, user_age, user_name, user_salary) values(?,?,?,?)", user.getUserId(),
				user.getUserAge(), user.getUserName(), user.getUserSalary());
	}

	public int deleteById(int userId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from user_entity where user_id = ?", userId);
	}

	public UserEntity findById(int userId) {
		return jdbcTemplate.query("select * from user_entity where user_id=" + userId, (rs) -> {
			if (rs.next())
				return new UserEntity(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
			else
				throw new UserNotFoundException("id: " + userId);
		});
	}

	public void update(UserEntity user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update user_entity set user_age=?, user_name=?, user_salary=?  where user_id = ?",
				user.getUserAge(), user.getUserName(), user.getUserSalary(), user.getUserId());
	}

}
