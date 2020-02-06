package com.musthafa.springboot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.musthafa.springboot.models.UserEntity;
import com.musthafa.springboot.repositories.UserRepositoryImplementation;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
	@Mock
	private JdbcTemplate template;

	@InjectMocks
	private UserRepositoryImplementation repository;

	@Test
	public void onFindAllReturnUserEntityList() throws SQLException {

		List<UserEntity> users = new LinkedList<UserEntity>();
		users.add(new UserEntity(1, 21, "Musthafa Mohammad", 10000));
		users.add(new UserEntity(2, 22, "Sravan Kumar", 10000));

		when(template.query(anyString(), any(RowMapper.class))).thenReturn(users);

		assertEquals(users, repository.findAll());
	}

}
