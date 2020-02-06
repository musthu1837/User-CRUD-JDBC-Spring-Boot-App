package com.musthafa.springboot.repositories;

import java.util.List;

import com.musthafa.springboot.models.UserEntity;

public interface UserRepository {

	List<UserEntity> findAll();

	int save(UserEntity user);

	int deleteById(int userId);

	UserEntity findById(int userId);

	int update(UserEntity user);

}