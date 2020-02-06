package com.musthafa.springboot.services;

import java.util.List;
import java.util.Optional;

import com.musthafa.springboot.models.UserEntity;

public interface UserService {
	public List<UserEntity> listUsers();

	public Optional<UserEntity> listUserById(int userId);

	public int addUser(UserEntity user);

	public int deleteUserById(int userId);

	public int updateUser(UserEntity user);

}