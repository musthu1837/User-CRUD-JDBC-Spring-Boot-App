package com.musthafa.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.musthafa.springboot.models.UserEntity;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from user_entity", (rs, rowNum) -> new UserEntity(rs.getInt("user_id"),
				rs.getString("user_name"), rs.getInt("user_age"), rs.getDouble("user_salary")));
	}

	public void save(UserEntity user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into user_entity (user_id, user_age, user_name, user_salary) values(?,?,?,?)",
				user.getUserId(), user.getUserAge(), user.getUserName(), user.getUserSalary());
	}

	public void deleteById(int userId) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from user_entity where user_id = ?", userId);
	}

	public Optional<UserEntity> findById(int userId) {
		try{
			return jdbcTemplate.queryForObject("select * from user_entity where user_id = ?", new Object[] { userId },
				(rs, rowNum) -> Optional.of(new UserEntity(rs.getInt("user_id"), rs.getString("user_name"),
						rs.getInt("user_age"), rs.getDouble("user_salary"))));
		}catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

	public void update(UserEntity user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
                "update user_entity set user_age=?, user_name=?, user_salary=?  where user_id = ?",
                user.getUserAge(), user.getUserName(), user.getUserSalary(), user.getUserId());
	}

}
