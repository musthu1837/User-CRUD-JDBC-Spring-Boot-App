package com.musthafa.springboot.services;

import java.util.List;

import com.musthafa.springboot.models.UserEntity;

public interface UserService {
	public List<UserEntity> listUsers();

	public UserEntity listUserById(int userId);

	public void addUser(UserEntity user);

	public void deleteUserById(int userId);

	public void updateUser(UserEntity user);

}