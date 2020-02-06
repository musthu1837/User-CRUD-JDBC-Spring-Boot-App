package com.musthafa.springboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.musthafa.springboot.models.UserEntity;
import com.musthafa.springboot.repositories.UserRepository;
import com.musthafa.springboot.services.UserServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImplementation service;

	@Test
	public void onListUsersReturnsUserEntityList() {
		List<UserEntity> users = new LinkedList<UserEntity>();
		users.add(new UserEntity(1, 21, "Musthafa Mohammad", 10000));
		users.add(new UserEntity(2, 22, "Sravan Kumar", 10000));

		when(repository.findAll()).thenReturn(users);

		assertFalse(service.listUsers().isEmpty());
	}

	@Test
	public void onListUsersReturnsEmptyUserEntityList() {
		List<UserEntity> users = new LinkedList<UserEntity>();

		when(repository.findAll()).thenReturn(users);

		assertTrue(service.listUsers().isEmpty());
	}

	@Test
	public void onListUserByIdReturnsUserEntity() {
		when(repository.findById(anyInt())).thenReturn(new UserEntity(2, 22, "Sravan Kumar", 10000));

		assertTrue(service.listUserById(anyInt()).isPresent());
	}

	@Test
	public void onListUserByIdReturnsNull() {
		when(repository.findById(anyInt())).thenReturn(null);

		assertFalse(service.listUserById(anyInt()).isPresent());
	}

	@Test
	public void onAddUserByIdReturnsValueOne() {

		when(repository.findById(anyInt())).thenReturn(null);
		UserEntity user = new UserEntity(2, 22, "Sravan Kumar", 10000);
		when(repository.save(user)).thenReturn(1);

		assertEquals(service.addUser(user), 1);
	}

	@Test
	public void onAddUserByIdReturnsValueZero() {
		UserEntity user = new UserEntity(2, 22, "Sravan Kumar", 10000);
		when(repository.findById(anyInt())).thenReturn(user);

		assertEquals(service.addUser(user), 0);
	}

	@Test
	public void onDeleteUserByIdReturnsValueOne() {
		UserEntity user = new UserEntity(2, 22, "Sravan Kumar", 10000);
		when(repository.findById(anyInt())).thenReturn(user);
		when(repository.deleteById(anyInt())).thenReturn(1);
		assertEquals(service.deleteUserById(anyInt()), 1);
	}

	@Test
	public void onDeleteUserByIdReturnsValueZero() {
		when(repository.findById(anyInt())).thenReturn(null);
		assertEquals(service.deleteUserById(anyInt()), 0);
	}

	@Test
	public void onUpdateUserByIdReturnsValueOne() {
		UserEntity user = new UserEntity(2, 22, "Sravan Kumar", 10000);
		when(repository.findById(anyInt())).thenReturn(user);
		when(repository.update(user)).thenReturn(1);
		assertEquals(service.updateUser(user), 1);
	}

	@Test
	public void onUpdateUserByIdReturnsValueZero() {
		UserEntity user = new UserEntity(2, 22, "Sravan Kumar", 10000);
		when(repository.findById(anyInt())).thenReturn(null);
		assertEquals(service.updateUser(user), 0);
	}
}
