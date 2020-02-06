package com.musthafa.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musthafa.springboot.models.UserEntity;
import com.musthafa.springboot.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserEntity> listUsers() {
		// TODO Auto-generated method stub
		List<UserEntity> result = userRepository.findAll();
		if (result.isEmpty())
			return new ArrayList<UserEntity>();
		else
			return result;
	}

	@Override
	public int addUser(UserEntity user) {
		// TODO Auto-generated method stub
		Optional<UserEntity> existingUser = Optional.ofNullable(userRepository.findById(user.getUserId()));
		if (!existingUser.isPresent())
			return userRepository.save(user);
		else
			return 0;
	}

	@Override
	public int deleteUserById(int userId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> existingUser = Optional.ofNullable(userRepository.findById(userId));
		if (existingUser.isPresent())
			return userRepository.deleteById(userId);
		else
			return 0;
	}

	@Override
	public int updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		Optional<UserEntity> existingUser = Optional.ofNullable(userRepository.findById(user.getUserId()));
		if (existingUser.isPresent())
			return userRepository.update(user);
		else
			return 0;
	}

	@Override
	public Optional<UserEntity> listUserById(int userId) {

		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findById(userId));
	}

}
